package digital;

public class Main {

    public static void main(String args[]){
        //start 2 threads, each reading its own file
        CustomFileReader reader1 = new CustomFileReader(FilePaths.FILE1);
        CustomFileReader reader2 = new CustomFileReader(FilePaths.FILE2);
        reader1.start();
        reader2.start();

        //wait for both threads to finish before starting to display the cache
        try {
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //start thread that displays the cache
        CachePrinter printer = new CachePrinter();
        printer.start();
    }


}
