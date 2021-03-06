LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#$(call import-add-path,$(LOCAL_PATH)/../../cocos2d)
#$(call import-add-path,$(LOCAL_PATH)/../../cocos2d/external)
#$(call import-add-path,$(LOCAL_PATH)/../../cocos2d/cocos)

 #LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog 2


LOCAL_MODULE := cocos2dcpp_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp

LOCAL_SRC_FILES := hellocpp/main.cpp \
                   ../../Classes/AppDelegate.cpp \
                   ../../Classes/AppuSDKInterfaceHelper.cpp \
                   ../../Classes/AudioManager.cpp \
                   ../../Classes/BubbleNode.cpp \
                   ../../Classes/CherryNode.cpp \
                   ../../Classes/Config.cpp \
                   ../../Classes/GameObject.cpp \
                   ../../Classes/GameOverLayer.cpp \
                   ../../Classes/GameScene.cpp \
                   ../../Classes/HazardNode.cpp \
                   ../../Classes/HelloWorldScene.cpp \
                   ../../Classes/Language.cpp \
                   ../../Classes/MainLayer.cpp \
                   ../../Classes/PayHelper.cpp \
                   ../../Classes/PopoBirdSprite.cpp \
                   ../../Classes/PopupLayer.cpp \
                   ../../Classes/WallNode.cpp  \
                   ../../Classes/HttpTest.cpp 
                   
                   

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../Classes

LOCAL_WHOLE_STATIC_LIBRARIES += cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocosdenshion_static
LOCAL_WHOLE_STATIC_LIBRARIES += box2d_static
LOCAL_WHOLE_STATIC_LIBRARIES += chipmunk_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocos_extension_static

include $(BUILD_SHARED_LIBRARY)


$(call import-module,cocos2dx)
$(call import-module,cocos2dx/platform/third_party/android/prebuilt/libcurl)
$(call import-module,CocosDenshion/android)
$(call import-module,extensions)
$(call import-module,external/Box2D)
$(call import-module,external/chipmunk)
