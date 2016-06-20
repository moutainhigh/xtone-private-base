package com.wanpg.bookread.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanpg.bookread.R;

public class Search_SoftAboutDialogone extends AlertDialog {

  Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    TextView confirm;
    TextView author;
    ImageView iv_img;



    public Search_SoftAboutDialogone(Context context, boolean cancelable,
                                     OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        // TODO Auto-generated constructor stub
    }

    public Search_SoftAboutDialogone(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    public Search_SoftAboutDialogone(Context context) {
        super(context);
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        window.setContentView(R.layout.search_adapter);
        iv_img = (ImageView) window.findViewById(R.id.search_dialog_imge_icon);
        titleView = (TextView) window.findViewById(R.id.search_dialog_name);
        confirm = (TextView) window.findViewById(R.id.search_dialog_tv_confirm);
        author = (TextView) window.findViewById(R.id.search_dialog_author);

    }

    public void setConfirm() {

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });
    }


    public void setauthor(int resId){

        author.setText(resId);

    }

    public void setauthor(String title){

        author.setText(title);
    }

    public void setTitle(int resId){

        titleView.setText(resId);

    }

    public void setTitle(String title){

        titleView.setText(title);
    }

    public void setIcon(int resId){

        iv_img.setImageResource(resId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setConfirm();
    }

    /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_adapter);
        findViewById(R.id.search_dialog_tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Search_SoftAboutDialogone.this.dismiss();
            }
        });
    }*/
}
