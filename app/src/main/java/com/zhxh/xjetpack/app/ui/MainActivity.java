package com.zhxh.xjetpack.app.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;
import android.widget.EditText;

import com.zhxh.xjetpack.R;
import com.zhxh.xjetpack.data.model.User;
import com.zhxh.xjetpack.databinding.ActivityMainBinding;
import com.zhxh.xjetpack.app.adapter.MainUserAdapter;
import com.zhxh.xjetpack.app.viewmodel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.firstName)
    EditText etfirstName;
    @BindView(R.id.lastName)
    EditText etlastName;
    @BindView(R.id.age)
    EditText etAge;
    @BindView(R.id.saveUser)
    Button saveUser;

    MainViewModel mainViewModel;
    private List<User> userList;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Initialize the View Model in your layout
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getUserList().observe(this, users -> {
            if (MainActivity.this.userList == null) {
                setListData(users);
            }
        });

        //Save a new User
        saveUser.setOnClickListener(v -> {
            String firstName = etfirstName.getText().toString().trim();
            String lastName = etlastName.getText().toString().trim();
            String age = etAge.getText().toString().trim();

            if (firstName.isEmpty()) {
                etfirstName.setError("Please Enter First Name");
                return;
            }

            if (lastName.isEmpty()) {
                etlastName.setError("Please Enter Last Name");
                return;
            }

            if (age.isEmpty()) {
                etAge.setError("Please Enter Your Age");
                return;
            }

            saveUser(firstName, lastName, age);

        });

    }

    private void saveUser(String firstName, String lastName, String age) {
        mainViewModel.addUser(new User(
                firstName,
                lastName,
                age
        ));
    }

    public void setListData(final List<User> userList) {
        this.userList = userList;
        if (userList.isEmpty()) {
            //Handle No User here - Like show an empty view
        } else {
            MainUserAdapter adapter =
                    new MainUserAdapter(userList, this);
            activityMainBinding.recyclerView.setAdapter(adapter);
        }
    }
}
