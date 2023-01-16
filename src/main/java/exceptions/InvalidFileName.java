package exceptions;

public class InvalidFileName extends RuntimeException {
    public InvalidFileName(String fileName) {
        System.out.println("[ERROR] Invalid name of file: " + fileName +
                ". Check that the file name matches the template: *.txt");
    }
}
