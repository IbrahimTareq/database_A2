package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import myPackage.Page;
import tree.HeapPage;
import tree.Record;


public class HeapFile {

    /**
     * Constructs a heap file backed by the specified file.
     * 
     * @param f
     *            the file that stores the on-disk backing store for this heap
     *            file.
     */
    private int id;
    private File file;
    
    public HeapFile(File f) {
        this.file = f;
        this.id = f.getAbsoluteFile().hashCode();
    }

    /**
     * Returns the File backing this HeapFile on disk.
     * 
     * @return the Frile backing this HeapFile on disk.
     */
    public File getFile() {
        // some code goes here
        return this.file;
    }

    /**
     * Returns an ID uniquely identifying this HeapFile. Implementation note:
     * you will need to generate this tableid somewhere ensure that each
     * HeapFile has a "unique id," and that you always return the same value for
     * a particular HeapFile. We suggest hashing the absolute file name of the
     * file underlying the heapfile, i.e. f.getAbsoluteFile().hashCode().
     * 
     * @return an ID uniquely identifying this HeapFile.
     */
    public int getId() {
        // some code goes here
        return this.id;
        //throw new UnsupportedOperationException("implement this");
    }

    
    // see DbFile.java for javadocs
    public void writePage(Page page) throws IOException {

    	RandomAccessFile raf = new RandomAccessFile(this.file, "rw");
    	PageId pid = page.getId();
    	int offset = BufferPool.PAGE_SIZE * pid.pageNumber();
    	raf.seek(offset);
    	raf.write(page.getPageData(), 0, BufferPool.PAGE_SIZE);
    	raf.close();   
    }
    
    // see DbFile.java for javadocs
    public Page readPage(PageId pid) {
        // some code goes here
        try {
            RandomAccessFile f = new RandomAccessFile(this.file,"r");
            int offset = BufferPool.PAGE_SIZE * pid.pageNumber();
            byte[] data = new byte[BufferPool.PAGE_SIZE];
            if (offset + BufferPool.PAGE_SIZE > f.length()) {
                System.err.println("Page offset exceeds max size, error!");
                System.exit(1);
            }
            f.seek(offset);
            f.readFully(data);
            f.close();
            return new HeapPage((HeapPageId) pid, data);
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
            throw new IllegalArgumentException();
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the number of pages in this HeapFile.
     */
    public int numPages() {
        // some code goes here
        return (int) Math.ceil(this.file.length()/BufferPool.PAGE_SIZE);
    }

    private HeapPage getFreePage(TransactionId tid) throws TransactionAbortedException, DbException
    {
    	for (int i = 0; i < this.numPages(); i++)
    	{
    		PageId pid = new HeapPageId(this.getId(), i);
    		HeapPage hpage = (HeapPage) Database.getBufferPool().getPage(tid, pid, Permissions.READ_WRITE);
        	if (hpage.getNumEmptySlots() > 0)
        		return hpage;
    	}
    	return null;
    }
    
    // see DbFile.java for javadocs
    public ArrayList<Record> insertTuple(Record rec)
    {
        // some code goes here
        // ArrayList<Page> modifiedPages = new ArrayList<Page>();
        HeapPage hpage = getFreePage(tid);
        if (hpage != null)
        {
        	hpage.insertTuple(t);
        	return new ArrayList<Page> (Arrays.asList(hpage));
        }
        
        // no empty pages found, so create a new one
        HeapPageId newHeapPageId = new HeapPageId(this.getId(), this.numPages());
        HeapPage newHeapPage = new HeapPage(newHeapPageId, HeapPage.createEmptyPageData());
        newHeapPage.insertTuple(t);
        
        RandomAccessFile raf = new RandomAccessFile(this.file, "rw");
        int offset = BufferPool.PAGE_SIZE * this.numPages();
        raf.seek(offset);
        byte[] newHeapPageData = newHeapPage.getPageData();
        raf.write(newHeapPageData, 0, BufferPool.PAGE_SIZE);
        raf.close();
        
        return new ArrayList<Page> (Arrays.asList(newHeapPage));
    }
	
}
