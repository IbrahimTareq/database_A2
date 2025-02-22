package tree;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class HeapPage 
{	
	int pageID;
	byte header[];
	int numOfSlots;
	Record records[];	
	int recordSize = 270;
	public final int PAGE_SIZE;
	
	public HeapPage(int pageSize)
	{
		this.PAGE_SIZE = pageSize;
		this.numOfSlots = getNumOfRecords();
		records = new Record[numOfSlots];		
		//Since header and records are same size, header index corresponds to record index
		header = new byte[numOfSlots];
		
		for (int i=0; i<header.length; i++)
		{
			//Initalizing all slots to empty
			header[i] = 0;
		}
	}
	
	public byte[] getPageData() 
	{	
		int len = PAGE_SIZE;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(len);
        DataOutputStream dos = new DataOutputStream(baos);
        
        //Create the records
        for (int i=0; i<records.length; i++) 
        {
            //Empty slot
            if (!isSlotUsed(i)) 
            {
                try {
					dos.writeByte(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
                continue;
            }
            
            //Non-empty slot
            try {
            	convertRecordToBytes(dos, records, i);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        //Fill empty space with bytes to make page length equal to page size
        int sizeOfPage = baos.size();
        int zerolen = PAGE_SIZE - sizeOfPage;
        byte[] zeroes = new byte[zerolen];
        try {
			dos.write(zeroes);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return baos.toByteArray();
	}
	
	public int getNumEmptySlots() 
	{
        int count = 0; 
        for (int i = 0; i < header.length; i++)
        {
        	//If the slot holds a value of 0 then increment count
            if (!isSlotUsed(i)) {
                count++;
            }
        }
        return count;
    }
	
	public boolean isSlotUsed(int i) 
	{
	    if (header[i] == 1)
	    {
	    	return true;
	    }
	    return false;
	}
	
	private int getNumOfRecords() 
	{        
    	return (int) Math.floor((PAGE_SIZE) / (recordSize));
    }
	
	public int getPageId() 
	{
	    return pageID;
	}
	
	public void setPageId(int pid) 
	{
		pageID = pid;
	}
	
	//Function that converts each field of the record into bytes
	public void convertRecordToBytes(DataOutputStream dos, Record records[], int i) throws Exception
	{
		int hourlyCounts = records[i].hourlyCounts;
        int id = records[i].id;
		int pageId = records[i].getRecordId().getPageId();
    	int slotNum = records[i].getRecordId().getSlotNum();
        String dateTime = records[i].dateTime; 
        int year = records[i].year; 
        String month = records[i].month;
		int mDate = records[i].mDate; 
		String day = records[i].day; 
		int time = records[i].time; 
		int sensorID = records[i].sensorID; 
		String sensorName = records[i].sensorName; 
		
		dos.writeInt(hourlyCounts);
		dos.writeInt(id);
		dos.writeInt(pageId);
		dos.writeByte(slotNum);
		dos.writeUTF(dateTime);
		dos.writeInt(year);
		dos.writeUTF(month);
		dos.writeByte(mDate);
		dos.writeUTF(day);
		dos.writeByte(time);
		dos.writeByte(sensorID);
		dos.writeUTF(sensorName);
	}
	
}
