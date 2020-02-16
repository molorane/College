/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import com.nanoware.bll.Functions;

/**
 *
 * @author Mothusi Molorane
 */
public class Dashboard extends javax.swing.JFrame {
    
    private long loggedInId = 2014098616;

    /**
     * Creates new form Home
     */
    public Dashboard() {
        super("Dashboard - "+Functions.appName());
        initComponents();
        Functions.setAppIcon(Dashboard.this);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnTerm = new javax.swing.JToggleButton();
        jtbtnApplications = new javax.swing.JToggleButton();
        btnSettings = new javax.swing.JToggleButton();
        jtbtnUsers = new javax.swing.JToggleButton();
        jtbtnAttendance = new javax.swing.JToggleButton();
        jtbtnAdmissions2 = new javax.swing.JToggleButton();
        jtbtnStaff = new javax.swing.JToggleButton();
        btnEnrolment = new javax.swing.JToggleButton();
        btnAdmission = new javax.swing.JToggleButton();
        btnAccount = new javax.swing.JToggleButton();
        btnGrades = new javax.swing.JToggleButton();
        jtbtnAdmissions7 = new javax.swing.JToggleButton();
        jtbtnAdmissions8 = new javax.swing.JToggleButton();
        jtbtnAssessments = new javax.swing.JToggleButton();
        jtbtnCampuses = new javax.swing.JToggleButton();
        jtbtnAdmissions11 = new javax.swing.JToggleButton();
        jbtnTimeTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(Functions.pnlBackgroundBottom());
        jPanel3.setToolTipText("");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(Functions.designedDetails());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(Functions.pnlBackgroundTop());
        jPanel4.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("ADMIN PANEL");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("THE STUDENT PORTAL");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        btnTerm.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnTerm.setForeground(new java.awt.Color(102, 102, 102));
        btnTerm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/schedule.png"))); // NOI18N
        btnTerm.setText("Terms");
        btnTerm.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTerm.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermActionPerformed(evt);
            }
        });

        jtbtnApplications.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnApplications.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnApplications.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/application.png"))); // NOI18N
        jtbtnApplications.setText("Applications");
        jtbtnApplications.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnApplications.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbtnApplications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbtnApplicationsActionPerformed(evt);
            }
        });

        btnSettings.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnSettings.setForeground(new java.awt.Color(102, 102, 102));
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/settings1.png"))); // NOI18N
        btnSettings.setText("Settings");
        btnSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        jtbtnUsers.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnUsers.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/account.png"))); // NOI18N
        jtbtnUsers.setText("Users");
        jtbtnUsers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnUsers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jtbtnAttendance.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnAttendance.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnAttendance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/attendance.png"))); // NOI18N
        jtbtnAttendance.setText("Attendance");
        jtbtnAttendance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnAttendance.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbtnAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbtnAttendanceActionPerformed(evt);
            }
        });

        jtbtnAdmissions2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnAdmissions2.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnAdmissions2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/bank.png"))); // NOI18N
        jtbtnAdmissions2.setText("Sponsors");
        jtbtnAdmissions2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnAdmissions2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jtbtnStaff.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnStaff.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/service.png"))); // NOI18N
        jtbtnStaff.setText("Staff");
        jtbtnStaff.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnStaff.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnEnrolment.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnEnrolment.setForeground(new java.awt.Color(102, 102, 102));
        btnEnrolment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/clipboard.png"))); // NOI18N
        btnEnrolment.setText("Enrolments");
        btnEnrolment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEnrolment.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEnrolment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrolmentActionPerformed(evt);
            }
        });

        btnAdmission.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAdmission.setForeground(new java.awt.Color(102, 102, 102));
        btnAdmission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/icon2.png"))); // NOI18N
        btnAdmission.setText("Admissions");
        btnAdmission.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmission.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmissionActionPerformed(evt);
            }
        });

        btnAccount.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnAccount.setForeground(new java.awt.Color(102, 102, 102));
        btnAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/shopping-cart.png"))); // NOI18N
        btnAccount.setText("Accounts");
        btnAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountActionPerformed(evt);
            }
        });

        btnGrades.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnGrades.setForeground(new java.awt.Color(102, 102, 102));
        btnGrades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/speaker.png"))); // NOI18N
        btnGrades.setText("Grades");
        btnGrades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGrades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGradesActionPerformed(evt);
            }
        });

        jtbtnAdmissions7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnAdmissions7.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnAdmissions7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/test.png"))); // NOI18N
        jtbtnAdmissions7.setText("Tests");
        jtbtnAdmissions7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnAdmissions7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jtbtnAdmissions8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnAdmissions8.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnAdmissions8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/pass1.png"))); // NOI18N
        jtbtnAdmissions8.setText("Exams");
        jtbtnAdmissions8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnAdmissions8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jtbtnAssessments.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnAssessments.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnAssessments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/law1.png"))); // NOI18N
        jtbtnAssessments.setText("Assessments");
        jtbtnAssessments.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnAssessments.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jtbtnCampuses.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnCampuses.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnCampuses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/campus.png"))); // NOI18N
        jtbtnCampuses.setText("Campuses");
        jtbtnCampuses.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnCampuses.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jtbtnAdmissions11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtbtnAdmissions11.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnAdmissions11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/analytics4.png"))); // NOI18N
        jtbtnAdmissions11.setText("Results");
        jtbtnAdmissions11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbtnAdmissions11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jbtnTimeTable.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnTimeTable.setForeground(new java.awt.Color(102, 102, 102));
        jbtnTimeTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard/clock.png"))); // NOI18N
        jbtnTimeTable.setText("Time Table");
        jbtnTimeTable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnTimeTable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtbtnAdmissions2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbtnAssessments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbtnAdmissions7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnrolment, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtbtnUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmission, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(jtbtnApplications, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnTimeTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtbtnCampuses, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(btnAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbtnStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtbtnAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbtnAdmissions8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGrades, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbtnAdmissions11, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdmission, btnEnrolment, jtbtnAdmissions7, jtbtnApplications});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnrolment, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdmission, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrades, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtbtnAdmissions7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbtnApplications, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbtnAdmissions8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbtnStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbtnAssessments, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbtnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbtnAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbtnAdmissions11, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtbtnAdmissions2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(jbtnTimeTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtbtnCampuses, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAccount, btnAdmission, btnEnrolment, btnGrades, btnSettings, btnTerm, jtbtnAdmissions11, jtbtnAdmissions2, jtbtnAdmissions7, jtbtnAdmissions8, jtbtnApplications, jtbtnAssessments, jtbtnAttendance, jtbtnCampuses, jtbtnStaff, jtbtnUsers});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbtnApplicationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtnApplicationsActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtbtnApplicationsActionPerformed

    private void jtbtnAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtnAttendanceActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtbtnAttendanceActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        // TODO add your handling code here:
        new Settings().setVisible(true);
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnEnrolmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrolmentActionPerformed
        // TODO add your handling code here:
        new Enrolment().setVisible(true);
    }//GEN-LAST:event_btnEnrolmentActionPerformed

    private void btnAdmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmissionActionPerformed
        // TODO add your handling code here:
        new Admission().setVisible(true);
    }//GEN-LAST:event_btnAdmissionActionPerformed

    private void btnAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountActionPerformed
        // TODO add your handling code here:
        new Accounts().setVisible(true);
    }//GEN-LAST:event_btnAccountActionPerformed

    private void btnGradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGradesActionPerformed
        // TODO add your handling code here:
        new GradesForm().setVisible(true);
    }//GEN-LAST:event_btnGradesActionPerformed

    private void btnTermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermActionPerformed
        // TODO add your handling code here:
        new TermForm().setVisible(true);
    }//GEN-LAST:event_btnTermActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        Functions.setFormTheme();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAccount;
    private javax.swing.JToggleButton btnAdmission;
    private javax.swing.JToggleButton btnEnrolment;
    private javax.swing.JToggleButton btnGrades;
    private javax.swing.JToggleButton btnSettings;
    private javax.swing.JToggleButton btnTerm;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbtnTimeTable;
    private javax.swing.JToggleButton jtbtnAdmissions11;
    private javax.swing.JToggleButton jtbtnAdmissions2;
    private javax.swing.JToggleButton jtbtnAdmissions7;
    private javax.swing.JToggleButton jtbtnAdmissions8;
    private javax.swing.JToggleButton jtbtnApplications;
    private javax.swing.JToggleButton jtbtnAssessments;
    private javax.swing.JToggleButton jtbtnAttendance;
    private javax.swing.JToggleButton jtbtnCampuses;
    private javax.swing.JToggleButton jtbtnStaff;
    private javax.swing.JToggleButton jtbtnUsers;
    // End of variables declaration//GEN-END:variables
}