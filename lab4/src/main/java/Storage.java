import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract public class Storage {
    File storage;

    public Storage(String path) {
        storage = new File(path);
    }

    public String getPath() {
        return storage.getPath();
    }

    abstract public void addFile(File file) throws IOException;

    abstract public void deleteFile(File file) throws IOException;

    public void deleteFiles(ArrayList<File> files) throws IOException {
        for (File file : files) {
            deleteFile(file);
        }
    }

    abstract public List<String> readLines(File file) throws IOException;
}
