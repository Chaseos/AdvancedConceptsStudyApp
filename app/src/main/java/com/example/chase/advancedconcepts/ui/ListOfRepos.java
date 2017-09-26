package com.example.chase.advancedconcepts.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chase.advancedconcepts.R;
import com.example.chase.advancedconcepts.api.model.GitHubRepo;
import com.example.chase.advancedconcepts.api.service.GitHubClient;
import com.example.chase.advancedconcepts.ui.adapter.GitHubRepoAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.chase.advancedconcepts.ui.MainActivity.USER_REPO;

public class ListOfRepos extends AppCompatActivity {
    @BindView(R.id.pagination_list)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repos_list);

        ButterKnife.bind(this);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call =
                client.reposForUser(getIntent().getStringExtra(USER_REPO));

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();

                listView.setAdapter(new GitHubRepoAdapter(ListOfRepos.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(ListOfRepos.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
