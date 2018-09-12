package com.bamboo.practice;

import com.bamboo.model.Person;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @autor wls on 2018/9/11
 */
public class PersonComparator implements Comparator<Person> {


    @Override
    public int compare(Person o1, Person o2) {
        if(o1.getAge()>o2.getAge()){
            return 1;
        }
        if(o1.getAge()<o2.getAge()){
            return -1 ;
        }
        return 0;
    }

    public static void main (String[] args){
        Person zhang = new Person("zhang", 3);
        Person wang = new Person("wang", 2);
        Person li = new Person("li", 1);
        Person persons[] ={zhang,wang,li};
        /*Arrays.sort(persons);
        System.out.println(Arrays.toString(persons));*/
        Integer a[] = {3,2,1};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        System.out.println(Arrays.toString(persons));
                 Arrays.sort(persons, new PersonComparator());
                System.out.println(Arrays.toString(persons));
    }
}
