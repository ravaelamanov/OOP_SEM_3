import java.util.ArrayList;

@FunctionalInterface
public interface ILimiter {
    int limit(ArrayList<? extends RestorePoint> restorePoints);
    
    default ILimiter and(ILimiter other) {
        return (points) -> Math.min(limit(points), other.limit(points));
    }

    default ILimiter or(ILimiter other) {
        return (points) -> Math.max(limit(points), other.limit(points));
    }
}
