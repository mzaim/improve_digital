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

    public CustomFileReader(String fileAddr){
        this.fileAddr = fileAddr;
        this.setName(fileAddr);
    }

    /**
     * assume words are separated by spaces, ignore punctuation
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
