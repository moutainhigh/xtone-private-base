#include "InGameFetchLayer.h"
#include "GameScene.h"
#include "Language.h"
#include "ExchangeLayer.h"
#include "Config.h"

using namespace cocos2d;

InGameFetchLayer::InGameFetchLayer()
{
	m_needAddDiamonds = 0;
}

InGameFetchLayer::~InGameFetchLayer()
{
	//PayHelper::instance()->removeDelegate();
}

bool InGameFetchLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);
		this->ignoreAnchorPointForPosition(false);

		CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();

		CCSprite *bg = CCSprite::create("gift_dlg_background.png");
		bg->setAnchorPoint(CCPointZero);
		bg->setPosition(CCPointZero);
		CCSize size = bg->getContentSize();
		this->setContentSize(size);

		CCLayerColor *bgMask = CCLayerColor::create(ccc4(0, 0, 0, 145));
		bgMask->setAnchorPoint(ccp(0.5, 0.5));
		bgMask->setPosition(ccp(size.width/2, size.height/2));
		bgMask->ignoreAnchorPointForPosition(false);
		this->addChild(bgMask);
		this->addChild(bg);

		m_confirmBtn = CCSprite::create("gift_dlg_accept.png");
		m_confirmBtn->setAnchorPoint(ccp(0, 0));
		m_confirmBtn->setPosition(ccp(220, 70));
		this->addChild(m_confirmBtn);

		m_cancelBtn = CCSprite::create("gift_dlg_cancel.png");
		m_cancelBtn->setAnchorPoint(ccp(1, 0));
		m_cancelBtn->setPosition(ccp(size.width - 30, 70));
		this->addChild(m_cancelBtn);

		CCLabelTTF *labelTips = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("GiftTips"), 100)->getCString(), "Arial", 25);
		labelTips->setAnchorPoint(ccp(0.5, 0.5));
		labelTips->setPosition(ccp(size.width/2, 300));
		labelTips->setColor(ccBLACK);
		this->addChild(labelTips);

		CCSprite *diamondIcon = CCSprite::create("diamond.png");
		diamondIcon->setAnchorPoint(CCPointZero);
		diamondIcon->setPosition(ccp(size.width/2 - 15, 200));
		this->addChild(diamondIcon);

		CCSprite *number50_1 = CCSprite::create("number_100.png");
		number50_1->setAnchorPoint(CCPointZero);
		number50_1->setPosition(ccp(size.width/2 + 20, 200));
		this->addChild(number50_1);

		CCSprite *sptZengsong = CCSprite::create("zengsong.png");
		sptZengsong->setAnchorPoint(ccp(0.5, 0.5));
		sptZengsong->setPosition(ccp(size.width/2 + 75, 175));
		sptZengsong->setScale(0.5f);
		this->addChild(sptZengsong);

		CCSprite *number50_2 = CCSprite::create("number_100.png");
		number50_2->setAnchorPoint(CCPointZero);
		number50_2->setPosition(ccp(size.width/2 + 100, 150));
		this->addChild(number50_2);

		CCLabelTTF *feeTipsLabel = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("GiftFeeTips"), 5)->getCString(), "Arial", 15);
		feeTipsLabel->setAnchorPoint(ccp(0.5, 0));
		feeTipsLabel->setPosition(ccp(size.width/2, 10));
		feeTipsLabel->setColor(ccc3(100, 60, 20));
		this->addChild(feeTipsLabel);

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2, visibleSize.height/2));

		bRet = true;
	} while (0);

	return bRet;
}

void InGameFetchLayer::onEnter()
{
	CCLayer::onEnter();

	//监听付费消息
	PayHelper::instance()->addDelegate(this);
	//更新钻石数量
	this->schedule(schedule_selector(InGameFetchLayer::scheduleDiamondLabels), 0.1f);
}

void InGameFetchLayer::keyBackClicked()
{
	quit();
}

void InGameFetchLayer::quit()
{
	removeFromParent();
	GameScene::instance()->returnToGameLayerFromInGameFetchLayer();
}

bool InGameFetchLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void InGameFetchLayer::registerWithTouchDispatcher()
{
	CCDirector::sharedDirector()->getTouchDispatcher()->addTargetedDelegate(this, 0, true);
}

void InGameFetchLayer::getGift()
{
	//PayHelper::instance()->addDelegate(this);

	//PayHelper::instance()->fetchGift();
	PayHelper::instance()->payment(s_shop_data[4].dollar, s_shop_data[4].orderId, "in game gift");
	quit();
}

bool InGameFetchLayer::ccTouchBegan( cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent )
{
	if (isTouchInsideNode(pTouch, m_confirmBtn)) {
		getGift();
	}

	if (isTouchInsideNode(pTouch, m_cancelBtn)) {
		quit();
	}

	return true;
}

void InGameFetchLayer::paymentCallback( int retcode, const char *orderNo )
{
	CCLog("InGameFetchLayer::paymentCallback retcode=%d, orderNo=%s", retcode, orderNo);
	std::string strOrderNo = orderNo;

	//支付成功
	if (retcode == 0) {
		int diamonds = 0;
		if (strOrderNo == s_shop_data[4].orderId) {
			diamonds = s_shop_data[4].diamonds+s_shop_data[4].gives;
		}
		m_needAddDiamonds = diamonds;
	}

	CCLog("InGameFetchLayer::paymentCallback end");
}

void InGameFetchLayer::scheduleDiamondLabels( float dt )
{
	if (m_needAddDiamonds > 0) {
		Config::instance()->addDiamond(m_needAddDiamonds);
		m_needAddDiamonds = 0;
		GameScene::instance()->playEffect("coinsin.ogg");
	}
}