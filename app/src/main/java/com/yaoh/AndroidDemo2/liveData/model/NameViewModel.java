package com.yaoh.AndroidDemo2.liveData.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author yaoh
 * @create 2019-08-01
 * @Describe
 */
public class NameViewModel extends ViewModel {

    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }





}
