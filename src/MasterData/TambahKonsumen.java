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
import besiusahamekar.koneksidb;

/**
 *
 * @author Banuajie
 */
public class TambahKonsumen extends javax.swing.JFrame {
Connection conn;
    /**
     * Creates new form TambahKonsumen
     */

public String idkonsumen,nama,alamat,kota,telepon,keterangan;

    public String getidkonsumen() {
        return idkonsumen;
    }

    public String getnama() {
        return nama;
    }

    public String getalamat() {
        return alamat;
    }
    public String getkota() {
        return kota;
    }

    public String gettelepon() {
        return telepon;
    }

    public String getketerangan() {
        return keterangan;
    }

    public TambahKonsumen() {
        initComponents();
        idkonsumen();
        jTketerangan.setVisible(false);
    }

    public void editData(){
      // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n "+jTidkonsumen.getText(), "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update tkonsumen set Nama = ?, Alamat= ?, Kota= ?, Telepon= ?, Keterangan= ? WHERE ID_Konsumen = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
        
        try {
                p.setString(1, jTnama.getText());
                p.setString(2, jTalamat.getText());
                p.setString(3, jTkota.getText());
                p.setString(4, jTtelepon.getText());
                p.setString(5, jTketerangan.getText());
                p.setString(6, jTidkonsumen.getText());
                                
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      
                      //JOptionPane.showMessageDialog(this,"Edit Data Berhasil");
          }

         // Memanggil Method   tampilDataKeJTable();
          } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
   }
   
    public void simpanData(){
//Connection conn;
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into tkonsumen values(?,?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
            
                st.setString(1, jTidkonsumen.getText());
                st.setString(2, jTnama.getText());
                st.setString(3, jTalamat.getText());
                st.setString(4, jTkota.getText());
                st.setString(5, jTtelepon.getText());
                st.setString(6, jTketerangan.getText());
               
            int rs=st.executeUpdate();

            if(rs>0){
            //JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    
            kosongkanfield();
            }
            st.close();
           
        }catch (SQLException sqle) {
           System.out.println("Input  Gagal = " + sqle.getMessage());
        }
        catch(Exception e){
           System.out.println("Koneksi Gagal " +e.getMessage());
        }
  }
    
    private void kosongkanfield(){
  
        jTidkonsumen.setText("");
        jTnama.setText("");
        jTalamat.setText("");
        jTkota.setText("");
        jTtelepon.setText("");
        jTketerangan.setText("");
        
        
  }
    
    public void tampildata(){
        
       
        jTidkonsumen.setText(idkonsumen);
        jTnama.setText(nama);
        jTalamat.setText(alamat);
        jTkota.setText(kota);
        jTtelepon.setText(telepon);
        jTketerangan.setText(keterangan);
        
        
        }
    
    
       public void idkonsumen(){
        
        try{
        conn =koneksidb.getkoneksi();
        Statement st= conn.createStatement();
        String sql= "select right(ID_Konsumen,2) as kd from tkonsumen order by kd desc";
        ResultSet rs=st.executeQuery(sql);
        
           if (rs.next()){
               
                int kode = Integer.parseInt(rs.getString("kd"))+1;
                if(kode>=10&&kode<=99)
                jTidkonsumen.setText("KNSM-"+"00"+Integer.toString(kode));
                else if(kode>=100&&kode<=999)
                jTidkonsumen.setText("KNSM-"+"0"+Integer.toString(kode));   
                               
                else
                    jTidkonsumen.setText("KNSM-"+"000"+Integer.toString(kode));
            }else{

                int kode = 1;

               jTidkonsumen.setText("KNSM-"+"000"+Integer.toString(kode));

                }

            rs.close();

        }  catch (SQLException sqle) {                   // Ketika Gagal Sql   // import java.sql.SQLException
           System.out.println("Proses Query Gagal = " + sqle);
           System.exit(0);
    }
           catch(Exception e){
           System.out.println("Koneksi DB Gagal " +e.getMessage());
           System.exit(0);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jBsimpan = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTtelepon = new javax.swing.JTextField();
        jTkota = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTnama = new javax.swing.JTextField();
        jTalamat = new javax.swing.JTextField();
        jTketerangan = new javax.swing.JTextField();
        jTidkonsumen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Konsumen");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Tambah Data Konsumen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("ID Konsumen");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel4.setText("Telepon");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Kota");

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel2.setText("Alamat");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText(":");

        jTtelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTteleponActionPerformed(evt);
            }
        });

        jTkota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTkotaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText(":");

        jTnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnamaActionPerformed(evt);
            }
        });

        jTalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTalamatActionPerformed(evt);
            }
        });

        jTketerangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTketeranganActionPerformed(evt);
            }
        });

        jTidkonsumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTidkonsumenActionPerformed(evt);
            }
        });
        jTidkonsumen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTidkonsumenFocusLost(evt);
            }
        });
        jTidkonsumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTidkonsumenKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel5.setText("Nama");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBsimpan)
                        .addGap(18, 18, 18)
                        .addComponent(jTketerangan, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTnama, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(jTidkonsumen)
                                    .addComponent(jTkota)
                                    .addComponent(jTalamat)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTtelepon)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)
                            .addComponent(jTidkonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(jTnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel17)
                            .addComponent(jTkota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(jTtelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBsimpan)
                    .addComponent(jTketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:
        String idkonsumen = jTidkonsumen.getText();
        String nama = jTnama.getText();
        String telepon = jTtelepon.getText();
        

        if(idkonsumen.equalsIgnoreCase("")||nama.equalsIgnoreCase("")||telepon.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this,"Ada Field Kosong");

        }else{
            String nilai = jBsimpan.getText();
            if(nilai=="Simpan"){
                simpanData();

            }else if(nilai=="Edit Data"){
                editData();
            }
            this.dispose();
        }
        idkonsumen();
    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jTteleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTteleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTteleponActionPerformed

    private void jTkotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTkotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTkotaActionPerformed

    private void jTnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnamaActionPerformed

    private void jTalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTalamatActionPerformed

    private void jTketeranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTketeranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTketeranganActionPerformed

    private void jTidkonsumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTidkonsumenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTidkonsumenActionPerformed

    private void jTidkonsumenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTidkonsumenFocusLost
        // TODO add your handling code here:
        String string = jTidkonsumen.getText();
        String upper = string.toUpperCase();
        jTidkonsumen.setText(upper);
    }//GEN-LAST:event_jTidkonsumenFocusLost

    private void jTidkonsumenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTidkonsumenKeyTyped
        // TODO add your handling code here:
        String string = jTidkonsumen.getText();
        String upper = string.toUpperCase();
        jTidkonsumen.setText(upper);
    }//GEN-LAST:event_jTidkonsumenKeyTyped

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
            java.util.logging.Logger.getLogger(TambahKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahKonsumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahKonsumen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBsimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTalamat;
    public javax.swing.JTextField jTidkonsumen;
    private javax.swing.JTextField jTketerangan;
    private javax.swing.JTextField jTkota;
    private javax.swing.JTextField jTnama;
    private javax.swing.JTextField jTtelepon;
    // End of variables declaration//GEN-END:variables
}
