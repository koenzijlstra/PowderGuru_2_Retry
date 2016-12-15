package com.example.koen.powderguru2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


public class Httphelper {
    final static String url1 = "http://api.worldweatheronline.com/free/v2/ski.ashx?key=dd08bcba2d0c46c2830153816160512&q=";
    // geprobeerd met premium?
    final static String urltest = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=dd08bcba2d0c46c2830153816160512&q=";
//    final static String url2 = "&num_of_days=";t
//    final static String url3 = "&format=json";

    // nu voor gemak devault value van 1 bij num of days
    final static String url4 = "&num_of_days=2&date=tomorrow&format=json";

    // http://api.worldweatheronline.com/free/v2/ski.ashx?key=dd08bcba2d0c46c2830153816160512&q=gerlos&num_of_days=2&format=json

    // stuk uit app 3!!
    // http://stackoverflow.com/questions/7190208/how-to-read-string-builder-line-by-line
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String input) throws IOException, JSONException {
        InputStream is = new URL(urltest + input + url4).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

