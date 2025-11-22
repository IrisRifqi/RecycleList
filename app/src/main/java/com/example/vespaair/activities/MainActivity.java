package com.example.vespaair.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vespaair.R;
import com.example.vespaair.api.ApiClient;
import com.example.vespaair.api.ApiService;
import com.example.vespaair.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService apiService;

    Button btnGet, btnPost;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        txtResult = findViewById(R.id.txtResult);
        btnGet.setOnClickListener(v -> getPost1());
        btnPost.setOnClickListener(v -> addUser());

        apiService = ApiClient.getClient().create(ApiService.class);

        // Contoh GET
        getPost1();

        // Contoh POST / tambah data
        addUser();
    }

    private void getPost1() {
        Call<User> call = apiService.getPostById(1);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    txtResult.setText("User ID: " + user.getId() + "\nName: " + user.getName() + "\nEmail: " + user.getEmail());
                } else {
                    Toast.makeText(MainActivity.this,
                            "Response error: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Gagal: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addUser() {
        // Pastikan class User punya constructor!
        User user = new User("Nicholas", "nico@example.com");

        Call<User> call = apiService.addUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,
                            "Berhasil tambah user!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Error tambah user: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Gagal request: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}