#ifndef _LANGUAGE_H_
#define _LANGUAGE_H_

#include "cocos2d.h"

#define	  GET_STRING(key)	(Language::instance()->getString(key))

class Language
{
public:
	Language();
	~Language() {};

	static Language* instance();
	void load();
	const char *getString(char *key);

private:
	static Language *m_instance;
	cocos2d::CCDictionary *m_stringDict;
};

#endif  //#ifndef _LANGUAGE_H_