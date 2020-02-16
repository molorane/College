/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.Controls;

import com.molorane.college.bll.impl.CountryBoImpl;
import com.molorane.college.bll.impl.GenderBoImpl;
import com.molorane.college.bll.impl.GurdianBoImpl;
import com.molorane.college.bll.impl.LanguageBoImpl;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.RaceBoImpl;
import com.molorane.college.bll.impl.RelationshipBoImpl;
import com.molorane.college.bll.impl.ReligionBoImpl;
import com.molorane.college.bll.impl.TitleBoImpl;
import com.molorane.college.custom.Functions;
import com.molorane.college.model.Country;
import com.molorane.college.model.Gender;
import com.molorane.college.model.Language;
import com.molorane.college.model.Person;
import com.molorane.college.model.Race;
import com.molorane.college.model.Religion;
import com.molorane.college.model.Title;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Mothusi Molorane
 */
public class PersonFrm extends javax.swing.JFrame {
    
    private final PersonBoImpl personImpl = new PersonBoImpl();
    private final RaceBoImpl raceImpl = new RaceBoImpl();
    private final GenderBoImpl genderImpl = new GenderBoImpl();
    private final CountryBoImpl nationalityImpl = new CountryBoImpl();
    private final TitleBoImpl titleImpl = new TitleBoImpl();
    private final LanguageBoImpl languageImpl = new LanguageBoImpl();
    private final ReligionBoImpl religionImpl = new ReligionBoImpl();
    private final RelationshipBoImpl relationshipImpl = new RelationshipBoImpl();
    private final GurdianBoImpl gurdianImpl = new GurdianBoImpl();
    
    private long personId;

    /**
     * Creates new form PersonFrm
     */
    public PersonFrm() {
        super("New Person - "+Functions.appName());
        initComponents();
        raceImpl.fillComboBoxRace(jcbRace);
        genderImpl.fillComboBoxGender(jcbGender);
        nationalityImpl.fillComboBoxCountry(jcbNationality);
        titleImpl.fillComboBoxTitle(jcbTitle);
        languageImpl.fillComboBoxLanguage(jcbLanguage);
        religionImpl.fillComboBoxReligion(jcbReligion);
        Functions.initForm(PersonFrm.this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtIdNo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jcbNationality = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jdpDob = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jcbRace = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbGender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbReligion = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcbTitle = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jcbLanguage = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtPostalCode = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtGenerated = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("PersonId:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 30, 80, -1));

        txtStudentID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtStudentID.setEnabled(false);
        txtStudentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStudentIDKeyReleased(evt);
            }
        });
        jPanel2.add(txtStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 27, 194, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Firstname:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 30, 89, -1));

        txtFirstName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 27, 171, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Identity No:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 74, -1, -1));

        txtIdNo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtIdNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 71, 196, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Lastname:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 74, 89, -1));

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 71, 171, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Nationality:");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 117, 80, -1));

        jcbNationality.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbNationality.setForeground(new java.awt.Color(102, 102, 102));
        jcbNationality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel2.add(jcbNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 117, 196, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Othername:");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 120, 89, -1));

        txtOtherName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtOtherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 117, 171, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("DOB:");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 163, 80, -1));

        jdpDob.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jdpDob, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 163, 196, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Race:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 163, 89, -1));

        jcbRace.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbRace.setForeground(new java.awt.Color(102, 102, 102));
        jcbRace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel2.add(jcbRace, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 163, 171, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Gender:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 209, 80, -1));

        jcbGender.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbGender.setForeground(new java.awt.Color(102, 102, 102));
        jcbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel2.add(jcbGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 209, 196, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Religion:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 212, 89, -1));

        jcbReligion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbReligion.setForeground(new java.awt.Color(102, 102, 102));
        jcbReligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel2.add(jcbReligion, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 209, 171, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Title:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 256, 80, -1));

        jcbTitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbTitle.setForeground(new java.awt.Color(102, 102, 102));
        jcbTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel2.add(jcbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 253, 98, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Language:");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 256, 89, -1));

        jcbLanguage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbLanguage.setForeground(new java.awt.Color(102, 102, 102));
        jcbLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel2.add(jcbLanguage, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 253, 171, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 27, -1, 254));

        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Phone:");
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 32, 80, -1));

        txtPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 27, 296, -1));

        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Telephone:");
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 78, -1, -1));

        txtTelephone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 73, 296, -1));

        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Fax:");
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 124, 80, -1));

        txtFax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 119, 296, -1));

        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Email:");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 163, 80, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 163, 296, -1));

        jLabel42.setForeground(new java.awt.Color(102, 102, 102));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Address:");
        jPanel2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 214, 80, -1));

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 209, 296, -1));

        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Details:");
        jPanel2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 260, 80, -1));

        txtPostalCode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(txtPostalCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 255, 296, -1));

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 51, 51));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setIconTextGap(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(888, 301, 134, -1));

        jLabel2.setText("Generated PersonID:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 150, -1));
        jPanel2.add(txtGenerated, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 316, 240, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 1060, 360));

        jPanel1.setBackground(Functions.pnlBackgroundTop());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NEW PERSONAL INFORMATION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStudentIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIDKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try{
            txtStudentID.setText(personImpl.GetNewStudentNo()+"");
            personId = Long.parseLong(txtStudentID.getText());
            Person p = new Person();
            p.setPersonId(personId);
            p.setIdno(txtIdNo.getText());
            p.setFirstName(txtFirstName.getText());
            p.setLastName(txtLastName.getText());
            p.setOtherName(txtOtherName.getText());
            Gender g = (Gender)jcbGender.getSelectedItem();
            p.setGenderId(g.getGenderId());
            Title t = (Title)jcbTitle.getSelectedItem();
            p.setTitleId(t.getTitleId());
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String dt = formater.format(jdpDob.getDate());
            p.setDob(formater.format(jdpDob.getDate()));
            Race r = (Race)jcbRace.getSelectedItem();
            p.setRaceId(r.getRaceId());
            Country c = (Country)jcbNationality.getSelectedItem();
            p.setNationalityId(c.getCountryId());
            Language l = (Language)jcbLanguage.getSelectedItem();
            p.setLanguageId(l.getLanguageId());
            Religion rl = (Religion)jcbReligion.getSelectedItem();
            p.setReligionId(rl.getReligionId());
            p.setAddress(txtAddress.getText());
            p.setPostalCode(txtPostalCode.getText());
            p.setTelephone(txtTelephone.getText());
            p.setFax(txtFax.getText());
            p.setCellphone(txtPhone.getText());
            p.setEmail(txtEmail.getText());
            int insertedPerson = personImpl.AddPerson(p);
            if(insertedPerson > 0){
                txtGenerated.setText(personImpl.GetNewStudentNo()+"");
                Functions.successMessage("Student with details :"+p.getPersonId()+","+p.getFirstName()+" saved.\n"
                        + "Use the assigned personId as staff no for the new lecture.");
            }
        }catch(NullPointerException ex){
            Functions.errorMessage("Ensure that all data is in correct format including date of birth.\n"+ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

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
                new PersonFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jcbGender;
    private javax.swing.JComboBox<String> jcbLanguage;
    private javax.swing.JComboBox<String> jcbNationality;
    private javax.swing.JComboBox<String> jcbRace;
    private javax.swing.JComboBox<String> jcbReligion;
    private javax.swing.JComboBox<String> jcbTitle;
    private org.jdesktop.swingx.JXDatePicker jdpDob;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGenerated;
    private javax.swing.JTextField txtIdNo;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPostalCode;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
