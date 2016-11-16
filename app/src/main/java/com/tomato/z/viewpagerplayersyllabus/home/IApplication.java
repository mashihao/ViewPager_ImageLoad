package com.tomato.z.viewpagerplayersyllabus.home;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * PROJECT_NAME: ViewPagerPlayerSyllabus
 * PACKAGE_NAME: com.tomato.z.viewpagerplayersyllabus.home
 * FUNCTIONAL_DESCRIPTION:
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME:
 */

public class IApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    public void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/ZZZ/imagecache/");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
//				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
//				.diskCacheSize(50 * 1024 * 1024) // 50 Mb
                //.diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//				.diskCacheFileCount(100)
//				.memoryCacheSize(2 * 1024 * 1024)
//				.memoryCacheSizePercentage(13) // default
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs() // Remove for release app
                .build();

        ImageLoader.getInstance().init(config);
    }
}
