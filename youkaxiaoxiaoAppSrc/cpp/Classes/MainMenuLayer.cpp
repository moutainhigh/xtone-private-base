#include "MainMenuLayer.h"
#include "GameScene.h"
#include "Config.h"
#include "ShopLayer.h"
#include "HelpLayer.h"
#include "ExchangeLayer.h"
#include "GiftLayer.h"
#include "Language.h"
#include "SimpleAudioEngine.h"
#include "AppSDK.h"

using namespace cocos2d;

MainMenuLayer::MainMenuLayer()
{
	m_isRequestedGift = false;
	//m_isShowed = false;
}

MainMenuLayer::~MainMenuLayer()
{
	CC_SAFE_RELEASE(m_muteFrame);
	CC_SAFE_RELEASE(m_soundFrame);

	CCNotificationCenter::sharedNotificationCenter()->removeObserver(this, MSG_CALLJNI_MAIN);
}

bool MainMenuLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());

		/*m_isShowed = Config::instance()->isShowed();
		if (m_isShowed)*/ {
			createUI();
		}

		CCNotificationCenter::sharedNotificationCenter()->addObserver(this,
			callfuncO_selector(MainMenuLayer::notifyHandle), MSG_CALLJNI_MAIN, NULL);

		bRet = true;
	} while (0);

	return bRet;
}

void MainMenuLayer::notifyHandle(CCObject* obj)
{
	CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
	float scalef = visibleSize.height/DESIGN_HEIGHT;
	int id = ((CCInteger *)obj)->getValue();
	if(111 == id)
	{
		m_gift->setPosition(ccp(visibleSize.width-65, 450.f*scalef));
		m_gift->setVisible(true);
	}else
	{
		m_gift->setVisible(false);
		m_gift->setPosition(ccp(visibleSize.width+100,visibleSize.height+100));
	}
}


void MainMenuLayer::createUI()
{
	CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
	CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
	float scalef = visibleSize.height/DESIGN_HEIGHT;

	CCSprite *bg = CCSprite::create("bg_mainscene.jpg");
	bg->setAnchorPoint(ccp(0.5, 0.5));
	bg->setPosition(ccp(visibleSize.width/2, visibleSize.height/2));
	bg->setScaleX(visibleSize.width/bg->getContentSize().width);
	bg->setScaleY(visibleSize.height/bg->getContentSize().height);
	addChild(bg);
	
	m_help = CCSprite::create("menu_help.png");
	m_help->setAnchorPoint(ccp(0, 0));
	m_help->setPosition(ccp(10, 10));
	this->addChild(m_help);

	m_sound = CCSprite::create("menu_sound_on.png") ;
	m_sound->setAnchorPoint(ccp(0, 0));
	m_sound->setPosition(
		ccp(10, m_help->getPositionY()+m_help->getContentSize().height+5));

	m_soundFrame = m_sound->displayFrame();
	m_soundFrame->retain();

	CCSprite *muteFrame = CCSprite::create("menu_sound_off.png");
	m_muteFrame = muteFrame->displayFrame();
	m_muteFrame->retain();

	if (Config::instance()->mute())
		m_sound->setDisplayFrame(m_muteFrame);

	this->addChild(m_sound);

	if (Config::instance()->mute() == false) {
		CocosDenshion::SimpleAudioEngine::sharedEngine()->playBackgroundMusic("music.ogg", true);
	}

	CCSprite *versions = CCSprite::create("bg_menuscene_version.png");
	versions->setAnchorPoint(ccp(0.5, 1));
	versions->setPosition(ccp(visibleSize.width/2, visibleSize.height-120*scalef));
	versions->setScale(0.7f);
	this->addChild(versions);

	/*CCLabelTTF *telTipLabel = CCLabelTTF::create(
	CCString::createWithFormat(GET_STRING("Tel"))->getCString(), "Arial", 22);
	telTipLabel->setAnchorPoint(ccp(0.5, 1));
	telTipLabel->setPosition(ccp(visibleSize.width/2, visibleSize.height - 10*scalef));
	this->addChild(telTipLabel);*/

	m_newGame = CCSprite::create("menu_start.png");
	m_newGame->setAnchorPoint(ccp(0.5, 1));
	m_newGame->setPosition(ccp(visibleSize.width/2, 290.f*scalef));
	this->addChild(m_newGame);

	m_resumeGame = CCSprite::create("menu_continue.png");
	m_resumeGame->setAnchorPoint(ccp(0.5, 1));
	m_resumeGame->setPosition(ccp(visibleSize.width/2, 200.f*scalef));
	this->addChild(m_resumeGame);

	m_shop = CCSprite::create("menu_shop.png");
	m_shop->setAnchorPoint(ccp(0.5, 1));
	m_shop->setPosition(ccp(visibleSize.width/2, 110.f*scalef));
	this->addChild(m_shop);
	m_shop->setVisible(true);
	//m_shop->setPosition(ccp(-100,-100));

	/*CCSprite *newTips = CCSprite::create("new.png");
	newTips->setAnchorPoint(ccp(0.5, 0.5));
	newTips->setPosition(ccp(m_shop->getContentSize().width-25, m_shop->getContentSize().height));
	m_shop->addChild(newTips);*/

	/*
	m_exchange = CCSprite::create("menu_CDKEY.png");
	m_exchange->setAnchorPoint(ccp(1, 0));
	m_exchange->setPosition(ccp(visibleSize.width-15, 30.f*scalef));
	this->addChild(m_exchange);
	*/

	/*m_moreGame = CCSprite::create("menu_moregame1.png");
	m_moreGame->setAnchorPoint(ccp(1, 0));
	m_moreGame->setPosition(ccp(visibleSize.width-10, 120.f*scalef));
	this->addChild(m_moreGame);*/
	
	m_gift = CCSprite::create("menu_moregame.png");
	m_gift->setAnchorPoint(ccp(0.5, 0.5));
	m_gift->setPosition(ccp(visibleSize.width-65, 450.f*scalef));
	this->addChild(m_gift);
	m_gift->setVisible(false);
	m_gift->setPosition(ccp(visibleSize.width+100,visibleSize.height+100));

	//我的账户
	 myAccount();

	 ////充值
	 recharge();

	this->setTouchEnabled(true);
	this->setKeypadEnabled(true);

	resumeMainMenu();

	
}

//充值
void MainMenuLayer::recharge()
{
	CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
	CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
	float scalef = visibleSize.height/DESIGN_HEIGHT;

	const char* ch = Language::instance()->getString("my_recharge");
	const char *fnt = "fonts/myfont.fnt";
	CCLabelBMFont *font = CCLabelBMFont::create(ch,fnt);
	CCMenuItemLabel *accMenu = CCMenuItemLabel::create(font,this,menu_selector(MainMenuLayer::callRecharge));
	accMenu->setPosition(ccp(visibleSize.width/2, 400.f*scalef));

	CCMenu  *menu = CCMenu::create(accMenu,NULL);
	menu->setPosition(CCPointZero);
    this->addChild(menu,0,332);
	menu->setVisible(true);

}

void MainMenuLayer::callRecharge(CCObject *sender)
{
	//充值回调
	AppSDK::instance()->pay(1,"one");

}




void MainMenuLayer::myAccount()
{
	CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
	CCPoint origin = CCDirector::sharedDirector()->getVisibleOrigin();
	float scalef = visibleSize.height/DESIGN_HEIGHT;

	const char* ch = Language::instance()->getString("my_account");
	const char *fnt = "fonts/myfont.fnt";
	CCLabelBMFont *font = CCLabelBMFont::create(ch,fnt);
	CCMenuItemLabel *accMenu = CCMenuItemLabel::create(font,this,menu_selector(MainMenuLayer::callAccount));
	accMenu->setPosition(ccp(visibleSize.width/2, 330.f*scalef));

	CCMenu  *menu = CCMenu::create(accMenu,NULL);
	menu->setPosition(CCPointZero);
    this->addChild(menu,0,333);
	menu->setVisible(true);

}

void MainMenuLayer::callAccount(CCObject *sender)
{

	AppSDK::instance()->login();

}

void MainMenuLayer::resumeMainMenu()
{
	m_gift->setScale(1.0f);
	CCScaleBy *scaleBy = CCScaleBy::create(0.8f, 0.8);
	CCDelayTime *delay = CCDelayTime::create(0.5f);
	CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), delay, NULL);
	CCRepeatForever *forever = CCRepeatForever::create(seq);

	if (m_gift) {
		m_gift->stopAllActions();
		m_gift->runAction(forever);
	}
}

void MainMenuLayer::onEnter()
{
	CCLayer::onEnter();

	/*if (!m_isShowed) {
	scheduleOnce(schedule_selector(MainMenuLayer::showUISchedule), 2.0f);
	}*/

	/*if (m_isShowed) {
		m_gift->setScale(1.0f);
		CCScaleBy *scaleBy = CCScaleBy::create(0.8f, 0.8);
		CCDelayTime *delay = CCDelayTime::create(0.5f);
		CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), delay, NULL);
		CCRepeatForever *forever = CCRepeatForever::create(seq);

		if (m_gift) {
		m_gift->stopAllActions();
		m_gift->runAction(forever);
		}

		//if (!m_isRequestedGift) {
		//	PayHelper::instance()->addDelegate(this);
		//	//PayHelper::instance()->requestGift();
		//}
	}*/
}

void MainMenuLayer::onExit()
{
	PayHelper::instance()->removeDelegate();
	CCLayer::onExit();
}

void MainMenuLayer::enableInput()
{
	enableTouchEvent();
	enableKeypad();
}

void MainMenuLayer::disableInput()
{
	disableTouchEvent();
	disableKeypad();
}

bool MainMenuLayer::isInputEnabled()
{
	return isKeypadEnabled() && isTouchEnabled();
}

void MainMenuLayer::enableTouchEvent()
{
	this->setTouchEnabled(true);
	CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this,0);
}

void MainMenuLayer::disableTouchEvent()
{
	this->setTouchEnabled(false);
	CCDirector::sharedDirector()->getTouchDispatcher()->removeDelegate(this);
}

void MainMenuLayer::enableKeypad()
{
	this->setKeypadEnabled(true);
}

void MainMenuLayer::disableKeypad()
{
	this->setKeypadEnabled(false);
}

void MainMenuLayer::keyBackClicked()
{
	CCDirector::sharedDirector()->end();
}

void MainMenuLayer::startNewPuzzle()
{
	GameScene::instance()->startNewPuzzle();
}

void MainMenuLayer::resumePuzzle()
{
	bool savedPuzzle = Config::instance()->hasSavedPuzzle();
	if (savedPuzzle) {
		GameScene::instance()->resumePuzzle();
	}
}

void MainMenuLayer::showShop()
{
	GameScene::instance()->stopFirework();
	disableInput();
	ShopLayer *shopLayer = ShopLayer::create();
	this->addChild(shopLayer);
}

void MainMenuLayer::showHelp()
{
	GameScene::instance()->stopFirework();
	disableInput();
	HelpLayer *helpLayer = HelpLayer::create();
	this->addChild(helpLayer);
}

void MainMenuLayer::showExchange()
{
	GameScene::instance()->stopFirework();
	disableInput();
	ExchangeLayer *layer = ExchangeLayer::create();
	this->addChild(layer);
}

void MainMenuLayer::showGift()
{
	GameScene::instance()->stopFirework();
	disableInput();
	GiftLayer *layer = GiftLayer::create();
	this->addChild(layer);

	this->getChildByTag(333)->setVisible(false);
	this->getChildByTag(332)->setVisible(false);

	
}

bool MainMenuLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void MainMenuLayer::ccTouchesEnded(CCSet* touches, CCEvent* event)
{
	CCTouch* touch = (CCTouch*)( touches->anyObject());

	if (isTouchInsideNode(touch, m_newGame)) {
		startNewPuzzle();
	}

	if (isTouchInsideNode(touch, m_resumeGame)) {
		resumePuzzle();
	}

	if (isTouchInsideNode(touch, m_shop)) {
		showShop();
	}

	/*
	if (isTouchInsideNode(touch, m_exchange)) {
	showExchange();
	}*/

	if (isTouchInsideNode(touch, m_gift)) {
		showGift();
	}

	//if (isTouchInsideNode(touch, m_moreGame)) {
	//	//PayHelper::instance()->payment(10, "200", "0.1 yuan");
	//}

	if (isTouchInsideNode(touch, m_sound)) {
		bool mute = !Config::instance()->mute();
		Config::instance()->setMute(mute);

		if (mute)
			m_sound->setDisplayFrame(m_muteFrame);
		else
			m_sound->setDisplayFrame(m_soundFrame);
	}

	if (isTouchInsideNode(touch, m_help)) {
		showHelp();
	}
}

void MainMenuLayer::registerWithTouchDispatcher()
{
    CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
}

void MainMenuLayer::showGiftSchedule( float dt )
{
	if (m_isRequestedGift) {
		//是否已经弹出gift
		if (this->getChildByTag(1000) == NULL) {
			showGift();
		}
	}
}

void MainMenuLayer::showUISchedule( float dt )
{
	if (!m_isShowed) {
		m_isShowed = true;
		createUI();
		Config::instance()->setShowed();

		if (!m_isRequestedGift) {
			PayHelper::instance()->addDelegate(this);
			//PayHelper::instance()->requestGift();
		}
	}
}

void MainMenuLayer::paymentCallback( int retcode, const char *orderNo )
{

}

void MainMenuLayer::notifyCallback()
{
	if (!m_isRequestedGift) {
		m_isRequestedGift = true;
		this->scheduleOnce(schedule_selector(MainMenuLayer::showGiftSchedule), 0.5f);
	}
}

