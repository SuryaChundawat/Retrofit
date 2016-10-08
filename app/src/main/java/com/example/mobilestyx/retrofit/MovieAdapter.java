package com.example.mobilestyx.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

    private final Context context;

    private String TAG="tag";

    //construct of movie adapter
    MovieAdapter(List<Actor> list, Context context) {
        this.movieList=list;
        this.context=context;


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
         Actor actor=movieList.get(position);
        Log.e(TAG,"on bind getview calling");

        holder.name.setText(actor.getName());
        holder.dob.setText(actor.getDob());
        holder.description.setText(actor.getDescription());
        Glide.with(context).load(actor.getImage()).
                thumbnail(0.5f).crossFade().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.image);

        if(MainActivity.referenceValue==11)
        {
            holder.favoriate.setChecked(true);
        }else
        {
            if(MainActivity.checkPosition[position])
            {
                holder.favoriate.setChecked(true);

            }else
            {
                holder.favoriate.setChecked(false);

            }
        }




        holder.favoriate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view)
            {
                if (MainActivity.referenceValue==11) {
                    Log.e(TAG," 11 is log");


                     MainActivity.checkPosition[MainActivity.setPosition.get(position)] = holder.favoriate.isChecked() ? true : false;

                }
                else
                {
                    Log.e(TAG,"without 11 is log");
                    MainActivity.checkPosition[position] = holder.favoriate.isChecked() ? true : false;

                }


            }
        });}

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
}
