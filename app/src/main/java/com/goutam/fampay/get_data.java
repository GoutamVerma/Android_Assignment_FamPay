package com.goutam.fampay;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class get_data {

    public static HashMap<String, List<List<String>>> packets = new HashMap<String, List<List<String>>>();

    public static JSONObject readJsonFromUrl() throws IOException, JSONException {
        String url = "https://run.mocky.io/v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad";
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void store(){
        try{
            JSONObject json = readJsonFromUrl();
            JSONArray jsonArray = json.getJSONArray("card_groups");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String design_type = object.getString("design_type");
                if (design_type.equals("HC3")==true){
                    JSONArray cards = object.getJSONArray("cards");
                    List<List<String>> listofcard = new ArrayList<>();
                    for (int j = 0; j < cards.length(); j++) {
                        JSONObject title = cards.getJSONObject(j);
                        JSONObject jsonObject = title.getJSONObject("bg_image");
                        List<String> values = new ArrayList<>();
                        values.add(title.getString("name"));
                        values.add(title.getString("description"));
                        values.add(title.getString("url"));
                        values.add(jsonObject.getString("image_url"));
                        listofcard.add(values);
                    }
                    packets.put(design_type,listofcard);
                }
                else if(design_type.equals("HC6")==true){
                    JSONArray cards = object.getJSONArray("cards");
                    List<List<String>> listofcard = new ArrayList<>();
                    for (int j = 0; j < cards.length(); j++) {
                        System.out.println("valye of j"+ j);
                        JSONObject title = cards.getJSONObject(j);
                        JSONObject jsonObject = title.getJSONObject("icon");
                        List<String> values = new ArrayList<>();
                        values.add(title.getString("name"));
                        values.add(title.getString("description"));
                        values.add(title.getString("url"));
                        values.add(jsonObject.getString("image_url"));
                        listofcard.add(values);

                    }
                    packets.put(design_type,listofcard);
                }
                else if(design_type.equals("HC5")==true){
                    JSONArray cards = object.getJSONArray("cards");
                    List<List<String>> listofcard = new ArrayList<>();
                    if(packets.get("HC5")==null){
                    for (int j = 0; j < cards.length(); j++) {
                        JSONObject title = cards.getJSONObject(j);
                        JSONObject jsonObject = title.getJSONObject("bg_image");
                        List<String> values = new ArrayList<>();
                        values.add(title.getString("name"));
                        values.add(title.getString("url"));
                        values.add(jsonObject.getString("image_url"));
                        listofcard.add(values);
                    }
                        packets.put(design_type,listofcard);

                    }
                }
                else if (design_type.equals("HC9")==true){
                    JSONArray cards = object.getJSONArray("cards");
                    List<List<String>> listofcard = new ArrayList<>();
                    for (int j = 0; j < cards.length(); j++) {
                        JSONObject title = cards.getJSONObject(j);
                        JSONObject jsonObject = title.getJSONObject("bg_image");
                        List<String> values = new ArrayList<>();
                        values.add(title.getString("name"));
                        values.add(title.getString("url"));
                        values.add(jsonObject.getString("image_url"));
                        listofcard.add(values);
                    }
                    packets.put(design_type,listofcard);
                }else if(design_type.equals("HC1")==true){
                    JSONArray cards = object.getJSONArray("cards");
                    List<List<String>> listofcard = new ArrayList<>();
                    for (int j = 0; j < cards.length(); j++) {
                        JSONObject title = cards.getJSONObject(j);
                        JSONObject jsonObject = title.getJSONObject("icon");
                        List<String> values = new ArrayList<>();
                        values.add(title.getString("title"));
                        values.add(title.getString("url"));
                        values.add(jsonObject.getString("image_url"));
                        listofcard.add(values);
                    }
                    packets.put(design_type,listofcard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }



}
