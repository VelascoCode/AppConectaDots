package com.devfcode.conectadots.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.devfcode.conectadots.models.sequential.SequentialDot;
import com.devfcode.conectadots.models.sequential.SequentialDotCollection;
import com.devfcode.conectadots.utils.Constants;
import com.devfcode.conectadots.generators.FigureGenerator;

import java.util.List;

/**
 * Created by cvelasco on 29/03/16.
 */
public class SequentialFigure extends AsyncTask<Void,Void,List<SequentialDot>>{

    private SequentialFigureCallback callback;
    private Context context;
    private SequentialDotCollection sequentialDotCollection;
    private List<SequentialDot> sequentialDots;

    public interface SequentialFigureCallback{
        void processFinish(List<SequentialDot> sequentialDots);
    }

    public SequentialFigure(Context context, SequentialFigureCallback callback){
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected List<SequentialDot> doInBackground(Void... params) {
        sequentialDotCollection= FigureGenerator.getSequentialDotCollection(context, Constants.SEQUENTIAL_DOTS_JSON_NAME_FILE);
        sequentialDots = sequentialDotCollection.sequential_dots;
        return sequentialDots;
    }

    @Override
    protected void onPostExecute(List<SequentialDot> sequentialDots) {
        if(callback != null){
            callback.processFinish(sequentialDots);
        }
    }


}
