/**
 * 
 */
package com.ydcun.demo.trafficsignal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ydcun_home
 *
 */
public class LampController {
	private Lamp currentLamp;
	public LampController(){
		currentLamp = Lamp.S2N;
		currentLamp.lighted();
		
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
		pool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				currentLamp = currentLamp.lightBlack();
			}
		}, 10, 10,TimeUnit.SECONDS);
	}
}
