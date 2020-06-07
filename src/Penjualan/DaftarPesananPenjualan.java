/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Penjualan;

import Pembelian.PesananPembelian;
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
public class DaftarPesananPenjualan extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
public String s;
String sql;
public String getStatus() {
        return s;
    }
    /**
     * Creates new form DaftarPesananPenjualan
     */
    public DaftarPesananPenjualan() {
        initComponents();
        setJTable();
        jmldatatable();
    }

    
     public void setJTable() {
    String [] JudulKolom={"No Pesanan","Tanggal","ID Konsumen","Nama Konsumen","Jml Pesanan","Total Pesanan","Keterangan","DP","Status"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTpesananbeli.setModel(tabModel);
    jTpesananbeli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTpesananbeli.getColumnModel().getColumn(0).setPreferredWidth(150);
    jTpesananbeli.getColumnModel().getColumn(1).setPreferredWidth(150);
    jTpesananbeli.getColumnModel().getColumn(2).setPreferredWidth(150);
    jTpesananbeli.getColumnModel().getColumn(3).setPreferredWidth(180);
    jTpesananbeli.getColumnModel().getColumn(4).setPreferredWidth(150);
    jTpesananbeli.getColumnModel().getColumn(5).setPreferredWidth(150);
    jTpesananbeli.getColumnModel().getColumn(6).setPreferredWidth(150);
    jTpesananbeli.getColumnModel().getColumn(7).setMinWidth(0);
    jTpesananbeli.getColumnModel().getColumn(7).setMaxWidth(0);
    jTpesananbeli.getColumnModel().getColumn(7).setWidth(0);
    jTpesananbeli.getColumnModel().getColumn(8).setMinWidth(0);
    jTpesananbeli.getColumnModel().getColumn(8).setMaxWidth(0);
    jTpesananbeli.getColumnModel().getColumn(8).setWidth(0);
    
    getDatapesanan();
    
    }
    
    public void getDatapesanan() {
        // import java.sql.connection
        
        
            try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            sql="Select * from tpesananjual where Status = '"+s+"'";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String Nopesanan,IDkonsumen,nama,jmlpesan,totalpesan,keterangan,DP,status;
                     int no=0;
                        while(rs.next()){
                        no=no+1;
                       Nopesanan=rs.getString("No_Pesanan");
                       Date tanggal=rs.getDate("Tanggal");
                       IDkonsumen=rs.getString("ID_Konsumen");
                       nama=rs.getString("Nama");
                       jmlpesan=rs.getString("Jml_Pesan");
                       totalpesan=rs.getString("Total_Pesan");
                       keterangan=rs.getString("Keterangan");
                       DP=rs.getString("DP");
                       status=rs.getString("Status");
                       
                        SimpleDateFormat Format = new SimpleDateFormat("dd-MM-yyyy");
                        String d = Format.format(tanggal);
                        
         Object Data[]={Nopesanan,d,IDkonsumen,nama,jmlpesan,totalpesan,keterangan,DP,status};

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
        katakunci ="Select * from tpesananjual WHERE No_Pesanan = '" +jTkatakunci.getText() + "'";
        else if(pilih==1)
        katakunci ="Select * from tpesananjual WHERE Tanggal Like '%" +jTkatakunci.getText() + "%'";
        else if(pilih==2)
        katakunci ="Select * from tpesananjual WHERE ID_Konsumen Like '%" +jTkatakunci.getText() + "%'";
        else
        katakunci ="Select * from tpesananjual WHERE Nama Like '%" +jTkatakunci.getText() + "%'";

        ResultSet rs=st.executeQuery(katakunci);

        hapusIsiJTable();
        // Menampilkan ke JTable  melalui tabModel
                     String Nopesanan,tanggal,IDkonsumen,nama,jmlpesan,totalpesan,keterangan,status;
                     int no=0;
                        while(rs.next()){
                        no=no+1;
                       Nopesanan=rs.getString("No_Pesanan");
                       tanggal=rs.getString("Tanggal");
                       IDkonsumen=rs.getString("ID_Konsumen");
                       nama=rs.getString("Nama");
                       jmlpesan=rs.getString("Jml_Pesan");
                       totalpesan=rs.getString("Total_Pesan");
                       keterangan=rs.getString("Keterangan");
                       status=rs.getString("Status");
                       
                       
         Object Data[]={Nopesanan,tanggal,IDkonsumen,nama,jmlpesan,totalpesan,keterangan,status};

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
         int rows = jTpesananbeli.getRowCount();
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
   int row = jTpesananbeli.getSelectedRow();
   // Mengambil data yang dipilih pada JTable
   String nopes = tabModel.getValueAt(row, 0).toString();
   // Konfirmasi sebelum melakukan penghapusan data
    
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus Data\n = '" + nopes.toString() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM tpesananjual WHERE No_Pesanan = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, nopes.toString());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTpesananbeli = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setTitle("Data Pesanan Penjualan Material");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Data Pesanan Penjualan Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

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

        jCcariberdasarkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Pesan", "Tanggal", "ID Konsumen", "Nama Konsumen" }));

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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jTpesananbeli.setAutoCreateRowSorter(true);
        jTpesananbeli.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTpesananbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Pesanan", "Tanggal", "Tgl Kirim", "Kode Supplier", "Nama Supplier", "Jml Pesanan", "Total Pesanan", "Keterangan"
            }
        ));
        jTpesananbeli.setFocusable(false);
        jTpesananbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTpesananbeliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTpesananbeliMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTpesananbeli);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(224, 224, 224)
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

    private void jTpesananbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTpesananbeliMouseClicked
        // TODO add your handling code here:
         int rows = jTpesananbeli.getSelectedRow();
        this.setTitle(tabModel.getValueAt(rows, 0).toString());
        if (evt.getClickCount() == 2) {
            PesananPembelian pp = new PesananPembelian();
            Point pnt = evt.getPoint();
            int row = jTpesananbeli.rowAtPoint(pnt);
            //do something
            int tabelsupplier = jTpesananbeli.getSelectedRow();
            pp.nopes = jTpesananbeli.getValueAt(tabelsupplier, 0).toString();

            pp.tampildatapesanan();
            pp.setenable();
            pp.setVisible(true);

            //this.dispose();

        }
    }//GEN-LAST:event_jTpesananbeliMouseClicked

    private void jTpesananbeliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTpesananbeliMouseEntered
        // TODO add your handling code here:
        String kc = jTkatakunci.getText();

        if(kc.equals("")){
            setJTable();
            jmldatatable();

        }
    }//GEN-LAST:event_jTpesananbeliMouseEntered

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JButton jButton5;
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
    private javax.swing.JTable jTpesananbeli;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
