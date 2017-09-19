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
		//Hello this is a comment		
	}// end main	
}// end class Editor
