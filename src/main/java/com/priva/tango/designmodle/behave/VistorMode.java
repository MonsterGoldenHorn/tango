package com.priva.tango.designmodle.behave;


public class VistorMode {
/**
 * 访问者可以对功能进行统一，可以做报表、UI、拦截器与过滤器。
 * 
 * 模板方法中被操纵对象是一类，访问者中对象可以是不同类的集合，访问者模式对模板中实现方法进一步抽象，两者可以结合
 * 
 * xml解析
 * 表单的json在vue中解析的是一个视图，在后台解析的是数据库结构
 * 
 * 
 */
    
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.add(new ComputerMovieVisitor());
     }
}
 interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
 }
 //NODE
 class Keyboard implements ComputerPart {
     
     @Override
     public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
     }
     
     public void keyDown() {
         System.out.println("输入");
     }
  }
//NODE
 class Monitor implements ComputerPart {
     
     @Override
     public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
     }
     
     public void show() {
         System.out.println("展示");
     }
  }
//NODE
 class Mouse implements ComputerPart {
     
     @Override
     public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
     }
     
     public void click() {
         System.out.println("点击");
     }
  }
 // O b j e c t S t r u c t u r e
 /**
— 能枚举它的元素。
— 可以提供一个高层的接口以允许该访问者访问它的元素。
— 可以是一个复合（参见 C o m p o s i t e（4 . 3））或是一个集合，如一个列表或一个无序集合。
  */
 class Computer{//驱动部分
     
     ComputerPart[] parts;
   
     public Computer(){
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};      
     } 
   
   
     public void add(ComputerPartVisitor computerPartVisitor) {
        for (int i = 0; i < parts.length; i++) {
           parts[i].accept(computerPartVisitor);
        }
      //  computerPartVisitor.visit(this);
     }
  }
 interface ComputerPartVisitor {
     public void visit(Mouse mouse);
     public void visit(Keyboard keyboard);
     public void visit(Monitor monitor);
  }
 //访问者，实现不同的操作
 class ComputerMovieVisitor implements ComputerPartVisitor {
     //ComputerPart当访问者访问对象结构中的每一个元素时，它可能会累积状态。如果没有访问者，这一状态将作为额外的参数传递给进行遍历的操作，或者定义为全局变量
     @Override
     public void visit(Mouse mouse) {
        System.out.println("connect Mouse.");
//        mouse.click();
     }
     @Override
     public void visit(Keyboard keyboard) {
        System.out.println("connect Keyboard.");
//        keyboard.keyDown();
     }
     @Override
     public void visit(Monitor monitor) {
        System.out.println("connect Monitor.");
//        monitor.show();
     }
  }
 
