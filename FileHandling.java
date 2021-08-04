import java.io.Serializable;
import java.io.*;
import java.util.Scanner;
import java.util.*;

class Student implements Serializable{
	int r_no;
	double marks;
	String name;
	Student(int rl,String nm,double mks){
		r_no = rl;
		name = nm;
		marks = mks;
	}
	public String toString(){
		String s= "Student{"+" Roll.no = "+r_no+",   name = "+name+",   marks = "+marks+" }";
		return s;
	}
}

class FileHandler{
    
    void writeFile(){
		Vector<Student> st = new Vector<>();
		int id = 0, choice = 1;
		double mk = 0;
		String nm = "";
		Scanner sc = new Scanner (System.in);
		System.out.println("\n<<< Enter data for file Students.txt >>>" );
		do {
			System.out.println("\nStudent entry :");
			System.out.print("Enter roll no. :");
			id = sc.nextInt();
			System.out.print("Enter name :");
			nm = sc.next();
			System.out.print("Enter marks :");
			mk = sc.nextInt();
			Student s = new Student(id,nm,mk);
			st.add(s);
			System.out.println("[ Student entry complete. ]\n");
			System.out.print("Do you want to continue entering (1 = Yes, 0 = No)?: ");
			choice = sc.nextInt();
		}while (choice == 1);
		
		try{
			FileOutputStream f = new FileOutputStream("Students.txt");
			ObjectOutputStream obs = new ObjectOutputStream(f);
			obs.writeObject(st);
			obs.close();
			f.close();
			System.out.println("Entries written to file Students.txt" + " : " + st.size());
			System.out.println("-----------------------------------------");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("-----------------------------------------");
		}
	}
	
	void readFile(){
		Vector<Student> st = new Vector<>();
		System.out.println("\n<<< Reading file Students.txt >>>");
		try {
			FileInputStream f = new FileInputStream("Students.txt");
			ObjectInputStream obs = new ObjectInputStream(f);
			st = (Vector<Student>) obs.readObject();
			obs.close();
			f.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("-----------------------------------------");
			return;
		}
		Iterator<Student> itr = st.iterator();
		while (itr.hasNext()){
			Student s = itr.next();
			System.out.println(s);
		}
		System.out.println("-----------------------------------------");
	}
	
	void search(int r){
		Vector<Student> st = new Vector<>();
		try {
			FileInputStream f = new FileInputStream("Students.txt");
			ObjectInputStream obs = new ObjectInputStream(f);
			st = (Vector<Student>) obs.readObject();
			obs.close();
			f.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("-----------------------------------------");
			return;
		}
		Iterator<Student> itr = st.iterator();
		while (itr.hasNext()){
			Student s = itr.next();
			if (s.r_no == r){
				System.out.println("Entry found:  ");
				System.out.println(s);
				System.out.println("-----------------------------------------");
				return;
			}
		}
		System.out.println("Entry not found.");
		System.out.println("-----------------------------------------");
	}
	
	void delete(int r){
		Vector<Student> st = new Vector<>();
		try {
			FileInputStream f = new FileInputStream("Students.txt");
			ObjectInputStream obs = new ObjectInputStream(f);
			st = (Vector<Student>) obs.readObject();
			obs.close();
			f.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("-----------------------------------------");
			return;
		}
		int i = 0;
		for (; i < st.size(); i++) {
			if (st.get(i).r_no == r)
				break;
		}
		if (i == st.size()){
			System.out.println("Entry not found.");
			System.out.println("-----------------------------------------");
			return;
		}
		else{
			st.remove(i);
			try {
				FileOutputStream f = new FileOutputStream("Students.txt");
				ObjectOutputStream obs = new ObjectOutputStream(f);
				obs.writeObject(st);
				obs.close();
				f.close();
				System.out.println("Entry deleted successfully.");
				System.out.println("-----------------------------------------");System.out.println("-----------------------------------------");
			}
			catch (Exception e){
				System.out.println(e.getMessage());
				System.out.println("-----------------------------------------");
			}
			return;
		}
	}
}


public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		FileHandler f = new FileHandler();
		int choice;
		do{
    		System.out.println("================ MENU ==================");
    		System.out.println("1. Write to file");
    		System.out.println("2. Read file");
    		System.out.println("3. Search for a given entry");
    		System.out.println("4. Delete an entry");
    		System.out.println("0. Exit");
    		System.out.println("==========================================");
    		System.out.print("Enter your choice: ");
    		choice = sc.nextInt();
    		int rno = 0;
			switch(choice) {
				case 1:
					f.writeFile();
					break;
				case 2:
					f.readFile();
					break;
				case 3:
					System.out.print("\nEnter roll no. to be searched :");
					rno = sc.nextInt();
					f.search(rno);
					break;
				case 4:
					System.out.print("\nEnter roll no. to be deleted :");
					rno = sc.nextInt();
					f.delete(rno);
					break;
				case 0 : 
					System.out.println("\nExited.");
					break;
				default:
					System.out.println("Invalid input.");
					break;
			}
		}while(choice!=0);
	}
}
