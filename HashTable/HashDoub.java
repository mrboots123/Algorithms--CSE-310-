import java.util.*;
import java.lang.Math;

public class HashDoub
{

  private LinkedList [] table;
  private double Size;
  Iterator iterator;

  public HashDoub(double size)
  {
    Size = size;
    table = new LinkedList[Size];

    for (int i = 0; i < Size; i++)  //initialize array of LinkedLists
    {
      table[i] = new LinkedList();
      }
  }

  public void displayTable()
  {
    iterator = table[0].iterator();
    for (int j = 0; j < Size; j++)
    {
      System.out.print(j + "/t");
       while (iterator.hasNext())
       {
            System.out.print(iterator.next()+" ");
       }
       System.out.println();
    }
  }
  
  public double h1(double key)
  {
      return key % 31;
  }
  
   public double h2(double key)
  {
      return 1 + (key % 30);
  }
  
  public double hashFunc(double key, double i)
  {
      return ((h1(k)+i*h2(key)) % 31);
  }

/*/ INSERT METHOD FOUND ONLINE
  public void insert(Link theLink) {
    int key = theLink.getKey();
    int hashVal = hashFunc(key);
    table[hashVal].insert(theLink);
*/

  public int ChainInsert(LinkedList[] T, double k)
  	{
  		int i = 0;
  		do
  		{
  			int j = hashFunc(k);
  			if (T[j] == null)
  			 {
  				 T[j].add(k);
  				 return j;
  			 }
  			i++;
  		} while(i != 23);
  		System.out.println("Error: Hash Size overflow");
  		return 0;
	}
 }