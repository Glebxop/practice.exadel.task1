package com.exadel.practice.usercontent.file.csv;

import java.util.List;

public interface ContentDeserializerCSV<T> {
    List<T> deserializeFromCsv(List<String[]> listUserCntent);

}
