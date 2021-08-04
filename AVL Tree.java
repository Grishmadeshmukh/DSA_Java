import java.util.Scanner;
import java.util.Stack;

class node{
	long number;
	String name;
	node l,r;
	int height;
	node (long num, String nam ){
		number = num;
		name = nam;
		l = null;
		r= null;
	}
}

class avlTree {
	node root;
    int bal;
    
    avlTree(){
        root=null;
    }
    
    
    // CREATE NODE /ACCEPT DATA
    void create() {
		Scanner sc = new Scanner(System.in);
		char ch;
		long number;
		String name;
		
		
		do {
			System.out.println("\n***ENTER DETAILS***");
			System.out.print("\nEnter name: ");
			name = sc.next();
			System.out.print("Enter number: ");
			number = sc.nextLong();
			node newContact = new node(number,name);
		
			root = this.insert(root,newContact);

			System.out.print("\nWant to insert more?(y/n): ");
			ch = sc.next().charAt(0);
			
		} while(ch=='y'||ch=='Y');
		System.out.println("New contact Created!");
	}
    
    // INSERT 
    node insert (node rt ,node newContact) {
		if (rt == null){
			rt = newContact;
			return rt;
		}

		if (newContact.name.compareToIgnoreCase(rt.name) < 0){	
			rt.l = insert(rt.l,newContact);
			bal = balance_factor(rt);
			if(bal == 2) {	
				if (newContact.name.compareToIgnoreCase(rt.l.name) < 0)
					rt = LL(rt) ;
				else
					rt=LR(rt);
			}
		}
		else  {
			rt.r=insert(rt.r,newContact);
			bal=balance_factor(rt);
			if (bal == -2) {	
				if (newContact.name.compareToIgnoreCase(rt.r.name) > 0)
					rt=RR(rt);
				else
					rt=RL(rt);
				
			}
		}
		rt.height = height(rt);
		return rt;
	}
	
	
	//BALANCE FACTOR
    int balance_factor(node n)  {
		return height(n.l) - height(n.r);
	}
	//HEIGHT
	int height(node n) {
		if (n == null)
			return -1;
		int lh = height(n.l) + 1;
		int rh = height(n.r) + 1;
		return (lh > rh)? lh : rh;
	}
	
	//ROTATIONS
	node LL(node n) {
		node temp = n.l;
		n.l = temp.r;
		temp.r = n;
		temp.height = height(temp);
		n.height = height(n);
		return temp;
	}
	
	node RR(node n) {
		node temp = n.r;
		n.r = temp.l;
		temp.l = n;
		temp.height = height(temp);
		n.height = height(n);
		return temp;
	}
	
	node LR(node n) {
		n.l=RR(n.l);
		n = LL(n);
		return n;
	}

	node RL(node n) {
		n.r=LL(n.r);
		n = RR(n);
		return n;
	}
	
	//DISPLAY TREE
	void display() {
		node ptr = root;
		Stack<node> st = new Stack<node>();
		int count = 0;
		
		if (root == null){
            System.out.println("\nEmpty tree!!");
            return;
        }

		else{
    		System.out.println("\n AVL TREE");
    		while( (ptr != null) || (!st.empty()) )  {
    			while(ptr != null) {
    				st.push(ptr);
    				ptr = ptr.l;
    			}
    			if (!st.empty()) {
    				ptr = st.pop();
    				System.out.println("( Name: " + ptr.name+ "     Number : "+ ptr.number+" )");
    				count ++;
    				ptr = ptr.r;
    			}
    		}
    		System.out.println("");
    		System.out.println("Tree height is : " + height(root));
    		System.out.println("AVL tree contains "+count + " entries.");
    	}
	}
	
} // avlTree ends



public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		avlTree tree = new avlTree();
		int choice;
		
		do {
    		System.out.println("\n-----------------------------------------");
    		System.out.println("\t\tMENU");
    		System.out.println("1.Accept entries.");
    		System.out.println("2.Display entries (inorder)");
    		System.out.println("0.Exit");
    		System.out.println("-----------------------------------------");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
    			case 1 :
    				tree.create();
    				break;
    			case 2 :
    				tree.display();
    				break;
    			case 0 :
    				System.out.println("Terminated.");
    				break;
    			default :
    				System.out.println("Invalid choice");
    				break;
			}
		} while(choice!= 0);

	}

}



