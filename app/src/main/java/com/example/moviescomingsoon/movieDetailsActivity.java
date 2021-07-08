package com.example.moviescomingsoon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import model.movies;
import okhttp3.Headers;

import static android.content.ContentValues.TAG;

public class movieDetailsActivity extends YouTubeBaseActivity {
    public TextView tvtitle;
    public RatingBar rate;
    public TextView tvdescription;
    YouTubePlayerView youTubePlayerView;

    public String google_api_key = "AIzaSyCnwxhoMNyTHZYJLI67BalA3MLxg9AYt4M";
    public static String url_video = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_movie_details);
        tvtitle = findViewById(R.id.title);
        rate = findViewById(R.id.ratingBar);
        tvdescription = findViewById(R.id.description);
        youTubePlayerView = findViewById(R.id.player);
        movies movie_object = Parcels.unwrap(getIntent().getParcelableExtra("movie_object"));


        tvdescription.setText(movie_object.getDescription());
        tvtitle.setText(movie_object.getTitle_of_movie());
        rate.setRating((float) movie_object.getNumVotes());

        AsyncHttpClient clientObject = new AsyncHttpClient();
        clientObject.get(String.format(url_video, movie_object.getMovieid()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results =  json.jsonObject.getJSONArray("results");
                    if(results.length() == 0){
                        //TODO: add toast to notify user that video can't be loaded
                        return;
                    }
                    String youtubekey = results.getJSONObject(0).getString("key");
                    //TODO: add input validation to check that site from api is in fact youtube, if not it might not crash lmao
                    Log.d("worked",youtubekey );
                    initializeYoutubeMaterials(youtubekey);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.i("did not work", "did not");

            }
        });


    }

    private void initializeYoutubeMaterials(final String youtubekey) {
        youTubePlayerView.initialize(google_api_key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.i(TAG, "worked");
                youTubePlayer.cueVideo(youtubekey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
               Log.i(TAG, "fhjnmg");
            }
        });
    }
}