import java.awt.*;
import java.io.*;
import java.io.BufferedReader;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Main extends JPanel{

	public static int X_CONST=100;
	public static int Y_CONST=50;
	public static int count=0;
	public static void paintStuff(){
		BufferedImage bi=new BufferedImage(3000,2000,
																				BufferedImage.TYPE_INT_BGR);
		Graphics2D g2 = bi.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0,0,g2.getWidth(),g2.getHeight());
		g2.setColor(Color.BLACK);
		Font font = new Font("Serif", Font.PLAIN, 40);
    g2.setFont(font);
		try (BufferedReader br = new BufferedReader(
																new FileReader(new File("HSK5")))) {
    	String line;
    	while ((line = br.readLine()) != null&&count<1301) {
     	  // process the line.
     	  count++;
     	  int x=X_CONST*(count%30);
				int y=Y_CONST*(count/30+1);
				g2.drawString(line, x,y);
   		}
			ImageIO.write(bi, "jpg", new File("./output_image.jpg"));
		}catch(Exception e){e.printStackTrace();} 
	}
	public static void main(String[] args){
			paintStuff();
	}
}
