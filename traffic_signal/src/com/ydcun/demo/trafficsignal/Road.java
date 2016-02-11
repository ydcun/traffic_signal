/**
 * 
 */
package com.ydcun.demo.trafficsignal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ydcun_home
 * 
 */
public class Road {
	private List<String> vehicles = new ArrayList<String>();
	
	/**
	 *·������
	 */
	private String name = null;
	
	public Road(String name){
		this.name  = name;
		
		//��·��ʼ�����ɽ�ͨ����    ÿ��(1-10)s����һ����ͨ����
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<1000;i++){
					try {
						Thread.sleep((new Random().nextInt(10)+1)*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					vehicles.add(Road.this.name+":_"+i+"�ų�");
				}
			}
		});
		
		//·��ͨ����
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if(vehicles.size()>0){
					boolean lighted = Lamp.valueOf(Road.this.name).getLight();
					if(lighted){
						System.out.println(vehicles.remove(0)+" is traversing!");
					}
				}
			}
		}, 1, 1,TimeUnit.SECONDS);
	}
	
}
