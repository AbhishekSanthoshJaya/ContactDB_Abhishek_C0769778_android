package com.aby.contactdb_abhishek_c0769778_android.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.aby.contactdb_abhishek_c0769778_android.model.Contact;

@Database(entities = Contact.class , exportSchema = false , version = 4)

public abstract class ContactDB extends RoomDatabase {


    public static final String DB_NAME = "user_db";

    private static ContactDB uInstance;


    public static ContactDB getInstance(Context context)
    {
        if(uInstance == null)
        {
            uInstance = Room.databaseBuilder(context.getApplicationContext(), ContactDB.class, ContactDB.DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return uInstance;
    }

    public abstract ContactDao daoObjct();
}
