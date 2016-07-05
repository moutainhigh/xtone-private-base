#include "GamePauseLayer.h"
#include "GameScene.h"
#include "CCScale9Sprite.h"
#include "SimpleAudioEngine.h"
#include "Config.h"

using namespace cocos2d;

GamePauseLayer::GamePauseLayer()
{

}

GamePauseLayer::~GamePauseLayer()
{
	
}

bool GamePauseLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);

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

		CCSprite *titleSprite = CCSprite::create("Dialog_Title.png");
		titleSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleSprite->setPosition(ccp(size.width/2, size.height-20));
		this->addChild(titleSprite);

		int offsetY = titleSprite->getContentSize().height/2+25;
		CCSprite *titleTextSprite = CCSprite::create("Title_Pause.png");
		titleTextSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleTextSprite->setPosition(
			ccp(titleSprite->getContentSize().width/2, offsetY));
		titleSprite->addChild(titleTextSprite);

		m_close = CCSprite::create("Cancel.png");
		m_close->setAnchorPoint(ccp(0.5, 0.5));
		m_close->setPosition(
			ccp(titleSprite->getContentSize().width/2+100, offsetY));
		titleSprite->addChild(m_close);

		m_pause = CCSprite::create("Pause.png");
		m_pause->setAnchorPoint(ccp(0.5, 0.5));
		m_pause->setPosition(ccp(size.width/2, size.height/2));
		this->addChild(m_pause);

		m_returnBtn = CCSprite::create("menu_top.png");
		m_returnBtn->setAnchorPoint(ccp(0, 0));
		m_returnBtn->setPosition(ccp(5, 10));
		this->addChild(m_returnBtn);

		m_resumeBtn = CCSprite::create("menu_continuegame.png");
		m_resumeBtn->setAnchorPoint(ccp(1, 0));
		m_resumeBtn->setPosition(ccp(size.width-5, 10));
		this->addChild(m_resumeBtn);

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2-size.width/2, visibleSize.height/2-size.height/2));

		bRet = true;
	} while (0);

	return bRet;
}

void GamePauseLayer::onEnter()
{
	CCLayer::onEnter();
	CocosDenshion::SimpleAudioEngine::sharedEngine()->stopBackgroundMusic();
	CocosDenshion::SimpleAudioEngine::sharedEngine()->stopAllEffects();
}

void GamePauseLayer::keyBackClicked()
{
	resumeGame();
}

bool GamePauseLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void GamePauseLayer::ccTouchesEnded(CCSet* touches, CCEvent* event)
{
	CCTouch* touch = (CCTouch*)( touches->anyObject());

	if (isTouchInsideNode(touch, m_returnBtn)) {
		returnToMainMenu();
	}
	
	if (isTouchInsideNode(touch, m_resumeBtn)) {
		resumeGame();
	}

	if (isTouchInsideNode(touch, m_pause)) {
		resumeGame();
	}

	if (isTouchInsideNode(touch, m_close)) {
		resumeGame();
	}
}

void GamePauseLayer::registerWithTouchDispatcher()
{
	CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
}

void GamePauseLayer::returnToMainMenu()
{
	removeFromParent();
	GameScene::instance()->returnToMainMenuFromPause();

	if (Config::instance()->mute() == false) {
		CocosDenshion::SimpleAudioEngine::sharedEngine()->playBackgroundMusic("music.ogg", true);
	}
}

void GamePauseLayer::resumeGame()
{
	removeFromParent();
	GameScene::instance()->resumeGameFromPause();

	if (Config::instance()->mute() == false) {
		CocosDenshion::SimpleAudioEngine::sharedEngine()->playBackgroundMusic("music.ogg", true);
	}
}
