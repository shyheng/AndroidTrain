package com.hhh.dingdingtask.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hhh.dingdingtask.R;
import com.hhh.dingdingtask.entity.Item;

public class Test extends LinearLayout {

    private int touchSlop;
    private float downX;
    private float downY;
    private boolean touchMode;
    private boolean slide;
    private int lastScrollX;

    private TextView name;
    private TextView url;
    private Button button;

    public Test(Context context) {
        super(context,null);
    }

    public Test(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Test(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.item, this);
        name = findViewById(R.id.t1);
        url = findViewById(R.id.t2);
        button = findViewById(R.id.b1);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下的位置
                downX = event.getRawX();
                downY = event.getRawY();
                lastScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float nowX = event.getRawX();
                float nowY = event.getRawY();

                //判断用户是上下滑动还是左右滑动
                if (!touchMode && (Math.abs(nowX - downX) > touchSlop || Math.abs(nowY - downY) > touchSlop)) {
                    touchMode = true;   //一旦该变量被置为true，则滑动方向确定
                    if (Math.abs(nowX - downX) > touchSlop && Math.abs(nowY - downY) <= touchSlop) {
                        slide = true;   //此时认为是左右滑动
                        getParent().requestDisallowInterceptTouchEvent(true);   //请求父控件不要拦截触摸事件

                        //以下代码避免出发点击事件
                        MotionEvent cancelEvent = MotionEvent.obtain(event);
                        cancelEvent.setAction(MotionEvent.ACTION_CANCEL | (event.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                        onTouchEvent(cancelEvent);
                    }
                }

                if (slide) {
                    float diffX = downX - nowX + lastScrollX;
                    if (diffX < 0)  //设置阻尼
                        diffX /= 3;
                    else if (diffX > button.getWidth())
                        diffX = (diffX - button.getWidth()) / 3 + button.getWidth();

                    scrollTo((int) diffX, 0);   //滑动到手指位置
                }

                break;
            case MotionEvent.ACTION_UP:
                if (slide) {    //如果是左右滑动，那么松手时需要自动滑到指定位置
                    ValueAnimator animator;     //使用的是ValueAnimator，而非Scroller
                    if (getScrollX() > button.getWidth() / 2) {
                        animator = ValueAnimator.ofInt(getScrollX(), button.getWidth());
                    } else {
                        animator = ValueAnimator.ofInt(getScrollX(), 0);
                    }
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            scrollTo((Integer) animation.getAnimatedValue(), 0);
                        }
                    });
                    animator.start();
                    slide = false;
                }
                touchMode = false;  //重置变量
                break;
        }

        return super.onTouchEvent(event);
    }

    public void setChannel(final Item item) {
        scrollTo(0, 0);
        name.setText(item.getTime());
        url.setText(item.getWorktype());
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
