package com.priva.tango.designmodle.behave;

import java.util.ArrayList;
import java.util.List;

public class MementoPattern {
/**
 * 别名：Token
 * 
 * 
 * 
 * 
Command(5.2): 命令可使用备忘录来为可撤消的操作维护状态。
Iterator(5.4):在同一个集合上中可有多个状态一起工作

 * 
 */
}

class Originator {
    
  public  String state;
  public Originator(){
      
  }
// 创建一个备忘录对象
  public Memento createMemento(){
      return new Memento(this);
    }
    // 根据备忘录对象恢复原发器状态
    public void restoreMemento(Memento m){
        state = m.state;
    }
    public void setState(String state)
    {
    this.state=state;
    }
    public String getState()
    {
    return this.state;
    }
  }

class Memento {
    public String state;
    public Memento(Originator o){
        state = o.state;
      }
      public void setState(String state)
      {
            this.state=state;
      }
      public String getState()
      {
             return this.state;
       }
  } 

class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();
  
    public void add(Memento state){
       mementoList.add(state);
    }
  
    public Memento get(int index){
       return mementoList.get(index);
    }
 }

