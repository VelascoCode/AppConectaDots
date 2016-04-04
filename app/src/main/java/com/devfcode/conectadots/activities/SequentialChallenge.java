package com.devfcode.conectadots.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.devfcode.conectadots.R;
import com.devfcode.conectadots.utils.draw.DrawingView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SequentialChallenge extends AppCompatActivity {

    @Bind(R.id.drawing)
    DrawingView drawView;
    private float smallBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequential_challenge);
        ButterKnife.bind(this);
        smallBrush = getResources().getInteger(R.integer.small_size);
        drawView.setBrushSize(smallBrush);
    }
}
