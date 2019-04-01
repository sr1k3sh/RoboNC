package com.arrtsm.app.woodapp.json;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "material")
public class Material {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "material_type")
    private String material_type;

    @ColumnInfo(name = "image_path")
    private String image_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getImage_path() {


        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Material(String material_type, String image_path) {
        this.material_type = material_type;
        this.image_path = image_path;
    }
}
