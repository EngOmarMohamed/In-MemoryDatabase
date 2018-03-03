package com.example;

import java.io.Serializable;
import java.util.Set;

public class SetRequest implements Serializable {
    public final String key;
    private final Object value;

    public SetRequest(String key, Object value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return key;
    }
    public Object getValue(){
        return value;
    }

}
