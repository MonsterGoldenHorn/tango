package com.priva.tango.designmodle.behave;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MediatorMode {
/**
 * 中介模式和传统的中介完全不一样，传统中介实际是代理。婚介所是中介模式
 *
F a c a d e与中介者的不同之处在于它是对一个对象子系统进行抽象，从而提供了一个
更为方便的接口。它的协议是单向的，即 F a c a d e对象对这个子系统类提出请求，但反之则不
行。相反，M e d i a t o r提供了各C o l l e a g u e对象不支持或不能支持的协作行为，而且协议是多向
的。
C o l l e a g u e可使用O b s e r v e r模式与M e d i a t o r通信


spring外层框架，可以将不同组件的不同实现方式通过spring统一管理协作


系统相互调用，关系复杂呈网状时，需要使用中介者把关系调整为星状，个人理解为监听者模式的升级，相互监听
 最大特点是：参与者的交互，参与者之间的解耦，不偏向某一方

1.Mediator
中介者定义一个接口用于与各同事（Colleague）对象通信。

2.ConcreteMediator
具体中介者通过协调各同事对象实现协作行为，了解并维护它的各个同事。

3.Colleague:
抽象同事类。

４.Colleagueclass
具体同事类。每个具体同事类都只需要知道自己的行为即可，但是他们都需要认识中介者

 */
    
    public static void main(String[] args) {
        DataCenter dc = new DataCenter();
        dc.addColleage(new Mysql());
        DataSource ds1 = new SqlServer();
        DataSource ds2 = new Oracle();
        dc.addColleage(ds1);
        dc.addColleage(ds2);
        
        dc.getData(ds1);
        dc.getData(ds2);
        
    }
}

class DataCenter{//Mediator ConcreteMediator
    private List<DataSource> list = new ArrayList<DataSource>();
    
    void addColleage(DataSource dataSource) {
        list.add(dataSource);
    }
    
    void getData(DataSource dataSource){
        dataSource.getData();
        syncData();
    }
    
    void syncData(){
        for (DataSource dataSource : list) {
            dataSource.sendData();
        }
    }
}

interface DataSource{
    ResultSet getData();
    void sendData();
}

class Oracle implements DataSource{

    @Override
    public ResultSet getData() {
        System.out.println("通过oracle获取数据");
        return null;
    }

    @Override
    public void sendData() {
        System.out.println("写入orcle数据库");
    }
}

class Mysql implements DataSource{

    @Override
    public ResultSet getData() {
        System.out.println("通过mysql获取数据");
        return null;
    }

    @Override
    public void sendData() {
        System.out.println("写入mysql数据库");
    }
}

class SqlServer implements DataSource{

    @Override
    public ResultSet getData() {
        System.out.println("通过SqlServer获取数据");
        return null;
    }

    @Override
    public void sendData() {
        System.out.println("写入SqlServer数据库");
    }
}
