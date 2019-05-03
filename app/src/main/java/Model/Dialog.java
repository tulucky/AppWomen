package Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class Dialog extends DialogFragment {
    NoticeDialogListener listener;

    public Dialog() {
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    //for activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoticeDialogListener) getTargetFragment();
        } catch (ClassCastException e) {

        }

    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Bạn mua sản phẩm ?").setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogPositiveClick(Dialog.this);

            }
        })
                .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(Dialog.this);
                    }
                });
        return builder.create();
    }
}
