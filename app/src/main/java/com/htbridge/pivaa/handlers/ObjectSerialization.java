package com.htbridge.pivaa.handlers;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectSerialization implements Serializable {
    private String proof = ":D";
    private String location;

    public ObjectSerialization(String location) {
        this.location = location;
    }

    /**
     * Method for setting value of proof
     * @param p
     */
    public void setProof(String p) {
        this.proof = p;
    }

    /**
     * Method for getting value of proof
     * @return
     */
    public String getProof() {
        return this.proof;
    }

    /**
     * Saving object
     */
    public void saveObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("proof", getProof());
            try (FileOutputStream fos = new FileOutputStream(new File(this.location))) {
                fos.write(json.toString().getBytes("UTF-8"));
            }

            Log.i("htbridge", "[ SAVE ] Serialization SUCCESS!");
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("htbridge", "[ SAVE ] Serialization FAIL!");
    }

    /**
     * Loading object
     */
    public void loadObject() {
        try {
            File f = new File(this.location);
            byte[] data = new byte[(int) f.length()];
            try (FileInputStream fis = new FileInputStream(f)) {
                fis.read(data);
            }
            JSONObject json = new JSONObject(new String(data, "UTF-8"));
            this.setProof(json.getString("proof"));

            Log.i("htbridge", "[ LOAD ] Serialization SUCCESS!");
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("htbridge", "[ LOAD ] Serialization FAIL!");
    }
}
