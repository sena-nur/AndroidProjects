package com.sena.landmarkbook;

import android.graphics.Bitmap;

public class Singleton {
   private Bitmap selectedImage;
   private static Singleton singleton;


    private Singleton() {

    }

    public Bitmap getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Bitmap selectedImage) {
        this.selectedImage = selectedImage;
    }
    public static Singleton getInstance () {
        if(singleton==null) {
            singleton= new Singleton();
        }
        return singleton;
    }
}
