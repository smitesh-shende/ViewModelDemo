package com.gl.viewmodeldemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.gl.viewmodeldemo.models.User;
import com.gl.viewmodeldemo.models.UsersViewModel;

import java.util.List;

/**
 * Main Activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * ListView to display list of items on UI
     */
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list_item);
        UsersViewModel usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        subscribeToModel(usersViewModel);
    }

    /**
     * Subscribe to model to observe any changes in UsersViewModel.
     *
     * @param model Instance of UsersViewModel
     */
    private void subscribeToModel(final UsersViewModel model) {
        // Observe Heroes
        model.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> userList) {
                displayUserList(userList);
            }
        });
    }

    /**
     * Displays list of Users
     * @param userList list of heroes
     */
    private void displayUserList(List<User> userList) {
        //Creating an String array for the ListView
        String[] users = new String[userList.size()];
        //looping through all the mHeroes and inserting the names inside the string array
        for (int i = 0; i < userList.size(); i++) {
            users[i] = userList.get(i).getName();
        }
        mListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                users));
    }
}
