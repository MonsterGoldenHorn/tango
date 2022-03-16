package com.priva.tango.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AutomicLock {

    public static void main(String[] args) {
        User u1 = new User(1, "zz");
        User u2 = new User(2, "cc");
        AtomicStampedReference<User> u = new AtomicStampedReference<User>(u1, 1);
        u.compareAndSet(u1, u1, u.getStamp(), u.getStamp()+1);
        System.out.println(u.getStamp());
        u.compareAndSet(u1, u1, u.getStamp(), u.getStamp()+1);
        System.out.println(u.getStamp());
    }

    public void aba(){
        AtomicReference<User> u = new AtomicReference<User>();
        User u1 = new User(1, "zz");
        User u2 = new User(2, "cc");
        u.set(u1);
        System.out.println(u.compareAndSet(u1,u1)+"-----"+u.get());
        System.out.println(u.compareAndSet(u1,u2)+"-----"+u.get());
        System.out.println(u.compareAndSet(u2,u1)+"-----"+u.get());
        System.out.println(u.compareAndSet(u1,u2)+"-----"+u.get());
    }
}

class User{
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}