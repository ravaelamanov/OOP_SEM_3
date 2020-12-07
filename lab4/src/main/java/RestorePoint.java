import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.PatchFailedException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract public class RestorePoint {
    protected LocalDateTime creationTime;
    protected ArrayList<File> files;
    protected double size;

    public RestorePoint() {
        creationTime = LocalDateTime.now();
        files = new ArrayList<>();
    }

    public double getSize() {
        return size;
    }

    public void addFile(File original, int modification, List<? extends RestorePoint> restorePoints, Storage storage) throws IOException, PatchFailedException, DiffException {
        String newFileName = modifyFilename(original, modification);
        File newFile = new File(newFileName);
        addFileImpl(original, newFile, restorePoints, storage);
        storage.addFile(newFile);
        newFile.delete();
        files.add(new File(newFileName));

    }

    public boolean contains(File file) {
        String fileName = file.getName().split("\\.", 2)[0];
        for (File f : files) {
            if (f.getName().matches(fileName + "_" + ".*"))
                return true;
        }
        return false;
    }

    public File getModification(File file) {
        String fileName = file.getName().split("\\.", 2)[0];
        for (File f : files) {
            if (f.getName().matches(fileName + "_" + ".*"))
                return f;
        }
        return null;
    }

    public double delete(File file) {
        Objects.requireNonNull(file);
        double size = file.length();
/*        if (!file.delete()) {
            throw new RuntimeException("Couldn't delete file! File name: " + file.getName());
        }*/
        files.remove(file);
        return size;
    }

    public double deleteAll() {
        double size = 0;
        ArrayList<File> filesToDelete = new ArrayList<>(files);
        for (File file : filesToDelete) {
            size += delete(file);
        }
        return size;
    }

    public ArrayList<File> getFiles() {
        return new ArrayList<>(files);
    }

    public int fileCount() {
        return files.size();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    abstract protected String modifyFilename(File original, int modification);

    abstract protected void addFileImpl(File original, File revised, List<? extends RestorePoint> restorePoints, Storage storage) throws IOException, PatchFailedException, DiffException;
}
