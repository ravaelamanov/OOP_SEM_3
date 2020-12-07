import java.util.ArrayList;

public class LengthLimiter implements ILimiter {
    int lenghtLimit;

    LengthLimiter(int lengthLimit) {
        this.lenghtLimit = lengthLimit;
    }

    @Override
    public int limit(ArrayList<? extends RestorePoint> restorePoints) {
        return restorePoints.size() <= lenghtLimit ? 0 : restorePoints.size() - lenghtLimit;
    }
    
    
}
