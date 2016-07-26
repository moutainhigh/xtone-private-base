#ifndef __FireworkLayer_H__
#define __FireworkLayer_H__

#include "cocos2d.h"

class FireworkLayer: public cocos2d::CCLayer
{
public:
	FireworkLayer();
	virtual ~FireworkLayer();
	CREATE_FUNC(FireworkLayer);
	virtual bool init();

	void fire(bool loop);
	void delayedFire(float delay, bool loop);
	void delayedFireCallback(cocos2d::CCNode *sender, void *param);
	void stopFire();
private:
	cocos2d::CCParticleSystem* createFireParticle();
	cocos2d::CCPoint randomPosition();
	void showFire(cocos2d::CCParticleSystem* fire, float delay);
	void resetFireSelector(cocos2d::CCNode* sender, void* fire);
	cocos2d::CCParticleSystem* m_fire1;
	cocos2d::CCParticleSystem* m_fire2;
	cocos2d::CCParticleSystem* m_fire3;
	cocos2d::CCParticleSystem* m_fire4;
	cocos2d::CCParticleSystem* m_fire5;
	cocos2d::CCParticleSystem* m_fire6;
};

#endif /* __FireworkLayer_H__ */
