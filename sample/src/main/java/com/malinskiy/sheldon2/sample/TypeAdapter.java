package com.malinskiy.sheldon2.sample;

import com.malinskiy.sheldon2.IGateway;
import com.malinskiy.sheldon2.adapter.IPreferenceAdapter;
import com.malinskiy.sheldon2.annotation.Adapter;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


@Adapter
public class TypeAdapter implements IPreferenceAdapter<Type> {
    @Nonnull @Override
    public Observable<Type> observe(@Nonnull String key, @Nonnull Type defaultValue, @Nonnull IGateway gateway) {
        return gateway.observeString(key, defaultValue.name())
                      .map(new Function<String, Type>() {
                          @Override public Type apply(String s) {
                              return Type.valueOf(s);
                          }
                      });
    }

    @Override public void put(@Nonnull String key, @Nonnull Type value, IGateway gateway) {
        gateway.putString(key, value.name());
    }
}
