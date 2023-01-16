public class InvalidFileName extends RuntimeException {
    public InvalidFileName(String fileName) {
        super("[ERROR] Invalid name of file: " + fileName +
                ". Check that the file name matches the template: *.txt");
    }
}
