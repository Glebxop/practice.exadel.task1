package com.exadel.practice.task1.comparator;


import com.exadel.practice.task1.model.AbstractUserContent;

import java.util.Comparator;

public class UserContentTitleComparator implements Comparator<AbstractUserContent> {



    public int compare(AbstractUserContent o1, AbstractUserContent o2) {
        /*if (o1.getTitle().length()>o2.getTitle().length())
            return -1;
        else if (o1.getTitle().length()<o2.getTitle().length())
            return 1;
        else return 0;*/

        //return ((String)o1.getTitle()).compareTo(o2.getTitle());


        return -((Integer)o1.getTitle().length()).compareTo(o2.getTitle().length());


    }
}
