/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterData;

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
public class PopJenisMaterial extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
    /**
     * Creates new form PopJenisMaterial
     */
    public PopJenisMaterial() {
        initComponents();
        setJTable();
    }

    
    private void setJTable() {
    String [] JudulKolom={"Kode","keterangan"};
    
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {true, true};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTjenismaterial.setModel(tabModel);
    jTjenismaterial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTjenismaterial.getColumnModel().getColumn(0).setPreferredWidth(180);
    jTjenismaterial.getColumnModel().getColumn(1).setPreferredWidth(250);
    
    
    getDatajenis();
       }
    
    private void getDatajenis() {
        // import java.sql.connection
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from jenismaterial";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String kode,keterangan;
                     
                        while(rs.next()){
                       
                       kode=rs.getString("Kode");
                       keterangan=rs.getString("Keterangan");
        
         Object Data[]={kode,keterangan};
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
    
   class cektabledata{
       
       int row = jTjenismaterial.getSelectedRow();
        // Mengambil data yang dipilih pada JTable
        String kode = tabModel.getValueAt(row, 0).toString();
        String keterangan = tabModel.getValueAt(row, 1).toString();
        
        
   } 
    
   public void simpanData(){
   cektabledata ctbd =new cektabledata();
//Connection conn;
     try{
                     
            conn=koneksidb.getkoneksi();
            String sql="Insert into jenismaterial values(?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, ctbd.kode);
                st.setString(2, ctbd.keterangan);
               
            int rs=st.executeUpdate();
            
            if(rs>0){
            //JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    
            getDatajenis();
            setJTable();
            jBsimpan.setEnabled(false);
            }
            st.close();
            
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
           JOptionPane.showMessageDialog(this,"Input Gagal");
           setJTable();
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
           JOptionPane.showMessageDialog(this,"Koneksi Gagal");
           setJTable();
        }
  } 
   
     private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);

  }
  }
   
 
     
  public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    cektabledata ctbd =new cektabledata();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus Data\n Barang = '" + ctbd.kode.toString() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM jenismaterial WHERE kode = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, ctbd.kode.toString());
        int rs=st.executeUpdate();
        if(rs>0){
        
        JOptionPane.showMessageDialog(this,"Data Sudah dihapus");
        setJTable();
        }
        
        
      } catch (Exception se) {  // Silahkan tambahkan catch Exception yang lain
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTjenismaterial = new javax.swing.JTable();
        jBsimpan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTjenismaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kode", "Keterangan"
            }
        ));
        jTjenismaterial.setToolTipText("Double Klik data yang akan di input");
        jTjenismaterial.setName(""); // NOI18N
        jTjenismaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTjenismaterialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTjenismaterial);

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.setEnabled(false);
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(109, 109, 109))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jBsimpan)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTjenismaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTjenismaterialMouseClicked
        // TODO add your handling code here:
        int row = jTjenismaterial.getSelectedRow();
        this.setTitle(tabModel.getValueAt(row, 0).toString());
    }//GEN-LAST:event_jTjenismaterialMouseClicked

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:

        simpanData();
        setJTable();

    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tabModel.insertRow(jTjenismaterial.getRowCount(),new Object[]  {"",""});

        jBsimpan.setEnabled(true);

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBsimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTjenismaterial;
    // End of variables declaration//GEN-END:variables
}
