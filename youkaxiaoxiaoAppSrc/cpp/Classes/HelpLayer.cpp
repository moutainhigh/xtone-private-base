#include "HelpLayer.h"
#include "GameScene.h"
#include "CCScale9Sprite.h"
#include "Language.h"

using namespace cocos2d;

HelpLayer::HelpLayer()
{
	
}

HelpLayer::~HelpLayer()
{

}

bool HelpLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);
		this->ignoreAnchorPointForPosition(false);

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

		CCSize size = CCSizeMake(430, 650);

		CCLayerColor *bgMask = CCLayerColor::create(ccc4(0, 0, 0, 145));
		bgMask->setAnchorPoint(ccp(0.5, 0.5));
		bgMask->setPosition(ccp(size.width/2, size.height/2+30*scalef));
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
		CCSprite *titleTextSprite = CCSprite::create("Title_Help.png");
		titleTextSprite->setAnchorPoint(ccp(0.5, 0.5));
		titleTextSprite->setPosition(
			ccp(titleSprite->getContentSize().width/2, offsetY));
		titleSprite->addChild(titleTextSprite);

		m_closeBtn = CCSprite::create("Cancel.png");
		m_closeBtn->setAnchorPoint(ccp(0.5, 0.5));
		m_closeBtn->setPosition(
			ccp(titleSprite->getContentSize().width/2+120, offsetY-10));
		titleSprite->addChild(m_closeBtn);

		CCLabelTTF *playTipsLabel = CCLabelTTF::create(
			CCString::createWithFormat("%s", GET_STRING("PlayMethod_1"))->getCString(), "Arial", 22);
		playTipsLabel->setAnchorPoint(ccp(0, 1));
		playTipsLabel->setPosition(ccp(15, size.height-60));
		playTipsLabel->setColor(ccBLACK);
		playTipsLabel->setDimensions(CCSizeMake(400, 50));
		this->addChild(playTipsLabel);

		CCSprite *tipImage1 = CCSprite::create("Help_1.png");
		tipImage1->setAnchorPoint(ccp(0.5, 1));
		tipImage1->setPosition(ccp(size.width/2, size.height-115));
		this->addChild(tipImage1);

		CCLabelTTF *scoreTipsLabel = CCLabelTTF::create(
			CCString::createWithFormat("%s", GET_STRING("PlayMethod_2"))->getCString(), "Arial", 22);
		scoreTipsLabel->setAnchorPoint(ccp(0.5, 1));
		scoreTipsLabel->setPosition(ccp(size.width/2, size.height-230));
		scoreTipsLabel->setColor(ccBLACK);
		this->addChild(scoreTipsLabel);

		CCSprite *tipImage2 = CCSprite::create("Help_2.png");
		tipImage2->setAnchorPoint(ccp(0.5, 1));
		tipImage2->setPosition(ccp(size.width/2, size.height-260));
		this->addChild(tipImage2);

		CCLabelTTF *propLabel = CCLabelTTF::create(
			CCString::createWithFormat("%s", GET_STRING("Props"))->getCString(), "Arial", 22);
		propLabel->setAnchorPoint(ccp(0, 1));
		propLabel->setPosition(ccp(15, size.height-380));
		propLabel->setColor(ccBLACK);
		this->addChild(propLabel);

		CCNode *bombProp = createPropDesc("Props_Bomb.png", GET_STRING("Help_Bomb"));
		bombProp->setAnchorPoint(ccp(0, 1));
		bombProp->setPosition(ccp(15, size.height-420));
		this->addChild(bombProp);

		CCNode *brushProp = createPropDesc("Props_Paint.png", GET_STRING("Help_Paint"));
		brushProp->setAnchorPoint(ccp(0, 1));
		brushProp->setPosition(ccp(15, size.height-470));
		this->addChild(brushProp);

		CCNode *refreshProp = createPropDesc("Props_Rainbow.png", GET_STRING("Help_Rainbow"));
		refreshProp->setAnchorPoint(ccp(0, 1));
		refreshProp->setPosition(ccp(15, size.height-520));
		this->addChild(refreshProp);

		/*CCLabelTTF *serviceTips = CCLabelTTF::create(
		CCString::createWithFormat("%s", GET_STRING("contract"))->getCString(), "Arial", 22);
		serviceTips->setAnchorPoint(ccp(0, 0));
		serviceTips->setPosition(ccp(15, 20));
		serviceTips->setColor(ccBLACK);
		serviceTips->setDimensions(CCSizeMake(400, 60));
		this->addChild(serviceTips);*/

		this->setAnchorPoint(ccp(0.5, 0.5));
		this->setPosition(ccp(visibleSize.width/2, visibleSize.height/2-30*scalef));

		bRet = true;
	} while (0);

	return bRet;
}

cocos2d::CCNode* HelpLayer::createPropDesc( const char *icon, const char* desc )
{
	CCNode *node = CCNode::create();
	int w=0;
	int h=0;

	CCSprite *iconSprite = CCSprite::create(icon);
	iconSprite->setAnchorPoint(ccp(0, 0));
	iconSprite->setPosition(ccp(0, 0));
	node->addChild(iconSprite);

	w += iconSprite->getContentSize().width;
	h = iconSprite->getContentSize().height;

	CCLabelTTF *descLabel = CCLabelTTF::create(
		CCString::createWithFormat("%s", desc)->getCString(), "Arial", 22);
	descLabel->setAnchorPoint(ccp(0, 0));
	descLabel->setPosition(ccp(55, 22));
	descLabel->setColor(ccBLACK);
	node->addChild(descLabel);

	w += descLabel->getContentSize().width;
	w += 10;

	CCLabelTTF *consumeLabel = CCLabelTTF::create(
		CCString::createWithFormat(GET_STRING("Help_coin"), 5)->getCString(), "Arial", 22);
	consumeLabel->setAnchorPoint(ccp(0, 0));
	consumeLabel->setPosition(ccp(55, 0));
	consumeLabel->setColor(ccBLACK);
	node->addChild(consumeLabel);

	node->setContentSize(CCSizeMake(w, h));

	return node;
}

void HelpLayer::onEnter()
{
	CCLayer::onEnter();
}

void HelpLayer::keyBackClicked()
{
	quit();
}

void HelpLayer::quit()
{
	removeFromParent();
	GameScene::instance()->returnToMainMenuFromHelp();
}

bool HelpLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void HelpLayer::ccTouchesEnded(CCSet* touches, CCEvent* event)
{
	CCTouch* touch = (CCTouch*)( touches->anyObject());

	if (isTouchInsideNode(touch, m_closeBtn)) {
		quit();
		return;
	}
}

void HelpLayer::registerWithTouchDispatcher()
{
	CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
}
