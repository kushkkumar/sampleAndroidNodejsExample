package com.example.nodecomunication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="http://10.0.2.2:6000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map=new HashMap<>();
                map.put("email",((TextView)findViewById(R.id.editText)).getText().toString());
                map.put("password",((TextView)findViewById(R.id.editText2)).getText().toString());

                Call<loginResult> call=retrofitInterface.executeResult(map);
                call.enqueue(new Callback<loginResult>() {
                    @Override
                    public void onResponse(Call<loginResult> call, Response<loginResult> response) {

                        if(response.code()==200){
                            loginResult loginResult=response.body();

                            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                    builder.setTitle(loginResult.getName())
                                    .setMessage(loginResult.getEmail())
                                    .show();

                        }
                        else
                        {
                            if(response.code()==404){
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<loginResult> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }



}
