package lucky.dev.tu.devandroid;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
//phai import ko no hieu la lop Viewper cua system

import Model.Account.Signin;
import Model.Account.Signup;
import Model.Account.Viewpager;
import me.didik.component.StickyNestedScrollView;

public class Login extends AppCompatActivity {
 TextView textView;
 TabLayout tabLayout;
 ViewPager viewPager;
 StickyNestedScrollView a;
    ImageView exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tabLayout = findViewById(R.id.tab_Layout);
        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
        viewPager = findViewById(R.id.view_pager);
        exit = findViewById(R.id.exit_login);
       Viewpager viewpager_adapter = new Viewpager(getSupportFragmentManager());
        //neu dat trung ten ta chon viewpager cua model account
        //nen dat khac ten
        viewpager_adapter.addFragment(new Signin(), "Đăng Nhập");
        viewpager_adapter.addFragment(new Signup(), "Đăng Ký");
        viewPager.setAdapter(viewpager_adapter);
        tabLayout.setupWithViewPager(viewPager);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
