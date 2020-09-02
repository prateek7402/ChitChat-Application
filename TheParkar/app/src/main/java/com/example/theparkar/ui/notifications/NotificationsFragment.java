package com.example.theparkar.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.theparkar.Data_Model.Data_Model_for_user_Details;
import com.example.theparkar.Login_State_Checker.Login_State_checker;
import com.example.theparkar.Login_State_Checker.Shrared_prefference_login;
import com.example.theparkar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Button logout;
    FirebaseAuth auth;
    Shrared_prefference_login shrared_prefference_login;
    TextView name_user,phone_user,email_user,address_user,car_user,car_number_user;
    de.hdodenhof.circleimageview.CircleImageView user_image;
    FirebaseFirestore firebaseFirestore;
    ImageView user_edit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        logout = root.findViewById(R.id.button_logout);
        auth = FirebaseAuth.getInstance();
        name_user = root.findViewById(R.id.user_id_name);
        phone_user = root.findViewById(R.id.user_phone_number);
        email_user = root.findViewById(R.id.user_email_id);
        address_user = root.findViewById(R.id.user_home_address);
        car_user = root.findViewById(R.id.user_vehicle);
        car_number_user = root.findViewById(R.id.user_vehicle_number);

        String user_id = auth.getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(user_id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Data_Model_for_user_Details data_model_for_user_details = documentSnapshot.toObject(Data_Model_for_user_Details.class);
                name_user.setText(data_model_for_user_details.getName());
                phone_user.setText(data_model_for_user_details.getPhone());
                email_user.setText(data_model_for_user_details.getPhone());
                address_user.setText(data_model_for_user_details.getAddress());
                car_user.setText(data_model_for_user_details.getVehicle());
                car_number_user.setText(data_model_for_user_details.getVehicle_Number());
            }
        });

        shrared_prefference_login = new Shrared_prefference_login(getContext());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                shrared_prefference_login.logout();
                Intent i = new Intent(getContext(), Login_State_checker.class);
                startActivity(i);
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        return root;
    }
}