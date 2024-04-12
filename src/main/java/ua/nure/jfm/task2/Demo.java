package ua.nure.jfm.task2;

public class Demo {
    public static void main(String[] args) {
        Array<Integer> arr = new ArrayImpl<>();
        arr.add(1);
        arr.add(2);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(9);
        arr.add(15);
        arr.add(3);

        System.out.printf("Array: %s%n", arr);
        System.out.printf("Index of 9: %d%n", arr.indexOf(9));
        System.out.printf("Index of 4: %d%n", arr.indexOf(4));
        arr.remove(4);
        System.out.printf("Array (removed element at index 4): %s%n", arr);
        arr.set(5, 11);
        System.out.printf("Array (set 11 at index 5): %s%n", arr);
        System.out.printf("Element at index 6: %d%n", arr.get(6));
        System.out.printf("Count of elements divisible by 2: %d%n", arr.stream().filter(el -> el % 2 == 0).count());
    }
}
