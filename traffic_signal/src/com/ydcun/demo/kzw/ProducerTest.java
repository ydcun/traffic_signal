/**
 * 
 */
package com.ydcun.demo.kzw;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * @author ydcun_home
 *������
 *�ڶ��⣺�ֳɳ����е�Test���еĴ����ڲ��ϵز������ݣ�
 *Ȼ�󽻸�TestDo.doSome()����ȥ����
 *�ͺ����������ڲ��ϵز������ݣ��������ڲ����������ݡ�
 *�뽫����������10���߳������������߲��������ݣ�
 *��Щ�����߶�����TestDo.doSome()����ȥ���д���
 *��ÿ�������߶���Ҫһ����ܴ����꣬����Ӧ��֤��Щ�������߳�����������������ݣ�ֻ����һ���������������
 *��һ�������߲����������ݣ���һ����������˭�����ԣ���Ҫ��֤��Щ�������߳��õ�����������˳��ġ�
 */
public class ProducerTest {
	final static Semaphore semaphore = new Semaphore(1);
	final static SynchronousQueue<String> queue = new SynchronousQueue<String>();
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						semaphore.acquire();
						String input = null;
						input = queue.take();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}}).start();
		}
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		for(int i=0;i<10;i++){  //���в��ܸĶ�
			String input = i+"";  //���в��ܸĶ�
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

//���ܸĶ���TestDo��
class TestDo {
	public static String doSome(String input){
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String output = input + ":"+ (System.currentTimeMillis() / 1000);
		return output;
	}
}