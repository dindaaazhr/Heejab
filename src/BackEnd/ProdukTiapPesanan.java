/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author safirmagenta22
 */
public class ProdukTiapPesanan extends javax.swing.JFrame {

    /**
     * Creates new form ProdukTiapPesanan
     */
    public ProdukTiapPesanan() {
        initComponents();
        Connect();
        simpan.setEnabled(false);
        kodeproduk.setEnabled(false);
        kodepesanan.setEnabled(false);
        
    }

    boolean change = false;
        // CONNECTION --
    Connection con = null;
    ResultSet rs = null;
    Statement st;
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public void Connect(){
        try{
            Class.forName(driver);
            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=HEEJAB; user=shania; password=inipassSQL;" +
                "loginTimeout=30;";
            
            con = DriverManager.getConnection(connectionUrl);
            st = con.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            
            
            // UNTUK MENAMPILKAN DATA KE TABEL
            // perintah untuk pengembalian resultset
            rs = st.executeQuery("SELECT * FROM produk_tiapPesanan");
            ResultSetMetaData rsmdt = rs.getMetaData();
            // menyimpan jumlah kolom
            int columns = rsmdt.getColumnCount();
            //object ini akan mengantarkan data ke Jtable
            DefaultTableModel dtm = new DefaultTableModel();
            Vector columns_name = new Vector();
            Vector data_rows = new Vector();
            
            for (int i = 1; i <= columns; i++) {
                columns_name.addElement(rsmdt.getColumnName(i));
            }
            dtm.setColumnIdentifiers(columns_name);
            
            while(rs.next()) {
                data_rows = new Vector();
                for (int j = 1; j <= columns; j++) {
                    data_rows.addElement(rs.getString(j));
                }
                dtm.addRow(data_rows);
            }
            // pass Default table Object into jTable1
            jTable1.setModel(dtm);
            
            // Menampilkan pop up berhasil atau tidaknya
            JOptionPane.showMessageDialog(null, "Berhasil ditampilkan :)");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal :(" + e.getMessage());
        }
    }

     void cari(){
//        ResultSet rs = null;
            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=HEEJAB; user=shania; password=inipassSQL;" + "loginTimeout=30;";
            try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
                DefaultTableModel tbl = new DefaultTableModel();
                tbl.addColumn("kodepesanan");
                tbl.addColumn("kodeproduk");
                tbl.addColumn("kuantitas");
        
                    try {
                        String pencarian = textSearch.getText();

                    String sql = "SELECT * FROM produk_tiapPesanan WHERE kodepesanan LIKE '%" + pencarian + "%' OR kodeproduk LIKE '%" 
                    + pencarian + "%' OR kuantitas LIKE '%" + pencarian + "%'";


                        rs = st.executeQuery(sql);

                        while(rs.next()) {
                        tbl.addRow(new Object[]{
                            rs.getString("kodepesanan"),
                            rs.getString("kodeproduk"),
                            rs.getString("kuantitas"),
                            });
                jTable1.setModel(tbl);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Gagal!" + e.getMessage());
                    }
            }   catch (SQLException ex) {
                    Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kodeproduk = new javax.swing.JTextField();
        batal = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        baru = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        hapus = new javax.swing.JButton();
        kodepesanan = new javax.swing.JTextField();
        simpan = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        kuantitas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kodeproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeprodukActionPerformed(evt);
            }
        });

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        ubah.setText("Ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        textSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSearchActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Pesanan");

        jLabel4.setText("Kode Produk");

        baru.setText("Baru");
        baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baruActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATA PRODUK TIAP PESANAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jButton7.setText("^");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setText("v");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Pesanan", "ID Pegawai", "Kuantitas"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        kodepesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodepesananActionPerformed(evt);
            }
        });

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jLabel5.setText("Kuantitas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cari)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addComponent(baru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(batal)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kodepesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kodepesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(kuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baru)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(batal)
                        .addComponent(simpan)
                        .addComponent(hapus)
                        .addComponent(ubah)
                        .addComponent(jButton6)
                        .addComponent(jButton7)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kodeprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeprodukActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(false);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        kuantitas.setEnabled(true);
        change = true;

    }//GEN-LAST:event_ubahActionPerformed

    private void textSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSearchActionPerformed

    private void baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baruActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(false);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        kodeproduk.setEnabled(true);
        kodepesanan.setEnabled(true);
        kuantitas.setEnabled(true);
        change = false;
    }//GEN-LAST:event_baruActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void kodepesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodepesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodepesananActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_cariActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
                   String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=HEEJAB; user=shania; password=inipassSQL;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {
           String sql = "Select * from produk_tiapPesanan where kodepesanan=? and kodeproduk=?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, kodepesanan.getText());
                pst.setString(2, kodeproduk.getText());
                ResultSet rs = pst.executeQuery();
            if (rs.next() && change == true) {

                    try {
                        sql = "UPDATE produk_tiapPesanan SET kuantitas=" + kuantitas.getText() +  " WHERE kodepesanan ='" + kodepesanan.getText() + "' and kodeproduk =" + kodeproduk.getText();
                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Berhasil diubah!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak berhasil diubah :( " + e.getMessage());
                    }
                    change = false;
                }
                else {
                    try {
                        sql = "INSERT INTO produk_tiapPesanan VALUES ('" +kodepesanan.getText()+ "','" + kodeproduk.getText() + "'," 
                               + kuantitas.getText() + ")";

                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
                    }
                }

            rs = st.executeQuery("SELECT * FROM produk_tiapPesanan");
            ResultSetMetaData rsmdt = rs.getMetaData();
            // menyimpan jumlah kolom
            int columns = rsmdt.getColumnCount();
            //object ini akan mengantarkan data ke Jtable
            DefaultTableModel dtm = new DefaultTableModel();
            Vector columns_name = new Vector();
            Vector data_rows = new Vector();
            
            for (int i = 1; i <= columns; i++) {
                columns_name.addElement(rsmdt.getColumnName(i));
            }
            dtm.setColumnIdentifiers(columns_name);
            
            while(rs.next()) {
                data_rows = new Vector();
                for (int j = 1; j <= columns; j++) {
                    data_rows.addElement(rs.getString(j));
                }
                dtm.addRow(data_rows);
            }
            // pass Default table Object into jTable1
            jTable1.setModel(dtm);

        } catch (SQLException e) {
            e.printStackTrace();
}
        
        baru.setEnabled(true);
        ubah.setEnabled(true);
        hapus.setEnabled(true);
        simpan.setEnabled(false);
        kodeproduk.setEnabled(false);
        kodepesanan.setEnabled(false);
        kuantitas.setEnabled(false);
    }//GEN-LAST:event_simpanActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(true);
        ubah.setEnabled(true);
        hapus.setEnabled(true);
        simpan.setEnabled(false);
        kodepesanan.setEnabled(false);
        kodeproduk.setEnabled(false);
        kuantitas.setEnabled(false);
        kodeproduk.setText("");
        kodepesanan.setText("");
        kuantitas.setText("");
        textSearch.setText("");
        change = false;
    }//GEN-LAST:event_batalActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        // UNTUK MENDISPLAY DATA DI TABEL KE TEXT FIELDS
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        kodepesanan.setText(model.getValueAt(selectedRowIndex, 0).toString());
        kodeproduk.setText(model.getValueAt(selectedRowIndex, 1).toString());
        kuantitas.setText(model.getValueAt(selectedRowIndex, 2).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
         // UNTUK HAPUSS
        try {
            String sql = "DELETE FROM produk_tiapPesanan WHERE kodepesanan='" + kodepesanan.getText() + "' AND kodeproduk='" + kodeproduk.getText() + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus! :)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak Berhasil Dihapus! :(" + e.getMessage());            
        }
        
        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=HEEJAB; user=shania; password=inipassSQL;" + "loginTimeout=30;";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {
        ResultSet rs = st.executeQuery("SELECT * FROM produk_tiapPesanan");
            ResultSetMetaData rsmdt = rs.getMetaData();
            // menyimpan jumlah kolom
            int columns = rsmdt.getColumnCount();
            //object ini akan mengantarkan data ke Jtable
            DefaultTableModel dtm = new DefaultTableModel();
            Vector columns_name = new Vector();
            Vector data_rows = new Vector();
            
            for (int i = 1; i <= columns; i++) {
                columns_name.addElement(rsmdt.getColumnName(i));
            }
            dtm.setColumnIdentifiers(columns_name);
            
            while(rs.next()) {
                data_rows = new Vector();
                for (int j = 1; j <= columns; j++) {
                    data_rows.addElement(rs.getString(j));
                }
                dtm.addRow(data_rows);
            }
            // pass Default table Object into jTable1
            jTable1.setModel(dtm);
        } catch (SQLException e) {
            e.printStackTrace();
}
    }//GEN-LAST:event_hapusActionPerformed

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
            java.util.logging.Logger.getLogger(ProdukTiapPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdukTiapPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdukTiapPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdukTiapPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdukTiapPesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baru;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JButton hapus;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kodepesanan;
    private javax.swing.JTextField kodeproduk;
    private javax.swing.JTextField kuantitas;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField textSearch;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
