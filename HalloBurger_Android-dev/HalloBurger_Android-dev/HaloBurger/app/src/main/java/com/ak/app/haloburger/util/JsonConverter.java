package com.ak.app.haloburger.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Created by el on 01/06/18.
 */

public class JsonConverter {

    public static Gson getGson() {
        return new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(final FieldAttributes fieldAttributes) {
                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                return (expose != null) && !expose.serialize();
            }

            @Override
            public boolean shouldSkipClass(final Class<?> aClass) {
                return false;
            }
        }).addDeserializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(final FieldAttributes fieldAttributes) {
                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                return (expose != null) && !expose.deserialize();
            }

            @Override
            public boolean shouldSkipClass(final Class<?> aClass) {
                return false;
            }
        }).create();
    }
}
