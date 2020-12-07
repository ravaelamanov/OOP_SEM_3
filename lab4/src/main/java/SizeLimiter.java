import java.util.ArrayList;

public class SizeLimiter implements ILimiter {
    double sizeLimit;

    public SizeLimiter(double sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    @Override
    public int limit(ArrayList<? extends RestorePoint> restorePoints) {
        double curSize = 0;
        int i;
        for (i = restorePoints.size() - 1; i >= 0; i--) {
            if (curSize > sizeLimit)
                break;
            curSize += restorePoints.get(i).getSize();
        }
        return restorePoints.size() - i;
    }
}
