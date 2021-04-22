package com.fatec.mom.domain.utils;

public interface Adapter<T, E> {

    T adapt(final E object);
}
