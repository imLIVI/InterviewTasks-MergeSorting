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

        // записываем в выходной файл
        writeSortedData("src/main/resources/out.txt", sortedArray);
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

    public static void writeSortedData(String fileName, int[] sortedArray) {
        String line;
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < sortedArray.length; i++) {
                writerToFile.write(String.valueOf(sortedArray[i] + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
