/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd_User;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author ThinkPad
 */
public class Pencarian extends javax.swing.JFrame {

    
    boolean a = false;
    public Pencarian() {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if("Windows".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        }catch(Exception ex){
            
        }
        initComponents();
        Connect();
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
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal :(" + e.getMessage());
        }
    }
    
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
                        String pencarian = pCari.getText();

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
    
        public void refreshAllFields(){
        try{
            if (!rs.isClosed()){
                kodeproduk.setText(rs.getString("KodeProduk")); 
                harga.setText(rs.getString("Harga"));
            }
        } catch (SQLException ex){
            Logger.getLogger(Pencarian.class.getName()).log(Level.SEVERE,null,ex );
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
            Logger.getLogger(Pencarian.class.getName()).log(Level.SEVERE,null,ex );
        }
        }
        
        ButtonGroup bG = new ButtonGroup();
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonMin = new javax.swing.JPanel();
        Buttonmax = new javax.swing.JPanel();
        fullmax = new javax.swing.JLabel();
        Buttonclose = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        MenuIcon = new javax.swing.JPanel();
        lineseting = new javax.swing.JPanel();
        setting = new javax.swing.JPanel();
        Buttonsetting = new javax.swing.JLabel();
        hidemenu = new javax.swing.JPanel();
        buttonhidemenu = new javax.swing.JLabel();
        linehidemenu = new javax.swing.JPanel();
        menuhide = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        dashboardview = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kodeproduk = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        pCari = new javax.swing.JTextField();
        kembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        next = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        last = new javax.swing.JButton();
        first = new javax.swing.JButton();
        kuantitas = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        keranjangFrame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Header.setBackground(new java.awt.Color(230, 230, 195));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(5, 10, 46));
        jLabel1.setText(" PENCARIAN PRODUK ");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 413, 50));

        ButtonMin.setBackground(new java.awt.Color(230, 230, 195));
        ButtonMin.setLayout(new java.awt.BorderLayout());
        Header.add(ButtonMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 50, 50));

        Buttonmax.setBackground(new java.awt.Color(5, 10, 46));
        Buttonmax.setLayout(new java.awt.BorderLayout());

        fullmax.setBackground(new java.awt.Color(198, 206, 237));
        fullmax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fullmax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/full_screen_32px.png"))); // NOI18N
        fullmax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fullmaxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fullmaxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fullmaxMouseExited(evt);
            }
        });
        Buttonmax.add(fullmax, java.awt.BorderLayout.CENTER);

        Header.add(Buttonmax, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 50, 50));

        Buttonclose.setBackground(new java.awt.Color(5, 10, 46));
        Buttonclose.setLayout(new java.awt.BorderLayout());

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete_32px.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        Buttonclose.add(close, java.awt.BorderLayout.CENTER);

        Header.add(Buttonclose, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 50));

        getContentPane().add(Header, java.awt.BorderLayout.PAGE_START);

        menu.setPreferredSize(new java.awt.Dimension(50, 450));
        menu.setLayout(new java.awt.BorderLayout());

        MenuIcon.setBackground(new java.awt.Color(5, 10, 46));
        MenuIcon.setPreferredSize(new java.awt.Dimension(50, 450));
        MenuIcon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineseting.setBackground(new java.awt.Color(5, 10, 46));
        lineseting.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout linesetingLayout = new javax.swing.GroupLayout(lineseting);
        lineseting.setLayout(linesetingLayout);
        linesetingLayout.setHorizontalGroup(
            linesetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linesetingLayout.setVerticalGroup(
            linesetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        MenuIcon.add(lineseting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 50, 5));

        setting.setBackground(new java.awt.Color(5, 10, 46));
        setting.setLayout(new java.awt.BorderLayout());

        Buttonsetting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Buttonsetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/settings_32px.png"))); // NOI18N
        Buttonsetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buttonsetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonsettingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonsettingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonsettingMouseExited(evt);
            }
        });
        setting.add(Buttonsetting, java.awt.BorderLayout.CENTER);

        MenuIcon.add(setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 50, 50));

        hidemenu.setBackground(new java.awt.Color(5, 10, 46));
        hidemenu.setLayout(new java.awt.BorderLayout());

        buttonhidemenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonhidemenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/menu_32px.png"))); // NOI18N
        buttonhidemenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonhidemenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonhidemenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonhidemenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonhidemenuMouseExited(evt);
            }
        });
        hidemenu.add(buttonhidemenu, java.awt.BorderLayout.CENTER);

        MenuIcon.add(hidemenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 50, 50));

        linehidemenu.setBackground(new java.awt.Color(5, 10, 46));
        linehidemenu.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout linehidemenuLayout = new javax.swing.GroupLayout(linehidemenu);
        linehidemenu.setLayout(linehidemenuLayout);
        linehidemenuLayout.setHorizontalGroup(
            linehidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linehidemenuLayout.setVerticalGroup(
            linehidemenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        MenuIcon.add(linehidemenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 5));

        menu.add(MenuIcon, java.awt.BorderLayout.LINE_START);

        menuhide.setBackground(new java.awt.Color(25, 29, 74));
        menuhide.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(null);

        jTree1.setBackground(new java.awt.Color(25, 29, 74));
        jTree1.setForeground(new java.awt.Color(25, 29, 74));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Tidak tersedia");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTree1.setName(""); // NOI18N
        jTree1.setSelectionModel(null);
        jTree1.setShowsRootHandles(true);
        jScrollPane2.setViewportView(jTree1);

        menuhide.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        menu.add(menuhide, java.awt.BorderLayout.CENTER);

        getContentPane().add(menu, java.awt.BorderLayout.LINE_START);

        dashboardview.setBackground(new java.awt.Color(198, 213, 245));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(5, 10, 46));
        jLabel4.setText("Kuantitas");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(5, 10, 46));
        jLabel2.setText("Kode Produk");

        kodeproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeprodukActionPerformed(evt);
            }
        });

        cari.setBackground(new java.awt.Color(5, 10, 46));
        cari.setText("CARI");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        pCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pCariActionPerformed(evt);
            }
        });

        kembali.setBackground(new java.awt.Color(5, 10, 46));
        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
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

        next.setBackground(new java.awt.Color(5, 10, 46));
        next.setText(">");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        prev.setBackground(new java.awt.Color(5, 10, 46));
        prev.setText("<");
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });

        last.setBackground(new java.awt.Color(5, 10, 46));
        last.setText(">>");
        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

        first.setBackground(new java.awt.Color(5, 10, 46));
        first.setText("<<");
        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(5, 10, 46));
        jButton1.setText("+ Keranjang");
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

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(5, 10, 46));
        jLabel5.setText("Harga");

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        keranjangFrame.setBackground(new java.awt.Color(5, 10, 46));
        keranjangFrame.setText("Keranjang Belanja");
        keranjangFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keranjangFrameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dashboardviewLayout = new javax.swing.GroupLayout(dashboardview);
        dashboardview.setLayout(dashboardviewLayout);
        dashboardviewLayout.setHorizontalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardviewLayout.createSequentialGroup()
                        .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(dashboardviewLayout.createSequentialGroup()
                                .addComponent(first)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prev)
                                .addGap(26, 26, 26)
                                .addComponent(next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last)
                                .addGap(458, 458, 458)
                                .addComponent(kembali))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dashboardviewLayout.createSequentialGroup()
                                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(dashboardviewLayout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(harga))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dashboardviewLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dashboardviewLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(kuantitas)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(keranjangFrame)))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(dashboardviewLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pCari, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        dashboardviewLayout.setVerticalGroup(
            dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardviewLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(keranjangFrame))
                .addGap(18, 18, 18)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cari)
                    .addComponent(pCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dashboardviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(prev)
                    .addComponent(last)
                    .addComponent(first)
                    .addComponent(kembali))
                .addGap(21, 21, 21))
        );

        getContentPane().add(dashboardview, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(860, 514));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void treelist(String folderfile){
        
    }
    
    
    
    public void changecolor(JPanel hover, Color rand){
        hover.setBackground(rand);
    }
    
    public void clickmenu(JPanel h1, JPanel h2, int numberbool){
        if(numberbool == 1){
            h1.setBackground(new Color(25, 29, 74));
            h2.setBackground(new Color(5, 10, 46));
        }
        else{
            h1.setBackground(new Color(5, 10, 46));
            h2.setBackground(new Color(25, 29, 74));
        }
    }
    
    public void changeimage(JLabel button, String resourcheimg){
        ImageIcon aimg = new ImageIcon(getClass().getResource(resourcheimg));
        button.setIcon(aimg);
    }
    
    public void hideshow(JPanel menushowhide, boolean dashboard, JLabel button){
        if(dashboard == true){
            menushowhide.setPreferredSize(new Dimension(50, menushowhide.getHeight()));
            changeimage(button, "/Icon/menu_32px.png");
        }
        else{
            menushowhide.setPreferredSize(new Dimension(270, menushowhide.getHeight()));
            changeimage(button, "/Icon/back_32px.png");
        }
        
    }
    
    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        changecolor(Buttonclose, new Color(198,206,237));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        changecolor(Buttonclose, new Color(5, 10, 46));
    }//GEN-LAST:event_closeMouseExited

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void fullmaxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullmaxMouseEntered
        changecolor(Buttonmax, new Color(198,206,237));
    }//GEN-LAST:event_fullmaxMouseEntered

    private void fullmaxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullmaxMouseExited
        changecolor(Buttonmax, new Color(5, 10, 46));
    }//GEN-LAST:event_fullmaxMouseExited

    private void fullmaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fullmaxMouseClicked
        if(this.getExtendedState()!= Pencarian.MAXIMIZED_BOTH){
          this.setExtendedState(Pencarian.MAXIMIZED_BOTH);
        }
        else{
            this.setExtendedState(Pencarian.NORMAL);
        }
    }//GEN-LAST:event_fullmaxMouseClicked

    private void ButtonsettingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonsettingMouseEntered
        changecolor(lineseting, new Color(230,230,195));
    }//GEN-LAST:event_ButtonsettingMouseEntered

    private void ButtonsettingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonsettingMouseExited
        changecolor(lineseting, new Color(230,230,195));
    }//GEN-LAST:event_ButtonsettingMouseExited

    private void ButtonsettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonsettingMouseClicked
        clickmenu(setting, hidemenu, 1);
    }//GEN-LAST:event_ButtonsettingMouseClicked

    private void buttonhidemenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseExited
        changecolor(linehidemenu, new Color(5, 10, 46));
    }//GEN-LAST:event_buttonhidemenuMouseExited

    private void buttonhidemenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseEntered
        changecolor(linehidemenu, new Color(247, 78, 105));
    }//GEN-LAST:event_buttonhidemenuMouseEntered

    private void buttonhidemenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonhidemenuMouseClicked
        clickmenu(hidemenu, setting, 1);
        //create void for methode hide and show panel menu
        if(a==true){
            hideshow(menu, a, buttonhidemenu);
            SwingUtilities.updateComponentTreeUI(this);
            //create methode change image

            a=false;
        }
        else{
            hideshow(menu, a, buttonhidemenu);
            SwingUtilities.updateComponentTreeUI(this);
            a=true;
        }

    }//GEN-LAST:event_buttonhidemenuMouseClicked

    private void kodeprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeprodukActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
  
        cari();
    }//GEN-LAST:event_cariActionPerformed

    private void pCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pCariActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        // UNTUK MENDISPLAY DATA DI TABEL KE TEXT FIELDS
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();

        kodeproduk.setText(model.getValueAt(selectedRowIndex, 0).toString());
        harga.setText(model.getValueAt(selectedRowIndex, 3).toString());
        kodeproduk.setEnabled(false);
        harga.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isClosed()){
                rs.next();
                refreshAllFields();
                updateNavigationButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pencarian.class.getName()).log(Level.SEVERE,null,ex);
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
            Logger.getLogger(Pencarian.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_prevActionPerformed

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        // TODO add your handling code here:
        try {
            rs.last();
            kodeproduk.setText(rs.getString("KodeProduk"));
            harga.setText(rs.getString("harga"));
        } catch (SQLException ex) {
            Logger.getLogger(Pencarian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lastActionPerformed

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        // TODO add your handling code here:
        try {
            rs.first();
            kodeproduk.setText(rs.getString("KodeProduk"));
            harga.setText(rs.getString("harga"));
        } catch (SQLException ex) {
            Logger.getLogger(Pencarian.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_firstActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        Beranda field = new Beranda();
        field.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_kembaliActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
            try {
//            st = con.createStatement();
//            String harga = "SELECT harga from Produk where kodeProduk=" + kodeproduk.getText();
//            rs = st.executeQuery(harga);
//            
//            int hargaKali = Integer.parseInt(harga);
            
            String sql = "INSERT INTO keranjangBelanja VALUES ('BulanKu'," + kodeproduk.getText() + ","
            + kuantitas.getText() + "," + harga.getText() + ")";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showConfirmDialog(null, "Berhasil Menyimpan :)");

//            // untuk menampilkan data ke tabel
//            rs = st.executeQuery("SELECT * FROM Keranjang Belanja");
//            ResultSetMetaData rsmdt = rs.getMetaData();
//            // menyimpan jumlah kolom
//            int columns = rsmdt.getColumnCount();
//            //object ini akan mengantarkan data ke Jtable
//            DefaultTableModel dtm = new DefaultTableModel();
//            Vector columns_name = new Vector();
//            Vector data_rows = new Vector();
//
//            for (int i = 1; i <= columns; i++) {
//                columns_name.addElement(rsmdt.getColumnName(i));
//            }
//            dtm.setColumnIdentifiers(columns_name);
//
//            while(rs.next()) {
//                data_rows = new Vector();
//                for (int j = 1; j <= columns; j++) {
//                    data_rows.addElement(rs.getString(j));
//                }
//                dtm.addRow(data_rows);
//            }
//            // pass Default table Object into jTable1
//            jTable1.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Tidak Berhasil Menyimpan :( " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void keranjangFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keranjangFrameMouseClicked
        // TODO add your handling code here:
        // perintah ke frame keranjang belanja
        Keranjang field = new Keranjang();
        field.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_keranjangFrameMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pencarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Pencarian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonMin;
    private javax.swing.JPanel Buttonclose;
    private javax.swing.JPanel Buttonmax;
    private javax.swing.JLabel Buttonsetting;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel MenuIcon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel buttonhidemenu;
    private javax.swing.JButton cari;
    private javax.swing.JLabel close;
    private javax.swing.JPanel dashboardview;
    private javax.swing.JButton first;
    private javax.swing.JLabel fullmax;
    private javax.swing.JTextField harga;
    private javax.swing.JPanel hidemenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton kembali;
    private javax.swing.JButton keranjangFrame;
    private javax.swing.JTextField kodeproduk;
    private javax.swing.JTextField kuantitas;
    private javax.swing.JButton last;
    private javax.swing.JPanel linehidemenu;
    private javax.swing.JPanel lineseting;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuhide;
    private javax.swing.JButton next;
    private javax.swing.JTextField pCari;
    private javax.swing.JButton prev;
    private javax.swing.JPanel setting;
    // End of variables declaration//GEN-END:variables

}
