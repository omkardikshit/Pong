package game;

public class Lib {

	
	
	public static double random(double min,double max) {
		return Math.floor((Math.random() * max) + min);
	}
	
	
	public static int constrain(int n,int low,int high) {
		if(n < low) {
			n = low;
		}else if(n > high){
			n = high;
		}
		return n;
	}
	
}
