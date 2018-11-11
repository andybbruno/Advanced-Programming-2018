package controller3;

import java.beans.*;

/**
 * This class contains the irrigation logic. In particular, this class
 * implements {@code PropertyChangeListener} interface in order to be update if
 * any of the bounded properties update.
 *
 * @author Andrea Bruno 585457
 */
public class Controller3 implements VetoableChangeListener {

    private final int HUM_UPPER_BOUND = 90;
    private final int HUM_LOWER_BOUND = 30;

    /**
     * {@code changes} manage a list of listeners and dispatches
     * {@link PropertyChangeEvent} to them.
     */
    private final VetoableChangeSupport changes = new VetoableChangeSupport(this);

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
    private void startIrrigation() throws PropertyVetoException {
        boolean old = this.on;
        this.on = true;
        changes.fireVetoableChange("on", old, this.on);
    }

    /**
     * Stop irrigation and fire a property change
     */
    private void stopIrrigation() throws PropertyVetoException {
        boolean old = this.on;
        this.on = false;
        changes.fireVetoableChange("on", old, this.on);
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
     * Add a {@code VetoableChangeListener} to the listener list.
     *
     * @param listener The {@code VetoableChangeListener} to be added
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        changes.addVetoableChangeListener(listener);
    }

    /**
     * Remove a {@code VetoableChangeListener} from the listener list.
     *
     * @param listener The {@code VetoableChangeListener} to be removed
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        changes.removeVetoableChangeListener(listener);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param event this object describe the event source and the property that
     * has changed.
     * @throws PropertyVetoException
     */
    @Override
    public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {

        if (event.getPropertyName().equals("currentHumidity")) {
            int oldHum = locHumidity;
            locHumidity = (int) event.getNewValue();

            if (locHumidity < HUM_LOWER_BOUND) {
                startIrrigation();
            }
            if (locHumidity > HUM_UPPER_BOUND) {
                stopIrrigation();
            }
            changes.fireVetoableChange("locHumidity", oldHum, locHumidity);
        }

        if (event.getPropertyName().equals("decreasing")) {
            boolean oldVal = (boolean) event.getOldValue();
            boolean newVal = (boolean) event.getNewValue();

            changes.fireVetoableChange("decreased", oldVal, newVal);
        }

    }
}
