import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class NIOCopy {

    public static void main(String[] args) {

        try {

            Path source = Path.of("C:/Users/mosca/Downloads/Profile.pdf");
            Path target = Path.of("C:/Users/mosca/Desktop/Profile.pdf");

            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (InvalidPathException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
