package com.xiaomomo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xiaomomo.R;

/**
 * Created by lihuanxing on 2017/7/2.
 * 封装并且初始化UniverImageLoader
 */

public class ImageLoaderUtil {

    private static final int THREAD_COUNT = 2;
    private static final int PRIORITY = 2;
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private static final int CONNECT_TIMEOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 30 * 1000;


    private static ImageLoader mImageLoader = null;
    private static ImageLoaderUtil imageLoaderUtil = null;

    public static ImageLoaderUtil getInstance(Context context){
        if (imageLoaderUtil == null){
            synchronized (ImageLoaderUtil.class){
                if (imageLoaderUtil == null){
                    imageLoaderUtil = new ImageLoaderUtil(context);
                }
            }
        }
        return imageLoaderUtil;
    }

    private ImageLoaderUtil(Context context){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT) //设置线程的数量
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY) //设置图片下载线程的优先级
                .denyCacheImageMultipleSizesInMemory() //防止缓存多套尺寸的图片
                .memoryCache(new WeakMemoryCache()) //使用弱引用进行内存缓存
                .diskCacheSize(DISK_CACHE_SIZE) //磁盘缓存的大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //文件名用MD5命名
                .tasksProcessingOrder(QueueProcessingType.LIFO) //设置图片下载的顺序
                .defaultDisplayImageOptions(getDefaultOptions()) //设置默认的options
                .imageDownloader(new BaseImageDownloader(context,CONNECT_TIMEOUT
                        ,READ_TIMEOUT)) //设置图片下载器
                .writeDebugLogs() //debug模式下输出日志
                .build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    public DisplayImageOptions getDefaultOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error) //当下载图片url为空的时候显示的图片
                .showImageOnFail(R.drawable.xadsdk_img_error) //当图片下载失败的时候显示的图片
                .cacheInMemory(true) //图片可以缓存到内存
                .cacheOnDisk(true)  //图片可以缓存到磁盘
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) //设置图片缩放类型
                .bitmapConfig(Bitmap.Config.RGB_565) //图片解码类型
                .decodingOptions(new BitmapFactory.Options()) //图片解码配置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();
        return  options;
    }

    /**
     * 加载图片
     * @param url
     * @param imageView
     * @param options
     * @param listener
     */
    public void display(String url, ImageView imageView,
                        DisplayImageOptions options,
                        ImageLoadingListener listener){
        if (mImageLoader != null){
            mImageLoader.displayImage(url,imageView,options,listener);
        }
    }

    public void display(String url, ImageView imageView, ImageLoadingListener listener){
        display(url,imageView,null,listener);
    }

    public void display(String url, ImageView imageView){
        display(url,imageView,null,null);
    }
}
