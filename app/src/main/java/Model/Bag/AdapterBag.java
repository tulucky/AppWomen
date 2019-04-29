package Model.Bag;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

import Model.BrandProductDetal.BottomAdapImage;
import Model.BrandProductDetal.BottomAdapSize;
import Model.BrandProductDetal.OrderP;
import Model.BrandProductDetal.ViewPagerProduct;
import Model.Product;
import Model.RetrofitO;
import Model.Service;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.ProductDetail;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBag extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<OrderP> data;
    List<Product> dataProduct;
    ConstraintLayout updateProduct;
    TextView price;
    TextView originPrice;
    TextView name;
    RecyclerView recColor;
    RecyclerView size;
    TextView number;
    ImageView imageUpdateSheet;
    TextView sizeDes;
    ImageView sub;
    ImageView plus;
    ImageView subUp;
    ImageView plusUp;
    TextView update;
    ConstraintLayout checkOut;


    public AdapterBag(Context mcontext, List<OrderP> data, ConstraintLayout updateProduct, ConstraintLayout check) {
        this.mcontext = mcontext;
        this.data = data;
        this.updateProduct = updateProduct;
        this.checkOut = check;
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
        holder.amount.setText("" + data.get(i).getNumber());
        Log.i("mn", "" + data.get(i).getIdProductb());
        int id = data.get(i).getIdProductb();
        if (data.get(i).getNumber() == 1) {
            holder.sub.setVisibility(View.GONE);
            holder.delete.setVisibility(View.VISIBLE);
        } else {
            holder.sub.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.GONE);
        }
        final ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
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
                if (data.get(i).getNumber() == 1) {
                    holder.amount.setText(" " + data.get(i).getNumber());
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = data.get(i).getNumber();
                k++;
                data.get(i).setNumber(k);
                notifyDataSetChanged();
                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<List<OrderP>> call = serviceApi.upNumber(data.get(i).getId(), k);
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
                int k = data.get(i).getNumber();
                k--;
                data.get(i).setNumber(k);
                notifyDataSetChanged();
                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<List<OrderP>> call = serviceApi.upNumber(data.get(i).getId(), k);
                call.enqueue(new Callback<List<OrderP>>() {
                    @Override
                    public void onResponse(Call<List<OrderP>> call, Response<List<OrderP>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<OrderP>> call, Throwable t) {

                    }
                });
                notifyDataSetChanged();
            }
        });
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<List<Product>> call = serviceApi.getaProduct(data.get(i).getIdProductb());
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(final Call<List<Product>> call, Response<List<Product>> response) {
                        checkOut.setVisibility(View.GONE);
                        price = updateProduct.findViewById(R.id.price_b);
                        originPrice = updateProduct.findViewById(R.id.origin_price_b);
                        name = updateProduct.findViewById(R.id.description_b);
                        recColor = updateProduct.findViewById(R.id.rec_color);
                        size = updateProduct.findViewById(R.id.size_rec);
                        number = updateProduct.findViewById(R.id.amount);
                        sizeDes = updateProduct.findViewById(R.id.size_des);
                        imageUpdateSheet = updateProduct.findViewById(R.id.image_botsheet);
                        subUp = updateProduct.findViewById(R.id.sub_up);
                        plusUp = updateProduct.findViewById(R.id.plus_up);
                        update = updateProduct.findViewById(R.id.bottom_update);
                        price.setText(response.body().get(0).getName());
                        originPrice.setText(response.body().get(0).getOriginprice());
                        name.setText(response.body().get(0).getDestile());
                        number.setText("" + data.get(i).getNumber());
                        getImageProduct(response.body().get(0).getId(), data.get(i).getImagebag(), data.get(i));
                        getImageSize(response.body().get(0).getId(), data.get(i));
                        subUp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int k = data.get(i).getNumber();
                                if (k == 1) {
                                    number.setText("" + k);
                                    data.get(i).setNumber(k);
                                } else {
                                    k--;
                                    number.setText("" + k);
                                    data.get(i).setNumber(k);
                                }
                            }
                        });
                        //ta gan 1 listener cho cai view  tu do cai view co the nhan dc su kien
                        // do da dc gan listener
                        // (du no o trong ham bat su kien cua thang khac
                        //nhung khi tan gan cho no 1 listener no ko phu thuoc vao thang kia
                        //chang han nhu imagedit
                        plusUp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int k = data.get(i).getNumber();
                                k++;
                                number.setText("" + k);
                                data.get(i).setNumber(k);
                            }
                        });
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mcontext, "hhahah", Toast.LENGTH_LONG).show();
                                ServiceApi serviceApi1 = RetrofitO.getmRetrofit().create(ServiceApi.class);
                                Log.i("ji", "" + data.get(i).getId() + " " + data.get(i).getImagebag() + " " + data.get(i).getSizebag() + " " +
                                        data.get(i).getNumber());
                                Call<List<OrderP>> call = serviceApi1.updateOrderProduct(data.get(i).getId(), data.get(i).getImagebag(),
                                        data.get(i).getSizebag(), data.get(i).getNumber()
                                        //update by id of the orders
                                );
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

                    private void getImageSize(int id, final OrderP orderP) {
                        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                        Call<List<String>> call = serviceApi.getaSize(id);
                        call.enqueue(new Callback<List<String>>() {
                            @Override
                            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                                int slected = response.body().indexOf(data.get(i).getSizebag());
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 5);
                                BottomAdapSize adapSize = new BottomAdapSize(mcontext, response.body(), orderP, sizeDes, slected);
                                size.setLayoutManager(gridLayoutManager);
                                size.setAdapter(adapSize);

                            }

                            @Override
                            public void onFailure(Call<List<String>> call, Throwable t) {

                            }
                        });


                    }

                    private void getImageProduct(int id, final String image, final OrderP orderP) {
                        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
                        Call<List<String>> call = service.getColor(id);
                        call.enqueue(new Callback<List<String>>() {
                            @Override
                            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                                Log.i("pd", " " + response.body());
                                int slected = response.body().indexOf(image);
                                Glide.with(mcontext)
                                        .load(RetrofitO.url + image)
                                        .into(imageUpdateSheet);
                                BottomAdapImage adapImage = new BottomAdapImage(mcontext, response.body(), imageUpdateSheet, orderP, slected);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 5);
                                recColor.setLayoutManager(gridLayoutManager);
                                recColor.setAdapter(adapImage);

                            }

                            @Override
                            public void onFailure(Call<List<String>> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
                Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.test);
                updateProduct.startAnimation(animation);
                updateProduct.setVisibility(View.VISIBLE);
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
