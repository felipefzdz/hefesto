package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Content {

    public static Content create(String value){
        return new AutoValue_Content(value);

    }

    public abstract String value();
}
