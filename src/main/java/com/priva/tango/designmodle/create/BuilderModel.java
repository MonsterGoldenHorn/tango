package com.priva.tango.designmodle.create;

/**
 * 
 * 特征为有建造过程
 * 
 * StringBuilder
 *
 */
public class BuilderModel {
    public static void main(String[] args) {
        Director de = new Director();//常用静态方法
        
        de.build();
    }
}

//抽象建造者
interface HouseBuilder{
    void wall(House house);
    void door(House house);
    void print(House house);
}

//产品
class House{
    String wall;
    String door;
    String print;
}

//建造实现
class FloatHouseBuilder implements HouseBuilder{

    @Override
    public void wall(House house) {
        
    }

    @Override
    public void door(House house) {
        
    }

    @Override
    public void print(House house) {
        
    }
}

//指挥施工者
class Director{
    HouseBuilder builder = null;
    //setter constructor
    
    House house = new House();
    //组装
    House build(){
        builder.wall(house);
        builder.door(house);
        builder.print(house);
        return house;
    }
    
    
    
}
