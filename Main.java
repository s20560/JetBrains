package phonebook;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        String[] find = ReadFiles.ReadFind();
        String[] directory = ReadFiles.ReadDirectory();
        long timeToLoad = System.currentTimeMillis() - start;

        long linearStart = System.currentTimeMillis();
        LinearSearch.linearSearch(directory, find, timeToLoad);
        long linearStop = System.currentTimeMillis() - linearStart;
        System.out.println();

        long jumpStart = System.currentTimeMillis();
        JumpSearch.performJumpSearch(directory,find,timeToLoad - linearStop);
        long jumpStop = System.currentTimeMillis() - jumpStart;

        binarySearch.performBinarySearch(directory,find,timeToLoad);

    }
}