package com.fatec.mom.domain.utils;

import java.util.List;

/**
 * Interface que converte as células do arquivo de codelist em objetos do tipo T.
 *
 * @param <T> objeto de destino.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
public interface ConverterDeserializer<T> {

    List<T> deserialize(List<String> rowCells);
}
