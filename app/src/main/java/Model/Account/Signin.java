package Model.Account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

import Model.Resum.Resum;
import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;


public class Signin extends Fragment {
    View view;
    TextView alertname;
    TextView alertpass;
    TextView buttonIn;
    TextInputEditText passIn;
    EditText nameIn;
    String regex;
    int checkedIn;
    String name;
    String pass;
    public Signin() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_signin,container,false);
        regex = "\\S+";
        checkedIn = 0;
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
                name = nameIn.getText().toString().trim();
                pass = passIn.getText().toString().trim();
              /*  Log.i("kk", name);
                Log.i("kk", pass);*/
                if (!Pattern.matches(regex, name)) {
                    /*   Log.i("pk", "kska");*/
                    alertname.setText("Vui lòng nhập name");
                    alertname.setVisibility(View.VISIBLE);
                } else if (!Pattern.matches(regex, pass)) {
                    alertpass.setText("Vui lòng nhập password");
                    alertpass.setVisibility(View.VISIBLE);
                } else {
                    ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                    Log.i("hpa", "" + name + " " + pass);
                    Call<List<Alter>> call = serviceApi.logIn(name, pass);
                    call.enqueue(new Callback<List<Alter>>() {
                        @Override
                        public void onResponse(Call<List<Alter>> call, Response<List<Alter>> response) {
                            Log.i("haa", "" + response.body().get(0).getAlter());

                            Toast.makeText(getActivity(), "kkll", Toast.LENGTH_LONG).show();
                            if (response.body().get(0).getAlter().equals("2")) {
                                alertname.setText("Nickname không đúng");
                                alertpass.setText("Password không đúng");
                                alertname.setVisibility(View.VISIBLE);
                                passIn.getText().clear();
                                nameIn.getText().clear();
                                alertpass.setVisibility(View.VISIBLE);
                            } else if (response.body().get(0).getAlter().equals("1")) {
                                alertpass.setText("Password không đúng");
                                alertpass.setVisibility(View.VISIBLE);
                                passIn.getText().clear();
                            } else if (response.body().get(0).getAlter().equals("0")) {
                                getActivity().finish();
                                /*   Toast.makeText(getActivity(), "dang nhap ok", Toast.LENGTH_LONG).show();*/
                                SharedPreferences sharedPref = getActivity().getSharedPreferences(
                                        "Accout", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("idName", name);
                                editor.apply();
                                Intent detail = getActivity().getIntent();
                                int ide = detail.getIntExtra("ide", 0);
                                if (ide == 1) {
                                    getActivity().finish();
                                } else {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.putExtra("ide", 3);
                                    getActivity().startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Alter>> call, Throwable t) {
                            t.getMessage();
                            Log.i("ol", "pppp" + t.getMessage());

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
                }, 3000);
            }
        });

       return view;

    }
}
