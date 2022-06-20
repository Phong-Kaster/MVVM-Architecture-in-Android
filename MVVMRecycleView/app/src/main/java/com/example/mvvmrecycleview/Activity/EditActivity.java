package com.example.mvvmrecycleview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmrecycleview.MainActivity;
import com.example.mvvmrecycleview.R;
import com.example.mvvmrecycleview.ViewModel.EditActivityViewModel;
import com.example.mvvmrecycleview.ViewModel.MainActivityViewModel;

/**
 * ? 2 activities do not use the same viewModel.
 * Each activity have its own view model.
 *
 * ? we have to store data's state. For example, rotating screen could kill activity's life circle
 * Therefore, storing data's state is a important mission.
 */
public class EditActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPhone;
    private Button buttonEdit;
    private Button buttonRemove;

    private EditActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        /*Step 1*/
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String position = getIntent().getStringExtra("position");



        /*Step 2*/
        setupComponent();
        setupScreen(name, phone);


        /**declare "EditViewModel" to be sure abide by MVVM architecture*/
        viewModel = new ViewModelProvider(this).get(EditActivityViewModel.class);

        /*Step 3*/
        buttonEdit.setOnClickListener(view->{
            String modifiedName = txtName.getText().toString();
            String modifiedPhone = txtPhone.getText().toString();

            viewModel.modify(position, modifiedName, modifiedPhone);
        });

        buttonRemove.setOnClickListener(view -> {
            viewModel.eradicate(position);
            finish();
        });
    }

    private void setupComponent() {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonRemove = findViewById(R.id.buttonRemove);
    }

    private void setupScreen(String name, String phone)
    {
        txtName.setText( name );
        txtPhone.setText( phone );
    }
}