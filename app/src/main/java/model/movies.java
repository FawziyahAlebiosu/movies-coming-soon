package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class movies {
    private static String title_of_movie;
    private static String link_to_movie_image;
    private static String description;

    public static String numVotes;

    public movies(JSONObject jsonObject) throws JSONException {
        //use the constructor to parse json into string variables
        title_of_movie = jsonObject.getString("title");
        link_to_movie_image = jsonObject.getString("poster_path");
        description = jsonObject.getString("overview");
    }

    //create a method that will create list of datatype movies
    public static List<movies> createMovies(JSONArray all_movies_in_json_form) throws JSONException {
        List<movies> good_movies = new ArrayList<>();
        for(int i = 0; i < all_movies_in_json_form.length(); i++){
            movies current_object = new movies(all_movies_in_json_form.getJSONObject(i));
            good_movies.add(current_object);
        }
        return good_movies;
    }

    public static String getTitle_of_movie() {
        return title_of_movie;
    }

    public static void setTitle_of_movie(String title_of_movie) {
        movies.title_of_movie = title_of_movie;
    }

    public static String getLink_to_movie_image() {
        //this will literally only give us a path not the complete www. etc
        //we need a full url so that we can use another library to load the image for us!
       //make a request to configurations api, get the size, and then append it

//width 342
        return String.format("https://image.tmdb.org/t/p/w342/%s", link_to_movie_image);
    }

    public static void setLink_to_movie_image(String link_to_movie_image) {
        movies.link_to_movie_image = link_to_movie_image;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        movies.description = description;
    }

    public static String getNumVotes() {
        return numVotes;
    }

    public static void setNumVotes(String numVotes) {
        movies.numVotes = numVotes;
    }
    /*
    based on what we recieve from the api:

    popularity
    vote_count
    video/image of the cover
    id
    adult genere boolean
    title of movie
    overview/description
    releae dat

    what matters?

    title
    poster image
    overview
    currently playing ?

    later on: vote of movie
     */
}
