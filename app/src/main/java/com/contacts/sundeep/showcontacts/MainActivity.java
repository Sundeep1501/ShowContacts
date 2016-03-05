package com.contacts.sundeep.showcontacts;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Context sharedContext;
        try {
            sharedContext = this.createPackageContext("com.contacts.sundeep.readcontacts", Context.CONTEXT_INCLUDE_CODE);
            List<Contact> contacts = new SQLiteDatabase(sharedContext).getContacts();
            Toast.makeText(this, contacts.size() + " Contacts", Toast.LENGTH_LONG).show();
            recyclerView.setAdapter(new ContactRecyclerAdapter(contacts));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
