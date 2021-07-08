package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class movies {

    private String title_of_movie;
    private  String link_to_movie_image;
    private  String description;

    private int  movieid;

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public double numVotes;
    //necessary for parceler

    public movies(){

    }

    public movies(JSONObject jsonObject) throws JSONException {
        //use the constructor to parse json into string variables
        this.title_of_movie = jsonObject.getString("title");
        this.link_to_movie_image = jsonObject.getString("poster_path");
        this.description = jsonObject.getString("overview");
        this.numVotes = jsonObject.getDouble("vote_average");
        this.movieid = jsonObject.getInt("id");
    }

    //create a method that will create list of datatype movies
    public static List<movies> createMovies(JSONArray all_movies_in_json_form) throws JSONException {
        List<movies> good_movies = new ArrayList<>();
        for(int i = 0; i < all_movies_in_json_form.length(); i++){

            good_movies.add(new movies(all_movies_in_json_form.getJSONObject(i)));
        }
        return good_movies;
    }

    public  String getTitle_of_movie() {
        return title_of_movie;
    }

    public  void setTitle_of_movie(String title_of_movie) {
       this.title_of_movie = title_of_movie;
    }

    public  String getLink_to_movie_image() {
        //this will literally only give us a path not the complete www. etc
        //we need a full url so that we can use another library to load the image for us!
       //make a request to configurations api, get the size, and then append it

//width 342
        return String.format("https://image.tmdb.org/t/p/w342/%s", link_to_movie_image);
    }

    public void setLink_to_movie_image(String link_to_movie_image) {
      this.link_to_movie_image = link_to_movie_image;
    }

    public  String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public double getNumVotes() {

        return numVotes;
    }

    public void setNumVotes(double numVotes) {
         this.numVotes= numVotes;
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
    //assignment two assumptions:
    create activity to show the details of move
    might be recyler view but idts, it's not necessary
    create intent to pass link of video, description and title
    call some api to populate rating widget?

     */
}
