/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Penjualan;

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
    String [] JudulKolom={"No Transaksi","No Pesanan","Tanggal","ID Konsumen","Nama Konsumen","Jml Item","Total Transaksi","Jml Bayar","Sisa Bayar","Keterangan","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false,false, false, false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTtransaksibeli.setModel(tabModel);
    jTtransaksibeli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTtransaksibeli.getColumnModel().getColumn(0).setPreferredWidth(180);
    jTtransaksibeli.getColumnModel().getColumn(1).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(1).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(1).setWidth(0);
    
    jTtransaksibeli.getColumnModel().getColumn(2).setPreferredWidth(180);
    jTtransaksibeli.getColumnModel().getColumn(3).setPreferredWidth(100);
    jTtransaksibeli.getColumnModel().getColumn(4).setPreferredWidth(180);
    jTtransaksibeli.getColumnModel().getColumn(5).setPreferredWidth(150);
    jTtransaksibeli.getColumnModel().getColumn(6).setPreferredWidth(150);
    
    jTtransaksibeli.getColumnModel().getColumn(7).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(7).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(7).setWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(8).setMinWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(8).setMaxWidth(0);
    jTtransaksibeli.getColumnModel().getColumn(8).setWidth(0);
    
    jTtransaksibeli.getColumnModel().getColumn(9).setPreferredWidth(150);
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
            String sql="Select * from ttransaksijual where Status = '1'";
            ResultSet rs=st.executeQuery(sql);

            // Menampilkan ke JTable  melalui tabModel
                     String Notransaksi,Nopesanan,IDSupplier,namasup,jmlitem,totaltransaksi,jmlbayar,sisabayar,keterangan,status;
                     int no=0;
                        while(rs.next()){
                        no=no+1;
                        Notransaksi=rs.getString("No_Transaksi");
                       Nopesanan=rs.getString("No_Pesanan");
                       Date tanggal=rs.getDate("Tanggal");
                       IDSupplier=rs.getString("ID_Konsumen");
                       namasup=rs.getString("Nama");
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
         int pilih = jCcariberdasarkan.getSelectedIndex();
         try {
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        if(pilih==0)
        katakunci ="Select * from ttransaksijual WHERE No_Transaksi = '" +jTkatakunci.getText() + "'";
        else if(pilih==1)
        katakunci ="Select * from ttransaksijual WHERE No_Pesanan ='" +jTkatakunci.getText() + "%'";
        else if(pilih==2)
        katakunci ="Select * from ttransaksijual WHERE Tanggal Like '%" +jTkatakunci.getText() + "%'";
        else
        katakunci ="Select * from ttransaksijual WHERE ID_Konsumen Like '%" +jTkatakunci.getText() + "%'";

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
                       IDSupplier=rs.getString("ID_Konsumen");
                       namasup=rs.getString("Nama");
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
         jmlData.setText(jmls);
       
             
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
        String sql = "DELETE FROM ttransaksijual WHERE No_Transaksi = ?";
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTkatakunci = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCcariberdasarkan = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtransaksibeli = new javax.swing.JTable();

        setTitle("Data Transaksi");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Data Transaksi", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

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

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Cari Berdasarkan");

        jCcariberdasarkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Transaksi", "No Pesan", "Tanggal", "ID Supplier" }));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText(":");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Jumlah Data : ");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jLabel3.setText(":");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTkatakunci)
                    .addComponent(jCcariberdasarkan, 0, 160, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jmlData)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTkatakunci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jCcariberdasarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String cari = jTkatakunci.getText();
        if (cari.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Kata Kunci Harus Di Isi ");setJTable();

        }
        else{
            cari();
            jmldatatable();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTtransaksibeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransaksibeliMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            ReturPenjualan tp = new ReturPenjualan();
            Point pnt = evt.getPoint();
            int row = jTtransaksibeli.rowAtPoint(pnt);
            //do something
            int tabelsupplier = jTtransaksibeli.getSelectedRow();
            tp.no_transaksi = jTtransaksibeli.getValueAt(tabelsupplier, 0).toString();

            tp.tampildatatransaksi();
            tp.getDatatransaksijual();
            tp.getNamakonsumen();
            tp.setenable();
            tp.jCkodeitem.setEnabled(true);
            tp.setVisible(true);

            //this.dispose();

        }
    }//GEN-LAST:event_jTtransaksibeliMouseClicked

    private void jTtransaksibeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtransaksibeliMouseEntered
        // TODO add your handling code here:
        String kc = jTkatakunci.getText();

        if(kc.equals("")){
            setJTable();
            jmldatatable();

        }
    }//GEN-LAST:event_jTtransaksibeliMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCcariberdasarkan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTkatakunci;
    private javax.swing.JTable jTtransaksibeli;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
