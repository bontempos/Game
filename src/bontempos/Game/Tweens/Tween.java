package bontempos.Game.Tweens;

public class Tween {

	//private static Tween instance;
	
	public Tween() {
		// TODO Auto-generated constructor stub
	}

	/*
	 *  linear
	 *  t = current time / step
	 *  d = interval / total of steps
	 *  b = initial position
	 *  c = delta between initial and end position 
	 */
	public static float easeInOut(float t, float b, float c, float d) {
		t /= d/2;
		if (t < 1) return c/2*t*t*t + b;
		t -= 2;
		return c/2*(t*t*t + 2) + b;
	}

	public static float[] easeInOut2d(float t, float[] b, float[] c, float d) {
		return new float[]{ easeInOut(t,b[0], c[0],d), easeInOut(t,b[1], c[1],d) };		
	}

	public static float[] easeInOut3d(float t, float[] b, float[] c, float d) {
		return new float[]{ easeInOut(t,b[0], c[0],d), easeInOut(t,b[1], c[1],d), easeInOut(t,b[2], c[2],d) };		
	}

}
