package tree;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Record implements java.io.Serializable {
	
	int id, year, mDate, time, sensorID, hourlyCounts;
	String dateTime, month, day, sensorName;
	
	private RecordId m_rid;
	
	public Record(int id, String dateTime, int year, String month, 
			int mDate, String day, int time, int sensorID, String sensorName, int hourlyCounts)
	{
		this.id = id;
		this.year = year;
		this.mDate = mDate;
		this.time = time;
		this.sensorID = sensorID;
		this.hourlyCounts = hourlyCounts;
		this.dateTime = dateTime;
		this.month = month;
		this.day = day;
		this.sensorName = sensorName;
	}	
	 
	public RecordId getRecordId() 
	{
	    return m_rid;
	}
	
	public void setRecordId(RecordId rid) 
	{
		m_rid = rid;
	}
}
