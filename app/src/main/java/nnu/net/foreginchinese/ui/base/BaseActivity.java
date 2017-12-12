package nnu.net.foreginchinese.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import nnu.net.foreginchinese.R;
import nnu.net.foreginchinese.common.AppManager;
import nnu.net.foreginchinese.listener.ActivityInit;


/**
 * Created by jsq on 2017/8/28.
 */
public abstract class BaseActivity extends SwipeBackActivity implements ActivityInit {

    protected Context mContext;
    public static final String TAG = BaseActivity.class.getSimpleName();
    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //设置窗口无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mContext = this;
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        AppManager.getInstance().addActivity(this);
    }

    protected void disableSwipeback() {
        mSwipeBackLayout.setEdgeTrackingEnabled(0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + this.getClass().getSimpleName());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (this instanceof ActivityInit) {
            ActivityInit iInit = this;
            iInit.onInitViews();
            iInit.onInitListeners();
            iInit.onInitData();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + this.getClass().getSimpleName());
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    protected void openActivity(Class<?> mClass) {
        Log.d(TAG, "openActivity: open " + mClass.getSimpleName());
        openActivity(mClass, null);
    }

    protected void openActivity(Class<?> mClass, Bundle bundle) {
        Intent intent = new Intent(this, mClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        Log.d(TAG, "openActivity with bundle: open " + mClass.getSimpleName());
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected void openActivityWithoutAnim(Class<?> mClass) {
        Log.d(TAG, "openActivityWithoutAnim: " + mClass.getSimpleName());
        openActivityWithoutAnim(mClass, null);
    }

    protected void openActivityWithoutAnim(Class<?> mClass, Bundle bundle) {

        Intent intent = new Intent(this, mClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        Log.d(TAG, "openActivityWithoutAnim with bundle: " + mClass.getSimpleName());
        startActivity(intent);
    }

    protected void openActivity(String action) {
        openActivity(action, null);
    }

    protected void openActivity(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        Log.d(TAG, "openActivity by action: action----" + action);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        Log.d(TAG, "finish: " + this.getClass().getSimpleName());
    }
    /**
     * 打印调试级别日志
     *
     * @param format
     * @param args
     */
    protected void logDebug(String format, Object... args) {
        logMessage(Log.DEBUG, format, args);
    }

    /**
     * 打印信息级别日志
     *
     * @param format
     * @param args
     */
    protected void logInfo(String format, Object... args) {
        logMessage(Log.INFO, format, args);
    }

    /**
     * 打印错误级别日志
     *
     * @param format
     * @param args
     */
    protected void logError(String format, Object... args) {
        logMessage(Log.ERROR, format, args);
    }

    /**
     * 展示短时Toast
     *
     * @param format
     * @param args
     */
    protected void showShortToast(String format, Object... args) {
        showToast(Toast.LENGTH_SHORT, format, args);
    }

    /**
     * 展示长时Toast
     *
     * @param format
     * @param args
     */
    protected void showLongToast(String format, Object... args) {
        showToast(Toast.LENGTH_LONG, format, args);
    }

    /**
     * 打印日志
     *
     * @param level
     * @param format
     * @param args
     */
    private void logMessage(int level, String format, Object... args) {
        String formattedString = String.format(format, args);
        switch (level) {
            case Log.DEBUG:
                Log.d(TAG, formattedString);
                break;
            case Log.INFO:
                Log.i(TAG, formattedString);
                break;
            case Log.ERROR:
                Log.e(TAG, formattedString);
                break;
        }
    }

    /**
     * 展示Toast
     *
     * @param duration
     * @param format
     * @param args
     */
    private void showToast(int duration, String format, Object... args) {
        Toast.makeText(mContext, String.format(format, args), duration).show();
    }
}
