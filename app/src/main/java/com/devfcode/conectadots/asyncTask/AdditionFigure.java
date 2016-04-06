package com.devfcode.conectadots.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.devfcode.conectadots.generators.FigureGenerator;
import com.devfcode.conectadots.models.addition.AdditionDot;
import com.devfcode.conectadots.models.addition.AdditionDotCollection;
import com.devfcode.conectadots.models.sequential.SequentialDot;
import com.devfcode.conectadots.utils.Constants;

import java.util.List;

/**
 * Created by cvelasco on 05/04/16.
 */
public class AdditionFigure extends AsyncTask<Void,Void,List<AdditionDot>> {
    private AdditionFigureCallback callback;
    private Context context;
    private AdditionDotCollection additionDotCollection;
    private  List<AdditionDot> additionDots;

    public AdditionFigure(Context context, AdditionFigureCallback callback){
        this.callback=callback;
        this.context=context;
    }

    public interface AdditionFigureCallback{
        void processFinish(List<AdditionDot> additionDots);
    }

    @Override
    protected List<AdditionDot> doInBackground(Void... params) {
        additionDotCollection= FigureGenerator.getAdditionDotCollection(context, Constants.ADDITION_DOTS_JSON_NAME_FILE);
        additionDots=additionDotCollection.additionDots;
        return additionDots;
    }

    @Override
    protected void onPostExecute(List<AdditionDot> additionDots) {
        if (callback != null) {
            callback.processFinish(additionDots);
        }
    }
}
