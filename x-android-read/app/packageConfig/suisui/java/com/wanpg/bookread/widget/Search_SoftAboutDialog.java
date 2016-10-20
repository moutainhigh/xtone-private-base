package com.wanpg.bookread.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wanpg.bookread.R;

public class Search_SoftAboutDialog extends AlertDialog {



    public Search_SoftAboutDialog(Context context, boolean cancelable,
                                  OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        // TODO Auto-generated constructor stub
    }

    public Search_SoftAboutDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    public Search_SoftAboutDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_adapter);
        findViewById(R.id.search_dialog_tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Search_SoftAboutDialog.this.dismiss();
            }
        });
    }
}
