package com.priva.tango.designmodle.behave;

import org.apache.tomcat.util.digester.Digester;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

public class InterpreterMode {
/**
 * 
 * xml解析，sql解析，正则表达式捕获，excel解析，用户使用界面录入配置
 * 
 * 被解析的可以用访问者模式访问，用解释器模式解析
 * 
 * AST语法树
 * 
 * 
 * 
 * C o m p o s i t e模式（4 . 3）: 抽象语法树是一个复合模式的实例。
 * F l y w e i g h t模式（4 . 6）：说明了如何在抽象语法树中共享终结符
 * I t e r a t o r（5 . 4）：解释器可用一个迭代器遍历该结构。
 * Vi s i t o r（5 . 11）：可用来在一个类中维护抽象语法树中的各节点的行为
 */
    
    public static void main(String[] args) {
        //1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            Digester digester = new Digester();//
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse("src/main/resources/demo.xml");
            NodeList sList = d.getElementsByTagName("student");
            //element(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


 class XSDValidator {
    
    static public void validate(InputStream xsdStream, InputStream xmlStream) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");                
        Source xsdSource = new StreamSource(xsdStream);
        Schema schema = schemaFactory.newSchema(xsdSource);
        
        Source xmlSource = new StreamSource(xmlStream);
        
        Validator validator = schema.newValidator();
        validator.validate(xmlSource);        
    }
    
}






