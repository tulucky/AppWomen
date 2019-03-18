package Model.ProductBrand;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lucky.dev.tu.devandroid.R;

public class AdapterMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    View view;
    Context mcontext;
    List<String> menuListItem;
    RecyclerView recyclerV;

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
               Toast.makeText(mcontext, "da click"+ i, Toast.LENGTH_LONG).show();
               recyclerV.setVisibility(View.GONE);



           }
       });
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
