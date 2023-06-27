/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adinda Az-Zahra
 */
public class Pembeli extends javax.swing.JFrame {

    public Pembeli() {
        initComponents();
        Connect();
        simpan.setEnabled(false);
        pUsername.setEnabled(false);
        pNama.setEnabled(false);
        pPassword.setEnabled(false);
        pNotelp.setEnabled(false);
        bLaki.setEnabled(false);
        bPerempuan.setEnabled(false);
        pTanggal.setEnabled(false);
    }
    
  
        // CONNECTION --
    Connection con = null;
    ResultSet rs = null;
    Statement st;
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public void Connect(){
        try{
            Class.forName(driver);
            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=Heejab; user=dinda; password=123;" +
                "loginTimeout=30;";
            
            con = DriverManager.getConnection(connectionUrl);
            st = con.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            
            
            // UNTUK MENAMPILKAN DATA KE TABEL
            // perintah untuk pengembalian resultset
            rs = st.executeQuery("SELECT * FROM Pembeli");
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
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Username");
        tbl.addColumn("Password");
        tbl.addColumn("Nama");
        tbl.addColumn("NoTelp");
        tbl.addColumn("JenisKelamin");
        tbl.addColumn("TanggalLahir");
        
        try {
            String pencarian = pCari.getText();
            
//            String sql = "SELECT * FROM Pembeli WHERE Username like '%" + pCari.getText() + "%'";
            
            String sql = "SELECT * FROM Pembeli WHERE Username LIKE '%" + pencarian + "%' OR Nama LIKE '%" 
                    + pencarian + "%' OR NoTelp LIKE '%" + pencarian + "%' OR JenisKelamin LIKE '%" 
                    + pencarian + "%' OR TanggalLahir LIKE '%" +  pencarian + "%'";
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Nama"),
                    rs.getString("NoTelp"),
                    rs.getString("JenisKelamin"),
                    rs.getString("TanggalLahir"),
                });
                jTable1.setModel(tbl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "KOK GABISA??" + e.getMessage());
        }
    }
    
        public void refreshAllFields(){
        try{
            if (!rs.isClosed()){
                pUsername.setText(rs.getString("Username"));
                pPassword.setText(rs.getString("Password"));
                pNama.setText(rs.getString("Nama"));
                pNotelp.setText(rs.getString("NoTelp"));
//                pJenisKelamin.setText(rs.getString("JenisKelamin"));
                pTanggal.setText(rs.getString("TanggalLahir"));      
            }
        } catch (SQLException ex){
            Logger.getLogger(Pembeli.class.getName()).log(Level.SEVERE,null,ex );
        }
        
    }
    
        public void updateNavigationButtons(){
        try{
            if (!rs.isClosed()){
                first.setEnabled(!rs.isFirst());
                prev.setEnabled(!rs.isFirst());
                next.setEnabled(!rs.isLast());
                last.setEnabled(!rs.isLast());
            }
        } catch (SQLException ex){
            Logger.getLogger(Pembeli.class.getName()).log(Level.SEVERE,null,ex );
        }
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        pNotelp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bLaki = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        bPerempuan = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pUsername = new javax.swing.JTextField();
        pNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        batal = new javax.swing.JButton();
        baru = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        pPassword = new javax.swing.JPasswordField();
        pTanggal = new javax.swing.JTextField();
        next = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        last = new javax.swing.JButton();
        first = new javax.swing.JButton();
        kembali2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        simpan.setText("Simpan");
        simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simpanMouseClicked(evt);
            }
        });
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        edit.setText("Ubah");
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusMouseClicked(evt);
            }
        });
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        jLabel3.setText("Password");

        buttonGroup1.add(bLaki);
        bLaki.setText("Laki-laki");

        jLabel4.setText("Nama");

        buttonGroup1.add(bPerempuan);
        bPerempuan.setText("Perempuan");

        jLabel6.setText("No Telp.");

        jLabel7.setText("Jenis Kelamin");

        jLabel8.setText("Tanggal Lahir");

        pUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pUsernameKeyReleased(evt);
            }
        });

        jLabel2.setText("Username");

        jButton5.setText("Cari");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        pCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pCariKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password", "Nama", "NoTelp", "Jenis Kelamin", "Tanggal Lahir"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        baru.setText("Baru");
        baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baruActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DATA PEMBELI");

        pPassword.setText("jPasswo");
        pPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pPasswordActionPerformed(evt);
            }
        });
        pPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pPasswordKeyReleased(evt);
            }
        });

        pTanggal.setText("0000-00-00");

        next.setText(">");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        prev.setText("<");
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });

        last.setText(">>");
        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

        first.setText("<<");
        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        kembali2.setText("Beranda");
        kembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(first)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prev)
                                .addGap(26, 26, 26)
                                .addComponent(next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(baru)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(batal))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(235, 235, 235)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pNama)
                                            .addComponent(pUsername)
                                            .addComponent(pPassword)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(235, 235, 235)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pNotelp)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bLaki, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bPerempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 165, Short.MAX_VALUE))
                                            .addComponent(pTanggal)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(kembali2)))
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton5)
                                        .addGap(18, 18, 18)
                                        .addComponent(pCari, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(80, 80, 80)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(kembali2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pNotelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(bLaki)
                    .addComponent(bPerempuan))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(pCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(batal)
                    .addComponent(hapus)
                    .addComponent(edit)
                    .addComponent(baru)
                    .addComponent(next)
                    .addComponent(prev)
                    .addComponent(last)
                    .addComponent(first))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_simpanMouseClicked

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
               String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=Heejab; user=dinda; password=123;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {
           String sql = "Select * from Pembeli where username=?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, pUsername.getText());
                ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                    String jk = null;
                    if(bLaki.isSelected()) {
                        jk = "Laki-laki";
                    } else if(bPerempuan.isSelected()) {
                        jk = "Perempuan";
                    }

                    try {
                        sql = "UPDATE Pembeli SET Username='" + pUsername.getText() + "',Password='" + pPassword.getText()
                                + "',Nama='" + pNama.getText() + "',JenisKelamin='" + jk + "',tanggalLahir='" + pTanggal.getText()
                                +"' where username='" + pUsername.getText() + "'";

                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Berhasil Diedit!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak berhasil diedit :( " + e.getMessage());
                    }
                }
                else {
                    String jk = null;
                    if(bLaki.isSelected()) {
                        jk = "Laki-laki";
                    } else if(bPerempuan.isSelected()) {
                        jk = "Perempuan";
                    }

                    try {
                        sql = "INSERT INTO Pembeli VALUES ('" + pUsername.getText()+ "','" + pPassword.getText() + "','" 
                               + pNama.getText() + "','" + pNotelp.getText() + "','"
                               + jk + "','" + pTanggal.getText() + "')";

                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
                    }
                }

            rs = st.executeQuery("SELECT * FROM Pembeli");
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
        edit.setEnabled(true);
        hapus.setEnabled(true);
        simpan.setEnabled(false);
        pUsername.setEnabled(false);
        pNama.setEnabled(false);
        pPassword.setEnabled(false);
        pNotelp.setEnabled(false);
        bLaki.setEnabled(false);
        bPerempuan.setEnabled(false);
        pTanggal.setEnabled(false);
    }//GEN-LAST:event_simpanActionPerformed

    private void pCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pCariKeyReleased
        // TODO add your handling code here:
        
        // UNTUK SEARCH TANPA BUTTON SEARCH awkwk
//        String pencarian = pCari.getText();
//        try {
//            Class.forName(driver);
//            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=Heejab; user=dinda; password=123;" +
//                "loginTimeout=30;";
//            
//            con = DriverManager.getConnection(connectionUrl);
//            
//            String sql = "SELECT * FROM Pembeli WHERE Username LIKE '%" + pencarian + "%' OR Nama LIKE '%" 
//                    + pencarian + "%' OR NoTelp LIKE '%" + pencarian + "%' OR JenisKelamin LIKE '%" 
//                    + pencarian + "%' OR TanggalLahir LIKE '%" +  pencarian + "%'";
//        } catch (Exception e) {
//        }

//        try {
//            Class.forName(driver);
//            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=Heejab; user=dinda; password=123;" +
//                "loginTimeout=30;";
//            
//            con = DriverManager.getConnection(connectionUrl);
//            
//        } catch (Exception e) {
//          JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
//
//        }
        
        
    }//GEN-LAST:event_pCariKeyReleased

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(true);
        edit.setEnabled(true);
        hapus.setEnabled(true);
        simpan.setEnabled(false);
        pUsername.setEnabled(false);
        pNama.setEnabled(false);
        pPassword.setEnabled(false);
        pNotelp.setEnabled(false);
        bLaki.setEnabled(false);
        bPerempuan.setEnabled(false);
        pTanggal.setEnabled(false);
        pUsername.setText("");
        pNama.setText("");
        pPassword.setText("");
        pNotelp.setText("");
        pTanggal.setText("");
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_batalActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
    
      
    }//GEN-LAST:event_hapusActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        // UNTUK MENDISPLAY DATA DI TABEL KE TEXT FIELDS
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        pUsername.setText(model.getValueAt(selectedRowIndex, 0).toString());
        pPassword.setText(model.getValueAt(selectedRowIndex, 1).toString());
        pNama.setText(model.getValueAt(selectedRowIndex, 2).toString());
        pNotelp.setText(model.getValueAt(selectedRowIndex, 3).toString());
        String jeniskelamin = model.getValueAt(selectedRowIndex, 4).toString();
        if(jeniskelamin.equals("Laki - Laki")) {
            bLaki.setSelected(true);
        }
        else {
            bPerempuan.setSelected(true);
        }
        pTanggal.setText(model.getValueAt(selectedRowIndex, 5).toString());
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_editMouseClicked

    private void hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseClicked
        // TODO add your handling code here:
        
        // UNTUK HAPUSS
        try {
            String sql = "DELETE FROM Pembeli WHERE Username='" + pUsername.getText() + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus! :)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak Berhasil Dihapus! :(" + e.getMessage());            
        }
        
        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=Heejab; user=dinda; password=123;" + "loginTimeout=30;";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {
        ResultSet rs = st.executeQuery("SELECT * FROM Pembeli");
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
    }//GEN-LAST:event_hapusMouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        
        // UNTUK SEARCH/CARIII
        cari();
//        
//         String pencarian = pCari.getText();
//        try {
//            Class.forName(driver);
//            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=Heejab; user=dinda; password=123;" +
//                "loginTimeout=30;";
//            
//            con = DriverManager.getConnection(connectionUrl);
//            
//            String sql = "SELECT * FROM Pembeli WHERE Username LIKE '%" + pencarian + "%' OR Nama LIKE '%" 
//                    + pencarian + "%' OR NoTelp LIKE '%" + pencarian + "%' OR JenisKelamin LIKE '%" 
//                    + pencarian + "%' OR TanggalLahir LIKE '%" +  pencarian + "%'";
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
    }//GEN-LAST:event_jButton5MouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        baru.setEnabled(false);
        edit.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        pNama.setEnabled(true);
        pPassword.setEnabled(true);
        pNotelp.setEnabled(true);
        bLaki.setEnabled(true);
        bPerempuan.setEnabled(true);
        pTanggal.setEnabled(true);
        pUsername.setEnabled(false);
    }//GEN-LAST:event_editActionPerformed

    private void baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baruActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(false);
        edit.setEnabled(false);
        hapus.setEnabled(false);
        pUsername.setEnabled(true);
        pNama.setEnabled(true);
        pPassword.setEnabled(true);
        pNotelp.setEnabled(true);
        bLaki.setEnabled(true);
        bPerempuan.setEnabled(true);
        pTanggal.setEnabled(true);
    }//GEN-LAST:event_baruActionPerformed

    private void pUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pUsernameKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pUsernameKeyReleased

    private void pPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pPasswordKeyReleased
        // TODO add your handling code here:
        if(pPassword.getText().length() > 0){
            simpan.setEnabled(true);
        } else{
            simpan.setEnabled(false);
        }
    }//GEN-LAST:event_pPasswordKeyReleased

    private void pPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pPasswordActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isClosed()){
                rs.next();
                refreshAllFields();
                updateNavigationButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pembeli.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_nextActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isClosed()){
                rs.previous();
                refreshAllFields();
                updateNavigationButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pembeli.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_prevActionPerformed

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        // TODO add your handling code here:
        try {
            rs.last();
            pUsername.setText(rs.getString("Username"));
            pPassword.setText(rs.getString("Password"));
            pNama.setText(rs.getString("Nama"));
            pNotelp.setText(rs.getString("NoTelp"));
            //            pJenisKelamin.setText(rs.getString("JenisKelamin"));
            pTanggal.setText(rs.getString("TanggalLahir"));
        } catch (SQLException ex) {
            Logger.getLogger(Pembeli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lastActionPerformed

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        // TODO add your handling code here:
        try {
            rs.first();
            pUsername.setText(rs.getString("Username"));
            pPassword.setText(rs.getString("Password"));
            pNama.setText(rs.getString("Nama"));
            pNotelp.setText(rs.getString("NoTelp"));
            //            pJenisKelamin.setText(rs.getString("JenisKelamin"));
            pTanggal.setText(rs.getString("TanggalLahir"));
        } catch (SQLException ex) {
            Logger.getLogger(Pembeli.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_firstActionPerformed

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
            java.util.logging.Logger.getLogger(Pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bLaki;
    private javax.swing.JRadioButton bPerempuan;
    private javax.swing.JButton baru;
    private javax.swing.JButton batal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton edit;
    private javax.swing.JButton first;
    private javax.swing.JButton hapus;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton kembali2;
    private javax.swing.JButton last;
    private javax.swing.JButton next;
    private javax.swing.JTextField pCari;
    private javax.swing.JTextField pNama;
    private javax.swing.JTextField pNotelp;
    private javax.swing.JPasswordField pPassword;
    private javax.swing.JTextField pTanggal;
    private javax.swing.JTextField pUsername;
    private javax.swing.JButton prev;
    private javax.swing.JButton simpan;
    // End of variables declaration//GEN-END:variables
}
