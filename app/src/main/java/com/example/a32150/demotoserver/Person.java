package com.example.a32150.demotoserver;

import java.util.ArrayList;

/**
 * Created by 32150 on 2017/11/27.
 */

public class Person {
    String name;
    int age;
    boolean isMale;
    Data data;
    ArrayList<String> favorite = new ArrayList<>();

    public Person() {
        this("羅西", 46, true, new Data(), null);

    }


    public Person(String name, int age, boolean isMale, Data data, ArrayList<String> favorite) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.data = data;

        if(favorite == null)    {
            this.favorite.add("電影");
            this.favorite.add("上網");
            this.favorite.add("遊戲");
            this.favorite.add("重訓");
        }  else {
            this.favorite = favorite;
        }
    }
}
