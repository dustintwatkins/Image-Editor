import java.io.IOException;
import java.io.PrintWriter;

public class Editor {
	public static void main(String[] args) throws IOException
	{	
		//Steps
		//1. Load the image
		//2. Perform operation
		//3. Store the image
		
		String file = args[0];
		String outputName = args[1];
		String transformation = args[2];
		
		Image image = new Image();
		image.load(file);
//		Image newImage = new Image();
//		newImage.pixels = new Pixel[1][5];
//		newImage.pixels[0][0].setRed(117);
//		newImage.pixels[0][0].setGreen(64);
//		newImage.pixels[0][0].setBlue(22);
//		newImage.pixels[0][1].setRed(117);
//		newImage.pixels[0][1].setGreen(62);
//		newImage.pixels[0][1].setBlue(21);
//		newImage.pixels[0][2].setRed(117);
//		newImage.pixels[0][2].setGreen(62);
//		newImage.pixels[0][2].setBlue(21);
//		newImage.pixels[0][3].setRed(119);
//		newImage.pixels[0][3].setGreen(61);
//		newImage.pixels[0][3].setBlue(21);
//		newImage.pixels[0][4].setRed(120);
//		newImage.pixels[0][4].setGreen(61);
//		newImage.pixels[0][4].setBlue(19);
		
//		
//		System.out.println("red = " + newImage.pixels[0][0].getRed());
//		newImage.blur(5);
		
		
		if(transformation.equals("invert"))
		{			
			image.invert();
		}
		else if(transformation.equals("grayscale"))
		{
			image.grayscale();
		}
		else if(transformation.equals("motionblur"))
		{
			String val = args[3];
			int blur = Integer.parseInt(val);
			if(blur > 0)
			{	
				image.blur(blur);
			}
			else
			{
				System.out.println("Blur value must be greater than 0");
			}
		}
		else if(transformation.equals("emboss"))
		{
			image.emboss();
		}
				
		PrintWriter pw = new PrintWriter(outputName);
		pw.println(image.header);
		pw.print(image.width + " ");
		pw.println(image.height);
		pw.println(image.max);
	
		for(int i = 0; i < image.height; i++)
		{
			for(int j = 0; j < image.width; j++)
			{
				pw.println(image.pixels[i][j].getRed());
				pw.println(image.pixels[i][j].getGreen());
				pw.println(image.pixels[i][j].getBlue());
			}//end inner for loop
		}//end outer for loop
		pw.close();		
		
	}// end main	
}// end class Editor
