
package com.example.multiviewholder;

;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Adapter_multi extends RecyclerView.Adapter {

    private ArrayList<Data> dataSet;
    Context mContext;

    public Adapter_multi(ArrayList<Data> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;
    String name = "";

    public static class Input_mode_holder extends RecyclerView.ViewHolder {

        EditText t1;
        Button b1;


        public Input_mode_holder(@NonNull View itemView) {
            super(itemView);

            this.t1 = (EditText) itemView.findViewById(R.id.input);
            this.b1 = (Button) itemView.findViewById(R.id.submit);
        }
    }

    public static class Display_mode_holder extends RecyclerView.ViewHolder {

        TextView user_name;
        de.hdodenhof.circleimageview.CircleImageView user_image;

        public Display_mode_holder(@NonNull View itemView) {
            super(itemView);

            user_name = (TextView) itemView.findViewById(R.id.user_name);
            user_image = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.user_image);
        }
    }


    public static class Picture_mode_holder extends RecyclerView.ViewHolder {
        TextView code;
        ImageView back;

        public Picture_mode_holder(@NonNull View itemView) {
            super(itemView);

            code = (TextView) itemView.findViewById(R.id.type);
            back = (ImageView) itemView.findViewById(R.id.background);
        }
    }

    public static class Music_mode_holder extends RecyclerView.ViewHolder {
        FloatingActionButton bt1;

        public Music_mode_holder(@NonNull View itemView) {
            super(itemView);

            bt1 = (FloatingActionButton) itemView.findViewById(R.id.play);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Data.INPUT_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_1, parent, false);
                return new Input_mode_holder(view);
            case Data.DISPLAY_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_2, parent, false);
                return new Display_mode_holder(view);
            case Data.IMAGE_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_3, parent, false);
                return new Input_mode_holder(view);
            case Data.MUSIC_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_4, parent, false);
                return new Music_mode_holder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Data.INPUT_MODE;
            case 1:
                return Data.DISPLAY_MODE;
            case 2:
                return Data.IMAGE_MODE;
            case 3:
                return Data.MUSIC_MODE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Data object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Data.INPUT_MODE:
                    ((Input_mode_holder) holder).t1.setText(object.text);
                    ((Input_mode_holder) holder).b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            name = ((Input_mode_holder) holder).t1.getText().toString();
                            Toast.makeText(mContext, "welcome : "+name, Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
                case Data.DISPLAY_MODE:
                    ((Display_mode_holder) holder).user_name.setText(object.text);
                    ((Display_mode_holder) holder).user_image.setImageResource(object.data);
                    break;
                case Data.IMAGE_MODE:
                  /*  ((Picture_mode_holder) holder).code.setText(object.text);
                    ((Picture_mode_holder) holder).back.setImageResource(object.data);
                  */  break;
                case Data.MUSIC_MODE:

                    ((Music_mode_holder) holder).bt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (fabStateVolume) {
                                if (mPlayer.isPlaying()) {
                                    mPlayer.stop();

                                }
                                ((Music_mode_holder) holder).bt1.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                                fabStateVolume = false;

                            } else {
                                mPlayer = MediaPlayer.create(mContext, R.raw.ye_baate);
                                mPlayer.setLooping(true);
                                mPlayer.start();
                                ((Music_mode_holder) holder).bt1.setImageResource(R.drawable.ic_pause_black_24dp);
                                fabStateVolume = true;

                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
