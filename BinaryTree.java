import java.util.LinkedList;
import java.util.Queue; 
import java.util.Scanner; 
import java.util.Stack; 

class Node { 
     int data; 
     Node lc; 
     Node rc; 
     
     Node(int data) { 
        this.data = data; 
        lc = null; 
        rc = null; 
     } 
} 

class BinaryTree { 
     Node root; 
     BinaryTree(){ 
         root = null; 
     } 
     Scanner sc = new Scanner(System.in); 
    
    void createBinaryTree() { 
        int data; 
        char ch; 
        do{    
             System.out.print("Enter data : "); 
             data = sc.nextInt(); 
             Node temp = new Node(data); 
             
            if (root == null) { 
                 root = temp; 
                 System.out.println("Root node added!!"); 
            } 
             
            else{ 
                 Node ptr; 
                 char dir; 
                 ptr = root; 
                 
                while (ptr != null) { 
                     System.out.println("Current node Data : " + ptr.data); 
                     System.out.print("Direction of the node(l/r) " + ptr.data + " : "); 
                     dir = sc.next().charAt(0); 
                     
                    if (dir == 'L' || dir == 'l') { 
                         /* if left child is null insert newly created Node there
                          else increment ptr */
                        if (ptr.lc == null){ 
                             ptr.lc = temp; 
                             System.out.println("Node added!!"); 
                             break; 
                        } 
                        else{ 
                             ptr = ptr.lc; 
                        } 
                    } 
                    else{ 
                         /* when dir= R
                          if right child is null insert newly created Node there
                          else increment ptr */
                        if (ptr.rc == null) { 
                             ptr.rc = temp; 
                             System.out.println("Node added!!"); 
                             break; 
                        } 
                        else{ 
                         ptr = ptr.rc; 
                        } 
                    } 
                } 
            } 
             System.out.print("\nDo you want to add more nodes ?(y/n) : "); 
             ch = sc.next().charAt(0); 
             System.out.println(""); 
        } while (ch != 'N' && ch != 'n'); 
    }
    
    // RECURSIVE FUNCTIONS
    
    void inOrderRecursive(Node localRoot) { 
        if (localRoot != null){ 
         inOrderRecursive(localRoot.lc); 
         System.out.print(localRoot.data + " "); 
         inOrderRecursive(localRoot.rc); 
        }
    }
    
    void preOrderRecursive(Node localRoot) { 
        if (localRoot != null) { 
             System.out.print(localRoot.data + " "); 
             preOrderRecursive(localRoot.lc); 
             preOrderRecursive(localRoot.rc); 
        } 
    } 
 
    void postOrderRecursive(Node localRoot) { 
            if (localRoot != null) { 
             postOrderRecursive(localRoot.lc); 
             postOrderRecursive(localRoot.rc); 
             System.out.print(localRoot.data + " "); 
            } 
    } 
    
    // NON-RECURSIVE FUNCTIONS
    
    void inOrderNonRecursive() { 
        Stack<Node> s = new Stack<Node>(); 
        Node ptr = root; 
        Node popped; 
        if (root == null) { 
            System.out.println("Empty tree!!"); 
            return; 
        } 
        
        while (true) { 
            while (ptr != null) { 
                s.push(ptr); 
                ptr = ptr.lc; 
            } 
            if (!s.empty()) { 
                popped = s.pop(); 
                ptr = popped; 
                System.out.print(popped.data + " "); 
                ptr = ptr.rc; 
            } 
            else
            break; 
        } 
    } 
    
    
    void preOrderNonRecursive() { 
         Stack<Node> s = new Stack<Node>(); 
         Node ptr = root; 
         Node popped; 
        if (root == null) { 
         System.out.println("Empty tree!!"); 
         return; 
        } 
        while (true) { 
            while (ptr != null) { 
                 System.out.print(ptr.data + " "); 
                 s.push(ptr); 
                 ptr = ptr.lc; 
            } 
            if (!s.empty()) { 
                 popped = s.pop(); 
                 ptr = popped; 
                 ptr = ptr.rc; 
            } 
             else
             break; 
        } 
    }
  
    
    void postOrderNonRecursive() { 
        Stack<Node> s1 = new Stack<Node>(); 
        Stack<Character> s2 = new Stack<>(); 
        Node ptr = root; 
        Node popped; 
        char poppedFlag; 
        
        if (root == null){ 
         System.out.println("Tree Empty!!"); 
         return; 
        } 
        
        while (true) { 
            while (ptr != null) { 
                 s1.push(ptr); 
                 s2.push('L'); 
                 ptr = ptr.lc; 
            } 
            if (!s1.empty()) { 
                 popped = s1.pop(); 
                 ptr = popped; 
                 poppedFlag = s2.pop(); 
                 if (poppedFlag == 'L') { 
                     s1.push(ptr); 
                     s2.push('R'); 
                     ptr = ptr.rc; 
                 } 
                 else{ 
                     System.out.print(ptr.data + " "); 
                     ptr = null; 
                 } 
            } 
            else
             break; 
        } 
    } 
  
   // LEVEL ORDER
   
   void levelOrder(Node localRoot) { 
        Queue<Node> q = new LinkedList<>(); 
        Node ptr = root; 
        if (root == null) { 
         System.out.println("Tree Empty!!"); 
         return; 
        } 
        
        while (ptr != null) { 
         System.out.print(ptr.element + " "); 
         if (ptr.lc != null) 
            q.add(ptr.lc); 
         if (ptr.rc != null) 
            q.add(ptr.rc); 
         if (q.isEmpty()) 
            ptr = null; 
         else
            ptr = q.remove(); 
        } 
    }
    
}  // end of class BinaryTree  
    
    
public class Main { 
        public static void main(String[] args) { 
                 BinaryTree b = new BinaryTree(); 
                 b.createBinaryTree(); 
                 
                 System.out.print("InOrder Recursive Traversal : "); 
                    if (b.root == null) 
                     System.out.print("Tree Empty!!"); 
                    else
                     b.inOrderRecursive(b.root); 
                    System.out.println(""); 
                
                 System.out.print("PreOrder Recursive Traversal : "); 
                    if (b.root == null) 
                     System.out.print("Tree Empty!!"); 
                    else
                     b.preOrderRecursive(b.root); 
                    System.out.println(""); 
                 
                 System.out.print("PostOrder Recursive Traversal : "); 
                    if (b.root == null) 
                     System.out.print("Tree Empty!!"); 
                    else
                     b.postOrderRecursive(b.root); 
                    System.out.println("\n"); 
                 
                 System.out.print("InOrder Non-Recursive Traversal : "); 
                 b.inOrderNonRecursive(); 
                 System.out.println(""); 
                 
                 System.out.print("PreOrder Non-Recursive Traversal : "); 
                 b.preOrderRecursive(b.root); 
                 System.out.println(""); 
                 
                 System.out.print("PostOrder Non-Recursive Traversal : "); 
                 b.postOrderRecursive(b.root); 
                 System.out.println("\n"); 
                 
                 System.out.print("Level Order Traversal : "); 
                 b.levelOrder(b.root); 
                 System.out.println(""); 
                
    } 
}    




