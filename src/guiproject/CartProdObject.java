package guiproject;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author RebeckaReitmaier
 */


public class CartProdObject extends javax.swing.JPanel {
    private IMatDataHandler dataHandler;
    
    ShoppingItem si;
    IMatFrame imf;
    boolean pinkBG;
    
    public CartProdObject(ShoppingItem si, IMatFrame imf, boolean pinkBG) {
        initComponents();
        this.dataHandler = IMatDataHandler.getInstance();
        this.si = si;
        this.imf = imf;
        this.pinkBG = pinkBG;
        
        priceLabel.setText(si.getTotal() +" kr");
        nameLabel.setText(si.getProduct().getName());
        imageLabelCart.setIcon(dataHandler.getImageIcon(si.getProduct(), 50,50));
        jSpinner2.setValue(si.getAmount());
        
        if (!pinkBG){
            setBackground(new java.awt.Color(215,173,173));
            imagePanel.setBackground(new java.awt.Color(215,173,173));
            editPanel.setBackground(new java.awt.Color(215,173,173));
        } 
    }
    @Override
    public String toString(){
        return nameLabel.getText()+ ", amount: " + jSpinner2.getValue();
    }
    
    
    public ShoppingItem getShoppingItem(){
        return si;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JPanel();
        imageLabelCart = new javax.swing.JLabel();
        editPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 248, 248));
        setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(196, 43, 43), new java.awt.Color(156, 25, 25)));
        setPreferredSize(new java.awt.Dimension(130, 100));

        imagePanel.setBackground(new java.awt.Color(255, 248, 248));
        imagePanel.setPreferredSize(new java.awt.Dimension(60, 60));
        imagePanel.add(imageLabelCart);

        editPanel.setBackground(new java.awt.Color(255, 248, 248));

        jLabel3.setText("Antal:");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 0, 100, 1));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteNormal.png"))); // NOI18N
        jButton1.setToolTipText("Ta bort från varukorg");
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/deleteHover.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Pris:");

        priceLabel.setText("jLabel4");

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(priceLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nameLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(editPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(editPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        imf.gpCon.sc.removeItem(si);
        imf.gpCon.addToCart(null, 0, imf);
        imf.gpCon.iMDH.shutDown();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged

    }//GEN-LAST:event_jSpinner2StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel editPanel;
    private javax.swing.JLabel imageLabelCart;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel priceLabel;
    // End of variables declaration//GEN-END:variables
}
