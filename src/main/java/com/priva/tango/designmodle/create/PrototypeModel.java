package com.priva.tango.designmodle.create;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.alibaba.dubbo.common.utils.IOUtils;

/**
 * 原型模式
 * 
 * 当一个系统应该独立于它的产品创建、构成和表示时，要使用 P r o t o t y p e模式；以及
• 当要实例化的类是在运行时刻指定时，例如，通过动态装载；或者
• 为了避免创建一个与产品类层次平行的工厂类层次时；或者
• 当一个类的实例只能有几个不同状态组合中的一种时。建立相应数目的原型并克隆它们
可能比每次用合适的状态手工实例化该类更方便一些。
<important>
结合工厂生成对象，如果工厂生产的产品只有确定数量，对工厂的产品使用原型
例如，数据库使用不同的包进行配置，在运行时只会有一种，数据库连接工厂connectFactory
</important>
单例重使用强调存在状态，原型重创建是创建方式


 * spring bean创建，可以设置Prototype
 * 对象对比，引出深浅拷贝
 * 
 * 浅拷贝时动态获取对象状态
 * 
 * 
 * 违反ocp，已有对象支持克隆需要修改源码
 * 
 */
public class PrototypeModel {

    public static void main(String[] args) {
        Sheep friend = new Sheep("femael", "19", null);
        Sheep sheep = new Sheep("male", "18", friend);
        System.out.println(friend.hashCode());
        try {
            Sheep sheep2 = (Sheep) sheep.cloneDeep2();
            
            System.out.println(sheep2);
            System.out.println(sheep2.friend.hashCode());
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Sheep implements Cloneable,Serializable{
    String sex;
    String age;
    public Sheep friend;
    public ArrayList<Integer> al = new ArrayList<Integer>();
    public Sheep(String sex, String age, Sheep friend) {
        super();
        this.sex = sex;
        this.age = age;
        this.friend = friend;
        al.add(5);
    }

    //浅拷贝,string 与基本数据类型
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Sheep)super.clone();
    }
    
    //深拷贝1，可以递归，不好用
    protected Object cloneDeep1() throws CloneNotSupportedException {
        Object  sheep = null;
        sheep = super.clone();
        Sheep pro = (Sheep) sheep;
        pro.friend = (Sheep) friend.clone();
        return pro;
    }

    //深拷贝2,必须实现Serializable
    protected Object cloneDeep2() throws CloneNotSupportedException {
        
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(this);
            
            bis = new ByteArrayInputStream(bos.toByteArray());
            is = new ObjectInputStream(bis);
            Sheep obj = (Sheep) is.readObject();
            
            return obj;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            org.apache.zookeeper.common.IOUtils.closeStream(bos);
            org.apache.zookeeper.common.IOUtils.closeStream(os);
            org.apache.zookeeper.common.IOUtils.closeStream(bis);
            org.apache.zookeeper.common.IOUtils.closeStream(is);
        }
        return null;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Sheep [sex=");
        builder.append(sex);
        builder.append(", age=");
        builder.append(age);
        builder.append(", friend=");
        builder.append(friend);
        builder.append(", al=");
        builder.append(al.get(0));
        builder.append("]");
        return builder.toString();
    }
}