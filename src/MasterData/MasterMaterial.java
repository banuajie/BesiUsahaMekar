/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterData;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import besiusahamekar.koneksidb;

/**
 *
 * @author Banuajie
 */
public class MasterMaterial extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
EditDaftarMaterial fAB = null;
    /**
     * Creates new form MasterMaterial
     */
    public MasterMaterial() {
        initComponents();
        setJTable();
        jmldatatable();
        txtkodesupplier.setEnabled(false);
        txtnamasupplier.setEnabled(false);
    }


    private void setJTable() {
    String [] JudulKolom={"Kode Material","Tipe Item","Nama Item","Jenis","Stok","Satuan","Harga Pokok","Harga Jual","Keterangan","ID Supplier","Nama Supplier"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTdaftaritem.setModel(tabModel);
    jTdaftaritem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTdaftaritem.getColumnModel().getColumn(0).setPreferredWidth(100);
    jTdaftaritem.getColumnModel().getColumn(1).setMinWidth(0);
    jTdaftaritem.getColumnModel().getColumn(1).setMaxWidth(0);
    jTdaftaritem.getColumnModel().getColumn(1).setWidth(0);
    //jTdaftaritem.getColumnModel().getColumn(1).setPreferredWidth(200);
    jTdaftaritem.getColumnModel().getColumn(2).setPreferredWidth(350);
    jTdaftaritem.getColumnModel().getColumn(3).setPreferredWidth(150);
    jTdaftaritem.getColumnModel().getColumn(4).setPreferredWidth(75);
    jTdaftaritem.getColumnModel().getColumn(5).setPreferredWidth(100);
    jTdaftaritem.getColumnModel().getColumn(6).setPreferredWidth(100);
    jTdaftaritem.getColumnModel().getColumn(7).setPreferredWidth(100);
    jTdaftaritem.getColumnModel().getColumn(8).setMinWidth(0);
    jTdaftaritem.getColumnModel().getColumn(8).setMaxWidth(0);
    jTdaftaritem.getColumnModel().getColumn(8).setWidth(0);
    jTdaftaritem.getColumnModel().getColumn(9).setPreferredWidth(100);
    jTdaftaritem.getColumnModel().getColumn(10).setPreferredWidth(100);
     
            hapusIsiJTable();
            getDatabarang();
        
       }
    
    public void getDatabarang() {
        // import java.sql.connection
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tmaterial where Tipe_Item='material'";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       kodeitem=rs.getString("Kode_Item");
                       tipeitem=rs.getString("Tipe_Item");
                       namaitem=rs.getString("Nama_Item");
                       jenis=rs.getString("Jenis");
                       stok=rs.getString("Stok");
                       satuan=rs.getString("Satuan");
                       hargapokok=rs.getString("Harga_Pokok");
                       hargajual=rs.getString("Harga_Jual");
                       keterangan=rs.getString("Keterangan");
                       idsupplier=rs.getString("ID_Supplier");
                       namasupplier=rs.getString("Nama_Supplier");
        
         Object Data[]={kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier};
         tabModel.addRow(Data);
        }
          // Tutup Koneksi
          st.close();
          rs.close();
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    

    }
    
    
    
    public void getStokminim() {
        // import java.sql.connection
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tmaterial where Stok <= 10";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       kodeitem=rs.getString("Kode_Item");
                       tipeitem=rs.getString("Tipe_Item");
                       namaitem=rs.getString("Nama_Item");
                       jenis=rs.getString("Jenis");
                       stok=rs.getString("Stok");
                       satuan=rs.getString("Satuan");
                       hargapokok=rs.getString("Harga_Pokok");
                       hargajual=rs.getString("Harga_Jual");
                       keterangan=rs.getString("Keterangan");
                       idsupplier=rs.getString("ID_Supplier");
                       namasupplier=rs.getString("Nama_Supplier");
        
         Object Data[]={kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier};
         tabModel.addRow(Data);
        }
          // Tutup Koneksi
          st.close();
          rs.close();
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }    

    }
    
    
     
     public void  cari(){
         String katakunci;
         int pilih = jCcariberdasarkan.getSelectedIndex();
         try {
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        if(pilih==0)
        katakunci ="Select * from tmaterial WHERE Nama_Item Like '%" +jTkatakunci.getText() + "%'";
        else if(pilih==1)
        katakunci ="Select * from tmaterial WHERE Kode_Item = '" +jTkatakunci.getText() + "'";
        else if(pilih==2)
        katakunci ="Select * from tmaterial WHERE Stok <= " +jTkatakunci.getText() + "";
        else if(pilih==3)
        katakunci ="Select * from tmaterial WHERE Jenis Like '%" +jTkatakunci.getText() + "%'";
        else
        katakunci ="Select * from tmaterial WHERE Satuan Like '%" +jTkatakunci.getText() + "%'";
        
        ResultSet rs=st.executeQuery(katakunci);

        hapusIsiJTable();
        // Menampilkan ke JTable  melalui tabModel
                     String kodeitem,tipeitem=null,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       kodeitem=rs.getString("Kode_Item");
                       tipeitem=rs.getString("Tipe_Item");
                       namaitem=rs.getString("Nama_Item");
                       jenis=rs.getString("Jenis");
                       stok=rs.getString("Stok");
                       satuan=rs.getString("Satuan");
                       hargapokok=rs.getString("Harga_Pokok");
                       hargajual=rs.getString("Harga_Jual");
                       keterangan=rs.getString("Keterangan");
                       idsupplier=rs.getString("ID_Supplier");
                       namasupplier=rs.getString("Nama_Supplier");
        
         Object Data[]={kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier};
         tabModel.addRow(Data);
        }
         

      st.close();
          rs.close();
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }
    }

     
     
     public void  carisupplier(){
         String katakunci;
         try {
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        
        katakunci ="Select * from tmaterial WHERE Nama_Supplier Like '%" +txtnamasupplier.getText() + "%'";
        
        ResultSet rs=st.executeQuery(katakunci);

        hapusIsiJTable();
        // Menampilkan ke JTable  melalui tabModel
                     String kodeitem,tipeitem=null,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       kodeitem=rs.getString("Kode_Item");
                       tipeitem=rs.getString("Tipe_Item");
                       namaitem=rs.getString("Nama_Item");
                       jenis=rs.getString("Jenis");
                       stok=rs.getString("Stok");
                       satuan=rs.getString("Satuan");
                       hargapokok=rs.getString("Harga_Pokok");
                       hargajual=rs.getString("Harga_Jual");
                       keterangan=rs.getString("Keterangan");
                       idsupplier=rs.getString("ID_Supplier");
                       namasupplier=rs.getString("Nama_Supplier");
        
         Object Data[]={kodeitem,tipeitem,namaitem,jenis,stok,satuan,hargapokok,hargajual,keterangan,idsupplier,namasupplier};
         tabModel.addRow(Data);
        }
         

      st.close();
          rs.close();
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    }
    }
     

    
  private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);

  }
  }
   
 public void hapus_Data() {
    
     int row = jTdaftaritem.getSelectedRow();
   // Mengambil data yang dipilih pada JTable
   String kode = tabModel.getValueAt(row, 0).toString();
   // Konfirmasi sebelum melakukan penghapusan data
    
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus Data\n = '" + kode.toString() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM tmaterial WHERE Kode_Item = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, kode.toString());
        int rs=st.executeUpdate();
        if(rs>0){
        
        JOptionPane.showMessageDialog(this,"Data Sudah dihapus");
        setJTable();
        }
        }catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
         JOptionPane.showMessageDialog(this,"Gagal Hapus Data.. ");
       }
    }
  }   
  
    public void jmldatatable(){
         int rows = jTdaftaritem.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData.setText(jmls);
   }   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTkatakunci = new javax.swing.JTextField();
        jBcari = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCcariberdasarkan = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBrefresh = new javax.swing.JButton();
        jCstokminim = new javax.swing.JCheckBox();
        txtkodesupplier = new javax.swing.JTextField();
        txtnamasupplier = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnsupplier = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btncarisupplier = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTdaftaritem = new javax.swing.JTable();
        jBhapus = new javax.swing.JButton();
        jBedit = new javax.swing.JButton();

        setForeground(java.awt.Color.lightGray);
        setTitle("Master Data Material");
        setVisible(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Master Data Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("Pencarian");

        jTkatakunci.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTkatakunci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTkatakunciActionPerformed(evt);
            }
        });
        jTkatakunci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkatakunciKeyPressed(evt);
            }
        });

        jBcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.gif"))); // NOI18N
        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Cari Berdasarkan");

        jCcariberdasarkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nama Material", "Kode Material", "Minimal Stok", "Jenis", "Satuan" }));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText(":");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Jumlah Data : ");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jLabel6.setText(":");

        jBrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        jBrefresh.setText("Refresh");
        jBrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBrefreshActionPerformed(evt);
            }
        });

        jCstokminim.setText("Stok Minim");
        jCstokminim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCstokminimActionPerformed(evt);
            }
        });

        jLabel3.setText("Kode Supplier");

        jLabel5.setText("Nama Supplier");

        jLabel7.setText(":");

        jLabel8.setText(":");

        btnsupplier.setText("...");
        btnsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupplierActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Cari Berdasarkan Supplier");

        btncarisupplier.setText("Cari Supplier");
        btncarisupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarisupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel6)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCcariberdasarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTkatakunci, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 372, Short.MAX_VALUE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jmlData))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jBcari, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBrefresh)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCstokminim))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtkodesupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncarisupplier)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTkatakunci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBcari)
                    .addComponent(jLabel6)
                    .addComponent(jBrefresh)
                    .addComponent(jCstokminim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jCcariberdasarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkodesupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(btnsupplier)
                    .addComponent(btncarisupplier))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jmlData)))
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jButton2.setText("Tambah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTdaftaritem.setAutoCreateRowSorter(true);
        jTdaftaritem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTdaftaritem.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Material", "Nama Item", "Jenis", "Stok", "Satuan", "Harga Pokok", "Harga Jual", "Keterangan", "ID Supplier", "Nama Supplier"
            }
        ));
        jTdaftaritem.setFocusable(false);
        jTdaftaritem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTdaftaritemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTdaftaritemMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTdaftaritem);

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
            }
        });

        jBedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tools.png"))); // NOI18N
        jBedit.setText("Edit");
        jBedit.setEnabled(false);
        jBedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBedit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBhapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jBedit)
                    .addComponent(jBhapus)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        TambahMaterial dp=new TambahMaterial();
        //MenuPanel.add(dp);
        //this.moveToBack();
        dp.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            jmldatatable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
        }
        jBhapus.setEnabled(false);
    }//GEN-LAST:event_jBhapusActionPerformed

    private void jTdaftaritemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTdaftaritemMouseClicked
        // TODO add your handling code here:
        int rows = jTdaftaritem.getSelectedRow();
        this.setTitle(tabModel.getValueAt(rows, 0).toString());
        
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);
        if(jTkatakunci.equals("")){
                hapusIsiJTable();
                getDatabarang();
        }
            
        //double click handle

        EditDaftarMaterial fDB = new EditDaftarMaterial();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTdaftaritem.rowAtPoint(pnt);
            //do something
            fDB.dispose();
            //fDB.fAB = this;
            int tabelBarang = jTdaftaritem.getSelectedRow();

            fDB.kodeitem = jTdaftaritem.getValueAt(tabelBarang, 0).toString();
            fDB.namaitem = jTdaftaritem.getValueAt(tabelBarang, 2).toString();
            fDB.jenis = jTdaftaritem.getValueAt(tabelBarang, 3).toString();
            fDB.stok = jTdaftaritem.getValueAt(tabelBarang, 4).toString();
            fDB.satuan = jTdaftaritem.getValueAt(tabelBarang, 5).toString();
            fDB.pokok = jTdaftaritem.getValueAt(tabelBarang, 6).toString();
            fDB.jual = jTdaftaritem.getValueAt(tabelBarang, 7).toString();
            fDB.keterangan = jTdaftaritem.getValueAt(tabelBarang, 8).toString();
            fDB.idsupplier = jTdaftaritem.getValueAt(tabelBarang, 9).toString();
            fDB.namasupplier = jTdaftaritem.getValueAt(tabelBarang, 10).toString();
            fDB.setVisible(true);
            fDB.tampildata();
        }
    }//GEN-LAST:event_jTdaftaritemMouseClicked

    private void jTdaftaritemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTdaftaritemMouseEntered
        // TODO add your handling code here:
        String kc = jTkatakunci.getText();
        String ns = txtnamasupplier.getText();
        if(kc.equals("") && ns.equals("")){
                hapusIsiJTable();
                getDatabarang();
        }
        if(jCstokminim.isSelected()){
            hapusIsiJTable();
            getStokminim();
        }
    }//GEN-LAST:event_jTdaftaritemMouseEntered

    private void jTkatakunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTkatakunciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTkatakunciActionPerformed

    private void jTkatakunciKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkatakunciKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            String cari = jTkatakunci.getText();
            if (cari.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Kata Kunci Harus Di Isi ");setJTable();

            }
            else{
                
                cari();
                jmldatatable();
            }
        }
    }//GEN-LAST:event_jTkatakunciKeyPressed

    private void jBcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcariActionPerformed
        // TODO add your handling code here:

        String cari = jTkatakunci.getText();
        if (cari.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Kata Kunci Harus Di Isi ");setJTable();

        }
        else{
           
            cari();
            jmldatatable();

        }

    }//GEN-LAST:event_jBcariActionPerformed

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        jTkatakunci.setText("");
        EditDaftarMaterial fDB = new EditDaftarMaterial();
        fDB.dispose();

        try {

            //fDB.fAB = this;
           int tabelBarang = jTdaftaritem.getSelectedRow();

            fDB.kodeitem = jTdaftaritem.getValueAt(tabelBarang, 0).toString();
            fDB.namaitem = jTdaftaritem.getValueAt(tabelBarang, 2).toString();
            fDB.jenis = jTdaftaritem.getValueAt(tabelBarang, 3).toString();
            fDB.stok = jTdaftaritem.getValueAt(tabelBarang, 4).toString();
            fDB.satuan = jTdaftaritem.getValueAt(tabelBarang, 5).toString();
            fDB.pokok = jTdaftaritem.getValueAt(tabelBarang, 6).toString();
            fDB.jual = jTdaftaritem.getValueAt(tabelBarang, 7).toString();
            fDB.keterangan = jTdaftaritem.getValueAt(tabelBarang, 8).toString();
            fDB.idsupplier = jTdaftaritem.getValueAt(tabelBarang, 9).toString();
            fDB.namasupplier = jTdaftaritem.getValueAt(tabelBarang, 10).toString();
            fDB.setVisible(true);
            fDB.tampildata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");

        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jBrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBrefreshActionPerformed
        // TODO add your handling code here:
        txtkodesupplier.setText("");
        txtnamasupplier.setText("");
        hapusIsiJTable();
        getDatabarang();
        jmldatatable();
    }//GEN-LAST:event_jBrefreshActionPerformed

    private void jCstokminimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCstokminimActionPerformed
        // TODO add your handling code here:
        hapusIsiJTable();
        getStokminim();
        jmldatatable();
    }//GEN-LAST:event_jCstokminimActionPerformed

    private void btnsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupplierActionPerformed
        // TODO add your handling code here:
        MasterSupplier dp=new MasterSupplier();
        dp.jBedit.setVisible(false);
        dp.jBhapus.setVisible(false);
        dp.jBtambah.setVisible(false);
        dp.jButton5.setVisible(false);
        dp.setTitle("");
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        
        panelPop.add(dp).setVisible(true);
        
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Supplier", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDatas(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }
    }//GEN-LAST:event_btnsupplierActionPerformed

    private void btncarisupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarisupplierActionPerformed
        // TODO add your handling code here:
        String carisupplier = txtnamasupplier.getText();
        if (carisupplier.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Kata Kunci Harus Di Isi ");setJTable();
        }
        else{
            carisupplier();
            jmldatatable();
        }
    }//GEN-LAST:event_btncarisupplierActionPerformed

    
    private void getDatas(String kode){
            
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tsupplier where ID_Supplier='"+kode+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       txtkodesupplier.setText(kode);
                       txtnamasupplier.setText(nama);
                       
        }
          // Tutup Koneksi
          st.close();
          rs.close();
         
    }
    catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
    catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
    } 
       
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btncarisupplier;
    private javax.swing.JButton btnsupplier;
    private javax.swing.JButton jBcari;
    public javax.swing.JButton jBedit;
    public javax.swing.JButton jBhapus;
    private javax.swing.JButton jBrefresh;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCcariberdasarkan;
    private javax.swing.JCheckBox jCstokminim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable jTdaftaritem;
    private javax.swing.JTextField jTkatakunci;
    private javax.swing.JLabel jmlData;
    public javax.swing.JTextField txtkodesupplier;
    public javax.swing.JTextField txtnamasupplier;
    // End of variables declaration//GEN-END:variables
}
