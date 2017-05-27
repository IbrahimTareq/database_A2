package tree;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) throws Exception 
	{
		int choice = -1;
		Scanner scan = new Scanner(System.in);
		BTreeApp btree = null;
		
		while (choice != 0)
		{
			showMenu();
	    	while (!scan.hasNext("[1234]")) {
	    	    System.out.println("That's not a valid option!");
	    	    System.out.println();
	    	    scan.next();
	    	    showMenu();
	    	}
			
		    choice = scan.nextInt();
		    System.out.println();
		    
		    switch (choice) {
		        case 1:
		            HeapApp heap = new HeapApp();
		            break;
		        case 2:
		            btree = new BTreeApp();
		            break;
		        case 3:
		        	btree.retrieveRecordsFromHeap();
		            break;
		        case 4:
		            System.exit(0);
		            break;
		        default:
		            // The user input an unexpected choice.
		    }
		}
	}
	
	public static void showMenu()
	{
		System.out.println("********MENU********");
		System.out.println("1 - Create heapfile");
    	System.out.println("2 - Create b-tree");
    	System.out.println("3 - Search b-tree");
    	System.out.println("4 - Exit");
    	System.out.println();
    	System.out.print("Select option: ");
	}

}
