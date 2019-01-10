package com.exadel.practice.usercontent.file.csv;

import java.util.List;

public interface ContentSerializerCsv<T> {
    List<String[]> serializeToCsv(List<T> object);

}
