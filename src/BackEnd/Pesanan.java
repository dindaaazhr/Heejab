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
public class Pesanan extends javax.swing.JFrame {

    public Pesanan() {
        initComponents();
        Connect();
        save.setEnabled(false);
        kodepesanan.setEnabled(false);
        idpegawai.setEnabled(false);
        kodeproduk.setEnabled(false);
        tanggal.setEnabled(false);
    }
    boolean change = false;
    
    Connection con = null;
    ResultSet rs = null;
    Statement st;
    
    public void Connect(){
//         ResultSet rs = null;
            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=heejab; user=dinda; password=123;" + "loginTimeout=30;";
            try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
//            
//            st = con.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            
            
            // UNTUK MENAMPILKAN DATA KE TABEL
            // perintah untuk pengembalian resultset
            rs = st.executeQuery("SELECT * FROM Pesanan");
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
            // pass Default table Object into jTable
            jTable1.setModel(dtm);

            // Menampilkan pop up berhasil atau tidaknya
            JOptionPane.showMessageDialog(null, "Berhasil ditampilkan :)");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Data" +  " " +e.getMessage());
        }
    }
        
    void cari(){
//        ResultSet rs = null;
           String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=heejab; user=dinda; password=123;" + "loginTimeout=30;";
            try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
                DefaultTableModel tbl = new DefaultTableModel();
                tbl.addColumn("KodePesanan");
                tbl.addColumn("Ps_idPegawai");
                tbl.addColumn("Ps_KodeProduk");
                tbl.addColumn("Tanggal");
                
                    try {
                        String pencarian = searchfield.getText();

//                        String sql = "SELECT * FROM Pembeli WHERE Username like '%" + pCari.getText() + "%'";
//                        st = con.createStatement();
                        String sql = "SELECT * FROM pesanan WHERE KodePesanan LIKE '%" + pencarian + "%' OR Ps_idPegawai LIKE '%" 
                                + pencarian + "%' OR Ps_KodeProduk LIKE '%" + pencarian + "%' OR Tanggal LIKE '%" + pencarian +"%'"; 


                        rs = st.executeQuery(sql);

                        while(rs.next()) {
                            tbl.addRow(new Object[]{
                                rs.getString("KodePesanan"),
                                rs.getString("Ps_idPegawai"),
                                rs.getString("Ps_KodeProduk"),
                                rs.getString("Tanggal"),
                            });
                            jTable1.setModel(tbl);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Gagal!" + e.getMessage());
                    }
            
        } catch (SQLException ex) {
                Logger.getLogger(Pesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    public void refreshAllFields(){
        try{
            if (!rs.isClosed()){
                kodepesanan.setText(rs.getString("KodePesanan"));
                idpegawai.setText(rs.getString("Ps_idPegawai"));
                kodeproduk.setText(rs.getString("Ps_KodeProduk"));   
                tanggal.setText(rs.getString("Tanggal"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void updateNavigationButtons(){
        try{
            if (!rs.isClosed()){
                first2.setEnabled(!rs.isFirst());
                prev2.setEnabled(!rs.isFirst());
                next3.setEnabled(!rs.isLast());
                last2.setEnabled(!rs.isLast());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pesanan.class.getName()).log(Level.SEVERE, null, ex);
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

        first1 = new javax.swing.JButton();
        prev1 = new javax.swing.JButton();
        next2 = new javax.swing.JButton();
        last1 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        kodepesanan = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        newbutton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        kembali2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        save = new javax.swing.JButton();
        idpegawai = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        kodeproduk = new javax.swing.JTextField();
        first2 = new javax.swing.JButton();
        prev2 = new javax.swing.JButton();
        next3 = new javax.swing.JButton();
        last2 = new javax.swing.JButton();
        searchfield = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jbutton = new javax.swing.JLabel();
        tanggal = new javax.swing.JTextField();

        first1.setText("<<");
        first1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first1ActionPerformed(evt);
            }
        });

        prev1.setText("<");
        prev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev1ActionPerformed(evt);
            }
        });

        next2.setText(">");
        next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next2ActionPerformed(evt);
            }
        });

        last1.setText(">>");
        last1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                last1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        delete.setText("Hapus");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        kodepesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodepesananActionPerformed(evt);
            }
        });

        cancel.setText("Batal");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel4.setText("ID Pegawai");

        newbutton.setText("Baru");
        newbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbuttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATA PESANAN");

        kembali2.setText("Beranda");
        kembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addGap(56, 56, 56)
                .addComponent(kembali2)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kembali2)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Pesanan", "ID Pegawai", "Kode Produk", "Tanggal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        idpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idpegawaiActionPerformed(evt);
            }
        });
        idpegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idpegawaiKeyReleased(evt);
            }
        });

        update.setText("Ubah");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Pesanan");

        jLabel5.setText("Kode Produk");

        kodeproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeprodukActionPerformed(evt);
            }
        });

        first2.setText("<<");
        first2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first2ActionPerformed(evt);
            }
        });

        prev2.setText("<");
        prev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prev2ActionPerformed(evt);
            }
        });

        next3.setText(">");
        next3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next3ActionPerformed(evt);
            }
        });

        last2.setText(">>");
        last2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                last2ActionPerformed(evt);
            }
        });

        searchfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchfieldActionPerformed(evt);
            }
        });

        search.setText("Cari");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jbutton.setText("Tanggal");

        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search)
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(first2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prev2)
                        .addGap(26, 26, 26)
                        .addComponent(next3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(last2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(newbutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancel))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(kodepesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kodepesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbutton))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newbutton)
                        .addComponent(next3)
                        .addComponent(prev2)
                        .addComponent(last2)
                        .addComponent(first2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancel)
                        .addComponent(save)
                        .addComponent(delete)
                        .addComponent(update)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void kodepesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodepesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodepesananActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        cari();
    }//GEN-LAST:event_searchActionPerformed

    private void searchfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfieldActionPerformed
       
    }//GEN-LAST:event_searchfieldActionPerformed

    private void newbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbuttonActionPerformed
        kodepesanan.setEnabled(false);
        idpegawai.setEnabled(true);
        kodeproduk.setEnabled(true);
        update.setEnabled(false);
        delete.setEnabled(false);
        newbutton.setEnabled(false);
        tanggal.setEnabled(false);
    }//GEN-LAST:event_newbuttonActionPerformed

    private void idpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idpegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idpegawaiActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update.setEnabled(false);
        delete.setEnabled(false);
        newbutton.setEnabled(false);
        save.setEnabled(true);
        idpegawai.setEnabled(true);
        kodepesanan.setEnabled(false);
        kodeproduk.setEnabled(true);
        change = true;
    }//GEN-LAST:event_updateActionPerformed

    private void kodeprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeprodukActionPerformed

    private void first1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first1ActionPerformed

    private void prev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prev1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prev1ActionPerformed

    private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_next2ActionPerformed

    private void last1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_last1ActionPerformed

    private void first2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first2ActionPerformed
        
    }//GEN-LAST:event_first2ActionPerformed

    private void prev2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prev2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_prev2ActionPerformed

    private void next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_next3ActionPerformed

    private void last2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_last2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=heejab; user=dinda; password=123;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
           String sql = "Select * from pesanan where kodepesanan=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,kodepesanan.getText());
                ResultSet rs = pst.executeQuery();
            if (rs.next() && change == true) {
                    try {
                        
                        sql = "UPDATE pesanan SET Ps_idPegawai='" + idpegawai.getText() + "',Ps_KodeProduk='" + kodeproduk.getText()  +"' where kodepesanan =" + kodepesanan.getText(); 
                        pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Berhasil Diedit!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak berhasil diedit :( " + e.getMessage());
                    } 
                    change = false;
                }
            else {
                    try {
                        sql = "INSERT INTO pesanan VALUES (" + idpegawai.getText() + "," + kodeproduk.getText() + ", GETDATE())";

                        pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, "ID Pegawai atau Kode Produk Tidak Ditemukan ");
                    }
                }

            rs = st.executeQuery("SELECT * FROM pesanan");
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
        
        newbutton.setEnabled(true);
        update.setEnabled(true);
        delete.setEnabled(true);
        save.setEnabled(false);
        idpegawai.setEnabled(false);
        kodeproduk.setEnabled(false);
        kodepesanan.setEnabled(false);
        tanggal.setEnabled(false);
    }//GEN-LAST:event_saveActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        idpegawai.setText("");
        kodepesanan.setText("");
        kodeproduk.setText("");
        searchfield.setText("");
        tanggal.setText("");
        update.setEnabled(true);
        delete.setEnabled(true);
        newbutton.setEnabled(true);
        delete.setEnabled(true);
        save.setEnabled(false);
        idpegawai.setEnabled(false);
        change = false;
    }//GEN-LAST:event_cancelActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        kodepesanan.setText(model.getValueAt(selectedRowIndex, 0).toString());
        idpegawai.setText(model.getValueAt(selectedRowIndex, 1).toString());
        kodeproduk.setText(model.getValueAt(selectedRowIndex, 2).toString());
        tanggal.setText(model.getValueAt(selectedRowIndex, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void idpegawaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idpegawaiKeyReleased
        if(idpegawai.getText().length() > 0){
            save.setEnabled(true);
        } else{
            save.setEnabled(false);
        }
    }//GEN-LAST:event_idpegawaiKeyReleased

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=heejab; user=dinda; password=123;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
                try {
                    String sql = "DELETE FROM pesanan WHERE kodepesanan='" + kodepesanan.getText() + "'";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.execute();
                    ResultSet rs = st.executeQuery("SELECT * FROM pesanan");
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
                    JOptionPane.showMessageDialog(null, "Berhasil Dihapus! :)");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tidak Berhasil Dihapus! :(" + e.getMessage());
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalActionPerformed

    private void kembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali2ActionPerformed
        // TODO add your handling code here:
        Home field = new Home();
        field.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_kembali2ActionPerformed

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
            java.util.logging.Logger.getLogger(Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton delete;
    private javax.swing.JButton first1;
    private javax.swing.JButton first2;
    private javax.swing.JTextField idpegawai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jbutton;
    private javax.swing.JButton kembali2;
    private javax.swing.JTextField kodepesanan;
    private javax.swing.JTextField kodeproduk;
    private javax.swing.JButton last1;
    private javax.swing.JButton last2;
    private javax.swing.JButton newbutton;
    private javax.swing.JButton next2;
    private javax.swing.JButton next3;
    private javax.swing.JButton prev1;
    private javax.swing.JButton prev2;
    private javax.swing.JButton save;
    private javax.swing.JButton search;
    private javax.swing.JTextField searchfield;
    private javax.swing.JTextField tanggal;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
