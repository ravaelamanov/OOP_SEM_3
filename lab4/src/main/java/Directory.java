import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.List;

public class Directory extends Storage {
    public Directory(String path) {
        super(path + "/");
        this.storage.mkdirs();
    }

    @Override
    public void addFile(File file) throws IOException {
        File fileToStore = new File(storage.getPath() + "/" + file.getName());
        try (FileChannel src = new FileInputStream(file).getChannel();
             FileChannel dest = new FileOutputStream(fileToStore).getChannel()) {
            dest.transferFrom(src, 0, src.size());
        }
    }

    @Override
    public void deleteFile(File file) throws IOException {
        File fileToDelete = new File(storage.getPath() + "/" + file.getName());
        System.out.println(fileToDelete.getAbsolutePath());
        if (!fileToDelete.delete()) {
            throw new IOException("Couldn't delete file: " + fileToDelete.getAbsolutePath());
        }
    }


    private File getFile(File file) throws FileNotFoundException {
        for (File fileInStorage : storage.listFiles()) {
            if (file.getName().equals(fileInStorage.getName()))
                return fileInStorage;
        }
        throw new FileNotFoundException("Not existing file! File name :" + file.getName());
    }

    @Override
    public List<String> readLines(File file) throws IOException {
        return Files.readAllLines(getFile(file).toPath());
    }
}
