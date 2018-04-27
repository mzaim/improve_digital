package digital;

public class Main {
    CustomFileReader reader1;
    CustomFileReader reader2;

    public void execute(){
        Thread t1 = new Thread(reader1, "file1_reader");
        Thread t2 = new Thread(reader2, "file2_reader");
        t1.start();
        t2.start();
    }

    public static void main(String args[]){



    }


}
