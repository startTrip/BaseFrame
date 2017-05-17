package com.mihua.frameproject;

import android.app.Application;

import com.mihua.thirdplatform.sharesdk.ShareManager;
import com.mihua.thirdplatform.zxing.activity.ZXingLibrary;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/04/28
 *     desc   :
 * </pre>
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareManager.initSDK(this);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
