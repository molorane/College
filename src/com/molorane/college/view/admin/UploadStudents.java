/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.admin;

import com.molorane.college.bll.impl.GenderBoImpl;
import com.molorane.college.bll.impl.LanguageBoImpl;
import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.RaceBoImpl;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.model.Gender;
import com.molorane.college.model.Language;
import com.molorane.college.model.Person;
import com.molorane.college.model.Race;
import com.molorane.college.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 * @author Mothusi Molorane
 */

public final class UploadStudents extends javax.swing.JFrame {
    
    private final PersonBoImpl personBoImpl = new PersonBoImpl();
    private final RaceBoImpl raceImpl = new RaceBoImpl();
    private final GenderBoImpl genderImpl = new GenderBoImpl();
    private final LanguageBoImpl languageImpl = new LanguageBoImpl();
    
    // variables
    private final DefaultTableModel tblModelUploadStudents;
    
    private final User loggedInUser;
    
    private List<HashMap<String,Object>> uploadStudents = new ArrayList<>();
    
     private long personId;
    /**
     * Creates new form Admin
     */
    public UploadStudents() {
        super("Admin - "+Functions.appName());
        initComponents();
        
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(UploadStudents.this);
        
        IsUserAdmin();
        
        raceImpl.fillComboBoxRace(jcbRace);
        genderImpl.fillComboBoxGender(jcbGender);
        languageImpl.fillComboBoxLanguage(jcbLanguage);
        
        tblModelUploadStudents = (DefaultTableModel)tblUploadStudents.getModel();
        
        Functions.initForm(UploadStudents.this);
    }
    
    private void IsUserAdmin(){
        if(loggedInUser != null){
            if(!loggedInUser.getRoleName().equalsIgnoreCase("Admin")){
                Functions.errorMessage("Access denied\n"
                        + "Only administrator is allowed to access this Panel.");
                System.exit(0);
            }
        }else{
            Functions.errorMessage("Login first.");
                System.exit(0);
        }
    }
    
    void btnViewStudentPressed(){
        Functions.setColor(btnViewStudents);
    }
    
    
    public void fillUploadStudents(List<HashMap<String, Object>> staffList){
        tblModelUploadStudents.setRowCount(0);
        staffList.forEach((row) -> {
            tblModelUploadStudents.addRow(new Object[]{
                row.get("studentno"),
                row.get("idno"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("otherName"),
                row.get("gender"),
                row.get("race"),
                row.get("langauge")
             });
        });
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupCheck = new javax.swing.ButtonGroup();
        jpnlPassword = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jpswPIN = new javax.swing.JPasswordField();
        jpnSideMenu = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnViewStudents = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnViewStudents = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        btnBrowseStudentsCSVFile = new javax.swing.JButton();
        btnUploadStudentsData = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblUploadStudents = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtIDNo = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        btnRegisterMatricStudent = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtStudentNo = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbRace = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbGender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbLanguage = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();
        jpnTop = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        jLabel16.setText("Admin PIN:");

        jpswPIN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jpnlPasswordLayout = new javax.swing.GroupLayout(jpnlPassword);
        jpnlPassword.setLayout(jpnlPasswordLayout);
        jpnlPasswordLayout.setHorizontalGroup(
            jpnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlPasswordLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpswPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jpnlPasswordLayout.setVerticalGroup(
            jpnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jpswPIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        btnViewStudents.setBackground(Functions.pnlBackgroundBottom());
        btnViewStudents.setForeground(new java.awt.Color(102, 0, 102));
        btnViewStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewStudentsMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/users.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Students");

        javax.swing.GroupLayout btnViewStudentsLayout = new javax.swing.GroupLayout(btnViewStudents);
        btnViewStudents.setLayout(btnViewStudentsLayout);
        btnViewStudentsLayout.setHorizontalGroup(
            btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnViewStudentsLayout.setVerticalGroup(
            btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblYear.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblYear.setForeground(new java.awt.Color(204, 204, 204));
        lblYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYear.setText("COLLEGE");

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addComponent(btnViewStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnViewStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBrowseStudentsCSVFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/csv.png"))); // NOI18N
        btnBrowseStudentsCSVFile.setToolTipText("Browse a csv file containing marks.");
        btnBrowseStudentsCSVFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseStudentsCSVFileActionPerformed(evt);
            }
        });

        btnUploadStudentsData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/upload.png"))); // NOI18N
        btnUploadStudentsData.setToolTipText("Save data from a csv file to repository");
        btnUploadStudentsData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadStudentsDataActionPerformed(evt);
            }
        });

        tblUploadStudents.setAutoCreateRowSorter(true);
        tblUploadStudents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblUploadStudents.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblUploadStudents.setForeground(new java.awt.Color(51, 51, 51));
        tblUploadStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StudentNo", "IDNo", "LastName", "Names", "OtherName", "Gender", "Race", "Language"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUploadStudents.setGridColor(new java.awt.Color(204, 204, 204));
        tblUploadStudents.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblUploadStudents.setRowHeight(40);
        jScrollPane10.setViewportView(tblUploadStudents);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnBrowseStudentsCSVFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUploadStudentsData)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator6)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnUploadStudentsData)
                    .addComponent(btnBrowseStudentsCSVFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jTabbedPane3.addTab("Upload Students", jPanel5);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("IDNo:");

        txtIDNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("LastName:");

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("FirstName:");

        txtFirstName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnRegisterMatricStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegisterMatricStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnRegisterMatricStudent.setText("Register");
        btnRegisterMatricStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterMatricStudentActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("StudentNo:");

        txtStudentNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("OtherName:");

        txtOtherName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setText("Race:");

        jLabel4.setText("Gender:");

        jLabel5.setText("Language:");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegisterMatricStudent)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIDNo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStudentNo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOtherName))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbRace, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtIDNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jcbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jcbRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jcbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2))
                .addGap(18, 18, 18)
                .addComponent(btnRegisterMatricStudent)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Add Student", jPanel14);

        javax.swing.GroupLayout jpnViewStudentsLayout = new javax.swing.GroupLayout(jpnViewStudents);
        jpnViewStudents.setLayout(jpnViewStudentsLayout);
        jpnViewStudentsLayout.setHorizontalGroup(
            jpnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jpnViewStudentsLayout.setVerticalGroup(
            jpnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jpnSlider.add(jpnViewStudents, "card2");

        jpnTop.setBackground(Functions.pnlBackgroundSideMenu());
        jpnTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("UPLOAD COLLEGE STUDENTS");

        javax.swing.GroupLayout jpnTopLayout = new javax.swing.GroupLayout(jpnTop);
        jpnTop.setLayout(jpnTopLayout);
        jpnTopLayout.setHorizontalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnTopLayout.setVerticalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jpnTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewStudentsMouseClicked
        // TODO add your handling code here:
        btnViewStudentPressed();
    }//GEN-LAST:event_btnViewStudentsMouseClicked

    private void btnBrowseStudentsCSVFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseStudentsCSVFileActionPerformed
        // TODO add your handling code here:
        try{
           String path = Functions.BrowseFile(this);
           if(path!=null){
            List<HashMap<String,Object>> students = Functions.readCollegeStudentsCSVFile(path);
            uploadStudents = students;
            fillUploadStudents(students);
           }
        }catch(Exception ex){
            Functions.errorMessage("A problem occurred while trying to upload the file.");
        }
    }//GEN-LAST:event_btnBrowseStudentsCSVFileActionPerformed

    private void btnUploadStudentsDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadStudentsDataActionPerformed
        // TODO add your handling code here:
        if(tblModelUploadStudents.getRowCount() > 0){
            int saved = personBoImpl.UploadCollegeStudents(uploadStudents);
            if(saved > 0){
                alert.notify("Students uploaded.", 1);
                tblModelUploadStudents.setRowCount(0);
            }else{
                alert.notify("Students not uploaded.",0);
            }
        }else{
            Functions.warningMessage("Browse csv file first.");
        }
    }//GEN-LAST:event_btnUploadStudentsDataActionPerformed

    private void btnRegisterMatricStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterMatricStudentActionPerformed
        // TODO add your handling code here:
        Gender g = (Gender)jcbGender.getSelectedItem();
        Race r = (Race)jcbRace.getSelectedItem();
        Language l = (Language)jcbLanguage.getSelectedItem();
        
        if(!g.getGender().equalsIgnoreCase("SELECT") && !r.getRace().equalsIgnoreCase("SELECT") && !l.getLanguage().equalsIgnoreCase("SELECT")){
            String studentno = txtStudentNo.getText();
            String idno = txtIDNo.getText();
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String otherName = txtOtherName.getText();
            
            if(studentno.length() > 0 && idno.length() > 0 && firstName.length() > 0 && lastName.length() > 0){
                Person p = new Person();
                p.setPersonId(Integer.parseInt(studentno));
                p.setIdno(idno);
                p.setFirstName(firstName);
                p.setLastName(lastName);
                p.setOtherName(otherName);
                p.setGenderId(g.getGenderId());
                p.setRaceId(r.getRaceId());
                p.setLanguageId(l.getLanguageId());
                int saved = personBoImpl.AddCollegeStudent(p);
                if(saved > 0){
                    alert.notify("Student information saved.", 1);
                    txtIDNo.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtOtherName.setText("");
                    txtStudentNo.setText("");
                }else{
                    alert.notify("Students information not saved.",0);
                }
            }else{
                Functions.errorMessage("Student must have idno, firstname and lastname.");
            }
        }else{
            Functions.errorMessage("Student must have gender, race and langauge selected.");
        }
    }//GEN-LAST:event_btnRegisterMatricStudentActionPerformed
 
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
                new UploadStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JButton btnBrowseStudentsCSVFile;
    private javax.swing.ButtonGroup btnGroupCheck;
    private javax.swing.JButton btnRegisterMatricStudent;
    private javax.swing.JButton btnUploadStudentsData;
    private javax.swing.JPanel btnViewStudents;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JComboBox<String> jcbGender;
    private javax.swing.JComboBox<String> jcbLanguage;
    private javax.swing.JComboBox<String> jcbRace;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JPanel jpnViewStudents;
    private javax.swing.JPanel jpnlPassword;
    private javax.swing.JPasswordField jpswPIN;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTable tblUploadStudents;
    private com.molorane.college.view.Controls.TitlePnl title;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtIDNo;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtStudentNo;
    // End of variables declaration//GEN-END:variables
}
