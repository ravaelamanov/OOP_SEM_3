import java.util.regex.Pattern;

public class IniFile {
    private String fileName;
    private static Pattern extPattern = Pattern.compile(".+\\.ini");

    IniFile(String fileName) throws NotIniFileException {
        this.fileName = fileName;
        validateExtension();
    }

    private void validateExtension() throws NotIniFileException {
        boolean hasValidExtension = extPattern.matcher(fileName).matches();
        if (!hasValidExtension) {
            String[] path = fileName.split("/");
            throw new NotIniFileException("Not .ini file. File name is: " + path[path.length - 1]);
        }
    }

    public String getFileName() {
        return fileName;
    }
}
