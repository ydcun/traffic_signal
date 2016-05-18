/**
 * 
 */
package com.ydcun.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ydcun_home
 *
 */
public class Test {
	public static void main(String[] args) {
		ExecutorService theardPool = Executors.newSingleThreadExecutor();
		Future<String> f = theardPool.submit(new Callable<String>() {
			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				Thread.currentThread().sleep(2000);
				return "hello";
			}
		});
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(f.get());
			System.out.println(f.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
