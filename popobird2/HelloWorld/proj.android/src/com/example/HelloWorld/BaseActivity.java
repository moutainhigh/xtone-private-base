package com.example.HelloWorld;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xinmei365.game.proxy.GameProxy;

public class BaseActivity extends Cocos2dxActivity{
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameProxy.getInstance().onCreate(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		GameProxy.getInstance().onStop(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		GameProxy.getInstance().onDestroy(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		GameProxy.getInstance().onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		GameProxy.getInstance().onPause(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		GameProxy.getInstance().onStart(this);
	}

	@Override
	public void onRestart() {
		super.onRestart();
		GameProxy.getInstance().onRestart(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		GameProxy.getInstance().onActivityResult(this, requestCode, resultCode,
				data);
	}

	@Override
	public void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		GameProxy.getInstance().onNewIntent(intent);
	}
	
	
	
}
