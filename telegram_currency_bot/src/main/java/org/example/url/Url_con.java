package org.example.url;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.List;

public class Url_con {


    public static ArrayList<Currency> currencyList() {
        ArrayList<Currency> response;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            URL url = new URL("https://cbu.uz/uz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            Type type = new TypeToken<List<Currency>>() {}.getType();

            response = gson.fromJson(bufferedReader, type);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
