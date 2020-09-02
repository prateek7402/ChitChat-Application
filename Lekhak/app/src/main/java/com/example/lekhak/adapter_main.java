package com.example.lekhak;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class adapter_main extends RecyclerView.Adapter<adapter_main.GitViewHolder> {


    private Context context;
    private Data[] data;

    public adapter_main(Context context, Data[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.info_container, viewGroup, false);
        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder gitViewHolder, int i) {
        Data user = data[i];
        gitViewHolder.userLikes.setText(user.getLikes());
        gitViewHolder.userComments.setText(user.getComments());
        gitViewHolder.userCaption.setText(user.getCaption());
       /* gitViewHolder.User_follower.setText(user.getFollowersUrl());
        gitViewHolder.User_following.setText(user.getFollowingUrl());*/
        Glide.with(gitViewHolder.userImage.getContext()).load(user.getUrl()).into(gitViewHolder.userImage);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GitViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userLikes;
        TextView userComments;
        TextView userCaption;
  /*      TextView User_follower;
        TextView User_following;
*/
        public GitViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.image);
            userLikes = (TextView) itemView.findViewById(R.id.likes);
            userComments = (TextView)itemView.findViewById(R.id.comments);
            userCaption = (TextView)itemView.findViewById(R.id.caption);
  /*          User_follower = (TextView) itemView.findViewById(R.id.followers);
            User_following = (TextView) itemView.findViewById(R.id.following);*/
        }
    }

}
