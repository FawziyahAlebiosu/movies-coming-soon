package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviescomingsoon.R;

import java.util.List;

import model.movies;

//TODO: ADD NUMVOTES TO RECYCLER VIEW
public class movieAdapter extends RecyclerView.Adapter<movieAdapter.ViewHolder> {

    //we need context so we can inflate the view, aka where the adpater is being cotnruscted from

    Context context;
    List<movies> movies;

    public movieAdapter(Context context, List<movies> movies){
        this.context = context;
        this.movies = movies;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movies_view = LayoutInflater.from(context).inflate(R.layout.movie_display, parent, false);
        return new ViewHolder(movies_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//get the movie at the position
        movies current_movie = movies.get(position);

        //bind that value into the viewholder
        holder.bind(current_movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleMovie;
        TextView descriptionMovie;
        ImageView posterImage;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleMovie = itemView.findViewById(R.id.titleMovie);
            descriptionMovie = itemView.findViewById(R.id.movieDescription);
            posterImage = itemView.findViewById(R.id.posterMovie);

        }

        public void bind(movies current_movie) {
            titleMovie.setText(current_movie.getTitle_of_movie());
            descriptionMovie.setText(current_movie.getDescription());
            //populate images
            Glide.with(context).load(current_movie.getLink_to_movie_image()).into(posterImage);
        }
    }

}
