import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class IncrementalRestorePoint extends RestorePoint {
    @Override
    protected String modifyFilename(File original, int modification) {
        String[] splitted = original.getName().split("\\.", 2);
        return splitted[0] + "_" + modification + ".diff";
    }

    @Override
    protected void addFileImpl(File original, File revised, List<? extends RestorePoint> restorePoints, Storage storage) throws IOException, PatchFailedException, DiffException {
        int fullPos = getLastFullPoint(original, restorePoints);

        if (fullPos < 0) {
            throw new FileNotFoundException("No full restore point for " + original.getName() + " file!");
        }

        RestorePoint fullPoint = restorePoints.get(fullPos);
        File lastModification = fullPoint.getModification(original);
        List<String> lastModificationLines = storage.readLines(lastModification);

        for (int i = fullPos + 1; i < restorePoints.size(); i++) {
            if (restorePoints.get(i).contains(original)) {
                File diff = restorePoints.get(i).getModification(original);
                List<String> diffLines = storage.readLines(diff);
                Patch<String> patch = UnifiedDiffUtils.parseUnifiedDiff(diffLines);
                lastModificationLines = DiffUtils.patch(lastModificationLines, patch);
            }
        }

        Patch<String> patch = DiffUtils.diff(lastModificationLines, Files.readAllLines(original.toPath()));
        List<String> diff = UnifiedDiffUtils.generateUnifiedDiff("", original.getName(), lastModificationLines, patch, 5);
        BufferedWriter writer = new BufferedWriter(new FileWriter(revised));
        for (String line : diff) {
            writer.write(line + "\n");
            size += line.length();
        }
        writer.close();
    }

    private int getLastFullPoint(File original, List<? extends RestorePoint> restorePoints) {
        int pos = 0;
        int fullPos = -1;
        for (RestorePoint restorePoint : restorePoints) {
            if (restorePoint.getClass().getName().equals(IncrementalRestorePoint.class.getName()))
                continue;
            if (restorePoint.contains(original)) {
                fullPos = pos;
            }
            pos++;
        }
        return fullPos;
    }
}
