package com.devfcode.conectadots.utils.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.devfcode.conectadots.R;
import com.devfcode.conectadots.asyncTask.AdditionFigure;
import com.devfcode.conectadots.models.addition.AdditionDot;
import com.devfcode.conectadots.models.addition.AdditionDotCollection;
import java.util.List;

/**
 * Created by cvelasco on 05/04/16.
 */
public class AdditionDrawingView extends View implements AdditionFigure.AdditionFigureCallback {

    //drawing path
    private Path drawPath;
    //dtrawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor=0xFFFF0000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    //Flag erase
    private boolean erase=false;
    //
    private float brushSize, lastBrushSize;
    private static final String TAG = "AdditionDrawingView";
    //
    private AdditionDotCollection additionDotCollection;
    public List<AdditionDot> additionDots;
    private int contador=0;


    public AdditionDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }
    private void setupDrawing() {
        //get drawing area setup for interaction
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        //initial path properties
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        //initial canvasPaint properties
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        canvasPaint.setTextSize(40);
        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setBrushSize(float newSize){
        //update size
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        drawCanvas= new Canvas(canvasBitmap);
        //Draw initial dots
        new AdditionFigure(this.getContext(),this).execute();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        float touchX=event.getX();
        float touchY=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    public void processFinish(List<AdditionDot> additionDots) {

        this.additionDots=additionDots;
        for(AdditionDot ad:additionDots)
        {
            //Imagen de puntos
            drawCanvas.drawCircle(ad.dotX,ad.dotY,23,canvasPaint);
            drawCanvas.drawText(String.valueOf(ad.resultOperation), ad.dotX+30, ad.dotY+10, canvasPaint);
        }
    }
}
