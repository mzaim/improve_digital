package digital;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.reverseOrder;

public class CachePrinter extends Thread{
    Cache cache = Cache.getInstance();

    @Override
    public void run() {
        Map<String, Map<String, Integer>> currentCache = cache.getContent();
       /* currentCache.forEach((key, val) -> {
            Integer occFile1 = getNbr(FilePaths.FILE1, val);
            Integer occFile2 =  getNbr(FilePaths.FILE2, val);
            Integer sum = occFile1 + occFile2;
            System.out.print("<" + key + ">");
            System.out.print("<" + sum + "> = ");
            System.out.print("<" + occFile1 + "> + ");
            System.out.println("<" + occFile2 + ">");
        });*/

        currentCache.entrySet()
                .stream()
                .map(entry -> valuesArray(entry))
                .sorted((x, y) -> Integer.compare(getValForOrder(y), getValForOrder(x))) //reverse x and y because I can't use Comparator.reverseOrder
                .forEach(value -> {
                    System.out.print("<" + value[0] + ">");
                    System.out.print("<" + value[1] + "> = ");
                    System.out.print("<" + value[2] + "> + ");
                    System.out.println("<" + value[3] + ">");
                });
    }

    private Integer getOccurence(String file, Map<String, Integer> val){
        Optional<Integer> possibleNbr = Optional.ofNullable(val.get(file));
        return possibleNbr.orElse(0);
    }

    private Integer getValForOrder(Object[] value){
        return Integer.parseInt(value[1].toString());
    }

    private Object[] valuesArray(Map.Entry<String, Map<String, Integer>> cacheEntry){
        Integer occFile1 = getOccurence(FilePaths.FILE1, cacheEntry.getValue());
        Integer occFile2 =  getOccurence(FilePaths.FILE2, cacheEntry.getValue());
        Integer sum = occFile1 + occFile2;

        Object[] values = new Object[4];
        values[0] = cacheEntry.getKey();
        values[1] = sum;
        values[2] = occFile1;
        values[3] = occFile2;

        return values;
    }
}
