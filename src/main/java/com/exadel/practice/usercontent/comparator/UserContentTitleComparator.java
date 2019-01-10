package com.exadel.practice.usercontent.comparator;


import com.exadel.practice.usercontent.model.AbstractUserContent;

import java.util.Comparator;

public class UserContentTitleComparator implements Comparator<AbstractUserContent> {



    public int compare(AbstractUserContent o1, AbstractUserContent o2) {


        return o1.getTitle().compareTo(o2.getTitle());


    }
}
