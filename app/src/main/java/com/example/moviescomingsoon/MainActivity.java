package com.example.moviescomingsoon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import model.movies;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String movies_now_playing_url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    public static String TAG = "main";
    public static List<movies> good_movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient clientObject = new AsyncHttpClient();

        //use client to make a get request and save the array we're given in return

        clientObject.get(movies_now_playing_url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "yay");
                //use json object to get json into a json array
                JSONObject jsonObject = json.jsonObject;

                try {

                    JSONArray all_results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "worked: " + all_results.toString());
                   good_movies = movies.createMovies(all_results);

                } catch (JSONException e) {
                    Log.e(TAG, "json part isn't working", e);

                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "no");
            }
        });
        //next,we want to parse the result array given to us!

    }
}