package com.example.vespaair.activities;

import android.content.Intent;
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
import com.example.vespaair.PostActivity;
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

        // init UI
        btnGet = findViewById(R.id.btnGet);
        btnPost = findViewById(R.id.btnPost);
        txtResult = findViewById(R.id.txtResult);
        editTextInput = findViewById(R.id.editTextInput);
        apiService = ApiClient.getClient().create(ApiService.class);
        btnGet.setOnClickListener(v -> {
            int postId = Integer.parseInt(editTextInput.getText().toString());
            getPost1(postId);
        });

        setOnClickListener();
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
    public void setOnClickListener() {
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        startActivity(intent);
    }
}
