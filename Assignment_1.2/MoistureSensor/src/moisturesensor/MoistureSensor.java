package moisturesensor;

import java.beans.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/**
 * This class simulate a logic of a moisture sensor
 *
 * @author Andrea Bruno 585457
 */
public class MoistureSensor {

    private final int UPPER_BOUND = 100;
    private final int LOWER_BOUND = 0;
    private final Timer timer = new Timer();
    private final Random random = new Random();

    /**
     * {@code changes} manage a list of listeners and dispatches
     * {@link PropertyChangeEvent} to them.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private boolean decreasing;
    private int currentHumidity;

    /**
     * The default constructor initialize the sensor and activates it. Once it
     * is active, it start "sensing" the humidity.
     */
    public MoistureSensor() {
        start();
    }

    /**
     * To start sensing, it's simulated a reading by calling a scheduler who
     * create a {@code ReadHumidity()} instance every second.
     */
    private void start() {
        timer.schedule(new ReadHumidity(), 0, 1000);
    }

    /**
     *
     * @return current humidity value
     */
    public int getHumidity() {
        return currentHumidity;
    }

    /**
     *
     * @return if the humidity value is decreasing
     */
    public boolean isDecreasing() {
        return decreasing;
    }

    /**
     * This method exploit a random object to simulate a "natural" environment.
     * Moreover, whether the humidity changes, it fires a property change, in
     * order to update the listeners that have been registered to keep track of
     * those property
     */
    private void simulateHumidity() {

        setDecreasing(random.nextBoolean());
        int tmp = random.nextInt(UPPER_BOUND);

        if ((decreasing) && (currentHumidity > LOWER_BOUND)) {
            setHumidity(Math.abs((currentHumidity - tmp) % UPPER_BOUND));
        } else if ((!decreasing) && (currentHumidity < UPPER_BOUND)) {
            setHumidity(Math.abs((currentHumidity + tmp) % UPPER_BOUND));
        }
    }

    public void setHumidity(int currentHumidity) {
        changes.firePropertyChange("currentHumidity", this.currentHumidity, currentHumidity);
        this.currentHumidity = currentHumidity;
    }
    
    public void setDecreasing(boolean decreasing){
        changes.firePropertyChange("decreasing", this.decreasing, decreasing);
        this.decreasing = decreasing;
    }

    /**
     * Add a {@code PropertyChangeListener} to the listener list.
     *
     * @param listener The {@code PropertyChangeListener} to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Remove a {@code PropertyChangeListener} from the listener list.
     *
     * @param listener The {@code PropertyChangeListener} to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }

    /**
     * This inner class is useful to simulate the behave of a real sensor:
     * thanks to {@code TimerTask} logic, we can let a scheduler start the
     * method {@code run()} as it wants.
     */
    private class ReadHumidity extends TimerTask {

        @Override
        public void run() {
            simulateHumidity();
        }
    }
}
