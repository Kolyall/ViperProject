package com.viperproject.common.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;

import com.viperproject.common.Layout;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;

/**
 * Created by User on 11.04.2017.
 */

public abstract class BaseLinearLayout extends LinearLayoutCompat {

    public BaseLinearLayout(Context context) {
        super(context);
        setupLayout();
        onViewCreated();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupLayout();
        onViewCreated();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupLayout();
        onViewCreated();
    }

    private void setupLayout() {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
    }

    protected void setContentView(int viewLayout) {
        inflate(getContext(),viewLayout,this);
        if(!isInEditMode()) {
            ButterKnife.bind(this);
        }
    }

    protected void onViewCreated(){

    }
}
