package com.example.chase.advancedconcepts.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chase.advancedconcepts.R;
import com.example.chase.advancedconcepts.api.model.GitHubRepo;
import com.example.chase.advancedconcepts.api.service.GitHubClient;
import com.example.chase.advancedconcepts.ui.adapter.GitHubRepoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    public static final String USER_REPO = "repo";

    @BindView(R.id.user_name_text)
    EditText userName;

    @OnClick(R.id.view_repos_button)
    public void startReposActivity() {
        Intent intent = new Intent(this, ListOfRepos.class);
        intent.putExtra(USER_REPO, String.valueOf(userName.getText()).trim());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}
