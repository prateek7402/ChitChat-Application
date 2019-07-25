package com.example.jaankaar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;



public class MyAccount extends Fragment {
    TextView t1, t2, t3;
    de.hdodenhof.circleimageview.CircleImageView pic;
    login_state_checker login_state_checker;
//    private OnFragmentInteractionListener mListener;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        t1 = (TextView)view.findViewById(R.id.pi_name);
        t2 = (TextView)view.findViewById(R.id.pi_phone);
        t3 = (TextView)view.findViewById(R.id.pi_email);
        pic = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.profile_image_pi);
        login_state_checker = new login_state_checker(view.getContext());
        t1.setText(login_state_checker.putname());
        t2.setText(login_state_checker.putnumber());
        t3.setText(login_state_checker.putemail());
        Uri savedImageURI = Uri.parse(login_state_checker.putimage());

        pic.setImageURI(savedImageURI);
        return view;
    }



    public void ADD(View v1)
    {

    }

    public void Edit_pi(View v2)
    {

    }
}
