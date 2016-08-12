#include "BrushLayer.h"
#include "GameScene.h"
#include "star.h"

using namespace cocos2d;

BrushLayer::BrushLayer()
{
	m_needChangeIndex = -1;
}

BrushLayer::~BrushLayer()
{

}

bool BrushLayer::init()
{
	bool bRet = false;
	do {
		CC_BREAK_IF(!CCLayer::init());
		CCSize visibleSize = CCDirector::sharedDirector()->getVisibleSize();
		this->setTouchEnabled(true);
		this->setKeypadEnabled(true);
		this->ignoreAnchorPointForPosition(false);

		m_arrow = CCSprite::create("paint_arrow.png");
		m_arrow->setAnchorPoint(ccp(0.5, 1));
		m_arrow->setPosition(ccp(10, m_arrow->getContentSize().height));

		m_bgBrush = CCSprite::create("paint_bg.png");
		m_bgBrush->setAnchorPoint(ccp(0, 0));
		m_bgBrush->setPosition(ccp(0, m_arrow->getContentSize().height-10));

		this->addChild(m_bgBrush);
		this->addChild(m_arrow);
		this->setContentSize(m_bgBrush->getContentSize());

		const char *fileNames[] = {
			"orange.png",
			"green.png",
			"red.png",
			"blue.png",
			"purple.png"
		};
		const int gap = 30;
		int offsetX = 10;

		for (int i = 0; i < 5; i++) {
			CCSprite *star = CCSprite::create(fileNames[i]);
			star->setAnchorPoint(ccp(0, 0.5));
			star->setPosition(ccp(offsetX, m_bgBrush->getContentSize().height/2));
			star->setTag(i+1);
			star->setScale(STAR_SCALE_FACTOR);
			m_bgBrush->addChild(star);
			offsetX += STAR_WIDTH + gap;
		}

		m_back = CCSprite::create("paint_back.png");
		m_back->setAnchorPoint(ccp(1, 0.5));
		m_back->setPosition(ccp(m_bgBrush->getContentSize().width-20, m_bgBrush->getContentSize().height/2));
		m_bgBrush->addChild(m_back);
		
		bRet = true;
	} while (0);

	return bRet;
}

void BrushLayer::onEnter()
{
	CCLayer::onEnter();
}

void BrushLayer::keyBackClicked()
{
	removeFromParent();
	GameScene::instance()->returnToGameLayerFromBrushLayer();
}

void BrushLayer::setArrowX( float x )
{
	m_arrow->setPositionX(x);
}

bool BrushLayer::isTouchInsideNode(CCTouch* touch, CCNode *node)
{
	CCPoint touchLocation = touch->getLocation();
	touchLocation = node->getParent()->convertToNodeSpace(touchLocation);
	CCRect bBox = node->boundingBox();

	return bBox.containsPoint(touchLocation);
}

void BrushLayer::ccTouchesEnded(CCSet* touches, CCEvent* event)
{
	CCTouch* touch = (CCTouch*)( touches->anyObject());

	if (isTouchInsideNode(touch, m_back)) {
		removeFromParent();
		GameScene::instance()->returnToGameLayerFromBrushLayer();
		return;
	}

	for (int i = 0; i < 5; i++) {
		CCNode *star = (CCNode *)m_bgBrush->getChildByTag(i+1);
		if (star && isTouchInsideNode(touch, star)) {
			removeFromParent();
			GameScene::instance()->returnToGameLayerFromBrushLayer();
			GameScene::instance()->useBrushByType(i+1, m_needChangeIndex);
			return;
		}
	}
}

void BrushLayer::registerWithTouchDispatcher()
{
	CCDirector::sharedDirector()->getTouchDispatcher()->addStandardDelegate(this, 0);
}
