package com.priva.tango.lambda;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
中间聚合操作：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered。

最终输出操作：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator。

短路操作：anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit。

获取stream方式
Stream<String> of = Stream.of("1","2");
Stream<Double> generateDouble = Stream.generate(Math::random);
Stream.iterate(1,i -> i +1).limit(10).forEach(System.out::println);
Stream.concat(Stream.of("欢迎","关注"),Stream.of("程序新视界")).forEach(System.out::println);
//元素化流，合并
Stream.of(1, 2, 3).flatMap(i -> Stream.of(i * 10)).forEach(System.out::println);
Stream.of(1, 2).peek(i -> System.out.println("peekCall:" + i)).forEach(System.out::println);
//跳过前N个元素，取剩余元素，如果没有则为空Stream。
Stream.of(1, 2, 3).skip(2).forEach(System.out::println);

Stream.of(1, 2, 3).collect(Collectors.toSet());

//并行
Stream.of(1, 2, 3).parallel().sorted().distinct().collect(collector);
 *
 */
public class StreamMain {
	public static void main(String[] args) {
		
		//for循环
		List<String> list1 = Arrays.asList("maven","install","plugin");
//		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
//			Object object = (Object) iterator.next();
//		}
		//join
//		String collect = list1.stream().distinct().
//		sorted().
//		collect(Collectors.joining(","));
//		System.out.println(collect);
		//1.简单筛选		
//		List<String>  f = list1.stream()
//		.filter(s -> s.startsWith("m"))
//		.map(String::toUpperCase)
//		.sorted()
//		.collect(Collectors.toList());
//		System.out.println(f);
		
		//2.对象筛选
		User user = new User("1", "Fix", 33);
		List<User> list2 = Arrays.asList(user, user, new User("5", "Fox", 33), new User("2", "Rabbit", 12), new User("3", "String", 32));
		List<User> list21 = Arrays.asList(user, new User("1", "Fox", 33), new User("2", "Rabbit", 12), new User("3", "String", 32));
		
		List<User> collect = list2.stream().filter(item -> list21.contains(item)).collect(toList());
		System.out.println("--------------------------");
		System.out.println(collect.size());
		//去重，按照hashCode----equals比较去重
//		List<User> list = list2.stream().distinct().collect(Collectors.toList());
//		System.out.println(list);
		//排序
//		List<User> list = list2.stream().filter(User.ageFun()).distinct().
//				sorted((User o1, User o2) -> o1.getAge()-o2.getAge()).
//				collect(Collectors.toList());
//		compareTo排序
//		Comparator<User> comparator = Comparator.comparing(User::getName);
//				(User o1, User o2)->o1.getName().compareTo(o2.getName());
		List<User> list = list2.stream().distinct().
				sorted(Comparator.comparing(User::getName).reversed()).
				collect(Collectors.toList());
		
//		List<String> list1 = Arrays.asList("maven","install","plugin");
//		int age =19;
//		//map转换对象，创建对象
//		List<User> list = list1.stream().map(
//				(name)->new User("1", name, age)
//			).collect(Collectors.toList());
//		
//		System.out.println(list);
		
		Map<String,User> map = new HashMap<String, User>();
//		Stream.of(map).forEach(action);
		
		
		Stream<String> of = Stream.of("1338","2");
		of.flatMapToInt(stm->Stream.of(stm).mapToInt(Integer::new)).sorted();
				

		//2 所有name收集成list。toList
			List<String> nameList = list2.stream().map(x->x.getName()).collect(Collectors.toList());
		//3 name收集成set 。toSet
			Set<String> nameSet = list2.stream().map(x->x.getName()).collect(Collectors.toSet());
		//4 list转为map   key不能重复
//			Map<String, String> map2 = list2.stream().collect(Collectors.toMap(User::getId, User::getName));
		//  key重复时报错的解决，BinaryOperator选择保留先后，当 value 为 null 时会抛 NPE 异常。
			Map<String, String> map3 = list2.stream().collect(Collectors.toMap(User::getId, User::getName,(k2,k1)->k1));
		//5 根据id分组
			Map<String, List<User>> groupByMap = list2.stream().collect(Collectors.groupingBy(User::getId));
		//6 分区 分区是分组的特殊情况。  它最多可以分为两组,如id大于2和不大于2的
			Map<Boolean, List<User>> part = list2.stream().collect(Collectors.partitioningBy(p -> p.getName().startsWith("R")));
		//7 name用逗号相连
			String join = list2.stream().map(User::getName).collect(Collectors.joining(","));
			System.out.println(part);
	}
}


class User {
	String id;
	String name;
	Integer age;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public User(String id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	static Predicate<User> ageFun() {
		return (Predicate<User>)p -> p.getAge() > 18;
	}
	
	static Predicate<User> nameFun() {
//		return new Predicate<User>() {
//			@Override
//			public boolean test(User t) {
//				return t.getAge()>18;
//			}
//		};
		return (Predicate<User>)p -> p.getName().startsWith("F");
	}
	
	/**
	 * 生成对象方法
	 */
	int ids = 0;
//	static Function<String, User> genUser(String name){
//		 
//		Function<String, User>(name){
//			
//		};
//	}
	@Override
	public boolean equals(Object obj) {
		 if (obj == null) {
			 return false; 
		 }
		 User u = (User)obj;
		 if((id+name).equals(u.getId()+u.getName())) {
			 return true;
		 }
		 return false;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
//	@Override
//	public int hashCode() {
//		return this.age;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		if (age == null) {
//			if (other.age != null)
//				return false;
//		} else if (!age.equals(other.age))
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}

}