#include "GameOverLayer.h"
#include "GameScene.h"
#include "CCScale9Sprite.h"
#include "Language.h"
#include "Config.h"

using namespace cocos2d;

GameOverLayer::GameOverLayer()
{

}

GameOverLayer::~GameOverLayer()
{
	
}

bool GameOverLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);
		//this->ignoreAnchorPointForPosition(false);

		CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();

		std::string str = "Dialog_bg.png";
		CCSprite *pBg = CCSprite::create(str.c_str());
		
		CCSize sizeBg = pBg->getContentSize();
		CCRect fullRect = CCRectMake(0, 0, sizeBg.width, sizeBg.height);
		CCRect insetRect = CCRectMake(30, 30, sizeBg.width-30*2, sizeBg.height-30*2);

		CCScale9Sprite *pScaleSprite = CCScale9Sprite::create(str.c_str(), fullRect, insetRect);
		if (!pScaleSprite) {
			return bRet;
		}

		CCSize size = CCSizeMake(350, 350);

		CCLayerColor *bgMask = CCLayerColor::create(ccc4(0, 0, 0, 145));
		bgMask->setAnchorPoint(ccp(0.5, 0.5));
		bgMask->setPosition(ccp(size.width/2, size.height/2));
		bgMask->ignoreAnchorPointForPosition(false);
		this->addChild(bgMask);

		pScaleSprite->setContentSize(size);
		pScaleSprite->setAnchorPoint(ccp(0.5, 0.5));
		this->setContentSize(pScaleSprite->getContentSize());
		pScaleSprite->setPosition(
			ccp(size.width/2, size.height/2));
		this->addChild(pScaleSprite);

		CCSprite *bgFace = CCSprite::create("GameOver.png");
		bgFace->setAnchorPoint(ccp(0.5, 0.5));
		bgFace->setPosition(ccp(size.width/2, size.height));
		this->addChild(bgFace);

		m_close = CCSprite::create("Cancel.png");
		m_close->setAnchorPoint(ccp(0.5, 0.5));
		m_close->setPosition(ccp(size.width, size.height));
		this->addChild(m_close);

		m_continueGame = CCSprite::create("menu_continue2.png");
		m_continueGame->setAnchorPoint(ccp(0.5, 0));
		m_continueGame->setPosition(ccp(size.width/2, 20));
		this->addChild(m_continueGame);

		CCLabelTTF *tipsLabel = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("ContinueChallengeTips"), 5)->getCString(), "Arial", 18);
		tipsLabel->setAnchorPoint(ccp(0.5, 0));
		tipsLabel->setPosition(ccp(size.width/2, 5));
		tipsLabel->setColor(ccc3(65, 65, 65));
		this->addChild(tipsLabel);

		CCLabelTTF *continueLabel = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("ContinueChallenge1"))->getCString(), "Arial", 32);
		continueLabel->setAnchorPoint(ccp(0.5, 0.5));
		//continueLabel->setDimensions(CCSizeMake(200, 65));
		continueLabel->setPosition(ccp(size.width/2, size.height/2));
		continueLabel->setColor(ccc3(0, 0, 0));
		this->addChild(continueLabel);

		m_countLabel = CCLabelTTF::create(CCString::createWithFormat("0")->getCString(), "Arial", 24);
		m_countLabel->setAnchorPoint(ccp(0, 0));
		m_countLabel->setPosition(ccp(size.width/2 + 110, 55));
		m_countLabel->setColor(ccc3(0, 0, 0));
		m_countLabel->setVisible(false);
		this->addChild(m_countLabel);

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2-size.width/2, visibleSize.height/2-size.height/2));

		bRet = true;
	} while (0);

	return bRet;
}

void GameOverLayer::onEnter()
{
	CCLayer::onEnter();
	enableInput();

	m_count = 8;
	schedule(schedule_selector(GameOverLayer::countSchedule), 1.f);
}

void GameOverLayer::keyBackClicked()
{
	closeButton();
}

void GameOverLayer::enableInput()
{
	this->setTouchEnabled(true);
	this->setKeypadEnabled(true);
	CCDirector::sharedDirector()->getTouchDispatcher()->addTargetedDelegate(this, 0, true);
	//CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this,0);
}

void GameOverLayer::disableInput()
{
	this->setTouchEnabled(false);
	this->setKeypadEnabled(false);
	CCDirector::sharedDirector()->getTouchDispatcher()->removeDelegate(this);
}

bool GameOverLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

//void GameOverLayer::ccTouchesEnded(CCSet* touches, CCEvent* event)
//{
//	CCTouch* touch = (CCTouch*)(touches->anyObject());
//
//	if (isTouchInsideNode(touch, m_close)) {
//		closeButton();
//	}
//	
//	if (isTouchInsideNode(touch, m_continueGame)) {
//		continueGame();
//	}
//}

bool GameOverLayer::ccTouchBegan( cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent )
{
	if (isTouchInsideNode(pTouch, m_close)) {
		closeButton();
	}

	if (isTouchInsideNode(pTouch, m_continueGame)) {
		continueGame();
	}

	return true;
}

void GameOverLayer::registerWithTouchDispatcher()
{
	//CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
	CCDirector::sharedDirector()->getTouchDispatcher()->addTargetedDelegate(this, 0, true);
}

void GameOverLayer::closeButton()
{
	GameScene::instance()->closeGameOver();
}

void GameOverLayer::continueGame()
{
	if (Config::instance()->consumeDiamond(5) == true) {
		GameScene::instance()->continueGameOver();
		removeFromParent();
	} else {
		GameScene::instance()->showFetchLayer();
	}
	unschedule(schedule_selector(GameOverLayer::countSchedule));
	m_countLabel->setVisible(false);
}

void GameOverLayer::updateCountLabel( int count )
{
	m_countLabel->setVisible(true);
	m_countLabel->setString(
		CCString::createWithFormat("%d", count)->getCString());
}

void GameOverLayer::countSchedule( float dt )
{
	if (m_count > 0) {
		m_count--;
		updateCountLabel(m_count);
	}
	else if (m_count == 0) {
		unschedule(schedule_selector(GameOverLayer::countSchedule));
		continueGame();
	}
}

