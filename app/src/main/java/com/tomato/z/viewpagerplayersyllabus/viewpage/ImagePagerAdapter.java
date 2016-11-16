package com.tomato.z.viewpagerplayersyllabus.viewpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tomato.z.viewpagerplayersyllabus.R;
import com.tomato.z.viewpagerplayersyllabus.home.AnimateFirstDisplayListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<String> imageList;
    private int size;
    private boolean isInfiniteLoop;
    private LayoutInflater inflater;
    private DisplayImageOptions options;

    public ImagePagerAdapter(Context context, List<String> imageList, DisplayImageOptions options) {
        this.context = context;
        this.imageList = imageList;
        this.size = imageList.size();
        this.inflater = LayoutInflater.from(context);
        this.options = options;
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        return isInfiniteLoop ? Integer.MAX_VALUE : size;
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder viewHolder = null;
        boolean isFristDisplay;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_scroll_imageview_pager, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            isFristDisplay = true;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            isFristDisplay = false;
        }


    ImageLoader.getInstance().displayImage(imageList.get(getPosition(position)), viewHolder.scrollPagerImageview, options, new AnimateFirstDisplayListener(isFristDisplay));

    return convertView;
    }


    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    static class ViewHolder {
        @BindView(R.id.scroll_pager_imageview)
        ImageView scrollPagerImageview;
        @BindView(R.id.fragment_index_progressbar)
        ProgressBar fragmentIndexProgressbar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}