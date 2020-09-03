package phonebook;

public class LinearSearch {

    static int counter = 0;

    public static long linearSearch(String[] directory, String[] find, long timeToLoadFiles) {
        long START = System.currentTimeMillis();
        System.out.println("Start searching (linear search)...");
        for (String entry : directory) {
            for (String name : find) {
                if (name.equals(entry)) {
                    counter++;
                    break;
                }
            }
        }
        long END = System.currentTimeMillis() + timeToLoadFiles;
        long timeElapsed = END - START;
        long minutes = (timeElapsed / 1000) / 60;
        long seconds = (timeElapsed / 1000) % 60;
        long millis = timeElapsed - seconds * 1000;

        System.out.printf("Found %d / %d entries. ", counter, find.length);
        System.out.printf("Time taken: %2d min. %2d sec. %2d ms.\n", minutes, seconds, millis);

        return timeElapsed;
    }
}
