package Model.Bag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Model.BrandProductDetal.OrderP;
import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.Login;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bag extends Fragment {
    List<OrderP> daTa;
    RecyclerView listOrder;
    ConstraintLayout updateProduct;
    ImageView exitBackground;
    ImageView cancel;
    TextView checkOut;
    ConstraintLayout layoutCheck;

    public Bag() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bag, container, false);
        listOrder = view.findViewById(R.id.list_order);
        updateProduct = view.findViewById(R.id.update_product);
        checkOut = view.findViewById(R.id.check_out);
        updateProduct.setVisibility(View.GONE);
        exitBackground = view.findViewById(R.id.exit_backgroud);
        cancel = view.findViewById(R.id.cancel_d);
        layoutCheck = view.findViewById(R.id.check);
        getListOrder();
        exitBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct.setVisibility(View.GONE);
            }
        });
        exitBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct.setVisibility(View.GONE);
                layoutCheck.setVisibility(View.VISIBLE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct.setVisibility(View.GONE);
                layoutCheck.setVisibility(View.VISIBLE);
            }
        });
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Accout"
                        , getActivity().MODE_PRIVATE);
                final String name = sharedPref.getString("idName", "khong");
                if (name.equals("khong")) {
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("idName");
                    editor.apply();
                    //doan xoa shared
                } else {
                    ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                    Call<List<OrderP>> call = serviceApi.getListOrder();
                    call.enqueue(new Callback<List<OrderP>>() {
                        @Override
                        public void onResponse(Call<List<OrderP>> call, Response<List<OrderP>> response) {

                            for (int i = 0; i < response.body().size(); i++) {
                                int idOrder = response.body().get(i).getId();
                                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                                Call<List<String>> callCheck = serviceApi.checkOut(name, idOrder, "checked");
                                callCheck.enqueue(new Callback<List<String>>() {
                                    @Override
                                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<List<String>> call, Throwable t) {

                                    }
                                });
                            }

                        }

                        @Override
                        public void onFailure(Call<List<OrderP>> call, Throwable t) {

                        }
                    });

                    Log.i("ll", name);
                }

            }
        });
        return view;

    }

    private void getListOrder() {
        final ServiceApi getOrders = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<OrderP>> callOrder = getOrders.getListOrder();
        callOrder.enqueue(new Callback<List<OrderP>>() {
            @Override
            public void onResponse(Call<List<OrderP>> call, retrofit2.Response<List<OrderP>> response) {
                Toast.makeText(getActivity(), " hhh", Toast.LENGTH_LONG).show();
                Log.i("hh", " " + response.body());
                daTa = response.body();
                listOrder.setNestedScrollingEnabled(false);
                AdapterBag adapterBag = new AdapterBag(getActivity(), daTa, updateProduct, layoutCheck);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                listOrder.setLayoutManager(linearLayoutManager);
                listOrder.setAdapter(adapterBag);

            }

            @Override
            public void onFailure(Call<List<OrderP>> call, Throwable t) {
                t.getMessage();

            }
        });
    }
}