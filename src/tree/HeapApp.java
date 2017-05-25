package tree;
 
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class HeapApp<Key extends Comparable<Key>, Value> {
	
	
    public static void main(String[] args) throws Exception {	
    	
        String csvFile = "C:\\Users\\Ibrahim\\Desktop\\peds.csv";
        final int PAGE_SIZE = 4096;
        int numOfRecords = 15;
   
    	ImportData imp = new ImportData(csvFile);
    	ArrayList<Record> records = imp.importRecords();

    	File file = new File("write.txt");
    	//long fileSize = file.length();
    	HeapFile heap = new HeapFile(file);
    	ArrayList<HeapPage> pages = heap.insertRecords(records);
    	heap.writePage(pages);
    	
    	RandomAccessFile raf = new RandomAccessFile(file, "rw");
    	int offset = 0;
    	raf.seek(offset);
    	long sizeOfFile = file.length();
    	long numOfPages = sizeOfFile / PAGE_SIZE;
    	
    	for (int i=0; i < numOfPages; i++)
    	{
    		raf.seek(PAGE_SIZE*i);
    		
    		for (int j=0; j < numOfRecords; j++)
    		{
    			System.out.println("PID: " + raf.readInt());
    	    	System.out.println("SlotNum: " + raf.readByte());
    	    	System.out.println("ID: " + raf.readInt());
    	    	System.out.println("DateTime: " + raf.readUTF());
    	    	System.out.println("Year: " + raf.readInt());
    	    	System.out.println("Month: " + raf.readUTF());
    	    	System.out.println("mDate: " + raf.readByte());
    	    	System.out.println("day: " + raf.readUTF());
    	    	System.out.println("time: " + raf.readByte());
    	    	System.out.println("sensorID: " + raf.readByte());
    	    	System.out.println("sensorName: " + raf.readUTF());
    	    	System.out.println("hourlyCounts: " + raf.readInt());
    	    	System.out.println();
    		}
    	}
    	raf.close();
    } 

}








/*Record obj = new Record(1, "1/07/2015", 2017, "July", 7, "Saturday", 5, 12, "A Street", 25);
Record obj2 = new Record(2, "2/07/2015", 2017, "July", 7, "Saturday", 5, 12, "B Street", 25);
Record obj3 = new Record(3, "3/07/2015", 2017, "July", 7, "Saturday", 5, 12, "C Street", 25);
Record obj4 = new Record(4, "4/07/2015", 2017, "July", 7, "Saturday", 5, 12, "D Street", 25);
Record obj5 = new Record(5, "5/07/2015", 2017, "July", 7, "Saturday", 5, 12, "E Street", 25);
Record obj6 = new Record(6, "6/07/2015", 2017, "July", 7, "Saturday", 5, 12, "F Street", 25);
Record obj7 = new Record(7, "7/07/2015", 2017, "July", 7, "Saturday", 5, 12, "G Street", 25);
Record obj8 = new Record(8, "8/07/2015", 2017, "July", 7, "Saturday", 5, 12, "H Street", 25);
Record obj9 = new Record(9, "9/07/2015", 2017, "July", 7, "Saturday", 5, 12, "I Street", 25);
Record obj10 = new Record(10, "10/07/2015", 2017, "July", 7, "Saturday", 5, 12, "J Street", 25);
Record obj11 = new Record(11, "11/07/2015", 2017, "July", 7, "Saturday", 5, 12, "K Street", 25);
Record obj12 = new Record(12, "12/07/2015", 2017, "July", 7, "Saturday", 5, 12, "L Street", 25);
Record obj13 = new Record(13, "13/07/2015", 2017, "July", 7, "Saturday", 5, 12, "M Street", 25);
Record obj14 = new Record(14, "14/07/2015", 2017, "July", 7, "Saturday", 5, 12, "N Street", 25);
Record obj15 = new Record(15, "15/07/2015", 2017, "July", 7, "Saturday", 5, 12, "O Street", 25);
Record obj16 = new Record(16, "16/07/2015", 2017, "July", 7, "Saturday", 5, 12, "P Street", 25);

ArrayList<Record> records = new ArrayList<Record>();
records.add(obj);
records.add(obj2);
records.add(obj3);
records.add(obj4);
records.add(obj5);
records.add(obj6);
records.add(obj7);
records.add(obj8);
records.add(obj9);
records.add(obj10);
records.add(obj11);
records.add(obj12);
records.add(obj13);
records.add(obj14);
records.add(obj15);
records.add(obj16);*/
