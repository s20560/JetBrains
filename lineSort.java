package sorting;

import java.util.*;

public class lineSort {
    static String[] listToSort = readLines();

    public static void sortLine() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        for (String line : listToSort) {
            int counter = 0;
            for (int i = 0; i < listToSort.length; i++) {
                if (listToSort[i].equals(line)) {
                    counter++;
                }
                map.put(line,counter);
            }
        }
        Map<String, Integer> sortedMap = sortByValue(map);

        System.out.printf("Total lines: %d.\n", listToSort.length);
        for(var entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times(s), " + entry.getValue()*100/listToSort.length + "%");
        }
    }

    public static void sortLineMerge() {
        System.out.println("Total lines: " + listToSort.length);
        System.out.println("Sorted data: ");
        for (int i = 0; i < listToSort.length ; i++) {
            System.out.println(listToSort[i]);
        }
    }

    private static void merge(String[] array, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        String[] temp = new String[right - left];

        while (i < middle && j < right) {
            if (array[i].compareTo(array[j]) < 0) {
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

    private static void mergeSort(String[] array, int leftIncl, int rightExclusive) {
        if (rightExclusive <= leftIncl + 1) {
            return;
        }
        int middle = leftIncl + (rightExclusive - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);
        mergeSort(array, middle, rightExclusive);

        merge(array,leftIncl,middle,rightExclusive);
    }

    private static String[] readLines() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        String[] listToSort = new String[lines.size()];
        for(int i = 0; i < lines.size(); i++) {
            listToSort[i] = lines.get(i);
        }
        mergeSort(listToSort,0,listToSort.length);
        return listToSort;
    }
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}