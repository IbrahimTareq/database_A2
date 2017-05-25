package myPackage;

import java.io.DataOutputStream;
import java.io.IOException;

public class StringField {

	private final String value;
	
	public StringField(String s) {
		value = s;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
	
	public void serialize(DataOutputStream dos) throws IOException {
		String s = value;
		dos.writeBytes(s);
	}
}
