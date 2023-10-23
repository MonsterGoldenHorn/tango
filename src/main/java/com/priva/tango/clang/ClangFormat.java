package com.priva.tango.clang;

public class ClangFormat {
    /**
     * child_process.spawn(`clang-format -style="{BasedOnStyle: Google,
     * IndentWidth: 4, AccessModifierOffset: -4, SortIncludes: false,
     * AllowShortIfStatementsOnASingleLine: false, ColumnLimit: 110,
     * Cpp11BracedListStyle: false }" -assume-filename="a.${lang}"`, { shell: true
     * });
     *
     //     * @param from
     */
//    public native String clang_format(String from);
    public native void clang();
    static {
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("clangFormat");
//        System.load("D:\\workspace c\\CMakeProject1\\out\\build\\x64-debug\\clangFormat.dll");
    }

//    public String format(String from) {
//        String s = this.clang(from);
//        return s;
//    }

    public static void main(String[] args) {
        ClangFormat clangFormat = new ClangFormat();
        clangFormat.clang();
    }

    /**
     * private need javap -v -p xxx.class
     * @param f
     * @return
     */
    private int methord(int f) {
        Integer i = 127;
        Integer rr = 12711;
        int k = 1;
//    double x = 0d;
//    double y = 0d;
        int s = i + k + f;
        return k;
    }

    private synchronized static void print(){
        System.out.println("ssa");
    }
}
