package com.priva.tango.designmodle.behave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class IteratorMode {
/**
 * 实现iterator,对不同集合类型 <important>提供遍历方案 </important>,可以自己定义反向迭代器
 * 
 * java集合类ArrayList  itr与HashTable Enumerator(指针类型的迭代方式)
 * 
 * 1) 谁控制该迭代 
 * 2) 谁定义遍历算法
 * 3) 迭代器健壮程度如何 fast fail
 * 4) 附加的迭代器操作
 * 
 * 
 * 
• 访问一个聚合对象的内容而无需暴露它的内部表示。
• 支持对聚合对象的多种遍历。
• 为遍历不同的聚合结构提供一个统一的接口 (即, 支持多态迭代)。


1）它支持以不同的方式遍历一个聚合 复杂的聚合可用多种方式进行遍历。例如 , 代码生
成和语义检查要遍历语法分析树。
3) 在同一个聚合上可以有多个遍历 每个迭代器保持它自己的遍历状态。因此你可以同
时进行多个遍历
 */
    
    public static void main(String[] args) {
       
         Organaziton root = new College("超级大学");
        Organaziton dep1 = new DepartMent("超人学院");
        Organaziton dep2 = new DepartMent("缝纫学院");
        Organaziton dep3 = new DepartMent("缝纫机学院");
        root.addChild(dep1);
        root.addChild(dep2);
        root.addChild(dep3);
        root.getName();
    }
    
}

class CollegeIterator implements Iterator{
    private Organaziton[] arr;
    private int size;
    private int index = 0;
    public CollegeIterator(Organaziton[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    @Override
    public boolean hasNext() {
        return size>0&&null!=arr[index];
    }

    @Override
    public Object next() {
        if(hasNext()){
           return arr[index++];
        }
        return null;
    }
//数组的这里没实现成功
    @Override
    public void remove() {
        Organaziton[] arr2 = null;
        if(size>0){
            arr2 = new Organaziton[--size];
            for(int i=0 ;i<size;i++){
                if(i==index){
                    continue;
                }
                arr2[i] = arr[i];
                arr = arr2;
            }
         }
    }
    
}

//组合模式
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
     Organaziton[] arr;
     int arrSize = 0;
     College(String title) {
         this.title = title;
         arr = new Organaziton[5];
     }

     @Override
     public void getName() {
         //System.out.println("学校"+this.title);
         this.print();
        
     }
     
     public void print() {
         CollegeIterator c = new CollegeIterator(arr);
         while(c.hasNext()){
             Organaziton o = (Organaziton) c.next();
             o.getName();
         }
    }
     
     @Override
     public void addChild(Organaziton o) {
         arr[arrSize] = o;
         arrSize++;
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