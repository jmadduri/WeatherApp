package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText zip;
    Button search;
    String zipcode;
    String countryName;
    JSONObject json;
    TextView currentTemp;
    TextView currentHigh;
    TextView currentLow;
    TextView city;
    TextView quote;
    TextView date1;
    TextView date2;
    TextView date3;
    TextView date4;
    TextView date5;
    TextView high1;
    TextView high2;
    TextView high3;
    TextView high4;
    TextView high5;
    TextView low1;
    TextView low2;
    TextView low3;
    TextView low4;
    TextView low5;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG-start","Check 1");
        zip = findViewById(R.id.id_zip);
        search = findViewById(R.id.id_getWeatherButton);
        currentHigh = findViewById(R.id.id_high);
        currentLow = findViewById(R.id.id_low);
        currentTemp = findViewById(R.id.id_currentTemp);
        city = findViewById(R.id.id_city);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView6);
        imageView3 = findViewById(R.id.imageView4);
        imageView4 = findViewById(R.id.imageView7);
        imageView5 = findViewById(R.id.imageView3);
        imageView6 = findViewById(R.id.imageView8);
        high1 = findViewById(R.id.textView14);
        high2 = findViewById(R.id.textView11);
        high3 = findViewById(R.id.textView15);
        high4 = findViewById(R.id.textView7);
        high5 = findViewById(R.id.textView19);
        low1 = findViewById(R.id.textView9);
        low2 = findViewById(R.id.textView8);
        low3 = findViewById(R.id.textView12);
        low4 = findViewById(R.id.textView6);
        low5 = findViewById(R.id.textView13);
        quote = findViewById(R.id.textView2);
        videoView = findViewById(R.id.id_videoView);
        date1 = findViewById(R.id.textView10);
        date2 = findViewById(R.id.textView17);
        date3 = findViewById(R.id.textView16);
        date4 = findViewById(R.id.textView);
        date5 = findViewById(R.id.textView18);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        zipcode = "";
        countryName = "";

        Log.d("TAG-start2","Check 2");

            zip.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String str = s.toString();
                    if (s.toString().length() != 0) {
                        zipcode = str;
                    }
                    Log.d("TAG", zipcode);
                }
            });

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (zipcode.length() >= 4) {
                        Weather weather = new Weather(zipcode);
                        weather.execute();
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, "Zipcode must be at least three digits.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }

        public static String getDateandTime(String epoch)
        {
            Long epoch1 = Long.parseLong(epoch);
            String date = new java.text.SimpleDateFormat("hh:mm").format(new java.util.Date (epoch1*1000));
            return date;
        }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("WeatherAPI", json.toString());
    }

    public class Weather extends AsyncTask<String, Void, String> {
        private String data;
        private String data2;
        private String zip;

        public Weather(String zipcode) {
            data = "";
            data2 = "";
            zip = zipcode;
        }

        @Override
        protected String doInBackground(String... strings) {
            if (zipcode.length() != 0) {
                try {
                    String url1 = "https://api.openweathermap.org/data/2.5/forecast?zip=" + zip + ",us&APPID=YOUR_API_KEY_HERE&units=imperial";
                    //"https://api.openweathermap.org/data/2.5/forecast?zip=08824,us&APPID=YOUR_API_KEY_HERE&units=imperial"
                    URL call;
                    call = new URL(url1);
                    URLConnection connection = call.openConnection();
                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    String str = "";
                    str = reader.readLine();
                    data = data + str;
                    Log.d("TAG-data", data);

                    String url2 = "https://api.openweathermap.org/data/2.5/weather?zip=" + zip + ",us&APPID=YOUR_API_KEY_HERE&units=imperial";
                    //"https://api.openweathermap.org/data/2.5/weather?zip=08824,us&APPID=YOUR_API_KEY_HERE&units=imperial"
                    URL call2;
                    call2 = new URL(url2);
                    URLConnection connection2 = call2.openConnection();
                    InputStream stream2 = connection2.getInputStream();
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(stream2));

                    String str2 = "";
                    str2 = reader2.readLine();
                    data2 = data2 + str2;
                    Log.d("TAG-data", data2);

                } catch (Exception e) {
                    Log.d("TAG56", e.getMessage());
                }
                return data;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject json2 = new JSONObject(data2);
                String temp = String.valueOf(json2.getJSONObject("main").getDouble("temp"));
                currentTemp.setText(Math.round(Double.parseDouble(temp))+ "º");
                String temp2 = String.valueOf(json2.getJSONObject("main").getDouble("temp_max"));
                currentHigh.setText("High: "+Math.round(Double.parseDouble(temp2))+ "º");
                String temp3 = String.valueOf(json2.getJSONObject("main").getDouble("temp_min"));
                currentLow.setText("Low: "+Math.round(Double.parseDouble(temp3))+ "º");
                String temp21 = (json2.getJSONArray("weather").getJSONObject(0).getString("icon"));
                DownloadImageTask task = new DownloadImageTask(imageView,temp21);
                task.execute();

                if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=10)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The current temperature is equivalent to 10 times the salary Steve Jobs would take.");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=20)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("You can't do much in this weather, like how you can't do much on an Apple Newton.");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=30)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The average user spends about 1/10th of the current temperature, amount of hours on a phone every day.");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=40)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("Apple suggests 32° Fahrenheit as the lowest operating ambient temperature.");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=50)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The currrent temperature is the amount in millions of iPhones sold in the fourth quarter of 2018.");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=60)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The temperature is approximately equal to 1/10th of the amount of time Steve Jobs kept his car so that he did not have to put a license plate on it.");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=70)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The current temperature is how many million Airpods are expected to be sold for 2018 and 2019(67 million).");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=80)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The current temperature is approximately equivalent to 1/5 millionth of the total iPod sales since it was released(400 million were sold).");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=90)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("Apple designed a landline phone with a stylus in 1983");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))<=100)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The iPhone has about twice the number of patents as the current temperature(200 patents).");
                }
                else if(Math.round(Double.parseDouble(String.valueOf(json2.getJSONObject("main").getDouble("temp"))))>100)
                {
                    quote.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    quote.setText("The weather is as hot as the cpu on the 2018 8 core Macbook Pro when used for difficult tasks(It got VERY hot!).");
                }

                JSONObject json = new JSONObject(data);
                JSONArray list = json.getJSONArray("list");
                JSONObject rec = list.getJSONObject(0);
                String temp5 = String.valueOf(rec.getJSONObject("main").getDouble("temp_max"));
                String temp6 = String.valueOf(rec.getJSONObject("main").getDouble("temp_min"));
                high1.setText("High: "+Math.round(Double.parseDouble(temp5))+ "º");
                low1.setText("Low: "+Math.round(Double.parseDouble(temp6))+ "º");
                date1.setText(getDateandTime(rec.getString("dt")));
                JSONArray weather2 = rec.getJSONArray("weather");
                JSONObject icon = weather2.getJSONObject(0);
                String ic = icon.getString("icon");
                task = new DownloadImageTask(imageView2,ic);
                task.execute();

                rec = list.getJSONObject(1);
                temp5 = String.valueOf(rec.getJSONObject("main").getDouble("temp_max"));
                temp6 = String.valueOf(rec.getJSONObject("main").getDouble("temp_min"));
                high2.setText("High: "+Math.round(Double.parseDouble(temp5))+ "º");
                low2.setText("Low: "+Math.round(Double.parseDouble(temp6))+ "º");
                date2.setText(getDateandTime(rec.getString("dt")));
                weather2 = rec.getJSONArray("weather");
                icon = weather2.getJSONObject(0);
                ic = icon.getString("icon");
                task = new DownloadImageTask(imageView3,ic);
                task.execute();

                rec = list.getJSONObject(2);
                temp5 = String.valueOf(rec.getJSONObject("main").getDouble("temp_max"));
                temp6 = String.valueOf(rec.getJSONObject("main").getDouble("temp_min"));
                high3.setText("High: "+Math.round(Double.parseDouble(temp5))+ "º");
                low3.setText("Low: "+Math.round(Double.parseDouble(temp6))+ "º");
                date3.setText(getDateandTime(rec.getString("dt")));
                weather2 = rec.getJSONArray("weather");
                icon = weather2.getJSONObject(0);
                ic = icon.getString("icon");
                task = new DownloadImageTask(imageView4,ic);
                task.execute();

                rec = list.getJSONObject(3);
                temp5 = String.valueOf(rec.getJSONObject("main").getDouble("temp_max"));
                temp6 = String.valueOf(rec.getJSONObject("main").getDouble("temp_min"));
                high4.setText("High: "+Math.round(Double.parseDouble(temp5))+ "º");
                low4.setText("Low: "+Math.round(Double.parseDouble(temp6))+ "º");
                date4.setText(getDateandTime(rec.getString("dt")));
                weather2 = rec.getJSONArray("weather");
                icon = weather2.getJSONObject(0);
                ic = icon.getString("icon");
                task = new DownloadImageTask(imageView5,ic);
                task.execute();

                rec = list.getJSONObject(4);
                temp5 = String.valueOf(rec.getJSONObject("main").getDouble("temp_max"));
                temp6 = String.valueOf(rec.getJSONObject("main").getDouble("temp_min"));
                high5.setText("High: "+Math.round(Double.parseDouble(temp5))+ "º");
                low5.setText("Low: "+Math.round(Double.parseDouble(temp6))+ "º");
                date5.setText(getDateandTime(rec.getString("dt")));
                weather2 = rec.getJSONArray("weather");
                icon = weather2.getJSONObject(0);
                ic = icon.getString("icon");
                task = new DownloadImageTask(imageView6,ic);
                task.execute();

            } catch (Exception e) {
                Toast toast = Toast.makeText(MainActivity.this, "Please enter a valid zipcode.", Toast.LENGTH_LONG);
                toast.show();
            }
            try {
                JSONObject json = new JSONObject(data);
                JSONObject cityJ = json.getJSONObject("city");
                city.setText(cityJ.getString("name"));
            } catch (Exception e) {
                Log.d("KEY", "ExceptionOccured");
            }
        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        ImageView imageView;
        String icon;

        public DownloadImageTask(ImageView imageView, String icon)
        {
            this.imageView=imageView;
            this.icon=icon;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap logo = null;
            try {
                InputStream is = new URL("https://openweathermap.org/img/wn/" + icon + "@2x.png").openStream();
                logo = BitmapFactory.decodeStream(is);
                } catch (Exception e) {
                Log.d("TAG",e.getMessage());
            }
            return logo;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}