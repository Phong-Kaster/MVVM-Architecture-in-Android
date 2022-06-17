package com.example.mvvmrecycleview.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmrecycleview.Model.Person;

import java.util.ArrayList;

public class PersonViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Person>> objects = new MutableLiveData<>();
    private ArrayList<Person> people = new ArrayList<>();

    public LiveData<ArrayList<Person>> getObjects() {
        return objects;
    }

    public void setObjects(MutableLiveData<ArrayList<Person>> objects) {
        this.objects = objects;
    }

    /**
     * @author Phong-Kaster
     * this function creates records in order to set up recycle view
     * */
    public void initialize()
    {
        Person person1 = new Person("Phong", "0366253623");
        people.add(person1);

        objects.setValue(people);
    }

    /**
     * @author Phong-Kaster
     * @param name is the name of person
     * @param phone is the phone of person
     *
     */
    public void create(String name, String phone)
    {
        if( name.length() < 1 || phone.length() < 10 )
        {
            return;
        }

        Person person = new Person(name, phone);
        people.add(person);

        objects.setValue(people);
    }

    /***
     * @author Phong-Kaster
     * modify a person who is in the list
     */
    public void modify(int position, String name, String phone)
    {
        if( name.length() < 1 || phone.length() < 10 )
        {
            return;
        }

        ArrayList<Person> people = objects.getValue();
        people.get(position).setName(name);
        people.get(position).setPhone(phone);
        objects.setValue(people);
    }

    /**
     * @author Phong-Kaster
     * remove a person from the list
     * */
    public void eradicate(int position)
    {
        if( position < 0)
            return;

        ArrayList<Person> people = objects.getValue();
        people.remove(position);
        objects.setValue(people);
    }
}
