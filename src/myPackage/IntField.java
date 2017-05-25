package myPackage;

import java.io.DataOutputStream;
import java.io.IOException;

public class IntField {
	
	private final int value;
	
	public IntField(int i) {
        value = i;
    }

    public String toString() {
        return Integer.toString(value);
    }
	
    public int getValue() {
        return value;
    }
	
	public void serialize(DataOutputStream dos) throws IOException {
        dos.writeInt(value);
    }
}
