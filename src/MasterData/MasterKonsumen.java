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
public class MasterKonsumen extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
    /**
     * Creates new form MasterKonsumen
     */
    public MasterKonsumen() {
        initComponents();
        setJTable();
        jmldatatable();
    }
    
        private void setJTable() {
    String [] JudulKolom={"ID Konsumen","Nama","Alamat","Kota","Telepon","Keterangan"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false, false, false, false, false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTkonsumen.setModel(tabModel);
    jTkonsumen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTkonsumen.getColumnModel().getColumn(0).setPreferredWidth(100);
    jTkonsumen.getColumnModel().getColumn(1).setPreferredWidth(250);
    jTkonsumen.getColumnModel().getColumn(2).setPreferredWidth(200);
    jTkonsumen.getColumnModel().getColumn(3).setPreferredWidth(100);
    jTkonsumen.getColumnModel().getColumn(4).setPreferredWidth(100);
    jTkonsumen.getColumnModel().getColumn(5).setMinWidth(0);
    jTkonsumen.getColumnModel().getColumn(5).setMaxWidth(0);
    jTkonsumen.getColumnModel().getColumn(5).setWidth(0);
    
    
    getDatakonsumen();
    }
    
    private void getDatakonsumen() {
        // import java.sql.connection
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tkonsumen";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String idkonsumen,nama,alamat,kota,telepon,keterangan;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       idkonsumen=rs.getString("ID_Konsumen");
                       nama=rs.getString("Nama");
                       alamat=rs.getString("Alamat");
                       kota=rs.getString("Kota");
                       telepon=rs.getString("Telepon");
                       keterangan=rs.getString("Keterangan");
                       
        
         Object Data[]={idkonsumen,nama,alamat,kota,telepon,keterangan};
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
        katakunci ="Select * from tkonsumen WHERE ID_Konsumen = '" +jTkatakunci.getText() + "'";
        else if(pilih==1)
        katakunci ="Select * from tkonsumen WHERE Nama Like '%" +jTkatakunci.getText() + "%'";
        else if(pilih==2)
        katakunci ="Select * from tkonsumen WHERE Alamat Like '%" +jTkatakunci.getText() + "%'";
        else
        katakunci ="Select * from tkonsumen WHERE Kota Like '%" +jTkatakunci.getText() + "%'";

        ResultSet rs=st.executeQuery(katakunci);

        hapusIsiJTable();
        // Menampilkan ke JTable  melalui tabModel
                     String idkonsumen,nama,alamat,kota,telepon,keterangan;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       idkonsumen=rs.getString("ID_Konsumen");
                       nama=rs.getString("Nama");
                       alamat=rs.getString("Alamat");
                       kota=rs.getString("Kota");
                       telepon=rs.getString("Telepon");
                       keterangan=rs.getString("Keterangan");
                       
        
         Object Data[]={idkonsumen,nama,alamat,kota,telepon,keterangan};
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
         int rows = jTkonsumen.getRowCount();
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
   int row = jTkonsumen.getSelectedRow();
   // Mengambil data yang dipilih pada JTable
   String ikonsumen = tabModel.getValueAt(row, 0).toString();
   // Konfirmasi sebelum melakukan penghapusan data
    
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus Data\n = '" + ikonsumen.toString() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM tkonsumen WHERE ID_Konsumen = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, ikonsumen.toString());
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

        jBhapus = new javax.swing.JButton();
        jBedit = new javax.swing.JButton();
        jBtambah = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTkonsumen = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTkatakunci = new javax.swing.JTextField();
        jBcari = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCcariberdasarkan = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jmlData = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Master Data Konsumen");
        setToolTipText("");

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

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Exit.png"))); // NOI18N
        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTkonsumen.setAutoCreateRowSorter(true);
        jTkonsumen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTkonsumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Konsumen", "Nama", "Alamat", "Kota", "Telepon", "Keterangan"
            }
        ));
        jTkonsumen.setFocusable(false);
        jTkonsumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTkonsumenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTkonsumenMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTkonsumen);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Master Data Konsumen", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12), java.awt.Color.black)); // NOI18N

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

        jBcari.setText("Cari");
        jBcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcariActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Cari Berdasarkan");

        jCcariberdasarkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID Konsumen", "Nama", "Alamat", "Kota" }));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText(":");

        jmlData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jmlData.setText("0");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Jumlah Data : ");

        jLabel3.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCcariberdasarkan, 0, 160, Short.MAX_VALUE)
                    .addComponent(jTkatakunci))
                .addGap(18, 18, 18)
                .addComponent(jBcari, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmlData))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTkatakunci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBcari)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jCcariberdasarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jmlData)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBedit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBhapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtambah)
                    .addComponent(jBedit)
                    .addComponent(jBhapus)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:
        jTkatakunci.setText("");
        TambahKonsumen fDB = new TambahKonsumen();
        fDB.dispose();

        try {

            int tabelpelanngan = jTkonsumen.getSelectedRow();

            fDB.idkonsumen = jTkonsumen.getValueAt(tabelpelanngan, 0).toString();
            fDB.nama = jTkonsumen.getValueAt(tabelpelanngan, 1).toString();
            fDB.alamat = jTkonsumen.getValueAt(tabelpelanngan, 2).toString();
            fDB.kota = jTkonsumen.getValueAt(tabelpelanngan, 3).toString();
            fDB.telepon = jTkonsumen.getValueAt(tabelpelanngan, 4).toString();
            fDB.keterangan= jTkonsumen.getValueAt(tabelpelanngan, 5).toString();

            fDB.jTidkonsumen.setEnabled(false);
            fDB.jBsimpan.setText("Edit Data");

            fDB.setVisible(true);
            fDB.tampildata();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);

        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:
        TambahKonsumen tm=new TambahKonsumen();
        //MenuPanel.add(dp);
        tm.setVisible(true);
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTkonsumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTkonsumenMouseClicked
        // TODO add your handling code here:

         int rows = jTkonsumen.getSelectedRow();
        this.setTitle(tabModel.getValueAt(rows, 0).toString());
        
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);

        //double click handle

        TambahKonsumen fDB = new TambahKonsumen();

        if (evt.getClickCount() == 2) {
            Point pnt = evt.getPoint();
            int row = jTkonsumen.rowAtPoint(pnt);
            //do something
            fDB.dispose();
            //fDB.fAB = this;
            int tabelpelanngan = jTkonsumen.getSelectedRow();

            fDB.idkonsumen = jTkonsumen.getValueAt(tabelpelanngan, 0).toString();
            fDB.nama = jTkonsumen.getValueAt(tabelpelanngan, 1).toString();
            fDB.alamat = jTkonsumen.getValueAt(tabelpelanngan, 2).toString();
            fDB.kota = jTkonsumen.getValueAt(tabelpelanngan, 3).toString();
            fDB.telepon = jTkonsumen.getValueAt(tabelpelanngan, 4).toString();
            fDB.keterangan= jTkonsumen.getValueAt(tabelpelanngan, 5).toString();

            fDB.jTidkonsumen.setEnabled(false);
            fDB.jBsimpan.setText("Edit Data");

            fDB.setVisible(true);
            fDB.tampildata();
        }
    }//GEN-LAST:event_jTkonsumenMouseClicked

    private void jTkonsumenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTkonsumenMouseEntered
        // TODO add your handling code here:
        String kc = jTkatakunci.getText();

        if(kc.equals("")){
            setJTable();
            jmldatatable();
            jBedit.setEnabled(false);
            jBhapus.setEnabled(false);
        }
    }//GEN-LAST:event_jTkonsumenMouseEntered

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcari;
    public javax.swing.JButton jBedit;
    public javax.swing.JButton jBhapus;
    public javax.swing.JButton jBtambah;
    public javax.swing.JButton jButton5;
    private javax.swing.JComboBox jCcariberdasarkan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTkatakunci;
    private javax.swing.JTable jTkonsumen;
    private javax.swing.JLabel jmlData;
    // End of variables declaration//GEN-END:variables
}
