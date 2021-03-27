package com.fatec.mom.domain.utils;

import java.util.List;

public interface ModelDeserializer<T> {

    T deserialize(List<String> rowCells, int order);

    List<T> deserializeAll(List<String> rowCells);
}
