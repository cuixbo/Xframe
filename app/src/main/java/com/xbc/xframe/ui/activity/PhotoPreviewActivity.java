package com.xbc.xframe.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;
import com.xbc.lib.common.util.BitmapUtil;
import com.xbc.lib.common.util.ButtonStateBuilder;
import com.xbc.lib.common.util.ToastUtil;
import com.xbc.xframe.R;
import com.xbc.xframe.app.base.BaseActivity;
import com.xbc.xframe.ui.dialog.BottomDialog;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片预览
 * Created by xiaobo.cui on 2017/1/20.
 */

public class PhotoPreviewActivity extends BaseActivity {

    String[] urls;
    String[] paths;
    int[] imgIds;
    int mIndex;
    PhotoPreviewPagerAdapter mAdapter;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.view_pager_indicator)
    PageIndicatorView mViewPagerIndicator;
    @BindView(R.id.rl_tool)
    RelativeLayout mRlTool;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.tv_share)
    TextView mTvShare;
    @BindView(R.id.tv_page_count)
    TextView mTvPageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
    }

    @Override
    protected void initIntent() {
        urls = getIntent().getStringArrayExtra("urls");
        paths = getIntent().getStringArrayExtra("paths");
        imgIds = getIntent().getIntArrayExtra("imgIds");
        mIndex = getIntent().getIntExtra("index", 0);
    }

    @Override
    protected void initView() {
        mAdapter = new PhotoPreviewPagerAdapter(urls, paths, imgIds);
        mViewPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mViewPager.setAdapter(mAdapter);

        mViewPagerIndicator.setViewPager(mViewPager);
        mViewPagerIndicator.setAnimationType(AnimationType.SCALE);
        if (mIndex < mAdapter.getCount()) {
            mViewPager.setCurrentItem(mIndex);
        } else {
            mIndex = 0;
        }
        if (mAdapter.getCount() > 9) {
            mViewPagerIndicator.setVisibility(View.INVISIBLE);
            mTvPageCount.setVisibility(View.VISIBLE);
            mTvPageCount.setText((mIndex + 1) + "/" + mAdapter.getCount());
        } else {
            mViewPagerIndicator.setVisibility(View.VISIBLE);
            mTvPageCount.setVisibility(View.INVISIBLE);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mAdapter.getCount() > 9) {
                    mTvPageCount.setText((position + 1) + "/" + mAdapter.getCount());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        new ButtonStateBuilder().setView(mTvSave)
                .setCornerRadius(8)
                .setNormal(Color.parseColor("#8A333333"), 0)
                .setPressed(Color.parseColor("#333333"), 0)
                .build();

        new ButtonStateBuilder().setView(mTvShare)
                .setCornerRadius(8)
                .setNormal(Color.parseColor("#8A333333"), 0)
                .setPressed(Color.parseColor("#333333"), 0)
                .build();


    }

    @Override
    protected void initListener() {
        mRlTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //block photo view onClick
            }
        });

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMore();
            }
        });
    }

    @Override
    protected void initData() {

    }


    public void showMore() {
        BottomDialog dialog = new BottomDialog(mContext);
        dialog.addItem("刷新", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).addItem("用手机浏览器打开", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).addItem("返回首页", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }


    public class PhotoPreviewPagerAdapter extends PagerAdapter {
        String[] urls;
        String[] paths;
        int[] imgIds;
        int mContainerWidth, mContainerHeight;

        public PhotoPreviewPagerAdapter(String[] urls, String[] paths, int[] imgIds) {
            this.urls = urls;
            this.paths = paths;
            this.imgIds = imgIds;
        }

        @Override
        public int getCount() {
            if (urls != null && urls.length > 0) {
                return urls.length;
            }
            if (paths != null && paths.length > 0) {
                return paths.length;
            }
            if (imgIds != null && imgIds.length > 0) {
                return imgIds.length;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(mContext, R.layout.layout_photo_view_container, null);
            try {
                if (mContainerWidth * mContainerHeight == 0) {
                    mContainerWidth = container.getMeasuredWidth();
                    mContainerHeight = container.getMeasuredHeight();
                }
                final PhotoView photoView = (PhotoView) view.findViewById(R.id.photo_view);
                final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pb_loading);
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        finish();
                    }
                });

                photoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                if (urls != null && urls.length > 0) {
                    Glide.with(mContext)
                            .load(urls[position])
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .listener(new RequestListener<String, Bitmap>() {

                                @Override
                                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                    ToastUtil.showToastWarn(mContext, "加载失败,请稍后再试");
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    if (photoView == null || !photoView.isAttachedToWindow()) {
                                        return true;
                                    }
                                    //                                progressBar.setIndeterminate(false);
                                    //                                progressBar.setIndeterminateDrawable(null);
                                    try {
                                        Log.e("xbc", position + " isFromMemoryCache:" + isFromMemoryCache + ",isFirstResource:" + isFirstResource);
                                        progressBar.setVisibility(View.GONE);
                                        if (resource != null) {//这里处理图片超长或超宽的MaxScale设置
                                            int width = resource.getWidth();
                                            int height = resource.getHeight();
                                            if (width * height > 0) {
                                                if (mContainerWidth * mContainerHeight > 0) {
                                                    float scale = 1F * height / width * mContainerWidth / mContainerHeight;
                                                    if (scale > photoView.getMaximumScale()) {
                                                        photoView.setMaximumScale(scale);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return false;
                                }
                            })
                            .into(new ImageViewTarget<Bitmap>(photoView) {

                                @Override
                                protected void setResource(Bitmap resource) {
                                    if (photoView == null || !photoView.isAttachedToWindow()) {
                                        return;
                                    }
                                    try {
                                        //增加对于图片长宽超过4096的处理
                                        Bitmap scaledBitmap = BitmapUtil.getMaxSizedBitmap(resource, 4096);
                                        if (scaledBitmap != null) {
                                            photoView.setImageBitmap(scaledBitmap);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                } else if (paths != null && paths.length > 0) {
                    Bitmap bitmap = BitmapUtil.getSmallBitmap(paths[position], 720, 1280);
                    photoView.setScaleType(ImageView.ScaleType.CENTER);
                    photoView.setImageBitmap(bitmap);
                    progressBar.setVisibility(View.GONE);
                } else if (imgIds != null && imgIds.length > 0) {
                    photoView.setImageResource(imgIds[position]);
                    photoView.setScaleType(ImageView.ScaleType.CENTER);
                    progressBar.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
