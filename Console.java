package sorting;

public class Console {

    public void OpenConsole(String dataType, String sortType) {
        switch (dataType + sortType) {
            case "longnatural":
                longSorting.sortLongMerge();
                break;

            case "longbyCount":
                longSorting.sortLong();
                break;

            case "wordnatural":
                wordSort.sortWordMerge();
                break;

            case "wordbyCount":
                wordSort.sortWord();
                break;

            case "linenatural":
                lineSort.sortLineMerge();
                break;

            case "linebyCount":
                lineSort.sortLine();
                break;

            default:
                System.out.println("Not a valid parameter. It's skipped.");
        }

    }
}