//Steve Carr
//Student ID: 
//CSE310
//4-5-11

import java.io.*;         
import java.util.*;

public class RedBlackTree {
	node root;
	node nil  = new node();		//node to represent nill value
	
	public RedBlackTree() { 			//Create New Red Black Tree
		root = nil;
	}			
	
	private static void insert(RedBlackTree T, node z) {		//RBT Insert Method
		node y = T.nil;
		node x = T.root;
		z.left = T.nil;
		z.right = T.nil;
		
		while(x != T.nil) {
			y = x;
			if(z.data < x.data) {
				x = x.left;
			}
			else 
				x = x.right;
			}
		z.parent = y;
		if(y == T.nil) 
			T.root = z;
		else if(z.data < y.data) 
			y.left = z;
		else 
			y.right = z;
		z.color = 0;
		ins_fixup(T,z);
	}
	
	private static void ins_fixup(RedBlackTree T, node z) {		//Fix up method for RBT Insert 
		while(z.parent.color == 0) {
			if(z.parent == z.parent.parent.left) {
				node y = z.parent.parent.right;	 	// case 1
				if(y.color == 0) {
					z.parent.color = 1;
					y.color = 1;
				    z.parent.parent.color = 0;
					z = z.parent.parent;
				}
				else {
					if(z == z.parent.right)	{		// case 2
						z = z.parent;
						left_rotate(T, z);
					}

					z.parent.color = 1;				// case 3
					z.parent.parent.color = 0;
					right_rotate(T, z.parent.parent);
				}
			}
			if(z.parent == z.parent.parent.right) {
				node y = z.parent.parent.left;	 	// case 1
				if(y.color == 0) {
					z.parent.color = 1;
					y.color = 1;
					z.parent.parent.color = 0;
					z = z.parent.parent;
				}
				else {
					if(z == z.parent.left)	{		// case 2
						z = z.parent;
						right_rotate(T, z);
					}
					z.parent.color = 1;				// case 3
					z.parent.parent.color = 0;
					left_rotate(T, z.parent.parent);
				}
			}
		}
			T.root.color = 1;
	}
	
	private void bstInsert(RedBlackTree T, node z) {		//Insert for valid RebBlackTree
		node y = T.nil;
		node x = T.root;
		z.left = T.nil;
		z.right = T.nil;
		while(x != T.nil) {
			y = x;
			if(z.data < x.data)
				x = x.left;
			else
				x = x.right;
			}
			z.parent = y;
			if(y == T.nil) {
				T.root = z;
			}
			else if(z.data < y.data)
				y.left = z;
			else
				y.right = z;
	}
	
	private void delete(RedBlackTree T, node d) {			//RBT Delete Method
		if(T.search(T.root, d.data) != nil) {
		d = T.search(T.root, d.data);
		node y = d;
		node x;
		int y_orig_color = y.color;
		if (d.left == T.nil) {
			x = d.right;
			transplant(T, d, d.right);
		}
		else if (d.right == T.nil) {
			x = d.left;
			transplant(T, d, d.left);
		}
		else {
			y = min(d.right);
			y_orig_color = y.color;
			x = y.right;
			if (y.parent == d) {
				x.parent = y;
			}
			else {
				transplant(T, y, y.right);
				y.right = d.right;
				y.right.parent = y;
			}
			transplant(T, d, y);
			y.left = d.left;
			y.left.parent = y;
			y.color = d.color;
		}
		if (y_orig_color == 1) {
			del_fixup(T, x);
			}
		}
	}
	
	private static void transplant(RedBlackTree T, node u, node v) {	//RBT Transplant Method
		if(u.parent == T.nil) { 
			T.root = v; }
		else if(u == u.parent.left) {
			u.parent.left = v; }
		else {
			u.parent.right = v; }
		v.parent = u.parent;
	}
	
	private void del_fixup(RedBlackTree T, node x) {	//Fix up method for Delete 
		while(x != T.root && x.color == 1)
		{
			 if(x == x.parent.left)
			 {
				node w = x.parent.right;
			 	if(w.color == 0)
			 	{
					w.color = 1;
					x.parent.color = 0;
					left_rotate(T, x.parent);
					w = x.parent.right;
				} 
				if(w.left.color == 1 && w.right.color == 1) //case 2
				{
					w.color = 0;
					x = x.parent;
				}
				else
				{
					if(w.right.color == 1)
					  {
						w.left.color = 1;					// case 3
						w.color = 0;
						right_rotate(T, w);
						w = x.parent.right;
					  }

					w.color = x.parent.color;				// case 4
					x.parent.color = 1;
					w.right.color = 1;
					left_rotate(T, x.parent);
					x = T.root;
				}
			}
			else
			{
			 if(x == x.parent.right)
				{
				node w = x.parent.left;
				if(w.color == 0)							//case 1
				{
					w.color = 1;
					x.parent.color = 0;
					right_rotate(T, x.parent);
					w = x.parent.left;
				}
				if(w.right.color == 1 && w.left.color == 1)	// case 2
				{
					w.color = 0;
					x = x.parent;
				}
				else
				{
					if(w.left.color == 1)
				    {
						w.right.color = 1;					// case 3
						w.color = 0;
						left_rotate(T, w);
						w = x.parent.left;
				    }
					w.color = x.parent.color;				// case 4
					x.parent.color = 1;
					w.left.color = 1;
					right_rotate(T, x.parent);
					x = T.root;
					}
			    }
			  }
		}
		x.color = 1;
	}
	
	private node min(node x) {			//BST Tree Minimum method
		while(x.left != nil) {
			x = x.left; }
		return x;
	}
	
	private node max(node x) {			//BST Tree Maximum method
		while(x.right != nil) {
			x = x.right; }
		return x;
	}
	
	private static void left_rotate(RedBlackTree T, node x) {		//RBT Left Rotate method
		node y = x.right;
		x.right = y.left;
		if(y.left != T.nil) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if(x.parent == T.nil) {
			T.root = y;
		}
		else if(x.parent.left == x) {
			x.parent.left = y;
		}
		else 	{
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	
	private static void right_rotate(RedBlackTree T,node x) {		//RBT Right Rotate method
		node y = x.left;
		x.left = y.right;
		if(y.right != T.nil) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if(x.parent == T.nil) {
			T.root = y;
		}
		else if(x.parent.left == x) {
			x.parent.left = y;
		}
		else 	{
			x.parent.right = y;
		}
		y.right = x;
		x.parent = y;
	}
	
	public node search(node x, int k) {		//search for node with key value k
		if(x == nil || k == x.data)
			return x;
		if(k < x.data)
			return search(x.left, k);
		else
			return search(x.right, k);
	}
	
	public void preorderWalk(PrintWriter p, node x) {	//method to print the values in preorder
		if(x != nil)
		{
			p.println(x);
			preorderWalk(p,x.left);
			preorderWalk(p,x.right);
		}
	}	
	
	public static void main(String [] args) throws Exception {
	        int tempc, tempv, steps=0;
			RedBlackTree t = new RedBlackTree();
			node n, Inode;
			
			try {
			File file = new File(args[0]);
	        Scanner scan = new Scanner(file);
			
			tempc = scan.nextInt(); 
			tempv = scan.nextInt();
			if (tempc == -1) {
				steps = tempv;
			}
			while(tempc != -1) 
			{
			    n = new node(tempv, tempc);
				t.bstInsert(t,n);
				tempc = scan.nextInt();
				tempv = scan.nextInt();
	        } 
			while (scan.hasNextInt()) {
				tempc = scan.nextInt();
				tempv = scan.nextInt();
				//System.out.println(tempc + " " + tempv);
				if(tempc == 0) {
					Inode = new node(tempv);
					//System.out.println(Inode);
					t.insert(t, Inode);
				}
				else {
					if(t.search(t.root, tempv) != t.nil)
					{
						t.delete(t,t.search(t.root, tempv));
					}
				}
			}	 	
		}
			catch (FileNotFoundException e) {
		       System.out.println("IO FileNotFoundException");
		        e.printStackTrace();
		     } 
		    catch (IOException e1) {
		      System.out.println("IO Exception");
		       e1.printStackTrace();
		    }
			catch(ArrayIndexOutOfBoundsException e2) {
				System.out.println("Array Index Out Of Bounds");
				e2.printStackTrace();
			}
	
			PrintWriter out = new PrintWriter ("output.txt");
			t.preorderWalk(out, t.root);
			out.println(-1);
			out.flush();
	        out.close();

	}
}

class node {		//Create New node
	int data, color;
	node left, right, parent;

	public node() {		//used to declare null node
		color = 1;
		left = null;
		right = null;
	}

	public node(int NewNode) {		//used to create node for RBT insert
		data = NewNode;
		color = 0;
		left = null;
		right = null;
	}

	public node(int NewNode, int c) {		//used to create node for BST insert
		data = NewNode;
		color = c;
		left = null;
		right = null;
	}
	
	public String toString() {			//print method for node values
		return color + " " + data;
	}
}