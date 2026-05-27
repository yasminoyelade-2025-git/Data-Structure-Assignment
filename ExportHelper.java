import java.io.FileWriter;
import java.io.IOException;

public class ExportHelper {

    public static void exportBooks(Book current,
                                   FileWriter writer)
            throws IOException {

        if (current != null) {

            exportBooks(
                    current.getLeft(),
                    writer
            );

            writer.write(
                    current.toString()
            );

            writer.write(
                    "\n----------------------------------\n"
            );

            exportBooks(
                    current.getRight(),
                    writer
            );
        }
    }
}