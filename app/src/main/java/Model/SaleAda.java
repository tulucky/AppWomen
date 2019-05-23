package Model;

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

import lucky.dev.tu.devandroid.R;

public class SaleAda extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Product> products;

    public SaleAda(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sale, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.name.setText(products.get(i).getNameB() + "-" + products.get(i).getPrice() + "$");
        holder.sale.setText(products.get(i).getSale());
        Glide.with(context).load(RetrofitO.url + products.get(i).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return 26;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView sale;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            sale = view.findViewById(R.id.sale);
            image = view.findViewById(R.id.image);

        }
    }
}
