package com.example.vespaair;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vespaair.api.ApiClient;
import com.example.vespaair.api.ApiService;
import com.example.vespaair.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    ApiService apiService;
    Button button;
    EditText titleText, bodyText, userIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        titleText = findViewById(R.id.titleInput);
        bodyText = findViewById(R.id.bodyInput);
        userIdText = findViewById(R.id.userIdInput);
        button = findViewById(R.id.button);

        apiService = ApiClient.getClient().create(ApiService.class);
        button.setOnClickListener(v -> postData());
    }

    private void postData() {
        String title = titleText.getText().toString();
        String body = bodyText.getText().toString();
        int userId = Integer.parseInt(userIdText.getText().toString());

        User user = new User(title, body, userId);

        Call<User> call = apiService.addUser(user);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PostActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PostActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(PostActivity.this, "Gagal: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}