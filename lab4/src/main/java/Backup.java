import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.PatchFailedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.*;
import java.util.*;

public class Backup {
    private int ID;
    private LocalDateTime creationTime;
    private double backupSize;
    ArrayList<RestorePoint> restorePoints;
    private HashMap<String, Integer> modifications;
    private Storage storage;
    private static int counter = 1;

    public Backup(String storagePath, Class<? extends Storage> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        backupSize = 0;
        ID = counter++;
        storage = clazz.getConstructor(String.class).newInstance(storagePath + "/" + ID);
        restorePoints = new ArrayList<>();
        modifications = new HashMap<>();
        creationTime = LocalDateTime.now();
    }

    public void createRestorePoint(ArrayList<String> paths, Class<? extends RestorePoint> clazz) throws IllegalAccessException, InstantiationException, IOException, PatchFailedException, DiffException {
        RestorePoint restorePoint = clazz.newInstance();
        for (ListIterator<String> it = paths.listIterator(); it.hasNext();) {
            String path = it.next();
            File file = new File(path);
            if (file.isDirectory()) {
                for (File file1 : file.listFiles()) {
                    it.add(file1.getPath());
                    it.previous();
                }
                continue;
            }
            if (!file.exists())
                throw new FileNotFoundException(path + " file does not exist!");
            if (!modifications.containsKey(file.getAbsolutePath()))
                modifications.put(file.getAbsolutePath(), 0);
            modifications.put(file.getAbsolutePath(), modifications.get(file.getAbsolutePath()) + 1);
            restorePoint.addFile(file, modifications.get(file.getAbsolutePath()), restorePoints, storage);
        }
        restorePoints.add(restorePoint);
        backupSize += restorePoint.getSize();
    }

    public void deleteFiles(Iterable<String> paths) throws IOException {
        double size = 0;
        for (String path : paths) {
            File file = new File(path);
            for (RestorePoint restorePoint : restorePoints) {
                if (restorePoint.contains(file)) {
                    File fileToDelete = restorePoint.getModification(file);
                    storage.deleteFile(fileToDelete);
                    size += restorePoint.delete(fileToDelete);
                }
            }
            modifications.remove(path);
        }
        backupSize -= size;
        restorePoints.removeAll(getEmptyPoints());
    }

    private ArrayList<RestorePoint> getEmptyPoints() {
        ArrayList<RestorePoint> emptyPoints = new ArrayList<>();
        for (RestorePoint restorePoint : restorePoints) {
            if (restorePoint.fileCount() == 0) {
                emptyPoints.add(restorePoint);
            }
        }
        return emptyPoints;
    }

    public void limit(ILimiter limiter) throws IOException {
        int count = limiter.limit(restorePoints);
        ArrayList<RestorePoint> pointsToDelete = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            pointsToDelete.add(restorePoints.get(i));
        }
        for (int i = count; i < restorePoints.size(); i++) {
            if (restorePoints.get(i).getClass().getName().equals(IncrementalRestorePoint.class.getName()))
                pointsToDelete.add(restorePoints.get(i));
        }
        double size = 0;
        for (RestorePoint restorePoint : pointsToDelete) {
            storage.deleteFiles(restorePoint.getFiles());
            size += restorePoint.deleteAll();
        }
        restorePoints.removeAll(pointsToDelete);
        backupSize -= size;
    }
}
