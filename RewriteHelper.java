import java.io.FileWriter;
import java.io.IOException;

public class RewriteHelper {

    public static void writeBooks(Book current,
                                  FileWriter writer)
            throws IOException {

        if (current != null) {

            writeBooks(
                    current.getLeft(),
                    writer
            );

            writer.write(
                    current.getIsbnCode() + "," +
                    current.getBookTitle() + "," +
                    current.getAuthorName() + "," +
                    current.getCategory() + "\n"
            );

            writeBooks(
                    current.getRight(),
                    writer
            );
        }
    }
}