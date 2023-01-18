import exceptions.InvalidFileNameException;
import exceptions.SpaceInStringException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Integer> numbersFromFile = new ArrayList<>();
    public static String sortingFlag = "";
    public static String dataType;
    public static String nameOutputFile;

    public static void main(String[] args) {

        // считываем данные
        parsingInputString(args);

        // сортируем
        int[] sortedArray = numbersFromFile.stream().mapToInt(i -> i).toArray();
        MergeSorting mergeSorting = new MergeSorting(sortingFlag);
        mergeSorting.mergeSorting(sortedArray, sortedArray.length);

        // записываем в выходной файл
        writeSortedData(nameOutputFile, sortedArray);
    }

    public static void parsingInputString(String[] args) {
        int counter = 1;
        if ((args[counter].equals("-a") || args[counter].equals("-d")) && counter == 1) {
            sortingFlag = args[counter++];
        }
        dataType = args[counter++];
        nameOutputFile = args[counter++];

        checkFileName(nameOutputFile);

        for (int i = counter; i < args.length; i++) {
            readData(args[i]);
        }
    }

    // CHECK: INVALID FILE NAME [*.txt]
    public static void checkFileName(String fileName) {
        try {
            if (!fileName.matches("[0-9a-z_]+\\.txt$"))
                throw new InvalidFileNameException(fileName);
        } catch (InvalidFileNameException e) {
        }
    }

    public static void readData(String nameInputFile) {
        checkFileName(nameInputFile);
        try (BufferedReader readerOfFiles = new BufferedReader(new FileReader(nameInputFile))) {
            // CHECK: INVALID DATA TYPE
            if (dataType.equals("-s") || dataType.equals("-i")) {
                String line;
                int counterLine = 0;
                while ((line = readerOfFiles.readLine()) != null) {
                    counterLine++;
                    // CHECK: SPACE IN THE LINE
                    if (line.matches("[\\d]")) {
                        numbersFromFile.add(Integer.parseInt(line));
                    } else {
                        try {
                            throw new SpaceInStringException(nameInputFile, counterLine);
                        } catch (SpaceInStringException e) {
                        }
                    }
                }
            } else
                System.out.println("[WARNING] Invalid data type");
        } catch (FileNotFoundException e) {
            // CHECK: INPUT FILE NOT FOUND
            System.out.println("[ERROR] The input file: " + nameInputFile + " wasn`t found. " +
                    "Please check the path and run the program again");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeSortedData(String fileName, int[] sortedArray) {
        String line;
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < sortedArray.length; i++) {
                writerToFile.write(String.valueOf(sortedArray[i] + "\n"));
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }
}
