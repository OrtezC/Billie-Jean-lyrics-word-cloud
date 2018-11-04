import com.kennycason.kumo.WordFrequency;

import java.util.*;
import java.io.*;
public class WordCount {

    public static void main(String[] args) throws IOException{

        File input = new File("lyrics.txt"); //Relative path of the lyrics text file.
        Scanner readFile = new Scanner(input);
        FileWriter filewriter = new FileWriter("WordCount.txt");
        PrintWriter output = new PrintWriter(filewriter);

        HashMap<String,Integer> words = new HashMap(); //Creates a HashMap with String as the key and the Integer being the value (repetitions of each word).

        String lyrics = readFile.useDelimiter("\\A").next(); //Read in our file as a large, single string.

        //Filters through the string, eliminating characters such that only words are left. Note: Contractions (don't) and conjunctions('cause) count as one word.
        lyrics = lyrics.replace(", "," ").replace("\"","").replace("?\"","").replace(",","").replace("(","").replace(")","")
                .replace("!","").replace("' "," ").replace(" '"," ").replace("?","");
        lyrics = lyrics.toLowerCase();

        Scanner readLyrics = new Scanner(lyrics); //Create a scanner instance of the filtered lyrics so that we may process the lyrics into a hashmap.

        //Processes the filtered lyrics into a hashmap with a while loop.
        while(readLyrics.hasNext()) {
            int count = 1;
            String key = readLyrics.next();
            if(words.containsKey(key)) {  //If the hashmap contains the string:
                count = words.get(key);  //Store the number of repetitions
                words.remove(key);  //Remove the old String key
                words.put(key, count + 1);  //Put a new String key with the updated repetitions(+1).
            }
            else words.put(key, count); //Otherwise, it's the first time seeing the word, so we put a count of 1;
        }

        HashMap<String, Integer> sortedwords = sortByValueDescending(words);

        for(HashMap.Entry<String, Integer> entry: sortedwords.entrySet()) {
            output.println(entry.getValue() + ": " +entry.getKey());
            System.out.println(entry.getValue() + ": " +entry.getKey());
        }

        output.close();




    }

    private static HashMap<String, Integer> sortByValueDescending(HashMap<String, Integer> unsorted) {

        List<HashMap.Entry<String, Integer>> list = new LinkedList<HashMap.Entry<String, Integer>>(unsorted.entrySet()); //Inserts unsorted hashmap into unsorted linked list of Map.Entry.

        Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
            public int compare(HashMap.Entry<String, Integer> o2, HashMap.Entry<String, Integer> o1) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();  //LinkedHashMap maintains the order
        for (HashMap.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
