public class Prac {

    public static void main(String[] args) {
        int height = 5;

        // Print lower part of the tree
        for (int i = 1; i <= height; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
