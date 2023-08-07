package com.example.unifinder.RegisterPassenger;

import com.google.firebase.storage.StorageReference;

public class FirebaseModel {
    StorageReference storageReference;
    String fileName;

    public FirebaseModel(StorageReference storageReference, String fileName) {
        this.storageReference = storageReference;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public StorageReference getStorageReference() {
        return storageReference;
    }

    public void setStorageReference(StorageReference storageReference) {
        this.storageReference = storageReference;
    }
}