
package pl.edu.pw.ee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;


public class HashOpenAdressingPerfomanceTest {
    
    public HashOpenAdressingPerfomanceTest() {
    }
        HashTable<String> table;
	List<String> wordsList;

	@Before
	public void wordsFromFile() {
		try {
			wordsList = new ArrayList<>(100000);
			wordsList = Files.readAllLines(Paths.get("src//test//java//pl//edu//pw//ee//words.txt"));

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void timeDlaDanychLiczb() {
		ArrayList<Integer> liczby = new ArrayList<>(7);
		liczby.add(4096);
		liczby.add(8192);
		liczby.add(16384);
		liczby.add(32768);
		liczby.add(65536);
		liczby.add(131072);
		liczby.add(262144);

		System.out.println("Czas dla liczb podanych w tabeli: ");
		for (Integer liczba : liczby) {
			System.out.println(liczba + " " + measureTime(liczba));
		}
		System.out.println();
	}
        
        private double measureTimeDlaRozmiaru(int size) {
		long sum = 0;
		table = new HashLinearProbing<>(size);

		for (String word : wordsList) {
			table.put(word);
		}
		long start = System.currentTimeMillis();

		for (String word : wordsList) {
			table.get(word);
		}
		long end = System.currentTimeMillis();
		sum += (end - start);
		return sum;
	}

	private double measureTime(int size) {
		Double finalTime = 0.0;
		ArrayList<Double> measuredTime = new ArrayList<>(30);
		for (int i = 0; i < 30; i++) {
			Double time = measureTimeDlaRozmiaru(size);
			measuredTime.add(time);
		}

		Collections.sort(measuredTime);
		int i = 10;
		while (i < 20) {
			finalTime += measuredTime.get(i);
			i++;
		}
		finalTime /= 10;
		return finalTime;

	}
    

    public class HashOpenAdressingImpl extends HashOpenAdressing {

        public int hashFunc(int key, int i) {
            return 0;
        }
    }
    
}
