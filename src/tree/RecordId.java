package tree;

public class RecordId {
	
	private int pageId;
    private int slotNum;

    public RecordId(int pageId, int slotNum) {
        this.pageId = pageId;
        this.slotNum = slotNum;
    }
    
    public int getSlotNum() {
        return slotNum;
    }

    public int getPageId() {
        return pageId;
    }
	
}
