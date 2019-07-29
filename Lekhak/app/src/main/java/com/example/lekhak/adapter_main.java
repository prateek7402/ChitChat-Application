package com.example.thegithub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.zip.Inflater;

public class adapter_main extends RecyclerView.Adapter<adapter_main.GitViewHolder> {


    private Context context;
    private User[] data;

    public adapter_main(Context context, User[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.users_layout, viewGroup, false);
        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder gitViewHolder, int i) {
        User user = data[i];
        gitViewHolder.userName.setText(user.getLogin());
       /* gitViewHolder.User_follower.setText(user.getFollowersUrl());
        gitViewHolder.User_following.setText(user.getFollowingUrl());*/
        Glide.with(gitViewHolder.userImage.getContext()).load(user.getAvatarUrl()).into(gitViewHolder.userImage);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GitViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
  /*      TextView User_follower;
        TextView User_following;
*/
        public GitViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.user_image);
            userName = (TextView) itemView.findViewById(R.id.user_name);
  /*          User_follower = (TextView) itemView.findViewById(R.id.followers);
            User_following = (TextView) itemView.findViewById(R.id.following);*/
        }
    }

}
