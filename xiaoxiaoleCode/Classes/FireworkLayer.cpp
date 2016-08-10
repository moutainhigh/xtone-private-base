#include "FireworkLayer.h"
#include "GameScene.h"

using namespace cocos2d;

FireworkLayer::FireworkLayer()
{

}

FireworkLayer::~FireworkLayer()
{

}

bool FireworkLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());

		CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
		CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
	
		m_fire1 = createFireParticle();
		m_fire2 = createFireParticle();
		m_fire3 = createFireParticle();
		m_fire4 = createFireParticle();
		m_fire5 = createFireParticle();
		m_fire6 = createFireParticle();
		this->addChild(m_fire1);
		this->addChild(m_fire2);
		this->addChild(m_fire3);
		this->addChild(m_fire4);
		this->addChild(m_fire5);
		this->addChild(m_fire6);

		bRet = true;
	} while (0);

	return bRet;
}

void FireworkLayer::fire(bool loop)
{
	m_fire1->setPosition(randomPosition());
	m_fire2->setPosition(randomPosition());
	m_fire3->setPosition(randomPosition());
	m_fire4->setPosition(randomPosition());
	m_fire5->setPosition(randomPosition());
	m_fire6->setPosition(randomPosition());
	CCFiniteTimeAction *action = CCCallFunc::create(m_fire1,
			callfunc_selector(CCParticleSystem::resetSystem));
	this->runAction(action);
	float delayTime;
	delayTime = (rand() % 10)/10.0f+0.5;
	this->showFire(m_fire2, delayTime);
	delayTime += (rand() % 10)/10.0f;
	this->showFire(m_fire3, delayTime);
	delayTime += (rand() % 5)/10.0f+0.5;
	this->showFire(m_fire4, delayTime);
	delayTime += (rand() % 10)/10.0f+0.5;
	this->showFire(m_fire5, delayTime);
	delayTime += (rand() % 10)/10.0f+0.5;
	this->showFire(m_fire6, delayTime);
	if (loop) {
		delayTime += (rand() % 10)/10.0f+2;
		this->delayedFire(delayTime, true);
	}
}

void FireworkLayer::delayedFireCallback( cocos2d::CCNode *sender, void *param )
{
	bool loop = (bool)param;

	fire(loop);
}

void FireworkLayer::delayedFire(float delay, bool loop)
{
	CCFiniteTimeAction *delayAction, *next, *seq;
	delayAction = CCDelayTime::create(delay);
	next = CCCallFuncND::create(this,
			callfuncND_selector(FireworkLayer::delayedFireCallback), (void*)loop);
	seq = CCSequence::create(delayAction, next, NULL);
	this->runAction(seq);
}

void FireworkLayer::stopFire()
{
	this->stopAllActions();
}

void FireworkLayer::showFire(CCParticleSystem* fire, float delay)
{
	CCFiniteTimeAction *delayAction, *action, *seq;
	delayAction = CCDelayTime::create(delay);
	action = CCCallFuncND::create(this,
			callfuncND_selector(FireworkLayer::resetFireSelector), (void*)fire);
	seq = CCSequence::create(delayAction, action, NULL);
	this->runAction(seq);
}

void FireworkLayer::resetFireSelector(CCNode* sender, void* fire)
{
	CCParticleSystem *fireSystem = (CCParticleSystem *)fire;
	fireSystem->resetSystem();

	int temp = rand() % 3;
	if (temp == 0) {
		GameScene::instance()->playEffect("fireworks_01.ogg");
	}
	else if (temp == 1) {
		GameScene::instance()->playEffect("fireworks_02.ogg");
	}
	else if (temp == 2) {
		GameScene::instance()->playEffect("fireworks_03.ogg");
	}
}

CCParticleSystem* FireworkLayer::createFireParticle()
{
	CCParticleExplosion *emitter = CCParticleExplosion::createWithTotalParticles(150);
	emitter->setTexture(CCTextureCache::sharedTextureCache()->textureForKey("fireworks.png"));
	emitter->setGravity(CCPoint(0,-80));
	emitter->setLife(1.0f);
	emitter->setLifeVar(0.2f);
	//emitter->setRadialAccel(50);
	//emitter->setRadialAccelVar(0);
	emitter->setStartSize(1.0f);
	emitter->setStartSizeVar(1.0f);
	emitter->setEndSize(15.0f);
	emitter->setEndSizeVar(5.0f);
	emitter->setOpacityModifyRGB(false);
	emitter->stopSystem();
	
	return emitter;
}

CCPoint FireworkLayer::randomPosition()
{
	CCSize vs = GameScene::instance()->visibleSize();
	CCPoint origin = GameScene::instance()->visibleOrigin();
	return CCPoint(origin.x + rand() % (int)vs.width,
			origin.y + (int)(vs.height-200) + rand() % (200-50));
}
