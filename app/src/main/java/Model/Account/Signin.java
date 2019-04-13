package Model.Account;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.R;
import retrofit2.Callback;
import retrofit2.Response;

public class Signin extends Fragment {
    View view;
    TextView alertname;
    TextView alertpass;
    TextView buttonIn;
    TextInputEditText passIn;
    EditText nameIn;
    String regex;

    String name;
    String pass;
    public Signin() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_signin,container,false);
        regex = "\\S+";
        nameIn = view.findViewById(R.id.name_signup);
        passIn = view.findViewById(R.id.pas_signup);
        alertname = view.findViewById(R.id.name_in);
        alertpass = view.findViewById(R.id.pass_in);
        alertname.setVisibility(View.GONE);
        alertpass.setVisibility(View.GONE);
        buttonIn = view.findViewById(R.id.button_in);
        buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameIn.getText().toString();
                pass = passIn.getText().toString();
                Log.i("kk", name);
                Log.i("kk", pass);
                if (!Pattern.matches(regex, name)) {
                    Log.i("kk", "kska");
                    alertname.setText("Vui lòng nhập name");
                    alertname.setVisibility(View.VISIBLE);
                }
                if (!Pattern.matches(regex, pass)) {
                    alertpass.setText("Vui lòng nhập password");
                    alertpass.setVisibility(View.VISIBLE);
                } else {
                    ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                    retrofit2.Call<List<String>> call = serviceApi.logIn(name, pass);
                    call.enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(retrofit2.Call<List<String>> call, Response<List<String>> response) {
                            Log.i("kk", "lllska");
                            if (response.body().get(0).equals(2)) {
                                alertname.setText("Nickname không đúng");
                                alertpass.setText("Password không đúng");
                                alertname.setVisibility(View.VISIBLE);
                                alertpass.setVisibility(View.VISIBLE);
                            }
                            if (response.body().get(0).equals(1)) {
                                alertpass.setText("Password không đúng");
                            }
                            if (response.body().get(0).equals(0)) {

                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<List<String>> call, Throwable t) {

                        }
                    });
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (alertname.getVisibility() == View.VISIBLE | alertpass.getVisibility() == View.VISIBLE) {
                            alertname.setVisibility(View.GONE);
                            alertpass.setVisibility(View.GONE);
                        }
                    }
                }, 5000);
            }
        });
       return view;
    }
}
