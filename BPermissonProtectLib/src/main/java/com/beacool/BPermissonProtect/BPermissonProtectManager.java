package com.beacool.BPermissonProtect;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoh on 2018/12/29.
 */

public class BPermissonProtectManager {

    private static final String TAG = "BPermissonProtect";

    protected static List<Intent> mLaunchIntentList = new ArrayList<>();
    protected static List<Intent> mPowerIntentList = new ArrayList<>();

    private static BPermissonProtectManager mInstance;
    private static Context mContext;

    private BPermissonProtectManager(Context context) {
        mContext = context.getApplicationContext();
        initLaunchIntentList();
        initPowerIntentList();
    }

    public static BPermissonProtectManager get(Context context) {
        if (mInstance == null) {
            mInstance = new BPermissonProtectManager(context);
        }
        return mInstance;
    }


    private void initLaunchIntentList() {
        //Android 7.0+ Doze 模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            boolean ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(mContext.getPackageName());
            if (!ignoringBatteryOptimizations) {
                Intent dozeIntent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                dozeIntent.setData(Uri.parse("package:" + mContext.getPackageName()));
                mLaunchIntentList.add(dozeIntent);
            }
        }

        // 小米 自启动管理
        Intent xiaomiIntent = new Intent();
        xiaomiIntent.setAction("miui.intent.action.OP_AUTO_START");
        xiaomiIntent.addCategory(Intent.CATEGORY_DEFAULT);
        mLaunchIntentList.add(xiaomiIntent);

        // 华为 自启管理
        Intent huaweiIntent = new Intent();
        huaweiIntent.setComponent(new ComponentName("com.huawei.systemmanager",
                "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"));
        mLaunchIntentList.add(huaweiIntent);

        // oppo自启动
        Intent oppoIntent = new Intent();
        oppoIntent.setComponent(new ComponentName("com.coloros.safecenter",
                "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
        mLaunchIntentList.add(oppoIntent);

        Intent oppoIntent2 = new Intent();
        oppoIntent2.setComponent(new ComponentName("com.color.safecenter",
                "com.color.safecenter.permission.startup.StartupAppListActivity"));
        mLaunchIntentList.add(oppoIntent2);

        Intent oppoIntent3 = new Intent();
        oppoIntent3.setComponent(new ComponentName("com.coloros.safecenter",
                "com.coloros.safecenter.startupapp.StartupAppListActivity"));
        mLaunchIntentList.add(oppoIntent3);
    }

    private void initPowerIntentList() {
        // 小米
        Intent xiaomiPowerIntent = new Intent();
        xiaomiPowerIntent.setComponent(new ComponentName("com.miui.powerkeeper",
                "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"));
        xiaomiPowerIntent.putExtra("package_name", mContext.getPackageName());
        xiaomiPowerIntent.putExtra("package_label", getApplicationName());
        mPowerIntentList.add(xiaomiPowerIntent);

        // 华为 锁屏清理
        Intent huaweiIntent = new Intent();
        huaweiIntent.setComponent(new ComponentName("com.huawei.systemmanager",
                "com.huawei.systemmanager.optimize.process.ProtectActivity"));
        mPowerIntentList.add(huaweiIntent);

        // oppo
        Intent oppoPowerIntent = new Intent();
        oppoPowerIntent.setComponent(new ComponentName("com.coloros.oppoguardelf",
                "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"));
        mPowerIntentList.add(oppoPowerIntent);
    }

    /**
     * 处理自启动
     */
    public boolean openSelfLaunchSetting(Activity activity) {
        boolean isExistActivity = false;
        for ( Intent intent : mLaunchIntentList) {
            if (!isActivityExists(intent)) {
                Log.e(TAG, " openSelfLaunchSetting --->  isActivityExists false: " + intent.toString());
                continue;
            }

            isExistActivity = true;
            startActivitySafely(activity, intent);
        }

        return isExistActivity;
    }

    /**
     * 处理
     *
     * @param activity
     */
    public boolean openPowerSetting(Activity activity) {
        boolean isExistActivity = false;

        for (Intent intent : mPowerIntentList) {
            if (!isActivityExists(intent)) {
                Log.e(TAG, " openPowerSetting ---> isActivityExists false: " + intent.toString());
                continue;
            }

            isExistActivity = true;
            startActivitySafely(activity, intent);
        }
        return isExistActivity;
    }

    /**
     * 判断本机上是否有能处理当前Intent的Activity
     */
    private boolean isActivityExists(Intent intent) {
        PackageManager pm = mContext.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list != null && list.size() > 0;
    }

    /**
     * 安全地启动一个Activity
     */
    protected void startActivitySafely(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static String sApplicationName;

    public String getApplicationName() {
        if (sApplicationName == null) {
            PackageManager pm;
            ApplicationInfo ai;
            try {
                pm = mContext.getPackageManager();
                ai = pm.getApplicationInfo(mContext.getPackageName(), 0);
                sApplicationName = pm.getApplicationLabel(ai).toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                sApplicationName = mContext.getPackageName();
            }
        }
        return sApplicationName;
    }

}
