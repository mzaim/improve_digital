/**
 * singleton used for caching
 */
package digital;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Cache instance;
    /**
     * word -> thread/file name, occurrences
     */
    private static Map<String, Map<String, Integer>> content;

    /**
     * make the constructor private so no one can instantiate the class
     */
    private Cache(){
        content = new HashMap<>();
    }

    /**
     * standard get instance method for singletons
     * @return
     */
    public static synchronized Cache getInstance(){
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    /**
     * adds a word to the cache, retaining the file where it came from
     * also, computes the number of occurrences per file
     * and the total occurrences
     * @param word
     * @param file
     */
    public synchronized void put(String word, String file){
        Map<String, Integer> fileOccurences = content.get(word);
        if(fileOccurences == null){
            fileOccurences = new HashMap<>();
            fileOccurences.put(file, 1);
            content.put(word, fileOccurences);
        }else {
            fileOccurences.merge(file, 1, Integer::sum);
        }
    }

    /**
     * returns a clone of the actual content in order to prevent
     * accidental modification
     */
    public synchronized Map<String, Map<String, Integer>> getContent(){
        return new HashMap<>(content);
    }

    /**
     * used for testing
     */
    void clear(){
        content = new HashMap<>();
    }
}
