import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.Exposure;

public class Main {
	public static void main(String[] args){
            RPiCamera camera = null;
			try {
				System.out.println("Making Camera");
				camera = new RPiCamera("/home/pi/Pictures");
				System.out.println("Camera Created");
			} catch (Exception e1) {
				System.err.println("error making camera");
				e1.printStackTrace();
			}
			System.out.println("Setting Brightness");
            camera.setBrightness(70);
            System.out.println("Setting Height");
            camera.setHeight(1944);
            System.out.println("Setting Width");
            camera.setWidth(2592);
            System.out.println("Setting Exposure");
            camera.setExposure(Exposure.BACKLIGHT);
           while(true){
        	    Mat source = null;
	   			try {
	   				source = bufferedImageToMat(camera.takeBufferedStill());
	   			} catch (Exception e) {
	   				e.printStackTrace();
	   				System.err.println("Camera interupted (or at least thats what josh told me)");
	   			}
	   			System.out.println("Processing and calculating");
	   			Vision.getInstance().process(source);
	   			Vision.getInstance().putDistance(DistanceCalc.getDistance());
           }
            
	}
	
	public static Mat bufferedImageToMat(BufferedImage bi) {
		  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
		  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		  mat.put(0, 0, data);
		  return mat;
		}
}
