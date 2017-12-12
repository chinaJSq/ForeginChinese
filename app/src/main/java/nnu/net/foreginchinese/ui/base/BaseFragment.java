package nnu.net.foreginchinese.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nnu.net.foreginchinese.listener.ActivityInit;


/**
 * Created by jsq on 2017/8/27.
 */
public abstract class BaseFragment extends Fragment implements ActivityInit {
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        int id = getLayoutId();
        mView = inflater.inflate(id, null);
        return mView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this instanceof ActivityInit) {
            ActivityInit iInit = this;
            iInit.onInitViews();
            iInit.onInitData();
            iInit.onInitListeners();
        }
    }

    protected View findViewById(int id) {
        return null == mView ? null : mView.findViewById(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();

    }
}
