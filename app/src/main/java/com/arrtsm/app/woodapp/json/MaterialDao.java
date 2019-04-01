package com.arrtsm.app.woodapp.json;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MaterialDao {

    @Query("SELECT * FROM material")
    LiveData<List<Material>> getAll();

    @Insert
    void insertAll(List<Material> materials);

    @Query("SELECT * FROM material where material_type = :material LIMIT 1")
    LiveData<Material> getMaterial(String material);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Material material);

    @Update
    void update(Material material);

    @Delete
    void delete(Material material);
}
