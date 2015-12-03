package com.bettingtracker;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author User1
 */
public class Serializer {


	/**
	 * Serializes a Serializable passed to it with the given file name
	 * @param c Object
	 */
	public static void serialize(Serializable c, String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(c);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			//i.printStackTrace();
		}
	}

	/**
	 * Un-serializes the specified file and returns the object
	 * @return Serializable object read from file
	 */
	public static Serializable unserialize(String fileName) throws IOException{
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Serializable c = (Serializable)in.readObject();
			in.close();
			fileIn.close();
			return c;      
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
			return null;
		}
	}
}


