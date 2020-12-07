import java.util.HashMap;
import java.util.regex.Pattern;

public class Section {
    private HashMap<String, String> section;
    private String formattedName;
    private String rawName;

    Section(String raw) {
        section = new HashMap<>();
        rawName = raw;
        formattedName = rawName.substring(1, rawName.length() - 1);
    }

    public String getFormattedName() {
        return formattedName;
    }

        public void addParameter(String parameter, String value) throws InvalidFileFormatException {
        if (section.containsKey(parameter))
            throw new InvalidFileFormatException("Duplicate parameter: " + parameter);
        section.put(parameter, value);
    }

    public String get(String parameter) throws NullPointerException {
        return section.get(parameter);
    }
}
