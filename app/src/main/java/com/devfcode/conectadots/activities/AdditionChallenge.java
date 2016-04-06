package com.devfcode.conectadots.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.devfcode.conectadots.R;
import com.devfcode.conectadots.asyncTask.AdditionFigure;
import com.devfcode.conectadots.models.addition.AdditionDot;
import com.devfcode.conectadots.utils.draw.AdditionDrawingView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AdditionChallenge extends AppCompatActivity implements AdditionFigure.AdditionFigureCallback {

    @Bind(R.id.first_operation_first_place)
    TextView firstOPerationFirstPLace;
    @Bind(R.id.first_operation_first_symbol)
    TextView firstOperationFirstSymbol;
    @Bind(R.id.first_operation_second_place)
    TextView firstOperationSecondPLace;
    @Bind(R.id.first_operation_equal_symbol)
    TextView firstOperationEqualSymbol;
    @Bind(R.id.first_operation_first_result)
    EditText firstOperationFirstResult;
    @Bind(R.id.second_operation_equal_symbol)
    TextView secondOperationEqualSymbol;
    @Bind(R.id.second_operation_first_place)
    TextView secondOperationFirstPlace;
    @Bind(R.id.second_operation_first_result)
    EditText secondOperationFirstResult;
    @Bind(R.id.second_operation_second_place)
    TextView secondOperationSecondPLace;
    @Bind(R.id.second_operation_first_symbol)
    TextView secondOperacionFirstSymbol;
    @Bind(R.id.addition_drawing)
    AdditionDrawingView additionDrawing;

    private float smallBrush;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_challenge);
        ButterKnife.bind(this);
        smallBrush = getResources().getInteger(R.integer.small_size);
        additionDrawing.setBrushSize(smallBrush);
        //Obtener informacion de puntos y operaciones
        new AdditionFigure(this.getApplicationContext(),this).execute();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void processFinish(List<AdditionDot> additionDots) {
        firstOPerationFirstPLace.setText(String.valueOf(additionDots.get(0).firstNumberPlace));
        firstOperationFirstSymbol.setText(additionDots.get(0).firstSymbolOperation);
        firstOperationSecondPLace.setText(String.valueOf(additionDots.get(0).secondNumberPlace));
        firstOperationEqualSymbol.setText(additionDots.get(0).secondSymbolOperation);

        secondOperationFirstPlace.setText(String.valueOf(additionDots.get(1).firstNumberPlace));
        secondOperacionFirstSymbol.setText(additionDots.get(1).firstSymbolOperation);
        secondOperationSecondPLace.setText(String.valueOf(additionDots.get(1).secondNumberPlace));
        secondOperationEqualSymbol.setText(additionDots.get(1).secondSymbolOperation);

    }
}
