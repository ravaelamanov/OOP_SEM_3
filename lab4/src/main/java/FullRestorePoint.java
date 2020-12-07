import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

public class FullRestorePoint extends RestorePoint {
    @Override
    protected String modifyFilename(File original, int modification) {
        String[] splitted = original.getName().split("\\.", 2);
        String newPath = splitted[0] + "_" + modification;
        if (splitted.length == 2)
            newPath += "." + splitted[1];
        return newPath;
    }

    @Override
    protected void addFileImpl(File original, File revised, List<? extends RestorePoint> restorePoints, Storage storage) throws IOException {
        try (FileChannel src = new FileInputStream(original).getChannel();
             FileChannel dest = new FileOutputStream(revised).getChannel()) {
            dest.transferFrom(src, 0, src.size());
        }
        size += original.length();
    }
}
