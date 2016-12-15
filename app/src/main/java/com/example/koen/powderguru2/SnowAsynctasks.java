package com.example.koen.powderguru2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class SnowAsynctasks extends AsyncTask {

    private static Context context;
    private String input;
    private String city;
    private String datetomorrow;
    private String temp12;
    private String sun12;
    private String maxtemp;
    private String mintemp;
    private String snowzero;
    private String snowthree;
    private String snowsix;
    private String snownine;
    private String snowtwelve;
    private String snowfifteen;
    private String snoweighteen;
    private String snowtwentyone;

    public SnowAsynctasks(Context c, String search) {
        input = search;
        context = c;
    }

    @Override
    protected void onPreExecute (){
        super.onPreExecute();
        Toast.makeText(context, "Searching", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Object[] params) {
        try{
            JSONObject fulljson = Httphelper.readJsonFromUrl(input);
            JSONObject data = fulljson.getJSONObject("data");
            JSONArray request = data.getJSONArray("request");
            JSONObject req = request.getJSONObject(0);
            city = req.getString("query");
            JSONArray weather = data.getJSONArray("weather");
            JSONObject tomorrow = weather.getJSONObject(1);
            datetomorrow = tomorrow.getString("date");

            JSONArray hourly = tomorrow.getJSONArray("hourly");
            JSONObject twelve = hourly.getJSONObject(4);

            Integer twelvetemp = twelve.getInt("tempC");
            temp12 = twelvetemp.toString();

            Integer twelvesun = twelve.getInt("chanceofsunshine");
            sun12 = twelvesun.toString();

            Integer max = tomorrow.getInt("maxtempC");
            Integer min = tomorrow.getInt("mintempC");
            maxtemp = max.toString();
            mintemp = min.toString();


            // hourly snowfall predictions
            JSONObject zero = hourly.getJSONObject(0);
            Integer zerosnow = zero.getInt("chanceofsnow");
            snowzero = zerosnow.toString();

            JSONObject three = hourly.getJSONObject(1);
            Integer threesnow = three.getInt("chanceofsnow");
            snowthree = threesnow.toString();

            JSONObject six = hourly.getJSONObject(2);
            Integer sixsnow = six.getInt("chanceofsnow");
            snowsix = sixsnow.toString();

            JSONObject nine = hourly.getJSONObject(3);
            Integer ninesnow = nine.getInt("chanceofsnow");
            snownine = ninesnow.toString();

            Integer twelvesnow = twelve.getInt("chanceofsnow");
            snowtwelve = twelvesnow.toString();

            JSONObject fifteen = hourly.getJSONObject(5);
            Integer fifteensnow = fifteen.getInt("chanceofsnow");
            snowfifteen = fifteensnow.toString();

            JSONObject eighteen = hourly.getJSONObject(6);
            Integer eighteensnow = eighteen.getInt("chanceofsnow");
            snoweighteen = eighteensnow.toString();

            JSONObject twentyone = hourly.getJSONObject(7);
            Integer twentyonesnow = twentyone.getInt("chanceofsnow");
            snowtwentyone = twentyonesnow.toString();


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);


        // niet netjes om intent in asynctask aan te roepen, van activity naar activity
        Intent gotoinfo = new Intent(context, PredictionsActivity.class);
        gotoinfo.putExtra("city", city);
        gotoinfo.putExtra("date", datetomorrow);
        gotoinfo.putExtra("temp12", temp12);
        gotoinfo.putExtra("sun", sun12);
        gotoinfo.putExtra("max", maxtemp);
        gotoinfo.putExtra("min", mintemp);

        // snowpredictions
        gotoinfo.putExtra("0", snowzero);
        gotoinfo.putExtra("3", snowthree);
        gotoinfo.putExtra("6", snowsix);
        gotoinfo.putExtra("9", snownine);
        gotoinfo.putExtra("12", snowtwelve);
        gotoinfo.putExtra("15", snowfifteen);
        gotoinfo.putExtra("18", snoweighteen);
        gotoinfo.putExtra("21", snowtwentyone);

        context.startActivity(gotoinfo);

    }
}