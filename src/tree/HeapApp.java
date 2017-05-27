package tree;
 
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class HeapApp
{
	
	public HeapApp() throws Exception
	{
        String csvFile = "peds.csv";
    	File file = new File("heapfile.txt");
        final int PAGE_SIZE_4096 = 4096;
        final int PAGE_SIZE_8192 = 8192;
        int pageSize;
        String input;
        
        /**
         * Imports the data into the program from the .csv file
         */
    	ImportData imp = new ImportData(csvFile);
    	ArrayList<Record> records = imp.importRecords();   
    	
    	input = readInput();   	
    	if (input.equals("a")){
    		pageSize = PAGE_SIZE_4096;
    	}
    	else{
    		pageSize = PAGE_SIZE_8192;
    	}
    	
    	demonstrateHeapfile(file, pageSize, records);        
        
    	//Add dummy records to test working of the heap file
        //addDummyData(file, pageSize);
	
        /**
         * Uncomment and run this block of code if you want to search for a record
         */
//	    	HeapFile heap = new HeapFile(file, pageSize);
     	//First parameter is the heapfile, second parameter is the hourlyCounts
//	    	heap.searchRecord(file, 150); 
	}
    
    public String readInput()
    {
        String input;
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("a - 4096 bytes");
    	System.out.println("b - 8192 bytes");
    	System.out.print("Select page size (a or b): ");
    	while (!scan.hasNext("[ab]")) {
    	    System.out.println("That's not a valid option!");
    	    scan.next();
    	    System.out.print("Select page size (a or b): ");
    	}
    	
    	input = scan.next();
    	//scan.close();
    	
    	return input;
    }
    
    public void addDummyData(File file, int pageSize) throws Exception
    {  	
        ArrayList<Record> records = new ArrayList<Record>();
        
        records.add(new Record(1, "1/07/2015", 2017, "July", 7, "Saturday", 5, 12, "A Street", 10));
        records.add(new Record(2, "2/07/2015", 2017, "July", 7, "Saturday", 5, 12, "B Street", 20));
        records.add(new Record(3, "3/07/2015", 2017, "July", 7, "Saturday", 5, 12, "C Street", 10));
        records.add(new Record(4, "4/07/2015", 2017, "July", 7, "Saturday", 5, 12, "D Street", 40));
        records.add(new Record(5, "5/07/2015", 2017, "July", 7, "Saturday", 5, 12, "E Street", 50));
        records.add(new Record(6, "6/07/2015", 2017, "July", 7, "Saturday", 5, 12, "F Street", 60));
        records.add(new Record(7, "7/07/2015", 2017, "July", 7, "Saturday", 5, 12, "G Street", 70));
        records.add(new Record(8, "8/07/2015", 2017, "July", 7, "Saturday", 5, 12, "H Street", 80));
        records.add(new Record(9, "9/07/2015", 2017, "July", 7, "Saturday", 5, 12, "I Street", 90));
        records.add(new Record(10, "10/07/2015", 2017, "July", 7, "Saturday", 5, 12, "J Street", 100));
        records.add(new Record(11, "11/07/2015", 2017, "July", 7, "Saturday", 5, 12, "K Street", 150));
        records.add(new Record(12, "12/07/2015", 2017, "July", 7, "Saturday", 5, 12, "L Street", 120));
        records.add(new Record(13, "13/07/2015", 2017, "July", 7, "Saturday", 5, 12, "M Street", 130));
        records.add(new Record(14, "14/07/2015", 2017, "July", 7, "Saturday", 5, 12, "N Street", 140));
        records.add(new Record(15, "15/07/2015", 2017, "July", 7, "Saturday", 5, 12, "O Street", 150));
        records.add(new Record(16, "16/07/2015", 2017, "July", 7, "Saturday", 5, 12, "P Street", 160));       
        records.add(new Record(17, "17/07/2015", 2017, "July", 7, "Saturday", 5, 12, "Q Street", 170));
        records.add(new Record(18, "18/07/2015", 2017, "July", 7, "Saturday", 5, 12, "R Street", 180));
        records.add(new Record(19, "19/07/2015", 2017, "July", 7, "Saturday", 5, 12, "S Street", 190));
        records.add(new Record(20, "20/07/2015", 2017, "July", 7, "Saturday", 5, 12, "T Street", 200));
        records.add(new Record(21, "21/07/2015", 2017, "July", 7, "Saturday", 5, 12, "U Street", 210));
        records.add(new Record(22, "22/07/2015", 2017, "July", 7, "Saturday", 5, 12, "V Street", 220));
        records.add(new Record(23, "23/07/2015", 2017, "July", 7, "Saturday", 5, 12, "W Street", 230));
        records.add(new Record(24, "24/07/2015", 2017, "July", 7, "Saturday", 5, 12, "X Street", 240));
        records.add(new Record(25, "25/07/2015", 2017, "July", 7, "Saturday", 5, 12, "Y Street", 250));
        records.add(new Record(26, "26/07/2015", 2017, "July", 7, "Saturday", 5, 12, "Z Street", 260));
        records.add(new Record(27, "27/07/2015", 2017, "July", 7, "Saturday", 5, 12, "1 Street", 270));
        records.add(new Record(28, "28/07/2015", 2017, "July", 7, "Saturday", 5, 12, "2 Street", 280));
        records.add(new Record(29, "29/07/2015", 2017, "July", 7, "Saturday", 5, 12, "3 Street", 290));
        records.add(new Record(30, "30/07/2015", 2017, "July", 7, "Saturday", 5, 12, "4 Street", 300));
        records.add(new Record(31, "31/07/2015", 2017, "July", 7, "Saturday", 5, 12, "5 Street", 310));
        records.add(new Record(32, "32/07/2015", 2017, "July", 7, "Saturday", 5, 12, "6 Street", 320));
        records.add(new Record(33, "33/07/2015", 2017, "July", 7, "Saturday", 5, 12, "7 Street", 330));
        records.add(new Record(34, "34/07/2015", 2017, "July", 7, "Saturday", 5, 12, "8 Street", 340));
        records.add(new Record(35, "35/07/2015", 2017, "July", 7, "Saturday", 5, 12, "9 Street", 350));
        
        demonstrateHeapfile(file, pageSize, records);
    }

    public void demonstrateHeapfile(File file, int pageSize, ArrayList<Record> records) throws Exception
    {
    	int offset;
    	long sizeOfFile;
    	long numOfPages;
    	final int numOfRecords;
    	RandomAccessFile raf;
    	HeapFile heap;
    	
    	if (pageSize == 4096)   
    		numOfRecords = 15;
    	else
    		numOfRecords = 30;
    		
        heap = new HeapFile(file, pageSize);
    	ArrayList<HeapPage> pages = heap.insertRecords(records);
    	heap.writePage(pages);
    	
    	raf = new RandomAccessFile(file, "rw");
    	offset = 0;
    	raf.seek(offset);
    	sizeOfFile = file.length();
    	numOfPages = sizeOfFile / pageSize;
    	
    	for (int i=0; i < numOfPages; i++)
    	{
    		raf.seek(pageSize*i);
    		
    		for (int j=0; j < numOfRecords; j++)
    		{
    	    	System.out.println("hourlyCounts: " + raf.readInt());
    	    	System.out.println("ID: " + raf.readInt());
    			System.out.println("PageID: " + raf.readInt());
    	    	System.out.println("SlotNum: " + raf.readByte());
    	    	System.out.println("DateTime: " + raf.readUTF());
    	    	System.out.println("Year: " + raf.readInt());
    	    	System.out.println("Month: " + raf.readUTF());
    	    	System.out.println("mDate: " + raf.readByte());
    	    	System.out.println("Day: " + raf.readUTF());
    	    	System.out.println("Time: " + raf.readByte());
    	    	System.out.println("SensorID: " + raf.readByte());
    	    	System.out.println("Sensor Name: " + raf.readUTF());
    	    	System.out.println();
    		}
    	}
    	raf.close();
    }
 
}

