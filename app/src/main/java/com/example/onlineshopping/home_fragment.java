package com.example.onlineshopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ArrayList<ProductList> list = new ArrayList<>();
    ListView lv;


    public home_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static home_fragment newInstance(String param1, String param2) {
        home_fragment fragment = new home_fragment();
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
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        lv= (ListView) view.findViewById(R.id.ProductListView);
        list.add(new ProductList(R.drawable.perfume,"The Man Company","Combo Perfume",1199));
        list.add(new ProductList(R.drawable.face_wash, "Bombay Shaving Company","Face Wash Combo",480));
        list.add(new ProductList(R.drawable.tshirt,"Puma","Risque Printed T-Shirt",799));
        list.add(new ProductList(R.drawable.tshirt_2,"Puma","Printed T-Shirt",499));
        list.add(new ProductList(R.drawable.bottle_6,"Fridge Bottles","Pack Of 6",769));
        list.add(new ProductList(R.drawable.jbl_speaker,"JBL Speaker","JBL Flip 3",4999));
        list.add(new ProductList(R.drawable.watch,"Longines","Analog Watch",1499));
        list.add(new ProductList(R.drawable.smartwatch,"Smartwatch","Bluetooth smartwatch",4099));
        list.add(new ProductList(R.drawable.shoes,"Adidas","Running Shoes",3499));
        list.add(new ProductList(R.drawable.shoes_1,"Formal Shoes","Tan Formal shoes",1369));





        ProductAdapter adapter = new ProductAdapter(list,view.getContext());
        lv.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;

    }
}