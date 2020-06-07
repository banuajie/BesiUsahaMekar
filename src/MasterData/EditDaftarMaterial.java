/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterData;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import besiusahamekar.koneksidb;

/**
 *
 * @author Banuajie
 */
public class EditDaftarMaterial extends javax.swing.JFrame {
public DefaultTableModel tabModel;
Connection conn;
public String kodeitem,namaitem,jenis,satuan,stok,pokok,jual,keterangan,idsupplier,namasupplier;

    public String getKode() {
        return kodeitem;
    }

    public String getNama() {
        return namaitem;
    }

    public String getjenis() {
        return jenis;
    }
    public String getsatuan() {
        return satuan;
    }

    public String getstok() {
        return stok;
    }

    public String getpokok() {
        return pokok;
    }
    public String getjual() {
        return jual;
    }

    public String getketerangan() {
        return keterangan;
    }
    
    public String getidsupplier() {
        return idsupplier;
    }
    
    public String getnamasupplier() {
        return namasupplier;
    }
    /**
     * Creates new form EditDaftarMaterial
     */
    public EditDaftarMaterial() {
        initComponents();
        getDatasatuan();
        getDatajenis();
        getNamaSupplier();
    }
    public void tampildata(){
        jTeditkode.setText(kodeitem);
        jTeditnama.setText(namaitem);
        jCeditjenis.getEditor().setItem(jenis);
        jCeditsatuan.getEditor().setItem(satuan);
        jTeditstok.setText(stok);
        jTedithargapokok.setText(pokok);
        jTedithargajual.setText(jual);
        jTeditketerangan.setText(keterangan);
        jCeditidsupplier.setText(idsupplier);
        jTeditnamasupplier.setText(namasupplier);
    }
       
    private void getDatajenis() {
        // import java.sql.connection
    
           try{
            jCeditjenis.removeAllItems();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select kode from jenismaterial";
            ResultSet rs=st.executeQuery(sql);

                
                        while(rs.next()){
                        String kodejenis = rs.getString("kode"); 
                        jCeditjenis.addItem(kodejenis);
                        
                       
      
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
   
 private void getDatasatuan() {
        // import java.sql.connection
            
           try{
            jCeditsatuan.removeAllItems();
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select kode from satuanmaterial";
            ResultSet rs=st.executeQuery(sql);

               
                        while(rs.next()){
                       String kodesatuan = rs.getString("kode"); // Get the Id
                       jCeditsatuan.addItem(kodesatuan);
                       
      
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
    
    public void rubahData() {
    // Konfirmasi sebelum melakukan perubahan data
    int ok = JOptionPane.showConfirmDialog(this,
        "Anda Yakin Ingin Mengubah Data Ini \n "+jTeditkode.getText(), "Konfirmasi ",JOptionPane.YES_NO_OPTION);
    // Apabila tombol Yes ditekan
    if (ok == 0) {
      try {
        conn=koneksidb.getkoneksi();
        String sql ="update tmaterial set Nama_Item = ?, Jenis= ?, Stok= ?, Satuan= ?, Harga_Pokok= ?, Harga_Jual= ?, Keterangan= ?, ID_Supplier= ?, Nama_Supplier= ? WHERE Kode_Item = ?";
        PreparedStatement p=(PreparedStatement) conn.prepareStatement(sql);
       
        
        try {
                     
           p.setString(1, jTeditnama.getText());
           p.setString(2, jCeditjenis.getSelectedItem().toString());
           p.setString(3, jTeditstok.getText());
           p.setString(4, jCeditsatuan.getSelectedItem().toString());
           p.setString(5, jTedithargapokok.getText());
           p.setString(6, jTedithargajual.getText());
           p.setString(7, jTeditketerangan.getText());
           p.setString(8, jCeditidsupplier.getText().toString());
           p.setString(9, jTeditnamasupplier.getText());
           p.setString(10, jTeditkode.getText());
          
           p.executeUpdate();
           int rs= p.executeUpdate();
          
           if(rs>=0){
                      
                    // JOptionPane.showMessageDialog(this,jCeditsatuan.getSelectedItem().toString()+""+jTeditstok.getText()+""+jTeditnama.getText()+""+jCeditjenis.getSelectedItem().toString());
          }

         // Memanggil Method   tampilDataKeJTable();
          } catch (SQLException se) { }     // Silahkan tambahkan Sendiri informasi Eksepsi
      } catch (SQLException se) {}  // Silahkan tambahkan Sendiri informasi Eksepsi
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
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTedithargajual = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTedithargapokok = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTeditketerangan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTeditnama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTeditstok = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jCeditsatuan = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCeditjenis = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTeditkode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCeditidsupplier = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTeditnamasupplier = new javax.swing.JTextField();
        jsimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Data Material");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Data Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12))); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText(":");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel7.setText("Jenis");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText(":");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel8.setText("Stok");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel9.setText("HargaJual");

        jTedithargajual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTedithargajual.setToolTipText("");
        jTedithargajual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTedithargajualKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel3.setText("Satuan");

        jTedithargapokok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTedithargapokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTedithargapokokKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText(":");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel1.setText("Kode Material");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel13.setText("Keterangan");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel4.setText("Harga Pokok");

        jTeditstok.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTeditstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTeditstokActionPerformed(evt);
            }
        });
        jTeditstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTeditstokKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText(":");

        jCeditsatuan.setEditable(true);
        jCeditsatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Satuan", "-----------------------------------" }));
        jCeditsatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCeditsatuanActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText(":");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCeditjenis.setEditable(true);
        jCeditjenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis", "-----------------------------------" }));
        jCeditjenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCeditjenisMouseClicked(evt);
            }
        });
        jCeditjenis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCeditjenisFocusGained(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel2.setText("Nama Item");

        jTeditkode.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText(":");

        jButton2.setText("jButton1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Supplier");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(":");

        jCeditidsupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCeditidsupplierFocusGained(evt);
            }
        });
        jCeditidsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCeditidsupplierKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCeditidsupplierKeyTyped(evt);
            }
        });

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTeditkode))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTeditnama)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jCeditsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCeditjenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(38, 38, 38))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel3)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel13))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14)
                                .addComponent(jLabel16)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(60, 60, 60)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTedithargapokok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(jTedithargajual, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTeditketerangan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCeditidsupplier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTeditnamasupplier)
                    .addComponent(jTeditstok, javax.swing.GroupLayout.Alignment.TRAILING)))
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
                    .addComponent(jTeditnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCeditjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel17)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCeditsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTeditstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTedithargapokok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTedithargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTeditketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jCeditidsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTeditnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jsimpan.setText("Simpan");
        jsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsimpan)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_formMouseEntered

    private void jsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsimpanActionPerformed
        // TODO add your handling code here:
        String editkode = jTeditkode.getText();
        String editnama = jTeditnama.getText();
        String editjenis = jCeditjenis.getSelectedItem().toString();
        String editsatuan = jCeditsatuan.getSelectedItem().toString();
        String editstok = jTeditstok.getText();
        String editpokok = jTedithargapokok.getText();
        String editjual = jTedithargajual.getText();
        String editketerangan = jTeditketerangan.getText();
        String editidsupplier = jCeditidsupplier.getText().toString();
        String editnamasupplier = jTeditnamasupplier.getText();
        
        int x = Integer.valueOf(jTedithargapokok.getText());
        int y = Integer.valueOf(jTedithargajual.getText());
            
            if(editkode.equalsIgnoreCase("")||editnama.equalsIgnoreCase("")||editjenis.equalsIgnoreCase("")||editsatuan.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this,"Ada Field Kosong");
                tampildata();
            }
            if(x>y){
                JOptionPane.showMessageDialog(this,"Maaf Kesalahan Input, Harga Pokok Tidak Boleh Lebih Besar Dari Harga Jual");
            }
            else{
                rubahData();
                this.dispose();
            }
    }//GEN-LAST:event_jsimpanActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        getDatajenis();
        getDatasatuan();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        PopSatuanMaterial dp=new PopSatuanMaterial();
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        panelPop.add(dp).setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Satuan", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDatasatuanDipilih(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }
        }

        private void getDatasatuanDipilih(String kode){

            jCeditsatuan.setSelectedItem(kode);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCeditjenisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCeditjenisFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jCeditjenisFocusGained

    private void jCeditjenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCeditjenisMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jCeditjenisMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PopJenisMaterial dp=new PopJenisMaterial();
        javax.swing.JPanel panelPop = new javax.swing.JPanel();
        panelPop.add(dp).setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, dp, "Pilih Jenis Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(!dp.getTitle().equals(""))getDatajenisDipilih(dp.getTitle());
        } else {
            System.out.println("Cancelled");
        }
        }

        private void getDatajenisDipilih(String kode){

            jCeditjenis.setSelectedItem(kode);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCeditsatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCeditsatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCeditsatuanActionPerformed

    private void jTeditstokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTeditstokKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTeditstokKeyTyped

    private void jTeditstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTeditstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTeditstokActionPerformed

    private void jTedithargapokokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTedithargapokokKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTedithargapokokKeyTyped

    private void jTedithargajualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTedithargajualKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTedithargajualKeyTyped

    private void jCeditidsupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCeditidsupplierFocusGained
        // TODO add your handling code here:
        getNamaSupplier();
    }//GEN-LAST:event_jCeditidsupplierFocusGained

    private void jCeditidsupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCeditidsupplierKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            getNamaSupplier();
            String string = jCeditidsupplier.getText();
            String upper = string.toUpperCase();
            jCeditidsupplier.setText(upper);
        }
    }//GEN-LAST:event_jCeditidsupplierKeyPressed

    private void jCeditidsupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCeditidsupplierKeyTyped
        // TODO add your handling code here:
        getNamaSupplier();
        String string = jCeditidsupplier.getText();
        String upper = string.toUpperCase();
        jCeditidsupplier.setText(upper);
    }//GEN-LAST:event_jCeditidsupplierKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(EditDaftarMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDaftarMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDaftarMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDaftarMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDaftarMaterial().setVisible(true);
            }
        });
    }
    
    
    private void getNamaSupplier() {
        // import java.sql.connection
       String id = jCeditidsupplier.getText();
           try{
            conn =koneksidb.getkoneksi();
            Statement st= conn.createStatement();
            String sql="Select * from tsupplier where ID_Supplier='"+id+"'";
            ResultSet rs=st.executeQuery(sql);

                        while(rs.next()){
                   
                       String nama =rs.getString("Nama");
                       jTeditnamasupplier.setText(nama);
                       
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
    
    
    
    private void getDatas(String kode){

            try{
                conn =koneksidb.getkoneksi();
                Statement st= conn.createStatement();
                String sql="Select * from tsupplier where ID_Supplier='"+kode+"'";
                ResultSet rs=st.executeQuery(sql);

                while(rs.next()){

                    String nama =rs.getString("Nama");
                    jCeditidsupplier.setText(kode);
                    jTeditnamasupplier.setText(nama);

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField jCeditidsupplier;
    private javax.swing.JComboBox jCeditjenis;
    private javax.swing.JComboBox jCeditsatuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTedithargajual;
    private javax.swing.JTextField jTedithargapokok;
    private javax.swing.JTextField jTeditketerangan;
    public javax.swing.JTextField jTeditkode;
    private javax.swing.JTextField jTeditnama;
    private javax.swing.JTextField jTeditnamasupplier;
    private javax.swing.JTextField jTeditstok;
    private javax.swing.JButton jsimpan;
    // End of variables declaration//GEN-END:variables
}
