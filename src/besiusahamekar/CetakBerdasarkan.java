/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package besiusahamekar;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static besiusahamekar.koneksidb.PathReport;

/**
 *
 * @author Banuajie
 */
public class CetakBerdasarkan extends javax.swing.JInternalFrame {
Connection conn =koneksidb.getkoneksi();
        
String tipeitem="";
String tampilan = "yyyy-MM-dd";
    /**
     * Creates new form CetakBerdasarkan
     */
    public CetakBerdasarkan() {
        initComponents();
    }

    public void cetakdaftaritem(){
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tipeitem", jCtipeitem.getSelectedItem().toString());
       
        try {

            reportSource = PathReport + "LaporanDaftarItem.jrxml";
            reportDest = PathReport + "LaporanDaftarItem.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }


    public void cetaklappesananbeli(){
        
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanPesananPembelian.jrxml";
            reportDest = PathReport + "LaporanPesananPembelian.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }


    public void cetaklaptransbelid(){
        
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanPembeliandetail.jrxml";
            reportDest = PathReport + "LaporanPembeliandetail.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }


    public void cetaklaptransbelir(){
        
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanPembelian.jrxml";
            reportDest = PathReport + "LaporanPembelian.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }


    public void cetaklapretur(){
        
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanReturPembelian.jrxml";
            reportDest = PathReport + "LaporanReturPembelian.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }


    public void cetaklapreturd(){
        
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanReturPembeliandetail.jrxml";
            reportDest = PathReport + "LaporanReturPembeliandetail.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }
    
     public void cetaklappesananjual(){
                 SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanPesananPenjualan.jrxml";
            reportDest = PathReport + "LaporanPesananPenjualan.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
     }
     
    public void cetaklaptransjualr(){
         SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanPenjualan.jrxml";
            reportDest = PathReport + "LaporanPenjualan.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
     }
    
    public void cetaklaptransjuald(){
         SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanPenjualandetail.jrxml";
            reportDest = PathReport + "LaporanPenjualandetail.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
     }
    
    public void cetaklapreturjual(){
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanReturPenjualan.jrxml";
            reportDest = PathReport + "LaporanReturPenjualan.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }
    
    public void cetaklapreturjuald(){
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanReturPenjualandetail.jrxml";
            reportDest = PathReport + "LaporanReturPenjualandetail.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }
   
      public void cetaklapstok(){
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        //String tglawal= String.valueOf(format.format(jDdari.getDate()));
        //String tglakhir = String.valueOf(format.format(jDsampai.getDate()));
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("TglAwal", jDdari.getDate());
        param.put("TglAkhir", jDsampai.getDate());
        try {

            reportSource = PathReport + "LaporanStokOrderbytanggal.jrxml";
            reportDest = PathReport + "LaporanStokOrderbytanggal.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
        }
    }
      
      public void cetaklapstoks(){
        String reportSource;
        String reportDest;
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("kode", jTkode.getText());
     
        try {

            reportSource = PathReport + "LaporanStokOrderbykode.jrxml";
            reportDest = PathReport + "LaporanStokOrderbykode.html";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println("Koneksi Mysql Gagal" + e.getMessage());
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

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDdari = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDsampai = new com.toedter.calendar.JDateChooser();
        jCtipelap = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jBsimpan = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTkode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCtipeitem = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Cetak Laporan");
        setToolTipText("");

        jLabel2.setText("Dari");

        jDdari.setDateFormatString("dd-MM-yyyy");
        jDdari.setEnabled(false);

        jLabel1.setText("Sampai");

        jDsampai.setDateFormatString("dd-MM-yyyy");
        jDsampai.setEnabled(false);

        jCtipelap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rekap", "Detail" }));
        jCtipelap.setEnabled(false);
        jCtipelap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCtipelapActionPerformed(evt);
            }
        });

        jLabel6.setText(":");

        jLabel7.setText(":");

        jBsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Floppy.png"))); // NOI18N
        jBsimpan.setText("Simpan");
        jBsimpan.setEnabled(false);
        jBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsimpanActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Undo.png"))); // NOI18N
        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTkode.setEnabled(false);

        jLabel8.setText("Kode");

        jLabel9.setText(":");

        jCtipeitem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Material" }));
        jCtipeitem.setEnabled(false);

        jLabel3.setText("Tipe Item");

        jLabel5.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBsimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCtipeitem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTkode))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDsampai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jDdari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCtipelap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(6, 6, 6)))
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCtipeitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2))
                    .addComponent(jDdari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDsampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCtipelap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBsimpan)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsimpanActionPerformed
        // TODO add your handling code here:
        
        if(jBsimpan.getText().equals("Cetak Material")){    
            cetakdaftaritem();
       }else if(jBsimpan.getText().equals("Cetak Lap Beli")){
           cetaklappesananbeli();
       }else if(jBsimpan.getText().equals("Cetak Lap Trans")){
          String pilih = jCtipelap.getSelectedItem().toString();
           if(pilih.equals("Rekap")){
               cetaklaptransbelir();
           }else{
               cetaklaptransbelid();
           }
           
       }else if(jBsimpan.getText().equals("Cetak Lap Retur")){
          String pilih = jCtipelap.getSelectedItem().toString();
           if(pilih.equals("Rekap")){
               cetaklapretur();
           }else{
               cetaklapreturd();
           }
       }else if(jBsimpan.getText().equals("Cetak Lap Jual")){
           cetaklappesananjual();
       }else if(jBsimpan.getText().equals("Cetak Lap Transs")){
          String pilih = jCtipelap.getSelectedItem().toString();
           if(pilih.equals("Rekap")){
               cetaklaptransjualr();
           }else{
               cetaklaptransjuald();
           }
           
       }else if(jBsimpan.getText().equals("Cetak Lap Returs")){
          String pilih = jCtipelap.getSelectedItem().toString();
           if(pilih.equals("Rekap")){
               cetaklapreturjual();
           }else{
               cetaklapreturjuald();
           }
       }else if(jBsimpan.getText().equals("Cetak Lap Stok")){
          String pilih = jCtipelap.getSelectedItem().toString();
           
               cetaklapstok();
           
                
       } else if(jBsimpan.getText().equals("Cetak Lap Stoks")){
          String pilih = jCtipelap.getSelectedItem().toString();
               
               cetaklapstoks();
                
       }
        
    }//GEN-LAST:event_jBsimpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCtipelapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCtipelapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCtipelapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBsimpan;
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox jCtipeitem;
    public javax.swing.JComboBox jCtipelap;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    public com.toedter.calendar.JDateChooser jDdari;
    public com.toedter.calendar.JDateChooser jDsampai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTkode;
    // End of variables declaration//GEN-END:variables
}
