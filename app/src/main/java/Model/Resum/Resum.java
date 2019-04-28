package Model.Resum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Model.Resum.OrderedAda;
import Model.Resum.OrderedP;
import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Resum extends Fragment {
    RecyclerView productLists;
    TextView nickName;
    ImageView setting;

    public Resum() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.resum, container, false);
        productLists = view.findViewById(R.id.ordered_rec);
        nickName = view.findViewById(R.id.nick_name);
        setting = view.findViewById(R.id.setting);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Accout"
                , getActivity().MODE_PRIVATE);
        String name = sharedPref.getString("idName", "khong");
        nickName.setText(name);
        final PopupMenu popupMenu = new PopupMenu(getActivity(), setting);
        popupMenu.inflate(R.menu.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.change_phone:
                                break;
                            case R.id.change_pas:
                                break;
                            case R.id.sign_out:
                                SharedPreferences sharedPref = getActivity().getSharedPreferences("Accout", getActivity().MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.remove("idName");
                                editor.apply();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("ide", 5);
                                getActivity().startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<OrderedP>> call = serviceApi.orderedProducts(name);
        call.enqueue(new Callback<List<OrderedP>>() {
            @Override
            public void onResponse(Call<List<OrderedP>> call, Response<List<OrderedP>> response) {
                Log.i("ll", "lol");
                productLists.setHasFixedSize(true);
                productLists.setNestedScrollingEnabled(false);
                productLists.setLayoutManager(new LinearLayoutManager(getActivity()));
                OrderedAda orderedAda = new OrderedAda(getActivity(), response.body());
                productLists.setAdapter(orderedAda);

            }

            @Override
            public void onFailure(Call<List<OrderedP>> call, Throwable t) {
                Log.i("ss", t.getMessage());

            }
        });
        return view;
    }
}
