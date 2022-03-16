package com.priva.tango.designmodle.construct;

import java.util.HashMap;
import java.util.Map;

public class FlyWeightMode {

    /**
     * 运用共享技术有效地支持大量细粒度的对象
     * 
     * 
F l y w e i g h t模式通常和C o m p o s i t e ( 4 . 3 )模式结合起来，用共享叶结点的有向无环图实现一个
逻辑上的层次结构。
通常，最好用F l y w e i g h t实现S t a t e ( 5 . 8 )和S t r a t e g y ( 5 . 9 )对象
     * 
     * 
     * 内部状态：稳定属性，分类少
     * 外部状态：属性不稳定，分类多
     * @param args
     */
    //池类  常量池
    public static void main(String[] args) {
        String s1="aa";
        String s2 = new String("aa");
        String s3 = "aa".intern();
        
        System.out.println(s2==s1);
        System.out.println(s1==s3);
        
        
        Integer i1 = 127;//-128----127
        Integer i2 = new Integer(127);
        Integer i3 = Integer.valueOf(127);
        System.out.println(i1==i2);
        System.out.println(i1==i3);
        
        ///client
        System.out.println("开始下旗");
        
        
        Instance in1 = ChessFactory.getBlack("兵", "红");
        in1.x=+1;
        System.out.println("过河");
        Instance in2 = ChessFactory.getBlack("兵", "黑");
        in2.x=+1;
        System.out.println("黑的也过河");
        Instance in3 = ChessFactory.getBlack("兵", "红");
        in3.x=+1;
        System.out.println("红的还要过河");
        Instance in4 = ChessFactory.getBlack("象", "黑");
        in4.x=+2;
        in4.y=+2;
        System.out.println("吃了");
    }
}

class ChessFactory{
    private static Map<String,Piece> map = new HashMap<String,Piece>();
    
    public static Instance getBlack(String name,String color){
        Piece piece = map.get(name);
        if(null==piece){
            piece = new Piece(name);
            map.put(name, piece);
        }
        
        Instance intst = new Instance(piece, color);
        return intst;
    }
}

//外部状态
class Instance{
    
    public Instance(Piece piece, String color) {
        this.piece = piece;
        this.color = color;
    }
    Piece piece;
    String color;
    int x;
    int y;
    
}
//内部状态
class Piece{
    private String name;
    private int size;
    private int weight;
    private String producer;
    public Piece(String name) {
        this.name = name;
        System.out.println("创建了一个"+name);
    }
}


