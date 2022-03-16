package com.priva.tango.test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class LockMain {
	public static void main(String[] args) {
		System.out.println(0x61c88647);
		System.out.println(2147483647 *0.618);
		System.out.println(0.6180339887*(2^32));
		System.out.println(Long.MAX_VALUE);
		Integer[] x = new Integer[60];
		x[0] = 1;
		x[1] = 1;
		for(int i=0;i<10;i++) {
			x[i+2]=x[i]*x[i]+x[i+1]*x[i+1];
			System.out.print(x[i+2]);
			System.out.print(';');
		}
//		HashMap<String,Integer> map = new HashMap(10,0.75f);
		HashMap<String,Integer> map = new HashMap();
		map.put("s", 1);
		map.put("nn", 2);
		map.put("s1", 1);
		map.put("nn1", 2);
		map.put("s2", 1);
		map.put("nn2", 2);
		map.put("s3", 1);
		map.put("nn3", 2);
		map.put("s4", 1);
		map.put("nn4", 2);
		map.put("s5", 1);//11
		map.put("nn5", 2);//12
		map.put("s6", 1);//13
		map.put("nn6", 2);
		map.put("s7", 1);
		map.put("nn7", 2);
		int n=15;
		System.out.println(17&32);
		System.out.println(17&16);
//		ReadWriteLock
//			只能解自己加的锁，分布式锁也是
		//延时队列 TODO
//		ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(i);
		//图片下载，上游下发数据资源短时间拥堵
		//1.新增时，实时性要求不高，延迟下载
		//2.修改
		//集合
//		Collection
//		List l;		
//			ArrayList 
//				初始10 扩容1.5倍 Arrays.copyOf()复制旧到新 System.arraycopy()删元素
//				序列化问题
//					elementData 基本存储单位 防止在序列化时将未占用的写入 单独实现readObject writeObject
//				增删改	增删时modCount作为版本号保证fail-fast,遍历的时候不能添加
//					增 ensureCapacityInternal(size + 1);如果不够先扩容 
//						如果add(x,v) 先保证容量，再拷贝旧x以后的数据+1，最后修改x的值
//					删 前面的逆向操作 removeRange可以删除区间
//			Vector 同样继承AbstractList
//				 get add加了synchronized 
//				 可以传入capacityIncrement控制扩容 个数，默认2倍
//			对比
//				List<String> list = new ArrayList<>();
//				Collections.synchronizedList(list);用了一个装饰模式对方法加上同步增强,这个类其他的方法比如unmodifiable都是这个原理
//			CopyOnWriteArrayList
//				juc 操作的都是数组 不适合内存敏感以及对实时性要求很高的场景,和vector与synchronizedList比少了一把读取锁
//			LinkedList	基于双向链表实现，使用 Node 存储链表节点信息（前面的都是数组），链表不支持随机访问，但插入删除只需要改变指针
		
//		Set s;
//			HashSet 内部使用map实现存储
//				set.add=map.put iterator=map.keySet().iterator()
//		Queue q;//最后来 TODO
//		BlockingQueue
//			LinkedBlockingQueue
//				有序
//				单端，尾插 Node{Node e; Node next}
//				插入 last = last.next = node;尾巴next等于自己
//				offer  添加一个元素并返回true   如果队列已满，则返回false
//				poll 移除并返问队列头部的元素
//				put 添加一个元素                       如果队列满，则阻塞
//				take 移除并返回队列头部的元素
//				
//			ArrayBlockingQueue
//				定长
//			PriorityBlockingQueue
//				优先
//			DelayQueue
//				延时
//		map
//		HashMap h;
//			初始默认 1.7:16   threshold 为 capacity*0.75 数量到达threshold才会resize resize为当前table长度（当前capacity）*2
//			使用第 0 个桶存放键为 null 的键值对 
//			put时先算hash用hash找index桶，再遍历entry对比key存在的情况会返回旧对象
//			
//			1.8 table中变成Node继承于entry 当添加节点到达8个时 treeifyBin将节点转换为TreeNode（红黑树）
/**

															
				ConcurrentHashmap											
 * 		
 */
		
//		TreeMap;//插入排序
//		HashTable ht;
//		LinkedHashMap;
//			继承自HashMap，在put新增时重写了newNode newTreeNode调用linkNodeLast保存一个链表
//			重写get后afterNodeAccess调整move node to last
//		ConcurrentHashMap  chm;
//		ConcurrentCache;//像jvm内存分区一样的东西
		
//		ReentrantLock
//			lock.await lock.singal
//			对比sync wait notifyall
		
		
//		分布式
//			1.数据库+redis
//				数据库版本号控制，redis缓存版本数据，用来控制下载这些单一进行,保证最终一致性
//				原始数据存入数据库，或者入redis，或者入rmq，保证数据不丢失
//			2.zk
//				集群下的两阶段提交，保证半数以上写成功
//			
//		TPS
//			1.数据库优化，要足够快
//			2.物理机多核
//			3.事务控制，防长时间等待
	}
}
