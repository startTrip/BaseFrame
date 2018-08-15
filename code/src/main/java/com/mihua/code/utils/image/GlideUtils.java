package com.mihua.code.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Created by liqiang on 2017/1/12.
 * 图片加载工具类
 */

public class GlideUtils {

  /*  public static void loadImage(Context context, String url, int erroTag, int emptyImage, ImageView iv) {
        Glide.with(context).load(url).placeholder(erroTag).error(emptyImage).into(iv);
    }*/

    //加载普通的图片
    public static void loadImage(Context context, String url, ImageView iv, int lodingImage, int errorImageView) {
        //默认添加占位符，根据自己需求可以跟换 (placeholder,error)
        Glide.with(context).load(url).crossFade().placeholder(lodingImage).error(errorImageView).into(iv);
    }

    //加载GIF图片
    public static void loadGifImage(Context context, String url, ImageView iv, int lodingImage, int errorImageView) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(lodingImage).error(errorImageView).into(iv);
    }

    //加载园型图片
    public static void loadCircleImage(Context context, String url, ImageView iv, int lodingImage, int errorImageView) {
        Glide.with(context).load(url).placeholder(lodingImage).error(errorImageView).transform(new GlideCircleTransform(context)).into(iv);
    }

    //加载圆角图片
    public static void loadRoundCornerImage(Context context, String url, ImageView iv, int lodingImage, int errorImageView) {
        Glide.with(context).load(url).placeholder(lodingImage).error(errorImageView).transform(new GlideRoundTransform(context, 10)).into(iv);
    }

    //加载手机本地文件里面的图片
    public static void loadImageLocal(Context context, final File FilePath, final ImageView imageView) {
        Glide.with(context).load(FilePath).into(imageView);
    }

    //加载项目图片
    public static void loadImageProject(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context).load(resourceId).into(imageView);
    }

    //加载指定大小
    public static void loadImageSize(Context context, String url, int width, int height, ImageView iv) {
        Glide.with(context).load(url).override(width, height).into(iv);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageLodingSize(Context context, String url, ImageView iv, int lodingImage, int errorImageView) {
        Glide.with(context).load(url).placeholder(lodingImage).error(errorImageView).into(iv);
    }

}
