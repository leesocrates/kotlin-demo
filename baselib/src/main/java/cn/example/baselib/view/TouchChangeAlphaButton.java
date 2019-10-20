package cn.example.baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

/**
 * 触摸状态时改变透明度，产生点击反馈的效果
 * Created by lee on 2016/8/5.
 */
public class TouchChangeAlphaButton extends AppCompatButton {

    /**
     * 触摸状态下的alpha值
     */
    private float touchedAlpha = 0.6f;

    public TouchChangeAlphaButton(Context context) {
        super(context);
        init();
    }

    public TouchChangeAlphaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchChangeAlphaButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setTouchedAlpha(float alpha){
        touchedAlpha = alpha;
    }

    private void init() {
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    setAlpha(touchedAlpha);
                } else {
                    setAlpha(1.0f);
                }
                return false;
            }
        });
    }

}
