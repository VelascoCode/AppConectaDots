package com.devfcode.conectadots.utils.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.devfcode.conectadots.R;
import com.devfcode.conectadots.models.sequential.SequentialDot;
import com.devfcode.conectadots.models.sequential.SequentialDotCollection;
import com.devfcode.conectadots.asyncTask.SequentialFigure;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cvelasco on 16/03/16.
 */
public class DrawingView extends View implements SequentialFigure.SequentialFigureCallback{

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
    private static final String TAG = "DrawingView";

    //
    private SequentialDotCollection sequentialDotCollection;
    private List<SequentialDot> sequentialDots;
    private List<TouchDot> touchDots;
    private int contador=0;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){
        //get drawing area setup for interaction
        drawPath = new Path();
        drawPaint=new Paint();
        drawPaint.setColor(paintColor);
        //initial path properties
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);


        //initial canvasPaint properties
        canvasPaint= new Paint(Paint.DITHER_FLAG);
        canvasPaint.setTextSize(40);

        //
        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);

        //Imagen de puntos
        //Inicializamos lista de TouchDots
        touchDots= new ArrayList<TouchDot>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        drawCanvas= new Canvas(canvasBitmap);
        Log.w(TAG, "---- ONSIZECHANGED---");
        drawFigure();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
        Log.w(TAG, "----ONDRAW----");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        float touchX=event.getX();
        float touchY=event.getY();
        float touchMajor = event.getTouchMajor();
        float touchMinor = event.getTouchMinor();
        touchDots.add(new TouchDot(touchX,touchY,touchMajor,touchMinor));
        Log.w(TAG, "EdgeFlags: " + event.getEdgeFlags());

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX,touchY);
                Log.w(TAG,"ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX,touchY);
                Log.w(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                Log.w(TAG, "ACTION_UP");
                drawPath.reset();
                validateTouchLine();

                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor (String newColor){
        //set color
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void setErase(boolean isErase){
        erase= isErase;
        if(erase)
            drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else
            drawPaint.setXfermode(null);
    }

    public void setBrushSize(float newSize){
        //update size
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }

    //
    public void drawFigure()
    {
        new SequentialFigure(this.getContext(),this).execute();
    }

    public void validateTouchLine()
    {
        int left,top,right,botton;
        for(TouchDot touchDot:touchDots)
        {
            Log.w(TAG,"X: "+touchDot.getDotX()+" Y: "+touchDot.getDotY()+" TouchMajor: "+touchDot.getTouchMajor()+ " TouchMinor: "+touchDot.getTouchMinor());

            left=(int)(touchDot.getDotX() - touchDot.getTouchMajor()/2);
            top=(int)(touchDot.getDotY() - touchDot.getTouchMinor()/2);
            right=(int)(touchDot.getDotX() + touchDot.getTouchMajor()/2);
            botton=(int)(touchDot.getDotY() + touchDot.getTouchMinor()/2);

            Log.w(TAG,"LEFT: "+left+" TOP: "+top+" RIGHT:"+right+" BOTTON: "+botton);

        }
        Log.w(TAG, "Numero de Puntos: " + touchDots.size());
        //ValidateTouchDots validateTouchDots=new ValidateTouchDots(,touchDots);
        touchDots.clear();

    }

    public List<ReferenceDot> referencesDots(){
        List <ReferenceDot> auxList= new ArrayList<ReferenceDot>();
        ReferenceDot initialReferenceDot = new ReferenceDot(sequentialDots.get(contador).dotX,sequentialDots.get(contador).dotY);
        ReferenceDot finalReferenceDot = new ReferenceDot(sequentialDots.get(contador+1).dotX,sequentialDots.get(contador+1).dotY);
        auxList.add(initialReferenceDot);
        auxList.add(finalReferenceDot);
        return auxList;
    }


    @Override
    public void processFinish(List<SequentialDot> sequentialDots) {

        this.sequentialDots=sequentialDots;
        for(SequentialDot sd:sequentialDots)
        {
            Log.w(TAG, "X: " + sd.dotX + " Y:" + sd.dotY);
            //Imagen de puntos
            drawCanvas.drawCircle(sd.dotX,sd.dotY,23,canvasPaint);
            drawCanvas.drawText(sd.value, sd.dotX+30, sd.dotY+10, canvasPaint);
        }

    }
}
