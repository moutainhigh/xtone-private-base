#include "Star.h"
#include <string>

Star::Star(StarType type, float scale)
    : m_type(type)
    , m_scale(scale)
    , m_attachedNode(0)
    , m_selected(false)
{
	const char *normal, *selected;
	if (type == Yellow) {
		normal = "orange.png";
		selected = "orange_heart.png";
	}
	else if (type == Green) {
		normal = "green.png";
		selected = "green_heart.png";
	}
	else if (type == Red) {
		normal = "red.png";
		selected = "red_heart.png";
	}
	else if (type == Blue) {
		normal = "blue.png";
		selected = "blue_heart.png";
	}
	else if (type == Purple) {
		normal = "purple.png";
		selected = "purple_heart.png";
	}
	//m_sprite = CCSprite::createWithSpriteFrameName(normal);
	m_sprite = CCSprite::create(normal);
	m_sprite->retain();
	m_sprite->setAnchorPoint(CCPoint(0, 0));
	m_sprite->setScale(STAR_SCALE_FACTOR);
	m_normalFrame = m_sprite->displayFrame();
	m_normalFrame->retain();
	/*CCSpriteFrameCache* cacher = CCSpriteFrameCache::sharedSpriteFrameCache();
	m_selectedFrame = cacher->spriteFrameByName(selected);
	m_selectedFrame->retain();*/
	CCSprite *selectedFrame = CCSprite::create(selected);
	m_selectedFrame = selectedFrame->displayFrame();
	m_selectedFrame->retain();
}

Star::~Star() {
	detach();
	m_sprite->release();
	m_normalFrame->release();
	m_selectedFrame->release();
}

CCPoint Star::getPosition() const {
	return m_sprite->getPosition();
}

void Star::setPosition(CCPoint p) {
	m_sprite->setPosition(p);
}

void Star::setPosition(float x, float y) {
	m_sprite->setPosition(CCPoint(x, y));
}

void Star::moveTo(CCPoint p) {
	m_sprite->runAction(CCEaseBackInOut::create(CCMoveTo::create(0.25f, p)));
}

void Star::runAction(CCAction* action) {
	m_sprite->runAction(action);
}

void Star::stopAction() {
	m_sprite->stopAllActions();
}

CCParticleSystem* Star::explode() {
	CCParticleExplosion *emitter = CCParticleExplosion::createWithTotalParticles(10);
	CCString key;
	key.initWithFormat("star.png");
	emitter->setTexture(CCTextureCache::sharedTextureCache()->textureForKey(key.getCString()));
	emitter->setPosition(m_sprite->getPosition()+CCPoint(STAR_WIDTH/2, STAR_HEIGHT/2));
	emitter->setGravity(CCPoint(0,-190));
	/*emitter->setRadialAccel(-100);
	emitter->setRadialAccelVar(-20);*/
	ccColor4F c4;
	switch (m_type) {
	case Star::Yellow:
		c4 = ccc4FFromccc3B(ccc3(251, 222, 41));
		break;
	case Star::Green:
		c4 = ccc4FFromccc3B(ccc3(105, 217, 75));
		break;
	case Star::Red:
		c4 = ccc4FFromccc3B(ccc3(251, 52, 105));
		break;
	case Star::Blue:
		c4 = ccc4FFromccc3B(ccc3(255, 100, 215));
		break;
	case Star::Purple:
		c4 = ccc4FFromccc3B(ccc3(169, 41, 252));
		break;
	default:
		break;
	}
	emitter->setStartColor(c4);
	emitter->setStartColorVar(ccc4f(0.0f, 0.0f, 0.0f, 0.0f));
	//emitter->setEndColor(ccc4f(1.0f, 1.0f, 1.0f, 1.0f));
	//emitter->setEndColorVar(ccc4f(0.0f, 0.0f, 0.0f, 0.0f));
	emitter->setScale(1.5f);
	emitter->setSpeed(220);
	emitter->setSpeedVar(70);
	emitter->setAngle(0);
	emitter->setAngle(360);

	if (m_attachedNode)
		m_attachedNode->addChild(emitter, 999);
	return emitter;
}

void Star::select() {
	if (m_selected)
		return;
	m_selected = true;
	m_sprite->setDisplayFrame(m_selectedFrame);
}

void Star::unselect() {
	if (m_selected == false)
		return;
	m_selected = false;
	m_sprite->setDisplayFrame(m_normalFrame);
}

bool Star::attach(CCNode* node) {
	if (node == NULL)
		return false;

	detach();
	node->addChild(m_sprite);
	m_attachedNode = node;
	return true;
}

bool Star::detach(CCNode* node) {
	if (m_attachedNode == NULL)
		return false;

	if (node == NULL || node == m_attachedNode) {
		m_attachedNode->removeChild(m_sprite);
		m_attachedNode = NULL;
		return true;
	}

	return false;
}

void Star::setScale( float scale )
{
	m_sprite->setScale(scale);
}

void Star::changeStarType( int type )
{
	StarType starType = getType(type);
	const char *normal, *selected;
	if (type == Yellow) {
		normal = "orange.png";
		selected = "orange_heart.png";
	}
	else if (type == Green) {
		normal = "green.png";
		selected = "green_heart.png";
	}
	else if (type == Red) {
		normal = "red.png";
		selected = "red_heart.png";
	}
	else if (type == Blue) {
		normal = "blue.png";
		selected = "blue_heart.png";
	}
	else if (type == Purple) {
		normal = "purple.png";
		selected = "purple_heart.png";
	}

	CCTexture2D *textureNoraml = CCTextureCache::sharedTextureCache()->addImage(normal);
	CCTexture2D *textureSelected = CCTextureCache::sharedTextureCache()->addImage(selected);

	m_sprite->setTexture(textureNoraml);
	m_normalFrame->setTexture(textureNoraml);
	m_selectedFrame->setTexture(textureSelected);

	m_type = starType;
}

CCSprite* createStarSprite(Star::StarType type) {
	std::string resource = "star";
	resource.append(1, (char)('0'+type));
	resource.append("a.png");
	CCSprite* sprite = CCSprite::createWithSpriteFrameName(resource.c_str());
	sprite->setAnchorPoint(CCPoint(0, 0));
	return sprite;
}
