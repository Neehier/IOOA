package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class TextFileUtilities {
	
	public static ArrayList<String> leesRuweTekst(String locatie) {
		ArrayList<String> regels = new ArrayList<String>();
		try {
			FileReader freader = new FileReader(locatie);
			BufferedReader bufreader = new BufferedReader(freader);
			
			String regel = bufreader.readLine();
			while(regel != null) {
				regel = bufreader.readLine();
				if(regel != null) {
					regels.add(regel);
				}
			}
			freader.close();
			bufreader.close();
		}
		
		catch(FileNotFoundException fnfe) {
			System.out.println("Exception opgetreden: " + fnfe.toString());
			System.out.println("Stack trace: "); fnfe.printStackTrace();
		}
		catch(IOException ioe) {
			System.out.println("Exception opgetreden: " + ioe.toString());
			System.out.println("Stack trace: "); ioe.printStackTrace();
		}
		return regels;
	}
	
	public static HashMap<String, Integer> printTekst(ArrayList<String> tekst, HashMap<String, Integer> wordcount) {
		for(String regel : tekst) {
			String[] woorden = regel.split(" ");
			for(String wbuf : woorden) {
				String w = opSchonenWoord(wbuf);
				if(wordcount.get(w) == null) {
					wordcount.put(w, 1); }
				else {
					wordcount.put(w, wordcount.get(w) + 1);
				}
				System.out.println(w + ": " + wordcount.get(w));
			}
		}
		
		return wordcount;
	}
	
	public static String opSchonenWoord(String woord) {
		String woordBuf = woord.replace(".","").replace(",", "").replace("?", "").replace("!", "").replace("<","").replace("_", "").replace(";","").replace("'", "").replace("*", "").replace(">", "").replace("(", "").replace(")","").replace("]","").replace("[","").replace("-","").replace(":", "").replace("\"", "").replace("ã³", "").replace(" ", "");
		String woordBuff = woordBuf.toLowerCase();
		return woordBuff;
	}
	
	public static void schrijfWoordFrequentieNaarFile(HashMap<String, Integer> wordcount, String locatie) {
		for(String woord : wordcount.keySet()) {
			String w = woord;
			int count = wordcount.get(w);
			try {
				File myFile = new File(locatie);
				FileWriter fileWriter = new FileWriter(myFile, true);
				PrintWriter printWriter = new PrintWriter(fileWriter);
				
				printWriter.print("Woord: " + woord + "\t\t |  Teller: " + count + "\n");
				fileWriter.close();
				printWriter.close();
			}
			catch(IOException ioe) {
				System.out.println("Exception opgetreden: " + ioe.toString());
				System.out.println("Stack trace: ");ioe.printStackTrace();
			}
		}
	}
}

