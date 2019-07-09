package moisturesensor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MoistureSensor implements Serializable {

    public final static String HUM_CHANNEL = "humidity";
    public final static String DECR_CHANNEL = "decreasing";

    private boolean decreasing;
    private int currentHumidity;
    private boolean started;

    private final int LOWER_BOUND = 0;
    private final int UPPER_BOUND = 100;
    private final int STEP_RANGE = 20;

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private Timer timer = new Timer();

    public MoistureSensor() {
        setDecreasing(false);
        setHumidity(50);
    }

    public void start() {
        if (!started) {
            started = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Random rand = new Random();
                    int tmpHumidity;

                    boolean decr = rand.nextBoolean();
                    setDecreasing(decr);

                    if (decr) {
                        tmpHumidity = Math.abs(currentHumidity - rand.nextInt(STEP_RANGE));
                    } else {
                        tmpHumidity = Math.min(Math.abs(currentHumidity + rand.nextInt(STEP_RANGE)), UPPER_BOUND);
                    }
                    setHumidity(tmpHumidity);

                }
            }, 0, 1000);

        }

    }

    public void stop() {
        timer.cancel();
        timer = new Timer();
        started = false;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setHumidity(int new_humidity) {
        changes.firePropertyChange(HUM_CHANNEL, this.currentHumidity, new_humidity);
        this.currentHumidity = new_humidity;
    }

    public int getHumidity() {
        return this.currentHumidity;
    }

    public void setDecreasing(boolean new_bool) {
        changes.firePropertyChange(DECR_CHANNEL, this.decreasing, new_bool);
        this.decreasing = new_bool;
    }

    public boolean isDecreasing() {
        return this.decreasing;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changes.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changes.removePropertyChangeListener(propertyName, listener);
    }
}
