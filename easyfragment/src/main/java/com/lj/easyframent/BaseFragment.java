package com.lj.easyframent;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lj.easyframent.widget.ProgressDialog;
import com.lj.util.RxBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public abstract class BaseFragment<T extends BaseFragment<T>> extends Fragment implements Consumer<RxBus.Event>, Predicate<RxBus.Event> {
    private AlertDialog mAlertDialog;
    protected ProgressDialog mProgressDialog;
    private String title;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this.getActivity());
        Disposable ds = RxBus.get().toFlowable(RxBus.Event.class).filter(this).observeOn(AndroidSchedulers.mainThread()).subscribe(this);
        compositeDisposable.add(ds);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = onCreateView(inflater, container);
        unbinder = ButterKnife.bind(this, v);
        return v;

    }


    protected abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container);

    @Override
    public void accept(RxBus.Event event) {
        rxReceiver(event);
    }

    @Override
    public boolean test(RxBus.Event event) {
        return rxFilter(event);
    }

    protected abstract boolean rxFilter(RxBus.Event event);

    protected abstract boolean rxReceiver(RxBus.Event event);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) compositeDisposable.clear();
        if (unbinder != null) unbinder.unbind();
        mProgressDialog.dismiss();
    }
}
