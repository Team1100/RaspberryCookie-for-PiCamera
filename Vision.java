import java.util.ArrayList;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision {

	static boolean imageRequested;
	private static GripPipeline gp;
	private static Vision vision;
	NetworkTable table;
	
	public Vision(){
		table = NetworkTable.getTable("VISION");
		imageRequested = false;
		gp = new GripPipeline();
	}
	
	public void putDistance(double dist){
		table.putNumber("$Distance", dist);
	}
	
	public NetworkTable getTable() {
		return table;
	}
	
	public static synchronized boolean isImageRequested() {
		return imageRequested;
	}
	
	public static Vision getInstance() {
		if(vision==null) {
			vision = new Vision();
		}
		return vision;
	}
	
	public synchronized ArrayList<double[]> requestContours() {
		return gp.getContourList();
	}
	
	public void process(Mat image) {
		if(gp == null) {
			System.err.println("Its gracious professionalism!!!!");
		}
		if(image == null) {
			System.err.println("Its the image!!!!");
		}
		gp.process(image);
	}
	
	
	
}
