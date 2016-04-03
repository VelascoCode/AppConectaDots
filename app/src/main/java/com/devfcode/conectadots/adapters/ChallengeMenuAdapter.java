package com.devfcode.conectadots.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.support.v4.app.Fragment;

import  com.devfcode.conectadots.R;

import com.devfcode.conectadots.models.challenge.Challenge;
import com.devfcode.conectadots.utils.picassoTransforms.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;

/**
 * Created by cvelasco on 02/04/16.
 */
public class ChallengeMenuAdapter extends RecyclerView.Adapter<ChallengeMenuAdapter.ViewHolder> {
    private Context context;
    private List<Challenge> challenges;
    private AppCompatActivity appCompatActivity;
    private OnChallengeSelected onChallengeSelected;

    public ChallengeMenuAdapter(Context context, Fragment fragment,List<Challenge> challenges)
    {
        this.context=context;
        this.challenges=challenges;
        try{
            onChallengeSelected = (OnChallengeSelected) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_challenge,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Challenge challenge = challenges.get(position);
        viewHolder.labelName.setText(challenge.name);
        viewHolder.labelReview.setText(challenge.review);
        int challengeImageId= context.getResources().getIdentifier(challenge.srcImg, "drawable", context.getPackageName());
        Picasso.with(context)
                .load(challengeImageId)
                .transform(new CircleTransform())
                .placeholder(R.drawable.ic_widgets_black_24dp)
                .into(viewHolder.imageChallenge);
        viewHolder.containerHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChallengeSelected.onChallengeClick(challenge);
            }
        });
    }


    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public interface OnChallengeSelected{
        void onChallengeClick(Challenge challenge);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_challenge)
        ImageView imageChallenge;
        @Bind(R.id.container_holder)
        LinearLayout containerHolder;
        @Bind(R.id.challenge_name)
        TextView labelName;
        @Bind(R.id.challenge_review)
        TextView labelReview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
