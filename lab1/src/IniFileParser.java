import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IniFileParser {
    IniFile file;
    private HashMap<String, Section> sectionMap;
    private static final Pattern[] patterns = new Pattern[]{Pattern.compile(";.*"), Pattern.compile("\\[\\w+\\]"),
            Pattern.compile("\\w+"), Pattern.compile("[a-zA-z0-9_/.]+")};

    IniFileParser(IniFile file) throws FileNotFoundException, InvalidFileFormatException {
        this.file = file;
        sectionMap = new HashMap<>();
        parse();
    }

    private void parse() throws InvalidFileFormatException, FileNotFoundException {
        Scanner sc = new Scanner(new File(file.getFileName()));
        Section section = null;
        while (sc.hasNextLine()) {
            String fileLine = sc.nextLine();
            if (fileLine.matches("\\s*"))
                continue;
            String[] splittedLine = fileLine.split("\\s+");
            for (String word : splittedLine) {
                if (match(patterns[0], word))
                    break;
                else if (match(patterns[1], word)) {
                    if (section != null) {
                        addSection(section.getFormattedName(), section);
                    }
                    section = new Section(word);
                } else if (word.contains("=")) {
                    String[] PV = word.split("=", 2);
                    if (section == null || !match(patterns[2], PV[0]) || !match(patterns[3], PV[1])) {
                        throw new InvalidFileFormatException("Invalid file format: " + word);
                    }
                    section.addParameter(PV[0], PV[1]);
                } else
                    throw new InvalidFileFormatException("Invalid file format: " + word);
            }
        }
        if (section != null)
            addSection(section.getFormattedName(), section);
    }

    private boolean match(Pattern pattern, String word) {
        return pattern.matcher(word).matches();
    }

    private void addSection(String sectionName, Section section) throws InvalidFileFormatException {
        if (sectionMap.containsKey(sectionName))
            throw new InvalidFileFormatException("Duplicate section: " + sectionName);
        sectionMap.put(sectionName, section);
    }

    public int getValueInt(String section, String parameter) throws NumberFormatException, NullPointerException {
        return Integer.parseInt(sectionMap.get(section).get(parameter));
    }

    public double getValueDouble(String section, String parameter) throws NumberFormatException, NullPointerException {
        return Double.parseDouble(sectionMap.get(section).get(parameter));
    }

    public String getValueString(String section, String parameter) throws NullPointerException {
        return sectionMap.get(section).get(parameter);
    }
}
