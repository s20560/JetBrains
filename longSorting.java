package sorting;

import java.util.*;

public class longSorting {
    static long[] listToSort = readNumbers();

    public static void sortLong() {
        LinkedHashMap<Long, Integer> map = new LinkedHashMap<>();
        for(long number : listToSort) {
            int counter = 0;
            for (int i = 0; i < listToSort.length; i++) {
                if (listToSort[i] == number) {
                    counter++;
                }
            }
            map.put(number,counter);
        }
        Map<Long, Integer> sortedMap = sortByValue(map);
        System.out.printf("Total numbers: %d.\n", listToSort.length);
        for(var entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times(s), " + Math.round((double)entry.getValue()*100/listToSort.length) + "%");
        }
    }

    public static void sortLongMerge() {
        System.out.println("Total numbers: " + listToSort.length);
        System.out.print("Sorted data: ");
        for (int i = 0; i < listToSort.length ; i++) {
            System.out.print(listToSort[i] + " ");
        }
    }

    private static long[] readNumbers() {
        ArrayList<Long> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (scanner.hasNextLong()){
            try {
                long number = scanner.nextLong();
                input = String.valueOf(number);
                numbers.add(number);
            } catch (InputMismatchException e) {
                System.out.println("" + input + " is not a long. It's skipped.");
            }
        }
        long[] listToSort = new long[numbers.size()];
        for(int i = 0; i < numbers.size(); i++) {
            listToSort[i] = numbers.get(i);
        }
        mergeSort(listToSort,0,listToSort.length);
        return listToSort;
    }

    private static void merge(long[] array, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        long[] temp = new long[right - left];

        while (i < middle && j < right) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }
        while (i < middle) {
            temp[k] = array[i];
            i++;
            k++;
        }
        while (j < right) {
            temp[k] = array[j];
            j++;
            k++;
        }
        System.arraycopy(temp, 0, array, left, temp.length);
    }

    private static void mergeSort(long[] array, int leftIncl, int rightExclusive) {
        if (rightExclusive <= leftIncl + 1) {
            return;
        }
        int middle = leftIncl + (rightExclusive - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);
        mergeSort(array, middle, rightExclusive);

        merge(array,leftIncl,middle,rightExclusive);
    }

    private static Map<Long, Integer> sortByValue(LinkedHashMap<Long, Integer> unsortMap) {

        List<Map.Entry<Long, Integer>> list = new LinkedList<Map.Entry<Long, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
            public int compare(Map.Entry<Long, Integer> o1,
                               Map.Entry<Long, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
        for (Map.Entry<Long, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}