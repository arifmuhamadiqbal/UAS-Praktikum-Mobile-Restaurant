package lat.pam.utsrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMenu extends AppCompatActivity {

    TextView pizza, spaghetti, burger, steak, frenchFries;
    TextView pizzaDetail, spaghettiDetail, burgerDetail, steakDetail, frenchFriesDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        pizza = findViewById(R.id.pepperoni_pizza);
        pizzaDetail = findViewById(R.id.pepperoni_pizza_detail);
        spaghetti = findViewById(R.id.spaghetti);
        spaghettiDetail = findViewById(R.id.spaghetti_detail);
        burger = findViewById(R.id.burger);
        burgerDetail = findViewById(R.id.burger_detail);
        steak = findViewById(R.id.steak);
        steakDetail = findViewById(R.id.steak_detail);
        frenchFries = findViewById(R.id.french_fries);
        frenchFriesDetail = findViewById(R.id.french_fries_detail);


        hubungi_server();

        FloatingActionButton fab = findViewById(R.id.button_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMenu.this, DetailMenu.class));
            }
        });


    }

    // connect API method
    private void hubungi_server() {
        // URL API
        String url= Global.base_url;
        // make request
        StringRequest request = new StringRequest(
                // request method to API
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            // if connect
            public void onResponse(String response) {
                // get data from API
                try {
                    // parse respone to jsonArray
                    JSONArray jsonArray = new JSONArray(response);
                    // pizza
                    String pizzaName = jsonArray.getJSONObject(0).getString("foodName");
                    pizza.setText(pizzaName);
                    String pizzaDetails = jsonArray.getJSONObject(0).getString("details");
                    pizzaDetail.setText(pizzaDetails);

                    // spaghetti
                    String spaghettiName = jsonArray.getJSONObject(1).getString("foodName");
                    spaghetti.setText(spaghettiName);
                    String spaghettiDetails = jsonArray.getJSONObject(1).getString("details");
                    spaghettiDetail.setText(spaghettiDetails);

                    // burger
                    String burgerName = jsonArray.getJSONObject(2).getString("foodName");
                    burger.setText(burgerName);
                    String burgerDetails = jsonArray.getJSONObject(2).getString("details");
                    burgerDetail.setText(burgerDetails);

                    // steak
                    String steakName = jsonArray.getJSONObject(3).getString("foodName");
                    steak.setText(steakName);
                    String steakDetails = jsonArray.getJSONObject(3).getString("details");
                    steakDetail.setText(steakDetails);

                    // french fries
                    String frenchFriesName = jsonArray.getJSONObject(4).getString("foodName");
                    frenchFries.setText(frenchFriesName);
                    String frenchFriesDetails = jsonArray.getJSONObject(4).getString("details");
                    frenchFriesDetail.setText(frenchFriesDetails);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                // if not connect
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListMenu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // request queue using volley
        RequestQueue requestQueue = Volley.newRequestQueue(ListMenu.this);
        requestQueue.add(request);
    }
}