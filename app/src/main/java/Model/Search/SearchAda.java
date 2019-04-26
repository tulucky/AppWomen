package Model.Search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Model.Product;
import Model.RetrofitO;
import lucky.dev.tu.devandroid.ProductDetail;
import lucky.dev.tu.devandroid.R;

public class SearchAda extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Context context;
    List<Product> products;
    List<Product> temp;
    RecyclerView search;

    public SearchAda(Context context, List<Product> productList, RecyclerView search) {
        this.context = context;
        this.products = new ArrayList<>(productList);
        this.search = search;
        temp = productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, viewGroup, false);
        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HolderView holder = (HolderView) viewHolder;
        Glide.with(context).load(RetrofitO.url + products.get(i).getImage())
                .into(holder.image);
        holder.name.setText(products.get(i).getTitle());
        holder.sale.setText(products.get(i).getSale());
        holder.price.setText(products.get(i).getName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("id", products.get(i).getId());
                intent.putExtra("image", products.get(i).getImage());
                context.startActivity(intent);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("id", products.get(i).getId());
                intent.putExtra("image", products.get(i).getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Product> ketQua = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    search.setVisibility(View.GONE);
                    ketQua.addAll(temp);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    Log.i("lp", " " + filterPattern);

                    for (Product item : temp) {
                        String convert = Normalizer.normalize(item.getTitle(), Normalizer.Form.NFD);
                        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                        String khongDau = pattern.matcher(convert).replaceAll("");
                        if (khongDau.toLowerCase().contains(filterPattern)) {
                            ketQua.add(item);
                        }
                    }
                }
                Log.i("lp", " " + temp);
                FilterResults results = new FilterResults();
                results.values = ketQua;
                results.count = ketQua.size();
                Log.i("lp", " " + ketQua);
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.i("lp", "lol");
                if (temp.size() == results.count | results.count == 0 | results == null) {
                    search.setVisibility(View.GONE);
                } else {
                    search.setVisibility(View.VISIBLE);
                    products.clear();
                    products.addAll((List) results.values);
                    notifyDataSetChanged();
                }
            }
        };
    }

    private class HolderView extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price;
        TextView name;
        TextView sale;


        public HolderView(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_search);
            name = itemView.findViewById(R.id.name_search);
            sale = itemView.findViewById(R.id.sale_search);
            price = itemView.findViewById(R.id.price_search);

        }
    }
}
