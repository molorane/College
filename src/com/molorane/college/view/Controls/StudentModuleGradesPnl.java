/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.Controls;

import com.molorane.college.bll.impl.StudentBoImpl;
import com.molorane.college.custom.Functions;
import com.molorane.college.jasperservice.JasperStudent;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class StudentModuleGradesPnl extends JPanel {
    
    
    private final StudentBoImpl studentBoImpl = new StudentBoImpl();
    private final JasperStudent jasperStudent = new JasperStudent();
    
    private DefaultTableModel tblModelStudentModuleGrades;
    private List<HashMap<String, Object>> grades;
    private double continuousAssessment;
    
    private int personId;
    private int termId;
    private String moduleCode;

    /**
     * Creates new form StudentModuleGrades
     * @param personId
     * @param termId
     * @param moduleCode
     */
    public StudentModuleGradesPnl(int personId, int termId, String moduleCode) {
        initComponents();
        this.personId = personId;
        this.termId = termId;
        this.moduleCode = moduleCode;
        
        grades = studentBoImpl.StudentModuleGrades(personId, termId, moduleCode);
        tblModelStudentModuleGrades = (DefaultTableModel)tblModuleGrades.getModel();
        continuousAssessment = 0;
        tblModelStudentModuleGrades.setRowCount(0);
        grades.forEach((HashMap<String, Object> row) -> {
            continuousAssessment += Float.parseFloat(row.get("weighted").toString());
            tblModelStudentModuleGrades.addRow(new Object[]{
                row.get("gradecategory"),
                row.get("mark"),
                row.get("weight"),
                row.get("weighted")
             });
        });
        lblConsinuousAssessment.setText(Functions.toTwoDecimal(continuousAssessment)+"");
    }
    
    public void refresh(int personId, int termId, String moduleCode){
        this.personId = personId;
        this.termId = termId;
        this.moduleCode = moduleCode;
        grades = studentBoImpl.StudentModuleGrades(personId, termId, moduleCode);
        tblModelStudentModuleGrades = (DefaultTableModel)tblModuleGrades.getModel();
        continuousAssessment = 0;
        tblModelStudentModuleGrades.setRowCount(0);
        grades.forEach((HashMap<String, Object> row) -> {
            continuousAssessment += Float.parseFloat(row.get("weighted").toString());
            tblModelStudentModuleGrades.addRow(new Object[]{
                row.get("gradecategory"),
                row.get("mark"),
                row.get("weight"),
                row.get("weighted")
             });
        });
        lblConsinuousAssessment.setText(Functions.toTwoDecimal(continuousAssessment)+"");  
    }
    
    
    public void display(){
        Object[] options = { };
        JOptionPane.showOptionDialog(null, this, "Student Grades",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        tblModuleGrades = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnStudentAssessment = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblConsinuousAssessment = new javax.swing.JLabel();

        jScrollPane9.setBorder(null);

        tblModuleGrades.setAutoCreateRowSorter(true);
        tblModuleGrades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblModuleGrades.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        tblModuleGrades.setForeground(new java.awt.Color(102, 102, 102));
        tblModuleGrades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Marks", "Weight", "Weighted"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblModuleGrades.setGridColor(new java.awt.Color(204, 204, 204));
        tblModuleGrades.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblModuleGrades.setRowHeight(40);
        jScrollPane9.setViewportView(tblModuleGrades);

        jPanel3.setBackground(Functions.pnlBackgroundTop());
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("If one of the grade categories is missing, yet it was uploaded, it's because the corresponding");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("grade weight is missing. The administrator or user in charge should upload the grade weight.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        btnStudentAssessment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnStudentAssessment.setForeground(new java.awt.Color(51, 51, 51));
        btnStudentAssessment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        btnStudentAssessment.setToolTipText("Print student assessment");
        btnStudentAssessment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentAssessmentActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("CONTINUOUS ASSESSMENT:");

        lblConsinuousAssessment.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblConsinuousAssessment.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane9)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnStudentAssessment)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblConsinuousAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(11, 11, 11)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblConsinuousAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addComponent(btnStudentAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStudentAssessmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentAssessmentActionPerformed
        // TODO add your handling code here:
        jasperStudent.StudentModuleGrades(personId, termId, moduleCode);
    }//GEN-LAST:event_btnStudentAssessmentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStudentAssessment;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblConsinuousAssessment;
    private javax.swing.JTable tblModuleGrades;
    // End of variables declaration//GEN-END:variables
}
