package com.ydcun.demo.kzw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//不能改动此Test类	
/**
 * 空中网
 *  第三题：现有程序同时启动了4个线程去调用TestDo.doSome(key, value)方法，由于TestDo.doSome(key, value)方法内的代码是先暂停1秒，然后再输出以秒为单位的当前时间值，所以，会打印出4个相同的时间值，如下所示：
		4:4:1258199615
		1:1:1258199615
		3:3:1258199615
		1:2:1258199615
        请修改代码，如果有几个线程调用TestDo.doSome(key, value)方法时，传递进去的key相等（equals比较为true），则这几个线程应互斥排队输出结果，即当有两个线程的key都是"1"时，它们中的一个要比另外其他线程晚1秒输出结果，如下所示：
		4:4:1258199615
		1:1:1258199615
		3:3:1258199615
		1:2:1258199616
	  总之，当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间值（要用互斥），如果key不同，则并行执行（相互之间不互斥）。原始代码如下：

 * 
 * 
 * a="1"+"";
 * b="1"+"";
 * 在一起的时候是常量相加，编译器会自动优化
 * 
 * a = key+key1;
 * b = key+key1; 编译器应为是变量所以不会进行优化
 * */
public class Test3 extends Thread {

	private TestDo1 testDo;
	private String key;
	private String value;

	public Test3(String key, String key2, String value) {
		this.testDo = TestDo1.getInstance();
		/*
		 * 常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
		 * 以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果
		 */
		this.key = key + key2;//这个地方就是来的都是不同的对象 
		this.value = value;
	}

	public static void main(String[] args) throws InterruptedException {
		Test3 a = new Test3("1", "", "1");
		Test3 b = new Test3("1", "", "2");
		Test3 c = new Test3("3", "", "3");
		Test3 d = new Test3("4", "", "4");
		System.out.println("begin:" + (System.currentTimeMillis() / 1000));
		a.start();
		b.start();
		c.start();
		d.start();

	}

	public void run() {
		testDo.doSome(key, value);
	}
}
class TestDo1 {
	private TestDo1() {
	}
	private static TestDo1 _instance = new TestDo1();

	public static TestDo1 getInstance() {
		return _instance;
	}
	//private ArrayList list = new ArrayList();//会出现线程不安全
	private CopyOnWriteArrayList list = new CopyOnWriteArrayList();
	public void doSome(Object key, String value) {
		Object o = key;
		if(!list.contains(o)){
			list.add(o);//其它线程在迭代的时候如果是用的arrylist就不能add
		}else{
			Iterator it = list.iterator();
			while(it.hasNext()){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object oo = it.next();
				if(o.equals(oo)){
					o = oo;
				}
			}
		}
		synchronized (o) {
			// 以大括号内的是需要局部同步的代码，不能改动!
			{
				try {
					Thread.sleep(1000);
					System.out.println(key + ":" + value + ":" + (System.currentTimeMillis() / 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
