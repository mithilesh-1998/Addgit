package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = null;
        HttpURLConnection connection = null ;
        int responseCode = 0;
        String urlString = "https://api.chucknorris.io/jokes/random";

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("problem in url");
        }
        //connection

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("connection problem");
        }

        //extract information from the connection object:

        if(responseCode == 200){
            BufferedReader in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();

            String readLine = null;


            while((readLine = in.readLine())!=null){
                apiData.append(readLine);
            }
            //
            in.close();

            JSONObject jsonApIResponse = new JSONObject(apiData.toString());

            System.out.println(jsonApIResponse.get("categories"));
            System.out.println(jsonApIResponse.get("created_at"));
            System.out.println(jsonApIResponse.get("icon_url"));
            System.out.println(jsonApIResponse.get("id"));
            System.out.println(jsonApIResponse.get("updated_at"));
            System.out.println(jsonApIResponse.get("url"));
            System.out.println(jsonApIResponse.get("value"));



        }else{
            System.out.println("API call could not be made!!!");
        }
    }
}