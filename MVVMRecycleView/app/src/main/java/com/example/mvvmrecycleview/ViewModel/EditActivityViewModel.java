package com.example.mvvmrecycleview.ViewModel;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.mvvmrecycleview.MainActivity;

public class EditActivityViewModel extends ViewModel {

    /**
     * @author Phong-Kaster
     * @param position is the item's location
     * @param name is the newer name
     * @param phone is the latest phone
     */
    public void modify(String position, String name, String phone)
    {
        int integerPosition = Integer.parseInt(position);

        if( integerPosition < 0 || name.length() < 1|| phone.length() < 10)
            return;

        MainActivity.getmInstanceActivity().updatePerson(integerPosition, name, phone);
    }

    /***
     * @author Phong-Kaster
     * @param position is the location of removed item
     */
    public void eradicate(String position)
    {
        int interPosition = Integer.parseInt(position);
        if( interPosition < 0)
            return;

        MainActivity.getmInstanceActivity().eradicatePerson(interPosition);
    }

}
