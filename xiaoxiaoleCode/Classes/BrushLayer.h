#ifndef _BRUSHLAYER_H_
#define _BRUSHLAYER_H_

#include "cocos2d.h"

class BrushLayer : public cocos2d::CCLayer
{
public:
	BrushLayer();
	~BrushLayer();
	CREATE_FUNC(BrushLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();

	void setNeedChangeIndex(int index) { m_needChangeIndex = index; };
	void setArrowX(float x);

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* event);
	void registerWithTouchDispatcher();
private:
	cocos2d::CCSprite *m_bgBrush;
	cocos2d::CCSprite *m_arrow;
	cocos2d::CCSprite *m_back;
	cocos2d::CCSprite *m_close;
	int m_needChangeIndex;
};


#endif /* _BRUSHLAYER_H_ */
