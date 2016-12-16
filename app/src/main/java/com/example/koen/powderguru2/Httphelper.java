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

/*
* Koen Zijlstra, 10741615
*
* this class constructs the full url, reads json from api and constructs json object
 */

public class Httphelper {

    // first and last part of complete url, is set to show data for today and tomorrow. can be adjusted to more days
    final static String url1 = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=dd08bcba2d0c46c2830153816160512&q=";
    final static String url2 = "&num_of_days=2&date=tomorrow&format=json";

    // used stringbuiler from http://stackoverflow.com/questions/7190208/how-to-read-string-builder-line-by-line
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    // read json from complete url, create jsonobject
    public static JSONObject readJsonFromUrl(String input) throws IOException, JSONException {
        InputStream is = new URL(url1 + input + url2).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }
}

