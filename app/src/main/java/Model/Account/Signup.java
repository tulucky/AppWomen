package Model.Account;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.Login;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends Fragment {
    View view;
    EditText nameSignUp;
    TextInputEditText passUp;
    TextView passAlter;
    TextInputEditText rePass;
    TextView buttonUp;
    String reGex;
    String name;
    String pass;
    String rePasswork;
    String reGexPass;
    ImageView succs;
    TextView nameAlter;
    public Signup() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup,container,false);
        reGex = "[a-zA-Z0-9]{5,9}";
        reGexPass = "\\S+";
        nameSignUp = view.findViewById(R.id.email_signup);
        passUp = view.findViewById(R.id.edit_pass_up);
        buttonUp = view.findViewById(R.id.button_up);
        succs = view.findViewById(R.id.succs);
        succs.setVisibility(View.GONE);
        passAlter = view.findViewById(R.id.alter_up);
        rePass = view.findViewById(R.id.edit_text_up);
        nameAlter = view.findViewById(R.id.name_up);
        nameAlter.setVisibility(View.GONE);
        passAlter.setVisibility(View.GONE);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameSignUp.getText().toString().trim();
                pass = passUp.getText().toString().trim();
                rePasswork = rePass.getText().toString().trim();
                if (!Pattern.matches(reGexPass, name)) {
                    nameAlter.setText("Vui lòng nhập nickname");
                    nameAlter.setVisibility(View.VISIBLE);

                } else if (!Pattern.matches(reGex, name)) {
                    nameAlter.setText("Nickname co ki tu a-z,A-Z,0-9 va co 5 ki tu");
                    nameAlter.setVisibility(View.VISIBLE);
                    nameSignUp.getText().clear();
                } else if (!Pattern.matches(reGexPass, pass)) {
                    Log.i("df", "fdfd");
                    passAlter.setText("Vui lòng nhập pasword");
                    passAlter.setVisibility(View.VISIBLE);
                } else if (!Pattern.matches(pass, rePasswork)) {
                    passAlter.setText("Mật khấu không khớp");
                    passAlter.setVisibility(View.VISIBLE);
                    passUp.getText().clear();
                    rePass.getText().clear();

                } else {
                    ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                    Log.i("hpa", "" + name + " " + pass);
                    Call<List<Alter>> call = serviceApi.signUp(name, pass);
                    call.enqueue(new Callback<List<Alter>>() {
                        @Override
                        public void onResponse(Call<List<Alter>> call, Response<List<Alter>> response) {
                            if (response.body().get(0).getAlter().equals("1")) {
                                nameAlter.setText("Tài khoản đã tồn tại");
                                nameAlter.setVisibility(View.VISIBLE);
                            } else {
                                if (response.body().get(0).getAlter().equals("2")) {
                                    succs.setVisibility(View.VISIBLE);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getActivity().finish();
                                            Intent intent = new Intent(getActivity(), Login.class);
                                            getActivity().startActivity(intent);
                                        }
                                    }, 2000);

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Alter>> call, Throwable t) {

                        }
                    });


                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (nameAlter.getVisibility() == View.VISIBLE | passAlter.getVisibility() == View.VISIBLE) {
                            nameAlter.setVisibility(View.GONE);
                            passAlter.setVisibility(View.GONE);
                        }
                    }
                }, 3000);

            }
        });

        return view;
    }
}
