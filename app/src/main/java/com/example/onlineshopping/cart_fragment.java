package com.example.onlineshopping;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cart_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cart_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final double TAX_PER_ITEM = 5;
    private double tax,total, cartValue;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<ProductList> list = new ArrayList<>();
    private HashMap<ProductList, Integer> cart = new HashMap<ProductList,Integer>();
    ListView lv;
    Button placeOrder;
    TextView emptyCart,cv,tv,totcv,taxes;
    private cartAdapter adapter;
    private User user;
    private DatabaseHelper db;

    public cart_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cart_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cart_fragment newInstance(String param1, String param2) {
        cart_fragment fragment = new cart_fragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        String email = this.getArguments().getString("email");

        db = new DatabaseHelper(view.getContext());
        user= db.returnUser(email);
        placeOrder = view.findViewById(R.id.placeOrder);
        cv = view.findViewById(R.id.cartVal);
        tv = view.findViewById(R.id.cartTV);
        totcv = view.findViewById(R.id.totCV);
        taxes = view.findViewById(R.id.taxes);
        emptyCart = view.findViewById(R.id.emptyCart);

        taxes.append(" ("+ Double.toString(TAX_PER_ITEM) + "%)");
        lv=view.findViewById(R.id.cartListView);
        list = ProductAdapter.cartList;

        if(list.size() == 0){
            emptyCart.setVisibility(View.VISIBLE);
            placeOrder.setVisibility(View.GONE);
            lv.setVisibility(View.INVISIBLE);
        }else{
            lv.setVisibility(View.VISIBLE);
            adapter = new cartAdapter(list,view.getContext());
            lv.setAdapter(adapter);
        }
        calculateValuesPrint();

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(user.getFullname().equals("null") || user.getAddress().equals("@@@") || user.getNumber().equals("xxxxxxxxxx"))){
                    confirmOrder();
                }
                else{
                    Toast.makeText(getActivity(), "Please Update your account information before placing order.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return  view;
    }
    private void calculateValuesPrint(){
        for(int i=0;i<list.size();i++){
            total+=list.get(i).getAmount();
        }
        tax=(double)(total * TAX_PER_ITEM)/100;
        cartValue = tax+total;
        cv.setText("Rs. " + Double.toString(total));
        tv.setText("Rs. " + Double.toString(tax));
        totcv.setText("Rs. " + Double.toString(cartValue));
    }

    public void resetCart(){
        cv.setText("Rs 0");
        tv.setText("Rs. 0");
        totcv.setText("Rs. 0");
        list.clear();
        lv.setVisibility(View.INVISIBLE);
        emptyCart.setVisibility(View.VISIBLE);
    }
    private void confirmOrder(){
        androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder1.setMessage("Confirm Your purchase\n" +
                "Click Yes to place Order. ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resetCart();
                        Toast.makeText(getActivity(), "Your Order has been placed!", Toast.LENGTH_SHORT).show();
                        placeOrder.setVisibility(View.GONE);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}