package com.ydcun.demo.kzw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//���ܸĶ���Test��	
/**
 * ������
 *  �����⣺���г���ͬʱ������4���߳�ȥ����TestDo.doSome(key, value)����������TestDo.doSome(key, value)�����ڵĴ���������ͣ1�룬Ȼ�����������Ϊ��λ�ĵ�ǰʱ��ֵ�����ԣ����ӡ��4����ͬ��ʱ��ֵ��������ʾ��
		4:4:1258199615
		1:1:1258199615
		3:3:1258199615
		1:2:1258199615
        ���޸Ĵ��룬����м����̵߳���TestDo.doSome(key, value)����ʱ�����ݽ�ȥ��key��ȣ�equals�Ƚ�Ϊtrue�������⼸���߳�Ӧ�����Ŷ��������������������̵߳�key����"1"ʱ�������е�һ��Ҫ�����������߳���1����������������ʾ��
		4:4:1258199615
		1:1:1258199615
		3:3:1258199615
		1:2:1258199616
	  ��֮����ÿ���߳���ָ����key���ʱ����Щ���key���߳�Ӧÿ��һ���������ʱ��ֵ��Ҫ�û��⣩�����key��ͬ������ִ�У��໥֮�䲻���⣩��ԭʼ�������£�

 * 
 * 
 * a="1"+"";
 * b="1"+"";
 * ��һ���ʱ���ǳ�����ӣ����������Զ��Ż�
 * 
 * a = key+key1;
 * b = key+key1; ������ӦΪ�Ǳ������Բ�������Ż�
 * */
public class Test3 extends Thread {

	private TestDo1 testDo;
	private String key;
	private String value;

	public Test3(String key, String key2, String value) {
		this.testDo = TestDo1.getInstance();
		/*
		 * ����"1"��"1"��ͬһ�������������д������Ҫ��"1"+""�ķ�ʽ�����µĶ���
		 * ��ʵ������û�иı䣬��Ȼ��ȣ�����Ϊ"1"����������ȴ������ͬһ����Ч��
		 */
		this.key = key + key2;//����ط��������Ķ��ǲ�ͬ�Ķ��� 
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
	//private ArrayList list = new ArrayList();//������̲߳���ȫ
	private CopyOnWriteArrayList list = new CopyOnWriteArrayList();
	public void doSome(Object key, String value) {
		Object o = key;
		if(!list.contains(o)){
			list.add(o);//�����߳��ڵ�����ʱ��������õ�arrylist�Ͳ���add
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
			// �Դ������ڵ�����Ҫ�ֲ�ͬ���Ĵ��룬���ܸĶ�!
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
