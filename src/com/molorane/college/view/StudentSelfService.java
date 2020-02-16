/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view;

import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.CampusBoImpl;
import com.molorane.college.bll.impl.CourseBoImpl;
import com.molorane.college.bll.impl.EnrolmentBoImpl;
import com.molorane.college.bll.impl.ModuleBoImpl;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.StudyTypeBoImpl;
import com.molorane.college.bll.impl.TermBoImpl;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.jasperservice.JasperEnrolments;
import com.molorane.college.model.Campus;
import com.molorane.college.model.Course;
import com.molorane.college.model.Module;
import com.molorane.college.model.StudyType;
import com.molorane.college.model.Term;
import com.molorane.college.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public final class StudentSelfService extends javax.swing.JFrame {
    
    private final PersonBoImpl personImpl = new PersonBoImpl();
    private final CourseBoImpl courseImpl = new CourseBoImpl();
    private final StudyTypeBoImpl studyTypeImpl = new StudyTypeBoImpl();
    private final ModuleBoImpl moduleImpl = new ModuleBoImpl();
    private final EnrolmentBoImpl enrolmentImpl = new EnrolmentBoImpl();
    private final TermBoImpl termImpl = new TermBoImpl();
    private final CampusBoImpl campusImpl = new CampusBoImpl();
    private final JasperEnrolments jasperEnolment = new JasperEnrolments();
    
    private boolean jcbDepartmentClicked = false;
    
    private final DefaultTableModel tblModelDepartmentCouses;
    private final DefaultTableModel tblModelCourseModules;
    private final DefaultTableModel tblModelSelectedModules;
    
    private final DefaultTableModel tblModelEnrolledCourses;
    private final DefaultTableModel tblModelEnrolledCourseModules;
    
    private String selectedCourseCode;
    private String selectedCourse;
    private String selectedTerm;
    
    private long personId;
    private final User loggedInUser;
    private boolean jcbEnrolledTermFilled;
    
    private final String beforeNavigation = "Before navigating you must first specify the student by typing the student no\n"
                    + "or by searching the student then selecting the desired student on the search list.";


    /**
     * Creates new form Enrol
     */
    public StudentSelfService() {
        super("Enrolments - "+Functions.appName());
        initComponents();
        
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(StudentSelfService.this);
        
        personId = loggedInUser.getPersonId();
        
        courseImpl.fillComboBoxDepartment(jcbDepartment);
        studyTypeImpl.fillComboBoxStudyType(jcbStudyType);
        //campusImpl.fillComboBoxCampuses(jcbCampus); 
        jcbCampus.setVisible(false);
        jcbDepartmentClicked = true;
        
        // set table models
        tblModelDepartmentCouses = (DefaultTableModel)jtblDepartmentCourses.getModel();         
        tblModelEnrolledCourses = (DefaultTableModel)tblEnrolledCourses.getModel();       
        tblModelEnrolledCourseModules = (DefaultTableModel)tblEnrolledCourseModules.getModel(); 
        tblModelCourseModules = (DefaultTableModel)tblCourseModules.getModel();
        tblModelSelectedModules = (DefaultTableModel)tblSelectedModules.getModel();  
        
        // set single selection mode for tables
        tblEnrolledCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblDepartmentCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        lblStudent.setText(loggedInUser.getPersonId()+": "+loggedInUser.getFirstName()+" "+loggedInUser.getLastName()); 
        
        StudentEnrolledCourses(enrolmentImpl.GetStudentCourseEnrolments(personId));
        
        Functions.initForm(StudentSelfService.this);
    }
    
     void btnAdminHomePressed(){
        Functions.resetColor(btnEnrolemnts);
        Functions.resetColor(btnEnrolStudent);
    }
    
    void profileRegistrationPressed(){
       Functions.setColor(btnEnrolemnts);
       Functions.resetColor(btnEnrolStudent);
    }
    
    void btnEnrolStudentPressed(){
       Functions.resetColor(btnEnrolemnts);
       Functions.setColor(btnEnrolStudent);
    }
    
    void btnPrintEnrolmentPressed(){
       Functions.resetColor(btnEnrolemnts);
       Functions.resetColor(btnEnrolStudent);
    }
    
    private void fillDepartmentCourse(String department){        
        ArrayList<Course> Eduhistory = courseImpl.GetAllDepartmentCourses(department);
        tblModelDepartmentCouses.setRowCount(0);
        Eduhistory.forEach((course) -> {
            tblModelDepartmentCouses.addRow(new Object[]{
               course.getCourseCode(), 
               course.getCourse()+" "+course.getLevel()
            });
        });      
    }
    
    private void fillCourseModules(String courseCode){
        ArrayList<Module> courseModules = moduleImpl.GetAllCourseModules(courseCode);
        tblModelCourseModules.setRowCount(0);
        courseModules.forEach((module) -> {
            tblModelCourseModules.addRow(new Object[]{
               module.getModuleCode(), 
               module.getModule()
            });
        });        
    }
    
    
    public void StudentEnrolled(){        
        for(int i=0; i < tblModelSelectedModules.getRowCount();i++){
            tblModelCourseModules.addRow(new Object[]{
                tblModelSelectedModules.getValueAt(i, 0).toString(), 
                tblModelSelectedModules.getValueAt(i, 1).toString()
            });
        }        
        jtblDepartmentCourses.setEnabled(true);
        jcbDepartment.setEnabled(true);
        tblModelSelectedModules.setRowCount(0);
    }
    
    public void StudentEnrolledCourses(List<HashMap<String, Object>> enrolments){
        tblModelEnrolledCourses.setRowCount(0);
        enrolments.forEach((row) -> {
            tblModelEnrolledCourses.addRow(new Object[]{
                row.get("courseCode"),
                row.get("course") + " "+ row.get("level"),
                row.get("term"),
                row.get("campusABR")
             });
        });
    }
    
    public void StudentEnrolledCourseModules(List<HashMap<String, Object>> enrolmentModules){
        tblModelEnrolledCourseModules.setRowCount(0);
        enrolmentModules.forEach((row) -> {
            tblModelEnrolledCourseModules.addRow(new Object[]{
                row.get("moduleCode"),
                row.get("module")
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

        jLabel21 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jpnEnrolment = new javax.swing.JPanel();
        jpnSideMenu = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnEnrolemnts = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnEnrolStudent = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();
        jpnTop = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblStudent = new javax.swing.JLabel();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnEnrolments = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblEnrolledCourses = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblEnrolledCourseModules = new javax.swing.JTable();
        jpnEnrolStudent = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jcbDepartment = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbTerm = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbStudyType = new javax.swing.JComboBox<>();
        jcbCampus = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCourseModules = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSelectedModules = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblDepartmentCourses = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnEnrol = new javax.swing.JButton();

        jLabel21.setText("jLabel21");

        jTextField1.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        btnEnrolemnts.setBackground(Functions.activeMenu());
        btnEnrolemnts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnrolemnts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnrolemntsMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/courses.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(Functions.setFontColor());
        jLabel8.setText("Enrolments");

        javax.swing.GroupLayout btnEnrolemntsLayout = new javax.swing.GroupLayout(btnEnrolemnts);
        btnEnrolemnts.setLayout(btnEnrolemntsLayout);
        btnEnrolemntsLayout.setHorizontalGroup(
            btnEnrolemntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEnrolemntsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnEnrolemntsLayout.setVerticalGroup(
            btnEnrolemntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEnrolemntsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnEnrolemntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        btnEnrolStudent.setBackground(Functions.pnlBackgroundSideMenu());
        btnEnrolStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnrolStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnrolStudentMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/profile_add.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(Functions.setFontColor());
        jLabel14.setText("Register Module");

        javax.swing.GroupLayout btnEnrolStudentLayout = new javax.swing.GroupLayout(btnEnrolStudent);
        btnEnrolStudent.setLayout(btnEnrolStudentLayout);
        btnEnrolStudentLayout.setHorizontalGroup(
            btnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnEnrolStudentLayout.setVerticalGroup(
            btnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(Functions.setFontColor());
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("STUDENT");

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEnrolStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEnrolemnts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnrolemnts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnrolStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnTop.setBackground(Functions.pnlBackgroundSideMenu());
        jpnTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("SELF SERVICE:");

        lblStudent.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblStudent.setForeground(new java.awt.Color(204, 204, 204));
        lblStudent.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jpnTopLayout = new javax.swing.GroupLayout(jpnTop);
        jpnTop.setLayout(jpnTopLayout);
        jpnTopLayout.setHorizontalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTopLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTopLayout.setVerticalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTopLayout.createSequentialGroup()
                .addGroup(jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane8.setBorder(null);

        tblEnrolledCourses.setAutoCreateRowSorter(true);
        tblEnrolledCourses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblEnrolledCourses.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblEnrolledCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COURSE CODE", "COURSE", "TERM", "CAMPUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEnrolledCourses.setGridColor(new java.awt.Color(204, 204, 204));
        tblEnrolledCourses.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblEnrolledCourses.setRowHeight(40);
        tblEnrolledCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEnrolledCoursesMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblEnrolledCourses);
        if (tblEnrolledCourses.getColumnModel().getColumnCount() > 0) {
            tblEnrolledCourses.getColumnModel().getColumn(0).setPreferredWidth(130);
            tblEnrolledCourses.getColumnModel().getColumn(0).setMaxWidth(130);
            tblEnrolledCourses.getColumnModel().getColumn(3).setPreferredWidth(90);
            tblEnrolledCourses.getColumnModel().getColumn(3).setMaxWidth(90);
        }

        jScrollPane9.setBorder(null);

        tblEnrolledCourseModules.setAutoCreateRowSorter(true);
        tblEnrolledCourseModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblEnrolledCourseModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblEnrolledCourseModules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULE CODE", "MODULE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEnrolledCourseModules.setGridColor(new java.awt.Color(204, 204, 204));
        tblEnrolledCourseModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblEnrolledCourseModules.setRowHeight(40);
        tblEnrolledCourseModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEnrolledCourseModulesMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblEnrolledCourseModules);
        if (tblEnrolledCourseModules.getColumnModel().getColumnCount() > 0) {
            tblEnrolledCourseModules.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblEnrolledCourseModules.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        javax.swing.GroupLayout jpnEnrolmentsLayout = new javax.swing.GroupLayout(jpnEnrolments);
        jpnEnrolments.setLayout(jpnEnrolmentsLayout);
        jpnEnrolmentsLayout.setHorizontalGroup(
            jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnEnrolmentsLayout.setVerticalGroup(
            jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEnrolmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpnSlider.add(jpnEnrolments, "card3");

        jpnEnrolStudent.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Department:");

        jcbDepartment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbDepartment.setForeground(new java.awt.Color(51, 51, 51));
        jcbDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDepartmentActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Term:");

        jcbTerm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbTerm.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Study Type:");

        jcbStudyType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbStudyType.setForeground(new java.awt.Color(51, 51, 51));

        jcbCampus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbCampus.setForeground(new java.awt.Color(51, 51, 51));

        jScrollPane6.setBorder(null);

        tblCourseModules.setAutoCreateRowSorter(true);
        tblCourseModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblCourseModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblCourseModules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULE CODE", "MODULE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCourseModules.setGridColor(new java.awt.Color(204, 204, 204));
        tblCourseModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblCourseModules.setRowHeight(40);
        tblCourseModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCourseModulesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblCourseModules);
        if (tblCourseModules.getColumnModel().getColumnCount() > 0) {
            tblCourseModules.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblCourseModules.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        jScrollPane7.setBorder(null);

        tblSelectedModules.setAutoCreateRowSorter(true);
        tblSelectedModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblSelectedModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblSelectedModules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULE CODE", "MODULE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSelectedModules.setGridColor(new java.awt.Color(204, 204, 204));
        tblSelectedModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblSelectedModules.setRowHeight(40);
        tblSelectedModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSelectedModulesMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblSelectedModules);
        if (tblSelectedModules.getColumnModel().getColumnCount() > 0) {
            tblSelectedModules.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblSelectedModules.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        jScrollPane4.setBorder(null);

        jtblDepartmentCourses.setAutoCreateRowSorter(true);
        jtblDepartmentCourses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblDepartmentCourses.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblDepartmentCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COURSE CODE", "COURSE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblDepartmentCourses.setGridColor(new java.awt.Color(204, 204, 204));
        jtblDepartmentCourses.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblDepartmentCourses.setRowHeight(40);
        jtblDepartmentCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblDepartmentCoursesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtblDepartmentCourses);
        if (jtblDepartmentCourses.getColumnModel().getColumnCount() > 0) {
            jtblDepartmentCourses.getColumnModel().getColumn(0).setPreferredWidth(150);
            jtblDepartmentCourses.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        btnEnrol.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEnrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnEnrol.setText("Register");
        btnEnrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnEnrolStudentLayout = new javax.swing.GroupLayout(jpnEnrolStudent);
        jpnEnrolStudent.setLayout(jpnEnrolStudentLayout);
        jpnEnrolStudentLayout.setHorizontalGroup(
            jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jcbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEnrolStudentLayout.createSequentialGroup()
                .addGroup(jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4))
                    .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEnrolStudentLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEnrol, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jpnEnrolStudentLayout.setVerticalGroup(
            jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jcbStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnEnrol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jpnSlider.add(jpnEnrolStudent, "card3");

        javax.swing.GroupLayout jpnEnrolmentLayout = new javax.swing.GroupLayout(jpnEnrolment);
        jpnEnrolment.setLayout(jpnEnrolmentLayout);
        jpnEnrolmentLayout.setHorizontalGroup(
            jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolmentLayout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnEnrolmentLayout.setVerticalGroup(
            jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolmentLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnEnrolmentLayout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(jpnEnrolment, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnrolemntsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnrolemntsMouseClicked
        // TODO add your handling code here:
        if(lblStudent.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            profileRegistrationPressed();
            jpnSlider.nextPanel(10, jpnEnrolments, jpnSlider.right);
        }
    }//GEN-LAST:event_btnEnrolemntsMouseClicked

    private void btnEnrolStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnrolStudentMouseClicked
        // TODO add your handling code here:
        if(lblStudent.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnEnrolStudentPressed();
            jpnSlider.nextPanel(10, jpnEnrolStudent, jpnSlider.right);
        }
    }//GEN-LAST:event_btnEnrolStudentMouseClicked

    private void tblEnrolledCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEnrolledCoursesMouseClicked
        // TODO add your handling code here:
        int row = tblEnrolledCourses.getSelectedRow();
        selectedCourseCode = tblEnrolledCourses.getModel().getValueAt(row, 0).toString();
        selectedTerm = tblEnrolledCourses.getModel().getValueAt(row, 2).toString();
        StudentEnrolledCourseModules(enrolmentImpl.GetStudentCourseEnrolmentModules(personId, selectedCourseCode, selectedTerm));
    }//GEN-LAST:event_tblEnrolledCoursesMouseClicked

    private void tblEnrolledCourseModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEnrolledCourseModulesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEnrolledCourseModulesMouseClicked

    private void btnEnrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrolActionPerformed
        // TODO add your handling code here:
        StudyType studyType = (StudyType)jcbStudyType.getSelectedItem();
            if(!studyType.getStudyType().equalsIgnoreCase("SELECT") && !jcbDepartment.getSelectedItem().toString().equalsIgnoreCase("SELECT")){

            String course = "Selected courseCode:"+selectedCourseCode+"\n"+
            "Selected course:"+selectedCourse+"\n";

            String modules = "";

            Term term = (Term)jcbTerm.getSelectedItem();

            Campus campus = campusImpl.GetCampus(1); // Pretoria Campus

            ArrayList<String> selectedModules = new ArrayList<>();

            for(int i=0; i < tblModelSelectedModules.getRowCount();i++){
                String moduleCode = tblModelSelectedModules.getValueAt(i, 0).toString();
                String module = tblModelSelectedModules.getValueAt(i, 1).toString();
                modules += "Module Code: "+moduleCode+" , ";
                modules += "Module : "+module+"\n";
                selectedModules.add(moduleCode);
            }

            String extras = "Term :"+term.getTerm()+"\n"+
            "Study Type: "+studyType.getStudyType()+"\n"+
            "Campus :"+campus.getCampusABR()+"\n";

            if(selectedModules.size() > 0){
                int rowsInserted = enrolmentImpl.EnrolStudent(personId, selectedCourseCode, selectedModules, term, studyType, campus);
                if(rowsInserted == tblModelSelectedModules.getRowCount()){
                    StudentEnrolled();
                    StudentEnrolledCourses(enrolmentImpl.GetStudentCourseEnrolments(personId));
                    Functions.successMessage(course+extras+modules);
                }else{
                    Functions.errorMessage(course+extras+modules);
                }
            }else{
                Functions.errorMessage("Before registration, you must first select the modules!");
            }
        }else{
            Functions.errorMessage("Department and study type must be selected.");
        }
    }//GEN-LAST:event_btnEnrolActionPerformed

    private void jcbDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDepartmentActionPerformed
        // TODO add your handling code here:
        if(jcbDepartmentClicked){
            String department = jcbDepartment.getSelectedItem().toString();
            fillDepartmentCourse(department);
            termImpl.fillComboBoxByDepartment(jcbTerm, Functions.getDeptFromDepartment(department));
            tblModelCourseModules.setRowCount(0);
            selectedCourseCode = null;
            selectedCourse = null;
            selectedTerm = null;
        }
    }//GEN-LAST:event_jcbDepartmentActionPerformed

    private void tblCourseModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCourseModulesMouseClicked
        // TODO add your handling code here:
        int row = tblCourseModules.getSelectedRow();
        String moduleCode = (tblCourseModules.getModel().getValueAt(row, 0).toString());
        String module = (tblCourseModules.getModel().getValueAt(row, 1).toString());

        tblModelSelectedModules.addRow(new Object[]{
            moduleCode,
            module
        });
        tblModelCourseModules.removeRow(row);

        if(tblModelSelectedModules.getRowCount() > 0){
            jtblDepartmentCourses.setEnabled(false);
            jcbDepartment.setEnabled(false);
        }
    }//GEN-LAST:event_tblCourseModulesMouseClicked

    private void tblSelectedModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectedModulesMouseClicked
        // TODO add your handling code here:
        int row = tblSelectedModules.getSelectedRow();
        String moduleCode = (tblSelectedModules.getModel().getValueAt(row, 0).toString());
        String module = (tblSelectedModules.getModel().getValueAt(row, 1).toString());

        tblModelCourseModules.addRow(new Object[]{
            moduleCode,
            module
        });
        tblModelSelectedModules.removeRow(row);

        if(tblModelSelectedModules.getRowCount() == 0){
            jtblDepartmentCourses.setEnabled(true);
            jcbDepartment.setEnabled(true);
        }
    }//GEN-LAST:event_tblSelectedModulesMouseClicked

    private void jtblDepartmentCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblDepartmentCoursesMouseClicked
        // TODO add your handling code here:
        int selectedModules = tblModelSelectedModules.getRowCount();

        if(selectedModules == 0){
            int row = jtblDepartmentCourses.getSelectedRow();
            String courseCode = (jtblDepartmentCourses.getModel().getValueAt(row, 0).toString());
            String course = (jtblDepartmentCourses.getModel().getValueAt(row, 1).toString());

            selectedCourseCode = courseCode;
            selectedCourse = course;

            fillCourseModules(courseCode);
        }else{
            Functions.errorMessage("Sorry, you have already selected "+selectedModules+" modules for course ("+selectedCourseCode+","+selectedCourse+").\n"
                + "Either complete registration by clicking register button or remove all of the selected modules by clicking on each of them.");
        }
    }//GEN-LAST:event_jtblDepartmentCoursesMouseClicked

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
                new StudentSelfService().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JButton btnEnrol;
    private javax.swing.JPanel btnEnrolStudent;
    private javax.swing.JPanel btnEnrolemnts;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> jcbCampus;
    private javax.swing.JComboBox<String> jcbDepartment;
    private javax.swing.JComboBox<String> jcbStudyType;
    private javax.swing.JComboBox<String> jcbTerm;
    private javax.swing.JPanel jpnEnrolStudent;
    private javax.swing.JPanel jpnEnrolment;
    private javax.swing.JPanel jpnEnrolments;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JTable jtblDepartmentCourses;
    private javax.swing.JLabel lblStudent;
    private javax.swing.JTable tblCourseModules;
    private javax.swing.JTable tblEnrolledCourseModules;
    private javax.swing.JTable tblEnrolledCourses;
    private javax.swing.JTable tblSelectedModules;
    private com.molorane.college.view.Controls.TitlePnl title;
    // End of variables declaration//GEN-END:variables
}
