package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Title {

    public static Title create(String title){
        return new AutoValue_Title(title);

    }

    public abstract String title();
}
