//Steve Carr
//Student ID: 
//CSE310
//5-3-11

import java.io.*;
import java.util.*;

class Homework9
{
	public static void main(String [] args) throws Exception
	{
		double size=0;
		HashChain[] list1;
		HashLin[] list2;
		HashQuad[] list3;
		HashDoub[] list4;

		try {
		File file = new File(args[0]);
		Scanner scan = new Scanner(file);

		size = scan.nextInt();;
		list1 = new HashChain[size];
		list2 = new HashLin[size];
		list3 = new HashQuad[size];
		list4 = new HashDoub[size];

	}
	catch (FileNotFoundException e)
	{
		System.out.println("IO FileNotFoundException");
		e.printStackTrace();
	}
	catch (IOException e1)
	{
		System.out.println("IO Exception");
		e1.printStackTrace();
	}
	catch(ArrayIndexOutOfBoundsException e2)
	{
		System.out.println("Array Index Out Of Bounds");
		e2.printStackTrace();
	}

	PrintWriter out = new PrintWriter (//FILL ME);
	out.println(-1);
	out.flush();
	out.close();
	}
}

