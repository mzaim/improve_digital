package digital;

import java.util.Map;
import java.util.Optional;

public class CachePrinter extends Thread{
    Cache cache = Cache.getInstance();

    /**
     * retrieves the cache content,
     * orders it by total occurrences
     * and prints it to standard output
     */
    @Override
    public void run() {
        Map<String, Map<String, Integer>> currentCache = cache.getContent();
        currentCache.entrySet()
                .stream()       //turn into stream to be able to process each entry
                .map(entry -> valuesArray(entry)) // turn each entry into a size 4 array
                .sorted((x, y) -> Integer.compare(getValForOrder(y), getValForOrder(x))) //sort by total occurrences descending (reverse x and y because I can't use Comparator.reverseOrder)
                .forEach(value -> {  //begin loop for display
                    System.out.print("<" + value[0] + ">");
                    System.out.print("<" + value[1] + "> = ");
                    System.out.print("<" + value[2] + "> + ");
                    System.out.println("<" + value[3] + ">");
                });
    }

    /**
     * splits the cache entry into a size 4 array for easier manipulation
     * pos 0 = cache key
     * pos 1 = total number of occurrences
     * pos 2 = nbr of occurrences in file1
     * pos 3 = nbr of occurrences in file2
     * @param cacheEntry
     * @return
     */
    private Object[] valuesArray(Map.Entry<String, Map<String, Integer>> cacheEntry){
        Integer occFile1 = getOccurrence(FilePaths.FILE1, cacheEntry.getValue());
        Integer occFile2 =  getOccurrence(FilePaths.FILE2, cacheEntry.getValue());
        Integer sum = occFile1 + occFile2;

        Object[] values = new Object[4];
        values[0] = cacheEntry.getKey();
        values[1] = sum;
        values[2] = occFile1;
        values[3] = occFile2;

        return values;
    }

    /**
     * parse the number of occurrences from the cache
     * in case the file did not contain the word and we have a null entry, 0 is returned
     * @param file
     * @param val
     * @return
     */
    private Integer getOccurrence(String file, Map<String, Integer> val){
        Optional<Integer> possibleNbr = Optional.ofNullable(val.get(file));
        return possibleNbr.orElse(0);
    }

    /**
     * returns total number of occurrences from the array structure
     * @param value
     * @return
     */
    private Integer getValForOrder(Object[] value){
        return Integer.parseInt(value[1].toString());
    }
}
