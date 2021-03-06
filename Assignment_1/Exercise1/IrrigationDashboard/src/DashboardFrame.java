
import controller.Controller;
import java.beans.PropertyChangeEvent;
import moisturesensor.MoistureSensor;

public class DashboardFrame extends javax.swing.JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private controller.Controller controller;
    private java.awt.Label decreasingLBL;
    private java.awt.Label humidityLBL;
    private moisturesensor.MoistureSensor moistureSensor;
    private java.awt.Label onLBL;
    private java.awt.Button startStopSensing;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form DashboardFrame
     */
    public DashboardFrame() {
        controller = new Controller();
        moistureSensor = new MoistureSensor();

        initComponents();
        bind();

    }

    private void bind() {

        moistureSensor.addPropertyChangeListener(MoistureSensor.HUM_CHANNEL, (PropertyChangeEvent evt) -> {
            int humidity = (int) evt.getNewValue();
            controller.setLocHumidity(humidity);
            humidityLBL.setText("" + humidity);
        });

        moistureSensor.addPropertyChangeListener(MoistureSensor.DECR_CHANNEL, (PropertyChangeEvent evt) -> {
            boolean dec = (boolean) evt.getNewValue();
            decreasingLBL.setText(dec ? "Decreasing" : "Not Decreasing");
        });

        controller.addPropertyChangeListener(Controller.ON_CHANNEL, (PropertyChangeEvent evt) -> {
            boolean new_on = (boolean) evt.getNewValue();
            onLBL.setText(new_on ? "Active" : "Not Active");
            moistureSensor.setDecreasing(!new_on);
        });

    }

    private void startStopSensingActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startStopSensingActionPerformed
        if (!moistureSensor.isStarted()) {
            moistureSensor.start();
            startStopSensing.setLabel("STOP");
        } else {
            moistureSensor.stop();
            startStopSensing.setLabel("START");
        }
    }// GEN-LAST:event_startStopSensingActionPerformed

    /**
     * 
     * THE FOLLOWING CODE IS AUTOGENERATED
     * 
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controller = new controller.Controller();
        moistureSensor = new moisturesensor.MoistureSensor();
        humidityLBL = new java.awt.Label();
        decreasingLBL = new java.awt.Label();
        startStopSensing = new java.awt.Button();
        onLBL = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        humidityLBL.setText("Loading...");

        decreasingLBL.setText("Loading...");

        startStopSensing.setLabel("Start");
        startStopSensing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStopSensingActionPerformed(evt);
            }
        });

        onLBL.setText("Loading...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(50, 50, 50)
                        .addComponent(startStopSensing, javax.swing.GroupLayout.PREFERRED_SIZE, 172,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(78, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(humidityLBL, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(decreasingLBL, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(onLBL, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(39, 39, 39)
                        .addComponent(humidityLBL, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(decreasingLBL, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(onLBL, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(startStopSensing, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardFrame().setVisible(true);
            }
        });
    }
}
