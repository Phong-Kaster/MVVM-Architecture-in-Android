package com.example.mvvmrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.example.mvvmrecycleview.Model.Person;
import com.example.mvvmrecycleview.RecycleViewAdapter.PersonRecycleViewAdapter;
import com.example.mvvmrecycleview.ViewModel.MainActivityViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
/**
 * ? 2 activities do not use the same viewModel.
 * Each activity have its own view model.
 *
 * ? we have to store data's state. For example, rotating screen could kill activity's life circle
 * Therefore, storing data's state is a important mission.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainActivityViewModel viewModel;

    private Button buttonCreate;
    private PersonRecycleViewAdapter adapter;

    private int i = 1;
    public static WeakReference<MainActivity> weakActivity;

    public static MainActivity getmInstanceActivity() {
        return weakActivity.get();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weakActivity = new WeakReference<>(MainActivity.this);


        setupComponent();
        setupViewModel();
        setupEvent();
    }

    /**
     * @author Phong-Kaster
     * this function will map defined component in this activity with XML layout.
     * */
    private void setupComponent()
    {
        buttonCreate = findViewById(R.id.buttonCreate);
        recyclerView = findViewById(R.id.mainRecycleView);
    }




    private void setupViewModel()
    {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.initialize();

        viewModel.getObjects().observe(this, new Observer<ArrayList<Person>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<Person> people) {
                setupRecycleView(people);
            }
        });
    }

    /**
     * @author Phong-Kaster
     * pour data into recycle view
     * */
    private void setupRecycleView(ArrayList<Person> objects)
    {
        adapter = new PersonRecycleViewAdapter(this, objects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupEvent()
    {
        buttonCreate.setOnClickListener(view->{
            i++;
            viewModel.create("Phong " + i, "0366253623" );
        });
    }


    /**
     * @author Phong-Kaster
     * @param position is the position of person whom is updated
     * @param name is the name of updated person
     * @param phone is the phone of updated phone
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updatePerson(int position, String name, String phone)
    {
        viewModel.modify(position, name, phone);
    }

    /**
     * @author Phong-Kaster
     * @param position is the location where person is eradicated
     * */
    public void eradicatePerson(int position)
    {
        viewModel.eradicate(position);
    }
}