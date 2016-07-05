#include "ExchangeLayer.h"
#include "GameScene.h"
#include "Language.h"
#include "CCScale9Sprite.h"
#include "Config.h"

using namespace cocos2d;

ExchangeLayer::ExchangeLayer()
{
	m_needAddDiamonds = 0;
}

ExchangeLayer::~ExchangeLayer()
{
	PayHelper::instance()->removeDelegate();
}

bool ExchangeLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);
		this->ignoreAnchorPointForPosition(false);

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
		bgMask->setPosition(ccp(size.width/2, size.height/2-120));
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
		CCSprite *titleTextSprite = CCSprite::create("Title_CDKEY.png");
		titleTextSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleTextSprite->setPosition(
			ccp(titleSprite->getContentSize().width/2, offsetY));
		titleSprite->addChild(titleTextSprite);

		m_close = CCSprite::create("Cancel.png");
		m_close->setAnchorPoint(ccp(0.5, 0.5));
		m_close->setPosition(
			ccp(titleSprite->getContentSize().width/2+120, offsetY));
		titleSprite->addChild(m_close);

		CCLabelTTF *tipsLabel = CCLabelTTF::create(CCString::createWithFormat(Language::instance()->getString("CDKEY_instruction"))->getCString(), "Arial", 22);

		tipsLabel->setAnchorPoint(ccp(0.5, 0));
		tipsLabel->setDimensions(ccp(300, 80));
		tipsLabel->setPosition(ccp(size.width/2, size.height/2+10));
		tipsLabel->setColor(ccBLACK);
		this->addChild(tipsLabel);

		m_fetchBtn = CCSprite::create("menu_lingqu.png");
		m_fetchBtn->setAnchorPoint(ccp(0.5, 0));
		m_fetchBtn->setPosition(ccp(size.width/2, 2));
		this->addChild(m_fetchBtn);

		m_cdkeyTips = CCLabelTTF::create(
			CCString::createWithFormat(" ")->getCString(), "Arial", 20);
		m_cdkeyTips->setAnchorPoint(ccp(0.5, 0));
		m_cdkeyTips->setPosition(ccp(size.width/2, 91));
		m_cdkeyTips->setColor(ccBLACK);
		this->addChild(m_cdkeyTips);

		m_menuTextBtn = CCSprite::create("menu_Text.png");
		m_menuTextBtn->setAnchorPoint(ccp(0.5, 0));
		m_menuTextBtn->setPosition(ccp(size.width/2, 115));
		this->addChild(m_menuTextBtn);

		m_textFieldTTF = CCTextFieldTTF::textFieldWithPlaceHolder(" ","Helvetica", 20);
		m_textFieldTTF->setAnchorPoint(ccp(0.5, 0));
		m_textFieldTTF->setPosition(m_menuTextBtn->getPosition());
		this->addChild(m_textFieldTTF);

		m_cdkeyLabel = CCLabelTTF::create(
			CCString::createWithFormat(" ")->getCString(), "Arial", 20);
		m_cdkeyLabel->setAnchorPoint(ccp(0.5, 0.5));
		m_cdkeyLabel->setPosition(ccp(m_menuTextBtn->getContentSize().width/2, m_menuTextBtn->getContentSize().height/2));
		m_cdkeyLabel->setColor(ccBLACK);
		m_menuTextBtn->addChild(m_cdkeyLabel);

		//绑定接口
		m_textFieldTTF->setDelegate(this);
		
		//关闭输入
		//textField->detachWithIME();

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2, visibleSize.height/2+120));

		bRet = true;
	} while (0);

	return bRet;
}

void ExchangeLayer::onEnter()
{
	CCLayer::onEnter();

	//监听付费消息
	PayHelper::instance()->addDelegate(this);
	//更新钻石数量
	this->schedule(schedule_selector(ExchangeLayer::scheduleDiamondLabels), 0.1f);
}

void ExchangeLayer::keyBackClicked()
{
	quit();
}

bool ExchangeLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

bool ExchangeLayer::ccTouchBegan( cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent )
{
	if (isTouchInsideNode(pTouch, m_fetchBtn)) {
		exchange();
	}

	if (isTouchInsideNode(pTouch, m_close)) {
		quit();
	}

	if (isTouchInsideNode(pTouch, m_menuTextBtn)) {
		//开启输入
		m_textFieldTTF->attachWithIME();
	}

	return true;
}

void ExchangeLayer::exchange()
{
	PayHelper::instance()->addDelegate(this);
	//PayHelper::instance()->exchangeCode(m_string.c_str());
}

void ExchangeLayer::quit()
{
	//if (this->getParent()->getTag() == 1000) {
	//	//返回到礼包领取返回
	//}
	//else {
	//	//返回到主界面
	//	GameScene::instance()->returnToMainMenuFromExchange();
	//}
	//removeFromParent();
	removeFromParent();
	GameScene::instance()->returnToMainMenuFromExchange();
}

void ExchangeLayer::registerWithTouchDispatcher()
{
	//CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
	CCDirector::sharedDirector()->getTouchDispatcher()->addTargetedDelegate(this, 0, true);
}

// CCTextFieldDelegate protocol
bool ExchangeLayer::onTextFieldAttachWithIME(CCTextFieldTTF * pSender)
{
	return false;
}

bool ExchangeLayer::onTextFieldDetachWithIME(CCTextFieldTTF * pSender)
{
	return false;
}

bool ExchangeLayer::onTextFieldInsertText(CCTextFieldTTF * pSender, const char * text, int nLen)
{
	// if insert enter, treat as default to detach with ime
	if ('\n' == *text)
	{
		return false;
	}

	m_string.append(text);
	m_cdkeyLabel->setString(m_string.c_str());

	return false;
}

bool ExchangeLayer::onTextFieldDeleteBackward(CCTextFieldTTF * pSender, const char * delText, int nLen)
{
	if (nLen > 0) {
		if (!m_string.empty()) {
			m_string.erase(m_string.end()-1);
		}
	}

	m_cdkeyLabel->setString(m_string.c_str());

	return false;
}

void ExchangeLayer::updateTipsWithCode(int retcode, int diamonds)
{
	if (retcode == 0) {
		m_cdkeyTips->setString(CCString::createWithFormat(GET_STRING("CDKEY_success"), diamonds)->getCString());
		//m_cdkeyTips->setString(CCString::createWithFormat(Language::instance()->getString("CDKEY_success"), diamonds)->getCString());


	}
	else {
		m_cdkeyTips->setString(CCString::createWithFormat(GET_STRING("CDKEY_fail"))->getCString());
		//m_cdkeyTips->setString(CCString::createWithFormat(Language::instance()->getString("CDKEY_fail"))->getCString());


	}
}

void ExchangeLayer::paymentCallback( int retcode, const char *orderNo )
{
	std::string strOrderId = orderNo;
	CCLog("ExchangeLayer::paymentCallback begin retcode=%d, orderNo=%s", retcode, orderNo);
	//支付成功
	if (retcode == 0) {
		int diamonds = 0;
		if (strOrderId == "3") {
			diamonds = 10;
		}
		m_needAddDiamonds = diamonds;
	}
	else {
		m_needAddDiamonds = -1;
	}
	CCLog("ExchangeLayer::paymentCallback end");
}

void ExchangeLayer::scheduleDiamondLabels( float dt )
{
	if (m_needAddDiamonds > 0) {
		Config::instance()->addDiamond(m_needAddDiamonds);
		updateTipsWithCode(0, m_needAddDiamonds);
		m_needAddDiamonds = 0;
		GameScene::instance()->playEffect("coinsin.ogg");
	}
	else if (m_needAddDiamonds == -1) {
		updateTipsWithCode(-1, 0);
		m_needAddDiamonds = 0;
	}
}
