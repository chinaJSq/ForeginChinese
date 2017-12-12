package nnu.net.foreginchinese.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import nnu.net.foreginchinese.R;
import nnu.net.foreginchinese.common.AppManager;
import nnu.net.foreginchinese.ui.base.BaseActivity;
import nnu.net.foreginchinese.ui.fragment.AroundFragment;
import nnu.net.foreginchinese.ui.fragment.HomeFragment;
import nnu.net.foreginchinese.ui.fragment.MeFragment;
import nnu.net.foreginchinese.ui.fragment.MoreFragment;
import nnu.net.foreginchinese.ui.fragment.SearchFragment;

public class MainActivity extends BaseActivity{
    private FragmentTabHost tabhost;
    private  ActionBar actionBar;
    private Class[] fragments = new Class[]{SearchFragment.class,
            HomeFragment.class,AroundFragment.class,
            MeFragment.class,MoreFragment.class};
    private int[] resTitles = new int[] {
            R.string.tab_title_search,
            R.string.tab_title_home,R.string.tab_title_around,
            R.string.tab_title_me,R.string.tab_title_more};
    private int[] icons = new int[]{
            R.drawable.tab_search_selector,
            R.drawable.tab_home_selector,R.drawable.tab_around_selector,
            R.drawable.tab_me_selector,R.drawable.tab_more_selector};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();

    }

    @Override
    public void onInitViews() {
        tabhost = (FragmentTabHost) findViewById(R.id.main_tabHost);
        tabhost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        for (int i = 0; i < fragments.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_tab,null);
            ImageView tabIcon = (ImageView) view.findViewById(R.id.item_tab_iv);
            TextView tabTitle = (TextView) view.findViewById(R.id.item_tab_tv);
            tabIcon.setImageResource(icons[i]);
            tabTitle.setText(resTitles[i]);
            tabhost.addTab(tabhost.newTabSpec(""+i).setIndicator(view),fragments[i],null);
        }
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int id = Integer.parseInt(tabId);
                actionBar.setTitle(resTitles[id]);
            }
        });
    }
    @Override
    public void onInitListeners() {

    }

    @Override
    public void onInitData() {
        disableSwipeback();//取消手势
        //用户是否注册
        Intent intent = getIntent();
        if (intent != null) {
                tabhost.setCurrentTab(2);
        }
    }

    @Override
    public void onClick(View v) {

    }
    private static boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit()
    {
        if (!isExit)
        {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else
        {

            AppManager.finishAllActivity();
            System.exit(0);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            isExit = false;
        }
    };

}
