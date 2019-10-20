package cn.example.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.customview.widget.ViewDragHelper;

/**
 * author : lixiangxiang
 * date : 2019/10/18
 * description : 可在父View中拖动的SurfaceView, 使用方法，代码new CustomSurfaceView实例，调用setParentView()设置父View即可
 */
public class CustomSurfaceView extends SurfaceView {

    public CustomSurfaceView(Context context) {
        super(context);

        setFocusable(true);

        // Set current surfaceview at top of the view tree.
        this.setZOrderOnTop(true);
        this.setBackgroundColor(Color.BLUE);

        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void setParentView(ViewGroup parentView){
        initDragHelper(parentView);
//        parentView.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.i("test", "ontouch event"+event);
//                viewDragHelper.processTouchEvent(event);
//                return true;
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("CustomeSurface", "CustomSurfaceView ontouchevent return true");
        return true;
    }

    public void processTouchEvent(MotionEvent event){
        if(viewDragHelper!=null){
            viewDragHelper.processTouchEvent(event);
        }
    }

    private View dragView = this;
    private ViewDragHelper viewDragHelper;
//    private int mPosLeft = -1, mPosTop = -1;
    private void initDragHelper(final ViewGroup parentView) {
        viewDragHelper = ViewDragHelper.create(
                parentView,
                1.0f,
                new ViewDragHelper.Callback() {
                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
                        boolean couldCapture = child.equals(dragView);
                        if(couldCapture){
                            Log.i("test", "---couldCapture");
                        } else {
                            Log.i("test", "---could not Capture");
                        }
                        return couldCapture;
                    }

                    @Override
                    public int clampViewPositionVertical(View child, int top, int dy) {

                        final int topBound = parentView.getPaddingTop(); //上边界
                        final int bottomBound = parentView.getHeight() - child.getHeight() - parentView.getPaddingBottom(); //下边界

                        return Math.min(Math.max(top, topBound), bottomBound); // 新y位于上下边界之间
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child, int left, int dx) {

                        final int leftBound = parentView.getPaddingLeft(); //左边界
                        final int rightBound = parentView.getWidth() - child.getWidth() - parentView.getPaddingRight(); //右边界

                        return Math.min(Math.max(left, leftBound), rightBound); // 新x位于左右边界之间
                    }

                    //为了防止button的事件拦截
                    @Override
                    public int getViewHorizontalDragRange(View child) {
                        return parentView.getMeasuredWidth() - child.getMeasuredWidth();
                    }

                    //为了防止button的事件拦截
                    @Override
                    public int getViewVerticalDragRange(View child) {
                        return parentView.getMeasuredHeight() - child.getMeasuredHeight();
                    }

                    @Override
                    public void onViewPositionChanged(
                            View changedView, int left, int top, int dx, int dy) {
                        super.onViewPositionChanged(changedView, left, top, dx, dy);
                        if (changedView.equals(dragView)) {
                            // 记录被拖拽的位置
//                            mPosLeft = left;
//                            mPosTop = top;
                            updatePosition(left, top);
                        }
                    }
                });
    }

    private void updatePosition(int left, int top){
        this.setLeft(left);
        this.setTop(top);
    }
}
