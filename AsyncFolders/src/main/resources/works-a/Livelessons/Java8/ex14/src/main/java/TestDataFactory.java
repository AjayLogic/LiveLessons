import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * This utility class contains methods for obtaining test data.
 */
public class TestDataFactory {
    /**
     * A utility class should always define a private constructor.
     */
    private TestDataFactory() {
    }

    /**
     * Return the input data in the given @a filename as a list of
     * Strings.
     */
    public static List<CharSequence> getInput(String filename,
                                              String splitter) {
        try {
            // Convert the filename into a pathname.
            URI uri = ClassLoader.getSystemResource(filename).toURI();

            // Open the file and get all the bytes.
            CharSequence bytes =
                new String(Files.readAllBytes(Paths.get(uri)));

            return Pattern
                // Compile splitter into a regular expression (regex).
                .compile(splitter)

                // Use the regex to split the file into a stream of
                // strings.
                .splitAsStream(bytes)

                // Filter out any empty strings.
                .filter(((Predicate<String>) String::isEmpty).negate())
                
                // Collect the results into a string.
                .collect(toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
