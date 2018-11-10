package controller;

import java.beans.*;

public class Controller implements PropertyChangeListener {

    private final int HUM_UPPER_BOUND = 90;
    private final int HUM_LOWER_BOUND = 30;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private boolean on;
    private int locHumidity;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

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

    private void startIrrigation() {
        boolean old = this.on;
        this.on = true;
        changes.firePropertyChange("on", old, this.on);
    }

    private void stopIrrigation() {
        boolean old = this.on;
        this.on = false;
        changes.firePropertyChange("on", old, this.on);
    }

    public boolean isOn() {
        return this.on;
    }
}
