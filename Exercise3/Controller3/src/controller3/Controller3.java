package controller3;

import java.beans.*;

/**
 * This class contains the irrigation logic. In particular, this class
 * implements {@code PropertyChangeListener} interface in order to be update if
 * any of the bounded properties update.
 *
 * @author Andrea Bruno 585457
 */
public class Controller3 implements PropertyChangeListener {

    private final int HUM_UPPER_BOUND = 90;
    private final int HUM_LOWER_BOUND = 30;

    /**
     * {@code changes} manage a list of listeners and dispatches
     * {@link PropertyChangeEvent} to them.
     */
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private boolean on;
    private int locHumidity;

    /**
     * Void constructor
     */
    public Controller3() {

    }

    /**
     * Start irrigation and fire a property change
     */
    private void startIrrigation() {
        boolean old = this.on;
        this.on = true;
        changes.firePropertyChange("on", old, this.on);
    }

    /**
     * Stop irrigation and fire a property change
     */
    private void stopIrrigation() {
        boolean old = this.on;
        this.on = false;
        changes.firePropertyChange("on", old, this.on);
    }

    /**
     * Actual sensor status
     *
     * @return if the sensor status is ON
     */
    public boolean isOn() {
        return this.on;
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
     * This method gets called when a bound property is changed.
     *
     * @param event this object describe the event source and the property that
     * has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {

        if (event.getPropertyName().equals("currentHumidity")) {
            int oldHum = locHumidity;
            locHumidity = (int) event.getNewValue();

            if (locHumidity < HUM_LOWER_BOUND) {
                startIrrigation();
            }
            if (locHumidity > HUM_UPPER_BOUND) {
                stopIrrigation();
            }
            changes.firePropertyChange("locHumidity", oldHum, locHumidity);
        }

        if (event.getPropertyName().equals("decreasing")) {
            boolean oldVal = (boolean) event.getOldValue();
            boolean newVal = (boolean) event.getNewValue();

            changes.firePropertyChange("decreased", oldVal, newVal);
        }

    }
}