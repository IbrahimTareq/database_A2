package tree;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class HeapFile 
{
	private File file;
	public static final int PAGE_SIZE = 4096;
    
    public HeapFile(File f) 
    {
        this.file = f;
    }
	
	public ArrayList<HeapPage> insertRecords(ArrayList<Record> listOfRecords)
	{
		//Arraylist that will contain all the pages
		ArrayList<HeapPage> pages = new ArrayList<HeapPage>();
		int pageID = 0;
		int slotNum = 0;
		int k = 0;
		
		//Create new heap page
		HeapPage newHeapPage = createPage(pageID);
		//Variable assigned value of page header
		int pageLength = newHeapPage.header.length;
		
		for (int i=0; i < listOfRecords.size(); i++)
		{
			for (k=0; k < pageLength; k++)
			{
				//If empty slot found
				if (!newHeapPage.isSlotUsed(k))
				{
					slotNum = k;
					Record rec = listOfRecords.get(i);
					RecordId rid = new RecordId(pageID, slotNum);
					rec.setRecordId(rid);													
					//Insert record into page slot 
					newHeapPage.records[k] = rec;
					//Set slot reference to full
					newHeapPage.header[k] = 1;
					break;
				}
			}
			
			//Inserts page into heap file if maximum page length reached then 
			//increments pageID, add to heap file and add a new Heap Page
			if (k == pageLength - 1)
			{
				pageID++;
				pages.add(newHeapPage);
				//Create a new Heap page with same name but different pageID
				newHeapPage = createPage(pageID);
			}
			
			//Inserts partially full page into heap file  
			if (i == listOfRecords.size() - 1)
			{
				pages.add(newHeapPage);
			}
		}
		return pages;
	}
	
	//Function that writes each page to the heap file
	public void writePage(ArrayList<HeapPage> pages) throws IOException 
	{
    	RandomAccessFile raf = new RandomAccessFile(this.file, "rw");
    	int offset = 0;
    	raf.seek(offset);
    	
    	for (int i=0; i<pages.size(); i++)
    	{
        	raf.write(pages.get(i).getPageData());	
    	}    
    	raf.close();
	}
	
	public HeapPage createPage(int pid)
	{
		//Create new heap page
		HeapPage newHeapPage = new HeapPage();
		newHeapPage.setPageId(pid);
		
		return newHeapPage;
	}
	
	public byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}

}
