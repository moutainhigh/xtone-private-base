#include "GameScene.h"
#include "SimpleAudioEngine.h"
#include "StarPuzzle.h"
#include "MainMenuLayer.h"
#include "GameOverLayer.h"
#include "GamePauseLayer.h"
#include "BrushLayer.h"
#include "Config.h"
#include "FetchLayer.h"
#include "FireworkLayer.h"
#include "Language.h"
#include "InGameFetchLayer.h"

USING_NS_CC;

#define PUZZLE_SIZE 10

GameScene* GameScene::m_instance = NULL;

const int MAIN_MENU_TAG = 10;
const int GAME_OVER_TAG = 20;
const int GAME_PAUSE_TAG = 30;
const int GAME_LAYER_TAG = 40;
const int GAME_FETCH_TAG = 50;

GameScene::GameScene()
{
	m_scene = CCScene::create();
	m_scene->retain();

	m_gameLayer = GameLayer::create();
	m_gameLayer->retain();
	m_gameLayer->setTag(GAME_LAYER_TAG);

	m_mainMenuLayer = MainMenuLayer::create();
	m_mainMenuLayer->retain();
	m_mainMenuLayer->setTag(MAIN_MENU_TAG);

	m_gameOverLayer = GameOverLayer::create();
	m_gameOverLayer->retain();
	m_gameOverLayer->setTag(GAME_OVER_TAG);

	m_gamePauseLayer = GamePauseLayer::create();
	m_gamePauseLayer->retain();
	m_gamePauseLayer->setTag(GAME_PAUSE_TAG);

	m_fetchLayer = FetchLayer::create();
	m_fetchLayer->retain();
	m_fetchLayer->setTag(GAME_FETCH_TAG);

	m_fireworkLayer = FireworkLayer::create();
	m_fireworkLayer->retain();
	m_scene->addChild(m_fireworkLayer, 1000);
	
	m_visibleSize = CCDirector::sharedDirector()->getVisibleSize();
	m_origin = CCDirector::sharedDirector()->getVisibleOrigin();
}

GameScene::~GameScene()
{
	CC_SAFE_RELEASE(m_scene);
	CC_SAFE_RELEASE(m_gameLayer);
	CC_SAFE_RELEASE(m_mainMenuLayer);
	CC_SAFE_RELEASE(m_gameOverLayer);
	CC_SAFE_RELEASE(m_gamePauseLayer);
	CC_SAFE_RELEASE(m_fetchLayer);
	CC_SAFE_RELEASE(m_fireworkLayer);
}

GameScene* GameScene::instance()
{
	if (m_instance == NULL)
		m_instance = new GameScene();
	return m_instance;
}

void GameScene::playEffect(const char *file)
{
	if (Config::instance()->mute() == false) {
		CocosDenshion::SimpleAudioEngine::sharedEngine()->playEffect(file);
	}
}

//mainmenu layer
void GameScene::startNewPuzzle()
{
	Config::instance()->clearSavedPuzzle();
	Config::instance()->flush();

	m_scene->removeChild(m_mainMenuLayer);
	m_scene->addChild(m_gameLayer);
	m_gameLayer->nextStage();

	m_fireworkLayer->stopFire();
	playEffect("readygo.ogg");
}

void GameScene::resumePuzzle()
{
	m_fireworkLayer->stopFire();
	m_scene->removeChild(m_mainMenuLayer);
	m_scene->addChild(m_gameLayer);
	Config* conf = Config::instance();
	std::string puzzle = conf->puzzle();
	int row = conf->row();
	int col = conf->col();
	int stage = conf->stage();
	int score = conf->score();
	int target = conf->target();
	m_gameLayer->resumePuzzle(puzzle, row, col, stage, score, target);
}

void GameScene::showMainMenu()
{
	m_gameLayer->resetStatus();
	m_scene->removeChild(m_gameLayer);
	m_scene->addChild(m_mainMenuLayer);

	delayedFire();
}

//gameover layer
void GameScene::showGameOver()
{
	m_gameLayer->addChild(m_gameOverLayer, 100);
}

void GameScene::closeGameOver()
{
	m_gameLayer->savePuzzle(true);
	m_gameLayer->removeChild(m_gameOverLayer);
	showMainMenu();
}

void GameScene::continueGameOver()
{
	m_gameLayer->backStageStartScore();
	m_gameLayer->newStage();
}

//pause layer
void GameScene::showPause()
{
	m_gameLayer->disableInput();
	m_gameLayer->addChild(m_gamePauseLayer, 100);
}

void GameScene::resumeGameFromPause()
{
	m_gameLayer->enableInput();
}

void GameScene::returnToMainMenuFromPause()
{
	m_gameLayer->savePuzzle(false);
	m_fireworkLayer->stopFire();
	showMainMenu();
}

//brush use
void GameScene::useBrushByType( int type, int index )
{
	m_gameLayer->useBrush(type, index);
}

void GameScene::returnToGameLayerFromBrushLayer()
{
	m_gameLayer->enableInput();
}

//InGameFetchLayer
void GameScene::returnToGameLayerFromInGameFetchLayer()
{
	m_gameLayer->enableInput();
	m_gameLayer->gamelayerWinAnimation();
	m_gameLayer->registPaymentDelegate();
}

//fetch layer
void GameScene::showFetchLayer()
{
	m_gameOverLayer->disableInput();
	m_gameLayer->disableInput();
	m_gameLayer->addChild(m_fetchLayer, 100);
}

void GameScene::returnToGameLayerFromFetchLayer()
{
	if (m_gameLayer->getChildByTag(GAME_OVER_TAG) != NULL) {
		m_gameOverLayer->enableInput();
		m_gameLayer->removeChild(m_fetchLayer);
	} else {
		m_gameLayer->enableInput();
		m_gameLayer->removeChild(m_fetchLayer);
	}
}

//buy
void GameScene::buyDiamonds( int dollar, const char *orderId, const char *orderDesc )
{
	PayHelper::instance()->payment(dollar, orderId, orderDesc);
}

//shop layer
void GameScene::returnToMainMenuFromShop()
{
	m_mainMenuLayer->enableInput();
	delayedFire();
}

//help layer
void GameScene::returnToMainMenuFromHelp()
{
	m_mainMenuLayer->enableInput();
	delayedFire();
}

//exchange layer
void GameScene::returnToMainMenuFromExchange()
{
	m_mainMenuLayer->enableInput();
	delayedFire();
}

//gift layer
void GameScene::returnToMainMenuFromGift()
{
	m_mainMenuLayer->enableInput();
	m_mainMenuLayer->resumeMainMenu();
	delayedFire();
}

//fire
void GameScene::firework()
{
	m_fireworkLayer->fire(false);
}

void GameScene::stopFirework()
{
	m_fireworkLayer->stopFire();
}

void GameScene::delayedFire()
{
	m_fireworkLayer->delayedFire(2.0f, true);
}


//GameLayer
GameLayer::GameLayer()
{
	m_puzzle = NULL;
	m_isPoping = false;
	m_isStageClear = false;
	m_propType = PropNone;

	m_targetScoreArray[0] = 0;
	m_targetScoreArray[1] = 1000;
	m_targetScoreArray[2] = 3000/*2500*/;
	m_targetScoreArray[3] = 5500/*4500*/;
}

GameLayer::~GameLayer()
{
	delete m_puzzle;
}

bool GameLayer::init()
{
    if ( !CCLayer::init() )
        return false;

	CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
	CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
	float scalef = visibleSize.height/DESIGN_HEIGHT;

	m_topCenter = CCPoint(origin.x + visibleSize.width/2, origin.y + visibleSize.height);
	m_rightCenter = CCPoint(origin.x + visibleSize.width, origin.y + visibleSize.height/2);

	CCSprite *bg = CCSprite::create("bg_mainscene.jpg");
	bg->setAnchorPoint(ccp(0.5, 0.5));
	bg->setPosition(ccp(visibleSize.width/2, visibleSize.height/2));
	bg->setScaleX(visibleSize.width/bg->getContentSize().width);
	bg->setScaleY(visibleSize.height/bg->getContentSize().height);
	addChild(bg);

	m_status.mute = Config::instance()->mute();
	m_status.highScore = Config::instance()->highScore();

	/*if (Config::instance()->mute() == false) {
	CocosDenshion::SimpleAudioEngine::sharedEngine()->playBackgroundMusic("music.ogg", true);
	}*/
	
	//labels
	//high score

	CCSprite *labelBg_02 = CCSprite::create("label_bg2.png");
	labelBg_02->setAnchorPoint(ccp(0.5, 1));
	labelBg_02->setPosition(ccp(m_topCenter.x, m_topCenter.y - 15*scalef));
	this->addChild(labelBg_02);

	m_bestScoreLabel = CCLabelTTF::create(
		CCString::createWithFormat("%s%d", GET_STRING("HighestScore"), m_status.highScore)->getCString(), "Arial", 28);
	m_bestScoreLabel->setAnchorPoint(ccp(0.5, 0.5));
	//m_bestScoreLabel->setPosition(ccp(m_topCenter.x, m_topCenter.y - 25*scalef));
	m_bestScoreLabel->setPosition(ccp(labelBg_02->getContentSize().width/2, labelBg_02->getContentSize().height/2));
	labelBg_02->addChild(m_bestScoreLabel);

	CCSprite *labelBg_01 = CCSprite::create("label_bg1.png");
	labelBg_01->setAnchorPoint(ccp(0, 1));
	labelBg_01->setPosition(ccp(5, m_topCenter.y - 70*scalef));
	this->addChild(labelBg_01);

	//stage
	m_stageNumLabel = CCLabelTTF::create(
		CCString::createWithFormat("%s%d", GET_STRING("Roundtext"), m_status.stage)->getCString(), "Arial", 28);
	m_stageNumLabel->setAnchorPoint(ccp(0, 0));
	m_stageNumLabel->setPosition(ccp(10, 12));
	labelBg_01->addChild(m_stageNumLabel);
	//target
	m_targetScoreLabel = CCLabelTTF::create(
		CCString::createWithFormat("%s%d", GET_STRING("TargetScoretext"), m_status.target)->getCString(), "Arial", 28);
	m_targetScoreLabel->setAnchorPoint(ccp(0, 0));
	m_targetScoreLabel->setPosition(ccp(40 + m_stageNumLabel->getContentSize().width, 12));
	labelBg_01->addChild(m_targetScoreLabel);

	//score
	m_scoreNumLabel = CCLabelTTF::create("0", "Arial", 40);
	m_scoreNumLabel->setAnchorPoint(ccp(0.5, 0.5));
	m_scoreNumLabel->setPosition(ccp(m_topCenter.x - 45, m_topCenter.y - 180*scalef));
	this->addChild(m_scoreNumLabel);
	m_scoreLabelPos = m_scoreNumLabel->getPosition();

	//props
	m_bombSprite = CCSprite::create("Props_Bomb.png");
	m_bombSprite->setAnchorPoint(ccp(0.5, 0.5));
	m_bombSprite->setPosition(ccp(m_topCenter.x+80, m_scoreLabelPos.y));
	this->addChild(m_bombSprite);

	m_brushSprite = CCSprite::create("Props_Paint.png");
	m_brushSprite->setAnchorPoint(ccp(0.5, 0.5));
	m_brushSprite->setPosition(ccp(m_topCenter.x+80+60, m_scoreLabelPos.y));
	this->addChild(m_brushSprite);

	m_refreshSprite = CCSprite::create("Props_Rainbow.png");
	m_refreshSprite->setAnchorPoint(ccp(0.5, 0.5));
	m_refreshSprite->setPosition(ccp(m_topCenter.x+80+60*2, m_scoreLabelPos.y));
	this->addChild(m_refreshSprite);
	
	//curr star score
	m_currScoreTotalLabel = CCLabelTTF::create("0 combos 0 scores", "Arial", 34);
	m_currScoreTotalLabel->setAnchorPoint(ccp(0.5, 0));
	m_currScoreTotalLabel->setPosition(ccp(m_topCenter.x, STAR_HEIGHT*10 + 20*scalef));
	m_currScoreTotalLabel->setVisible(false);
	this->addChild(m_currScoreTotalLabel);
	
	//表扬动画
	m_goodPopstar = CCSprite::create("combo_1.png");
	m_goodPopstar->setAnchorPoint(ccp(0.5, 0.5));
	m_goodPopstar->setPosition(ccp(m_topCenter.x, m_rightCenter.y));
	m_goodPopstar->setVisible(false);
	this->addChild(m_goodPopstar, 10);

	m_betterPopstar = CCSprite::create("combo_2.png");
	m_betterPopstar->setAnchorPoint(ccp(0.5, 0.5));
	m_betterPopstar->setPosition(ccp(m_topCenter.x, m_rightCenter.y));
	m_betterPopstar->setVisible(false);
	this->addChild(m_betterPopstar, 10);

	m_fantasticPopstar = CCSprite::create("combo_3.png");
	m_fantasticPopstar->setAnchorPoint(ccp(0.5, 0.5));
	m_fantasticPopstar->setPosition(ccp(m_topCenter.x, m_rightCenter.y));
	m_fantasticPopstar->setVisible(false);
	this->addChild(m_fantasticPopstar, 10);

	//恭喜过关
	m_stageClearSprite = CCSprite::create("stage_clear.png");
	m_stageClearSprite->setAnchorPoint(ccp(0.5, 0.5));
	m_stageClearSprite->setPosition(ccp(m_topCenter.x, m_rightCenter.y));
	m_stageClearSprite->setVisible(false);
	this->addChild(m_stageClearSprite, 10);

	//暂停按钮
	m_pauseBtn = CCSprite::create("Item_pause.png");
	m_pauseBtn->setAnchorPoint(ccp(0.5, 0.5));
	m_pauseBtn->setPosition(ccp(50, m_scoreLabelPos.y));
	this->addChild(m_pauseBtn);

	//钻石领取
	m_diamondFetchBtn = CCSprite::create("Dialog_Item.png");
	m_diamondFetchBtn->setAnchorPoint(ccp(1, 1));
	m_diamondFetchBtn->setPosition(
		ccp(visibleSize.width-5, m_topCenter.y - 70*scalef));
	this->addChild(m_diamondFetchBtn);
	
	CCSprite *diamondSprite = CCSprite::create("diamond.png");
	diamondSprite->setAnchorPoint(ccp(0, 0));
	diamondSprite->setPosition(ccp(0, 0));
	m_diamondFetchBtn->addChild(diamondSprite);

	CCSprite *addSprite = CCSprite::create("coin_add.png");
	addSprite->setAnchorPoint(ccp(1, 0));
	addSprite->setPosition(ccp(m_diamondFetchBtn->getContentSize().width, 0));
	m_diamondFetchBtn->addChild(addSprite);

	m_currDiamonds = Config::instance()->diamonds();
	m_diamondLabel = CCLabelTTF::create(
		CCString::createWithFormat("%d", m_currDiamonds)->getCString(), "Arial", 28);
	m_diamondLabel->setAnchorPoint(ccp(0.5, 0.5));
	m_diamondLabel->setPosition(
		ccp(m_diamondFetchBtn->getContentSize().width/2, m_diamondFetchBtn->getContentSize().height/2));
	m_diamondFetchBtn->addChild(m_diamondLabel);

	//test
	//Config::instance()->addDiamond(0);
	
    return true;
}

void GameLayer::onEnter()
{
	CCLayer::onEnter();

	m_needAddDiamonds = 0;
	//监听付费消息
	PayHelper::instance()->addDelegate(this);
	//更新钻石数量
	this->schedule(schedule_selector(GameLayer::scheduleDiamondLabels), 0.1f);

	//更新钻石数量
	updateDiamond();
}

void GameLayer::updateScoreLabel()
{
	m_scoreNumLabel->setString(
		CCString::createWithFormat("%d", m_status.score)->getCString());
}

void GameLayer::updateStatus()
{
	m_bestScoreLabel->setString(
		CCString::createWithFormat("%s%d", GET_STRING("HighestScore"), m_status.highScore)->getCString());

	m_stageNumLabel->setString(
		CCString::createWithFormat("%s%d", GET_STRING("Roundtext"), m_status.stage)->getCString());

	m_targetScoreLabel->setString(
		CCString::createWithFormat("%s%d", GET_STRING("TargetScoretext"), m_status.target)->getCString());

	m_scoreNumLabel->setString(
		CCString::createWithFormat("%d", m_status.score)->getCString());

	updateDiamond();
}

void GameLayer::updateComboLabel(int combos)
{
	m_currScoreTotalLabel->setVisible(true);
	int scores = m_puzzle->score(combos);
	m_currScoreTotalLabel->setString(
		CCString::createWithFormat(GET_STRING("cleanscore"), combos, scores)->getCString());
}

void GameLayer::updatePropTipsLabel( PropType type )
{
	m_currScoreTotalLabel->setVisible(true);
	const char *strTips = NULL;
	if (type == PropBomb) {
		strTips = GET_STRING("PropsTips_Bomb");
	}
	else if (type == PropBrush) {
		strTips = GET_STRING("PropsTips_Paint");
	}
	else if (type == PropRefresh) {
		strTips = GET_STRING("PropsTips_Rainbow");
	}
	m_currScoreTotalLabel->setString(
		CCString::createWithFormat("%s", strTips)->getCString());
}

void GameLayer::updateHighScore( int score )
{
	if (score > m_status.highScore) {
		m_status.highScore = score;
		m_bestScoreLabel->setString(
			CCString::createWithFormat("%s%d", GET_STRING("HighestScore"), m_status.highScore)->getCString());

		Config::instance()->updateHighScore(score);
	}
}

void GameLayer::updateDiamond()
{
	int num = Config::instance()->diamonds();
	m_diamondLabel->setString(
		CCString::createWithFormat("%d", num)->getCString());
}

void GameLayer::createPuzzle( int row, int col )
{
	if (m_puzzle)
		delete m_puzzle;

	disableInput();
	m_puzzle = new StarPuzzle(row, col);
	m_puzzle->setTop(m_topCenter.y);
	m_puzzle->setLeft(GameScene::instance()->visibleOrigin().x);
	m_puzzle->setBottom(GameScene::instance()->visibleOrigin().y);
	m_puzzle->setRight(m_rightCenter.x);
	m_puzzle->initPuzzle(StarPuzzle::NORMAL);
	m_puzzle->attach(this);
}

void GameLayer::resumePuzzle(std::string puzzle, int row, int col, int stage, int score, int target)
{
	if (m_puzzle)
		delete m_puzzle;

	disableInput();
	m_puzzle = new StarPuzzle(row, col);
	m_puzzle->setTop(m_topCenter.y);
	m_puzzle->setLeft(GameScene::instance()->visibleOrigin().x);
	m_puzzle->setBottom(GameScene::instance()->visibleOrigin().y);
	m_puzzle->setRight(m_rightCenter.x);
	m_puzzle->loadPuzzle(puzzle);
	m_puzzle->attach(this);
	m_status.stage = stage;
	m_status.score = score;
	m_status.target = target;

	bool isGameOver = Config::instance()->isGameOver();
	if (!isGameOver) {
		//show stage info and start resume game
		showStageInfoAndStart();
	} else {
		GameScene::instance()->showGameOver();
	}
}

void GameLayer::savePuzzle(bool bGameOver)
{
	if (m_puzzle == NULL)
		return;

	std::string puzzle = m_puzzle->serialization();
	Config::instance()->savePuzzle(puzzle, PUZZLE_SIZE, PUZZLE_SIZE, m_status.stage, m_status.score, m_status.target, bGameOver);
	Config::instance()->flush();
}

void GameLayer::enableInput()
{
	enableTouchEvent();
	enableKeypad();
}

void GameLayer::disableInput()
{
	disableTouchEvent();
	disableKeypad();
}

bool GameLayer::isInputEnabled()
{
	return isKeypadEnabled() && isTouchEnabled();
}

void GameLayer::enableTouchEvent()
{
	this->setTouchEnabled(true);
	CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this,0);
}

void GameLayer::disableTouchEvent()
{
	this->setTouchEnabled(false);
	CCDirector::sharedDirector()->getTouchDispatcher()->removeDelegate(this);
}

void GameLayer::enableKeypad()
{
	this->setKeypadEnabled(true);
}

void GameLayer::disableKeypad()
{
	this->setKeypadEnabled(false);
}

void GameLayer::keyBackClicked()
{
	GameScene::instance()->showPause();
}

void GameLayer::showBrush(float arrowX, float y, int index)
{
	BrushLayer *brush = BrushLayer::create();
	brush->setAnchorPoint(ccp(0.5, 0));
	brush->setPosition(ccp(m_topCenter.x, y));
	brush->setArrowX(arrowX);
	brush->setNeedChangeIndex(index);
	this->addChild(brush);

	disableInput();
}

void GameLayer::useBrush( int starType, int index )
{
	CCLog("starType=%d", starType);
	m_puzzle->changeStarByIndex(starType, index);
	GameScene::instance()->playEffect("Props_Paint.ogg");
	Config::instance()->consumeDiamond(5);
	updateDiamond();
}

bool GameLayer::isTouchInsideNode( cocos2d::CCTouch* touch, cocos2d::CCNode *node )
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void GameLayer::ccTouchesBegan( cocos2d::CCSet *touches, cocos2d::CCEvent *pEvent )
{
	CCTouch* touch = (CCTouch*)( touches->anyObject() );
	CCPoint location = touch->getLocation();

	if (isTouchInsideNode(touch, m_pauseBtn)) {
		GameScene::instance()->showPause();
		return;
	}
	
	if (isTouchInsideNode(touch, m_diamondFetchBtn)) {
		GameScene::instance()->showFetchLayer();
		return;
	}

	if (isTouchInsideNode(touch, m_bombSprite)) {
		if (Config::instance()->diamonds() >= 5) {
			if (m_propType != PropBomb ) {
				m_propType = PropBomb;
				CCScaleBy *scaleBy = CCScaleBy::create(0.3f, 0.8f);
				CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), NULL);
				CCRepeatForever *repeat = CCRepeatForever::create(seq);
				m_bombSprite->runAction(repeat);
				m_brushSprite->stopAllActions();
				m_brushSprite->setScale(1.f);
				updatePropTipsLabel(PropBomb);
			}
			else if (m_propType == PropBomb) {
				m_propType = PropNone;
				m_bombSprite->setScale(1.f);
				m_bombSprite->stopAllActions();
				m_currScoreTotalLabel->setVisible(false);
			}
			return;
		} else {
			GameScene::instance()->showFetchLayer();
			return;
		}
	}

	if (isTouchInsideNode(touch, m_brushSprite)) {
		if (Config::instance()->diamonds() >= 5) {
			if (m_propType != PropBrush) {
				m_propType = PropBrush;
				CCScaleBy *scaleBy = CCScaleBy::create(0.3f, 0.8f);
				CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), NULL);
				CCRepeatForever *repeat = CCRepeatForever::create(seq);
				m_brushSprite->runAction(repeat);
				m_bombSprite->stopAllActions();
				m_bombSprite->setScale(1.f);
				updatePropTipsLabel(PropBrush);
			}
			else if (m_propType == PropBrush) {
				m_propType = PropNone;
				m_brushSprite->setScale(1.f);
				m_brushSprite->stopAllActions();
				m_currScoreTotalLabel->setVisible(false);
			}
			return;
		} else {
			GameScene::instance()->showFetchLayer();
			return;
		}
	}

	if (isTouchInsideNode(touch, m_refreshSprite) && !m_isPoping) {
		if (Config::instance()->diamonds() >= 5) {
			if (m_propType == PropNone) {
				disableInput();
				m_puzzle->stopAllStarAction();
				float delay = m_puzzle->doRefreshAction();
				scheduleAnimation(refreshTurnOver, delay);
				scheduleAnimation(refreshOver, delay*2 + 0.15f);
				updatePropTipsLabel(PropRefresh);
				GameScene::instance()->playEffect("Props_Rainbow.ogg");
				Config::instance()->consumeDiamond(5);
				updateDiamond();
				unschedule(schedule_selector(GameLayer::autoTipsHandler));
				return;
			}
		} else {
			GameScene::instance()->showFetchLayer();
			return;
		}
	}

	if (m_propType == PropBomb) {
		bool ret = m_puzzle->bombed(location);
		if (ret) {
			m_propType = PropNone;
			m_bombSprite->setScale(1.f);
			m_bombSprite->stopAllActions();
			m_currScoreTotalLabel->setVisible(false);
			Config::instance()->consumeDiamond(5);
			updateDiamond();
			return;
		}
	}

	if (m_propType == PropBrush) {
		int index = m_puzzle->getHitIndex(location);
		if (index < MAX_PUZZLE_ROW*MAX_PUZZLE_COL && m_puzzle->isDefined(index)) {
			CCPoint touchPoint = m_puzzle->getStarPotisionByIndex(index);
			showBrush(touchPoint.x+STAR_WIDTH/2, touchPoint.y+STAR_HEIGHT-10, index);

			m_propType = PropNone;
			m_brushSprite->setScale(1.f);
			m_brushSprite->stopAllActions();
			m_currScoreTotalLabel->setVisible(false);
		}
		return;
	}

	if (m_isPoping) return;

	HitResult result;
	result = m_puzzle->hitTest(CCPoint(location.x, location.y));
	handleResult(result);
}

void GameLayer::ccTouchesEnded(CCSet* touches, CCEvent* pEvent)
{
	
}

void GameLayer::handleResult(HitResult& hr)
{
	if (hr.type == HitResult::HIT_STAR) {

	}
	else if (hr.type == HitResult::HIT_POPSTAR) {
		m_popStarSet = hr.popSelects;

		if (hr.data > 0) {
			m_isPoping = true;
			m_popCount = 0;
			scheduleAnimation(PopOneStar);
		}
		
		updateComboLabel(hr.data);
		goodComboAnimation(hr.data);
		stopTipTimer();
	}
}

void GameLayer::checkPuzzleSolved()
{
	if (m_puzzle->isSolved() == false)
		return;

	disableInput();
	bonusSpawnOutAnimation();
}

void GameLayer::autoTipsHandler( float dt )
{
	m_puzzle->theMostSelect();
}

void GameLayer::startTipTimer()
{
	this->schedule(schedule_selector(GameLayer::autoTipsHandler), 6.f);
}

void GameLayer::stopTipTimer()
{
	this->unschedule(schedule_selector(GameLayer::autoTipsHandler));
	m_puzzle->stopAllStarAction();
	m_puzzle->resumeStarsSize();
}

void GameLayer::backStageStartScore()
{
	m_status.score = Config::instance()->stageScore();
}

void GameLayer::registPaymentDelegate()
{
	//监听付费消息
	PayHelper::instance()->addDelegate(this);	
}

void GameLayer::addBonusCallback( CCNode *node, void *param )
{
	int bonusScore = (int)param;

	//m_status.score += bonusScore;
	addScore(bonusScore);
	updateScoreLabel();
}

void GameLayer::bonusMoveInCallback()
{
	//奖励动画
	float delay = 0;
	int bonus = m_puzzle->bonus();
	if (bonus > 0) {
		CCLabelTTF *bonusLabel = CCLabelTTF::create(
			CCString::createWithFormat("%d", bonus)->getCString(), "Arial", 48);
		bonusLabel->setAnchorPoint(ccp(0.5, 0.5));
		bonusLabel->setPosition(m_bonusScoreLabel->getPosition());
		CCMoveTo *moveTo = CCMoveTo::create(1.f, m_scoreLabelPos);
		CCCallFuncND *funcND = CCCallFuncND::create(this, 
			callfuncND_selector(GameLayer::addBonusCallback), (void *)bonus);
		CCFiniteTimeAction *removeAction = CCRemoveSelf::create(true);
		CCSequence *seq = CCSequence::create(moveTo, funcND, removeAction, NULL);
		bonusLabel->runAction(seq);
		this->addChild(bonusLabel);
		delay += 1.f;
	}

	float delayBonus = showBonusAnimation();
	delay += delayBonus;
	scheduleAnimation(BonusMoveOut, delay);
}

void GameLayer::bonusMoveOutCallback()
{
	scheduleAnimation(PuzzleEnd, 0.25f);
}

void GameLayer::bonusMoveOutAnimation()
{
	CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();

	CCFiniteTimeAction* removeBonusAction = CCRemoveSelf::create(true);
	CCCallFunc *callfunc = CCCallFunc::create(this, callfunc_selector(GameLayer::bonusMoveOutCallback));
	CCMoveBy *bonusMove = CCMoveBy::create(
		0.5f, ccp(origin.x - m_bonusScoreLabel->getContentSize().width/2 - m_topCenter.x, 0));
	CCSequence *seqBonus = CCSequence::create(bonusMove, callfunc, removeBonusAction, NULL);
	m_bonusScoreLabel->runAction(seqBonus);

	CCFiniteTimeAction* removeRemainAction = CCRemoveSelf::create(true);
	CCMoveBy *remainMove = CCMoveBy::create(
		0.5f, ccp(origin.x - m_remainStarLabel->getContentSize().width/2 - m_topCenter.x, 0));
	CCSequence *seqRemain = CCSequence::create(remainMove, removeRemainAction, NULL);
	m_remainStarLabel->runAction(seqRemain);
}

void GameLayer::bonusSpawnOutAnimation()
{
	int bonus = m_puzzle->bonus();
	int remains = m_puzzle->remainStars();

	m_bonusScoreLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("remainscore"), bonus)->getCString(), "Arial", 48);
	m_bonusScoreLabel->setAnchorPoint(ccp(0.5, 0));
	m_bonusScoreLabel->setPosition(
		ccp(m_rightCenter.x + m_bonusScoreLabel->getContentSize().width/2, m_rightCenter.y));
	this->addChild(m_bonusScoreLabel);

	m_remainStarLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("remainstar"), remains)->getCString(), "Arial", 48);
	m_remainStarLabel->setAnchorPoint(ccp(0.5, 0));
	int offsetY = m_bonusScoreLabel->getPositionY() - m_remainStarLabel->getContentSize().height - 15; 
	m_remainStarLabel->setPosition(ccp(m_rightCenter.x + m_remainStarLabel->getContentSize().width/2, offsetY));
	this->addChild(m_remainStarLabel);

	CCCallFunc *callfunc = CCCallFunc::create(this, callfunc_selector(GameLayer::bonusMoveInCallback));
	CCMoveBy *bonusMove = CCMoveBy::create(0.5f, ccp(m_topCenter.x - m_bonusScoreLabel->getPositionX(), 0));
	CCSequence *seq = CCSequence::create(bonusMove, callfunc, NULL);
	m_bonusScoreLabel->runAction(seq);

	CCMoveBy *remainMove = CCMoveBy::create(0.5f, ccp(m_topCenter.x - m_remainStarLabel->getPositionX(), 0));
	m_remainStarLabel->runAction(remainMove);
}

float GameLayer::showBonusAnimation()
{
	float delay = 0.1f;
	float interv = 0.1f;
	int clearedCnt = 0;
	int remain = m_puzzle->remainStars();
	while (remain > 0) {
		scheduleAnimation(ExplodeTailStar, delay);
		//clearedCnt++;
		remain--;
		delay += interv;
	}

	return delay;
}

void GameLayer::goodComboAnimation( int count )
{
	if (count < 5)
		return;

	CCFiniteTimeAction *delay;
	CCFiniteTimeAction *action1, *action2, *action3;
	CCFiniteTimeAction *seq;
	action1 = CCBlink::create(0.5f, 5);
	action2 = CCScaleTo::create(0.2f, 1.f);
	delay = CCDelayTime::create(0.5f);
	action3 = CCFadeOut::create(0.2f);

	seq = CCSequence::create(action1, action2, delay, action3, NULL);

	if (count < 6) {
		m_goodPopstar->setScale(1.5f);
		m_goodPopstar->setVisible(true);
		m_goodPopstar->setOpacity(255);
		m_goodPopstar->runAction(seq);
		GameScene::instance()->playEffect("combo_1.ogg");
	}
	else if (count < 7) {
		m_betterPopstar->setScale(1.5f);
		m_betterPopstar->setVisible(true);
		m_betterPopstar->setOpacity(255);
		m_betterPopstar->runAction(seq);
		GameScene::instance()->playEffect("combo_2.ogg");
	}
	else {
		m_fantasticPopstar->setScale(1.5f);
		m_fantasticPopstar->setVisible(true);
		m_fantasticPopstar->setOpacity(255);
		m_fantasticPopstar->runAction(seq);
		GameScene::instance()->firework();
		GameScene::instance()->playEffect("combo_3.ogg");
	}
}

void GameLayer::scheduleAnimation(Animation anim, float delay) {
	CCFiniteTimeAction* delayAction, *animAction = NULL;
	delayAction = CCDelayTime::create(delay);
	switch (anim) {
	case GameOver:
		animAction = CCCallFunc::create(this,
		callfunc_selector(GameLayer::gameOverAnimation));
		break;
	case WinPuzzle:
		animAction = CCCallFunc::create(this,
		callfunc_selector(GameLayer::winPuzzleAnimation));
		break;
	case NextStage:
		animAction = CCCallFunc::create(this,
		callfunc_selector(GameLayer::nextStage));
		break;
	case MainMenu:
		animAction = CCCallFunc::create(this,
		callfunc_selector(GameLayer::returnToMainMenu));
		break;
	case PuzzleEnd:
		animAction = CCCallFunc::create(this,
		callfunc_selector(GameLayer::puzzleEnd));
		break;
	case ExplodeTailStar:
		animAction = CCCallFunc::create(this,
		callfunc_selector(GameLayer::explodeTailStarAnimation));
		break;
	case ExplodeRemainStars:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::explodeRemainStarAnimation));
		break;
	case PopOneStar:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::explodeOneStar));
		break;
	case BonusMoveOut:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::bonusMoveOutAnimation));
		break;
	case StartGame:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::startGameAnimation));
		break;
	case refreshTurnOver:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::refreshTurnOverAnimation));
		break;
	case refreshOver:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::refreshOverAnimation));
		break;
	case startTips:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::startTipTimer));
		break;
	case PopstarEnd:
		animAction = CCCallFunc::create(this,
			callfunc_selector(GameLayer::popstarEndCallback));
		break;
	default:
		break;
	}

	this->runAction(CCSequence::create(delayAction, animAction, NULL));
}

void GameLayer::refreshTurnOverAnimation()
{
	m_puzzle->refreshPuzzle();
}

void GameLayer::refreshOverAnimation()
{
	enableInput();
	m_currScoreTotalLabel->setVisible(false);
	m_puzzle->resumeStarsSize();
	startTipTimer();
}

void GameLayer::explodeTailStarAnimation()
{
	m_puzzle->removeAndPopTailStar();
}

void GameLayer::explodeRemainStarAnimation()
{
	m_puzzle->removeAndPopAllRemainStars();
}

void GameLayer::popstarEndCallback()
{
	m_isPoping = false;
	checkPuzzleSolved();
	startTipTimer();
}

void GameLayer::addScore( int score )
{
	m_status.score += score;

	if (isStageClear(m_status.score) && !m_isStageClear) {
		m_isStageClear = true;
		CCFiniteTimeAction *action1, *action2, *action3, *spawn;
		CCFiniteTimeAction *seq;

		action1 = CCBlink::create(0.5f, 5);
		action2 = CCScaleTo::create(0.2f, 0.5f);
		action3 = CCMoveTo::create(0.2f, 
			ccp(m_rightCenter.x - m_stageClearSprite->getContentSize().width/2*0.5, m_scoreLabelPos.y - 60));
		spawn = CCSpawn::create(action2, action3, NULL);
		seq = CCSequence::create(action1, spawn, NULL);

		m_stageClearSprite->setPosition(ccp(m_topCenter.x, m_rightCenter.y));
		m_stageClearSprite->setScale(1.5f);
		m_stageClearSprite->setVisible(true);
		m_stageClearSprite->runAction(seq);
	}
}

void GameLayer::addScoreEndCallback(CCNode *node, void *param)
{
	int score = (int)param;
	//m_status.score += score;
	addScore(score);

	updateScoreLabel();
}

void GameLayer::starScoreFlyAnimation( int index )
{
	int score = 10*m_popCount + 5;
	m_popCount ++;

	CCPoint startPos = m_puzzle->getStarPotisionByIndex(index);
	CCString *scoreStr = CCString::createWithFormat("%d", score);
	CCLabelTTF *labelScore = CCLabelTTF::create(scoreStr->getCString(), "Arial", 48);
	labelScore->setPosition(startPos);
	this->addChild(labelScore);
	
	CCMoveTo *moveTo = CCMoveTo::create(0.5f, m_scoreLabelPos);
	CCFiniteTimeAction* removeAction = CCRemoveSelf::create(true);
	CCCallFuncND *callFunc = CCCallFuncND::create(this, 
		callfuncND_selector(GameLayer::addScoreEndCallback), (void *)score);
	CCSequence *seq = CCSequence::create(moveTo, removeAction, callFunc, NULL);

	labelScore->runAction(seq);
}

void GameLayer::explodeOneStar()
{
	if (m_popStarSet.empty()) {
		m_puzzle->applyChanges();
		scheduleAnimation(PopstarEnd, 0.25f);
	}

	std::set<int>::iterator iter = m_popStarSet.begin();
	for (; iter != m_popStarSet.end(); iter++) {
		starScoreFlyAnimation(*iter);
		m_puzzle->popStar(*iter);
		m_popStarSet.erase(iter);
		scheduleAnimation(PopOneStar, 0.05f);
		break;
	}
}

void GameLayer::gameOverAnimation()
{
	GameScene::instance()->showGameOver();
}

float GameLayer::stageReportAnimation()
{
	float delayTime = 0;

	CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();

	m_nextStageLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("Round"), m_status.stage)->getCString(), "Arial", 48);
	m_nextStageLabel->setAnchorPoint(ccp(0.5, 0));
	m_nextStageLabel->setPosition(
		ccp(m_rightCenter.x + m_nextStageLabel->getContentSize().width/2, m_rightCenter.y));
	this->addChild(m_nextStageLabel);

	m_nextTargetScoreLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("TargetScore"), target(m_status.stage))->getCString(), "Arial", 48);
	m_nextTargetScoreLabel->setAnchorPoint(ccp(0.5, 0));
	int offsetY = m_nextStageLabel->getPositionY() - m_nextTargetScoreLabel->getContentSize().height - 15; 
	m_nextTargetScoreLabel->setPosition(ccp(m_rightCenter.x + m_nextTargetScoreLabel->getContentSize().width/2, offsetY));
	this->addChild(m_nextTargetScoreLabel);

	CCFiniteTimeAction *removeNextStage = CCRemoveSelf::create(true);
	CCDelayTime *delay = CCDelayTime::create(1.f);
	int nextStageMoveDistance = m_topCenter.x - m_nextStageLabel->getPositionX();
	CCMoveBy *nextStageMoveIn = CCMoveBy::create(0.5f, ccp(nextStageMoveDistance, 0));
	CCMoveBy *nextStageMoveOut = CCMoveBy::create(0.5f, ccp(nextStageMoveDistance, 0));
	CCSequence *nextStageSeq = CCSequence::create(
		nextStageMoveIn, delay, nextStageMoveOut, removeNextStage, NULL);
	m_nextStageLabel->runAction(nextStageSeq);

	CCFiniteTimeAction* removeNextTarget = CCRemoveSelf::create(true);
	CCDelayTime *delayOut = CCDelayTime::create(0.5f);
	CCDelayTime *delayTarget = CCDelayTime::create(0.5f);
	int nextTargetMoveDistance = m_topCenter.x - m_nextTargetScoreLabel->getPositionX();
	CCMoveBy *targetMoveIn = CCMoveBy::create(0.5f, ccp(nextTargetMoveDistance, 0));
	CCMoveBy *targetMoveOut = CCMoveBy::create(0.5f, ccp(nextTargetMoveDistance, 0));
	CCSequence *nextTargetSeq = CCSequence::create(
		delayOut, targetMoveIn, delayTarget, targetMoveOut, removeNextTarget, NULL);
	m_nextTargetScoreLabel->runAction(nextTargetSeq);

	delayTime += 2.0f;

	return delayTime;
}

void GameLayer::winPuzzleAnimation()
{
	GameScene::instance()->playEffect("NextGameRound.ogg");
	scheduleAnimation(NextStage, 0.5f);  //2.5s is total animation.
}

void GameLayer::puzzleEnd()
{
	if (isStageClear(m_status.score) == false) {
		scheduleAnimation(GameOver, 0.2f);
	}
	else {
		m_currScoreTotalLabel->setVisible(false);

		/*if (m_status.stage == 1) {
		showInGameFetchLayer();
		}
		else */{
			scheduleAnimation(WinPuzzle);
		}

		Config::instance()->savedStageScore(m_status.score);
	}
	
	m_stageClearSprite->setVisible(false);
	m_isStageClear = false;
	updateHighScore(m_status.score);
	stopTipTimer();
}

void GameLayer::showInGameFetchLayer()
{
	InGameFetchLayer *ingameFetchLayer = InGameFetchLayer::create();
	this->addChild(ingameFetchLayer);

	this->disableInput();
}

void GameLayer::gamelayerWinAnimation()
{
	scheduleAnimation(WinPuzzle);
}

void GameLayer::returnToMainMenu()
{
	GameScene::instance()->showMainMenu();
}

void GameLayer::resetStatus()
{
	m_status.highScore = Config::instance()->highScore();
	m_status.stage = 0;
	m_status.score = 0;
	m_currScoreTotalLabel->setVisible(false);
	m_stageClearSprite->setVisible(false);
}

bool GameLayer::isStageClear(int score)
{
	return m_status.target <= score;
}

int GameLayer::target(int stage)
{
	if (stage <= 3)
		return m_targetScoreArray[stage];

	int sum = m_targetScoreArray[3];
	for (int i = stage; i > 3; i--) {
		sum += 2000 + (i - 3)*20;
	}

	return sum;
}

void GameLayer::nextStage()
{
	m_status.stage ++;
	newStage();
}

void GameLayer::newStage()
{
	createPuzzle(PUZZLE_SIZE, PUZZLE_SIZE);
	m_status.target = target(m_status.stage);
	
	showStageInfoAndStart();
}

void GameLayer::showStageInfoAndStart()
{
	updateStatus();
	float delay = stageReportAnimation();
	scheduleAnimation(StartGame, delay+0.2f);
}

void GameLayer::startGameAnimation()
{
	//start game
	float delay = m_puzzle->puzzleStartAnimation();

	CCFiniteTimeAction *delayAction = CCDelayTime::create(delay);
	CCAction *enableInputAction = CCCallFunc::create(this, callfunc_selector(GameLayer::enableInput));
	CCSequence *seq = CCSequence::create(delayAction, enableInputAction, NULL);
	scheduleAnimation(startTips, delay);

	this->runAction(seq);
}

void GameLayer::paymentCallback( int retcode, const char *orderNo )
{
	std::string strOrderId = orderNo;
	CCLog("GameLayer::paymentCallback retcode=%d, orderNo=%s", retcode, orderNo);
	//支付成功
	if (retcode == 0) {
		int diamonds = 0;
		if (strOrderId == s_shop_data[4].orderId) {
			diamonds = s_shop_data[4].diamonds+s_shop_data[4].gives;
		}
		m_needAddDiamonds = diamonds;
	}
	CCLog("GameLayer::paymentCallback end");
}

void GameLayer::scheduleDiamondLabels( float dt )
{
	if (m_needAddDiamonds > 0) {
		Config::instance()->addDiamond(m_needAddDiamonds);
		m_needAddDiamonds = 0;
		updateDiamond();
		GameScene::instance()->playEffect("coinsin.ogg");
	}
}

