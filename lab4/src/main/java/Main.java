import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Backup backup = new Backup("backups", Archive.class);

            backup.createRestorePoint(new ArrayList<>(Arrays.asList("old.txt", "new.txt")), FullRestorePoint.class);

            writeToFile("old.txt", "this\nis\na\nnew\nfile\n");
            backup.createRestorePoint(new ArrayList<>(Arrays.asList("old.txt")), IncrementalRestorePoint.class);

            writeToFile("old.txt", "this\nis\na\nnew\nnew\nfile\n");
            backup.createRestorePoint(new ArrayList<>(Arrays.asList("old.txt")), IncrementalRestorePoint.class);

            writeToFile("old.txt", "this\nis\na\nvery\nnew\nfile\n");
            backup.createRestorePoint(new ArrayList<>(Arrays.asList("old.txt")), FullRestorePoint.class);

            //backup.deleteFiles(Arrays.asList("old.txt"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }

    }

    public static void writeToFile(String path, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(data);
        writer.flush();
        writer.close();
    }
}
