package com.example.vespaair;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.vespaair.activities.MainActivity;
import com.example.vespaair.api.ApiClient;
import com.example.vespaair.api.ApiService;
import com.example.vespaair.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    ApiService apiService;
    Button button;
    EditText idText, titleText, bodyText, userIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        idText = findViewById(R.id.idTxt);
        titleText = findViewById(R.id.titleTxt);
        bodyText = findViewById(R.id.bodyTxt);
        userIdText = findViewById(R.id.userIdTxt);
        button = findViewById(R.id.button);
        apiService = ApiClient.getClient().create(ApiService.class);
        setOnClickListener();
        postID();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void postID() {
        User user = new User("Nicholas", "nico@example.com");
        Call<User> call = apiService.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Toast.makeText(PostActivity.this, "Berhasil tambah!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostActivity.this, "Response error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PostActivity.this,
                        "Gagal: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setOnClickListener(){
            Toast.makeText(PostActivity.this, "Posted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PostActivity.this, MainActivity.class);
            startActivity(intent);
    }


}