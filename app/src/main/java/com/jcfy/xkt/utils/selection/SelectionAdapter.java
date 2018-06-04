package com.jcfy.xkt.utils.selection;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
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

    public void bindLayout(ViewGroup layout) {
        bindLayout(layout, -1);
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
                    if (mListener != null)
                        mListener.onSelection(finalIndex, isChildViewSelection(finalChildView));
                }
            });
        }
    }

    private boolean isChildViewSelection(View childView) {
        return getViewTag(childView).equals("selection");
    }

    private void toggleChildViewTag(View childView) {
        childView.setTag(isChildViewSelection(childView) ? "" : "selection");
    }

    public void bindViewPager(ViewPager viewPager, ViewGroup bindLayout) {
        viewPager.addOnPageChangeListener(this);
        bindLayout(bindLayout, 0);
    }

    private void setChildViewOrChildViewGroup(int index, View view) {
        toggleChildViewTag(view);
        if (view instanceof ViewGroup) {
            setChildLayout(index, (ViewGroup) view);
        } else {
            setChild(index, 0, view, isChildViewSelection(view));
        }
    }

    private void setChild(int parentIndex, int childIndex, View view, boolean toggle) {
        SelectionBinder selectionBinder = mBinderMap.get(view.getClass());
        if (selectionBinder == null)
            throw new RuntimeException(view.getClass().toString() + " not register");
        if (!isNeedSelection(childIndex, selectionBinder)) return;
        selectionBinder.toggleView(parentIndex, toggle, view);
    }

    private void setChildLayout(int index, ViewGroup childLayout) {
        View view;
        for (int i = 0; i < childLayout.getChildCount(); i++) {
            view = childLayout.getChildAt(i);
            setChild(index, i, view, isChildViewSelection(childLayout));
        }
    }

    /**
     * 判断是否需要设置绑定 SelectionBinder
     */
    private boolean isNeedSelection(int index, SelectionBinder binder) {
        if (!binder.needSelectionChildIndex().isEmpty()) {
            List<Integer> needBinderIndex = binder.needSelectionChildIndex();
            return needBinderIndex.contains(index);
        }
        return true;
    }

    private void setSingleChild(int index) {
        if (mIsSingleSelection) {
            if (mSelectionIndex != -1) {
                setChildViewOrChildViewGroup(mSelectionIndex, mBindLayout.getChildAt(mSelectionIndex));
            }
            mSelectionIndex = index;
        }
    }

    public void setSelection(int index) {
//        if (mIsSingleSelection && mSelectionIndex == index) return;
        if (mIsSingleSelection) mSelectionIndex = index;
        setChildViewOrChildViewGroup(index, mBindLayout.getChildAt(index));
        mBindLayout.getChildAt(index).setTag("selection");
    }

    public int getSelectionIndex() {
        return mSelectionIndex;
    }

    private String getViewTag(View view) {
        return view.getTag() == null ? "" : view.getTag().toString();
    }

    public SelectionAdapter singleSelection() {
        this.mIsSingleSelection = true;
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
        mListener.onSelection(position, isChildViewSelection(mBindLayout.getChildAt(position)));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnSelectionListener {
        void onSelection(int index, boolean toggle);
    }

}
