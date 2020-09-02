package com.example.techinshorts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapter_main extends RecyclerView.Adapter<adapter_main.GitViewHolder> {


    private Context context;
    private Data data;
    private List<Article> articles;


    public adapter_main(Context context, Data data) {
        this.context = context;
        this.data = data;
        this.articles  = data.getArticles();

    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.news_feed_container, viewGroup, false);
        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder gitViewHolder, int i) {
        Article user = articles.get(i);
        gitViewHolder.title.setText(user.getTitle());
        gitViewHolder.description.setText(user.getDescription());
        gitViewHolder.published_by.setText(user.getAuthor());
        gitViewHolder.time.setText(user.getPublishedAt());
       /* gitViewHolder.User_follower.setText(user.getFollowersUrl());
        gitViewHolder.User_following.setText(user.getFollowingUrl());*/
        Glide.with(gitViewHolder.News_image.getContext()).load(user.getUrlToImage()).into(gitViewHolder.News_image);
        /*gitViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("To access the full descriptionn please login using your google account:");
                builder.setIcon(R.drawable.ic_person_black_24dp);
                builder.setCancelable(false);
                builder.setPositiveButton("Proceed to login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setNegativeButton("Want to continue like a guest only", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return data.getTotalResults();
    }

    class GitViewHolder extends RecyclerView.ViewHolder {
        ImageView News_image;
        TextView description;
        TextView title;
        TextView time;
        TextView published_by;
        GitViewHolder(@NonNull View itemView) {
            super(itemView);
            News_image = itemView.findViewById(R.id.new_back);
            title =  itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_description);
            published_by = itemView.findViewById(R.id.news_publish);
            time = itemView.findViewById(R.id.news_time);
  /*          User_follower = (TextView) itemView.findViewById(R.id.followers);
            User_following = (TextView) itemView.findViewById(R.id.following);*/
        }
    }

}

