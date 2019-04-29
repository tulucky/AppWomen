package Model.Account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Model.Resum.Resum;
import lucky.dev.tu.devandroid.Login;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;

public class Account extends Fragment {
    TextView login;

    public Account() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account, container, false);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("Accout", Context.MODE_PRIVATE);
                String name = preferences.getString("idName", "khong");
                if (name.equals("khong")) {
                    Intent intent = new Intent(getActivity(), Login.class);
                    getActivity().startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("ide", 3);
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }

}
