//Steve Carr
//Student ID: 
//CSE310
//3-1-11

import java.io.*;         
import java.util.*;       
         
public class Homework3
{
  public static void main (String[] args) throws Exception
  {  
    Comparable[] temp = null;
    int arraySize = 0;
    
    try {
    BufferedReader reader = new BufferedReader(new FileReader(args[0]));
    arraySize = Integer.parseInt(reader.readLine());
    temp = new Comparable[arraySize];
    for (int i = 0; i < arraySize; i++)
			{
				temp[i] = Integer.parseInt(reader.readLine());
			}
     reader.close();
     }
     
    catch (FileNotFoundException e) 
    {
       System.out.println("IO FileNotFoundException");
        e.printStackTrace();
     } 
    catch (IOException e) 
    {
      System.out.println("IO Exception");
       e.printStackTrace();
    }
    
   /* for(int i=0; i<arraySize; i++)
     {
        System.out.println(temp[i]);
     } */
    Comparable [ ] tmpArray = new Comparable[ temp.length];
    MergeSort(temp,tmpArray,0,arraySize-1);
    
    File file = new File ("output.txt");
    PrintStream output = new PrintStream(file);
    output.println("Number of elements in Array: " + arraySize+"\n");
     for (int i=0; i < arraySize; i++)
     {
        output.println(temp[i]);
      }
    }

  private static void MergeSort(Comparable[] a, Comparable [ ] tmpArray, int start, int end)
  {
	if(start < end)
	{
		int middle = (start + end) / 2;

		MergeSort(a, tmpArray, start, middle);
		MergeSort(a, tmpArray, middle + 1, end);
		Merge(a, tmpArray, start, middle+1, end);
	}
  }
  
 @SuppressWarnings("unchecked") 
 private static void Merge(Comparable[] a, Comparable [ ] tmpArray, int start, int middle, int end) 
  {
    int n1 = middle - 1;
    int n2 = start;
    int numElements = end- start + 1;
    
    while( start <= n1 && middle <= end)
    {
        
        if(a[start].compareTo(a[middle]) <= 0 )
        tmpArray[n2++] = a[start++];
   
        else
        tmpArray[n2++] = a[middle++];
    }
    
    while( start <= n1)    
    tmpArray[n2++] = a[start++];
    
    while( middle <= end)  
    tmpArray[n2++] = a[middle++];
    
    for( int i = 0; i < numElements; i++, end-- )
            a[end] = tmpArray[end];
    } 
 }

