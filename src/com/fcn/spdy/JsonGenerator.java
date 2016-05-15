package com.fcn.spdy;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class JsonGenerator {

	public static String convert(Object ob, String filePath){


		Gson gson = new Gson();

			// convert java object to JSON format,
			// and returned as JSON formatted string
			String json = gson.toJson(ob);

			try {
				//write converted json data to a file named "file.json"
				FileWriter writer = new FileWriter(filePath + "file.json");
				writer.write(json);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(json);
			
			return json;
		    
	}
	public static void convert(String ob, String filePath){




			try {
				//write converted json data to a file named "file.json"
				FileWriter writer = new FileWriter(filePath + "file.json");
				writer.write(ob);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(ob);
			

		    
	}

}
