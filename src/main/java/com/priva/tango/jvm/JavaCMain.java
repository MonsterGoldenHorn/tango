package com.priva.tango.jvm;

import com.sun.tools.javac.Main;

/**
 * javac 编译源文件
 * @description
 * @date 2023/9/5 10:37
 */
public class JavaCMain {
    /**
     * jdk编译器使用java代码来编译
     * 代码位置src/jdk.compiler/share/classes/com/sun/tools/javac/main/Main.java
     * 读取代码体后解析为JCTree
     * 树中上层包含类描述，下层包含函数method与字段variable描述，函数其下包含函数体
     *
     */
    public static void main(String[] args) throws Exception {
        Main.main(new String[]{"java文件全路径"});
    }
}
