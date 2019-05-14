package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import Model.Account.Account;
import Model.Resum.Resum;
import Model.Bag.Bag;
import Model.Brand.Brand;
import Model.Home.Home;
import Model.RetrofitO;
import Model.ServiceApi;
import Model.SoLuong;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView introhome;
    ImageView brand;
    ImageView bag;
    ImageView account;
    TextView tHome;
    TextView tBrand;
    TextView tBag;
    TextView tAccount;
    ImageView love;
    ImageView search;
    ConstraintLayout actionBar;
    public TextView number;
    String name;
    Brand brandM;
    Bag bagM;
    Account accountM;
    Resum resum;
    Home home;
    Fragment active;
    public NestedScrollView nestedScrollView;
    //thay doi file php ko can chay lai app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        introhome = findViewById(R.id.home);
        brand = findViewById(R.id.brand);
        bag = findViewById(R.id.bag);
        account = findViewById(R.id.avatar);
        tHome = findViewById(R.id.text_home);
        tBrand = findViewById(R.id.text_crown);
        tBag = findViewById(R.id.text_bag);
        tAccount = findViewById(R.id.text_avatar);
        introhome.setOnClickListener(this);
        brand.setOnClickListener(this);
        bag.setOnClickListener(this);
        account.setOnClickListener(this);
        introhome.setImageResource(R.drawable.home1);
        actionBar = findViewById(R.id.action_bar);
        search = findViewById(R.id.search_a);
        number = findViewById(R.id.text_number);
        number.setVisibility(GONE);
        brandM = new Brand();
        bagM = new Bag();
        accountM = new Account();
        resum = new Resum();
        home = new Home();
        SharedPreferences preferences = this.getSharedPreferences("Accout", this.MODE_PRIVATE);
        name = preferences.getString("idName", "khong");
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<SoLuong>> call = serviceApi.getSoLuong(name);
        call.enqueue(new Callback<List<SoLuong>>() {
            @Override
            public void onResponse(Call<List<SoLuong>> call, retrofit2.Response<List<SoLuong>> response) {
                if (response.body().get(0).getSoLuong() == 0) {
                    number.setVisibility(GONE);
                } else {
                    number.setVisibility(VISIBLE);
                    number.setText("" + response.body().get(0).getSoLuong());
                }
            }

            @Override
            public void onFailure(Call<List<SoLuong>> call, Throwable t) {

            }
        });
        tHome.setTextColor(getResources().getColor(R.color.pink));
        love = findViewById(R.id.loved);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Wishlish.class);
                MainActivity.this.startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Intent intent = getIntent();
        int k = intent.getIntExtra("ide", 0);
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (k) {
            case 2:
                actionBar.setVisibility(View.GONE);
                bag.setImageResource(R.drawable.bag1);
                tBag.setTextColor(getResources().getColor(R.color.pink));
                introhome.setImageResource(R.drawable.home);
                tHome.setTextColor(getResources().getColor(R.color.black));
                brand.setImageResource(R.drawable.crow);
                tBrand.setTextColor(getResources().getColor(R.color.black));
                account.setImageResource(R.drawable.avatar);
                tAccount.setTextColor(getResources().getColor(R.color.black));
                fragmentManager.beginTransaction().replace(R.id.container, bagM).commit();
                active = bagM;
                break;
            case 3:
                actionBar.setVisibility(View.GONE);
                bag.setImageResource(R.drawable.bag);
                tBag.setTextColor(getResources().getColor(R.color.black));
                introhome.setImageResource(R.drawable.home);
                tHome.setTextColor(getResources().getColor(R.color.black));
                brand.setImageResource(R.drawable.crow);
                tBrand.setTextColor(getResources().getColor(R.color.black));
                account.setImageResource(R.drawable.avatar1);
                tAccount.setTextColor(getResources().getColor(R.color.pink));
                fragmentManager.beginTransaction().replace(R.id.container, resum).commit();
                break;
            case 5:
                if (accountM.isHidden()) {
                    fragmentManager.beginTransaction().hide(active).show(accountM).commit();
                    active = accountM;
                    actionBar.setVisibility(View.GONE);
                    bag.setImageResource(R.drawable.bag);
                    tBag.setTextColor(getResources().getColor(R.color.black));
                    introhome.setImageResource(R.drawable.home);
                    tHome.setTextColor(getResources().getColor(R.color.black));
                    brand.setImageResource(R.drawable.crow);
                    active = accountM;
                    tBrand.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar1);
                    tAccount.setTextColor(getResources().getColor(R.color.pink));
                }
                break;
            default:
                active = home;
                FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                transaction3.add(R.id.container, home);
                transaction3.commit();
        }


    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<SoLuong>> call = serviceApi.getSoLuong(name);
        call.enqueue(new Callback<List<SoLuong>>() {
            @Override
            public void onResponse(Call<List<SoLuong>> call, retrofit2.Response<List<SoLuong>> response) {
                if (response.body().get(0).getSoLuong() == 0) {
                    number.setVisibility(GONE);
                } else {
                    number.setVisibility(VISIBLE);
                    number.setText("" + response.body().get(0).getSoLuong());
                }
            }

            @Override
            public void onFailure(Call<List<SoLuong>> call, Throwable t) {

            }
        });
        switch (v.getId()) {
            case R.id.brand:
                if (brandM.isHidden()) {
                    fragmentManager.beginTransaction().hide(active).show(brandM).commit();
                    active = brandM;
                    actionBar.setVisibility(View.VISIBLE);
                    brand.setImageResource(R.drawable.crow1);
                    tBrand.setTextColor(getResources().getColor(R.color.pink));
                    introhome.setImageResource(R.drawable.home);
                    tHome.setTextColor(getResources().getColor(R.color.black));
                    bag.setImageResource(R.drawable.bag);
                    active = brandM;
                    tBag.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar);
                    tAccount.setTextColor(getResources().getColor(R.color.black));
                } else if (!brandM.isAdded()) {
                    fragmentManager.beginTransaction().hide(active).commit();
                    actionBar.setVisibility(View.VISIBLE);
                    brand.setImageResource(R.drawable.crow1);
                    tBrand.setTextColor(getResources().getColor(R.color.pink));
                    introhome.setImageResource(R.drawable.home);
                    tHome.setTextColor(getResources().getColor(R.color.black));
                    bag.setImageResource(R.drawable.bag);
                    active = brandM;
                    tBag.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar);
                    tAccount.setTextColor(getResources().getColor(R.color.black));
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.container, brandM);
                    transaction.commit();
                }
                break;
            case R.id.bag:
                if (bagM.isHidden()) {
                    fragmentManager.beginTransaction().hide(active).show(bagM).commit();
                    //chu y active phai o sau dong nay khi do frament da dc them vao
                    active = bagM;
                    actionBar.setVisibility(View.GONE);
                    bag.setImageResource(R.drawable.bag1);
                    tBag.setTextColor(getResources().getColor(R.color.pink));
                    introhome.setImageResource(R.drawable.home);
                    tHome.setTextColor(getResources().getColor(R.color.black));
                    brand.setImageResource(R.drawable.crow);
                    active = bagM;
                    tBrand.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar);
                    tAccount.setTextColor(getResources().getColor(R.color.black));
                } else if (!bagM.isAdded()) {
                    fragmentManager.beginTransaction().hide(active).commit();
                    actionBar.setVisibility(View.GONE);
                    bag.setImageResource(R.drawable.bag1);
                    tBag.setTextColor(getResources().getColor(R.color.pink));
                    introhome.setImageResource(R.drawable.home);
                    tHome.setTextColor(getResources().getColor(R.color.black));
                    brand.setImageResource(R.drawable.crow);
                    active = bagM;
                    tBrand.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar);
                    tAccount.setTextColor(getResources().getColor(R.color.black));
                    FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                    transaction1.add(R.id.container, bagM);
                    transaction1.commit();
                }
                break;
            case R.id.avatar:
                SharedPreferences preferences = this.getSharedPreferences("Accout", this.MODE_PRIVATE);
                name = preferences.getString("idName", "khong");
                if (name.equals("khong")) {
                    if (accountM.isHidden()) {
                        fragmentManager.beginTransaction().hide(active).show(accountM).commit();
                        active = accountM;
                        actionBar.setVisibility(View.GONE);
                        bag.setImageResource(R.drawable.bag);
                        tBag.setTextColor(getResources().getColor(R.color.black));
                        introhome.setImageResource(R.drawable.home);
                        tHome.setTextColor(getResources().getColor(R.color.black));
                        brand.setImageResource(R.drawable.crow);
                        active = accountM;
                        tBrand.setTextColor(getResources().getColor(R.color.black));
                        account.setImageResource(R.drawable.avatar1);
                        tAccount.setTextColor(getResources().getColor(R.color.pink));
                    } else if (!accountM.isAdded()) {
                        fragmentManager.beginTransaction().hide(active).commit();
                        actionBar.setVisibility(View.GONE);
                        bag.setImageResource(R.drawable.bag);
                        tBag.setTextColor(getResources().getColor(R.color.black));
                        introhome.setImageResource(R.drawable.home);
                        tHome.setTextColor(getResources().getColor(R.color.black));
                        brand.setImageResource(R.drawable.crow);
                        active = accountM;
                        tBrand.setTextColor(getResources().getColor(R.color.black));
                        account.setImageResource(R.drawable.avatar1);
                        tAccount.setTextColor(getResources().getColor(R.color.pink));
                        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                        transaction2.add(R.id.container, accountM);
                        transaction2.commit();
                    }
                } else {
                    if (resum.isHidden()) {
                        fragmentManager.beginTransaction().hide(active).show(resum).commit();
                        active = resum;
                        actionBar.setVisibility(View.GONE);
                        bag.setImageResource(R.drawable.bag);
                        tBag.setTextColor(getResources().getColor(R.color.black));
                        introhome.setImageResource(R.drawable.home);
                        tHome.setTextColor(getResources().getColor(R.color.black));
                        brand.setImageResource(R.drawable.crow);
                        active = resum;
                        tBrand.setTextColor(getResources().getColor(R.color.black));
                        account.setImageResource(R.drawable.avatar1);
                        tAccount.setTextColor(getResources().getColor(R.color.pink));
                    } else if (!resum.isAdded()) {
                        fragmentManager.beginTransaction().hide(active).commit();
                    actionBar.setVisibility(View.GONE);
                    bag.setImageResource(R.drawable.bag);
                    tBag.setTextColor(getResources().getColor(R.color.black));
                        introhome.setImageResource(R.drawable.home);
                    tHome.setTextColor(getResources().getColor(R.color.black));
                    brand.setImageResource(R.drawable.crow);
                        active = resum;
                    tBrand.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar1);
                    tAccount.setTextColor(getResources().getColor(R.color.pink));
                    FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                        transaction2.add(R.id.container, resum);
                        transaction2.commit();
                    }
                }
                    break;

            case R.id.home:
                if (home.isHidden()) {
                    fragmentManager.beginTransaction().hide(active).show(home).commit();
                    active = home;
                    actionBar.setVisibility(View.VISIBLE);
                    bag.setImageResource(R.drawable.bag);
                    tBag.setTextColor(getResources().getColor(R.color.black));
                    introhome.setImageResource(R.drawable.home1);
                    tHome.setTextColor(getResources().getColor(R.color.pink));
                    brand.setImageResource(R.drawable.crow);
                    tBrand.setTextColor(getResources().getColor(R.color.black));
                    account.setImageResource(R.drawable.avatar);
                    tAccount.setTextColor(getResources().getColor(R.color.black));
                }
                break;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (findViewById(R.id.recy_main) != null) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.recy_main);
            getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
        }

        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<SoLuong>> call = serviceApi.getSoLuong(name);
        call.enqueue(new Callback<List<SoLuong>>() {
            @Override
            public void onResponse(Call<List<SoLuong>> call, retrofit2.Response<List<SoLuong>> response) {
                if (response.body().get(0).getSoLuong() == 0) {
                    number.setVisibility(GONE);
                } else {
                    number.setVisibility(VISIBLE);
                    number.setText("" + response.body().get(0).getSoLuong());
                }
            }

            @Override
            public void onFailure(Call<List<SoLuong>> call, Throwable t) {

            }
        });
    }
}
