package pl.localdeals.localdealsapp;

import android.location.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String URL_GET_DEALS = "http://app.localdeals.pl/get_deals.php";
    public static String URL_GET_LOCALS = "http://app.localdeals.pl/get_locals.php";

    static public String get_data(String mode_url, String post_str_data) {
        String data = "";
        try {
            URL url = new URL(mode_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(post_str_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                data += temp;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    static public String encode_single_post(String key, String value){

        try {
            return URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }


    static ArrayList<Deal> raw_data_to_deals(String raw_data){
        ArrayList<Deal> deals = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(raw_data);
            JSONObject singleJsonObject;
            for(int j=0; j != json.length(); j++){
                singleJsonObject = json.getJSONObject(j);
                deals.add(new Deal(singleJsonObject.getInt("DEAL_ID"),
                        singleJsonObject.getInt("TYPE"),
                        singleJsonObject.getInt("DAY"),
                        singleJsonObject.getInt("HOUR_START"),
                        singleJsonObject.getInt("HOUR_END"),
                        singleJsonObject.getInt("POINTS"),
                        singleJsonObject.getInt("LOCAL_ID"),
                        singleJsonObject.getString("DEAL_NAME"),
                        singleJsonObject.getString("PRICE"),
                        singleJsonObject.getString("DESCRIPTION"),
                        singleJsonObject.getString("TERMS"),
                        singleJsonObject.getString("JPG")));
                singleJsonObject = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deals;
    }


    static ArrayList<Local> raw_data_to_locals(String raw_data){
        ArrayList<Local> locals = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(raw_data);
            JSONObject singleJsonObject;
            for(int j=0; j != json.length(); j++){
                singleJsonObject = json.getJSONObject(j);
                locals.add(new Local(singleJsonObject.getInt("ID"),
                        singleJsonObject.getString("NAME"),
                        singleJsonObject.getString("CITY"),
                        singleJsonObject.getString("ADDRESS"),
                        singleJsonObject.getString("URL"),
                        singleJsonObject.getString("JPG"),
                        singleJsonObject.getDouble("LONGITUDINAL"),
                        singleJsonObject.getDouble("LATERAL")));
                singleJsonObject = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locals;
    }

    static Map raw_data_to_locals_dict(String raw_data, Location device_location){
        Map<Integer, Local> locals = new HashMap<>();
        try {
            JSONArray json = new JSONArray(raw_data);
            JSONObject singleJsonObject;
            for(int j=0; j != json.length(); j++){
                singleJsonObject = json.getJSONObject(j);
                Local temp_local = new Local(singleJsonObject.getInt("ID"),
                        singleJsonObject.getString("NAME"),
                        singleJsonObject.getString("CITY"),
                        singleJsonObject.getString("ADDRESS"),
                        singleJsonObject.getString("URL"),
                        singleJsonObject.getString("JPG"),
                        singleJsonObject.getDouble("LONGITUDINAL"),
                        singleJsonObject.getDouble("LATERAL"));
                temp_local.calculate_distance(device_location);
                locals.put(singleJsonObject.getInt("ID"), temp_local);

                singleJsonObject = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locals;
    }



}

