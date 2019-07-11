package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

public class Write {
	
	FileWriter writer = null;
	JSONArray array = null;
	JSONObject object = null;
	
	public Write(String path) throws IOException {
		this.writer = new FileWriter(new File( path +"Coleta.json"));
		this.array = new JSONArray();
	}
	
	@SuppressWarnings({ "unchecked" })
	public void addObject(int position, WebElement target, String type, List<?> mutations) {
		this.object = new JSONObject();
		this.object.put("Elemento", position);
		this.object.put("Ação", type);
		this.object.put("Heigth", target.getSize().getHeight());
		this.object.put("Width", target.getSize().getWidth());
		//this.object.put("Texto", target.getText());
		this.object.put("Localização x", target.getLocation().getX());
		this.object.put("Localização y", target.getLocation().getY());
		this.object.put("Info mutation", mutations);
		this.array.add(this.object);
	}
	
	public void WriteJSON() throws IOException {
		this.writer.write(this.array.toJSONString());
	}
	
	public void close() throws IOException {
	       this.writer.close();
	}
}
