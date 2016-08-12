#ifndef _GAMEPAUSELAYER_H_
#define _GAMEPAUSELAYER_H_

#include "cocos2d.h"

class GamePauseLayer : public cocos2d::CCLayer
{
public:
	GamePauseLayer();
	~GamePauseLayer();
	CREATE_FUNC(GamePauseLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();

	void returnToMainMenu();
	void resumeGame();

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* event);
	void registerWithTouchDispatcher();
private:
	cocos2d::CCSprite *m_returnBtn;
	cocos2d::CCSprite *m_resumeBtn;
	cocos2d::CCSprite *m_pause;
	cocos2d::CCSprite *m_close;
};


#endif /* _GAMEPAUSELAYER_H_ */
