package com.fatec.mom.domain.utils;

import java.util.List;

public interface ConverterDeserializerWithOrder<T> {

    T deserialize(List<String> rowCells, int order);
}
