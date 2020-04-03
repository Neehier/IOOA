package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class TextMining {

	public static void main(String[] args) {
		HashMap<String, Integer> word2frequency = new HashMap<String, Integer>();
		String readLocatie = "/Users/nicko/Documents/Assessments IOOA/dik_trom.txt";
		ArrayList<String> regels = TextFileUtilities.leesRuweTekst(readLocatie);
		word2frequency = TextFileUtilities.printTekst(regels, word2frequency);
		String writeLocatie = "/Users/nicko/Documents/Assessments IOOA/wordcount.txt";
		TextFileUtilities.schrijfWoordFrequentieNaarFile(word2frequency, writeLocatie);
	}

}
