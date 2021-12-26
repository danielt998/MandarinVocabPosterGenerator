import java.awt.*;
import java.io.*;
import java.io.BufferedReader;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends JPanel{

  public static int X_CONST=220;
  public static int Y_CONST=100;
  public static int charRatio = X_CONST / Y_CONST;
  public static int count=0;

  public static String FILE_PATH = "HSK6";/*"pleco-out.txt"*/

  private static final double PAGE_RATIO = 1.618; //TODO: maybe allow other formats e.g. letter, portrait, ...

  private static final int xWidth = 6000;

  //TODO:THERE ARE A LOT OF POTENTIAL precision issues and stuff!!
  public static void paintStuff() throws IOException {
//    BufferedImage bi=new BufferedImage(6000,4000,
  //                                      BufferedImage.TYPE_INT_BGR);
    BufferedImage bi = new BufferedImage(xWidth, (int)(xWidth/ PAGE_RATIO), BufferedImage.TYPE_INT_BGR);
    Extract.readInDictionary();
    Graphics2D g2 = bi.createGraphics();
//    g2.setColor(Color.WHITE);
//    g2.fillRect(0,0,g2.getWidth(),g2.getHeight());
//    g2.setColor(Color.BLACK);
    Font font = new Font("Serif", Font.PLAIN, 40);
    Font pinyinFont = new Font("Serif", Font.PLAIN, 15);
    Font latinFont = new Font("Serif", Font.PLAIN, 10);

    final long noLines = Files.lines(Paths.get(FILE_PATH)).count();//+1? as we seem to start at 0 or smth (first square is blank - though that needs fixing)

    try (BufferedReader br = new BufferedReader(
                                new FileReader(FILE_PATH))) {
      String line;
      while ((line = br.readLine()) != null && count < noLines) {
        int noRows = (int) Math.ceil(Math.sqrt(charRatio * noLines) / PAGE_RATIO);
        int noCols = (int) (noLines/noRows);
        // process the line.
        int x=X_CONST*(count%noCols);//30);
        int y=Y_CONST*(count/noCols+1);
        g2.setFont(font);
        g2.drawString(line, x,y);
        int y_pinyin = (Y_CONST )*(count/noCols+1) + Y_CONST/4;
        int y_text = (Y_CONST )*(count/noCols+1) + Y_CONST/2;
        g2.setFont(pinyinFont);
        Word word= Extract.getWordFromChinese(line);
        String pinyin = "invalid pinyin";
        String english = "invalid English";
        if(word != null){
          pinyin = word.getPinyinWithTones();
          english = word.getDefinition();
          if(english.length()>35){
          english = english.substring(0,35);
          }
        }
        g2.drawString(pinyin,x,y_pinyin);
        g2.setFont(latinFont);
        g2.drawString(english, x,y_text);
        count++;
      }
      ImageIO.write(bi, "jpg", new File("./output_image.jpg"));
    } catch(Exception e){e.printStackTrace();}
  }
  public static void main(String[] args){
    try {
      paintStuff();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
