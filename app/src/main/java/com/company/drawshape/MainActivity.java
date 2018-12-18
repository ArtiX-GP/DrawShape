package com.company.drawshape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private EditText mFormEditText;
    private EditText mColorEditText;
    private EditText mFillEditText;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Инициализация текстовых полей.
        mFormEditText = findViewById(R.id.FormValueEditText);
        mColorEditText = findViewById(R.id.ColorValueEditText);
        mFillEditText = findViewById(R.id.FillValueEditText);
        mContainer = findViewById(R.id.Container);
    }

    public void onClick(View view) {
        try {
//            Получение значений, которые ввели в текстовые поля.
            int form = Integer.parseInt(mFormEditText.getText().toString());
            int color = Integer.parseInt(mColorEditText.getText().toString());
            int fill = Integer.parseInt(mFillEditText.getText().toString());

            mContainer.removeAllViews();
            mContainer.addView(new DrawView(this, form, color, fill));

////            Передаем параметры во вторую активность.
//            Intent intent = new Intent(MainActivity.this, DrawActivity.class);
//            intent.putExtra("Form", form);
//            intent.putExtra("Color", color);
//            intent.putExtra("Fill", fill);
//            startActivity(intent);
        } catch (NumberFormatException e) {
//            Если ввели неверные значения, то ничего не делаем.
            e.printStackTrace();
        }
    }
}
