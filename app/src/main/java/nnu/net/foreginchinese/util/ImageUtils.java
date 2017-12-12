package nnu.net.foreginchinese.util;

import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import nnu.net.foreginchinese.R;


/**
 * Created by jsq on 17/12/12上午9:31.
 * <p/>
 */
public class ImageUtils {

    private ImageUtils() {

    }

    public static ImageUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void display(ImageView imageView, String url) {

        display(imageView, url, R.mipmap.ic_load
                , R.mipmap.ic_error);
    }

    /**
     * 这里不得不强调下Glide的一个强大的功能，当你在With后面的传Activity或者Fragment，
     * Glide就可以根据当前Activity或者Fragment的生命周期维护图片的生命周期，
     * 比如但activity销毁的时候，就会自动取消需要加载的图片
     *
     * @param imageView
     * @param url
     * @param loadingImg
     * @param errorImg
     */
    public void display(ImageView imageView, String url, int loadingImg, int errorImg) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        DrawableTypeRequest request = Glide.with(imageView.getContext()).load(url);
        if (0 != loadingImg) {
            request.placeholder(loadingImg);
        }
        if (0 != errorImg) {
            request.error(errorImg);
        }
        request
                //用原图的1/10作为缩略图，如果缩略图先被加载出来则先显示缩略图
                .thumbnail(0.1f)
                .fitCenter()
                .crossFade()
                //解决加载出来的瞬间闪一下的问题
                .dontAnimate()
                .into(imageView);
    }

    private static final class LazyHolder {
        private static final ImageUtils INSTANCE = new ImageUtils();
    }
}
