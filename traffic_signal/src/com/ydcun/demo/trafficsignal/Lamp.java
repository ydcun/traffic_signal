/**
 * 
 */
package com.ydcun.demo.trafficsignal;

/**
 * @author ydcun_home
 *
 */
public enum Lamp {
	S2N("N2S","S2W",false),S2W("N2E","E2W",false),E2W("W2E","E2S",false),E2S("W2N","S2N",false),
	N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),
	S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
	
	private String opposite;
	private String next;
	private boolean light;

	private Lamp(String opposite,String next,boolean light){
		this.opposite = opposite;
		this.next = next;
		this.light = light;
	}
	/**灯变亮（代替绿灯意：可以通过）**/
	public void lighted(){
		this.light  = true;
		if(opposite!=null){
			Lamp.valueOf(opposite).lighted();
			System.out.println(name()+"变亮");
		}
	}
	/**灯变黑（代替红灯意：不能通过）**/
	public Lamp lightBlack(){
		this.light = false;
		if(opposite!=null){
			Lamp.valueOf(opposite).lightBlack();
		}
		Lamp nextLamp = null;
		if(next!=null){
			nextLamp = Lamp.valueOf(next);
			nextLamp.lighted();
			System.out.println("由"+name()+"灯变成"+nextLamp.name()+"灯");
		}
		return nextLamp;
	}
	/**灯的状态**/
	public boolean getLight(){
		return light;
	}
}
