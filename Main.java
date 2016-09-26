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
	//public void paint(Graphics g){
//
	public static void paintStuff(){
			BufferedImage bi=new BufferedImage(3000,2000,
																				BufferedImage.TYPE_INT_RGB);

		//Graphics2D g2=(Graphics2D)g;
		Graphics2D g2 = bi.createGraphics();
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
//	System.out.println("x:"+x+"y:"+y+"count:"+count);
				g2.drawString(line, x,y);
   		}

			//Graphics2D cg=bi.createGraphics();
			//paintAll(cg);
			ImageIO.write(bi, "png", new File("./output_image.png"));
		}catch(Exception e){e.printStackTrace();} 
	}
	public static void main(String[] args){
      //JFrame f = new JFrame();
      //f.getContentPane().add(new Main());
			paintStuff();
      //f.setSize(1800, 1000);
      //f.setVisible(true);
	}
}
