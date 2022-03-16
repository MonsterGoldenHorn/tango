package com.priva.tango.designmodle.behave;

public class CommandMode {
    /**
     * Command 将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化
     * 别名： 动作( A c t i o n )，事务( Tr a n s a c t i o n )
     * 
     * 记录命令，可实现撤销操作
     *JdbcTemplate
1) Command模式将调用操作的对象与知道如何实现该操作的对象解耦。
2) Command是头等的对象。它们可像其他的对象一样被操纵和扩展。
3) 你可将多个命令装配成一个复合命令。例如是前面描述的 M a c r o C o m m a n d类。一般说
来，复合命令是C o m p o s i t e模式的一个实例。
4) 增加新的C o m m a n d很容易，因为这无需改变已有的类

可能导致具体命令类过多

related
C o m p o s i t e模式（4 . 3）可被用来实现宏命令。
M e m e n t o模式（5 . 6）可用来保持某个状态，命令用这一状态来取消它的效果。
在被放入历史表列前必须被拷贝的命令起到一种原型 ( 3 . 4 )的作用。

     */
    
    public static void main(String[] args) {
        Sql sql = new Sql("insert into aa values(1)");
        Update action = new Update(sql);
        Controller c = new Controller();
        c.undoAction();
        c.receiveAction(action);
        c.undoAction();
        
        Save save = new Save(sql);
        c.receiveAction(save);
//对cmd进行扩展调用
    }
    
    
}
//Invoker
class Controller{
    Command cmd;
    void receiveAction(Command cmd){
        this.cmd = cmd;
        cmd.excute();
    }
    
    void undoAction(){//UndoCommand使用后免除空判断
        if(null==cmd){
            System.out.println("未提交");
            return;
        }
        cmd.undo();   
    }
    
}

interface Command{
    void excute();
    void undo();//在实现类当中记录实体类
}


class Sql{
    private String sql;
    
    public Sql(String sql) {
        this.sql = sql;
    }
    
    public void excute() {
        System.out.println(" 更新数据库记录 ");
    }
    
    public void rollback() {
        System.out.println(" rollback ");
    }
}

class Update implements Command{
    private Sql sql;
    
    public Update(Sql sql) {
        this.sql = sql;
    }

    @Override
    public void excute() {
        sql.excute();
    }

    @Override
    public void undo() {
        sql.rollback();
    }
}

class Save implements Command{
    private Sql sql;
    
    public Save(Sql sql) {
        this.sql = sql;
    }

    @Override
    public void excute() {
        sql.excute();
    }

    @Override
    public void undo() {
        sql.rollback();
    }
}
//空命令，作为初始化参数，参数为空时默认的执行方法，免除判断操作
class UndoCommand implements Command{

    @Override
    public void excute() {
    }

    @Override
    public void undo() {
    }
}
