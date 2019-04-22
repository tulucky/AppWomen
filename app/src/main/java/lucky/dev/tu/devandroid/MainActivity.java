package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import Model.Account.Account;
import Model.Bag.Bag;
import Model.Brand.Brand;
import Model.Home.Home;
import Model.State;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView home;
    ImageView brand;
    ImageView bag;
    ImageView account;
    TextView tHome;
    TextView tBrand;
    TextView tBag;
    TextView tAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        home = findViewById(R.id.home);
        brand = findViewById(R.id.brand);
        bag = findViewById(R.id.bag);
        account = findViewById(R.id.avatar);
        tHome = findViewById(R.id.text_home);
        tBrand = findViewById(R.id.text_crown);
        tBag = findViewById(R.id.text_bag);
        tAccount = findViewById(R.id.text_avatar);
        home.setOnClickListener(this);
        brand.setOnClickListener(this);
        bag.setOnClickListener(this);
        account.setOnClickListener(this);
        home.setImageResource(R.drawable.home1);
        tHome.setTextColor(getResources().getColor(R.color.colorAccent));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Home home = new Home();
        transaction.add(R.id.container, home);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (v.getId()) {
            case R.id.brand:
                brand.setImageResource(R.drawable.crow1);
                tBrand.setTextColor(getResources().getColor(R.color.colorAccent));
                home.setImageResource(R.drawable.home);
                tHome.setTextColor(getResources().getColor(R.color.black));
                bag.setImageResource(R.drawable.bag);
                tBag.setTextColor(getResources().getColor(R.color.black));
                account.setImageResource(R.drawable.avatar);
                tAccount.setTextColor(getResources().getColor(R.color.black));
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Brand brandM = new Brand();
                transaction.replace(R.id.container, brandM);
                transaction.commit();
                break;
            case R.id.bag:
                bag.setImageResource(R.drawable.bag1);
                tBag.setTextColor(getResources().getColor(R.color.colorAccent));
                home.setImageResource(R.drawable.home);
                tHome.setTextColor(getResources().getColor(R.color.black));
                brand.setImageResource(R.drawable.crow);
                tBrand.setTextColor(getResources().getColor(R.color.black));
                account.setImageResource(R.drawable.avatar);
                tAccount.setTextColor(getResources().getColor(R.color.black));
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                Bag bagM = new Bag();
                transaction1.replace(R.id.container, bagM);
                transaction1.commit();
                break;
            case R.id.avatar:
                bag.setImageResource(R.drawable.bag);
                tBag.setTextColor(getResources().getColor(R.color.black));
                home.setImageResource(R.drawable.home);
                tHome.setTextColor(getResources().getColor(R.color.black));
                brand.setImageResource(R.drawable.crow);
                tBrand.setTextColor(getResources().getColor(R.color.black));
                account.setImageResource(R.drawable.avatar1);
                tAccount.setTextColor(getResources().getColor(R.color.colorAccent));
                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                Account accountM = new Account();
                transaction2.replace(R.id.container, accountM);
                transaction2.commit();
                break;
            case R.id.home:
                bag.setImageResource(R.drawable.bag);
                tBag.setTextColor(getResources().getColor(R.color.black));
                home.setImageResource(R.drawable.home1);
                tHome.setTextColor(getResources().getColor(R.color.colorAccent));
                brand.setImageResource(R.drawable.crow);
                tBrand.setTextColor(getResources().getColor(R.color.black));
                account.setImageResource(R.drawable.avatar);
                tAccount.setTextColor(getResources().getColor(R.color.black));
                FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                Home home = new Home();
                transaction3.replace(R.id.container, home);
                transaction3.commit();
                break;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.recy_main);
        getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
    }
}
