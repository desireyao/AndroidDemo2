package com.yaoh.AndroidDemo2.widgets.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.widget.Button;

/**
 * Created by yaoh on 2018/4/8.
 */

public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    protected String TAG = getClass().getSimpleName();

    public DialogButtonClickListener listener;
    protected Button mPositiveButton;
    protected Button mNegativeButton;

    protected OnDialogStartListener mOnDialogStartListener;
    protected AlertDialog mDialog;

    private boolean isStarted;

    protected OnDialogDismissListener mOnDialogDismissListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return onBaseCreateDialog(savedInstanceState);
    }

    public void setOnButtonClickListener(DialogButtonClickListener listener) {
        this.listener = listener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener mOnDialogDismissListener) {
        this.mOnDialogDismissListener = mOnDialogDismissListener;
    }

    public interface DialogButtonClickListener {

        public void onPositiveClick();

        public void onNegativeClick();
    }


    @Override
    public void onStart() {
        super.onStart();

        if (!isStarted) {
            isStarted = true;

            onSetWindowAnimation();

            mPositiveButton = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            mNegativeButton = mDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            onBaseDialogStart();

            if (mOnDialogStartListener != null) {
                mOnDialogStartListener.onDialogStart();
            }
        }
    }

    public abstract Dialog onBaseCreateDialog(Bundle savedInstanceState);

    public abstract void onBaseDialogStart();

    public void onSetWindowAnimation() {
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
//        window.setWindowAnimations(R.style.dialogAnim2);

        window.setWindowAnimations(android.R.style.Animation_Dialog);


    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mOnDialogDismissListener != null) {
            mOnDialogDismissListener.onDialogDismiss();
        }
    }

    public void showDialog(FragmentActivity activity, OnDialogStartListener listener) {
        show(activity.getSupportFragmentManager(), "");

        mOnDialogStartListener = listener;
    }

    public void showDialog(FragmentActivity activity) {
        showDialog(activity, null);
    }

    public interface OnDialogStartListener {
        public void onDialogStart();
    }

    public interface OnDialogDismissListener {
        public void onDialogDismiss();
    }
}
