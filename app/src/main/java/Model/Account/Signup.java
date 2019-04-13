package Model.Account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

import lucky.dev.tu.devandroid.R;

public class Signup extends Fragment {
    View view;
    EditText nameSignUp;
    TextView buttonUp;
    String reGex;
    String name;
    TextView alter;
    public Signup() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup,container,false);
        reGex = "[a-zA-Z0-9]{5,9}";
        nameSignUp = view.findViewById(R.id.email_signup);
        buttonUp = view.findViewById(R.id.button_up);
        alter = view.findViewById(R.id.name_up);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameSignUp.getText().toString().trim();
                if (Pattern.matches(reGex, name)) {

                } else {
                    alter.setText("Nickname chi chua ki tu a-z,A-Z,0-9");

                }

            }
        });

        return view;
    }
}
