package myPackage;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import tree.Record;

public class NewHeap {
	
	   private ArrayList<Record> items;
	     
	    public NewHeap() {
	        items = new ArrayList<Record>();
	    }
	         
	    private void siftUp() {
	        int k = items.size() - 1;
	        while (k > 0) {
	            int p = (k-1)/2;
	            Record item = items.get(k);
	            Record parent = items.get(p);
	            if (item.id > parent.id) {
	                // swap
	                items.set(k, parent);
	                items.set(p, item);
	                 
	                // move up one level
	                k = p;
	            } else {
	                break;
	            }
	        }
	    }
	     
	    public void insert(Record item) {
	        items.add(item);
	        siftUp();
	    }
	     
	    private void siftDown() {
	        int k = 0;
	        int l = 2*k+1;
	        while (l < items.size()) {
	            int max=l, r=l+1;
	            if (r < items.size()) { // there is a right child
	                if ( items.get(r).id > (items.get(l).id) ) {
	                    max++;
	                }
	            }
	            if (items.get(k).id > (items.get(max).id)) {
	                    // switch
	                    Record temp = items.get(k);
	                    items.set(k, items.get(max));
	                    items.set(max, temp);
	                    k = max;
	                    l = 2*k+1;
	            } else {
	                break;
	            }
	        }
	    }
	     
	    public Record delete() 
	    throws NoSuchElementException {
	        if (items.size() == 0) {
	            throw new NoSuchElementException();
	        }
	        if (items.size() == 1) {
	            return items.remove(0);
	        }
	        Record hold = items.get(0);
	        items.set(0, items.remove(items.size()-1));
	        siftDown();
	        return hold;
	    }
	 
	    public int size() {
	        return items.size();
	    }
	     
	    public boolean isEmpty() {
	        return items.isEmpty();
	         
	    }
	     
	    public String toString() {
	        return items.toString();
	    }
}
