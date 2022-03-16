package com.priva.tango.clang;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

public class RunTimeCallCF {
    public static void main(String[] args) throws IOException {
        Process exec = Runtime.getRuntime().exec(new String[]{"clang-format", "-i", "D:\\workspace1\\tango\\src\\main\\java\\com\\priva\\tango\\clang\\ClangFormat.java"});
//        OutputStream outputStream = exec.getOutputStream();
        InputStream inputStream = exec.getInputStream();
        List<String> list = IOUtils.readLines(inputStream, Charset.forName("utf-8"));
        System.out.println(list);
    }
}
