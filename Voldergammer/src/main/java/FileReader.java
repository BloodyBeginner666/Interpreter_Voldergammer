import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    String path;
    public FileReader(String path) {
        this.path = path;
    }

    public List<String> readFile() throws IOException {
        List<String> code = new ArrayList<>();
        Scanner scanner = new Scanner(new File(this.path));
        while (scanner.hasNextLine()) {
            code.add(scanner.nextLine());
        }
        return code;
    }
}