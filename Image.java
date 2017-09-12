import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Image 
{

	Pixel[][] pixels;
	//first val is height
	//second val width
	
	int width = 0;
	int height = 0;
	int max = 0;
	String header;
	private Scanner scan;
	
	//Methods
	public void load(String file) throws IOException
	{
		String delim = "((#[^\\n]*\\n)|(\\s+))+";
		File fr = new File(file);
		scan = new Scanner(fr);
		scan.useDelimiter(delim);
		header = scan.next();
	
		if(header.equals("P3"))
		{
			width = scan.nextInt();
			height = scan .nextInt();
			max = scan.nextInt();
			pixels = new Pixel[height][width];
			
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					Pixel p = new Pixel();
					p.setRed(scan.nextInt());
					p.setGreen(scan.nextInt());
					p.setBlue(scan.nextInt());
					pixels[i][j] = p;
				}//end inner for loop
			}//end outer for loop
		}//end if statement
		else
		{
			System.out.println("File does not begin with \"P3\"");
		}
		
	}//end load function
	
	public void invert()
	{
//		System.out.println("entered invert method");
		Image newImage = new Image();
//		newImage.pixels = pixels;
		
//		System.out.println(header);
//		System.out.print(width + " ");
//		System.out.println(height);
//		System.out.println(max);
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				int red = max - pixels[i][j].getRed();
				pixels[i][j].setRed(red);
//				newImage.pixels[i][j].setRed(red);
			
				int green = max - pixels[i][j].getGreen();
				pixels[i][j].setGreen(green);
//				newImage.pixels[i][j].setGreen(green);
				
				int blue = max - pixels[i][j].getBlue();
				pixels[i][j].setBlue(blue);
//				newImage.pixels[i][j].setBlue(blue);
				
//				System.out.println(red);
//				System.out.println(green);
//				System.out.println(blue);
			}
		}
		
//		return newImage;
	}
	
	public void grayscale()
	{
//		System.out.println("entered grayscale method");
		
		Image newImage = new Image();
		newImage.pixels = pixels;
		
//		System.out.println(header);
//		System.out.print(width + " ");
//		System.out.println(height);
//		System.out.println(max);
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				int red = pixels[i][j].getRed();
				int blue = pixels[i][j].getBlue();
				int green = pixels[i][j].getGreen();
				
				int total = red + blue + green;
				int val = total / 3;
				
				pixels[i][j].setRed(val);
				pixels[i][j].setBlue(val);
				pixels[i][j].setGreen(val);

//				System.out.println(val);
//				System.out.println(val);
//				System.out.println(val);
			}//end inner for loop
		}//end outer for loop

	}//End grayscalemethod
	
	public void emboss()
	{
//		System.out.println("entered emboss function");
		
		Image newImage = new Image();
		newImage.pixels = pixels;
		
		for(int i = height - 1; i >= 0 ; i--)
		{
			for(int j = width - 1; j >= 0; j--)
			{
				int val;
				if(i - 1 < 0 || j -1 < 0)
				{
					val = 128;
				}
				else
				{
					int redDiff = pixels[i][j].getRed() - pixels[i-1][j-1].getRed();
					int greenDiff = pixels[i][j].getGreen() - pixels[i-1][j-1].getGreen();
					int blueDiff = pixels[i][j].getBlue() - pixels[i-1][j-1].getBlue();					
					
					int redVal = Math.abs(redDiff);
					int greenVal = Math.abs(greenDiff);
					int blueVal = Math.abs(blueDiff);
					
					int temp = Math.max(redVal, greenVal);
					int maxNum = Math.max(temp, blueVal);
					int maxDiff;
					
					if(maxNum == redVal)
					{
						maxDiff = redDiff;
					}
					else if(maxNum == greenVal)
					{
						maxDiff = greenDiff;
					}
					else if(maxNum == blueVal)
					{
						maxDiff = blueDiff;
					}
					else
					{
						maxDiff = maxNum;
					}
					
					val = 128 + maxDiff;
					
					if(val > 255)
					{
						val = 255;
					}
					else if(val < 0)
					{
						val = 0;
					}
					
				}//end else
				
				pixels[i][j].setRed(val);
				pixels[i][j].setBlue(val);
				pixels[i][j].setGreen(val);
				
			}//end inner for loop
		}//end outer for loop
	}
	
	public void blur(int val)
	{
		int blur = val;
		
		if(blur > 0)
		{
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					int counter = 0;
					int redTemp = 0;
					int greenTemp = 0;
					int blueTemp = 0;
					
					for(int k = 0; k < blur; k++)
					{
						if(j+k >= width)
						{
							//Do nothing, out of bounds
						}
						else
						{
							counter++;
							redTemp += pixels[i][j+k].getRed();
							greenTemp += pixels[i][j+k].getGreen();
							blueTemp += pixels[i][j+k].getBlue();
						}
					}//end inner for loop
					
					pixels[i][j].setRed(redTemp/counter);
					pixels[i][j].setGreen(greenTemp/counter);
					pixels[i][j].setBlue(blueTemp/counter);
					
				}//end middle for loop
			}//end outer for loop
		}//end if(blur > 0)
		else
		{
			System.out.println("blur value is not greater than 0");
		}
	}//end blur function

}//end class


