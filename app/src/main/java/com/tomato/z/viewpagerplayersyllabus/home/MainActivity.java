package com.tomato.z.viewpagerplayersyllabus.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tomato.z.viewpagerplayersyllabus.R;
import com.tomato.z.viewpagerplayersyllabus.viewpage.AutoScrollViewPager;
import com.tomato.z.viewpagerplayersyllabus.viewpage.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.auto_scroll_viewpager)
    AutoScrollViewPager autoScrollViewpager;

    ImagePagerAdapter imagePagerAdapter;
    List<String> imageList;

    DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        imageList = new ArrayList<>();
        imageList.add("https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/transition_explode.gif");
        imageList.add("https://raw.githubusercontent.com/lgvalle/Material-Animations/master/screenshots/transition_explode.gif");
        imageList.add("http://img2.3lian.com/2014/f4/100/d/17.jpg");
        imageList.add("http://pic36.nipic.com/20131209/13954356_200619566183_2.jpg");

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(0))
                .build();


        imagePagerAdapter = new ImagePagerAdapter(this, imageList, options).setInfiniteLoop(true);

        autoScrollViewpager.setInterval(6000);
        autoScrollViewpager.setScrollDurationFactor(3.00);
        autoScrollViewpager.startAutoScroll();
        autoScrollViewpager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageList.size());

        autoScrollViewpager.setAdapter(imagePagerAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (autoScrollViewpager != null) {
            autoScrollViewpager.stopAutoScroll();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (autoScrollViewpager != null) {
            autoScrollViewpager.startAutoScroll();
        }
    }


}
