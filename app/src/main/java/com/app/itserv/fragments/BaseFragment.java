package com.app.itserv.fragments;

import com.app.commons.ToUIEvent;
import com.yycloud.app.utils.MyVolley;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected View mView;
    private Unbinder unbinder;

    protected abstract int layoutViewId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(layoutViewId(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        MyVolley.getRequestQueue().cancelAll(TAG);
    }

    public View findViewById(int id) {
        return mView.findViewById(id);
    }

    public abstract void onUpdateUI();

    public void onEvent(ToUIEvent toUIEvent) {

    }
}
