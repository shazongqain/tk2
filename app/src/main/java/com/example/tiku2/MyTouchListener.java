package com.example.tiku2;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MyTouchListener implements View.OnTouchListener {
    private PointF startPoint= new PointF();
    private Matrix matrix=new Matrix();
    private Matrix currentMatrix=new Matrix();
    private int mode=0;
    private static final int DRAG=1;
    private static final int ZOOM=2;
    private float startDis;
    private PointF midPoint;
    private ImageView imageView;
    private GestureDetector detector;

    public MyTouchListener(ImageView image) {
        this.imageView = image;

        detector=new GestureDetector(new GestureDetector.SimpleOnGestureListener());
        detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (imageView.getScaleX()!=1){
                    imageView.setScaleX(1);
                    imageView.setScaleY(1);
                }else {
                    imageView.setScaleX(3);
                    imageView.setScaleY(3);
                }
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
    }


    public boolean onTouch(View v, MotionEvent event) {

        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        detector.onTouchEvent(event);
        switch (event.getAction()&MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode=DRAG;
                matrix.set(imageView.getImageMatrix());
                currentMatrix.set(imageView.getImageMatrix());
                startPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                if(mode==DRAG){
                    float dx=event.getX()-startPoint.x;
                    float dy=event.getY()-startPoint.y;
                    matrix.set(currentMatrix);

                    matrix.postTranslate(dx, dy);
                }else if(mode==ZOOM){
                    float endDis=distance(event);
                    if(endDis>10f){

                        float scale=endDis/startDis;



                        matrix.set(currentMatrix);
                        matrix.postScale(scale,scale,midPoint.x,midPoint.y);
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode=0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode=ZOOM;
                startDis=distance(event);
                if(startDis>10f){
                    midPoint=mid(event);
                    currentMatrix.set(imageView.getImageMatrix());
                }
                break;

            default:
                break;
        }

        imageView.setImageMatrix(matrix);
        return true;
    }



    public float distance(MotionEvent event) {
        float dx=event.getX(1)-event.getX(0);
        float dy=event.getY(1)-event.getY(0);
        return (float) Math.sqrt(dx*dx+dy*dy);
    }


    public static PointF mid(MotionEvent event){
        float midx=(event.getX(1)+event.getX(0))/2;
        float midy=(event.getY(1)+event.getY(0))/2;
        return new PointF(midx,midy);
    }
}


