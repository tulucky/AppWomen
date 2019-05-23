package Model.Brand;

import android.content.Context;
import android.content.Intent;
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

import Model.Product;
import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.ProducBrandtActivity;
import lucky.dev.tu.devandroid.ProductDetail;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<BrandModel> listBrand;
    View view;

    public BrandAdapter(Context mcontext, List<BrandModel> listBrand) {
        this.mcontext = mcontext;
        this.listBrand = listBrand;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater myLayouInflater = LayoutInflater.from(mcontext);
        view = myLayouInflater.inflate(R.layout.item_brand,viewGroup,false);
        return new MyHolderBrand(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final MyHolderBrand holder = (MyHolderBrand) viewHolder;
        Glide.with(mcontext)
                .load(RetrofitO.url + listBrand.get(i).getImage())
                .into(holder.imageBrand);
        Glide.with(mcontext)
                .load(RetrofitO.url + listBrand.get(i).getLogo())
                .into(holder.logoBrand);

        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = serviceApi.brandImages(listBrand.get(i).getId());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, final Response<List<Product>> response) {
                Log.i("ko", " " + response.body());
                Glide.with(mcontext)
                        .load(RetrofitO.url + response.body().get(0).getImage())
                        .into(holder.item1);
                holder.price1.setText(response.body().get(0).getNameB() + "-" + response.body().get(0).getOriginprice() + "$");
                holder.sale1.setText(response.body().get(0).getSale());
                Glide.with(mcontext)
                        .load(RetrofitO.url + response.body().get(1).getImage())
                        .into(holder.item2);
                holder.price2.setText(response.body().get(1).getNameB() + "-" + response.body().get(1).getOriginprice() + "$");
                holder.sale2.setText(response.body().get(1).getSale());
                Glide.with(mcontext)
                        .load(RetrofitO.url + response.body().get(2).getImage())
                        .into(holder.item3);
                holder.price3.setText(response.body().get(2).getNameB() + "-" + response.body().get(2).getOriginprice() + "$");
                holder.sale3.setText(response.body().get(2).getSale());

                holder.item1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mcontext, ProductDetail.class);
                        intent.putExtra("id", response.body().get(0).getId());
                        intent.putExtra("image", response.body().get(0).getImage());
                        mcontext.startActivity(intent);
                    }
                });
                holder.item2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mcontext, ProductDetail.class);
                        intent.putExtra("id", response.body().get(1).getId());
                        intent.putExtra("image", response.body().get(1).getImage());
                        mcontext.startActivity(intent);
                    }
                });
                holder.item3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mcontext, ProductDetail.class);
                        intent.putExtra("id", response.body().get(2).getId());
                        intent.putExtra("image", response.body().get(2).getImage());
                        mcontext.startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        holder.imageBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ProducBrandtActivity.class);
                intent.putExtra("id", listBrand.get(i).getId());
                intent.putExtra("brandimage", listBrand.get(i).getImage());
                intent.putExtra("logo", listBrand.get(i).getLogo());
                intent.putExtra("nameb", listBrand.get(i).getNameb());
                intent.putExtra("description", listBrand.get(i).getDesbrand());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBrand.size();
    }

    private class MyHolderBrand extends RecyclerView.ViewHolder {
        ImageView imageBrand;
        ImageView logoBrand;
        ImageView item1;
        TextView price1;
        TextView sale1;
        ImageView item2;
        TextView price2;
        TextView sale2;
        ImageView item3;
        TextView price3;
        TextView sale3;
        public MyHolderBrand(View view) {
            super(view);
            imageBrand = view.findViewById(R.id.image_brand);
            logoBrand = view.findViewById(R.id.logo_brand);
            item1 = view.findViewById(R.id.item1);
            price1= view.findViewById(R.id.price1);
            sale1 = view.findViewById(R.id.sale1);
            item2 = view.findViewById(R.id.item2);
            price2= view.findViewById(R.id.price2);
            sale2 = view.findViewById(R.id.sale2);
            item3 = view.findViewById(R.id.item3);
            price3= view.findViewById(R.id.price3);
            sale3 = view.findViewById(R.id.sale3);
        }
    }
}
