package com.priva.tango.designmodle.construct;

import java.util.ArrayList;
import java.util.List;

public class CompositeMode {
/**
 * <important> 装饰者组合关系有主次，添加功能，组合模式类型平级或者主次不明显，有树形关系
 * 
 * 
 * 描述了如何使用递归组合,可以弄个菜单树，文件夹，组织架构
 * 
 * HashMap组合模式put叶子节点 putAll中间节点
 *
 * 将对象组合成树形结构以表示“部分 -整体”的层次结构。C o m p o s i t e使得用户对单个对象
和组合对象的使用具有一致性。
 * 
 *D e c o r a t o r模式经常与 C o m p o s i t e模式一起使用。当装饰和组合一起使用时，它们
通常有一个公共的父类。因此装饰必须支持具有 A d d、R e m o v e和GetChild 操作的C o m p o n e n t接口。
 */
    
    public static void main(String[] args) {
        Organaziton root = new College("超级大学");
        Organaziton dep1 = new DepartMent("超人学院");
        Organaziton dep2 = new DepartMent("缝纫学院");
        Organaziton subj2 = new Subject("缝补修理");
        Organaziton subj1 = new Subject("计算机");
        Organaziton subj3 = new Subject("垃圾回收");
        
        dep1.addChild(subj3);
        dep1.addChild(subj2);
        dep1.addChild(subj1);
        dep2.addChild(subj1);
        root.addChild(dep1);
        root.addChild(dep2);
        
        root.getName();
    }
    
    
}

abstract class Organaziton{
   abstract void getName();
   void addChild(Organaziton o){
       
   }
   void addAll(List<Organaziton> list){
       
   }
}
//根节点
class College extends Organaziton{
    private String title;
    List<Organaziton> list = new ArrayList<Organaziton>();
    
    College(String title) {
        this.title = title;
    }

    @Override
    public void getName() {
        System.out.println("学校"+this.title);
        for(Organaziton o:list){
            o.getName();
        }
    }

    @Override
    public void addChild(Organaziton o) {
        list.add(o);
    }

    @Override
    public void addAll(List<Organaziton> list) {
        // TODO Auto-generated method stub
        
    }
}
//节点
class DepartMent extends Organaziton{
    private String title;
    List<Organaziton> list = new ArrayList<Organaziton>();
    public DepartMent(String title) {
        this.title = title;
    }
    @Override
    public void getName() {
       System.out.println("部门"+this.title);
       for(Organaziton o:list){
           o.getName();
       }
    }

    @Override
    public void addChild(Organaziton o) {
        list.add(o);
    }

    @Override
    public void addAll(List<Organaziton> list) {
        // TODO Auto-generated method stub
        
    }
}
//叶子节点
class Subject extends Organaziton{
    private String title;
    public Subject(String title) {
        this.title = title;
    }
    @Override
    public void getName() {
        System.out.println("学科--"+this.title+"---没有其他了");
    }
}
