import java.util.Scanner;

public class InputHelper {

    public static int getIntInput(Scanner scanner,
                                  String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );
            }

            catch (NumberFormatException e) {

                System.out.println(
                        "Invalid number input."
                );
            }
        }
    }

    public static String getStringInput(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }
}