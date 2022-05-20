package com.example.onlineshopping;



import static com.google.android.material.internal.ContextUtils.getActivity;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class cartAdapter extends BaseAdapter {

    public ArrayList<ProductList> arrayListListener;
    private List<ProductList> cartList;
    Context context;
    TextView cartQty ;

    public cartAdapter(List<ProductList> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
        this.arrayListListener = new ArrayList<ProductList>();
        this.arrayListListener.addAll(cartList);
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final viewHolder holder;


        if(view == null){
            view= LayoutInflater.from(context).inflate(R.layout.cart_layout,null);
            holder= new cartAdapter.viewHolder();
            holder.proImg = (ImageView) view.findViewById(R.id.cart_proImage);
            holder.compName = (TextView) view.findViewById(R.id.cart_compName);
            holder.proName = (TextView) view.findViewById(R.id.cart_proName);
            holder.proAmt = (TextView) view.findViewById(R.id.cart_amtView);

            view.setTag(holder);
        }
        else{
            holder= (viewHolder) view.getTag();
        }

        try{
            int image= cartList.get(i).getImage();
            holder.proImg.setImageResource(image);
            holder.compName.setText(cartList.get(i).getCompanyName());
            holder.proName.setText(cartList.get(i).getProductName());
            holder.proAmt.setText("Rs. " + cartList.get(i).getAmount());



        }catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;

    }

    public class viewHolder{
        ImageView proImg;
        TextView compName,proName,proAmt;
    }
}
