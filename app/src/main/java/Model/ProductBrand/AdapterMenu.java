package Model.ProductBrand;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import Model.Service;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;

public class AdapterMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    View view;
    Context mcontext;
    List<String> menuListItem;
    RecyclerView recyclerV;
    KProgressHUD kProgressHUD;

    public AdapterMenu(Context mcontext, List<String> menuList,RecyclerView recyclerView) {
        this.mcontext = mcontext;
        menuListItem= new ArrayList<>();
        this.menuListItem = menuList;
        this.recyclerV= recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater myinflate = LayoutInflater.from(mcontext);
       view= myinflate.inflate(R.layout.item_menu_list,viewGroup,false);
       return new MyHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
       MyHolderMenu holder = (MyHolderMenu) viewHolder;
       holder.textView.setText(menuListItem.get(i));
       holder.textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               recyclerV.setVisibility(View.GONE);
               switch (i){
                   case 0:
                       new Asyn().execute(1);
               }

           }
       });
    }
    public class Asyn extends AsyncTask<Integer,Void,Void>{
        @Override
        protected void onPreExecute() {
           kProgressHUD =  KProgressHUD.create(mcontext)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .setBackgroundColor(Color.DKGRAY)
                    .show();
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Service.Request(integers[0]);
           return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            kProgressHUD.dismiss();

        }


    }


    @Override
    public int getItemCount() {
        return menuListItem.size();
    }

    private class MyHolderMenu extends RecyclerView.ViewHolder {
        TextView textView;
        public MyHolderMenu(View view) {
            super(view);
            textView= view.findViewById(R.id.text_menu_list);
        }
    }
}
