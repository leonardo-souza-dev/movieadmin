package com.example.leo.movieadmin;

import org.json.JSONObject;

/**
 * Created by rm48306 on 27/06/2016.
 */
public class Message {

    private final boolean sucess;
    private final String message;
    private final JSONObject object;

    public Message(final boolean s, final String m, final JSONObject o) {
        this.sucess = s;
        this.message = m;
        this.object = o;
    }

    public boolean getSucess() {
        return this.sucess;
    }

    public String getMessage() {
        return this.message;
    }

    public JSONObject getObject() {
        return this.object;
    }

    public String getObject(String key) throws Exception {
        return this.object.getString(key);
    }
}
