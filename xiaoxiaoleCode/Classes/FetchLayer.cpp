#include "FetchLayer.h"
#include "GameScene.h"
#include "Language.h"
#include "PayHelper.h"

using namespace cocos2d;

FetchLayer::FetchLayer()
{

}

FetchLayer::~FetchLayer()
{

}

bool FetchLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);

		CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();

		CCSprite *bg = CCSprite::create("Dialog_bg2.png");

		CCLayerColor *bgMask = CCLayerColor::create(ccc4(0, 0, 0, 145));
		bgMask->setAnchorPoint(ccp(0.5, 0.5));
		bgMask->setPosition(ccp(bg->getContentSize().width/2, bg->getContentSize().height/2));
		bgMask->ignoreAnchorPointForPosition(false);
		this->addChild(bgMask);

		bg->setAnchorPoint(CCPointZero);
		bg->setPosition(CCPointZero);
		this->addChild(bg);
		CCSize size = bg->getContentSize();
		this->setContentSize(size);
		
		m_give = CCSprite::create("zengsong.png");
		m_give->setAnchorPoint(ccp(0.5, 0.5));
		m_give->setPosition(ccp(size.width/2-70, size.height/2+40));
		m_give->setScale(1.5f);
		this->addChild(m_give);

		CCLabelTTF *buyLabel = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("buy"))->getCString(), "Arial", 28);
		buyLabel->setAnchorPoint(CCPointZero);
		buyLabel->setPosition(ccp(size.width/2, m_give->getPositionY()+40));
		buyLabel->setColor(ccGREEN);
		this->addChild(buyLabel);

		CCSprite *diamondBuy = CCSprite::create("diamond.png");
		diamondBuy->setAnchorPoint(ccp(0.5, 0.5));
		diamondBuy->setPosition(ccp(size.width/2+20, m_give->getPositionY()+10));
		this->addChild(diamondBuy);
		CCLabelTTF *diamondBuyNum = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("HowMany"), 50)->getCString(), "Arial", 28);
		diamondBuyNum->setAnchorPoint(ccp(0.5, 0.5));
		diamondBuyNum->setPosition(ccp(diamondBuy->getPositionX()+60, diamondBuy->getPositionY()));
		diamondBuyNum->setColor(ccBLACK);
		this->addChild(diamondBuyNum);

		CCSprite *diamondGive = CCSprite::create("diamond.png");
		diamondGive->setAnchorPoint(ccp(0.5, 0.5));
		diamondGive->setPosition(ccp(size.width/2+30, m_give->getPositionY()-40));
		this->addChild(diamondGive);
		CCLabelTTF *diamondGiveNum = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("HowMany"), 50)->getCString(), "Arial", 28);
		diamondGiveNum->setAnchorPoint(ccp(0.5, 0.5));
		diamondGiveNum->setPosition(ccp(diamondGive->getPositionX()+60, diamondGive->getPositionY()));
		diamondGiveNum->setColor(ccBLACK);
		this->addChild(diamondGiveNum);

		CCSprite *titleSprite = CCSprite::create("Dialog_Title.png");
		titleSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleSprite->setPosition(ccp(size.width/2, size.height-20));
		this->addChild(titleSprite);

		int offsetY = titleSprite->getContentSize().height/2+25;
		CCSprite *titleTextSprite = CCSprite::create("Title_QuickShop.png");
		titleTextSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleTextSprite->setPosition(
			ccp(titleSprite->getContentSize().width/2, offsetY));
		titleSprite->addChild(titleTextSprite);

		m_closeBtn = CCSprite::create("Cancel.png");
		m_closeBtn->setAnchorPoint(ccp(0.5, 0.5));
		m_closeBtn->setPosition(
			ccp(titleSprite->getContentSize().width/2+130, offsetY));
		titleSprite->addChild(m_closeBtn);

		m_fetchBtn = CCSprite::create("menu_lingqu.png");
		m_fetchBtn->setAnchorPoint(ccp(0.5, 0));
		m_fetchBtn->setPosition(ccp(size.width/2, 10));
		this->addChild(m_fetchBtn);

		CCLabelTTF *labelFeeTips = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("QuickShop"), 100, "10")->getCString(), "Arial", 15);
		labelFeeTips->setAnchorPoint(ccp(0.5, 0));
		labelFeeTips->setPosition(
			ccp(size.width/2, m_fetchBtn->getPositionY()+m_fetchBtn->getContentSize().height+2));
		labelFeeTips->setColor(ccGRAY);
		this->addChild(labelFeeTips);

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2-size.width/2, visibleSize.height/2-size.height/2));

		bRet = true;
	} while (0);

	return bRet;
}

void FetchLayer::onEnter()
{
	CCLayer::onEnter();

	m_give->setScale(1.5f);
	CCScaleBy *scaleBy = CCScaleBy::create(0.8f, 1.2);
	CCDelayTime *delay = CCDelayTime::create(0.5f);
	CCSequence *seq = CCSequence::create(scaleBy, scaleBy->reverse(), delay, NULL);
	CCRepeatForever *forever = CCRepeatForever::create(seq);

	if (m_give) {
		m_give->runAction(forever);
	}
}

void FetchLayer::keyBackClicked()
{
	GameScene::instance()->returnToGameLayerFromFetchLayer();
}

bool FetchLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

//void FetchLayer::ccTouchesBegan(CCSet* touches, CCEvent* event)
//{
//	CCTouch* touch = (CCTouch*)( touches->anyObject());
//
//	if (isTouchInsideNode(touch, m_fetchBtn)) {
//		fetchDiamond();
//		return;
//	}
//
//	if (isTouchInsideNode(touch, m_closeBtn)) {
//		closeButton();
//		return;
//	}
//}

bool FetchLayer::ccTouchBegan( CCTouch *pTouch, CCEvent *pEvent )
{
	if (isTouchInsideNode(pTouch, m_fetchBtn)) {
		fetchDiamond();
	}

	if (isTouchInsideNode(pTouch, m_closeBtn)) {
		closeButton();
	}

	return true;
}

void FetchLayer::registerWithTouchDispatcher()
{
	//CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
	CCDirector::sharedDirector()->getTouchDispatcher()->addTargetedDelegate(this, 0, true);
}

void FetchLayer::fetchDiamond()
{
	GameScene::instance()->buyDiamonds(s_shop_data[4].dollar, s_shop_data[4].orderId, "game fetch");
	GameScene::instance()->returnToGameLayerFromFetchLayer();
}

void FetchLayer::closeButton()
{
	GameScene::instance()->returnToGameLayerFromFetchLayer();
}
