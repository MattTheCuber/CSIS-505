public class Main {
    public static void main(String[] args) {
        IndexedList indexedList = new IndexedList();
        indexedList.insert("Alice");
        indexedList.insert("Bob");
        indexedList.insert("Alex");
        indexedList.insert("Brenda");
        indexedList.insert("Chris");
        indexedList.insert("Carol");
        indexedList.insert("David");
        indexedList.insert("Emily");

        indexedList.print();
        System.out.println();

        String[] searches = { "Alex", "Carol", "Emily", "Charles", "charles", "CHARLES", "C", "", null };
        for (String search : searches) {
            String result = indexedList.searchVine(search);
            System.out.println("Search result for '" + search + "': " + result);
        }
    }
}
