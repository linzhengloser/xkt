package com.jcfy.xkt.utils.selection;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linzheng
 */
public class SelectionAdapter implements ViewPager.OnPageChangeListener {

    private Map<Class, SelectionBinder> mBinderMap;

    private Boolean mIsSingleSelection = false;

    private ViewGroup mBindLayout;

    private int mSelectionIndex = -1;

    private OnSelectionListener mListener;

    public SelectionAdapter() {
        mBinderMap = new HashMap();
    }

    public <T extends View> void register(Class<T> clazz, SelectionBinder<T> binder) {
        if (!mBinderMap.containsKey(clazz)) mBinderMap.put(clazz, binder);
    }

    public void bindLayout(ViewGroup layout, int defaultSectionIndex) {
        mBindLayout = layout;
        View childView;
        for (int i = 0; i < mBindLayout.getChildCount(); i++) {
            childView = mBindLayout.getChildAt(i);
            final int finalIndex = i;
            final View finalChildView = childView;
            if (i == defaultSectionIndex) {
                if (mIsSingleSelection) mSelectionIndex = i;
                setChildViewOrChildViewGroup(i, childView);
            }
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsSingleSelection && finalIndex == mSelectionIndex) return;
                    setSingleChild(finalIndex);
                    setChildViewOrChildViewGroup(finalIndex, finalChildView);
                }
            });
        }
    }

    public void bindViewPager(ViewPager viewPager, ViewGroup bindLayout) {
        viewPager.addOnPageChangeListener(this);
        bindLayout(bindLayout, 0);
    }

    private void setChildViewOrChildViewGroup(int index, View view) {
        if (view instanceof ViewGroup) {
            setChildLayout(index, (ViewGroup) view);
        } else {
            setChild(index, view);
        }
    }

    private void setChild(int index, View view) {
        view.setTag(getViewTag(view).equals("selection") ? "" : "selection");
        boolean toggle = view.getTag().equals("selection");
        SelectionBinder selectionBinder = mBinderMap.get(view.getClass());
        if (selectionBinder == null)
            throw new RuntimeException(view.getClass().toString() + " not register");
        selectionBinder.toggleView(index, toggle, view);
        if (mListener != null)
            mListener.onSelection(index, toggle);
    }

    private void setChildLayout(int index, ViewGroup child) {
        View view;
        for (int i = 0; i < child.getChildCount(); i++) {
            view = child.getChildAt(i);
            setChild(index, view);
        }
    }

    private void setSingleChild(int index) {
        if (mIsSingleSelection && mSelectionIndex != -1) {
            setChildViewOrChildViewGroup(mSelectionIndex, mBindLayout.getChildAt(mSelectionIndex));
            mSelectionIndex = index;
        }
    }

    public void setSelection(int index) {
        setChildViewOrChildViewGroup(index, mBindLayout.getChildAt(index));
    }

    private String getViewTag(View view) {
        return view.getTag() == null ? "" : view.getTag().toString();
    }

    public SelectionAdapter singleSelection() {
        this.mIsSingleSelection = !mIsSingleSelection;
        return this;
    }

    public SelectionAdapter setListener(OnSelectionListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % mBindLayout.getChildCount();
        setSingleChild(newPosition);
        setSelection(newPosition);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static interface OnSelectionListener {
        void onSelection(int index, boolean toggle);
    }

}
