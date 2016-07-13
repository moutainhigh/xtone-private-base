#ifndef _FETCHLAYER_H_
#define _FETCHLAYER_H_

#include "cocos2d.h"

class FetchLayer : public cocos2d::CCLayer
{
public:
	FetchLayer();
	~FetchLayer();
	CREATE_FUNC(FetchLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();

	void fetchDiamond();
	void closeButton();

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	//virtual void ccTouchesBegan(cocos2d::CCSet *touches, cocos2d::CCEvent *event);
	virtual bool ccTouchBegan(cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent);
	void registerWithTouchDispatcher();
private:
	cocos2d::CCSprite *m_fetchBtn;
	cocos2d::CCSprite *m_closeBtn;
	cocos2d::CCSprite *m_give;
};


#endif /* _FETCHLAYER_H_ */
