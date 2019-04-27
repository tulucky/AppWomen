package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ImageView brandLogo;
    TextView textLogo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        brandLogo = findViewById(R.id.b_logo);
        textLogo = findViewById(R.id.text_logo);
        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.brand_logo);
        brandLogo.startAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(Splash.this, R.anim.text_logo);
        textLogo.startAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(intent);
                finish();
            }
        }, 3500);
    }
}
