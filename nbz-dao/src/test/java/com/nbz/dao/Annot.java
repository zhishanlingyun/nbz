package com.nbz.dao;

import com.nbz.dao.annotation.Entity;
import org.junit.Test;

public class Annot {

    @Test
    public void foo(){
        A a = new A();
        B b = new B();
        System.out.println(a.getClass().isAnnotationPresent(Entity.class));
        System.out.println(b.getClass().isAnnotationPresent(Entity.class));
    }


}
