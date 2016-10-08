package com.example.mobilestyx.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    String url = "https://api.github.com";
    String TAG="tag";
    private RecyclerView recyclerView;
    private List<Actor> listing=new ArrayList<Actor>();
    private List<Actor> Dublisting =new ArrayList<Actor>();
    public static ArrayList<Integer>setPosition;

    public List<Actor> fav_list=new ArrayList<Actor>();

    private MovieAdapter movieAdapter;
    public static Boolean[] checkPosition;
    public static int referenceValue=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"on create = ");
        // final TextView textView=(TextView)findViewById(R.id.txt);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading......");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Minterface apiService =
                ApiClient.getClient().create(Minterface.class);
            Call<Example>call=apiService.getUser();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                progressDialog.dismiss();
                listing =response.body().getActors();
                Dublisting.addAll(listing);
                Log.e(TAG,"dublisting size is"+Dublisting.size());


                checkPosition=new Boolean[Dublisting.size()];
                for (int i = 0; i <Dublisting.size() ; i++) {
                    checkPosition[i]=false;
                }




                 recyclerView=(RecyclerView)findViewById(R.id.list_view);
                 movieAdapter= new MovieAdapter(Dublisting,getApplicationContext());
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(movieAdapter);



              // textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG,"error"+t.toString());
            }


        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar, (Menu) menu);
         MenuItem searchItem = menu.findItem(R.id.search);

        // this is two method is important to call interface for ontext change
         SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getTitle().toString()){
            case "search" :

                break;
            case "favoriteList":
                Log.e("fav list clicked","log");
                favoriteList();
                break;
            case "AllList":
                Log.e("All list clicked","log");
                AllList();
                break;


        }


        return super.onOptionsItemSelected(item);
    }

    private void AllList() {

        referenceValue=0;
        Dublisting.clear();
        Log.e(TAG,"dublisting size"+Dublisting.size());
        Dublisting.addAll(listing);
        movieAdapter.notifyDataSetChanged();

    }


    private void favoriteList() {
        referenceValue=11;
        setPosition=new ArrayList<Integer>();
        Dublisting.clear();
        fav_list.clear();


        for (int i=0;i<checkPosition.length;i++)
        {
            if(checkPosition[i]){
                setPosition.add(i);
                fav_list.add(listing.get(i));
            Log.e(TAG,"fav list size"+fav_list.size());}

        }
        Log.e(TAG,"dublisting size"+Dublisting.size());
        Dublisting.addAll(fav_list);
        movieAdapter.notifyDataSetChanged();






    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
         List<Actor> filteredModelList = filter(listing, newText);
        Log.e("search ",""+newText);
        movieAdapter.setFilter(filteredModelList);

        return true;
    }

    private List<Actor> filter(List<Actor> models, String query) {
        query = query.toLowerCase();

        final List<Actor> filteredModelList = new ArrayList<>();
        for (Actor model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
