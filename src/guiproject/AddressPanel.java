/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 *
 * @author Jenny
 */
public class AddressPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddressPanel
     */
    public AddressPanel() {
        initComponents();
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adress1Label1 = new javax.swing.JLabel();
        address1TextField = new javax.swing.JTextField();
        adress2TextField1 = new javax.swing.JTextField();
        adress2Label1 = new javax.swing.JLabel();
        codeLabel1 = new javax.swing.JLabel();
        postCodeTextField = new javax.swing.JTextField();
        cityTextField1 = new javax.swing.JTextField();
        cityLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        adress1Label1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress1Label1.setForeground(new java.awt.Color(51, 51, 51));
        adress1Label1.setText("Adress 1");

        address1TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddressUpdate(evt);
            }
        });

        adress2TextField1.setEditable(false);
        adress2TextField1.setBackground(new java.awt.Color(204, 204, 204));

        adress2Label1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress2Label1.setForeground(new java.awt.Color(153, 153, 153));
        adress2Label1.setText("Adress 2");

        codeLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        codeLabel1.setForeground(new java.awt.Color(51, 51, 51));
        codeLabel1.setText("Postnummer");

        postCodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PostCodeUpdate(evt);
            }
        });

        cityTextField1.setEditable(false);
        cityTextField1.setBackground(new java.awt.Color(204, 204, 204));

        cityLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cityLabel1.setForeground(new java.awt.Color(153, 153, 153));
        cityLabel1.setText("Ort");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(adress1Label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(address1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(adress2Label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adress2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(codeLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(cityLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cityTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress1Label1)
                    .addComponent(address1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label1)
                    .addComponent(adress2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel1)
                    .addComponent(postCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel1)
                    .addComponent(cityTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddressUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddressUpdate
        IMatDataHandler.getInstance().getCustomer().setPostAddress(address1TextField.getText());
    }//GEN-LAST:event_AddressUpdate

    private void PostCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PostCodeUpdate
        IMatDataHandler.getInstance().getCustomer().setPostCode(postCodeTextField.getText());
    }//GEN-LAST:event_PostCodeUpdate

    protected boolean hasAddress(){
        return !address1TextField.getText().equals("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address1TextField;
    private javax.swing.JLabel adress1Label1;
    private javax.swing.JLabel adress2Label1;
    private javax.swing.JTextField adress2TextField1;
    private javax.swing.JLabel cityLabel1;
    private javax.swing.JTextField cityTextField1;
    private javax.swing.JLabel codeLabel1;
    private javax.swing.JTextField postCodeTextField;
    // End of variables declaration//GEN-END:variables
}
