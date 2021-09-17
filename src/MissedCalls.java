import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class MissedCalls {
    private Map<LocalDateTime, String> missedCalls = new TreeMap<>();

    public Map<LocalDateTime, String> getMissedCalls() {
        return missedCalls;
    }

    public void addMissedCall(LocalDateTime time, String phone) {
        missedCalls.put(time, phone);
    }

    public void clearMissedCalls() {
        missedCalls.clear();
    }
}
