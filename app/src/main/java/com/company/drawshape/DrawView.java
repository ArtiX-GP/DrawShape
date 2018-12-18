package com.company.drawshape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DrawView extends View {

//        1 - круг.
//        2 - прямоугольник.
//        3 - треугольник.
    private int mForm;

    private int mColor;

//        1 - пустая.
//        2 - залитая.
//        3 - штрихи.
    private int mFill;

//        Кисть.
    private Paint mPaint;
    private Path mPath;

//        Стартовые координаты.
    private int mSX, mSY;

//        Конечные координаты.
    private int mEX, mEY;

    public DrawView(Context context) {
        super(context);
        mColor = Color.RED;
        mPath = new Path();
        mPaint = new Paint();
    }

    public DrawView(Context context, int form, int color, int fill) {
        super(context);

        if (form < 1 || form > 3) {
            mForm = 0;
        } else {
            mForm = form;
        }

//            Настраиваем цвет заливки.
        switch (color) {
            case 1:
                mColor = Color.RED;
                break;
            case 2:
                mColor = Color.GREEN;
                break;
            default:
                mColor = Color.BLUE;
                break;
        }

        if (fill < 1 || fill > 3) {
            mFill = 0;
        } else {
            mFill = fill;
        }

        mPath = new Path();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        if (mFill != 2) {
            mPaint.setStyle(Paint.Style.STROKE);
        } else {
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        switch (mForm) {
            case 1:
                mSX = 50;
                mSY = 50;

                int rad = 75;

                mEX = mSX + rad * 2;
                mEY = mSY + rad * 2;
                canvas.drawCircle(mSX + rad, mSY + rad, rad, mPaint);
                break;
            case 2:
                mSX = 50;
                mSY = 50;

                mEX = 350;
                mEY = 175;
                canvas.drawRect(mSX, mSY, mEX, mEY, mPaint);
                break;
            case 3:
                mEX = 125;
                mEY = 125;

                int halfWidth = 75;

                mSX = mEX - halfWidth;
                mSY = mEY - halfWidth;

                mPath.reset();
//               Рисуем треугольник.
                mPath.moveTo(mEX, mEY - halfWidth);
                mPath.lineTo(mEX - halfWidth, mEY + halfWidth);
                mPath.lineTo(mEX + halfWidth, mEY + halfWidth);
                mPath.lineTo(mEX, mEY - halfWidth);
                mPath.close();

                mEX += halfWidth;
                mEY += halfWidth;

                canvas.drawPath(mPath, mPaint);
                break;
        }

        if (mFill == 3) {
            mPaint.setStrokeWidth(2);
            mPath.reset();

//                Шаг, с которым будем рисовать штрихи.
            int delta = 10;

//                Длина между крайними точками.
//                Умножаем, чтобы наверняка покрыть фигуру.
            double d = Math.sqrt(((mEX - mSX) * (mEX - mSX)) + ((mEY - mSY) * (mEY - mSY))) * 1.3;

            int lines = (int) (d / delta);

            int step = delta;
            for (int i = 0; i < lines; i++) {
//                    Движемся в точку x, y.
                if (mSX + step >= mEX) {
                    mPath.moveTo(mEX, mSY + ((mSX + step) - mEX));
                } else {
                    mPath.moveTo(mSX + step, mSY);
                }

//                    Рисуем линию из x,y до x1, y1 (Конечные точки).
                if (mSY + step >= mEY) {
                    mPath.lineTo((mSX + ((mSY + step) - mEY)), Math.min(mSY + step, mEY));
                } else {
                    mPath.lineTo(mSX, Math.min(mSY + step, mEY));
                }
                step += delta;
            }

            canvas.drawPath(mPath, mPaint);
        }
    }
}
