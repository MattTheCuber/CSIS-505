import java.util.ArrayList;

public class IndexedList {
    private ArrayList<List<String>> indexedLists = new ArrayList<>(26);

    public IndexedList() {
        for (int i = 0; i < 26; i++) {
            String letter = "list " + (char) ('A' + i);
            List<String> newList = new List<>(letter);
            indexedLists.add(newList);
        }
    }

    private int characterIndex(char c) {
        // Later letters are have higher ASCII values.
        // For example, 'D' is 68 and 'A' is 65. So, 'D' should be at index 68 - 65 = 3.
        return Character.toUpperCase(c) - 'A';
    }

    public void insert(String name) {
        if (name == null || name.isEmpty()) {
            return;
        }
        int index = characterIndex(name.charAt(0));
        indexedLists.get(index).insertAtBack(name);
    }

    public void print() {
        for (List<String> list : indexedLists) {
            if (!list.isEmpty()) {
                list.print();
            }
        }
    }

    public String searchVine(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        int index = characterIndex(name.charAt(0));
        List<String> list = indexedLists.get(index);
        return list.searchVine(name);
    }
}
