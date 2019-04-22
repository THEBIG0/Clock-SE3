package Clock;

/*
 * Testing environment for checking simple functions of digit class.
 */
import bgi.TouchEvent;

/**
 * @author sbrown
 */
public class TestClockDigit extends javax.swing.JFrame{

    /**
     * Creates new form DigitTestBed
     */
    public TestClockDigit() {
        initComponents();
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        charField = new javax.swing.JTextField();
        manualSetButton = new javax.swing.JButton();
        hideButton = new javax.swing.JButton();
        incButton = new javax.swing.JButton();
        decButton = new javax.swing.JButton();
        touchReadout = new javax.swing.JLabel();
        digitCell = new javax.swing.JPanel();
        digit = new Clock.Digit();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        charField.setColumns(1);
        charField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        charField.setText(":");

        manualSetButton.setText("Set");
        manualSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualSetButtonActionPerformed(evt);
            }
        });

        hideButton.setLabel("Hide digit");
        hideButton.setMaximumSize(new java.awt.Dimension(44, 32));
        hideButton.setMinimumSize(new java.awt.Dimension(44, 32));
        hideButton.setPreferredSize(new java.awt.Dimension(44, 32));
        hideButton.setSize(new java.awt.Dimension(44, 0));
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });

        incButton.setText("▲");
        incButton.setPreferredSize(new java.awt.Dimension(44, 32));
        incButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incButtonActionPerformed(evt);
            }
        });

        decButton.setText("▼");
        decButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decButtonActionPerformed(evt);
            }
        });

        touchReadout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        touchReadout.setText("Touched");
        touchReadout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(incButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(controlPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(charField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manualSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hideButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(touchReadout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(decButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(incButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(hideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(charField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manualSetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(touchReadout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        digitCell.setBackground(new java.awt.Color(0, 0, 0));
        digitCell.setPreferredSize(new java.awt.Dimension(154, 308));
        digitCell.setSize(new java.awt.Dimension(154, 308));
        digitCell.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        digit.addTouchListener(new bgi.TouchListener() {
            public void touchInitiated(bgi.TouchEvent evt) {
            }
            public void touchReleased(bgi.TouchEvent evt) {
                digitTouchReleased(evt);
            }
            public void touchCancelled(bgi.TouchEvent evt) {
            }
        });
        digitCell.add(digit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(digitCell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(digitCell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void incButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incButtonActionPerformed
        int currentDigit = digit.getDigit();
        digit.setDigit((currentDigit + 1) % 10);
    }//GEN-LAST:event_incButtonActionPerformed

    private void decButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decButtonActionPerformed
        int currentDigit = digit.getDigit();
        if (currentDigit == -1) currentDigit++;
        digit.setDigit((currentDigit + 9) % 10);
    }//GEN-LAST:event_decButtonActionPerformed
    
    /**
     * Calls setChar method of digit ONLY with the first character of the input.
     * Also removes extra characters from field
     */
    private void manualSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualSetButtonActionPerformed
        char parsedChar = this.charField.getText().charAt(0);
        this.charField.setText(String.valueOf(parsedChar));
        digit.setChar(parsedChar);
    }//GEN-LAST:event_manualSetButtonActionPerformed

    /**
     * toggle button to hide/show digit
     */
    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        if (hideButton.getText().equals("Hide digit")){
            digit.setChar(' ');
            hideButton.setText("Hide text");
            String[] str = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            digit.setText(str);
        }   else {
            digit.setDigit(0);
            digit.clearText();
            hideButton.setText("Hide digit");
        }
    }//GEN-LAST:event_hideButtonActionPerformed

    private void digitTouchReleased(bgi.TouchEvent evt) {//GEN-FIRST:event_digitTouchReleased
        touchReadout.setText("Touched " + evt.getTouched());
    }//GEN-LAST:event_digitTouchReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestClockDigit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestClockDigit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestClockDigit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestClockDigit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestClockDigit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField charField;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton decButton;
    private Clock.Digit digit;
    private javax.swing.JPanel digitCell;
    private javax.swing.JButton hideButton;
    private javax.swing.JButton incButton;
    private javax.swing.JButton manualSetButton;
    private javax.swing.JLabel touchReadout;
    // End of variables declaration//GEN-END:variables
}
