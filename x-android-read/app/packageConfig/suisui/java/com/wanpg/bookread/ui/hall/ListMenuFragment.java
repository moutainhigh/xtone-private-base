package com.wanpg.bookread.ui.hall;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.umeng.fb.FeedbackAgent;
import com.wanpg.bookread.BaseFragment;
import com.wanpg.bookread.R;
import com.wanpg.bookread.ui.activity.BookDownloadActivity;
import com.wanpg.bookread.ui.activity.BookFileBrowserActivity;
import com.wanpg.bookread.ui.activity.BookSettingReadActivity;
import com.wanpg.bookread.ui.activity.BookSettingSoftActivity;

public class ListMenuFragment extends BaseFragment implements View.OnClickListener {

	
	private View mMainView;
	
	private ListView lv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mMainView = inflater.inflate(R.layout.layout_leftsi, null);
		LinearLayout ly1 =(LinearLayout)mMainView.findViewById(R.id.loc);
		LinearLayout ly2 =(LinearLayout)mMainView.findViewById(R.id.yuedu);
		LinearLayout ly3 =(LinearLayout)mMainView.findViewById(R.id.xiazai);
		LinearLayout ly4 =(LinearLayout)mMainView.findViewById(R.id.xitong);
		LinearLayout ly5 =(LinearLayout)mMainView.findViewById(R.id.yijian);
		LinearLayout ly6 =(LinearLayout)mMainView.findViewById(R.id.tuichu);
		ly1.setOnClickListener(this);
		ly2.setOnClickListener(this);
		ly3.setOnClickListener(this);
		ly4.setOnClickListener(this);
		ly5.setOnClickListener(this);
		ly6.setOnClickListener(this);
//		lv = (ListView) mMainView.findViewById(R.id.lv_menu_bar);
//		lv.setAdapter(new MenuBarListAdapter(this));
		return mMainView;
//		lv.setOnClickListener(this);
}

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()){

			case R.id.loc:

//					BookFileBrowserFragment fragment = new BookFileBrowserFragment();
//					BookMainActivity.fragmentManager.beginTransaction()
//						.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_right,
//							R.anim.slide_in_from_right, R.anim.slide_out_from_right)
//						.add(BookMainActivity.fragment_container_id, fragment)
//						.addToBackStack(null)
//						.commit();
				intent = new Intent();
				intent.setClass(mActivity, BookFileBrowserActivity.class);
				mActivity.startActivityForResult(intent, 1);
				mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
				break;

			case R.id.yuedu:

				intent = new Intent(mActivity, BookSettingReadActivity.class);
				mActivity.startActivity(intent);
				mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
				break;

			case R.id.xiazai:

				intent = new Intent(mActivity, BookDownloadActivity.class);
				mActivity.startActivity(intent);
				mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
				break;

			case R.id.xitong:

				intent = new Intent(mActivity, BookSettingSoftActivity.class);
				mActivity.startActivity(intent);
				mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
				break;

			case R.id.yijian:

				FeedbackAgent agent = new FeedbackAgent(mActivity);
				agent.startFeedbackActivity();
				mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
				break;

			case R.id.tuichu:
				mActivity.finish();
				break;
			default:
				break;
		}
	}
}
