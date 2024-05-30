import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pair<Integer, String>> list = new ArrayList<>();
        list.add(new Pair<>(1, "One"));
        list.add(new Pair<>(2, "Two"));
        list.add(new Pair<>(3, "Three"));
        list.add(new Pair<>(4, "Four"));
        list.add(new Pair<>(5, "Five"));
        list.add(new Pair<>(6, "Six"));
        list.add(new Pair<>(7, "Seven"));
        list.add(new Pair<>(8, "Eight"));
        list.add(new Pair<>(9, "Nine"));
        list.add(new Pair<>(10, "Ten"));

        for (Pair<Integer, String> pair : list) {
            System.out.println(pair.toString());
        }
    }
}
