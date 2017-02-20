import java.util.ArrayList;

public class DistanceCalc {
	public static double getDistance(){
		ArrayList<double[]> conts = Vision.getInstance().requestContours();
		double centerX = 0;
		for(double[] d : conts) {	
			centerX+=d[1];
		}
		
		centerX/=conts.size();
		double trueCenterX = 2592/2;
		double difference = centerX - trueCenterX;
		
		return difference;
	}
}