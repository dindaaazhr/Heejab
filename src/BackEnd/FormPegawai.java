package BackEnd;

//import java.sql.Connection;

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

//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import javax.swing.JOptionPane;
//import java.sql.ResultSet;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Ruri
 */
public class FormPegawai extends javax.swing.JFrame {
    
    public FormPegawai() {
        initComponents();
        Connect();
        save.setEnabled(false);
        idpegawai.setEnabled(false);
        nama.setEnabled(false);
        alamat.setEnabled(false);
        perempuan.setEnabled(false);
        laki.setEnabled(false);
        tanggallahir.setEnabled(false);
        gaji.setEnabled(false);
        nomorrekening.setEnabled(false);
        packager.setEnabled(false);
        admin.setEnabled(false);
        jumlahboxpegawai.setEnabled(false);
        kategoriadmin.setEnabled(false);
    }
        
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
            rs = st.executeQuery("SELECT * FROM pegawai");
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
                tbl.addColumn("idPegawai");
                tbl.addColumn("Nama");
                tbl.addColumn("Alamat");
                tbl.addColumn("JenisKelamin");
                tbl.addColumn("TanggalLahir");
                tbl.addColumn("Gaji");
                tbl.addColumn("NomorRekening");
                tbl.addColumn("TipePegawai");
                tbl.addColumn("JumlahBoxPegawai");
                tbl.addColumn("KategoriAdmin");
        
                    try {
                        String pencarian = searchfield.getText();

            //            String sql = "SELECT * FROM Pembeli WHERE Username like '%" + pCari.getText() + "%'";
            //            st = con.createStatement();
                        String sql = "SELECT * FROM pegawai WHERE idPegawai LIKE '%" + pencarian + "%' OR Nama LIKE '%" 
                                + pencarian + "%' OR Alamat LIKE '%" + pencarian + "%' OR JenisKelamin LIKE '%" 
                                + pencarian + "%' OR Tanggallahir LIKE '%" +  pencarian + "%' OR Gaji LIKE '%" + pencarian + "%' OR NomorRekening LIKE '%" + pencarian + "%' OR TipePegawai LIKE '%" + pencarian + "%' OR JumlahBoxPegawai LIKE '%" + pencarian + "%' OR KategoriAdmin LIKE '%" + pencarian + "%'"; 


                        rs = st.executeQuery(sql);

                        while(rs.next()) {
                            tbl.addRow(new Object[]{
                                rs.getString("idPegawai"),
                                rs.getString("Nama"),
                                rs.getString("Alamat"),
                                rs.getString("JenisKelamin"),
                                rs.getString("TanggalLahir"),
                                rs.getString("Gaji"),
                                rs.getString("NomorRekening"),
                                rs.getString("TipePegawai"),
                                rs.getString("JumlahBoxPegawai"),
                                rs.getString("KategoriAdmin"),
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
    
    public void refreshAllFields(){
        try{
            if (!rs.isClosed()){
                idpegawai.setText(rs.getString("idPegawai"));
                nama.setText(rs.getString("Nama"));
                alamat.setText(rs.getString("Alamat"));

                //            pJenisKelamin.setText(rs.getString("JenisKelamin"));
                tanggallahir.setText(rs.getString("Tanggallahir"));
                gaji.setText(rs.getString("Gaji"));
                nomorrekening.setText(rs.getString("NomorRekening"));
                jumlahboxpegawai.setText(rs.getString("JumlahBoxPegawai"));
                kategoriadmin.setText(rs.getString("KategoriAdmin"));     
            }
        } catch (SQLException ex){
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE,null,ex );
        }
        
    }
    
    public void updateNavigationButtons(){
        try{
            if (!rs.isClosed()){
                first1.setEnabled(!rs.isFirst());
                prev1.setEnabled(!rs.isFirst());
                next1.setEnabled(!rs.isLast());
                last1.setEnabled(!rs.isLast());
            }
        } catch (SQLException ex){
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE,null,ex );
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        first = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        next1 = new javax.swing.JButton();
        last = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idpegawai = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        laki = new javax.swing.JRadioButton();
        perempuan = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        gaji = new javax.swing.JTextField();
        nomorrekening = new javax.swing.JTextField();
        kategoriadmin = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        newbutton = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchfield = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        alamat = new javax.swing.JTextField();
        tanggallahir = new javax.swing.JTextField();
        jumlahboxpegawai = new javax.swing.JTextField();
        packager = new javax.swing.JRadioButton();
        admin = new javax.swing.JRadioButton();
        first1 = new javax.swing.JButton();
        prev1 = new javax.swing.JButton();
        next2 = new javax.swing.JButton();
        last1 = new javax.swing.JButton();
        kembali2 = new javax.swing.JButton();

        first.setText("<<");
        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        prev.setText("<");
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });

        next1.setText(">");
        next1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next1ActionPerformed(evt);
            }
        });

        last.setText(">>");
        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("DATA PEGAWAI");

        jLabel2.setText("ID Pegawai");

        jLabel3.setText("Nama");

        jLabel4.setText("Alamat");

        jLabel5.setText("Jenis Kelamin");

        idpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                idpegawaiMouseReleased(evt);
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

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaKeyReleased(evt);
            }
        });

        buttonGroup1.add(laki);
        laki.setText("Laki - Laki");

        buttonGroup1.add(perempuan);
        perempuan.setText("Perempuan");
        perempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perempuanActionPerformed(evt);
            }
        });

        jLabel6.setText("Tanggal Lahir");

        jLabel7.setText("Gaji");

        jLabel8.setText("Nomor Rekening");

        jLabel9.setText("Tipe Pegawai");

        jLabel10.setText("Jumlah Box Pegawai");

        jLabel11.setText("Kategori Admin Pegawai");

        kategoriadmin.setText(" ");
        kategoriadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriadminActionPerformed(evt);
            }
        });

        update.setText("Ubah");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        newbutton.setText("Baru");
        newbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbuttonActionPerformed(evt);
            }
        });

        delete.setText("Hapus");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        save.setText("Simpan");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        cancel.setText("Batal");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Nama", "Alamat", "Jenis Kelamin", "Tanggal Lahir", "Gaji", "Nomor Rekening", "Tipe Pegawai", "Jumlah Box Pegawai", "Kategori Admin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setResizable(false);
        }

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

        tanggallahir.setText("0000-00-00");

        jumlahboxpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahboxpegawaiActionPerformed(evt);
            }
        });

        buttonGroup2.add(packager);
        packager.setText("Packager");
        packager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                packagerMouseReleased(evt);
            }
        });
        packager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packagerActionPerformed(evt);
            }
        });

        buttonGroup2.add(admin);
        admin.setText("Admin");
        admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                adminMouseReleased(evt);
            }
        });
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(first1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prev1)
                        .addGap(26, 26, 26)
                        .addComponent(next2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(last1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(520, 520, 520))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jumlahboxpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(packager, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(admin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kategoriadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomorrekening)
                            .addComponent(gaji)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(perempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(laki, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(alamat)
                            .addComponent(nama)
                            .addComponent(tanggallahir, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kembali2)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(idpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(kembali2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(perempuan)
                    .addComponent(laki))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tanggallahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(gaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nomorrekening, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(packager)
                    .addComponent(admin))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahboxpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(kategoriadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(newbutton)
                    .addComponent(update)
                    .addComponent(save)
                    .addComponent(delete)
                    .addComponent(next2)
                    .addComponent(prev1)
                    .addComponent(last1)
                    .addComponent(first1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbuttonActionPerformed
        nama.setEnabled(true);
        alamat.setEnabled(true);
        perempuan.setEnabled(true);
        laki.setEnabled(true);
        tanggallahir.setEnabled(true);
        gaji.setEnabled(true);
        nomorrekening.setEnabled(true);
        packager.setEnabled(true);
        admin.setEnabled(true);
        jumlahboxpegawai.setEnabled(true);
        kategoriadmin.setEnabled(true);
        update.setEnabled(false);
        delete.setEnabled(false);
        newbutton.setEnabled(false);
    }//GEN-LAST:event_newbuttonActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        cari();
    }//GEN-LAST:event_searchActionPerformed

    private void idpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idpegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idpegawaiActionPerformed

    private void perempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perempuanActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=heejab; user=dinda; password=123;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
           String sql = "Select * from pegawai where idpegawai=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, idpegawai.getText());
                ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                    String jk = null;
                    if(laki.isSelected()) {
                        jk = "Laki-laki";
                    } else if(perempuan.isSelected()) {
                        jk = "Perempuan";
                    }
                    
                    String tp = null;
                    if(packager.isSelected()) {
                        tp = "Packager";
                    } else if(admin.isSelected()) {
                        tp = "Admin";
                    }

                    try {
                        sql = "UPDATE pegawai SET Nama='" + nama.getText()
                                + "',Alamat='" + alamat.getText() + "',JenisKelamin='" + jk + "',Tanggallahir='" + tanggallahir.getText() + "',Gaji='" + gaji.getText() + "',NomorRekening='" + nomorrekening.getText() + "',TipePegawai='" + tp + "',JumlahBoxPegawai='" + jumlahboxpegawai.getText() + "',KategoriAdmin='" + kategoriadmin.getText()
                                +"' where idPegawai='" + idpegawai.getText() + "'";

                        pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Berhasil Diedit!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak berhasil diedit :( " + e.getMessage());
                    }
                }
            else {
                    String jk = null;
                    if(laki.isSelected()) {
                        jk = "Laki-laki";
                    } else if(perempuan.isSelected()) {
                        jk = "Perempuan";
                    }
                    
                    String tp = null;
                    if(packager.isSelected()) {
                        tp = "Packager";
                    } else if(admin.isSelected()) {
                        tp = "Admin";
                    }
                    
                    
                    try {
                        sql = "INSERT INTO pegawai VALUES ('" + nama.getText() + "','" 
                               + alamat.getText() + "','" + jk + "','" + tanggallahir.getText() + "','" + gaji.getText() + "','" + nomorrekening.getText() + "','" + tp + "','" +jumlahboxpegawai.getText() + "','" + kategoriadmin.getText() + "')";

                         pst = con.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");
                    } catch (Exception e) {
                        JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
                    }
                }

            rs = st.executeQuery("SELECT * FROM pegawai");
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
        nama.setEnabled(false);
        alamat.setEnabled(false);
        laki.setEnabled(false);
        perempuan.setEnabled(false);
        tanggallahir.setEnabled(false);
        gaji.setEnabled(false);
        nomorrekening.setEnabled(false);
        packager.setEnabled(false);
        admin.setEnabled(false);
        jumlahboxpegawai.setEnabled(false);
        kategoriadmin.setEnabled(false);
        

//        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=HEEJAB; user=ruridesfianti; password=321;" + "loginTimeout=30;";
//        ResultSet resultSet = null;
//            try (Connection con = DriverManager.getConnection(connectionUrl);
//                Statement st = con.createStatement();) {
//                String jk = null;
//                if(laki.isSelected()) {
//                    jk = "Laki-laki";
//                } else if(perempuan.isSelected()) {
//                    jk = "Perempuan";
//                }
//
//                String tipe_pegawai;
//                        tipe_pegawai = tipepegawai.getSelectedItem().toString();
//       
//                        try{
//                String sql = "INSERT INTO Pembeli VALUES ('" + idpegawai.getText()+ "','" + nama.getText() + "','" 
//                        + alamat.getText() + "','" + jk + "','" + tanggallahir.getText() + "','" + "','" + gaji.getText() + "')" + nomorrekening.getText() + "')" + tipe_pegawai + "','" + jumlahboxpegawai.getText()+ kategoriadmin.getText();
//
//                 PreparedStatement pst = con.prepareStatement(sql);
//                 pst.execute();
//                 JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");
//
//                 rs = st.executeQuery("SELECT * FROM Pembeli");
//                 ResultSetMetaData rsmdt = rs.getMetaData();
//                 // menyimpan jumlah kolom
//                 int columns = rsmdt.getColumnCount();
//                 //object ini akan mengantarkan data ke Jtable
//                 DefaultTableModel dtm = new DefaultTableModel();
//                 Vector columns_name = new Vector();
//                 Vector data_rows = new Vector();
//
//                 for (int i = 1; i <= columns; i++) {
//                     columns_name.addElement(rsmdt.getColumnName(i));
//                 }
//                 dtm.setColumnIdentifiers(columns_name);
//                 while(rs.next()) {
//                     data_rows = new Vector();
//                     for (int j = 1; j <= columns; j++) {
//                         data_rows.addElement(rs.getString(j));
//                     }
//                     dtm.addRow(data_rows);
//                 }
//                 // pass Default table Object into jTable1
//                 jTable1.setModel(dtm);
//            } catch (Exception e) {
//                 JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
//}} catch(SQLException e) {
//    e.printStackTrace();
//}
//        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=Heejab; user=dinda; password=123;" + "loginTimeout=30;";
//        try (Connection connection = DriverManager.getConnection(connectionUrl);
//                Statement statement = connection.createStatement();) {
//           String query = "insert into pegawai(idpegawai, nama, alamat, jeniskelamin, tanggallahir, gaji, nomorrekening, tipepegawai, jumlahboxpegawai, kategoriadmin) values (?,?,?,?,?,?,?,?,?,?)";
//                PreparedStatement pst = connection.prepareStatement(query);
//                pst.setString(1, idpegawai.getText());
//                pst.setString(2, nama.getText());
//                pst.setString(3, alamat.getText());
//                if (perempuan.isSelected()) {
//                    jeniskelamin = "Perempuan";
//                }
//                if (laki.isSelected()) {
//                    jeniskelamin = "Laki-laki";
//                }
//                pst.setString(4, jeniskelamin);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String date = sdf.format(tanggallahir.getDate());
//                pst.setString(5, date);
//                pst.setString(6, gaji.getText());
//                pst.setString(7, nomorrekening.getText());
//                String tipe_pegawai;
//                tipe_pegawai = tipepegawai.getSelectedItem().toString();
//                pst.setString(8, tipe_pegawai);
//                pst.setString(9, jumlahboxpegawai.getText());
//                pst.setString(10, kategoriadmin.getText());
//                pst.executeUpdate();
//                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
//                model.setRowCount(0);
//                lihat_pegawai();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
    }//GEN-LAST:event_saveActionPerformed

    private void kategoriadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriadminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoriadminActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        idpegawai.setText("");
        nama.setText("");
        alamat.setText("");
        buttonGroup1.clearSelection();
        tanggallahir.setText("");
        gaji.setText("");
        nomorrekening.setText("");
        buttonGroup2.clearSelection();
        jumlahboxpegawai.setText("");
        kategoriadmin.setText("");
        searchfield.setText("");
        update.setEnabled(true);
        delete.setEnabled(true);
        newbutton.setEnabled(true);
        delete.setEnabled(true);
        save.setEnabled(false);
        idpegawai.setEnabled(false);
        nama.setEnabled(false);
        alamat.setEnabled(false);
        perempuan.setEnabled(false);
        laki.setEnabled(false);
        tanggallahir.setEnabled(false);
        gaji.setEnabled(false);
        nomorrekening.setEnabled(false);
        packager.setEnabled(false);
        admin.setEnabled(false);
        jumlahboxpegawai.setEnabled(false);
        kategoriadmin.setEnabled(false);
//        idpegawai.setText("");
//        nama.setText("");
//        alamat.setText("");
//        buttonGroup1.clearSelection();
//        tanggallahir.cleanup();
//        gaji.setText("");
//        nomorrekening.setText("");
        
        
        
    }//GEN-LAST:event_cancelActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update.setEnabled(false);
        delete.setEnabled(false);
        newbutton.setEnabled(false);
        save.setEnabled(true);
        idpegawai.setEnabled(false);
        nama.setEnabled(true);
        alamat.setEnabled(true);
        perempuan.setEnabled(true);
        laki.setEnabled(true);
        tanggallahir.setEnabled(true);
        gaji.setEnabled(true);
        nomorrekening.setEnabled(true);
        packager.setEnabled(true);
        admin.setEnabled(true);
        jumlahboxpegawai.setEnabled(true);
        kategoriadmin.setEnabled(true);
//        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
//                +"DatabaseName=Heejab; user=dinda; password=123;" + "loginTimeout=30;";
//        try (Connection connection = DriverManager.getConnection(connectionUrl);
//                Statement statement = connection.createStatement();) {
//            int row = jTable1.getSelectedRow();
//            String value = (jTable1.getModel().getValueAt(row, 0).toString()); 
//            String query = "UPDATE pegawai SET idpegawai=?, nama=?, alamat=?, jeniskelamin=?, tanggallahir=?, gaji=?, nomorrekening=?, tipepegawai=?, jumlahboxpegawai=?, kategoriadmin=? where idpegawai="+value;
//            PreparedStatement pst = connection.prepareStatement(query);
//                pst.setString(1, idpegawai.getText());
//                pst.setString(2, nama.getText());
//                pst.setString(3, alamat.getText());
//                if (perempuan.isSelected()) {
//                    jeniskelamin = "Perempuan"; 
//                }
//                if (laki.isSelected()) {
//                    jeniskelamin = "Laki-laki";
//                }
//                pst.setString(4, jeniskelamin);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String date = sdf.format(tanggallahir.getDate());
//                pst.setString(5, date);
//                pst.setString(6, gaji.getText());
//                pst.setString(7, nomorrekening.getText());
//                String tipe_pegawai;
//                tipe_pegawai = tipepegawai.getSelectedItem().toString();
//                pst.setString(8, tipe_pegawai);
//                pst.setString(9, jumlahboxpegawai.getText());
//                pst.setString(10, kategoriadmin.getText());
//                pst.executeUpdate();
//                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
//                model.setRowCount(0);
//                lihat_pegawai();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate!");
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_updateActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        
        idpegawai.setText(model.getValueAt(selectedRowIndex, 0).toString());
        nama.setText(model.getValueAt(selectedRowIndex, 1).toString());
        alamat.setText(model.getValueAt(selectedRowIndex, 2).toString());
        String jeniskelamin = model.getValueAt(selectedRowIndex, 3).toString();
        if(jeniskelamin.equals("Laki-laki")) {
            laki.setSelected(true);
        }
        else {
            perempuan.setSelected(true);
        }
        tanggallahir.setText(model.getValueAt(selectedRowIndex, 4).toString());
        gaji.setText(model.getValueAt(selectedRowIndex, 5).toString());
        nomorrekening.setText(model.getValueAt(selectedRowIndex, 6).toString());
        String tipepegawai = model.getValueAt(selectedRowIndex, 7).toString();
        if(tipepegawai.equals("Packager")) {
            packager.setSelected(true);
        }
        else {
            admin.setSelected(true);
        }
        jumlahboxpegawai.setText((String) model.getValueAt(selectedRowIndex, 8));
        kategoriadmin.setText(model.getValueAt(selectedRowIndex, 9).toString());
        
        
//       int i = jTable1.getSelectedRow();
//       TableModel model = jTable1.getModel();
//       idpegawai.setText(model.getValueAt(i,1).toString());
//       nama.setText(model.getValueAt(i,2).toString());
//       alamat.setText(model.getValueAt(i,3).toString());
//       String jeniskelamin = model.getValueAt(i,4).toString();
//        if(jeniskelamin.equals("Laki - Laki")) {
//            laki.setSelected(true);
//        }
//        else {
//            perempuan.setSelected(true);
//        }
//        
//       //tanggallahir.setText(model.getValueAt(i,5).toString());
//       gaji.setText(model.getValueAt(i,6).toString());
//       nomorrekening.setText(model.getValueAt(i,7).toString());
//       String tipepegawai1 = model.getValueAt(i,8).toString();
//        switch (tipepegawai1) {
//            case "Packager":
//                tipepegawai.setSelectedIndex(0);
//                break;
//            case "Admin":
//                tipepegawai.setSelectedIndex(1);
//                break;
//        }
//        jumlahboxpegawai.setText(model.getValueAt(i,9).toString());
//        kategoriadmin.setText(model.getValueAt(i,10).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    
    }//GEN-LAST:event_deleteActionPerformed

    private void idpegawaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idpegawaiKeyReleased
      
    }//GEN-LAST:event_idpegawaiKeyReleased

    private void namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyReleased
        if(nama.getText().length() > 0){
            save.setEnabled(true);
        } else{
            save.setEnabled(false);
        }
    }//GEN-LAST:event_namaKeyReleased

    private void jumlahboxpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahboxpegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahboxpegawaiActionPerformed

    private void searchfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchfieldActionPerformed

    private void packagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packagerActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_packagerActionPerformed

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_adminActionPerformed

    private void packagerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_packagerMouseReleased
        // TODO add your handling code here:
        jumlahboxpegawai.setEnabled(true); 
        if(packager.isSelected()) {
                        kategoriadmin.setEnabled(false); 
                    } else{
             kategoriadmin.setEnabled(true); 
        }
    }//GEN-LAST:event_packagerMouseReleased

    private void adminMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminMouseReleased
        // TODO add your handling code here:
        kategoriadmin.setEnabled(true); 
        if(admin.isSelected()) {
                        jumlahboxpegawai.setEnabled(false); 
                    } else{
             jumlahboxpegawai.setEnabled(true); 
        }
    }//GEN-LAST:event_adminMouseReleased

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        String connectionUrl = "jdbc:sqlserver://localhost:1433; integratedSecurity=false; encrypt=false; trustServerCertificate=true;"
                +"DatabaseName=heejab; user=dinda; password=123;" + "loginTimeout=30;";
        ResultSet resultSet = null;
        try (Connection con = DriverManager.getConnection(connectionUrl);
                Statement st = con.createStatement();) {
                try {
                    String sql = "DELETE FROM pegawai WHERE idPegawai='" + idpegawai.getText() + "'";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.execute();
                    ResultSet rs = st.executeQuery("SELECT * FROM pegawai");
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

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        // TODO add your handling code here:
  
    }//GEN-LAST:event_firstActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prevActionPerformed

    private void next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next1ActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isClosed()){
                rs.next();
                refreshAllFields();
                updateNavigationButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_next1ActionPerformed

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lastActionPerformed

    private void first1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first1ActionPerformed
        // TODO add your handling code here:
            try {
            rs.first();
            idpegawai.setText(rs.getString("idPegawai"));
            nama.setText(rs.getString("Nama"));
            alamat.setText(rs.getString("Alamat"));
            
            //            pJenisKelamin.setText(rs.getString("JenisKelamin"));
            tanggallahir.setText(rs.getString("Tanggallahir"));
            gaji.setText(rs.getString("Gaji"));
            nomorrekening.setText(rs.getString("NomorRekening"));
            jumlahboxpegawai.setText(rs.getString("JumlahBoxPegawai"));
            kategoriadmin.setText(rs.getString("KategoriAdmin"));
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_first1ActionPerformed

    private void prev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prev1ActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isClosed()){
                rs.previous();
                refreshAllFields();
                updateNavigationButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_prev1ActionPerformed

    private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isClosed()){
                rs.next();
                refreshAllFields();
                updateNavigationButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_next2ActionPerformed

    private void last1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last1ActionPerformed
        // TODO add your handling code here:
        try {
            rs.first();
            idpegawai.setText(rs.getString("idPegawai"));
            nama.setText(rs.getString("Nama"));
            alamat.setText(rs.getString("Alamat"));
            //  pJenisKelamin.setText(rs.getString("JenisKelamin"));
            tanggallahir.setText(rs.getString("Tanggallahir"));
            gaji.setText(rs.getString("Gaji"));
            nomorrekening.setText(rs.getString("NomorRekening"));
            jumlahboxpegawai.setText(rs.getString("JumlahBoxPegawai"));
            kategoriadmin.setText(rs.getString("KategoriAdmin"));
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_last1ActionPerformed

    private void idpegawaiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idpegawaiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_idpegawaiMouseReleased

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

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
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormPegawai().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton admin;
    private javax.swing.JTextField alamat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancel;
    private javax.swing.JButton delete;
    private javax.swing.JButton first;
    private javax.swing.JButton first1;
    private javax.swing.JTextField gaji;
    private javax.swing.JTextField idpegawai;
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
    private javax.swing.JTextField jumlahboxpegawai;
    private javax.swing.JTextField kategoriadmin;
    private javax.swing.JButton kembali2;
    private javax.swing.JRadioButton laki;
    private javax.swing.JButton last;
    private javax.swing.JButton last1;
    private javax.swing.JTextField nama;
    private javax.swing.JButton newbutton;
    private javax.swing.JButton next1;
    private javax.swing.JButton next2;
    private javax.swing.JTextField nomorrekening;
    private javax.swing.JRadioButton packager;
    private javax.swing.JRadioButton perempuan;
    private javax.swing.JButton prev;
    private javax.swing.JButton prev1;
    private javax.swing.JButton save;
    private javax.swing.JButton search;
    private javax.swing.JTextField searchfield;
    private javax.swing.JTextField tanggallahir;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
