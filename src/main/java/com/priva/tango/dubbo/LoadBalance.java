package com.priva.tango.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;

public class LoadBalance {
	public static void main(String[] args) {
		/**
		 * 1.RandomLoadBalance
		 * 随机
		 * 权重一样使用invokers.get(random.nextInt(length));，随机取一个
		 * 权重不同则相加取随机数，依次减权重到负数就提供
		 * ！权重越高，负数出现的机会越大
		 int offset = random.nextInt(totalWeight);
            // Return a invoker based on the random value.
            for (int i = 0; i < length; i++) {
                offset -= getWeight(invokers.get(i), invocation);
                if (offset < 0) {
                    return invokers.get(i);
                }
            }
            
            
            配置xml <dubbo:reference id="demoService" loadbalance="random"/>
            注解 @Reference(loadbalance = "random")
		 */
		/**
		 * 
		 */
		
	}
}
