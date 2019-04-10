package Model.Bag;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import Model.BrandProductDetal.OrderP;
import Model.Product;
import Model.RetrofitO;
import Model.Service;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBag extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<OrderP> data;
    List<Product> dataProduct;
    int number = 1;

    public AdapterBag(Context mcontext, List<OrderP> data) {
        this.mcontext = mcontext;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_orders, viewGroup, false);
        return new ViewHolderBag(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolderBag holder = (ViewHolderBag) viewHolder;
        Glide.with(mcontext).load(RetrofitO.url + data.get(i).getImagebag()).into(holder.imageBag);
        holder.textEdit.setText(data.get(i).getSizebag());
        Log.i("mn", "" + data.get(i).getIdProductb());
        int id = data.get(i).getIdProductb();
        holder.sub.setVisibility(View.GONE);
        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
        final Call<List<Product>> call = service.getaProduct(id);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                dataProduct = response.body();
                holder.priceBag.setText(dataProduct.get(0).getName());
                holder.originPriBag.setText(dataProduct.get(0).getOriginprice());
                holder.sale.setText(dataProduct.get(0).getSale());
                holder.desBag.setText(dataProduct.get(0).getDestile());

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 1) {
                    holder.amount.setText(" " + number);
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                holder.delete.setVisibility(View.GONE);
                holder.sub.setVisibility(View.VISIBLE);
                holder.amount.setText(" " + number);
                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<List<OrderP>> call = serviceApi.upNumber(data.get(i).getId(), number);
                call.enqueue(new Callback<List<OrderP>>() {
                    @Override
                    public void onResponse(Call<List<OrderP>> call, Response<List<OrderP>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<OrderP>> call, Throwable t) {

                    }
                });
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                holder.amount.setText(" " + number);
                if (number == 1) {
                    holder.delete.setVisibility(View.VISIBLE);
                    holder.sub.setVisibility(View.GONE);
                }
                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<List<OrderP>> call = serviceApi.upNumber(data.get(i).getId(), number);
                call.enqueue(new Callback<List<OrderP>>() {
                    @Override
                    public void onResponse(Call<List<OrderP>> call, Response<List<OrderP>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<OrderP>> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ViewHolderBag extends RecyclerView.ViewHolder {
        ImageView imageBag;
        TextView priceBag;
        TextView originPriBag;
        TextView desBag;
        ImageView imageEdit;
        TextView textEdit;
        ImageView sub;
        ImageView plus;
        ImageView delete;
        TextView sale;
        TextView amount;

        public ViewHolderBag(View view) {
            super(view);
            imageBag = view.findViewById(R.id.image_bag);
            priceBag = view.findViewById(R.id.price_bag);
            originPriBag = view.findViewById(R.id.origin_pri_bag);
            desBag = view.findViewById(R.id.des_bag);
            imageEdit = view.findViewById(R.id.image_edit);
            textEdit = view.findViewById(R.id.text_edit);
            sub = view.findViewById(R.id.sub);
            plus = view.findViewById(R.id.plus);
            delete = view.findViewById(R.id.delete);
            sale = view.findViewById(R.id.sale_bag);
            amount = view.findViewById(R.id.amount);
        }
    }
}
