package Model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;

public class GridProduct extends Fragment {
    RecyclerView listProduct;
    List<Product> listData;
    private static final String urlData3 = "http://192.168.1.24/wmshop/tops.php";

    public GridProduct() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hg", "" + container);
        View view = inflater.inflate(R.layout.gridproduct, container, false);
        listProduct = view.findViewById(R.id.grid_product);
        listProduct.setNestedScrollingEnabled(false);
        listProduct.setHasFixedSize(true);
        getGrid();

        return view;

    }

    private void getGrid() {
        listData = new ArrayList<>();
        listProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        StringRequest obj = new StringRequest(Request.Method.GET, urlData3,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("l", "" + response);
                        JSONArray aray = null;
                        try {
                            aray = new JSONArray(response);

                            for (int i = 0; i < aray.length(); i++) {
                                // chu y du lieu tra ve tu url len de la acsoc thi ta moi getdc jsonobject
                                JSONObject a = aray.getJSONObject(i);
                                listData.add(new Product(a.getInt("id"),
                                        a.getString("image"),
                                        a.getString("name"),
                                        a.getString("originprice"),
                                        a.getString("sale"))
                                );
                            }

                            GridAdapter madapter = new GridAdapter(getActivity(), listData);
                            listProduct.setAdapter(madapter);
                            Log.i("yy", "" + listData.size());
                        } catch (JSONException e1) {
                            Log.i("j", "khong");
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(obj);

    }


}
