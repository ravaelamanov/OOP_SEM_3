import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            IniFile file = new IniFile("src/file.ini");
            IniFileParser parser = new IniFileParser(file);
            System.out.println(parser.getValueDouble("NCMD" , "SapmleRate"));
            System.out.println(parser.getValueInt("COMMON", "StatisterTimeMs"));
            System.out.println(parser.getValueString("COMMON", "DiskCachePath"));
        }
        catch (NullPointerException | NotIniFileException | InvalidFileFormatException | FileNotFoundException |
                NumberFormatException ex) {
            System.err.println(ex.toString());
        }
    }
}
