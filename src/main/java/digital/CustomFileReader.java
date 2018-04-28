package digital;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CustomFileReader extends Thread {

    private String fileAddr;
    private Cache cache = Cache.getInstance();

    /**
     * for easier manipulation we will name the thread after the file that is read
     * this will be used inside the cache as well
     * @param fileAddr
     */
    public CustomFileReader(String fileAddr){
        this.fileAddr = fileAddr;
        this.setName(fileAddr);
    }

    /**
     * Reads file line by line and parses the individual words.
     * Assume words are separated by spaces, ignore punctuation.
     * Each word is then added to the cache, specifying the file it came from
     */
    public void run() {
        Path path = Paths.get(fileAddr);
        try {
            Files.lines(path)
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .forEach(word -> cache.put(word, this.getName()));
        }catch(IOException ioe){
            ioe.printStackTrace();
            throw new UncheckedIOException(ioe);
        }
    }

}
