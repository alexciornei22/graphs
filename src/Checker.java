import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class Checker {
    public static void main(String[] args) throws IOException {
        File dirProblem1 = new File("tests/pb1_tests");
        File dirProblem2 = new File("tests/pb2_tests");
        File dirProblem3 = new File("tests/pb3_tests");

        File inputFile = new File("date.in");
        File outputFile = new File("date.out");

        System.out.println("Problema 1:");
        for (File testFile : Objects.requireNonNull(dirProblem1.listFiles())) {
            if (!testFile.getName().endsWith(".in"))
                continue;
            File refFile = new File(dirProblem1.toPath() + "/" + testFile.getName().substring(0, testFile.getName().lastIndexOf('.')) + ".out");

            Files.copy(testFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Cycle.main(null);

            long result = Files.mismatch(refFile.toPath(), outputFile.toPath());
            if (result == -1) {
                System.out.println(testFile.getName() + " OK");
            } else {
                System.out.println(testFile.getName() + " Wrong");
            }
        }
        System.out.println();

        System.out.println("Problema 2:");
        for (File testFile : Objects.requireNonNull(dirProblem2.listFiles())) {
            if (!testFile.getName().endsWith(".in"))
                continue;
            File refFile = new File(dirProblem2.toPath() + "/" + testFile.getName().substring(0, testFile.getName().lastIndexOf('.')) + ".out");

            Files.copy(testFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            ShortestPathDAG.main(null);

            long result = Files.mismatch(refFile.toPath(), outputFile.toPath());
            if (result == -1) {
                System.out.println(testFile.getName() + " OK");
            } else {
                System.out.println(testFile.getName() + " Wrong " + result);
            }
        }
        System.out.println();

        System.out.println("Problema 3:");
        for (File testFile : Objects.requireNonNull(dirProblem3.listFiles())) {
            if (!testFile.getName().endsWith(".in"))
                continue;
            File refFile = new File(dirProblem3.toPath() + "/" + testFile.getName().substring(0, testFile.getName().lastIndexOf('.')) + ".out");

            Files.copy(testFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            ShortestPathSmallRange.main(null);

            long result = Files.mismatch(refFile.toPath(), outputFile.toPath());
            if (result == -1) {
                System.out.println(testFile.getName() + " OK");
            } else {
                System.out.println(testFile.getName() + " Wrong");
            }
        }
        System.out.println();

    }
}
