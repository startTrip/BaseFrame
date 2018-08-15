package com.mihua.frameproject.sharesdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mihua.frameproject.R;
import com.mihua.thirdplatform.sharesdk.ShareData;
import com.mihua.thirdplatform.sharesdk.ShareManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.qq_login)
    Button mQqLogin;
    @BindView(R.id.weixin_login)
    Button mWeixinLogin;
    @BindView(R.id.weibo_login)
    Button mWeiboLogin;
    @BindView(R.id.qq_share)
    Button mQqShare;
    @BindView(R.id.weixin_share)
    Button mWeixinShare;
    @BindView(R.id.weibo_share)
    Button mWeiboShare;
    private ShareManager shareManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        shareManager = ShareManager.getInstance();
    }


    @OnClick({R.id.qq_login, R.id.weixin_login, R.id.weibo_login,R.id.qq_share, R.id.weixin_share, R.id.weibo_share,R.id.oneKey_share})
    public void onClick(View view) {
        Log.d("thirdPlatform", "login");

        switch (view.getId()) {
            case R.id.qq_login:
                showQQLogin();

                break;
            case R.id.weixin_login:

                break;
            case R.id.weibo_login:

                break;
            case R.id.qq_share:
                showQQShare();
                break;
            case R.id.weixin_share:

                break;
            case R.id.weibo_share:

                break;
            case R.id.oneKey_share:
                showShare();
                break;
        }
    }

    // QQ分享
    private void showQQShare() {
        ShareData shareData = new ShareData();
        shareData.mPlatformType = ShareManager.PlatformType.QQ;
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setText("我是分享文本");
        shareParams.setTitleUrl("https://www.baidu.com/");
        shareData.mShareParams = shareParams;
        shareManager.shareData(shareData, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e("thirdPlatform", platform.getName() + "arg2:" + hashMap.toString());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("thirdPlatform", throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("thirdPlatform", "cancel");
            }
        });
    }

    // QQ登录
    private void showQQLogin() {
        shareManager.loginEntry(ShareManager.PlatformType.QQ, new PlatformActionListener() {
            @Override
            public void onCancel(Platform arg0, int arg1) {
                Log.e("thirdPlatform", "cancel");
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                Log.e("thirdPlatform", arg0.getName() + "arg2:" + arg2.toString());
            }

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                Log.e("thirdPlatform", arg2.getMessage());
            }
        });
    }

    // 一键分享
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
