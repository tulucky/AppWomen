package Model.Resum;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import Model.Product;
import Model.RetrofitO;
import Model.State;
import lucky.dev.tu.devandroid.R;

public class OrderedAda extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<OrderedP> products;

    public OrderedAda(Context mcontext, List<OrderedP> products) {
        this.mcontext = mcontext;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.ordered_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Holder holder = (Holder) viewHolder;
        Glide.with(mcontext).load(RetrofitO.url + products.get(i).getImage())
                .into(holder.image);
        holder.name.setText(products.get(i).getName());
        holder.size.setText(products.get(i).getSize());
        holder.number.setText(products.get(i).getNumber());
        holder.date.setText(products.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView size;
        TextView number;
        TextView date;

        public Holder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
            size = view.findViewById(R.id.size);
            number = view.findViewById(R.id.number);
            date = view.findViewById(R.id.date);
        }
    }
}
