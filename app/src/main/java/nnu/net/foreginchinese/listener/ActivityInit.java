package nnu.net.foreginchinese.listener;

import android.view.View;

/**
 * Created by Administrator on 2017/11/12.
 */

public interface ActivityInit extends View.OnClickListener {
    void onInitViews();
    void onInitListeners();
    void onInitData();
}
