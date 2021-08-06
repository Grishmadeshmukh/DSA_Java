import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


class Node{
    int data;
    Node link;
    Node(){
        data=-1;
        link=null;
    }
    Node(int d){
        data=d;
        link=null;
    }
}

class List{
    Scanner sc= new Scanner(System.in);
    Node head;
    List(){
        
        head=null;
    }
    Node gethead(){
        System.out.println("head data: "+ head.data);
        return head;
    }
    void create(){
        int d;
        char ch;
        Node ptr;      
        do{
            System.out.print("\nEnter data: "); 
            d= sc.nextInt();
            Node temp=new Node(d);  
            if(head==null)
                head=temp;
            else{
                ptr=head;
                while(ptr.link!=null){   //Traverse ptr to last node
                    ptr=ptr.link;
                }
              ptr.link=temp;  
            }
            System.out.print("Do you want to continue?(y/n): "); 
            ch = sc.next().charAt(0);
        }while(ch=='y'||ch=='Y');
    }
    void display(){
        Node ptr=head;
        if(head==null){
            System.out.print("\nList is empty"); 
        }
        System.out.print("\nList contains\n"); 
        while(ptr!=null){
            System.out.print(ptr.data+ "->"); 
            ptr=ptr.link;
        }
        System.out.print("NULL");
        //System.out.print("\nEnd of list");
    }
    void insert(){
        int pos,d;
        Node ptr=head;
        Node prev = new Node();
        System.out.print("Enter data for the new node to be inserted: ");
        d= sc.nextInt();
        System.out.print("Enter position where node should be inserted: ");
        pos= sc.nextInt();
        Node temp= new Node(d);
        // ll is empty
        if (head == null) { 
            head = temp;
            System.out.println("Node Appended");
        }
        //pos=1
        if(pos==1){
            temp.link=head;
            head=temp;
        }
        // between two nodes
        else{
            for(int i=1;i<pos;i++){
                prev=ptr;
                ptr=ptr.link;
            }
            prev.link=temp;
            temp.link=ptr;
        }
    }
    void sortasc(){
        Node p1,p2;
        int i,c=0;
        p1=head;
        while(p1!=null){
            c++;
            p1=p1.link;
        }
        for(i=1;i<c;i++){
            p1=head;
            p2=p1.link;
            for(int j=0;j<c-1;j++){
                if(p1.data>p2.data){
                    int temp=p1.data;
                    p1.data=p2.data;
                    p2.data=temp;
                }
                p1=p2;
                p2=p2.link;
            }
        }
    }
    void sortdesc(){
        Node p1,p2;
        int i,c=0;
        p1=head;
        while(p1!=null){
            c++;
            p1=p1.link;
        }
        for(i=1;i<c;i++){
            p1=head;
            p2=p1.link;
            for(int j=0;j<c-1;j++){
                if(p1.data<p2.data){
                    int temp=p1.data;
                    p1.data=p2.data;
                    p2.data=temp;
                    
                }
                p1=p2;
                p2=p2.link;
            }
        }
    }
    
    Node combine(Node p,Node q){
        //System.out.println("Entered");
        if (p == null && q == null)
			return null;
		else if (p != null && q == null)
			return p;
		else if (p == null && q != null)
			return q;
		else if(p.data == q.data){
		    System.out.println("p.data == q.data ("+p.data +","+ q.data+")");
		    p.link = combine(p.link, q);
		    return p;
		}
		else if (p.data < q.data) {
		    System.out.println("p.data < q.data ("+p.data +","+ q.data+")");
			p.link = combine(p.link, q);
			return p;
		} 
		else {
		    System.out.println("p.data > q.data ("+p.data +","+ q.data+")");
			q.link = combine(p, q.link);
			return q;

		}
    }
    
    void deletenode(){
        System.out.print("\nEnter data to be deleted: ");
        int d= sc.nextInt();
        Node cur=head;
        Node prev=head;
        while((cur.data!=d) && (cur!=null)){
            prev=cur;
            cur=cur.link;
        }
        //delete first node
        if(cur==head){
            //cur=head;
            head=head.link;
            cur=null;
        }
        //delete otherwise
        else{
            prev.link=cur.link;
            cur=null;
        }    
    }
    
    void deletefirst(){
        Node ptr= head;
        //ll empty
        if(head==null){
            System.out.print("\nList is empty");
        }
        //delete first element
        ptr=head;
        head=head.link;
        ptr=null;
    }
    
    void deletelast(){
        Node current=head;
        Node previous= new Node();
        while(current.link!=null){
          previous=current;
          current=current.link;	
        }
        //tail=previous;
        previous.link=null;
        current=null;
    }
    
}

public class Main {
    public static void main(String[] args) throws IOException {
        List l1=new List();
        List l2=new List();
        Node h1 = new Node();
        Node h2 = new Node();
        
        l1.create();
        //l1.sortasc();
        //l1.sortdesc();
        l1.display();
        
        //l2.create();
        //l2.sortasc();
        //l2.display();
        
       // h1=l1.gethead();
       // h2=l2.gethead();
        
       // l1.combine(h1,h2);
        //l1.display();
        
        //l1.deletenode();
        l1.deletefirst();
        l1.deletelast();
        l1.display();
    }
}
