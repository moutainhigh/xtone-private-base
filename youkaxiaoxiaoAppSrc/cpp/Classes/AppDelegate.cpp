#include "AppDelegate.h"
#include "GameScene.h"
#include "Config.h"
#include "Language.h"
#include "SimpleAudioEngine.h"

USING_NS_CC;

AppDelegate::AppDelegate() {

}

AppDelegate::~AppDelegate() 
{
}

bool AppDelegate::applicationDidFinishLaunching() {
    // initialize director
    CCDirector* pDirector = CCDirector::sharedDirector();
    pDirector->setOpenGLView(CCEGLView::sharedOpenGLView()); 

	CCSize designSize = CCSizeMake(DESIGN_WIDTH, DESIGN_HEIGHT);
	std::vector<std::string> searchPaths;
	
	searchPaths.push_back("Music");
	CCFileUtils::sharedFileUtils()->setSearchPaths(searchPaths);
	CCEGLView::sharedOpenGLView()->setDesignResolutionSize(designSize.width, designSize.height, kResolutionFixedWidth);

	//language load
	Language::instance()->load();

	//Config load
	Config::instance()->load();

	//frame cache load
	CCTextureCache* texCacher = CCTextureCache::sharedTextureCache();
	texCacher->addImage("star.png");
	texCacher->addImage("fireworks.png");

	//pDirector->setDisplayStats(true);
	pDirector->setAnimationInterval(1.0 / 60);

    GameScene::instance()->showMainMenu();
	pDirector->runWithScene(GameScene::scene());

	//payment listening
	PayHelper::instance()->initialize();

    return true;
}

// This function will be called when the app is inactive. When comes a phone call,it's be invoked too
void AppDelegate::applicationDidEnterBackground() {
    CCDirector::sharedDirector()->stopAnimation();
	
    // if you use SimpleAudioEngine, it must be pause
    CocosDenshion::SimpleAudioEngine::sharedEngine()->pauseBackgroundMusic();
}

// this function will be called when the app is active again
void AppDelegate::applicationWillEnterForeground() {
    CCDirector::sharedDirector()->startAnimation();

    // if you use SimpleAudioEngine, it must resume here
    CocosDenshion::SimpleAudioEngine::sharedEngine()->resumeBackgroundMusic();
}
