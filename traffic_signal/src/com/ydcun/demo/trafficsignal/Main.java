/**
 * 
 */
package com.ydcun.demo.trafficsignal;

/**
 * @author ydcun_home
 *
 */
public class Main {
	public static void main(String[] args) {
		String[] arr = {
				"S2N","S2W","E2W","E2S","N2S","N2E","W2E","W2N","S2E","E2N","N2W","W2S"
		};
		for(int i =0;i<arr.length;i++){
			new Road(arr[i]);
		}
		
		new LampController();
	}
}
