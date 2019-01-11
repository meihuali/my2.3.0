package com.example.a77299007.myapplication.progress;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import com.example.a77299007.myapplication.R;

public class ProgressDialog extends Dialog {
    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.TransparentDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = super.getWindow();

        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT ;
            //计算dialog的高度第二种方式
//            params.height = DimensionUtils.getDisplayMetrics().heightPixels - InputUtil.INSTANCE.getStatusBarHeight() -
//                    App.getsInstance().getResources().getDimensionPixelSize(R.dimen.toolbar_height);
//            params.gravity = Gravity.BOTTOM;
            window.setAttributes(params);
        }
        super.setContentView(R.layout.dialog_progress);
//        super.setCanceledOnTouchOutside(true);//点击外部取消对话框
        super.setCanceledOnTouchOutside(false);
    }
}
