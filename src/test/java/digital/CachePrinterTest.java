package digital;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class CachePrinterTest {

    static Cache cache = Cache.getInstance();
    CachePrinter printer = new CachePrinter();

    @BeforeAll
    public static void init(){
        cache.clear();
        cache.put("word1", FilePaths.FILE1);
        cache.put("word2", FilePaths.FILE1);
        cache.put("word3", FilePaths.FILE1);
        cache.put("word3", FilePaths.FILE1);
        cache.put("word3", FilePaths.FILE1);
        cache.put("word4", FilePaths.FILE2);
        cache.put("word5", FilePaths.FILE2);
        cache.put("word1", FilePaths.FILE2);
        cache.put("word1", FilePaths.FILE2);
        cache.put("word2", FilePaths.FILE2);
    }

    /**
     * just for checking console output
     */
    @Test
    public void testRun(){
        printer.run();
    }
}
