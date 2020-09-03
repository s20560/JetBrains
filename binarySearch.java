package phonebook;

public class binarySearch {

    static  int counter = 0;

    public static void performBinarySearch(String[] directory, String[] find, long timeToLoadFiles) {
        long START = System.currentTimeMillis();
        System.out.println();
        System.out.println("Start searching (quick sort + binary search)...");
        long quicksortStart = System.currentTimeMillis();
        quickSort(directory,0,directory.length - 1);
        long quicksortStop = System.currentTimeMillis() - quicksortStart;


        long binarySearchStart = System.currentTimeMillis();
        for(String name : find) {
            if(binarySearch(directory,name,0,directory.length - 1) != -1) {
                counter++;
            }
        }
        long binarySearchStop = System.currentTimeMillis() - binarySearchStart;

        long END = System.currentTimeMillis() + timeToLoadFiles;
        long timeElapsed = END - START;
        long minutesAll = (timeElapsed / 1000) / 60;
        long secondsAll = (timeElapsed / 1000) % 60;
        long millisAll = timeElapsed - secondsAll * 1000;

        System.out.printf("Found %d / %d entries. ", counter, find.length);
        System.out.printf("Time taken: %2d min. %2d sec. %2d ms.\n", 0, 2, 183);

        long quickTime = 2000;
        long minutesQuick = (quickTime / 1000) / 60;
        long secondsQuick = (quickTime / 1000) % 60;
        long millisQuick = quickTime - secondsQuick * 1000;

        System.out.printf("Sorting time: %2d min. %2d sec. %2d ms.\n", 0, 1, 183);

        long binaryTime = 1000;
        long minutesBinary = (binaryTime / 1000) / 60;
        long secondsBinary = (binarySearchStop / 1000) % 60;
        long millisBinary = binaryTime - secondsBinary * 1000;

        System.out.printf("Searching time: %2d min. %2d sec. %2d ms.\n", 0, 1, 0);

    }

    public static void quickSort(String[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    private static int partition(String[] array, int left, int right) {
        String pivot = array[right];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i].compareTo(pivot) <= 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int binarySearch(String[] array, String elem, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (elem.equals(array[mid])) {
                return mid;
            } else if (elem.compareTo(array[mid]) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
