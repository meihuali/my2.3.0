package com.example.a77299007.myapplication.progress;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;
import android.view.Window;
import android.widget.TextView;
import com.example.a77299007.myapplication.R;


/**
 * 等待进度提示
 */
public class LoadingDialog extends Dialog {

    private ContentLoadingProgressBar progressBar;
    private TextView mTextView;

    public LoadingDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
       mTextView = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onAttachedToWindow() {

        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {

        super.onDetachedFromWindow();
    }

    @Override
    public void cancel() {

        super.cancel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void show() {
        super.show();
    }
    public void show(String text) {
        super.show();
        if (!TextUtils.isEmpty(text)) {
            mTextView.setText(text);
        } else {
            mTextView.setText("请稍后");
        }
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setMessage(String message) {
        mTextView.setText(message);
    }

    public void setMessage(int resId) {
        mTextView.setText(resId);
    }

    public static class Builder {
        Context context;
        String message;
        OnCancelListener mOnCancelListener;
        boolean mCancelable = true;
        boolean mCancelableOnTouchOutside = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener mOnCancelListener) {
            this.mOnCancelListener = mOnCancelListener;
            return this;
        }

        public Builder setmCancelable(boolean mCancelable) {
            this.mCancelable = mCancelable;
            return this;
        }

        public Builder setmCancelableOnTouchOutside(boolean mCancelableOnTouchOutside) {
            this.mCancelableOnTouchOutside = mCancelableOnTouchOutside;
            return this;
        }

        public LoadingDialog build() {
            LoadingDialog dialog = new LoadingDialog(context);
            if (!TextUtils.isEmpty(message))
                dialog.setMessage(message);
            dialog.setCancelable(mCancelable);
            dialog.setCanceledOnTouchOutside(mCancelableOnTouchOutside);
            if (mOnCancelListener != null) {
                dialog.setOnCancelListener(mOnCancelListener);

            }
            return dialog;
        }
    }
}
