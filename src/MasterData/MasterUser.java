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
import org.mindrot.jbcrypt.BCrypt;
import besiusahamekar.koneksidb;

/**
 *
 * @author Banuajie
 */
public class MasterUser extends javax.swing.JInternalFrame {
public DefaultTableModel tabModel;
Connection conn;
    /**
     * Creates new form MasterUser
     */
    public MasterUser() {
        initComponents();
        setJTable();
    }
    
    private void setJTable() {
    String [] JudulKolom={"ID User","Nama","Bagian","Password","Konfirmasi"};
    tabModel = new DefaultTableModel(null, JudulKolom){
                  boolean[] canEdit = new boolean [] {false, false,false, false,false};
                  @Override
                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
                  }
              };
    jTuser.setModel(tabModel);
   
    jTuser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTuser.getColumnModel().getColumn(0).setPreferredWidth(100);
    jTuser.getColumnModel().getColumn(1).setPreferredWidth(203);
    jTuser.getColumnModel().getColumn(2).setMinWidth(0);
    jTuser.getColumnModel().getColumn(2).setMaxWidth(0);
    jTuser.getColumnModel().getColumn(2).setWidth(0);
    jTuser.getColumnModel().getColumn(3).setMinWidth(0);
    jTuser.getColumnModel().getColumn(3).setMaxWidth(0);
    jTuser.getColumnModel().getColumn(3).setWidth(0);
    jTuser.getColumnModel().getColumn(4).setMinWidth(0);
    jTuser.getColumnModel().getColumn(4).setMaxWidth(0);
    jTuser.getColumnModel().getColumn(4).setWidth(0);
    getDatauser();
       }
    
    private void getDatauser() {
        // import java.sql.connection
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tuser";
            ResultSet rs=st.executeQuery(sql);

                // Menampilkan ke JTable  melalui tabModel
                     String iduser,nama,bagian,password,konfirmasi;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       iduser=rs.getString("ID_User");
                       nama=rs.getString("Nama");
                       bagian=rs.getString("Bagian");
                       password=rs.getString("Password");
                       konfirmasi=rs.getString("Konfirmasi");
                       
        
         Object Data[]={iduser,nama,bagian,password,konfirmasi};
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
    
    public void simpanData(){
//Connection conn;
       
     try{
            conn=koneksidb.getkoneksi();
            String sql="Insert into tuser values(?,?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sql);
                st.setString(1, jTiduser.getText());
                st.setString(2, jTnama.getText());
                st.setString(3, jCbagian.getSelectedItem().toString());
                st.setString(4, BCrypt.hashpw(jTpassword.getText(), BCrypt.gensalt()));
                st.setString(5, BCrypt.hashpw(jTkonfirmasi.getText(), BCrypt.gensalt()));
                
                
            int rs=st.executeUpdate();

            if(rs>0){
            //JOptionPane.showMessageDialog(this,"Input Berhasil");
      	    
            tampilDataKeJTable();
            
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
    
    private void hapusIsiJTable() {

         int row = tabModel.getRowCount();
         for (int i = 0; i < row; i++) {
         tabModel.removeRow(0);
         }
         
         int row2 = tabModel.getRowCount();
         for (int x = 0; x < row2; x++) {
         tabModel.removeRow(0);

         }
  }
    
    public void tampilDataKeJTable() {
    
    try {
            hapusIsiJTable();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tuser";
            ResultSet rs=st.executeQuery(sql);
         
        
         // Menampilkan ke JTable  melalui tabModel
                     String iduser,nama,bagian,password,konfirmasi;
                     //int no=0;
                        while(rs.next()){
                       //no=no+1;
                       iduser=rs.getString("ID_User");
                       nama=rs.getString("Nama");
                       bagian=rs.getString("Bagian");
                       password=rs.getString("Password");
                       konfirmasi=rs.getString("Konfirmasi");
                       
        
         Object Data[]={iduser,nama,bagian,password,konfirmasi};
         tabModel.addRow(Data);
      }
                        st.close();
          rs.close();
  }
    catch (Exception e) {}
}
    
    private void kosongkanfield(){
  
        jTiduser.setText("");
        
        jTpassword.setText("");
        jTkonfirmasi.setText("");
       
  }
    
    public void ambilData_dari_JTable() {
        int row = jTuser.getSelectedRow();

         // Mengambil data yang dipilih pada JTable
        String iduser = tabModel.getValueAt(row, 0).toString();
        String nama = tabModel.getValueAt(row, 1).toString();
        String bagian = tabModel.getValueAt(row, 2).toString();
        String password = tabModel.getValueAt(row, 3).toString();
        String konfirmasi = tabModel.getValueAt(row, 4).toString();
                
            jTiduser.setText(iduser);
            jCbagian.setSelectedItem(bagian);
            jTpassword.setText(password);
            jTkonfirmasi.setText(konfirmasi);
            
     
  }
    
    public void rubahData() {
      
      
    // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n ", "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update tuser set Nama = ?, bagian=?, Password= ?, Konfirmasi= ? WHERE ID_User = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTiduser.getText());
           p.setString(2, jTnama.getText());
           p.setString(3, jCbagian.getSelectedItem().toString());
           p.setString(4, BCrypt.hashpw(jTpassword.getText(), BCrypt.gensalt()));
           p.setString(5, BCrypt.hashpw(jTkonfirmasi.getText(), BCrypt.gensalt()));
           
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      tampilDataKeJTable();
                      JOptionPane.showMessageDialog(this,"Edit Data Berhasil");
          }

         // Memanggil Method   tampilDataKeJTable();
          tampilDataKeJTable();
          setJTable();
          
          
      
        } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
    }
    
        
    
  }
    
    public void hapus_Data() {
    // Konfirmasi sebelum melakukan penghapusan data
    ambilData_dari_JTable();
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Menghapus = '" + jTiduser.getText() +
        "'", "Konfirmasi Menghapus Data",JOptionPane.YES_NO_OPTION);
    if (ok == 0) {     // Apabila tombol OK ditekan
      try {
        conn=koneksidb.getkoneksi();
        String sql = "DELETE FROM tuser WHERE ID_User = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, jTiduser.getText());
        int rs=st.executeUpdate();
        if(rs>0){
        tampilDataKeJTable();
        
        setJTable();
        //JOptionPane.showMessageDialog(this,"Data Sudah dihapus");
        }
        
        kosongkanfield();
        
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

        jLabel18 = new javax.swing.JLabel();
        jTpassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jBtambah = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jBedit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTuser = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTiduser = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTkonfirmasi = new javax.swing.JPasswordField();
        jBhapus = new javax.swing.JButton();
        jCbagian = new javax.swing.JComboBox();
        jTnama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Master Data User");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText(":");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel5.setText("Bagian");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel9.setText("Konfirmasi");

        jBtambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        jBtambah.setText("Tambah");
        jBtambah.setToolTipText("");
        jBtambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtambahActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jBedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tools.png"))); // NOI18N
        jBedit.setText("Edit");
        jBedit.setToolTipText("");
        jBedit.setEnabled(false);
        jBedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditActionPerformed(evt);
            }
        });

        jScrollPane1.setToolTipText("");

        jTuser.setAutoCreateRowSorter(true);
        jTuser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "User ID", "Sebagai"
            }
        ));
        jTuser.setEditingRow(0);
        jTuser.setFocusable(false);
        jTuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTuserMouseClicked(evt);
            }
        });
        jTuser.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTuserAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTuser);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText(":");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jTiduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTiduserActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel7.setText("Password");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel1.setText("ID User");

        jBhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bin_Full.png"))); // NOI18N
        jBhapus.setText("Hapus");
        jBhapus.setEnabled(false);
        jBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBhapusActionPerformed(evt);
            }
        });

        jCbagian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Bagian Kasir", "Bagian Gudang" }));

        jTnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTnamaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel11.setText("Nama");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTnama)
                            .addComponent(jTiduser)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBedit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBhapus))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTpassword))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCbagian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTkonfirmasi)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(jTiduser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14)
                    .addComponent(jCbagian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel17)
                    .addComponent(jTpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel18)
                    .addComponent(jTkonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBhapus)
                    .addComponent(jBedit)
                    .addComponent(jBtambah))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtambahActionPerformed
        // TODO add your handling code here:

        String iduser = jTiduser.getText();
        String nama = jTnama.getText();
        String bagian = jCbagian.getSelectedItem().toString();
        String password = jTpassword.getText();
        String konfirmasi = jTkonfirmasi.getText();

        if(password.equalsIgnoreCase(konfirmasi)){
            if(iduser.equalsIgnoreCase("")||nama.equalsIgnoreCase("")||password.equalsIgnoreCase("")||konfirmasi.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Ada Field Kosong");
                setJTable();
            }else{
                simpanData();
                kosongkanfield();

            }
        }else{
            JOptionPane.showMessageDialog(this,"Password Harus Sama");
            jTpassword.setText("");
            jTkonfirmasi.setText("");
        }
    }//GEN-LAST:event_jBtambahActionPerformed

    private void jBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditActionPerformed
        // TODO add your handling code here:

        try {

            rubahData();
            kosongkanfield();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Edit");

        }
    }//GEN-LAST:event_jBeditActionPerformed

    private void jTuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTuserMouseClicked
        // TODO add your handling code here:
        ambilData_dari_JTable();
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);
        jBtambah.setEnabled(false);
    }//GEN-LAST:event_jTuserMouseClicked

    private void jTiduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTiduserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTiduserActionPerformed

    private void jBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBhapusActionPerformed
        // TODO add your handling code here:
        try {
            hapus_Data();
            kosongkanfield();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Klik Data yang ingin di Hapus");
            setJTable();
        }
        jBhapus.setEnabled(false);
    }//GEN-LAST:event_jBhapusActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        setJTable();
        kosongkanfield();
        jBtambah.setEnabled(true);
        jBedit.setEnabled(true);
        jBhapus.setEnabled(true);        
    }//GEN-LAST:event_formMouseClicked

    private void jTnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTnamaActionPerformed

    private void jTuserAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTuserAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTuserAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBedit;
    private javax.swing.JButton jBhapus;
    private javax.swing.JButton jBtambah;
    private javax.swing.JComboBox jCbagian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTiduser;
    private javax.swing.JPasswordField jTkonfirmasi;
    private javax.swing.JTextField jTnama;
    private javax.swing.JPasswordField jTpassword;
    private javax.swing.JTable jTuser;
    // End of variables declaration//GEN-END:variables
}
