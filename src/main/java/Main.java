import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static List<Integer> numbersFromFile = new ArrayList<>();
    public static void main(String[] args) {

        // считываем данные
        readData(args);

        // сортируем
        int[] sortedArray = numbersFromFile.stream().mapToInt(i -> i).toArray();
        MergeSorting.mergeSorting(sortedArray, numbersFromFile.size());
        System.out.println(Arrays.toString(sortedArray));
    }

    public static void readData(String[] args) {
        String line;
        for (int i = 0; i < args.length; i++) {
            try (BufferedReader readerOfFiles = new BufferedReader(new FileReader(args[i]))) {
                while((line = readerOfFiles.readLine()) != null) {
                    // если строка - преобразуем в число
                    numbersFromFile.add(Integer.parseInt(line));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
