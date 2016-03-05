package com.contacts.sundeep.showcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundeep on 3/5/16.
 */
public class SQLiteDatabase extends SQLiteOpenHelper implements ContactTable {

    public SQLiteDatabase(Context context) {
        super(context, "SContacts.db", null, 1);
    }


    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        android.database.sqlite.SQLiteDatabase rDatabase = getReadableDatabase();
        Cursor query = rDatabase.query(TABLE_NAME, null, null, null, null, null, COLUMN_NAME + " asc");
        if (query == null)
            return contacts;

        if (query.moveToFirst()) {
            do {
                contacts.add(getContact(query));
            } while (query.moveToNext());
        }
        query.close();
        return contacts;
    }

    private Contact getContact(Cursor query) {
        Contact contact = new Contact();
        contact.setId(query.getInt(query.getColumnIndex(COLUMN_ID)));
        contact.setRawId(query.getInt(query.getColumnIndex(COLUMN_RAW_ID)));
        contact.setName(query.getString(query.getColumnIndex(COLUMN_NAME)));
        contact.setEmail(query.getString(query.getColumnIndex(COLUMN_ACCOUNT_NAME)));
        contact.setAccountType(query.getString(query.getColumnIndex(COLUMN_ACCOUNT_TYPE)));
        String[] strings = query.getString(query.getColumnIndex(COLUMN_PHONE_NO)).split(",");
        for (String phno : strings) {
            contact.addPhoneno(phno);
        }
        return contact;
    }
}
