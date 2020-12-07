import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Archive extends Storage {
    ZipFile zipFile;
    public Archive(String path) {
        super(path + ".zip");
        zipFile = new ZipFile(storage);
    }

    @Override
    public void addFile(File file) throws ZipException {
        zipFile.addFile(file);
    }

    @Override
    public void deleteFile(File file) throws IOException {
        FileHeader fileHeader = zipFile.getFileHeader(file.getName());

        if (fileHeader == null) {
            throw new ZipException("No such file: " + file.getName(), ZipException.Type.FILE_NOT_FOUND);
        }

        zipFile.removeFile(fileHeader);
    }

    @Override
    public List<String> readLines(File file) throws IOException {
        FileHeader fileHeader = zipFile.getFileHeader(file.getName());

        if (fileHeader == null) {
            throw new ZipException("No such file: " + file.getName(), ZipException.Type.FILE_NOT_FOUND);
        }

        InputStream in = zipFile.getInputStream(fileHeader);
        List<String> lines = new BufferedReader(new InputStreamReader(in,
                StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        in.close();
        return lines;
    }
}
