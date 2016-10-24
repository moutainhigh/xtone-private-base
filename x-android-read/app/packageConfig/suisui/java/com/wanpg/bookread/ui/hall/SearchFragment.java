package com.wanpg.bookread.ui.hall;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wanpg.bookread.BaseFragment;
import com.wanpg.bookread.R;
import com.wanpg.bookread.ui.adapter.SearchAdapter;
import com.wanpg.bookread.utils.LogUtil;
import com.wanpg.bookread.widget.KeywordsFlowFrameLayout;
import com.wanpg.bookread.widget.Notice;
import com.wanpg.bookread.widget.Search_SoftAboutDialog;
import com.wanpg.bookread.widget.Search_SoftAboutDialogone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//搜索页面
public class SearchFragment extends BaseFragment {

    private View parent;

    private EditText et_search_content;
    private ImageButton ib_search;
    private RelativeLayout rl_search_cancel;

    private Button bt_change_searchwords;
    private KeywordsFlowFrameLayout key_words_layout;
    private String searchKeysAll = null;
    private String[] searchKeys = null;



    private ListView search_lv;
    private String[] info_Names={"黑色风暴","红心灿烂封面","历史上的那些人","卧底","虚空里的盛宴","清风明月","心缘","幽默的智慧"};
    private Integer[] info_icon={R.drawable.name_1,R.drawable.name_2,R.drawable.name_3,R.drawable.name_4,
            R.drawable.name_5,R.drawable.name_6,R.drawable.name_7,R.drawable.name_8};
    private String[] info_author={"周力军     著","曹其明     著","何跃青     著","周力军     著","孙欲言     著","刘瑛      著","心缘      著","邢延国     著"};
    private String[] info_remark={"简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","","","","","",""};
    private String[] info_size={"21万字","15万字","17万字","48万字","17万字","15万字","48万字","21万字"};
    private ArrayList<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();







    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogUtil.D("wanpg", this.getClass().getSimpleName()+"_onCreateView");
        if(parent==null){
            parent = inflater.inflate(R.layout.layout_booksearch, null);
            initData();
        }else{
            ((ViewGroup)parent.getParent()).removeView(parent);
        }
        return parent;
    }

    private void initData() {
        // TODO Auto-generated method stub
    /*	searchKeysAll = mActivity.getSharedPreferences(Config.CONFIG_SOFT, Context.MODE_PRIVATE)
        		.getString("search_keys", "海贼之横行天下,疆域秘藏,异世怪医,天地决,天路,亲历五月");
    	
    	long curDate = System.currentTimeMillis();
    	long searchDate = mActivity.getSharedPreferences(Config.CONFIG_SOFT, Context.MODE_PRIVATE)
        		.getLong("search_key_date", curDate);
    	if(searchDate==curDate || (curDate-searchDate)>5 * 24 * 60 * 60 * 1000){
    		//说明是第一次打开,和五天前获取的数据
            LogUtil.D("wanpg", "BookSearchFragment_initData 获取新数据");
    		initSearchTags();
    	}*/
        initUI();
    }

    private void initUI() {
        // TODO Auto-generated method stub
        et_search_content = (EditText) parent.findViewById(R.id.et_search_content);
      /*  ib_search = (ImageButton) parent.findViewById(R.id.ib_search);
        rl_search_cancel = (RelativeLayout) parent.findViewById(R.id.rl_search_cancel);*/

        search_lv = (ListView) parent.findViewById(R.id.search_lv);

        //添加联系人信息
    /*    for(int i=0;i<info_Names.length;i++){
            Map<String,Object> item = new HashMap<String,Object>();
           item.put("img",info_icon[i] );
            item.put("name", info_Names[i]);
            item.put("jh",R.drawable.jh);
            mInfos.add(item);
        }*/

        //添加联系人信息
        for(int i=0;i<info_Names.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",info_icon[i] );
            map.put("name", info_Names[i]);
            map.put("author",info_author[i]);
            map.put("jh",R.drawable.jh);
            map.put("remark",info_remark[i]);
            map.put("size",info_size[i]);
            listItems.add(map);
        }



        SearchAdapter searchAdapter  = new SearchAdapter(getActivity(),listItems);
        search_lv.setAdapter(searchAdapter);


        search_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              /* Search_SoftAboutDialog aboutDialog = new Search_SoftAboutDialog(getActivity(), R.style.about_dialog);
               Window win = aboutDialog.getWindow();
               WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
               layoutParams.x = 0;
               layoutParams.y = 0;
               layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
               layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
               win.setAttributes(layoutParams);
               aboutDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
               aboutDialog.show();*/

                final  Search_SoftAboutDialogone ab = new Search_SoftAboutDialogone(getActivity());

                ab.setIcon((Integer) listItems.get(position).get("img"));
                ab.setTitle( listItems.get(position).get("name")+"");
                ab.setauthor(listItems.get(position).get("author")+"");

                ab.setConfirm();


            /* ab.show();*/
            }
        });


       /* //适配器
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getdata(),R.layout.searchfragment_listviewadaoter,
                new String[]{"imag","name","jh"},new int[]{R.id.search_imge_icon,R.id.search_name,R.id.search_jh});


        //设置适配器
        search_lv.setAdapter(adapter);*/


        //搜索栏功能
        et_search_content.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView tv, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                commitSearchData(tv.getText().toString());
                return false;
            }
        });

     /*   rl_search_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                et_search_content.setText("");
                rl_search_cancel.setVisibility(View.INVISIBLE);
            }
        });*/
     /*   ib_search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                commitSearchData(et_search_content.getText().toString());
            }
        });*/
      /*  key_words_layout = (KeywordsFlowFrameLayout) parent.findViewById(R.id.key_words_layout);
        key_words_layout.setDuration(666);
        key_words_layout.setOnItemClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (v instanceof TextView) {
                    String keyword = ((TextView) v).getText().toString();
                    commitSearchData(keyword);
                }
            }
        });*/
   /*     searchKeys = randomSearchKeys();
        key_words_layout.rubKeywords();
        for (int i = 0; i < searchKeys.length; i++) {
            key_words_layout.feedKeyword(searchKeys[i]);
        }
        key_words_layout.go2Show(KeywordsFlowFrameLayout.ANIMATION_IN);
        
        bt_change_searchwords = (Button) parent.findViewById(R.id.bt_change_searchwords);

        bt_change_searchwords.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	nextSearchTags();
            }
        });

        parent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                hideSoftKeyborad();
            }
        });
*/
    }

    private void commitSearchData(String data) {
        // TODO Auto-generated method stub
        hideSoftKeyborad();
        if (data.equals("")) {
            Notice.showToast("您输入的内容为空！");
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("searchWord", data);
            onFragmentDo(TYPE_TO_BOOK_SEARCH_RESULT, bundle);
        }
    }
/*
    private void nextSearchTags(){
    	searchKeys = randomSearchKeys();
        key_words_layout.rubKeywords();
        for (int i = 0; i < searchKeys.length; i++) {
            key_words_layout.feedKeyword(searchKeys[i]);
        }
        key_words_layout.go2Show(KeywordsFlowFrameLayout.ANIMATION_IN);
    }*/
    
 /*   private void initSearchTags() {
        // TODO Auto-generated method stub
        new BackTask() {
            @Override
            public void doInThreadWhenCalling(String info) {
                // TODO Auto-generated method stub

            }

            @Override
            public String doInThread() {
                // TODO Auto-generated method stub
                searchKeysAll = ShuPengApi.getHotSearchWords(1, 100);
                mActivity.getSharedPreferences(Config.CONFIG_SOFT, Context.MODE_PRIVATE)
            	.edit().putString("search_keys", searchKeysAll).commit();

                mActivity.getSharedPreferences(Config.CONFIG_SOFT, Context.MODE_PRIVATE)
            	.edit().putLong("search_key_date", System.currentTimeMillis()).commit();
                return null;
            }

            @Override
            public void doBeforeThread() {
                // TODO Auto-generated method stub

            }

            @Override
            public void doAfterThread(String result) {
                // TODO Auto-generated method stub
            }
        }.submit();

    }
*/
   /* private String[] randomSearchKeys() {
    	String s1[] = null;
    	String s2[] = searchKeysAll.split(",");
    	int length = s2.length;
    	if(length==6){
    		s1 = s2;
    	}else if(length < 6){
    		s1 = "海贼之横行天下,疆域秘藏,异世怪医,天地决,天路".split(",");
    	}else{
    		int[] a = getRandomPos(6, s2.length);
    		s1 = new String[a.length];
    		for(int i=0;i<a.length;i++){
    			s1[i] = s2[a[i]];
    		}
    	}
    	return s1;
	}*/

    /**
     * 返回rSize个数的数组，随机的，返回在0-fsize中取，包括0，不包括fsize
     * @param rSzie
     * @param fSize
     * @return
     */
    private int[] getRandomPos(int rSzie, int fSize){
        int[] a = new int[rSzie];
        for(int i=0;i<rSzie;i++){
            a[i] = (int) (Math.random() * fSize);
            for (int j=0; j<i;j++){
                if (a[j] == a[i]){
                    i--;
                    break;
                }
            }
        }
        return a;
    }

    public void hideSoftKeyborad() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        et_search_content.setCursorVisible(false);
        imm.hideSoftInputFromWindow(parent.getWindowToken(), 0);
    }



  /*  //初始化商品信息
    private List<Map<String,Objects>>getdata(){

        //添加联系人信息
        for(int i=0;i<info_Names.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",info_icon[i] );
            map.put("name", info_Names[i]);
            map.put("jh",R.drawable.jh);
            listItems.add(map);
        }
        return listItems;
    }
*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
    }
}
