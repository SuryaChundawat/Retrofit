package com.example.mobilestyx.retrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;




import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.github.com";
    String TAG="tag";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"on create");
         final TextView textView=(TextView)findViewById(R.id.txt);
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
               textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG,"error"+t.toString());
            }


        });


      /*  call.enqueue(new retrofit2.Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, retrofit2.Response<Pojo> response) {
                  textView.setText(response.body().getCompany());

            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                 Log.e(TAG,"error"+t.toString());
            }
        });*/





     /*   RestAdapter radapter=new RestAdapter.Builder().setEndpoint(url).build();

        Minterface restInt=radapter.create(Minterface.class);
        restInt.getUser(new Callback<Pojo>() {
            @Override
            public void success(Pojo pojo, Response response)
            {
                textView.setText(pojo.getCompany());

            }

            @Override
            public void failure(RetrofitError error) {
                String fail=error.getMessage();
                Log.e("fail message",fail);

            }
        });*/


    }
}
