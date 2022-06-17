package com.example.mvvmrecycleview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvmrecycleview.MainActivity;
import com.example.mvvmrecycleview.Model.Person;
import com.example.mvvmrecycleview.R;
import com.example.mvvmrecycleview.ViewModel.PersonViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPhone;
    private Button buttonEdit;
    private Button buttonRemove;

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



        /*Step 3*/
        buttonEdit.setOnClickListener(view->{
            String modifiedName = txtName.getText().toString();
            String modifiedPhone = txtPhone.getText().toString();

            MainActivity.getmInstanceActivity().updatePerson(Integer.parseInt(position), modifiedName, modifiedPhone);
            Toast.makeText(this, "Updated Successfully !", Toast.LENGTH_LONG).show();
        });

        buttonRemove.setOnClickListener(view -> {
            MainActivity.getmInstanceActivity().eradicatePerson(Integer.parseInt(position));
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