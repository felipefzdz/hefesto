package org.olid16.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserId {
    
    public static UserId create(Integer id){
        return new AutoValue_UserId(id);
        
    }
    
    public abstract Integer id();
}
