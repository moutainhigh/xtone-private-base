#ifndef _GAMEOVERLAYER_H_
#define _GAMEOVERLAYER_H_

#include "cocos2d.h"

class GameOverLayer : public cocos2d::CCLayer
{
public:
	GameOverLayer();
	~GameOverLayer();
	CREATE_FUNC(GameOverLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();
	void enableInput();
	void disableInput();

	void closeButton();
	void continueGame();

	void countSchedule(float dt);
	void updateCountLabel(int count);

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	//virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* event);
	virtual bool ccTouchBegan(cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent);
	void registerWithTouchDispatcher();
private:
	cocos2d::CCSprite *m_close;
	cocos2d::CCSprite *m_continueGame;
	cocos2d::CCLabelTTF *m_countLabel;
	int m_count;
};


#endif /* _GAMEOVERLAYER_H_ */
