package Model.Bag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

import Model.BrandProductDetal.OrderP;
import Model.Dialog;
import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.Login;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.ProductDetail;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bag extends Fragment implements Dialog.NoticeDialogListener {
    List<OrderP> daTa;
    RecyclerView listOrder;
    ConstraintLayout updateProduct;
    ImageView exitBackground;
    ImageView cancel;
    TextView checkOut;
    TextView priceCheck;
    ConstraintLayout layoutCheck;
    ImageView bagBag;
    TextView textBag;
    DialogFragment dialog;

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
        priceCheck = view.findViewById(R.id.price_check);
        bagBag = view.findViewById(R.id.bagbag);
        textBag = view.findViewById(R.id.text_bagbag);
        bagBag.setVisibility(View.GONE);
        textBag.setVisibility(View.GONE);
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
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Accout", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("idName", "khong");
                if (name.equals("khong")) {
                    Toast.makeText(getActivity(), "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    dialog = new Dialog();
                    dialog.setTargetFragment(Bag.this, 1);
                    dialog.show(getActivity().getSupportFragmentManager(), "chon di");
                }
            }
        });
        return view;

    }

    private void getListOrder() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Accout", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("idName", "khong");
        final ServiceApi getOrders = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<OrderP>> callOrder = getOrders.getListOrder(name);
        callOrder.enqueue(new Callback<List<OrderP>>() {
            @Override
            public void onResponse(Call<List<OrderP>> call, retrofit2.Response<List<OrderP>> response) {
                if (response.body().size() == 0) {
                    bagBag.setVisibility(View.VISIBLE);
                    textBag.setVisibility(View.VISIBLE);
                }
                Log.i("hh", " " + response.body());
                daTa = response.body();
                listOrder.setNestedScrollingEnabled(false);
                AdapterBag adapterBag = new AdapterBag(getActivity(), daTa, updateProduct, layoutCheck, priceCheck);
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.dismiss();

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Accout"
                , Context.MODE_PRIVATE);
        final String name = sharedPref.getString("idName", "khong");
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<OrderP>> call = serviceApi.getListOrder(name);
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
                    if (i == response.body().size() - 1) {
                        final KProgressHUD kProgressHUD = KProgressHUD.create(getActivity())
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
                                Log.i("ooo", name);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("ide", 3);
                                getActivity().startActivity(intent);
                                kProgressHUD.dismiss();
                            }
                        }, 2000);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OrderP>> call, Throwable t) {
                Log.i("ooo", t.getMessage());
            }
        });
    }
}
