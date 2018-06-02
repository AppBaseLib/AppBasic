package com.abt.common.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.logging.Logger;

/**
 * @描述： @自定义绑定属性
 * @作者： @黄卫旗
 * @创建时间： @21/05/2018
 */
public class ImageViewDataBinding {

    @BindingAdapter("ir")
    public static final void setImageResource(ImageView imageView, int ir) {
        imageView.setImageResource(ir);
    }

    @BindingAdapter("br")
    public static final void setBackgroundResource(ImageView imageView, int br) {
        imageView.setBackgroundResource(br);
    }

    @BindingAdapter("bitmap")
    public static final void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter("filepath")
    public static final void setImageLocal(ImageView imageView, String filepath) {
        // TODO ImageLoader.into(imageView).loadImage(filepath);
    }

}
