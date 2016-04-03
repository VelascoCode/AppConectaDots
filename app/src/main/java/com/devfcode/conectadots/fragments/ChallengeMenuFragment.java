package com.devfcode.conectadots.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.devfcode.conectadots.R;
import com.devfcode.conectadots.adapters.ChallengeMenuAdapter;
import com.devfcode.conectadots.models.challenge.Challenge;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ChallengeMenuFragment extends Fragment implements com.devfcode.conectadots.asyncTask.ChallengeMenu.ChallengeMenuCallback{
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private ChallengeMenuAdapter challengeMenuAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<Challenge> challenges;
    private static final String TAG = ChallengeMenuFragment.class.getSimpleName();

    public ChallengeMenuFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new com.devfcode.conectadots.asyncTask.ChallengeMenu(getContext(),this).execute();
    }

    private void setUpRecyclerView(){
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        challengeMenuAdapter = new ChallengeMenuAdapter(getActivity(),this,challenges);
        recyclerView.setAdapter(challengeMenuAdapter);
    }

    @Override
    public void processFinish(List<Challenge> challenges) {
        this.challenges=challenges;
        setUpRecyclerView();
    }

}
