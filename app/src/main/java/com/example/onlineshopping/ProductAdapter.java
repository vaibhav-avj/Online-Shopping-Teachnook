package com.example.onlineshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    public ArrayList<ProductList> arrayListListener;
    private List<ProductList> productList;
    Context context;
    public static ArrayList<ProductList> cartList = new ArrayList<ProductList>();


    public ProductAdapter(List<ProductList> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.arrayListListener = new ArrayList<ProductList>();
        this.arrayListListener.addAll(productList);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final viewHolder holder;
        if(view == null){
            view= LayoutInflater.from(context).inflate(R.layout.product_layout,null);
            holder= new viewHolder();
            holder.cart = view.findViewById(R.id.cartImg);
            holder.proImg = (ImageView) view.findViewById(R.id.proImage);
            holder.compName = (TextView) view.findViewById(R.id.compName);
            holder.proName = (TextView) view.findViewById(R.id.proName);
            holder.proAmt = (TextView) view.findViewById(R.id.amtView);

            view.setTag(holder);
        }
        else{
            holder= (viewHolder) view.getTag();
        }
        try{
            int image= productList.get(i).getImage();
            holder.proImg.setImageResource(image);
            holder.compName.setText(productList.get(i).getCompanyName());
            holder.proName.setText(productList.get(i).getProductName());
            holder.proAmt.setText("Rs. " + productList.get(i).getAmount());

            holder.cart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int flag = checkCart(i);
                   if(flag==1)
                   {
                       cartList.add(new ProductList(productList.get(i).getImage(),productList.get(i).getCompanyName(), productList.get(i).getProductName(),productList.get(i).getAmount()));
                       Toast.makeText(context, holder.proName.getText().toString() + " has been added to cart!", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(context, "Product already in cart", Toast.LENGTH_SHORT).show();
                   }

               }
            });

        }catch(Exception e){

        }

        return view;
    }

    private int checkCart(int i){
        int flag=1;
        for(int j=0;j<cartList.size();j++){
            if(cartList.get(j).getProductName().equals(productList.get(i).getProductName())){
                flag=0;
                break;
            }
            else
                flag=1;
        }
        return flag;
    }

    public class viewHolder{
        ImageView proImg;
        Button cart;
        TextView compName,proName,proAmt,removeItem;
    }
}
