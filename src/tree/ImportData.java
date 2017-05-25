package tree;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;



public class ImportData {
	
	String csvFile;
	
	public ImportData(String csvFile)
	{
		this.csvFile = csvFile;
	}
	
	public ArrayList<Record> importRecords()
	{
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Record> listOfNormalRecords = new ArrayList<>();
                
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	//Use comma as a separator
                String[] data = line.split(cvsSplitBy);
                
                int id = Integer.parseInt(data[0]);
                String dateTime = data[1]; 
                int year = Integer.parseInt(data[2]);
                String month = data[3];
    			int mDate = Integer.parseInt(data[4]);
    			String day = data[5];
    			int time = Integer.parseInt(data[6]);
    			int sensorID = Integer.parseInt(data[7]); 
    			String sensorName = data[8];
    			int hourlyCounts = Integer.parseInt(data[9]);
    			
                Record obj = new Record(id, dateTime, year, month, mDate, day, time, sensorID, sensorName, hourlyCounts);
                
                listOfNormalRecords.add(obj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return listOfNormalRecords;
	}
	
	public ArrayList<byte[]> importRecordsInBytes()
	{
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ArrayList<byte[]> listOfRecords = new ArrayList<byte[]>();
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) 
            {
                //Use comma as a separator
                String[] data = line.split(cvsSplitBy);
                
                int id = Integer.parseInt(data[0]);
                String dateTime = data[1]; 
                int year = Integer.parseInt(data[2]);
                String month = data[3];
    			int mDate = Integer.parseInt(data[4]);
    			String day = data[5];
    			int time = Integer.parseInt(data[6]);
    			int sensorID = Integer.parseInt(data[7]); 
    			String sensorName = data[8];
    			int hourlyCounts = Integer.parseInt(data[9]);
    			
                Record obj = new Record(id, dateTime, year, month, mDate, day, time, sensorID, sensorName, hourlyCounts);
                //System.out.println("ID: "+obj.id + "Sensor Name: "+sensorName);
                
                ObjectOutput out = new ObjectOutputStream(bos);   
                out.writeObject(obj);
                out.flush();
                byte[] recordsInBytes = bos.toByteArray();
                listOfRecords.add(recordsInBytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return listOfRecords;
	}
	
}
