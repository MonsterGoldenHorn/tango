package com.priva.tango.jvm;

import com.sun.tools.javac.Main;

/**
 * javac 编译源文件
 *
 * <pre>
 *     {@code
 *
 *     }
 * </pre>
 *
 * @description
 * @date 2023/9/5 10:37
 */
public class JavaCMain {
    /**
     * jdk编译器使用java代码来编译
     * 代码位置src/jdk.compiler/share/classes/com/sun/tools/javac/main/Main.java
     * 读取代码后解析为JCTree
     * 树中上层包含类描述,引用等，类中包含函数method与字段variable描述，函数其下包含函数体
     *      描述中包含了代码位置，初始值，异常抛出等信息
     * 相关处理概念：
     *          顶层类（Top-Level Class），是 Java 中对类的一种定义方式。在 .java 文件中，处于最外层的类就称为顶层类，在其外部不存在将其包围起来的任何代码块。
     *          在编译过程中会将编译的java类汇集成一个树，树的根就是外层类
     *  方法解析时可见最大方法参数数量为255包含所有默认的参数
     *
     *
     * ClassWriter 将解析的类写到class文件中
     */

    /**
     * java类翻译，defs为java类解析出来的树形结构
     * ** 所有类会默认添加无参构造init方法
     *  如果包含了静态代码块或者静态属性需要赋初值，才会增加clinit方法
     * <pre>
     *     {@code
     *          List<JCTree> normalizeDefs(List<JCTree> defs, Symbol.ClassSymbol c) {
     *             ListBuffer<JCTree.JCStatement> initCode = new ListBuffer<>();
     *             ListBuffer<Attribute.TypeCompound> initTAs = new ListBuffer<>();
     *             ListBuffer<JCTree.JCStatement> clinitCode = new ListBuffer<>();
     *             ListBuffer<Attribute.TypeCompound> clinitTAs = new ListBuffer<>();
     *             ListBuffer<JCTree> methodDefs = new ListBuffer<>();
     *             for (List<JCTree> l = defs; l.nonEmpty(); l = l.tail) {
     *                 JCTree def = l.head;
     *                 switch (def.getTag()) {
     *                      //有代码块，如果是static就加clinit，不是就加init
     *                     case BLOCK:
     *                         JCTree.JCBlock block = (JCTree.JCBlock)def;
     *                         if ((block.flags & STATIC) != 0)
     *                             clinitCode.append(block);
     *                         else if ((block.flags & SYNTHETIC) == 0)
     *                             initCode.append(block);
     *                         break;
     *                     case METHODDEF:
     *                         methodDefs.append(def);
     *                         break;
     *                     case VARDEF:
     *                         JCTree.JCVariableDecl vdef = (JCTree.JCVariableDecl) def;
     *                         Symbol.VarSymbol sym = vdef.sym;
     *                         checkDimension(vdef.pos(), sym.type);
     *                         if (vdef.init != null) {
     *                             if ((sym.flags() & STATIC) == 0) {
     *                                  //final常量走这一段
     *                                 JCTree.JCStatement init = make.at(vdef.pos()).
     *                                         Assignment(sym, vdef.init);
     *                                 initCode.append(init);
     *                                 endPosTable.replaceTree(vdef, init);
     *                                 initTAs.addAll(getAndRemoveNonFieldTAs(sym));
     *                             } else if (sym.getConstValue() == null) {
     *                                 JCTree.JCStatement init = make.at(vdef.pos).
     *                                         Assignment(sym, vdef.init);
     *                                 // 静态参数有初始值但未赋值 Initialize class (static) variables only if
 *                                     // 并不是final常量 they are not compile-time constants.
     *                                 clinitCode.append(init);
     *                                 endPosTable.replaceTree(vdef, init);
     *                                 clinitTAs.addAll(getAndRemoveNonFieldTAs(sym));
     *                             } else {
     *                                 checkStringConstant(vdef.init.pos(), sym.getConstValue());
     *                                 vdef.init.accept(classReferenceVisitor);
     *                             }
     *                         }
     *                         break;
     *                     default:
     *                         Assert.error();
     *                 }
     *             }
     *             if (initCode.length() != 0) {
     *                 List<JCTree.JCStatement> inits = initCode.toList();
     *                 initTAs.addAll(c.getInitTypeAttributes());
     *                 List<Attribute.TypeCompound> initTAlist = initTAs.toList();
     *                 for (JCTree t : methodDefs) {
     *                     normalizeMethod((JCTree.JCMethodDecl)t, inits, initTAlist);
     *                 }
     *             }
     *             if (clinitCode.length() != 0) {
     *                 Symbol.MethodSymbol clinit = new Symbol.MethodSymbol(
     *                         STATIC | (c.flags() & STRICTFP),
     *                         names.clinit,
     *                         new Type.MethodType(
     *                                 List.nil(), syms.voidType,
     *                                 List.nil(), syms.methodClass),
     *                         c);
     *                 c.members().enter(clinit);
     *                 List<JCTree.JCStatement> clinitStats = clinitCode.toList();
     *                 JCTree.JCBlock block = make.at(clinitStats.head.pos()).Block(0, clinitStats);
     *                 block.endpos = TreeInfo.endPos(clinitStats.last());
     *                 methodDefs.append(make.MethodDef(clinit, block));
     *
     *                 if (!clinitTAs.isEmpty())
     *                     clinit.appendUniqueTypeAttributes(clinitTAs.toList());
     *                 if (!c.getClassInitTypeAttributes().isEmpty())
     *                     clinit.appendUniqueTypeAttributes(c.getClassInitTypeAttributes());
     *             }
     *             return methodDefs.toList();
     *         }
     *     }
     * </pre>
     */
    /**
     * 代码映射
     * 所有方法调用通过访问者模式进行树形结构处理
     * com.sun.tools.javac.jvm.ByteCodes 中包含了指令的代码
     * 方法体中所有方法都是执行的指令加上代码member的引用
     * 所有代码的ascii码转换成16进制保存到class文件中
     *
     * 解析时通过本方法加载@Data
     *  annotationProcessingOccurred = procEnvImpl.doProcessing(roots, classSymbols, pckSymbols, deferredDiagnosticHandler);
     *
     * class文件结构：
     * 描述，版本，常量池，方法指令集合，class文件的16进制转换对应的code代码可反编译
     *
     */
    public static void main(String[] args) throws Exception {
        Main.main(new String[]{"java文件全路径"});
    }
}
