package com.exadel.practice.task1.file.json;

import java.util.List;

public interface ContentSerializerJson<T> {

    List<T> serializeToJson(List<T> object);
}
