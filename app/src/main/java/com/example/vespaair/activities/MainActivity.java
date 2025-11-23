package com.example.vespaair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.vespaair.R;
import com.example.vespaair.api.ApiClient;
import com.example.vespaair.api.ApiService;
import com.example.vespaair.model.User;
import com.example.vespaair.PostActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService apiService;
    Button btnGet, btnPost, btnDelete;
    TextView txtResult;
    EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        btnDelete = findViewById(R.id.btnDelete);
        txtResult = findViewById(R.id.txtResult);
        editTextInput = findViewById(R.id.editTextInput);
        apiService = ApiClient.getClient().create(ApiService.class);

        btnGet.setOnClickListener(v -> {
            if (editTextInput.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "ID Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            } else {
                int postId = Integer.parseInt(editTextInput.getText().toString());
                getPost1(postId);
            }
        });

        btnDelete.setOnClickListener(v -> {
            int postId = Integer.parseInt(editTextInput.getText().toString());
            deletePost(postId);
        });
        // pasang click listener utk tombol POST
        setOnClickListener();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getPost1(int postId) {
        Call<User> call = apiService.getPostById(postId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    txtResult.setText(
                            "ID: " + user.getId() +
                                    "\nTitle: " + user.getTitle() +
                                    "\nBody: " + user.getBody()  +
                                    "\nUser ID: " + user.getUserId());
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
    private void deletePost(int postId) {
        Call<Void> call = apiService.deleteUser(postId);
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "User Terhapus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Gagal: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setOnClickListener() {
        btnPost.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        });
    }


}
