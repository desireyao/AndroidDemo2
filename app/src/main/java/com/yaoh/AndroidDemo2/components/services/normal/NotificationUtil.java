package com.yaoh.AndroidDemo2.components.services.normal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by yaoh on 2018/10/15.
 */

public class NotificationUtil {
    private static final String TAG = "BSNotificationUtil";

    private volatile static NotificationUtil mInstance;

    private NotificationManager mNotificationManager;
    private Context mContext;

    private NotificationUtil(Context context) {
        mContext = context.getApplicationContext();
    }

    public static NotificationUtil get(Context context) {
        if (mInstance == null) {
            synchronized (NotificationUtil.class) {
                if (mInstance == null) {
                    mInstance = new NotificationUtil(context);
                }
            }
        }
        return mInstance;
    }

    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    //兼容android8.0以及之前版本获取Notification.Builder方法
    private Notification.Builder getNotificationBuilder(String title, String msg, int icon) {
        Notification.Builder builder = new Notification.Builder(mContext)
                .setAutoCancel(true)  //是否自动取消，设置为true，点击通知栏 ，移除通知
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(icon)  //通知栏消息小图标，不设置是不会显示通知的
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_HIGH)
//                .setContent(getRemoteView())
                //ledARGB 表示灯光颜色、ledOnMs 亮持续时间、ledOffMs 暗的时间
//                .setLights(Color.RED, 3000, 3000)
//                .setVibrate(new long[]{100, 100, 200})//震动的模式，第一次100ms，第二次100ms，第三次200ms
                //.setStyle(new Notification.BigTextStyle())
                ;

        //通过版本号判断兼容了低版本没有通知渠道方法的问题，只有当版本号大于26（Build.VERSION_CODES.O）时才使用渠道相关方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //builder的channelId需和下面channel的保持一致；
            builder.setChannelId("channel_id");
            NotificationChannel channel = new NotificationChannel("channel_id", "出入通知",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setBypassDnd(true);    //  设置可以绕过请勿打扰模式
            channel.canBypassDnd();        //  可否绕过请勿打扰模式

            //锁屏显示通知
            channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            channel.shouldShowLights();//是否会闪光
            channel.enableLights(true);//闪光
            //指定闪光时的灯光颜色，为了兼容低版本在上面builder上通过setLights方法设置了
            //channel.setLightColor(Color.RED);
            channel.canShowBadge();//桌面launcher消息角标
            channel.enableVibration(true);//是否允许震动
            //震动模式，第一次100ms，第二次100ms，第三次200ms，为了兼容低版本在上面builder上设置了
//            channel.setVibrationPattern(new long[]{100,100,200});
            channel.getAudioAttributes();//获取系统通知响铃声音的配置
            channel.getGroup();//获取通知渠道组

            //绑定通知渠道
            getNotificationManager().createNotificationChannel(channel);
        }

        return builder;
    }

//    private RemoteViews getRemoteView() {
//        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.layout_notification);//RemoveViews所加载的布局文件
////        remoteViews.setImageViewBitmap();
//        return remoteViews;
//    }

    public void notification(String title, String msg, int icon) {
        int notification_id = new Random().nextInt(10000) + 1000;
        Notification.Builder builder = getNotificationBuilder(title, msg, icon);
        getNotificationManager().notify(notification_id, builder.build());
    }

    public Notification createNotification(String title, String msg, int icon) {
        Notification.Builder builder = getNotificationBuilder(title, msg, icon);
        return builder.build();
    }

}
