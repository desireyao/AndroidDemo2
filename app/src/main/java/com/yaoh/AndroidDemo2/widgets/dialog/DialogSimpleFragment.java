package com.yaoh.AndroidDemo2.widgets.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

import com.yaoh.AndroidDemo2.R;

/**
 * Created by yaoh on 2019/3/4
 */
public class DialogSimpleFragment extends BaseDialogFragment{

    @Override
    public Dialog onBaseCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        mDialog = builder
                .setView(R.layout.dialog_simple_layout)
                .create();
        return mDialog;
    }

    @Override
    public void onBaseDialogStart() {
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(null);
//        window.getDecorView().setPadding(ViewUtil.dp2px(16), 0,
//                ViewUtil.dp2px(16), 0); //消除边距
//        window.setGravity(Gravity.TOP);
//        window.setWindowAnimations(R.style.dialogAnim);
//        mDialog.setCancelable(false);
//        mDialog.setCanceledOnTouchOutside(false);

        //设置margin为屏幕的20%
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.verticalMargin = 0.2f;

    }
}
