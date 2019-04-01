package com.arrtsm.app.woodapp.dao;

public class displayfragmentdao {
    private String Name,Image;
    private  String a ="http://192.168.0.118/testfolder/texture.txt";

    public displayfragmentdao(String material_type, String image_path)
    {
        this.setName(material_type);
        this.setImage(image_path);
    }

    public String getName() {
        return Name;
    }

    public void setName(String material_type) {
        Name = material_type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image_path) {
        Image =  image_path;
    }


}
