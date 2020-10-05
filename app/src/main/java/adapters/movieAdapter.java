package adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviescomingsoon.MainActivity;
import com.example.moviescomingsoon.R;
import com.example.moviescomingsoon.movieDetailsActivity;

import org.parceler.Parcels;

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
        RelativeLayout row;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleMovie = itemView.findViewById(R.id.titleMovie);
            descriptionMovie = itemView.findViewById(R.id.movieDescription);
            posterImage = itemView.findViewById(R.id.posterMovie);
            row = itemView.findViewById(R.id.individualRow);

        }

        public void bind(final movies current_movie) {

            titleMovie.setText(current_movie.getTitle_of_movie());
            descriptionMovie.setText(current_movie.getDescription());
            //populate images
            Glide.with(context).load(current_movie.getLink_to_movie_image()).into(posterImage);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //we want listener to be entire row, not just the title- done

                    //create an intent, and grab description, and go to anew activity
                    Intent intent = new Intent(context, movieDetailsActivity.class);



                    intent.putExtra("movie_object", Parcels.wrap(current_movie));

                    context.startActivity(intent);
                   // MainActivity.this.startActivityForResult(intent, 100);
                }
            });
        }
    }

}
