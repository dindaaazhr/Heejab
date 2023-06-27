/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd_User;

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
 * @author safirmagenta22
 */
public class Produk extends javax.swing.JFrame {

    /**
     * Creates new form Produk
     */
    public Produk() {
        initComponents();
        Connect();
        simpan.setEnabled(false);
        kodeproduk.setEnabled(false);
        bahan.setEnabled(false);
        harga.setEnabled(false);
        stok.setEnabled(false);
        ukuran.setEnabled(false);
        motif.setEnabled(false);
        tipeP.setEnabled(false);
        tipeIr.setEnabled(false);
        tipeIn.setEnabled(false);
        pashmina.setEnabled(false);
        segiempat.setEnabled(false);
        inner.setEnabled(false);
        instan.setEnabled(false);
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
            rs = st.executeQuery("SELECT * FROM produk");
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
    
    //void cari(){
        
//           String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=Bismillah; user=ruridesfianti; password=321;" + "loginTimeout=30;";
//            try (Connection con = DriverManager.getConnection(connectionUrl);
//                Statement st = con.createStatement();) {
//
//        DefaultTableModel tbl = new DefaultTableModel();
//        tbl.addColumn("KodeProduk");
//        tbl.addColumn("Kategori");
//        tbl.addColumn("Bahan");
//        tbl.addColumn("Harga");
//        tbl.addColumn("Stok");
//        tbl.addColumn("Ukuran");
//        tbl.addColumn("Motif");
//        tbl.addColumn("TipePashmina");
//        tbl.addColumn("TipeInner");
//        tbl.addColumn("TipeInstan");
//        
//        try {
//            String pencarian = cari.getText();
//            
//            String sql = "SELECT * FROM produk WHERE KodeProduk LIKE '%" + pencarian + "%' OR Kategori LIKE '%" 
//                    + pencarian + "%' OR Bahan LIKE '%" + pencarian + "%' OR Harga LIKE '%" 
//                    + pencarian + "%' OR Stok LIKE '%" +  pencarian + "%' OR Ukuran LIKE '%" + pencarian + "%' OR Motif LIKE '%"
//                    + pencarian + "%' OR TipePashmina LIKE '%" + pencarian + "%' OR TipeInner LIKE '%"
//                    + pencarian + "%' OR TipeInstan LIKE '%" +  pencarian + "%'";
//            
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            
//            while(rs.next()) {
//                tbl.addRow(new Object[]{
//                    rs.getString("KodeProduk"),
//                    rs.getString("Kategori"),
//                    rs.getString("Bahan"),
//                    rs.getString("Harga"),
//                    rs.getString("Stok"),
//                    rs.getString("Ukuran"),
//                    rs.getString("Motif"),
//                    rs.getString("TipePashmina"),
//                    rs.getString("TipeInner"),
//                    rs.getString("TipeInstan"),
//                });
//                jTable1.setModel(tbl);
//            }
//        } catch (Exception e){
//             JOptionPane.showMessageDialog(null, "Gagal!" + e.getMessage());
//        }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "KOK GABISA??" + e.getMessage());
//        }
    //}

    void cari(){
//        ResultSet rs = null;
            String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=Heejab; user=dinda; password=123;" + "loginTimeout=30;";
            try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
                DefaultTableModel tbl = new DefaultTableModel();
                tbl.addColumn("KodeProduk");
                tbl.addColumn("Kategori");
                tbl.addColumn("Bahan");
                tbl.addColumn("Harga");
                tbl.addColumn("Stok");
                tbl.addColumn("Ukuran");
                tbl.addColumn("Motif");
                tbl.addColumn("TipePashmina");
                tbl.addColumn("TipeInner");
                tbl.addColumn("TipeInstan");
        
                    try {
                        String pencarian = textSearch.getText();

                    String sql = "SELECT * FROM produk WHERE KodeProduk LIKE '%" + pencarian + "%' OR Kategori LIKE '%" 
                    + pencarian + "%' OR Bahan LIKE '%" + pencarian + "%' OR Harga LIKE '%" 
                    + pencarian + "%' OR Stok LIKE '%" +  pencarian + "%' OR Ukuran LIKE '%" + pencarian + "%' OR Motif LIKE '%"
                    + pencarian + "%' OR TipePashmina LIKE '%" + pencarian + "%' OR TipeInner LIKE '%"
                    + pencarian + "%' OR TipeInstan LIKE '%" +  pencarian + "%'";


                        rs = st.executeQuery(sql);

                        while(rs.next()) {
                        tbl.addRow(new Object[]{
                            rs.getString("KodeProduk"),
                            rs.getString("Kategori"),
                            rs.getString("Bahan"),
                            rs.getString("Harga"),
                            rs.getString("Stok"),
                            rs.getString("Ukuran"),
                            rs.getString("Motif"),
                            rs.getString("TipePashmina"),
                            rs.getString("TipeInner"),
                            rs.getString("TipeInstan"),
                            });
                jTable1.setModel(tbl);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Gagal!" + e.getMessage());
                    }
            }   catch (SQLException ex) {
                    Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kodeproduk = new javax.swing.JTextField();
        bahan = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        stok = new javax.swing.JTextField();
        ukuran = new javax.swing.JTextField();
        textSearch = new javax.swing.JTextField();
        motif = new javax.swing.JTextField();
        tipeP = new javax.swing.JTextField();
        tipeIr = new javax.swing.JTextField();
        tipeIn = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        batal = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        baru = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        pashmina = new javax.swing.JRadioButton();
        segiempat = new javax.swing.JRadioButton();
        inner = new javax.swing.JRadioButton();
        instan = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA PRODUK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel3.setText("Bahan");

        jLabel4.setText("Kategori");

        jLabel5.setText("Harga");

        jLabel2.setText("Kode Produk");

        jLabel6.setText("Stok");

        jLabel7.setText("Ukuran");

        jLabel8.setText("Motif");

        jLabel9.setText("Tipe Pashmina");

        jLabel10.setText("Tipe Inner");

        jLabel11.setText("Tipe Instan");

        kodeproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeprodukActionPerformed(evt);
            }
        });

        bahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bahanActionPerformed(evt);
            }
        });

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokActionPerformed(evt);
            }
        });

        ukuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ukuranActionPerformed(evt);
            }
        });

        textSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSearchActionPerformed(evt);
            }
        });

        motif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motifActionPerformed(evt);
            }
        });

        tipeP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipePActionPerformed(evt);
            }
        });

        tipeIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipeIrActionPerformed(evt);
            }
        });

        tipeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipeInActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Produk", "Kategori", "Bahan", "Harga", "Stok", "Size", "Motif", "Tipe Pashmina", "Tipe Inner", "Tipe Instan"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusMouseClicked(evt);
            }
        });

        baru.setText("Baru");
        baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baruActionPerformed(evt);
            }
        });

        ubah.setText("Ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        jButton6.setText("v");

        jButton7.setText("^");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        cari.setText("Search");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        buttonGroup1.add(pashmina);
        pashmina.setText("Pashmina");
        pashmina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pashminaActionPerformed(evt);
            }
        });

        buttonGroup1.add(segiempat);
        segiempat.setText("Segi Empat");
        segiempat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segiempatActionPerformed(evt);
            }
        });

        buttonGroup1.add(inner);
        inner.setText("Inner Hijab");
        inner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                innerActionPerformed(evt);
            }
        });

        buttonGroup1.add(instan);
        instan.setText("Hijab Instan");
        instan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cari))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(segiempat, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pashmina, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(inner, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(instan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(stok))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(harga))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(bahan, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(motif))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tipeP))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tipeIr))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tipeIn))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(43, 43, 43)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(baru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(batal)
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(motif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tipeP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tipeIr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tipeIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(segiempat)
                            .addComponent(inner)
                            .addComponent(instan)
                            .addComponent(pashmina))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(bahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kodeprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeprodukActionPerformed

    private void bahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bahanActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokActionPerformed

    private void ukuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ukuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ukuranActionPerformed

    private void textSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSearchActionPerformed

    private void motifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motifActionPerformed

    private void tipePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipePActionPerformed

    private void tipeIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipeIrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipeIrActionPerformed

    private void tipeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipeInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipeInActionPerformed

    private void baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baruActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(false);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        bahan.setEnabled(true);
        harga.setEnabled(true);
        stok.setEnabled(true);
        ukuran.setEnabled(true);
        motif.setEnabled(true);
        tipeP.setEnabled(true);
        tipeIr.setEnabled(true);
        tipeIn.setEnabled(true);
        pashmina.setEnabled(true);
        segiempat.setEnabled(true);
        inner.setEnabled(true);
        instan.setEnabled(true);
    }//GEN-LAST:event_baruActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(false);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        simpan.setEnabled(true);
        bahan.setEnabled(true);
        harga.setEnabled(true);
        stok.setEnabled(true);
        ukuran.setEnabled(true);
        motif.setEnabled(true);
        tipeP.setEnabled(true);
        tipeIr.setEnabled(true);
        tipeIn.setEnabled(true);
        pashmina.setEnabled(true);
        segiempat.setEnabled(true);
        inner.setEnabled(true);
        instan.setEnabled(true);
    }//GEN-LAST:event_ubahActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        // UNTUK SEARCH/CARIII
        cari();
    }//GEN-LAST:event_cariActionPerformed

    private void pashminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pashminaActionPerformed
        // TODO add your handling code here:
        motif.setEnabled(false);
        tipeP.setEnabled(true);
        tipeIr.setEnabled(false);
        tipeIn.setEnabled(false);
    }//GEN-LAST:event_pashminaActionPerformed

    private void innerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_innerActionPerformed
        // TODO add your handling code here:
        motif.setEnabled(false);
        tipeP.setEnabled(false);
        tipeIr.setEnabled(true);
        tipeIn.setEnabled(false);
    }//GEN-LAST:event_innerActionPerformed

    private void instanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instanActionPerformed
        // TODO add your handling code here:
        motif.setEnabled(false);
        tipeP.setEnabled(false);
        tipeIr.setEnabled(false);
        tipeIn.setEnabled(true);
    }//GEN-LAST:event_instanActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
                   String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=Heejab; user=dinda; password=123;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {
           String sql = "Select * from produk where KodeProduk=?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, kodeproduk.getText());
                ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                    String kat = null;
                    if(pashmina.isSelected()) {
                        kat = "Pashmina";
                    } else if(segiempat.isSelected()) {
                        kat = "Segi Empat";
                    } else if(inner.isSelected()) {
                        kat = "Inner Hijab";
                    } else if(instan.isSelected()) {
                        kat = "Hijab Instan";
                    }

                    try {
                        sql = "UPDATE produk SET Kategori='" + kat+ "',Bahan='" + bahan.getText()
                                + "',Harga=" + harga.getText() + ",Stok=" + stok.getText() + ",Ukuran='" + ukuran.getText()
                                + "',Motif='" + motif.getText() + "',TipePashmina='" + tipeP.getText() + "',TipeInner='" + tipeIr.getText()
                                + "',TipeInstan='" + tipeIn.getText()
                                +"' where KodeProduk='" + kodeproduk.getText() + "'";

                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Berhasil Diedit!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak berhasil diedit :( " + e.getMessage());
                    }
                }
                else {
                    String kat = null;
                    if(pashmina.isSelected()) {
                        kat = "Pashmina";
                    } else if(segiempat.isSelected()) {
                        kat = "Segi Empat";
                    } else if(inner.isSelected()) {
                        kat = "Inner Hijab";
                    } else if(instan.isSelected()) {
                        kat = "Hijab Instan";
                    }

                    try {
                        sql = "INSERT INTO produk VALUES ('" +kat+ "','" + bahan.getText() + "'," 
                               + harga.getText() + "," + stok.getText() + ",'"
                               + ukuran.getText() + "','" + motif.getText() + "','" + tipeP.getText() 
                                + "','" + tipeIr.getText() + "','" + tipeIn.getText()
                                + "')";

                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
                    }
                }

            rs = st.executeQuery("SELECT * FROM produk");
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
        bahan.setEnabled(false);
        harga.setEnabled(false);
        stok.setEnabled(false);
        ukuran.setEnabled(false);
        motif.setEnabled(false);
        tipeP.setEnabled(false);
        tipeIr.setEnabled(false);
        tipeIn.setEnabled(false);
        pashmina.setEnabled(false);
        segiempat.setEnabled(false);
        inner.setEnabled(false);
        instan.setEnabled(false);

    }//GEN-LAST:event_simpanActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        baru.setEnabled(true);
        ubah.setEnabled(true);
        hapus.setEnabled(true);
        simpan.setEnabled(false);
        bahan.setEnabled(false);
        harga.setEnabled(false);
        stok.setEnabled(false);
        ukuran.setEnabled(false);
        motif.setEnabled(false);
        tipeP.setEnabled(false);
        tipeIr.setEnabled(false);
        tipeIn.setEnabled(false);
        pashmina.setEnabled(false);
        segiempat.setEnabled(false);
        inner.setEnabled(false);
        instan.setEnabled(false);
        kodeproduk.setText("");
        buttonGroup1.clearSelection();
        bahan.setText("");
        harga.setText("");
        stok.setText("");
        ukuran.setText("");
        motif.setText("");
        tipeP.setText("");
        tipeIr.setText("");
        tipeIn.setText("");

    }//GEN-LAST:event_batalActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         
        // UNTUK MENDISPLAY DATA DI TABEL KE TEXT FIELDS
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        kodeproduk.setText(model.getValueAt(selectedRowIndex, 0).toString());
        String kategori = model.getValueAt(selectedRowIndex, 1).toString();
        if(kategori.equals("Pashmina")) {
            pashmina.setSelected(true);
            segiempat.setSelected(false);
            inner.setSelected(false);
            instan.setSelected(false);
        }
        else if(kategori.equals("Segi Empat")){
            segiempat.setSelected(true);
            pashmina.setSelected(false);
            inner.setSelected(false);
            instan.setSelected(false);
        } else if(kategori.equals("Inner Hijab")){
            inner.setSelected(true);
            pashmina.setSelected(false);
            segiempat.setSelected(false);
            instan.setSelected(false);
        } else if(kategori.equals("Hijab Instan")){
            instan.setSelected(true);
            pashmina.setSelected(false);
            inner.setSelected(false);
            segiempat.setSelected(false);
        }
        bahan.setText(model.getValueAt(selectedRowIndex, 2).toString());
        harga.setText(model.getValueAt(selectedRowIndex, 3).toString());
        stok.setText(model.getValueAt(selectedRowIndex, 4).toString());
        ukuran.setText(model.getValueAt(selectedRowIndex, 5).toString());
        motif.setText(model.getValueAt(selectedRowIndex, 6).toString());
        tipeP.setText(model.getValueAt(selectedRowIndex, 7).toString());
        tipeIr.setText(model.getValueAt(selectedRowIndex, 8).toString());
        tipeIn.setText(model.getValueAt(selectedRowIndex, 9).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseClicked
        // TODO add your handling code here:
        
        // UNTUK HAPUSS
        try {
            String sql = "DELETE FROM produk WHERE KodeProduk=" + kodeproduk.getText();
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
        ResultSet rs = st.executeQuery("SELECT * FROM produk");
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

    private void segiempatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segiempatActionPerformed
        // TODO add your handling code here:
        motif.setEnabled(true);
        tipeP.setEnabled(false);
        tipeIr.setEnabled(false);
        tipeIn.setEnabled(false);
    }//GEN-LAST:event_segiempatActionPerformed

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
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Produk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bahan;
    private javax.swing.JButton baru;
    private javax.swing.JButton batal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cari;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JRadioButton inner;
    private javax.swing.JRadioButton instan;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kodeproduk;
    private javax.swing.JTextField motif;
    private javax.swing.JRadioButton pashmina;
    private javax.swing.JRadioButton segiempat;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField stok;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField tipeIn;
    private javax.swing.JTextField tipeIr;
    private javax.swing.JTextField tipeP;
    private javax.swing.JButton ubah;
    private javax.swing.JTextField ukuran;
    // End of variables declaration//GEN-END:variables
}
