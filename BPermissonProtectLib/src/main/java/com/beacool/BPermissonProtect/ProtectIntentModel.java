package com.beacool.BPermissonProtect;

import android.content.Intent;

/**
 * Created by yaoh on 2018/12/29.
 */

public class ProtectIntentModel {

    public Intent intent;
    public int type;

    public ProtectIntentModel(Intent intent, int type) {
        this.intent = intent;
        this.type = type;
    }
}
