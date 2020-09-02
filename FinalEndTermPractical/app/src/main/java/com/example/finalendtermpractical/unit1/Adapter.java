package com.example.finalendtermpractical.unit1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalendtermpractical.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter {

    ArrayList<DataClass> dataset;
    Context context;
    FirebaseAuth auth ;

    public Adapter(ArrayList<DataClass> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    public static class InputMode extends RecyclerView.ViewHolder {
        EditText tname;
        Button bt;

        public InputMode(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.input);
            bt = itemView.findViewById(R.id.submit);
        }
    }

    public static class DisplayMode extends RecyclerView.ViewHolder {
        public DisplayMode(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class RegisterMode extends RecyclerView.ViewHolder {
        EditText t_name_r, t_pass_r;
        Button bt_r;

        public RegisterMode(@NonNull View itemView) {
            super(itemView);
            t_name_r = itemView.findViewById(R.id.email_r);
            t_pass_r = itemView.findViewById(R.id.pass_r);
            bt_r = itemView.findViewById(R.id.but_r);
        }
    }

    public static class SignupMode extends RecyclerView.ViewHolder {
        EditText name_s,pass_s;
        Button bt_s;
        public SignupMode(@NonNull View itemView) {
            super(itemView);
            name_s = itemView.findViewById(R.id.email_s);
            pass_s = itemView.findViewById(R.id.pass_s);
            bt_s  = itemView.findViewById(R.id.but_s);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DataClass.INPUT_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.input, parent, false);
                return new InputMode(view);
            case DataClass.DISPLAY_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diplaymode, parent, false);
                return new DisplayMode(view);
            case DataClass.REGISTER_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registermode,parent,false);
                return new RegisterMode(view);
            case DataClass.SIGNUP_MODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.signupmode,parent,false);
                return new SignupMode(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
         DataClass obj = dataset.get(position);
         switch (obj.type)
         {
             case DataClass.INPUT_MODE:
             {
                 ((InputMode)holder).bt.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         String hh = ((InputMode)holder).tname.getText().toString();
                         Toast.makeText(context, hh, Toast.LENGTH_SHORT).show();
                     }
                 });
                 break;
             }
             case DataClass.DISPLAY_MODE:
                 break;
             case DataClass.REGISTER_MODE:
             {
                 auth = FirebaseAuth.getInstance();
                 ((RegisterMode)holder).bt_r.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         String email = ((RegisterMode)holder).t_name_r.getText().toString();
                         String pass = ((RegisterMode)holder).t_pass_r.getText().toString();
                         auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                             @Override
                             public void onSuccess(AuthResult authResult) {
                                 Toast.makeText(context, "User Created"+authResult.getUser(), Toast.LENGTH_SHORT).show();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(context, "User creationn Failed", Toast.LENGTH_SHORT).show();
                             }
                         });
                     }
                 });
                 break;
             }
             case DataClass.SIGNUP_MODE:
             {
                 auth = FirebaseAuth.getInstance();
                 ((SignupMode)holder).bt_s.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         String email = ((SignupMode)holder).name_s.getText().toString();
                         String pass = ((SignupMode)holder).pass_s.getText().toString();
                         auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                             @Override
                             public void onSuccess(AuthResult authResult) {
                                 Toast.makeText(context, "User Created"+authResult.getUser(), Toast.LENGTH_SHORT).show();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(context, "User creationn Failed", Toast.LENGTH_SHORT).show();
                             }
                         });
                     }
                 });
                 break;
             }
         }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        switch (dataset.get(position).type)
        {
            case 0:
                return DataClass.INPUT_MODE;
            case 1:
                return DataClass.DISPLAY_MODE;
            case 2:
                return DataClass.REGISTER_MODE;
            case 3:
                return DataClass.SIGNUP_MODE;
            default:
                return -1;
        }
    }
}
