package com.example.onlineshopping;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView uname,email;
    private EditText address,mobile,fullname;
    private DatabaseHelper db;
    private HomePage hp;
    private Button update;
    User mUser;

    Context context;


    public account_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static account_fragment newInstance(String param1, String param2) {
        account_fragment fragment = new account_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        context = view.getContext();
        String user_email = this.getArguments().getString("email");
        String user_uname= this.getArguments().getString("uname");

        update = view.findViewById(R.id.updateBtn);
        uname = view.findViewById(R.id.acc_uname);
        email = view.findViewById(R.id.acc_email);
        mobile = view.findViewById(R.id.acc_mobile_text);
        address = view.findViewById(R.id.acc_add_edit_text);
        fullname = view.findViewById(R.id.acc_fullname_text);

        uname.setText("@"+user_uname.trim());
        email.setText(user_email);

        db= new DatabaseHelper(view.getContext());
        hp = new HomePage();
        mUser = db.returnUser(user_email);


        if(!(mUser.getFullname().equals("null") || mUser.getAddress().equals("@@@") || mUser.getNumber().equals("xxxxxxxxxx"))){
            setValues(mUser);
        }
        else{
            makeEditable();
            Toast.makeText(context, "Enter Your Information to Update the database!", Toast.LENGTH_SHORT).show();
        }


        update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                User currUser= getUserDetails();
                if(currUser!=null){
                    currUser.setEmail(user_email);
                    db.updateUserDetails(currUser);
                    setValues(currUser);
                    Toast.makeText(context, "Successfully Updated ", Toast.LENGTH_SHORT).show();
                    removeEditable();
                }

            }
            catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    });
    return view;
    }

    private void setValues(User temp){
        mobile.setText(temp.getNumber());
        address.setText(temp.getAddress());
        fullname.setText(temp.getFullname());
    }
    private void makeEditable(){
        mobile.setEnabled(true);
        address.setEnabled(true);
        fullname.setEnabled(true);
        update.setEnabled(true);
    }
    private void removeEditable(){
        mobile.setEnabled(false);
        address.setEnabled(false);
        fullname.setEnabled(false);
        update.setEnabled(false);
    }

    private User getUserDetails(){
        User temp = new User();

        if(fullname.getText().toString().isEmpty() || mobile.getText().toString().isEmpty() || address.getText().toString().isEmpty()){
            Toast.makeText(context, "Please enter values in all the fields!", Toast.LENGTH_SHORT).show();
            return null;
        }else{
            if(mobile.getText().toString().length() == 10){
                temp.setAddress(address.getText().toString());
                temp.setFullname(fullname.getText().toString());
                temp.setNumber(mobile.getText().toString());
            }
            else{
                Toast.makeText(context, "Invalid Mobile number!", Toast.LENGTH_SHORT).show();
                mobile.setText("");
                return null;
            }

        }
        return temp;
    }
}