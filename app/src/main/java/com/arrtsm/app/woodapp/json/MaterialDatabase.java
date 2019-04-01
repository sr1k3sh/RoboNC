package com.arrtsm.app.woodapp.json;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Material.class}, version = 1)
public abstract  class MaterialDatabase extends RoomDatabase {

    public abstract MaterialDao materialDao();

    private static MaterialDatabase INSTANCE;

    public static MaterialDatabase getInstance(Context context){
        if (INSTANCE==null){
            return Room.databaseBuilder(context.getApplicationContext(),
                    MaterialDatabase.class, "material").build();
        }
        return INSTANCE;
    }
}
