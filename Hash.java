import java.util.Scanner;

class Customer {
	long id;
	String name;
	Double balance;
	Customer(long id, String nm, Double bal) {
		this.id = id;
		name = nm;
		balance = bal;
	}
	Customer(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Customer entry :");
		System.out.print("Enter id :");
		id = sc.nextLong();
		System.out.print("Enter name :");
		name = sc.next();
		System.out.print("Enter balance :");
		balance = sc.nextDouble();
		
	}
	
	public String toString() {
		String rep = "[Customer ID : " + id + ", Name : " + name + ", Balance :" + balance + "]" ;
		return rep;
	}
}


class HashTable {
	static int MAX_SIZE = 4;
	int size;
	int hashadress;
	Customer hashtable[] = new Customer[MAX_SIZE];
	
	HashTable() {
		for (int i = 0; i < MAX_SIZE; i ++)
			hashtable[i] = null;
	}
	int hash(long key){
		return (int) (key % MAX_SIZE);
	}
	
	void insert(){
	    char ch;
	    Scanner sc= new Scanner(System.in);
	    do{
    		if (size == MAX_SIZE){
    			System.out.println("Cannot accept more entries :/");
    			System.out.println("-----------------------------------");
    			return;
    		}
    		Customer c = new Customer();
    		int h = hash(c.id);
    		while (hashtable[h] != null) 
    			h = (h + 1) % MAX_SIZE;
    		hashtable[h] = c;
    		size ++;
    		System.out.println("Entry successful.");
    		System.out.println("-----------------------------------");
    		System.out.print("Do you wanna add more entries?(y/n): ");
    		ch=sc.next().charAt(0);
	    }while(ch=='y'|| ch=='Y');
	}
	
	void display(){
		if (size == 0){
			System.out.println("No entries.");
			System.out.println("-----------------------------------");
			return;
		}
		System.out.println(size + " entries :");
		for (int i = 0; i < MAX_SIZE; i ++){
			if (hashtable[i] != null)
				System.out.println(hashtable[i]);
		}
		System.out.println("-----------------------------------");
		
	}
	void search(long id){
		int h = hash(id);
		int n_searched = 0;
		while (n_searched < MAX_SIZE){
			Customer cur = hashtable[h];
			if (cur != null && cur.id == id){
				System.out.print("Found : ");
				System.out.println(cur);
				System.out.println("-----------------------------------");
				return;
			}
			h = (h + 1) % MAX_SIZE;
			n_searched ++;
		}
		System.out.println("Entry not found.");
		System.out.println("-----------------------------------");
		
	}
	
	void delete(long id){
		int h = hash(id);
		int n_searched = 0;
		while (n_searched < MAX_SIZE){
			Customer cur = hashtable[h];
			if (cur != null && cur.id == id){
				hashtable[h] = null;
				System.out.println("Deleted customer with id " + id + ".");
				System.out.println("-----------------------------------");
				size --;
				return;
			}
			h = (h + 1) % MAX_SIZE;
			n_searched ++;
		}
		System.out.println("Entry not found.");
		System.out.println("-----------------------------------");
	}
}

public class Main {
	public static void main(String args[]) {
		HashTable t = new HashTable();
		Scanner sc = new Scanner(System.in);
		int choice;
		do{
    		System.out.println("\n-------------MENU---------------");
    		System.out.println("1. Insert new customer.");
    		System.out.println("2. Display all entries");
    		System.out.println("3. Search for a given entry");
    		System.out.println("4. Delete an entry");
    		System.out.println("0. Exit");
    		System.out.println("-----------------------------------");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			long  id = 0;
			
			switch(choice){
    			case 1: 
    				t.insert();
    				break;
    			case 2:
    				t.display();
    				break;
    			case 3:
    				System.out.print("Enter id :");
    				id = sc.nextLong();
    				t.search(id);
    				break;
    			case 4:
    				System.out.print("Enter id :");
    				id = sc.nextLong();
    				t.delete(id);
    				break;
    			case 0 :
    				System.out.println("Terminated.");
    				break;
    			default :
    				System.out.println("Invalid input.");
    				break;
    			}
		} while(choice!=0);
	}
}


