package pl.localdeals.localdealsapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


public class CurrentDealsActivity extends AppCompatActivity {
    int day, hour, minutes;
    public Map<Integer, Local> allLocals;
    public ArrayList<Deal> allDeals, todaysDeals, selectedDeals;
    RelativeLayout loadingview;
    RecyclerView recyclerView;
    TextView loadingInfo;
    private Location location;
    LocationManager locationManager;
    SwipeRefreshLayout swipeRefreshLayout;
    MyTimer myTimer;
    Button buttonDrink, buttonEat, buttonPlaces, buttonFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_deals);
        loadingview = findViewById(R.id.loading_view);
        recyclerView = findViewById(R.id.RecyclerView_deals);
        loadingInfo = findViewById(R.id.loading_info);
        buttonDrink = findViewById(R.id.menu_button_drink);
        buttonEat = findViewById(R.id.menu_button_eat);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadingInfo.setText(R.string.loading_data);

        GpsTracker gpsTracker = new GpsTracker(getApplicationContext());
        location = gpsTracker.getDeviceLocation();

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);

        myTimer = new MyTimer(day, hour, minutes);


        DownloadData downloadData = new DownloadData();
        downloadData.execute();


        buttonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingview.setVisibility(View.VISIBLE);
                selectedDeals = myTimer.pickDealsByType(new ArrayList<>(todaysDeals), 1);
                recyclerView.setAdapter(new DealAdapter(selectedDeals, allLocals, recyclerView, getApplicationContext()));
                loadingview.setVisibility(View.INVISIBLE);
                changeButtonColor(1);

            }
        });

        buttonEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingview.setVisibility(View.VISIBLE);
                selectedDeals = myTimer.pickDealsByType(new ArrayList<>(todaysDeals), 2);
                recyclerView.setAdapter(new DealAdapter(selectedDeals, allLocals, recyclerView, getApplicationContext()));
                loadingview.setVisibility(View.INVISIBLE);
                changeButtonColor(2);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                loadingview.setVisibility(View.VISIBLE);
                GpsTracker gpsTracker = new GpsTracker(getApplicationContext());
                location = gpsTracker.getDeviceLocation();
                DownloadData downloadData = new DownloadData();
                downloadData.execute();
                changeButtonColor(0);
            }
        });
    }


    private class DownloadData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String raw_data;
            raw_data = Utils.get_data(Utils.URL_GET_LOCALS, "code");
            allLocals = Utils.raw_data_to_locals_dict(raw_data, location);
            raw_data = Utils.get_data(Utils.URL_GET_DEALS, "code");
            allDeals = Utils.raw_data_to_deals(raw_data);
            return raw_data;
        }

        @Override
        protected void onPostExecute(String downloaded_data){
            if(downloaded_data.length() < 3){
                try{
                    Toast.makeText(getApplicationContext(), "Error:\n" + String.valueOf(downloaded_data), Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error :(", Toast.LENGTH_LONG).show();
                }
            }else{
                todaysDeals = myTimer.pickDealsByDay(new ArrayList<>(allDeals), myTimer.today);
                todaysDeals = new ArrayList<>(allDeals);
                recyclerView.setAdapter(new DealAdapter(todaysDeals, allLocals, recyclerView, getApplicationContext()));
                loadingview.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    private void showAlert(String info){
        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void changeButtonColor(int flag){
        if (flag == 1){
            buttonEat.setTextColor(getResources().getColor(R.color.white_text));
            buttonDrink.setTextColor(getResources().getColor(R.color.colorAccent));
        }else if(flag == 2){
            buttonDrink.setTextColor(getResources().getColor(R.color.white_text));
            buttonEat.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }



}


