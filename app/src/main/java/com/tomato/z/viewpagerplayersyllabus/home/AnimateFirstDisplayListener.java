package com.tomato.z.viewpagerplayersyllabus.home;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

    private ProgressBar progressBar;
    private boolean isFirstDisplay = true;

    public AnimateFirstDisplayListener(ProgressBar progressBar, boolean isFristDisplay) {
        this.progressBar = progressBar;
        this.isFirstDisplay = isFristDisplay;
    }

    public AnimateFirstDisplayListener(boolean isFristDisplay) {
        this.isFirstDisplay = isFristDisplay;
    }

    public AnimateFirstDisplayListener() {

    }

    @Override
    public void onLoadingStarted(String imageUri, View view) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        String message = null;
        switch (failReason.getType()) {
            case IO_ERROR:
//				message = "Input/Output error";
                message = "读取异常";
                break;
            case DECODING_ERROR:
//				message = "Image can't be decoded";
                message = "无法解析图片";
                break;
            case NETWORK_DENIED:
//				message = "Downloads are denied";
                message = "拒绝访问";
                break;
            case OUT_OF_MEMORY:
//				message = "Out Of Memory error";
                message = "内存错误";
                break;
            case UNKNOWN:
//				message = "Unknown error";
                message = "未知错误";
                break;
        }
        Log.i("ZZZ_EXCEPTION", message);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        if (loadedImage != null) {
            if (isFirstDisplay) {
                FadeInBitmapDisplayer.animate((ImageView) view, 500);
                isFirstDisplay = false;
            }
        }
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        Log.i("ZZZ_EXCEPTION", "加载图片成功");
    }


}
