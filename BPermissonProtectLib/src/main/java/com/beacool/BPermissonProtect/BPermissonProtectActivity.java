package com.beacool.BPermissonProtect;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beacool.BPermissonProtect.utils.ScreenUtil;

public class BPermissonProtectActivity extends AppCompatActivity implements View.OnClickListener {

    protected Toolbar mToolbar;
    protected TextView include_toolbar_title;

    private WebView mWebView;
    private Button btn_self_start_permission;
    private Button btn_power_protection;
    private RelativeLayout layout_btn_protect;

    private BPermissonProtectManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpermisson_protect);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initStatusBar();
        initTitle("后台权限设置", true);
        initData();
        initView();
    }

    private void initData() {
        mManager = BPermissonProtectManager.get(this);
    }

    private void initView() {
        btn_self_start_permission = findViewById(R.id.btn_self_start_permission);
        btn_power_protection = findViewById(R.id.btn_power_protection);
        btn_self_start_permission.setOnClickListener(this);
        btn_power_protection.setOnClickListener(this);

        layout_btn_protect = findViewById(R.id.layout_btn_protect);

        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.contains("guideDetail.html")) {
                    layout_btn_protect.setVisibility(View.VISIBLE);
                }
            }
        });

        String url = "file:///android_asset/guide/index.html";
        loadUrl(url);
    }

    private void initStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        initToolBar();
    }

    private void initToolBar() {
        View statusBar = findViewById(R.id.statusBarView);
        if (statusBar != null) {
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = ScreenUtil.getStatusBarHeight(this);
            statusBar.setBackgroundColor(getResources().getColor(R.color.white));
        }

        mToolbar = findViewById(R.id.include_toolbar);
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(getResources().getColor(R.color.white));
            include_toolbar_title = mToolbar.findViewById(R.id.include_toolbar_title);
        }
    }

    /**
     * 设置标题，返回icon，默认点击事件
     *
     * @param title
     * @return toolbar
     */
    protected void initTitle(String title, boolean isBackVisible) {
        initTitle(title, isBackVisible, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置标题，返回icon，默认点击事件
     *
     * @param title
     * @return toolbar
     */
    protected void initTitle(String title, boolean isBackVisible, View.OnClickListener clickListener) {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isBackVisible);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((TextView) mToolbar.findViewById(R.id.include_toolbar_title)).setText(title);
//          mToolbar.setNavigationIcon(isBtnVisible ? R.drawable.icon_toolbar_back
//                    : R.drawable.selector_toolbar_btn_transparent);
            mToolbar.setNavigationOnClickListener(isBackVisible ? clickListener : null);
        }
    }


    public void loadUrl(String url) {
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_self_start_permission) {
            if (!mManager.openSelfLaunchSetting(this)) {
                Toast.makeText(this, "请手动设置", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btn_power_protection) {
            if (!mManager.openPowerSetting(this)) {
                Toast.makeText(this, "请手动设置", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
