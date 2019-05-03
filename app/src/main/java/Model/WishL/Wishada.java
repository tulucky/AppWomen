package Model.WishL;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Product;
import Model.RetrofitO;
import Model.State;
import lucky.dev.tu.devandroid.R;

public class Wishada extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<Product> products;

    public Wishada(Context mcontext, List<Product> products) {
        this.mcontext = mcontext;
        this.products = new ArrayList<>(products);
        Collections.reverse(this.products);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.wl_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Holder holder = (Holder) viewHolder;
        Glide.with(mcontext).load(RetrofitO.url + products.get(i).getImage())
                .into(holder.imageWl);
        holder.nameWl.setText(products.get(i).getTitle());
        holder.oPriceWl.setText(products.get(i).getOriginprice());
        holder.oPriceWl.setPaintFlags(holder.oPriceWl.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.priceWl.setText(products.get(i).getName());
        holder.saleWl.setText(products.get(i).getSale());
        holder.lovedwl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                State.removeProduct(products.get(i));
                products.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private class Holder extends RecyclerView.ViewHolder {
        ImageView imageWl;
        ImageView lovedwl;
        ImageView bagWl;
        TextView nameWl;
        TextView oPriceWl;
        TextView saleWl;
        TextView priceWl;

        public Holder(View view) {
            super(view);
            imageWl = view.findViewById(R.id.imagewl);
            lovedwl = view.findViewById(R.id.lovedwl);
            bagWl = view.findViewById(R.id.bagwl);
            nameWl = view.findViewById(R.id.namewl);
            oPriceWl = view.findViewById(R.id.opricewl);
            saleWl = view.findViewById(R.id.salewl);
            priceWl = view.findViewById(R.id.pricewl);
        }
    }
}
