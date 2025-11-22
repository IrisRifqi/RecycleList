package com.example.vespaair.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vespaair.R;
import com.example.vespaair.api.ApiClient;
import com.example.vespaair.api.ApiService;
import com.example.vespaair.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService apiService;
    Button btnGet, btnPost;
    TextView txtResult;
    EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int postId = Integer.parseInt(editTextInput.getText().toString());
        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        txtResult = findViewById(R.id.txtResult);
        editTextInput = findViewById(R.id.editTextInput);
        btnGet.setOnClickListener(v -> getPost1(postId));
        btnPost.setOnClickListener(v -> addUser());
        apiService = ApiClient.getClient().create(ApiService.class);


        // Contoh GET
        getPost1(postId);

        // Contoh POST / tambah data
        addUser();
    }

    private void getPost1(int postId) {

        Call<User> call = apiService.getPostById(postId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    txtResult.setText("ID: " + user.getId() + "\nTitle: " + user.getTitle() + "\nBody: " + user.getBody() + "\nUser ID: " + user.getUserId());
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