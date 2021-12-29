package demo.FX;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Coordinates {
    private final Map<Double,Double> current = new ConcurrentSkipListMap<>();

    public synchronized  void addCoordinates(double x, double y) {
        current.put(x,y);
    }

    public Map<Double, Double> getCurrent() {
        return current;
    }
}
