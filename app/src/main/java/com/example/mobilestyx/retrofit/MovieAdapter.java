package com.example.mobilestyx.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobilestyx on 05/10/16.
 */


  class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
    private  List<Actor> movieList;

    private  Context context;

    private String TAG="tag";
    SharedPreference sharedPreference;

    //construct of movie adapter
    MovieAdapter(List<Actor> list, Context context) {
        this.movieList=list;
        this.context=context;
        sharedPreference=new SharedPreference();


        Log.e("movie list ",""+movieList.size());
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.content,parent,false);
        Log.e(TAG,"Adapter construct calling");
        return new MyViewHolder(item);  //pass holder to MyViewHolder
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Actor actor = movieList.get(position);
        Log.e(TAG, "on bind getview calling");

        holder.name.setText(actor.getName());
        holder.dob.setText(actor.getDob());
        holder.description.setText(actor.getDescription());
        Glide.with(context).load(actor.getImage()).
                thumbnail(0.5f).crossFade().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.image);
        holder.favoriate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tag = holder.favoriate.getTag().toString();
                if (tag.equalsIgnoreCase("grey")) {
                    sharedPreference.addFavorite(context, movieList.get(position));
                    Toast.makeText(context,"store sucess",
                            Toast.LENGTH_SHORT).show();

                    holder.favoriate.setTag("red");
                    holder.favoriate.setChecked(true);
                } else {
                    sharedPreference.removeFavorite(context,movieList.get(position));
                    holder.favoriate.setTag("grey");
                    holder.favoriate.setChecked(false);
                    Toast.makeText(context,
                            "unchacked",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (checkFavoriteItem(movieList.get(position))) {
            holder.favoriate.setChecked(true);
            Log.e("true fav","log");
            holder.favoriate.setTag("red");
        } else {
            holder.favoriate.setChecked(false);
            holder.favoriate.setTag("grey");
        }




    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Actor actor) {
        boolean check = false;

        List<Actor> favorites = sharedPreference.getFavorites(context);

        if (favorites != null) {
            for (Actor actor1: favorites) {
                if (actor1.equals(actor)) {
                    check = true;


                    break;
                }
            }
        }
        return check;
    }



    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setFilter(List<Actor> countryModels) {
        movieList = new ArrayList<>();
        movieList.addAll(countryModels);
        Log.e("set filter","notifiled change");
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private  ToggleButton favoriate;
        TextView name,dob,description;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
             name=(TextView)itemView.findViewById(R.id.name);
             favoriate=(ToggleButton)itemView.findViewById(R.id.fav);
             dob=(TextView)itemView.findViewById(R.id.dob);
             description=(TextView)itemView.findViewById(R.id.description);
             image=(ImageView)itemView.findViewById(R.id.image_view);


        }
    }

    public void add(Actor actor) {

        movieList.add(actor);
        notifyDataSetChanged();
    }


    public void remove(Actor actor) {

        movieList.remove(actor);
        notifyDataSetChanged();
    }

}
