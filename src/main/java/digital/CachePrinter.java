package digital;

import java.util.Map;
import java.util.stream.Stream;

public class CachePrinter implements Runnable{
    Cache cache = Cache.getInstance();

    @Override
    public void run() {
        Map<String, Map<String, Integer>> currentCache = cache.getContent();

        //<word> <total occurrences> = <occurrences in file1> + <occurrences in file2>
        currentCache.forEach((key, val) -> {
            Integer occFile1 = getNbr("file1", val);
            Integer occFile2 =  getNbr("file2", val);
            Integer sum = occFile1 + occFile2;
            System.out.print("<" + key + ">");
            System.out.print("<" + sum + "> = ");
            System.out.print("<" + occFile1 + "> + ");
            System.out.println("<" + occFile2 + ">");
        });
    }

    private Integer getNbr(String file, Object val){
        Object possibleNbr = ((Map)val).get(file);
        if(possibleNbr == null){
            return 0;
        }else{
            return new Integer(possibleNbr.toString());
        }
    }
}
