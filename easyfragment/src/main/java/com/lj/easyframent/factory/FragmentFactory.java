package com.lj.easyframent.factory;

import android.os.Bundle;
import android.util.SparseArray;

import com.lj.easyframent.BaseFragment;

public class FragmentFactory implements IFragmentFactory {
    public static final FragmentFactory self = new FragmentFactory();

    protected static SparseArray<BaseFragment> cache = new SparseArray<>();

    protected FragmentFactory() {
    }

    public BaseFragment getFragment(int key, boolean useCache, Bundle data) {
        BaseFragment bf = null;

        if (useCache) {//使用缓存
            bf = cache.get(key);
        }

//        if (bf != null) {
//            return bf;
//        }
//
//        switch (key) {
//
////            //议题
////            case FragmentKey.KEY_MEETING_TOPIC_FRAGMENT:
////                bf = new TopicFragment();
////                break;
////            //正在签到
////            case FragmentKey.KEY_SIGN_SIGNING:
////                bf = new SignFragment();
////                break;
////            //已经签到
////            case FragmentKey.KEY_SIGN_SIGNED:
////                bf = new SignedFragment();
////                break;
////            //呼叫服务
////            case FragmentKey.KEY_CALL:
////                bf = new CallFragment();
////                break;
////            //发言
////            case FragmentKey.KEY_SPEAK:
////                bf = new SpeakFragment();
////                break;
//        }
//        if (bf != null) {
//            cache.put(key, bf);
//            if (data != null) bf.setArguments(data);
//        }
        return bf;
    }
}
