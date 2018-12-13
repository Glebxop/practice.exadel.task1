package com.exadel.practice.task1.file.json;

public interface ContentDeserializerJson <T>{

    T deserializeFromJson(String content);
}
