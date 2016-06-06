package com.example.share;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private UMShareAPI mShareAPI;
    private SHARE_MEDIA platform = SHARE_MEDIA.QQ;
    private Button shareThreeLoginBtn,shareGetUserInformationBtn,shareShareBtn;
    private TextView shareGetUserInformationNameText;
    private ImageView shareGetUserInformationImage;
    private User user;
    UMShareListener umShareLister = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText( getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            if(action==UMAuthListener.ACTION_GET_PROFILE){
                Log.i("数据+++",data.toString());
                user = new User();
                user.setUserName(data.get("screen_name"));
                user.setHeadPortrait(data.get("profile_image_url"));
                shareGetUserInformationNameText.setText(user.getUserName());
                Log.i("http",user.getHeadPortrait());
                //Glide.with(MainActivity.this).load(user.getHeadPortrait()).into(shareGetUserInformationImage);
                Picasso.with(MainActivity.this)
                        .load(user.getHeadPortrait())//加载地址
                        .placeholder(R.mipmap.ic_launcher)//占位图
                        .error(R.mipmap.ic_launcher)//加载失败的图
                        .fit()//充满
                        .tag(MainActivity.this)//标记
                        .into(shareGetUserInformationImage);//加载到ImageView
            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShareAPI = UMShareAPI.get(this);
        PushAgent mPushAgent = PushAgent.getInstance(MainActivity.this);
        //推送测试
        mPushAgent.enable(new IUmengRegisterCallback() {

            @Override
            public void onRegistered(String registrationId) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        String registrationId = UmengRegistrar.getRegistrationId(MainActivity.this);
                        Log.d("device_token", registrationId);
                    }
                });
            }
        });
        PushAgent.getInstance(MainActivity.this).onAppStart();
        shareGetUserInformationNameText= (TextView) findViewById(R.id.shareGetUserInformationNameText);
        shareGetUserInformationImage = (ImageView) findViewById(R.id.shareGetUserInformationImage);
        shareThreeLoginBtn = (Button) findViewById(R.id.shareThreeLoginBtn);
        shareThreeLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        shareGetUserInformationBtn = (Button) findViewById(R.id.shareGetUserInformationBtn);
        shareGetUserInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareAPI.getPlatformInfo(MainActivity.this, platform, umAuthListener);
            }
        });
        /**
         * 目前支持文本、图片（本地及URL）、音频及视频URL的分享

         图片(url)

         UMImage image = new UMImage(ShareActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");

         图片(本地资源引用)

         UMImage image = new UMImage(ShareActivity.this,
         BitmapFactory.decodeResource(getResources(), R.drawable.image));

         图片(本地绝对路径)

         UMImage image = new UMImage(ShareActivity.this,
         BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

         URL音频及图片

         UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
         music.setTitle("sdasdasd");
         music.setThumb(new UMImage(ShareActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));

         url视频

         UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
         */
        shareShareBtn = (Button) findViewById(R.id.shareShareBtn);
        shareShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                        {
                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA,
                                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.DOUBAN
                        };
                UMImage image = new UMImage(MainActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
                new ShareAction(MainActivity.this).setDisplayList( displaylist )
                        .withText( "呵呵" )
                        .withTitle("title")
                        .withTargetUrl("http://www.baidu.com")
                        .withMedia(image)
                        .setListenerList(umShareLister)
                        .open();
            }
        });
    }

    private void Login() {
        mShareAPI.doOauthVerify(this, platform, umAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        //Log.i("数据+++",data.toString());
    }

}
