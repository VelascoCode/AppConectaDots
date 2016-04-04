package com.devfcode.conectadots.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.devfcode.conectadots.R;
import com.devfcode.conectadots.fragments.ChallengeMenuFragment;
import com.devfcode.conectadots.generators.ChallengeMenuGenerator;
import com.devfcode.conectadots.models.challenge.Challenge;
import com.devfcode.conectadots.models.challenge.ChallengeCollection;
import com.devfcode.conectadots.utils.Constants;
import com.devfcode.conectadots.adapters.ChallengeMenuAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ChallengeMenuAdapter.OnChallengeSelected{

    private static final String TAG = "MainActivity";
    private ChallengeCollection challengeCollection;
    private List<Challenge> challenges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_fragment);
        if(fragment == null){
            fragment = new ChallengeMenuFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container_fragment, fragment)
                    .commit();
        }
    }

    @Override
    public void onChallengeClick(Challenge challenge) {

        switch (challenge.type)
        {
            case "sequentialChallenge":
                startActivity(new Intent(this, SequentialChallenge.class));
                break;
            case "additionChallenge":
                startActivity(new Intent(this, AdditionChallenge.class));
                break;
            case "additionFigureChallenge":
                startActivity(new Intent(this, SequentialChallenge.class));
                break;
            default : break;

        }

        Toast.makeText(this.getApplicationContext(), challenge.type, Toast.LENGTH_SHORT).show();

    }

}
