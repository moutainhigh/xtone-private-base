#include "ShopLayer.h"
#include "GameScene.h"
#include "CCScale9Sprite.h"
#include "Language.h"
#include "Config.h"

using namespace cocos2d;

const int BUTTON_TAG = 10;

ShopLayer::ShopLayer()
{
	m_needAddDiamonds = 0;
}

ShopLayer::~ShopLayer()
{

}

bool ShopLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);

		CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
		float scalef = visibleSize.height/DESIGN_HEIGHT;

		std::string str = "Dialog_bg.png";
		CCSprite *pBg = CCSprite::create(str.c_str());

		CCSize sizeBg = pBg->getContentSize();
		CCRect fullRect = CCRectMake(0, 0, sizeBg.width, sizeBg.height);
		CCRect insetRect = CCRectMake(30, 30, sizeBg.width-30*2, sizeBg.height-30*2);
		CCScale9Sprite *pScaleSprite = CCScale9Sprite::create(str.c_str(), fullRect, insetRect);
		if (!pScaleSprite) {
			return bRet;
		}

		CCSize size = CCSizeMake(450, 600);

		CCLayerColor *bgMask = CCLayerColor::create(ccc4(0, 0, 0, 145));
		bgMask->setAnchorPoint(ccp(0.5, 0.5));
		bgMask->setPosition(ccp(size.width/2, size.height/2+20*scalef));
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
		titleSprite->setPosition(ccp(size.width/2, size.height));
		this->addChild(titleSprite);

		int offsetY = titleSprite->getContentSize().height/2+25;
		CCSprite *titleTextSprite = CCSprite::create("Title_shop.png");
		titleTextSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleTextSprite->setPosition(
			ccp(titleSprite->getContentSize().width/2, offsetY));
		titleSprite->addChild(titleTextSprite);

		m_closeBtn = CCSprite::create("Cancel.png");
		m_closeBtn->setAnchorPoint(ccp(0.5, 0.5));
		m_closeBtn->setPosition(
			ccp(titleSprite->getContentSize().width/2+120, offsetY-10));
		titleSprite->addChild(m_closeBtn);

		createListMenu();

		/*CCLabelTTF *inChargeTips = CCLabelTTF::create(
		CCString::createWithFormat("%s", GET_STRING("Tel"))->getCString(), "Arial", 22);
		inChargeTips->setAnchorPoint(ccp(0.5, 0));
		inChargeTips->setPosition(ccp(size.width/2, 5));
		inChargeTips->setColor(ccBLACK);
		this->addChild(inChargeTips);*/

		CCLabelTTF *hasLabel = CCLabelTTF::create(
			CCString::createWithFormat("%s", GET_STRING("Have"))->getCString(), "Arial", 22);
		hasLabel->setAnchorPoint(ccp(0, 0));
		hasLabel->setPosition(ccp(120, 45));
		hasLabel->setColor(ccBLACK);
		this->addChild(hasLabel);

		CCSprite *diamondHas = CCSprite::create("diamond.png");
		diamondHas->setAnchorPoint(ccp(0, 0));
		diamondHas->setPosition(ccp(180, 35));
		this->addChild(diamondHas);

		int currDiamonds = Config::instance()->diamonds();
		m_diamondTotalLabel = CCLabelTTF::create(
			CCString::createWithFormat(GET_STRING("HowMany"), currDiamonds)->getCString(), "Arial", 22);
		m_diamondTotalLabel->setAnchorPoint(ccp(0, 0));
		m_diamondTotalLabel->setPosition(ccp(240, 45));
		m_diamondTotalLabel->setColor(ccBLACK);
		this->addChild(m_diamondTotalLabel);

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2-size.width/2, visibleSize.height/2-size.height/2-20*scalef));

		bRet = true;
	} while (0);

	return bRet;
}

void ShopLayer::onEnter()
{
	CCLayer::onEnter();

	//监听付费消息
	PayHelper::instance()->addDelegate(this);
	//更新钻石数量
	this->schedule(schedule_selector(ShopLayer::scheduleDiamondLabels), 0.1f);
}

void ShopLayer::onExit()
{
	PayHelper::instance()->removeDelegate();
	CCLayer::onExit();
}

void ShopLayer::keyBackClicked()
{
	quit();
}

bool ShopLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void ShopLayer::ccTouchesEnded(CCSet* touches, CCEvent* event)
{
	CCTouch* touch = (CCTouch*)( touches->anyObject());

	if (isTouchInsideNode(touch, m_closeBtn)) {
		quit();
		return;
	}

	for (int i = 0; i < (sizeof(s_shop_data)/sizeof(s_shop_data[0])); i++) {
		CCNode *listItem = (CCNode *)this->getChildByTag(i+1);
		if (listItem) {
			CCNode *button = listItem->getChildByTag(BUTTON_TAG);
			if (button && isTouchInsideNode(touch, button)) {
				const char *orderId = s_shop_data[i].orderId;
				int money = s_shop_data[i].dollar;
				GameScene::instance()->buyDiamonds(money, orderId, "shop buy");
				return;
			}
		}
	}
}

void ShopLayer::registerWithTouchDispatcher()
{
	CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
}

void ShopLayer::quit()
{
	removeFromParent();
	GameScene::instance()->returnToMainMenuFromShop();
}

const float s_scale_y = 0.5f;

void ShopLayer::createListMenu()
{
	int offsetY = this->getContentSize().height - 50;
	for (int i = 0; i < (sizeof(s_shop_data)/sizeof(s_shop_data[0])); i++) {
		CCNode *listItem = createListItem(
			s_shop_data[i].diamonds, s_shop_data[i].gives, s_shop_data[i].dollar/100);
		listItem->setPosition(10, offsetY);
		offsetY -= (5+listItem->getContentSize().height * s_scale_y);
		listItem->setTag(i+1);
		this->addChild(listItem);
	}
}

cocos2d::CCNode * ShopLayer::createListItem( int diamondNum, int giveDiamondNum, int dollar)
{
	CCNode *listItemNode = CCNode::create();
	listItemNode->setAnchorPoint(ccp(0, 1));
	listItemNode->setScaleY(s_scale_y);

	CCSprite *bg = CCSprite::create("Dialog_Item2.png");
	bg->setAnchorPoint(ccp(0.5, 0.5));
	bg->setPosition(ccp(bg->getContentSize().width/2, bg->getContentSize().height/2));
	listItemNode->setContentSize(bg->getContentSize());
	listItemNode->addChild(bg);

	int height = bg->getContentSize().height;
	CCSprite *diamondSprite = CCSprite::create("diamond.png");
	diamondSprite->setAnchorPoint(ccp(0, 0.5));
	diamondSprite->setPosition(ccp(10, height/2));
	listItemNode->addChild(diamondSprite);

	CCLabelTTF *diamondNumLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("HowMany"), diamondNum)->getCString(), "Arial", 28);
	diamondNumLabel->setAnchorPoint(ccp(0, 0.5));
	diamondNumLabel->setPosition(ccp(70, height/2));
	listItemNode->addChild(diamondNumLabel);

	CCSprite *giveSprite = CCSprite::create("zengsong.png");
	giveSprite->setAnchorPoint(ccp(0, 0.5));
	giveSprite->setScale(0.3f);
	giveSprite->setPosition(ccp(150, height/2));
	listItemNode->addChild(giveSprite);

	CCLabelTTF *giveDiamondNumLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("HowMany"), giveDiamondNum)->getCString(), "Arial", 28);
	giveDiamondNumLabel->setAnchorPoint(ccp(0, 0.5));
	giveDiamondNumLabel->setPosition(ccp(210, height/2));
	listItemNode->addChild(giveDiamondNumLabel);

	CCSprite *button = CCSprite::create("menu_money.png");
	button->setAnchorPoint(ccp(0, 0.5));
	button->setPosition(ccp(280, height/2));
	button->setTag(BUTTON_TAG);
	listItemNode->addChild(button);

	CCLabelTTF *moneyLabel = CCLabelTTF::create(
		CCString::createWithFormat("%s%d%s", GET_STRING("MoneyUnit1"), dollar, GET_STRING("MoneyUnit2"))->getCString(), "Arial", 28);
	moneyLabel->setAnchorPoint(ccp(0.5, 0.5));
	moneyLabel->setPosition(ccp(button->getContentSize().width/2, button->getContentSize().height/2));
	button->addChild(moneyLabel);

	return listItemNode;
}

void ShopLayer::updateDiamond()
{
	int num = Config::instance()->diamonds();
	m_diamondTotalLabel->setString(
		CCString::createWithFormat(GET_STRING("HowMany"), num)->getCString());
}

void ShopLayer::paymentCallback( int retcode, const char *orderNo )
{
	CCLog("ShopLayer::paymentCallback begin %d, orderNo=%s", retcode, orderNo);
	std::string strOrderNo = orderNo;
	//支付成功
	if (retcode == 0) {
		int diamonds = getDiamonds(orderNo);
		if (diamonds > 0) {
			m_needAddDiamonds = diamonds;
		}
	}
	CCLog("ShopLayer::paymentCallback end");
}

int ShopLayer::getDiamonds( const char *orderNo )
{
	std::string strOrderID = orderNo;
	int diamonds = 0;

	for (unsigned int i=0; i<(sizeof(s_shop_data) / sizeof(s_shop_data[0])); i++) {
		if (strOrderID == s_shop_data[i].orderId) {
			diamonds = s_shop_data[i].diamonds + s_shop_data[i].gives;
			break;
		}
	}

	return diamonds;
}

void ShopLayer::scheduleDiamondLabels( float dt )
{
	if (m_needAddDiamonds > 0) {
		Config::instance()->addDiamond(m_needAddDiamonds);
		m_needAddDiamonds = 0;
		updateDiamond();
		GameScene::instance()->playEffect("coinsin.ogg");
	}
}
