package tree;

public class Tuple {
	
	private byte[] record;
	private RecordId rid;
	
	public Tuple(byte[] record, RecordId rid)
	{
		this.record = record;
		this.rid = rid;
	}
	
	public byte[] getRecord() 
	{
	    return record;
	}
	
	public RecordId getRecordId() 
	{
	    return rid;
	}
	
}
