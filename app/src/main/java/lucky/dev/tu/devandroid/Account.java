package lucky.dev.tu.devandroid;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
//phai import ko no hieu la lop Viewper cua system

import Model.Account.Signin;
import Model.Account.Signup;
import Model.Account.Viewpager;
import me.didik.component.StickyNestedScrollView;

public class Account extends AppCompatActivity {
 TextView textView;
 TabLayout tabLayout;
 ViewPager viewPager;
 StickyNestedScrollView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        tabLayout = findViewById(R.id.tab_Layout);
        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
        viewPager = findViewById(R.id.view_pager);
       Viewpager viewpager_adapter = new Viewpager(getSupportFragmentManager());
        //neu dat trung ten ta chon viewpager cua model account
        //nen dat khac ten
        viewpager_adapter.addFragment(new Signin(),"Đăng nhập");
        viewpager_adapter.addFragment(new Signup(),"Đăng ký");
        viewPager.setAdapter(viewpager_adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
