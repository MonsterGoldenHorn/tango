package com.priva.tango;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class TangoApplicationTests {

public static void main(String[] args) {
	System.out.println(Pattern.matches("^\\d*$", "123"));
}

}
