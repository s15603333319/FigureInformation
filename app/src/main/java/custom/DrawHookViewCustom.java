package custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 张同心 on 2017/9/14.
 * @function 自定义画对勾效果
 */

public class DrawHookViewCustom extends View {

    private int progress = 0;//绘制圆弧的进度值
    private int linel_x = 0;//线 1 的x轴
    private int linel_y = 0;//线 1 的y周
    private int line2_x = 0;//线 2 的x轴
    private int line2_y = 0;//线 2 的y轴
    private TextView mTvClearSuccess;

    public DrawHookViewCustom(Context context) {
        super(context);
    }

    public DrawHookViewCustom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawHookViewCustom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        progress++;
        /*绘制圆弧*/
        Paint paint = new Paint();
        paint.setAntiAlias(true);//消除锯齿
        paint.setColor(Color.BLUE);//设置画笔颜色
        paint.setStrokeWidth(5);//设置圆弧宽度
        paint.setStyle(Paint.Style.STROKE);//设置圆弧为空心
        /*获取圆心的坐标*/
        int center = getWidth() / 2;
        int center1 = center -getWidth() / 5;

        int radius = getWidth() / 2 -5;//圆弧半径
        RectF rectF = new RectF(center - radius -1,center - radius - 1,
                center + radius + 1, center + radius + 1);
        canvas.drawArc(rectF, 235, -360 * progress, false,paint);

        if (progress >= 100){
            if (linel_x < radius / 3){
                linel_x++;
                linel_y++;
            }
            canvas.drawLine(center1, center, center1 + linel_x, center + linel_y, paint);

            if (linel_x == radius / 3){
                line2_x = linel_x;
                line2_y = linel_y;
                linel_x++;
                linel_y++;
            }
            if (linel_x > radius / 3 && line2_x <= radius){
                line2_x++;
                line2_y--;
            }
            canvas.drawLine(center1 + linel_x - 1, center + linel_y, center1 +line2_x, center + line2_y, paint);
            drawSuccess(mTvClearSuccess);
        }
        postInvalidateDelayed(10);
    }

    private void drawSuccess(TextView mTvClearSuccess) {
        mTvClearSuccess.setText("清理完成");
    }
    public void setTextView(TextView tv){
        mTvClearSuccess = tv;
    }
}
