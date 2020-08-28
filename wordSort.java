package sorting;

import java.util.*;

public class wordSort {
    static String[] listToSort = readWords();
    static StringBuilder sb = new StringBuilder();

    public static void sortWord() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        for (String word : listToSort) {
            int counter = 0;
            for (int i = 0; i < listToSort.length; i++) {
                if (listToSort[i].equals(word)) {
                    counter++;
                }
                map.put(word,counter);
            }
        }
        Map<String, Integer> sortedMap = sortByValue(map);

        System.out.printf("Total words: %d.\n", listToSort.length);
        for(var entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times(s), " + entry.getValue()*100/listToSort.length + "%");
        }
    }

    public static void sortWordMerge() {
        System.out.println("Total words: " + listToSort.length);
        System.out.print("Sorted data: ");
        for (int i = 0; i < listToSort.length ; i++) {
            System.out.print(listToSort[i] + " ");
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

    private static String[] readWords() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        for (String line : lines) {
            String[] wordsInLine = line.split("\\s+|\\r\\n");
            words.addAll(Arrays.asList(wordsInLine));
        }
        String[] listToSort = new String[words.size()];
        for(int i = 0; i < words.size(); i++) {
            listToSort[i] = words.get(i);
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