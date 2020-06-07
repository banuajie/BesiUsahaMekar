/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pembelian;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import besiusahamekar.koneksidb;


/**
 *
 * @author Banuajie
 */
public class DaftarTransaksi extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
    /**
     * Creates new form DaftarTransaksi
     */
    public DaftarTransaksi() {
        initComponents();
        setJTable();
        jmldatatable();
    }

    
    private void setJTable() {
    String [] JudulKolom={"No Transaksi","No Pesanan","Tanggal","ID Supplier","Nama Supplier","Jml Item","Total Transaksi","Jml Bayar","Sisa Bayar","Keterangan","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false,false, false, false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTtransaksibeli.setModel(tabModel);
    jTtransaksibeli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTtransaksibeli.getColumnModel().getColumn(0).setPreferredWidth(240);
    jTtransaksibeli.getColumnModel().getColumn(1).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(1).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(1).setWidth(0);
    
    jTtransaksibeli.getColumnModel().getColumn(2).setPreferredWidth(150);
    jTtransaksibeli.getColumnModel().getColumn(3).setPreferredWidth(100);
    jTtransaksibeli.getColumnModel().getColumn(4).setPreferredWidth(150);
    jTtransaksibeli.getColumnModel().getColumn(5).setPreferredWidth(150);
    jTtransaksibeli.getColumnModel().getColumn(6).setPreferredWidth(150);
    
    jTtransaksibeli.getColumnModel().getColumn(7).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(7).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(7).setWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(8).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(8).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(8).setWidth(0);
    
    jTtransaksibeli.getColumnModel().getColumn(9).setPreferredWidth(100);
    jTtransaksibeli.getColumnModel().getColumn(10).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(10).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(10).setWidth(0);
    
    getDatapesanan();
    
    }
    
    private void getDatapesanan() {
        // import java.sql.connection
        
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from ttransaksibeli where Status = '1'";
            ResultSet rs=st.executeQuery(sql);

            // Menampilkan ke JTable  melalui tabModel
                     String Notransaksi,Nopesanan,IDSupplier,namasup,jmlitem,totaltransaksi,jmlbayar,sisabayar,keterangan,status;
                     int no=0;
                        while(rs.next()){
                        no=no+1;
                        Notransaksi=rs.getString("No_Transaksi");
                        Nopesanan=rs.getString("No_Pesanan");
                        Date tanggal=rs.getDate("Tanggal");
                        IDSupplier=rs.getString("ID_Supplier");
                        namasup=rs.getString("Nama_Supplier");
                        jmlitem=rs.getString("Jml_Item");
                        totaltransaksi=rs.getString("Total_Transaksi");
                        jmlbayar=rs.getString("Jml_Bayar");
                        sisabayar=rs.getString("Sisa_Bayar");
                        keterangan=rs.getString("Keterangan");
                        status=rs.getString("Status");
                       
                       SimpleDateFormat Format = new SimpleDateFormat("dd-MM-yyyy");
                        String d = Format.format(tanggal);
                       
         Object Data[]={Notransaksi,Nopesanan,d,IDSupplier,namasup,jmlitem,totaltransaksi,jmlbayar,sisabayar,keterangan,status};

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
         int pilih = jCcariberdasarkan4.getSelectedIndex();
         try {
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        if(pilih==0)
        katakunci ="Select * from ttransaksibeli WHERE No_Transaksi = '" +jTkatakunci4.getText() + "'";
        else if(pilih==1)
        katakunci ="Select * from ttransaksibeli WHERE Tanggal Like '%" +jTkatakunci4.getText() + "%'";
        else
        katakunci ="Select * from ttransaksibeli WHERE ID_Supplier Like '%" +jTkatakunci4.getText() + "%'";

        ResultSet rs=st.executeQuery(katakunci);

        hapusIsiJTable();
        // Menampilkan ke JTable  melalui tabModel
                     String Notransaksi,Nopesanan,tanggal,IDSupplier,namasup,jmlitem,totaltransaksi,jmlbayar,sisabayar,keterangan,status;
                     int no=0;
                        while(rs.next()){
                        no=no+1;
                        Notransaksi=rs.getString("No_Transaksi");
                       Nopesanan=rs.getString("No_Pesanan");
                       tanggal=rs.getString("Tanggal");
                       IDSupplier=rs.getString("ID_Supplier");
                       namasup=rs.getString("Nama_Supplier");
                       jmlitem=rs.getString("Jml_Item");
                       totaltransaksi=rs.getString("Total_Transaksi");
                       jmlbayar=rs.getString("Jml_Bayar");
                       sisabayar=rs.getString("Sisa_Bayar");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("Status");
                       
         Object Data[]={Notransaksi,Nopesanan,tanggal,IDSupplier,namasup,jmlitem,totaltransaksi,jmlbayar,sisabayar,keterangan,status};

         tabModel.addRow(Data);
        }
       
         if(tabModel.getRowCount()>0){
           JOptionPane.showMessageDialog(this,"Data Ditemukan ");
                          
           }else{
           JOptionPane.showMessageDialog(this,"Data Tidak Ditemukan.. ");setJTable();
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
    
     public void jmldatatable(){
         
         int rows = jTtransaksibeli.getRowCount();
         String jmls = String.valueOf(rows);
         jmlData4.setText(jmls);
                    
   }  
     
     private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);

  }
  }
    
  public void hapus_Data() {
   int row = jTtransaksibeli.getSelectedRow();
   // Mengambil data yang dipilih pada JTable
   String notrans = tabModel.getValueAt(row, 0).toString();
   // Konfirmasi sebelum melakukan penghapusan data
    
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus Data\n = '" + notrans.toString() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM ttransaksibeli WHERE No_Transaksi = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, notrans.toString());
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTkatakunci4 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jCcariberdasarkan4 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jmlData4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtransaksibeli = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pencarian Data", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), java.awt.Color.black)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel14.setText("Masukan Kata Kunci");

        jTkatakunci4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTkatakunci4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTkatakunci4ActionPerformed(evt);
            }
        });
        jTkatakunci4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTkatakunci4KeyPressed(evt);
            }
        });

        jButton6.setText("Cari...");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel15.setText("Cari Data Berdasarkan");

        jCcariberdasarkan4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Transaksi", "Tanggal", "ID Supplier" }));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText(":");

        jLabel40.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel40.setText("Jumlah Data : ");

        jmlData4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jmlData4.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(303, 303, 303)
                                .addComponent(jButton6))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jCcariberdasarkan4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jmlData4))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTkatakunci4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTkatakunci4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jCcariberdasarkan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jmlData4)))
        );

        jTtransaksibeli.setAutoCreateRowSorter(true);
        jTtransaksibeli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTtransaksibeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Transaksi", "No Pesanan", "Tanggal", "Tgl Kirim", "Kode Supplier", "Nama Supplier", "Jml Pesanan", "Total Pesanan", "Keterangan"
            }
        ));
        jTtransaksibeli.setFocusable(false);
        jTtransaksibeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTtransaksibeliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTtransaksibeliMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTtransaksibeli);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel17.setText("Daftar Kendaraan");

        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTkatakunci4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTkatakunci4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTkatakunci4ActionPerformed

    private void jTkatakunci4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTkatakunci4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            String cari = jTkatakunci4.getText();
            if (cari.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Kata Kunci Harus Di Isi ");setJTable();

            }
            else{
                cari();
                jmldatatable();

            }
        }
    }//GEN-LAST:event_jTkatakunci4KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        String cari = jTkatakunci4.getText();
        if (cari.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Kata Kunci Harus Di Isi ");setJTable();

        }
        else{
            cari();
            jmldatatable();

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTtransaksibeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransaksibeliMouseClicked
        // TODO add your handling code here:
        int rows = jTtransaksibeli.getSelectedRow();
        this.setTitle(tabModel.getValueAt(rows, 0).toString());
        if (evt.getClickCount() == 2) {
            ReturPembelian tp = new ReturPembelian();
            Point pnt = evt.getPoint();
            int row = jTtransaksibeli.rowAtPoint(pnt);
            //do something
            int tabelsupplier = jTtransaksibeli.getSelectedRow();
            tp.no_transaksi = jTtransaksibeli.getValueAt(tabelsupplier, 0).toString();

            tp.tampildatatransaksi();
            tp.getDatatransaksibeli();
            tp.getNamaSupplier();
            tp.setenable();
            tp.jCkodeitem.setEnabled(true);
            tp.setVisible(true);

            //this.dispose();

        }
    }//GEN-LAST:event_jTtransaksibeliMouseClicked

    private void jTtransaksibeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransaksibeliMouseEntered
        // TODO add your handling code here:
        String kc = jTkatakunci4.getText();

        if(kc.equals("")){
            setJTable();
            jmldatatable();

        }
    }//GEN-LAST:event_jTtransaksibeliMouseEntered

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jCcariberdasarkan4;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTkatakunci4;
    private javax.swing.JTable jTtransaksibeli;
    private javax.swing.JLabel jmlData4;
    // End of variables declaration//GEN-END:variables
}
