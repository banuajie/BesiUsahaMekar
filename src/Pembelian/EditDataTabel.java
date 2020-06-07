/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pembelian;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import besiusahamekar.koneksidb;

/**
 *
 * @author Banuajie
 */
public class EditDataTabel extends javax.swing.JFrame {
Connection conn;
String stotal;
    /**
     * Creates new form EditPesanBeli
     */

public String no_retur,no_transaksi,kodeitem,namaitem,jumlah,harga,total;
public String pilih;

 public String getretur() {
        return no_retur;
    }
    public String getKode() {
        return kodeitem;
    }

    public String getNama() {
        return namaitem;
    }

    public String getjumlah() {
        return jumlah;
    }
    public String getharga() {
        return harga;
    }

    public String gettotal() {
        return total;
    }

        public String gettrans() {
        return no_transaksi;
    }
        
       public String getselected() {
        return pilih;
    }    
    /**
     * Creates new form EditPesananPembelian
     */
    public EditDataTabel() {
        initComponents();
    }

    public void tampildata(){
        jTeditkode.setText(kodeitem);
        jTnama.setText(namaitem);
        jTjumlah.setText(jumlah);
        jTharga.setText(harga);
        jTtotal.setText(total);
        }
    
    public void hitungtotal(){
        int jumlah = Integer.parseInt(jTjumlah.getText());
        int harga = Integer.parseInt(jTharga.getText());
        int total = jumlah * harga ;
        stotal= String.valueOf(total);
        jTtotal.setText(stotal);
    }
    
    public void rubahData() {
        
    // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n "+jTeditkode.getText(), "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update ttemppesananbeli set Nama = ?, Jumlah= ?, Harga= ?, Total= ? WHERE Kode_Item = ? ";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTnama.getText());
           p.setString(2, jTjumlah.getText());
           p.setString(3, jTharga.getText());
           p.setString(4, jTtotal.getText());
           p.setString(5, jTeditkode.getText());
           
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      
                     // JOptionPane.showMessageDialog(this,"Edit Data Berhasil");
          }

         // Memanggil Method   tampilDataKeJTable();
          } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
    
        
    
  }
    
    public void rubahDatatrans() {
        
    // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n "+jTeditkode.getText(), "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update ttemptransaksibeli set Nama = ?, Jumlah= ?, Harga= ?, Total= ? WHERE Kode_Item = ? and No_Transaksi = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTnama.getText());
           p.setString(2, jTjumlah.getText());
           p.setString(3, jTharga.getText());
           p.setString(4, jTtotal.getText());
           p.setString(5, jTeditkode.getText());
           p.setString(6, no_transaksi);
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      
                     // JOptionPane.showMessageDialog(this,"Edit Data Berhasil");
          }

         // Memanggil Method   tampilDataKeJTable();
          } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
    
        
    
  }
    
    
     public void rubahDataitemmasuk() {
        
    // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n "+jTeditkode.getText(), "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update ttempmaterialmasuk set Nama = ?, Jumlah= ?, Harga= ?, Total= ? WHERE Kode_Item = ? and No_Transaksi = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTnama.getText());
           p.setString(2, jTjumlah.getText());
           p.setString(3, jTharga.getText());
           p.setString(4, jTtotal.getText());
           p.setString(5, jTeditkode.getText());
           p.setString(6, no_transaksi);
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      
                     // JOptionPane.showMessageDialog(this,"Edit Data Berhasil");
          }

         // Memanggil Method   tampilDataKeJTable();
          } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
    
        
    
  }
    
     public void rubahDataretur() {
    
         String noretur = no_retur;
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update ttempreturbeli set Jumlah= ? , Total=? WHERE No_Retur = ? and Kode_Item = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTjumlah.getText());
           p.setString(2, jTtotal.getText());
           p.setString(3, noretur);
           p.setString(4, jTeditkode.getText());
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
          
         // Memanggil Method   tampilDataKeJTable();
          } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsimpan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTtotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTnama = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTjumlah = new javax.swing.JTextField();
        jTharga = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTeditkode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jsimpan.setText("Simpan");
        jsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsimpanActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Edit Data", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12))); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Jumlah");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel8.setText("Total");

        jTtotal.setToolTipText("");
        jTtotal.setEnabled(false);
        jTtotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTtotalFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setText("Harga");

        jTnama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTnamaFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText(":");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("Kode Material");

        jTjumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTjumlahFocusLost(evt);
            }
        });
        jTjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTjumlahKeyTyped(evt);
            }
        });

        jTharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jThargaActionPerformed(evt);
            }
        });
        jTharga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jThargaFocusLost(evt);
            }
        });
        jTharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jThargaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Nama Material");

        jTeditkode.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(25, 25, 25)
                        .addComponent(jTnama))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(24, 24, 24)
                        .addComponent(jTeditkode))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTjumlah)
                            .addComponent(jTharga)
                            .addComponent(jTtotal))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTeditkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(jTnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel7)
                    .addComponent(jTjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3)
                    .addComponent(jTharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8)
                    .addComponent(jTtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jsimpan)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsimpan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsimpanActionPerformed
        // TODO add your handling code here:
        TransaksiPembelian tp = new TransaksiPembelian();
        String nilai = jsimpan.getText();
        if(nilai=="Simpan"){

            String editkode = jTeditkode.getText();
            String nama = jTnama.getText();
            String jumlah = jTjumlah.getText();
            String harga = jTharga.getText();
            String total = jTtotal.getText();

            if(editkode.equalsIgnoreCase("")||nama.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||harga.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Ada Field Kosong");
                tampildata();
            }else{
                rubahData();
                this.dispose();
            }

        }else if(nilai=="Edit"){

            String editkode = jTeditkode.getText();
            String nama = jTnama.getText();
            String jumlah = jTjumlah.getText();
            String harga = jTharga.getText();
            String total = jTtotal.getText();

            if(editkode.equalsIgnoreCase("")||nama.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||harga.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Ada Field Kosong");
                tampildata();
            }else{
                rubahDatatrans();

                tp.getDatatransaksi();
                tp.jmldatatable();

                this.dispose();
            }
        }else if(nilai=="Edit Item"){

            String editkode = jTeditkode.getText();
            String nama = jTnama.getText();
            String jumlah = jTjumlah.getText();
            String harga = jTharga.getText();
            String total = jTtotal.getText();

            if(editkode.equalsIgnoreCase("")||nama.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||harga.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Ada Field Kosong");
                tampildata();
            }else{
                rubahDataitemmasuk();

                tp.getDatatransaksi();
                tp.jmldatatable();

                this.dispose();
            }
        }else if(nilai=="Edit Retur"){

            String editkode = jTeditkode.getText();
            String nama = jTnama.getText();
            String jumlah = jTjumlah.getText();
            String harga = jTharga.getText();
            String total = jTtotal.getText();

            if(editkode.equalsIgnoreCase("")||nama.equalsIgnoreCase("")||jumlah.equalsIgnoreCase("")||harga.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Ada Field Kosong");
                tampildata();
            }else{
                ReturPembelian rb = new ReturPembelian();
                rubahDataretur();
                //tp.getDatatransaksi();

                //rb.getDatareturbeli();
                rb.tampilDataKeJTable();
                rb.jmldatatable();
                rb.hitungsubtotal();
                rb.hitungjum();

                this.dispose();
            }
        }

    }//GEN-LAST:event_jsimpanActionPerformed

    private void jTtotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTtotalFocusLost
        // TODO add your handling code here:
        // nominal = jTtotal.getText();
        // formatnumber();
        // jTtotal.setText(formattedNumber);
    }//GEN-LAST:event_jTtotalFocusLost

    private void jTnamaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTnamaFocusLost
        // TODO add your handling code here:
        //nominal = jTnama.getText();
        // formatnumber();
        // jTnama.setText(formattedNumber);
    }//GEN-LAST:event_jTnamaFocusLost

    private void jTjumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTjumlahFocusLost
        // TODO add your handling code here:
        hitungtotal();
    }//GEN-LAST:event_jTjumlahFocusLost

    private void jThargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jThargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jThargaActionPerformed

    private void jThargaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jThargaFocusLost
        hitungtotal();
    }//GEN-LAST:event_jThargaFocusLost

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1MouseClicked

    private void jTjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTjumlahKeyTyped
        // TODO add your handling code here:
                     char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_jTjumlahKeyTyped

    private void jThargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jThargaKeyTyped
        // TODO add your handling code here:
                     char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
      }
    }//GEN-LAST:event_jThargaKeyTyped

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
            java.util.logging.Logger.getLogger(EditDataTabel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDataTabel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDataTabel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDataTabel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDataTabel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTeditkode;
    public javax.swing.JTextField jTharga;
    private javax.swing.JTextField jTjumlah;
    public javax.swing.JTextField jTnama;
    private javax.swing.JTextField jTtotal;
    public javax.swing.JButton jsimpan;
    // End of variables declaration//GEN-END:variables
}
