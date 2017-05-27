package tree;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class BTreeApp 
{
	BTree<Integer, Integer> st;
	File file;
	int pageSize;
	
	public BTreeApp() throws Exception
	{
		final int PAGE_SIZE_4096 = 4096;
        final int PAGE_SIZE_8192 = 8192;
        int pageSize;
        String input;
		
		//Heapfile
		File file = new File("heapfile.txt");
		this.file = file;
    	
        BTree<Integer, Integer> st = new BTree<Integer, Integer>();
    	this.st = st;
    	
        //Reads input for page size from user
        input = readInput();   	
        if (input.equals("a")){
    		pageSize = PAGE_SIZE_4096;
    	}
    	else{
    		pageSize = PAGE_SIZE_8192;
    	}
        this.pageSize = pageSize;
    	
        //Page size MUST be the same as the page size entered in HeapApp
        insertIntoBtree(st, file, pageSize);
        System.out.println("Records successfully inserted into B-tree\n");
        //retrieveRecordsFromHeap(st, file, pageSize);
	}
	
	public void retrieveRecordsFromHeap() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int hours;
		String hourlyCounts;
		
    	System.out.println("Size of B-tree:	" + this.st.size() + "\n");
    	System.out.print("Enter hourlyCounts: ");
    	
    	//Validates input
    	while (!sc.hasNext("[0-9]+")) {
    	    System.out.println("That's not a valid option!");
    	    sc.next();
    	    System.out.print("Select page size (a or b): ");
    	}
    	
    	hourlyCounts = sc.next();
    	hours = Integer.parseInt(hourlyCounts);
    	//sc.close();
    	
    	//Retrieves all the records for the hourlyCounts
    	this.st.get(hours);
    	System.out.println("Searching for records with hourlyCounts of " + hours + "..");
    	System.out.println();
    	searchRecord(this.file, hours, this.pageSize);
	}

	public String readInput()
	{
		String input;
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("a - 4096 bytes");
    	System.out.println("b - 8192 bytes");
    	System.out.print("Select page size used for Heapfile (a or b): ");
    	while (!scan.hasNext("[ab]")) {
    	    System.out.println("That's not a valid option!");
    	    scan.next();
    	    System.out.print("Select page size (a or b): ");
    	}   	
    	input = scan.next();
    	
    	return input;
	}
	
	//Inserts the records from the heapfile into the B-tree
	public void insertIntoBtree(BTree<Integer, Integer> st, File file, int pageSize) throws Exception
    {
    	RandomAccessFile raf;
		final int numOfRecords;
        int recordID;
		int offset = 0;
    	long sizeOfFile = file.length();
    	long numOfPages = sizeOfFile / pageSize;
    	
    	if (pageSize == 4096)   
    		numOfRecords = 15;
    	else
    		numOfRecords = 30;
    	
    	raf = new RandomAccessFile(file, "rw");
    	raf.seek(offset);
    	
    	for (int i=0; i < numOfPages; i++)
    	{
            int hourlyCounts;
    		raf.seek(pageSize*i);
    		
    		for (int j=0; j < numOfRecords; j++)
    		{
    			hourlyCounts = raf.readInt();
    			//Denies insertion of empty records
    			if (hourlyCounts == 0)
    			{
    				raf.readInt();
    				raf.readInt();
    				raf.readByte();
    				raf.readUTF();
    				raf.readInt();
    				raf.readUTF();
    				raf.readByte();
    				raf.readUTF();
    				raf.readByte();
    				raf.readByte();
    				raf.readUTF();
    				continue;
    			}
				recordID = raf.readInt();
				st.put(hourlyCounts, recordID);
				
  				raf.readInt();
				raf.readByte();
				raf.readUTF();
				raf.readInt();
				raf.readUTF();
				raf.readByte();
				raf.readUTF();
				raf.readByte();
				raf.readByte();
				raf.readUTF();
    		}
    	}    	
    	raf.close();
    }
	
	//Searches and prints out records for given hourlyCounts from heapfile based on index from B-tree
	public void searchRecord(File file, int hourlyCounts, int pageSize) throws Exception
	{
		RandomAccessFile raf;
    	int currentHourlyCounts;
    	final int numOfRecords;
    	boolean recordFound = false;
		int totalRecords = 0;
		int offset = 0;
    	long sizeOfFile = file.length();
    	long numOfPages = sizeOfFile / pageSize;
    	
    	if (pageSize == 4096)   
    		numOfRecords = 15;
    	else
    		numOfRecords = 30;
    	
    	raf = new RandomAccessFile(file, "rw");
    	raf.seek(offset);
    	
    	for (int i=0; i < numOfPages; i++)
    	{
    		raf.seek(pageSize*i);
    		
    		for (int j=0; j < numOfRecords; j++)
    		{
    			currentHourlyCounts = raf.readInt();
    			if (currentHourlyCounts != hourlyCounts)
    			{
    				raf.readInt();
    				raf.readInt();
    				raf.readByte();
    				raf.readUTF();
    				raf.readInt();
    				raf.readUTF();
    				raf.readByte();
    				raf.readUTF();
    				raf.readByte();
    				raf.readByte();
    				raf.readUTF();
    				continue;
    			}
    			System.out.println("hourlyCounts: " + currentHourlyCounts);
    	    	System.out.println("ID: " + raf.readInt());
    			System.out.println("PID: " + raf.readInt());
    	    	System.out.println("SlotNum: " + raf.readByte());
    	    	System.out.println("DateTime: " + raf.readUTF());
    	    	System.out.println("Year: " + raf.readInt());
    	    	System.out.println("Month: " + raf.readUTF());
    	    	System.out.println("mDate: " + raf.readByte());
    	    	System.out.println("Day: " + raf.readUTF());
    	    	System.out.println("Time: " + raf.readByte());
    	    	System.out.println("SensorID: " + raf.readByte());
    	    	System.out.println("SensorName: " + raf.readUTF());
    	    	System.out.println();
    	    	
    	    	totalRecords++;
    	    	recordFound = true;
    		}
    	}
    	
		if (recordFound == false)		
			System.out.println("No record found for the given hourlyCount value!\n");		
		else{
			System.out.println("Total records found: " + totalRecords);	
			System.out.println();
		}		
		
    	raf.close();
	}
}
