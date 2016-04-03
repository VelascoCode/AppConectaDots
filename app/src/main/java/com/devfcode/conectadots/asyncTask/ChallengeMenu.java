package com.devfcode.conectadots.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.devfcode.conectadots.generators.ChallengeMenuGenerator;
import com.devfcode.conectadots.models.challenge.Challenge;
import com.devfcode.conectadots.models.challenge.ChallengeCollection;
import com.devfcode.conectadots.utils.Constants;

import java.util.List;

/**
 * Created by cvelasco on 02/04/16.
 */
public class ChallengeMenu extends AsyncTask<Void,Void,List<Challenge>> {

    private ChallengeMenuCallback callback;
    private Context context;
    private ChallengeCollection challengeCollection;
    private  List<Challenge> challenges;

    public ChallengeMenu (Context context, ChallengeMenuCallback callback){
        this.context=context;
        this.callback=callback;
    }

    public interface ChallengeMenuCallback{
        void processFinish(List<Challenge> challenges);
    }

    @Override
    protected List<Challenge> doInBackground(Void... params) {
        challengeCollection = ChallengeMenuGenerator.getChallengeCollection(context, Constants.CHALLENGE_MENU_JSON_NAME_FILE);
        challenges=challengeCollection.challenges;
        return challenges;
    }

    @Override
    protected void onPostExecute(List<Challenge> challenges) {
        if(callback != null){
            callback.processFinish(challenges);
        }
    }
}
