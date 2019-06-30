package main;

import java.io.FileWriter;
import java.io.IOException;

public class Write {
	
	FileWriter writer = null;
	
	public Write(FileWriter write) {
		this.writer = write;
	}
	
	public void writeJS(String text) throws IOException {
		if(!text.isEmpty())
			this.writer.write(text + "\n");
	}
	
	public void close() throws IOException {
	       this.writer.close();
	}
}
