package cn.example.baselib.util;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

public class ViewExtend {

    public static void addTouchChangeAlphaListener(View view){
        if(view == null){
            return;
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(v!=null && v.isEnabled()){
                    if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                        v.setAlpha(0.6f);
                    } else {
                        v.setAlpha(1.0f);
                    }
                }
                return false;
            }
        });
    }

    public static void expandViewTouchDelegate(final View view, final int left, final int top, final int right, final int bottom){
        if(view == null || view.getParent() == null || view.getParent() instanceof View){
            return;
        }
        ((View)view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);
                bounds.left -= left;
                bounds.top -= top;
                bounds.right += right;
                bounds.bottom += bottom;
                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                ((View)view.getParent()).setTouchDelegate(touchDelegate);
            }
        });
    }
}
