#include "StarPuzzle.h"
#include "PopStarSolver.h"
#include "GameScene.h"
#include <cstdlib>
#include <cstring>
#include <algorithm>

using namespace std;

StarPuzzle::StarPuzzle(int row, int col)
    : m_row(row)
    , m_col(col)
    , m_size(row * col)
    , m_score(0)
    , m_step(0)
{
	for (int i = 0; i < m_size; i++) {
		m_stars[i] = 0;
	}
	m_solver = new PopStarSolver(row, col);
	m_emitterArray = new CCArray;
}

StarPuzzle::~StarPuzzle() {
	clearPuzzle();
	delete m_solver;
	m_emitterArray->release();
}

bool StarPuzzle::attach(CCNode* node) {
	if (node == NULL)
		return false;
	bool succ = true;
	for (int i = 0; i < m_size; i++) {
		if (m_stars[i] != 0) {
			succ &= m_stars[i]->attach(node);
		}
	}
	return succ;
}

HitResult StarPuzzle::hitTest(const CCPoint& point) {
	//CCLog("point %0.2f, %0.2f, row %d, col %d", point.x, point.y, row, col);
	int index = getHitIndex(point);

	HitResult result;
	if (index < m_size && m_stars[index] != 0) {
		std::set<int> adjacent = m_solver->adjacent(m_matrix, index);
		doUnSelection();
		if (adjacent.size() <= 1) {
		    result.type = HitResult::HIT_NOTHING;
			result.data = 0;
		}
		else {
			m_selectedStarIndex.clear();
			doSelection(adjacent);

			m_changes = m_solver->step(m_matrix, index);
			result.type = HitResult::HIT_POPSTAR;
			result.data = m_selectedStarIndex.size();
			result.location = m_stars[index]->getPosition();
			result.popSelects = m_selectedStarIndex;
			//result.type = HitResult::HIT_STAR;
			//result.data = m_selectedStarIndex.size();
		}
	}
	else {
		doUnSelection();
		result.type = HitResult::HIT_NOTHING;
		result.data = 0;
	}
	return result;
}

void StarPuzzle::theMostSelect() {
	std::set<int> adjacentMax;
	for (int i = 0; i < m_size; i++) {
		if (m_matrix[i] != UNDEFINED) {
			std::set<int> adjacent = m_solver->adjacent(m_matrix, i);
			if (adjacent.size() > adjacentMax.size()) {
				adjacentMax.clear();
				adjacentMax = adjacent;
			}
		}
	}

	std::set<int>::iterator iter = adjacentMax.begin();
	for (; iter != adjacentMax.end(); iter++) {
		int i = *iter;
		if (m_matrix[i] != UNDEFINED && m_stars[i] != NULL) {
			CCScaleBy *scaleBy = CCScaleBy::create(0.5f, 0.92f);
			CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), NULL);
			CCRepeat *repeat = CCRepeat::create(seq, 2);
			m_stars[i]->runAction(repeat);
		}
	}
}

float StarPuzzle::puzzleStartAnimation() {
	float delayTime = 0.0f;
	float delayFactor = 0.03f;
	float baseActionTime = 0.5f;
	float actionTime = 0.0f;
	CCFiniteTimeAction *delay, *action, *seq;
	for (int i = 0; i < m_size; i++) {
		if (m_stars[i] != 0) {
			int r = row(i);
			int c = column(i);
			m_stars[i]->setPosition(m_left + c * STAR_WIDTH, m_top);
			delayTime = r * delayFactor * 2 + delayFactor * (i % 2);
			delay = CCDelayTime::create(delayTime);
			actionTime = (1 - r * STAR_HEIGHT / (m_top - m_bottom)) * baseActionTime;
			action = CCMoveTo::create(actionTime, CCPoint(m_left + c * STAR_WIDTH, m_bottom + r * STAR_HEIGHT));
			seq = CCSequence::create(delay, action, NULL);
			m_stars[i]->runAction(seq);
		}
	}
	return delayTime+actionTime+0.2f;
}

void StarPuzzle::popStar() {
    std::set<int>::iterator iter = m_selectedStarIndex.begin();
    for (; iter != m_selectedStarIndex.end(); iter++) {
    	explodeStar(*iter);
    }
    GameScene::instance()->playEffect("pop.ogg");
    m_selectedStarIndex.clear();
}

void StarPuzzle::popStar(int index) {
	if (index < m_size) {
		explodeStar(index);
		GameScene::instance()->playEffect("pop.ogg");
		m_selectedStarIndex.erase(index);
	}
}

void StarPuzzle::removeAndPopTailStar() {
	for (int i = m_size-1; i >= 0; i --) {
		if (m_stars[i] != NULL) {
			m_matrix[i] = UNDEFINED;
			explodeStar(i);
			GameScene::instance()->playEffect("pop.ogg");
			return;
		}
	}
}

void StarPuzzle::removeAndPopAllRemainStars() {
	for (int i = 0; i < m_size; i++) {
		if (m_stars[i] != NULL) {
			m_matrix[i] = UNDEFINED;
			explodeStar(i);
		}
	}
	GameScene::instance()->playEffect("pop.ogg");
}

void StarPuzzle::applyChanges() {
    std::map<int, int>::iterator iter = m_changes.begin();
    int newPos;
    for (; iter != m_changes.end(); iter++) {
    	newPos = iter->first;
        m_stars[newPos] = m_stars[iter->second];
        m_stars[iter->second] = NULL;
        m_stars[newPos]->moveTo(m_left + column(newPos) * STAR_WIDTH, m_bottom + row(newPos) * STAR_HEIGHT);
    }
    m_changes.clear();
}

bool StarPuzzle::isSolved() {
	return m_solver->solved(m_matrix);
}

int StarPuzzle::remainStars() {
	return m_solver->remain(m_matrix);
}

int StarPuzzle::bonus() {
	return m_solver->bonus(m_matrix);
}

int StarPuzzle::bonus(int remainCount) {
	return m_solver->bonus(remainCount);
}

int StarPuzzle::score(int count) {
	return m_solver->score(count);
}

int StarPuzzle::evaluateMaxScore() {
	int* copyedMatrix = new int[m_size];
	memcpy(copyedMatrix, m_matrix, m_size * sizeof(int));
	int score = 0;
	int index = UNDEFINED;
	while (!m_solver->solved(copyedMatrix)) {
		index = m_solver->next(copyedMatrix);
		score += m_solver->score(m_solver->adjacentCount(copyedMatrix, index));
		m_solver->step(copyedMatrix, index);
	}
	score += m_solver->bonus(copyedMatrix);
	delete[] copyedMatrix;
	return score;
}

cocos2d::CCPoint StarPuzzle::getStarPotisionByIndex( int index ) {
	CCPoint pos;

	if (index > m_size || index < 0)
		return pos;

	int r = row(index);
	int c = column(index);
	pos = ccp(m_left + c * STAR_WIDTH, m_bottom + r * STAR_HEIGHT);

	return pos;
}

bool StarPuzzle::bombed( const CCPoint& point ) {
	int index = getHitIndex(point);

	if (index < m_size && m_stars[index] != 0) {
		std::set<int> result = m_solver->bombed(m_matrix, index);

		std::set<int>::iterator iter = result.begin();
		for (; iter != result.end(); iter++) {
			int i = *iter;
			if (m_stars[i] != NULL) {
				m_matrix[i] = UNDEFINED;
				explodeStar(i);
			}
		}
		GameScene::instance()->playEffect("Props_Bomb.ogg");
		m_changes = m_solver->step(m_matrix, index);
		applyChanges();

		return true;
	}
	return false;
}

bool StarPuzzle::changeStarByIndex( int toType, int index ) {
	if (index > m_size || index < 0)
		return false;

	if (m_stars[index] != NULL) {
		m_matrix[index] = toType;
		m_stars[index]->changeStarType(toType);
	}

	return true;
}

float StarPuzzle::doRefreshAction() {
	float delay = 0.25f;

	for (int i = 0; i < m_size; i++) {
		if (m_matrix[i] != UNDEFINED && m_stars[i] != NULL) {
			m_stars[i]->setScale(STAR_SCALE_FACTOR);
			CCScaleBy *scaleBy = CCScaleBy::create(delay, 0.2f);
			CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), NULL);
			m_stars[i]->runAction(seq);
		}
	}
	return delay;
}

void StarPuzzle::resumeStarsSize() {
	for (int i = 0; i < m_size; i++) {
		if (m_matrix[i] != UNDEFINED && m_stars[i] != NULL) {
			m_stars[i]->setScale(STAR_SCALE_FACTOR);
		}
	}
}

void StarPuzzle::stopAllStarAction() {
	for (int i = 0; i < m_size; i++) {
		if (m_matrix[i] != UNDEFINED && m_stars[i] != NULL) {
			m_stars[i]->stopAction();
		}
	}
}

void StarPuzzle::refreshPuzzle() {
	for (int i = 0; i < m_size; i++) {
		if (m_matrix[i] != UNDEFINED) {
			int starType = rand() % 5 + 1;
			m_matrix[i] = starType;
			m_stars[i]->changeStarType(starType);
		}
	}
}

int StarPuzzle::getHitIndex(const CCPoint& point) {
	int row = max(0, (int)((point.y - m_bottom) / STAR_HEIGHT));
	int col = max(0, (int)((point.x - m_left) / STAR_WIDTH));
	int index = row * m_col + col;

	return index;
}

bool StarPuzzle::isDefined( int index ) {
	if (index > m_size || index < 0)
		return false;

	return (m_matrix[index] > 0);
}

void StarPuzzle::initPuzzle(PuzzleLevel level) {
	for (int i = 0; i < 10; i++) {
		//CCLog("generatePuzzle times %d", i+1);
		clearPuzzle();
		generatePuzzle(level);
		if (evaluateMaxScore() >= 2000)
			break;
	}
	createStars();
	hideAllStars();
}

void StarPuzzle::clearPuzzle() {
	for (int i = 0; i < m_size; i++) {
		if (m_stars[i] != NULL) {
			delete m_stars[i];
			m_stars[i] = NULL;
		}
	}
	CCObject* it = NULL;
	CCARRAY_FOREACH(m_emitterArray, it) {
		CCParticleSystem *emitter = dynamic_cast<CCParticleSystem*>(it);
		emitter->removeFromParent();
	}
	m_emitterArray->removeAllObjects();
	m_score = 0;
	m_step = 0;
}

std::string StarPuzzle::serialization() {
	std::string s;
	for (int i = 0; i < m_size; i++) {
		if (m_stars[i] == 0)
			s += '0';
		else
			s += '0'+m_stars[i]->type();
	}
	return s;
}

void StarPuzzle::loadPuzzle(std::string s) {
	assert(s.length() == m_size);
	clearPuzzle();
	for (int i = 0; i < m_size; i++) {
		if (s[i] == '0')
			m_matrix[i] = UNDEFINED;
		else
			m_matrix[i] = s[i] - '0';
	}
	createStars();
	hideAllStars();
}

void StarPuzzle::hideAllStars() {
	for (int i = 0; i < m_size; i++) {;
		if (m_stars[i] != 0) {
			int r = row(i);
			int c = column(i);
			m_stars[i]->setPosition(m_left + c * STAR_WIDTH, m_top);
		}
	}
}

void StarPuzzle::resetStarsPosition() {
	for (int i = 0; i < m_size; i++) {
		if (m_stars[i] != 0) {
			int r = row(i);
			int c = column(i);
			m_stars[i]->setPosition(m_left + c * STAR_WIDTH, m_bottom + r * STAR_HEIGHT);
		}
	}
}

void StarPuzzle::doSelection(std::set<int>& selectList) {
	if (selectList.size() <= 1)
		return;
	m_selectedStarIndex = selectList;
	std::set<int>::iterator iter = m_selectedStarIndex.begin();
	for (; iter != m_selectedStarIndex.end(); iter++) {
		if (m_stars[*iter] != 0)
			m_stars[*iter]->select();
	}
}

void StarPuzzle::doUnSelection() {
	std::set<int>::iterator iter = m_selectedStarIndex.begin();
	for (; iter != m_selectedStarIndex.end(); iter++) {
		if (m_stars[*iter] != 0)
			m_stars[*iter]->unselect();
	}
	m_selectedStarIndex.clear();
}

void StarPuzzle::generatePuzzle(PuzzleLevel level) {
	switch (level) {
		case EASY:   generateEasyPuzzle();     break;
		case NORMAL: generateNormalPuzzle();   break;
		case HARD:   generateHardPuzzle();     break;
		case EXPERT: generateExpertPuzzle();   break;
		default:     generateNormalPuzzle();   break;
	}
}

// 15%几率和上一个相同
void StarPuzzle::generateEasyPuzzle() {
	int var = 0;
	for (int i = 0; i < m_size; i++) {
		var = rand() % 100;
		if (var < 15 && i > 0)
			m_matrix[i] = m_matrix[i-1];
		else
			m_matrix[i] = var % 5 + 1;
	}
}

void StarPuzzle::generateNormalPuzzle() {
	for (int i = 0; i < m_size; i++) {
		m_matrix[i] = rand() % 5 + 1;
	}
}

void StarPuzzle::generateHardPuzzle() {
	int var = 0;
	for (int i = 0; i < m_size; i++) {
		var = rand() % 100;
		if (var < 10 && i > 0)
			m_matrix[i] = m_matrix[i-1];
		else
			m_matrix[i] = var % 5 + 1;
	}
}

void StarPuzzle::generateExpertPuzzle() {
}

void StarPuzzle::createStars() {
	for (int i = 0; i < m_size; i++) {
		if (m_matrix[i] != UNDEFINED)
			m_stars[i] = new Star(Star::getType(m_matrix[i]));
		else
			m_stars[i] = NULL;
	}
}

void StarPuzzle::explodeStar(int index) {
	m_emitterArray->addObject(m_stars[index]->explode());
	delete m_stars[index];
	m_stars[index] = NULL;
}

int StarPuzzle::row(int index) {
	return (int)(index / m_col);
}

int StarPuzzle::column(int index) {
	return index % m_col;
}

void StarPuzzle::dumpStatus() {
	std::set<int>::iterator iter = m_selectedStarIndex.begin();
	std::string s;
	char buff[16];
	for (; iter != m_selectedStarIndex.end(); iter++) {
		int len = sprintf(buff, "%d, ", *iter);
		s.append(buff, len);
	}
	//CCLog("selected: %s", s.c_str());
	s = "";
	std::map<int, int>::iterator it = m_changes.begin();
	for (; it != m_changes.end(); it++) {
        int len = sprintf(buff, "%d->%d, ", it->first, it->second);
        s.append(buff, len);
    }
	//CCLog("changes: %s", s.c_str());
}
