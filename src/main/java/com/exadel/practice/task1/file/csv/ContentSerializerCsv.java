package com.exadel.practice.task1.file.csv;

import java.util.List;

public interface ContentSerializerCsv<T> {
    List<String[]> serializeToCsv(List<T> object);

}
