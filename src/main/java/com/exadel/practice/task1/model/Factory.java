package com.exadel.practice.task1.model;


public class Factory {

    public AbstractUserContent abstractUserContentNew(String[] type) {
        AbstractUserContent abstractUserContent = null;
        switch (Integer.valueOf(type[0])) {
            case 2:
                abstractUserContent = new Annotation(Integer.valueOf(type[1]),
                        new User(Integer.valueOf(type[2]), type[3], type[4]), type[5], type[6]);
                break;
            case 0:
                abstractUserContent = new Attachment(Integer.valueOf(type[1]),
                        new User(Integer.valueOf(type[2]), type[3], type[4]), type[5], Double.valueOf(type[6]));
                break;
            case 1:
                abstractUserContent = new Comment(Integer.valueOf(type[1]),
                        new User(Integer.valueOf(type[2]), type[3], type[4]), type[5], type[6]);
                break;


        }
        return abstractUserContent;
    }

}
