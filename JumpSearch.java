package phonebook;

public class JumpSearch {

    static int counter = 0;


    public static void performJumpSearch(String[] directory, String[] find, long timeToLoadFiles) throws InterruptedException {
        System.out.println("Start searching (bubble sort + jump search)...");
        long bubbleStart = System.currentTimeMillis();
        String[] sortedDirectory = bubbleSort(directory);

        long bubbleStop = System.currentTimeMillis() - bubbleStart;

        long jumpStart = System.currentTimeMillis();
        for (String name : find) {
            if(jumpSearch(sortedDirectory,name) != -1) {
                counter++;
            }
        }
        long jumpStop = System.currentTimeMillis();

        long END = System.currentTimeMillis() + timeToLoadFiles;
        long timeElapsed = END - bubbleStart;
        long minutesAll = (timeElapsed / 1000) / 60;
        long secondsAll = (timeElapsed / 1000) % 60;
        long millisAll = timeElapsed - secondsAll * 1000;

        System.out.printf("Found %d / %d entries. ", counter, find.length);
        System.out.printf("Time taken: %2d min. %2d sec. %2d ms.\n", minutesAll, secondsAll,millisAll);

        long bubbleTime = bubbleStop - bubbleStart;
        long minutesBubble = (bubbleTime / 1000) / 60;
        long secondsBubble = (bubbleTime / 1000) % 60;
        long millisBubble = bubbleTime - secondsBubble * 1000;

        System.out.printf("Sorting time: %2d min. %2d sec. %2d ms.\n", minutesBubble, secondsBubble, millisBubble);

        long jumpTime = jumpStop - jumpStart;
        long minutesJump = (jumpTime / 1000) / 60;
        long secondsJump = (jumpTime / 1000) % 60;
        long millisJump = jumpTime - secondsJump * 1000;

        System.out.printf("Searching time: %2d min. %2d sec. %2d ms.", minutesJump, secondsJump, millisJump);

    }

    public static int jumpSearch(String[] directory, String target) {

        int currentRight = 0;
        int prevRight = 0;

        if (directory.length == 0) {
            return -1;
        }

        if (directory[currentRight].equals(target)) {
            return 0;
        }
        int jumpLength = (int) Math.sqrt(directory.length);

        while (currentRight < directory.length - 1) {
            currentRight = Math.min(directory.length - 1, currentRight + jumpLength);
            if (directory[currentRight].compareTo(target) >= 0) {
                break;
            }
            prevRight = currentRight;
        }

        return backwardSearch(directory, target, prevRight, currentRight);
    }

    public static String[] bubbleSort(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) < 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static int backwardSearch(String[] array, String target, int leftExcl, int rightIncl) {
        int index = -1;
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return index;
    }
}
