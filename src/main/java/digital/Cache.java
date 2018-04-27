/**
 * singleton used for caching
 */
package digital;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Cache instance;
    /**
     * word -> thread/file name, occurences
     */
    private static Map<String, Map<String, Integer>> content;

    /**
     * make the constructor private so no one can instantiate the class
     */
    private Cache(){
        content = new HashMap<String, Map<String, Integer>>();
    }

    public static synchronized Cache getInstance(){
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    public static synchronized void put(String word, String file){
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
        return new HashMap<String, Map<String, Integer>>(content);
    }
}
