import java.util.* ;

class Adjacencies {
	LinkedList<Integer> adj;
	Adjacencies(){
		adj = new LinkedList<Integer>();
	}
}

class Graph{
	int n; 
	int e; 
	int[][] adjMat; 
	Adjacencies adjList[] ;
	boolean matImplem;
	
	Graph(){
		n = 0;
		e = 0;
	}
	
	void createUsingAdjMat(){
		matImplem = true;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of houses: " );
		n = sc.nextInt();
		System.out.print("Enter the number of lanes: " );
		e = sc.nextInt();
		adjMat = new int[n][n];
		System.out.print("Should the lanes be undirected? (1=Yes, 0=No): ");
		boolean undirected = (sc.nextInt() == 1)? true : false;
		for(int i=0; i<e ; i++){
			System.out.println("\nLane " + i + " : ");
			System.out.print("Enter house numbers (Space separated): " );
			int u = sc.nextInt();
			int v = sc.nextInt();
			adjMat[u][v] = 1;
			if (undirected)
				adjMat[v][u] = 1;
		}
	}
	
	void createUsingAdjList(){
		matImplem = false;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of houses: " );
		n = sc.nextInt();
		System.out.print("Enter the number of lanes: " );
		e = sc.nextInt();
		adjList = new Adjacencies[n];
		System.out.print("Should the lanes be undirected? (1=Yes, 0=No): ");
		boolean undirected = (sc.nextInt() == 1)? true : false;
		for (int i=0; i<n; i++){
			adjList[i] = new Adjacencies();
		}
		
		for(int i=0; i<e;i++){
			System.out.println("Lane " + i + " : ");
			System.out.print("Enter house numbers (space separated) : " );
			int u = sc.nextInt();
			int v = sc.nextInt();
			adjList[u].adj.add(v);
			if (undirected)
				adjList[v].adj.add(u);
		}
	}
	
	void display(){
		if (matImplem)
			displayAdjMat();
		else
			displayAdjList();
	}
	
	void displayAdjList(){
		System.out.println("Adjacency Lists :");
		for(int i=0; i<n; i++){
			System.out.print(i + " : ");
			System.out.println(adjList[i].adj);
		}
				
	}
	
	void displayAdjMat() {
		System.out.println("Adjacency Matrix :");
		for( int i=0; i<n; i++) {
			for (int j=0; j<n; j++) 
				System.out.print(adjMat[i][j] + " ");
			System.out.println();
		}
	}
	
	void bfs() {
		System.out.println("Breadth First Traversal");
		if (matImplem)
			bfsMat();
		else
			bfsList();
	}
	
	void bfsMat() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter starting vertex : " );
		int start = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean visited[] = new boolean[n];
		q.add(start);
		while(!q.isEmpty()){
			int v = q.remove();
			if (visited[v])
				continue;
			for(int i = 0; i < n; i ++){
				if (adjMat[v][i] == 1)
					q.add(i);
			}
			visited[v] = true;
			System.out.print(v + " ");
		}
		System.out.println();
	}
	
	void bfsList(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter starting vertex : " );
		int start = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean visited[] = new boolean[n];
		q.add(start);
		while(!q.isEmpty()){
			int v = q.remove();
			if (visited[v])
				continue;
			q.addAll(adjList[v].adj);
			visited[v] = true;
			System.out.print(v + " ");
		}
		System.out.println();
	}

	void dfs() {
		System.out.println("Depth First Traversal");
		if (matImplem)
			dfsMat();
		else
			dfsList();
	}
	
	void dfsMat() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter starting vertex : " );
		int start = sc.nextInt();
		
		Stack<Integer> stk = new Stack<Integer>();
		boolean visited[] = new boolean[n];
		stk.add(start);
		while(!stk.isEmpty()){
			int v = stk.pop();
			if (visited[v])
				continue;
			for(int i = 0; i < n; i ++){
				if (adjMat[v][i] == 1)
					stk.push(i);
			}
			visited[v] = true;
			System.out.print(v + " ");
		}
		System.out.println();
	}
	
	void dfsList(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter starting vertex : " );
		int start = sc.nextInt();
		
		Stack<Integer> stk = new Stack<Integer>();
		boolean visited[] = new boolean[n];
		stk.add(start);
		while(!stk.isEmpty()){
			int v = stk.pop();
			if (visited[v])
				continue;
			stk.addAll(adjList[v].adj);
			visited[v] = true;
			System.out.print(v + " ");
		}
		System.out.println();
	 }
}


public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Graph g = new Graph();
		int choice;
		do {
    		System.out.println("\n-----------------------------------------");
    		System.out.println("\tMENU :");
    		System.out.println("1.Create graph using adjacency matrix");
    		System.out.println("2.Create graph using adjacency list");
    		System.out.println("3.Display using breadth-first traversal");
    		System.out.println("4.Display using depth-first traversal");
    		System.out.println("5.Display graph adjacencies");
    		System.out.println("0.Exit");
    		System.out.println("-----------------------------------------");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1 :
				g.createUsingAdjMat();
				break;
			case 2 :
				g.createUsingAdjList();
				break;
			case 3 :
				g.bfs();
				break;
			case 4 :
				g.dfs();
				break;
			case 5 :
				g.display();
				break;
			case 0 :
				System.out.println("Terminated.");
				break;
			default :
				System.out.println("Invalid input.");
				break;
			}
		} while(choice != 0);

		
	}
}









