package Model.Bag;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.BrandProductDetal.BottomAdapImage;
import Model.BrandProductDetal.BottomAdapSize;
import Model.BrandProductDetal.OrderP;
import Model.Product;
import Model.RetrofitO;
import Model.ServiceApi;
import Model.SoLuong;
import Model.State;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBag extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    private List<OrderP> data;
    private List<Product> dataProduct;
    private ConstraintLayout updateProduct;
    TextView price;
    private TextView originPrice;
    private TextView name;
    private RecyclerView recColor;
    private RecyclerView size;
    private TextView number;
    private ImageView imageUpdateSheet;
    private TextView sizeDes;
    private ImageView subUp;
    private ImageView plusUp;
    private TextView update;
    private ConstraintLayout checkOut;
    TextView priceCheck;
    String nameUser;
    int s;
    float tong;
    int ide;
    int cout = 0;
    int dem;
    public int tongSoLuong() {
        s = 0;
        for (int i = 0; i < data.size(); i++) {
            s = s + data.get(i).getNumber();
        }
        return s;
    }

    public float tongGia() {
        tong = 0;
        for (int i = 0; i < data.size(); i++) {
            Log.i("poi", "tongGia1: " + data.get(i).getNumber() + " " + data.get(i).getPrice());
            tong = tong + data.get(i).getNumber() * data.get(i).getPrice();
        }
        return tong;
    }
  /*  public float tongGia2() {
        tong = 0;
        for (int i = 0; i < data.size(); i++) {
            Log.i("poi", "tongGia1: " + data.get(i).getNumber() + " " + data.get(i).getPrice());
            tong = tong + data.get(i).getNumber() * data.get(i).getPrice();
        }
        return tong;
    }*/
    /*public void capNhatGia(){
        final ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<SoLuong>> call2 = serviceApi.getPriceCheck(nameUser);
        call2.enqueue(new Callback<List<SoLuong>>() {
            @Override
            public void onResponse(Call<List<SoLuong>> call, Response<List<SoLuong>> response) {
                Log.i("mop", "" + response.body().get(0).getGia());
                priceCheck.setText("" + response.body().get(0).getGia() + "$");
                //get price
            }
            @Override
            public void onFailure(Call<List<SoLuong>> call, Throwable t) {
            }
        });

    }*/

    public AdapterBag(Context mcontext, List<OrderP> data, ConstraintLayout updateProduct, ConstraintLayout check, TextView priceCheck) {
        this.mcontext = mcontext;
        Log.i("lp", " " + mcontext);
        this.data = data;
        this.updateProduct = updateProduct;
        this.checkOut = check;
        this.priceCheck = priceCheck;
        if (mcontext != null) {
            SharedPreferences sharedPreferences = mcontext.getSharedPreferences("Accout", Context.MODE_PRIVATE);
            nameUser = sharedPreferences.getString("idName", "khong");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_orders, viewGroup, false);
        return new ViewHolderBag(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.i("poi", "Gia1: " + data.get(i).getPrice());
        dem = i;
        final SoLuong soLuong = new SoLuong();
        //capNhatGia();
        final ViewHolderBag holder = (ViewHolderBag) viewHolder;
        Glide.with(mcontext).load(RetrofitO.url + data.get(i).getImagebag()).into(holder.imageBag);
        holder.textEdit.setText(data.get(i).getSizebag());
        //khi nguoi dung click + hoac tru qua nhanh dan den viec chua kip inset xong(van con dang isert) vao host free da bi xoa mat id gay ra
        //loi outofindex .giai quet tao id chung
        holder.amount.setText("" + data.get(i).getNumber());
        final int id = data.get(i).getIdProductb();
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
            //get a product
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                dataProduct = response.body();
                Log.i("pi", " " + dataProduct.get(0).getPrice());
                holder.priceBag.setText(dataProduct.get(0).getName());
                holder.originPriBag.setText(dataProduct.get(0).getOriginprice());
                holder.originPriBag.setPaintFlags(holder.originPriBag.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
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
                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<Void> call = serviceApi.deleteOrder(data.get(i).getId());
                Log.i("cc", "onResponse:lololo ");
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("cc", "onResponse: " + response);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("cc", "onRes: " + t.getMessage());
                    }
                });
                Log.i("pp1", "onCl: " + i);
                data.remove(i);
                notifyDataSetChanged();
                ((MainActivity) mcontext).number.setText("" + tongSoLuong());
                if (data.size() == 0) {
                    priceCheck.setText("" + 0.0 + "$");
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = data.get(i).getNumber();
                k++;
                if (k > 1) {
                    holder.delete.setVisibility(View.GONE);
                    holder.sub.setVisibility(View.VISIBLE);
                }
                holder.amount.setText("" + k);
                data.get(i).setNumber(k);
                //dung tao ide de ko truyen data.get(i).get(id) vao asytask nguoi dung xoa nhanh trong khi insert vao db se gay ra loi vi no mat id.
                ide = data.get(i).getId();
                ((MainActivity) mcontext).number.setText("" + tongSoLuong());
                priceCheck.setText("" + tongGia() + "$");
                new Asyn().execute(ide, k);

            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = data.get(i).getNumber();
                k--;
                if (k == 1) {
                    k = 1;
                    holder.sub.setVisibility(View.GONE);
                    holder.delete.setVisibility(View.VISIBLE);
                }
                holder.amount.setText("" + k);
                data.get(i).setNumber(k);
                ide = data.get(i).getId();
                ((MainActivity) mcontext).number.setText("" + tongSoLuong());
                priceCheck.setText("" + tongGia() + "$");
                new Asyn().execute(ide, k);
            }
        });
        priceCheck.setText("" + tongGia() + "$");
        Log.i("pop", "onBindViewHolder: " + tongGia());
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                Call<List<Product>> call = serviceApi.getaProduct(data.get(i).getIdProductb());
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(final Call<List<Product>> call, Response<List<Product>> response) {
                        checkOut.setVisibility(View.GONE);
                        //checkout an do do khi cai nay an set checkout víible
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
                        originPrice.setPaintFlags(originPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
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
                                ((MainActivity) mcontext).number.setText("" + tongSoLuong());
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
                                final KProgressHUD kProgressHUD = KProgressHUD.create(mcontext)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setCancellable(true)
                                        .setBackgroundColor(Color.GRAY)
                                        .setAnimationSpeed(2)
                                        .setSize(100, 100)
                                        .setDimAmount(0.5f)
                                        .show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        kProgressHUD.dismiss();
                                        checkOut.setVisibility(View.VISIBLE);
                                        updateProduct.setVisibility(View.GONE);
                                        notifyDataSetChanged();
                                    }
                                }, 1500);

                            }
                        });
                    }

                    private void getImageSize(int id, final OrderP orderP) {
                        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                        Call<List<String>> call = serviceApi.getaSize(id);
                        call.enqueue(new Callback<List<String>>() {
                            @Override
                            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                                if (response.isSuccessful()) {
                                    int slected = response.body().indexOf(data.get(i).getSizebag());
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 5);
                                    BottomAdapSize adapSize = new BottomAdapSize(mcontext, response.body(), orderP, sizeDes, slected);
                                    size.setLayoutManager(gridLayoutManager);
                                    size.setAdapter(adapSize);
                                }

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

  /*  public void capNhat(int i, int k){
        SharedPreferences sharedPref = mcontext.getSharedPreferences(
                "Orders", Context.MODE_PRIVATE);
        float gia= sharedPref.getFloat(""+data.get(i).getId(),0);
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<Void> call = serviceApi.upNumberPrice(data.get(i).getId(),k,gia);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }*/

    class Asyn extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
            Call<Void> call = serviceApi.upNumberPrice(integers[0], integers[1]);
            try {
                Response<Void> response = call.execute();
                Log.i("dd", "doInBackground: " + response);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
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
