package moisturesensor;

import java.beans.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class MoistureSensor {

    private final int UPPER_BOUND = 100;
    private final int LOWER_BOUND = 0;
    private final Timer timer = new Timer();
    private final Random random = new Random();
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    private boolean decreasing;
    private int currentHumidity = 1;

    
    public MoistureSensor(){
        start();
    }
    

    //activate sensor
    public void start() {
        timer.schedule(new ReadHumidity(), 0, 1000);
    }

    public int getHumidity() {
        return currentHumidity;
    }
    
    public boolean isDecreasing() {
        return decreasing;
    }

    private void setHumidity() {

        int oldHumidity = currentHumidity;
        boolean oldDecreasing = decreasing;

        decreasing = random.nextBoolean();
        int tmp = random.nextInt(UPPER_BOUND);

        if ((decreasing) && (currentHumidity > LOWER_BOUND)) {
            currentHumidity = Math.abs((currentHumidity - tmp) % UPPER_BOUND);
        } else if ((!decreasing) && (currentHumidity < UPPER_BOUND)) {
            currentHumidity = Math.abs((currentHumidity + tmp) % UPPER_BOUND);
        }
        
        changes.firePropertyChange("decreasing", oldDecreasing, decreasing);
        changes.firePropertyChange("currentHumidity", oldHumidity, currentHumidity);

    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }


    class ReadHumidity extends TimerTask {

        @Override
        public void run() {
            setHumidity();
        }
    }
}
