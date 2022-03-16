package com.priva.tango.spring.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CircleBeanA.java
 *
 * @author tangjc
 * @date 2021年7月21日
 */
@Component
public class CircleBeanA {
	@Autowired
	CircleBeanB b;
}
