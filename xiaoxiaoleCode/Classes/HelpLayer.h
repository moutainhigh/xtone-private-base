#ifndef _HELPLAYER_H_
#define _HELPLAYER_H_

#include "cocos2d.h"

class HelpLayer : public cocos2d::CCLayer
{
public:
	HelpLayer();
	~HelpLayer();
	CREATE_FUNC(HelpLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();
	void quit();

	cocos2d::CCNode* createPropDesc(const char *icon, const char* desc);

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* event);
	void registerWithTouchDispatcher();
private:
	cocos2d::CCSprite *m_closeBtn;
};


#endif /* _HELPLAYER_H_ */
