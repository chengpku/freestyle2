/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import cn.edu.pku.datasource.PostgreSQLManager;
import cn.edu.pku.datasource.ShapefileManager;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 *
 * @author 10245
 */
public class SaveToDB extends javax.swing.JFrame {

    /**
     * Creates new form SaveToDB
     */
    public SaveToDB() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_ok.setText("OK");
        jButton_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(jButton_ok)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addComponent(jButton_ok)
                .addGap(79, 79, 79))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okActionPerformed
        // TODO add your handling code here:
        String host = "localhost";
        String port = "5432";
        String database = "postgis_23_sample";
        String user = "postgres";
        String pwd = "123";
        PostgreSQLManager manager = new PostgreSQLManager();
        manager.connetToPostgre(host, port, database, user, pwd);
//        manager.transformShapefileToPostgis("D:/", "test.shp", database, user);
        //read shapefile from disk
        ShapefileManager shpManager = new ShapefileManager();
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null)
            System.out.print("wrong file");
        SimpleFeatureSource featureSource = shpManager.readShpFromFile(file);
        manager.postgisConfig.setDatabaseName(database);
        manager.postgisConfig.setHost(host);
        manager.postgisConfig.setPassword(pwd);
        manager.postgisConfig.setUser(user);
        manager.postgisConfig.setValidateConnection(true);//why this
//        manager.postgisConfig.setSchema(featureSource.getSchema().getTypeName());
        try {
            SimpleFeatureCollection featureCollection = featureSource.getFeatures();
            //drop suffix name
            String name = file.getName().substring(0,file.getName().lastIndexOf("."));
            manager.storeFeatureCollectionToPostgis(featureCollection, name);
        } catch (IOException ex) {
            Logger.getLogger(PostgreSQLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        manager.disconnection();
        System.out.println("data is imported!");
        this.dispose();
    }//GEN-LAST:event_jButton_okActionPerformed

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
            java.util.logging.Logger.getLogger(SaveToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaveToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaveToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaveToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaveToDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ok;
    // End of variables declaration//GEN-END:variables
}