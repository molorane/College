/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.admin;

import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.CampusBoImpl;
import com.molorane.college.bll.impl.CourseBoImpl;
import com.molorane.college.bll.impl.ModuleBoImpl;
import com.molorane.college.bll.impl.ResultRemarkBoImpl;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.model.Campus;
import com.molorane.college.model.Course;
import com.molorane.college.model.Module;
import com.molorane.college.model.ResultRemark;
import com.molorane.college.model.User;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class Settings extends javax.swing.JFrame {
    
    private final CampusBoImpl campusImpl = new CampusBoImpl();
    private final CourseBoImpl courseImpl = new CourseBoImpl();
    private final ModuleBoImpl moduleImpl = new ModuleBoImpl();
    private final ResultRemarkBoImpl resultRemarkBoImpl = new ResultRemarkBoImpl();
    
    private DefaultTableModel tblModelCampuses;
    private int REF_CAMPUS;
    
    private boolean jcbDepartmentClicked = false;
    
    private DefaultTableModel tblModelDepartmentCouses;
    private DefaultTableModel tblModelCourseModules;
    private DefaultTableModel tblModelSelectedModules;
    private DefaultTableModel tblModelResultRemark;
    
    private String selectedCourseCode;
    private String selectedCourse;
    private String selectedTerm;
    
    private long personId;
    private final User loggedInUser;
    
    /**
     * Creates new form Settings
     */
    
    public Settings() {
        super("Settings - "+Functions.appName());
        initComponents();
        
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(Settings.this);
        
        courseImpl.fillComboBoxDepartment(jcbDepartment);
               
        jcbDepartmentClicked = true;
        
        tblModelDepartmentCouses = (DefaultTableModel)jtblDepartmentCourses.getModel();
        tblModelCourseModules = (DefaultTableModel)jtblCourseModules.getModel();      
        tblModelCampuses = (DefaultTableModel)tblCampuses.getModel();      
        tblModelResultRemark = (DefaultTableModel)jtblResultRemark.getModel();
        
        
        jtblDepartmentCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblDepartmentCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Functions.initForm(Settings.this);
    }
    
    void btnAdminHomePressed(){
        Functions.setColor(btnAdminHome);
        Functions.resetColor(btnCourses);
        Functions.resetColor(btnCampuses);
        Functions.resetColor(btnAdminCourseRegistration);
        Functions.resetColor(btnResultRemark);
    }
    
    void btnCoursesPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.setColor(btnCourses);
       Functions.resetColor(btnCampuses);
       Functions.resetColor(btnAdminCourseRegistration);
       Functions.resetColor(btnResultRemark);
    }
    
    void btnCampusesPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.resetColor(btnCourses);
       Functions.setColor(btnCampuses);
       Functions.resetColor(btnAdminCourseRegistration);
       Functions.resetColor(btnResultRemark);
    }
    
    void btnAdminCourseRegistrationPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.resetColor(btnCourses);
       Functions.resetColor(btnCampuses);
       Functions.setColor(btnAdminCourseRegistration);
       Functions.resetColor(btnResultRemark);
    }
    
    void btnResultRemarkPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.resetColor(btnCourses);
       Functions.resetColor(btnCampuses);
       Functions.resetColor(btnAdminCourseRegistration);
       Functions.setColor(btnResultRemark);
    }
    
    private void fillCampuses(){
        ArrayList<Campus> personEnrolments = campusImpl.GetAllCampuses();
        tblModelCampuses.setRowCount(0);
        personEnrolments.forEach((campus) -> {
            tblModelCampuses.addRow(new Object[]{
                campus.getCampusCode(),
                campus.getCampusName(),
                campus.getCampusABR(), 
                (campus.getIsHQ() != 0),
                campus.getTelephone(),
                campus.getFax(),
                campus.getAddress(),
                campus.getEmail()
            });
        });
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
    
    private void fillResultRemark(){
        ArrayList<ResultRemark> resultRemarks = resultRemarkBoImpl.GetAllResultRemarks();
        tblModelResultRemark.setRowCount(0);
        resultRemarks.forEach((remark) -> {
            tblModelResultRemark.addRow(new Object[]{
               remark.getResultRemarkId(),
                remark.getResultRemark()
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

        jTextField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btngTyoe = new javax.swing.ButtonGroup();
        buttonGroupIsHq = new javax.swing.ButtonGroup();
        jpnSettings = new javax.swing.JPanel();
        jpnSideMenu = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAdminHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCourses = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAdminCourseRegistration = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnCampuses = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnResultRemark = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        btnStaff = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        btnStaff1 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        btnStaff2 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnHome = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jpnCourses = new javax.swing.JPanel();
        jcbDepartment = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtblDepartmentCourses = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtblCourseModules = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jpnCampuses = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCampuses = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtCampus = new javax.swing.JTextField();
        txtTelephone = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtShortName = new javax.swing.JTextField();
        jrbtnISHQYes = new javax.swing.JRadioButton();
        jrbtnISHQNo = new javax.swing.JRadioButton();
        jLabel66 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jtbtnSaveCampus = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jpnRegisteredCourses = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPersonEnrolments = new javax.swing.JTable();
        jpnResultRemark = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtblResultRemark = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        jLabel21.setText("jLabel21");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SETTINGS");

        btnAdminHome.setBackground(new java.awt.Color(64, 43, 100));
        btnAdminHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminHomeMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/home.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Home");

        javax.swing.GroupLayout btnAdminHomeLayout = new javax.swing.GroupLayout(btnAdminHome);
        btnAdminHome.setLayout(btnAdminHomeLayout);
        btnAdminHomeLayout.setHorizontalGroup(
            btnAdminHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminHomeLayout.setVerticalGroup(
            btnAdminHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        btnCourses.setBackground(Functions.pnlBackgroundSideMenu());
        btnCourses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCoursesMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/courses.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Courses");

        javax.swing.GroupLayout btnCoursesLayout = new javax.swing.GroupLayout(btnCourses);
        btnCourses.setLayout(btnCoursesLayout);
        btnCoursesLayout.setHorizontalGroup(
            btnCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCoursesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCoursesLayout.setVerticalGroup(
            btnCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        btnAdminCourseRegistration.setBackground(Functions.pnlBackgroundSideMenu());
        btnAdminCourseRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminCourseRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminCourseRegistrationMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/addons.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Positions");

        javax.swing.GroupLayout btnAdminCourseRegistrationLayout = new javax.swing.GroupLayout(btnAdminCourseRegistration);
        btnAdminCourseRegistration.setLayout(btnAdminCourseRegistrationLayout);
        btnAdminCourseRegistrationLayout.setHorizontalGroup(
            btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminCourseRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminCourseRegistrationLayout.setVerticalGroup(
            btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        btnCampuses.setBackground(Functions.pnlBackgroundSideMenu());
        btnCampuses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCampuses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCampusesMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/enterprise.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Campuses");

        javax.swing.GroupLayout btnCampusesLayout = new javax.swing.GroupLayout(btnCampuses);
        btnCampuses.setLayout(btnCampusesLayout);
        btnCampusesLayout.setHorizontalGroup(
            btnCampusesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCampusesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCampusesLayout.setVerticalGroup(
            btnCampusesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCampusesLayout.createSequentialGroup()
                .addGroup(btnCampusesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        btnResultRemark.setBackground(Functions.pnlBackgroundSideMenu());
        btnResultRemark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResultRemarkMouseClicked(evt);
            }
        });

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/bookmark.png"))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(204, 204, 204));
        jLabel56.setText("Result Remarks");

        javax.swing.GroupLayout btnResultRemarkLayout = new javax.swing.GroupLayout(btnResultRemark);
        btnResultRemark.setLayout(btnResultRemarkLayout);
        btnResultRemarkLayout.setHorizontalGroup(
            btnResultRemarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnResultRemarkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        btnResultRemarkLayout.setVerticalGroup(
            btnResultRemarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        btnStaff.setBackground(Functions.pnlBackgroundSideMenu());
        btnStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaffMouseClicked(evt);
            }
        });

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/church.png"))); // NOI18N

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(204, 204, 204));
        jLabel61.setText("Religion");

        javax.swing.GroupLayout btnStaffLayout = new javax.swing.GroupLayout(btnStaff);
        btnStaff.setLayout(btnStaffLayout);
        btnStaffLayout.setHorizontalGroup(
            btnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addGap(18, 18, 18)
                .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStaffLayout.setVerticalGroup(
            btnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        btnStaff1.setBackground(Functions.pnlBackgroundSideMenu());
        btnStaff1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaff1MouseClicked(evt);
            }
        });

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/themes.png"))); // NOI18N

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(204, 204, 204));
        jLabel63.setText("Relationship");

        javax.swing.GroupLayout btnStaff1Layout = new javax.swing.GroupLayout(btnStaff1);
        btnStaff1.setLayout(btnStaff1Layout);
        btnStaff1Layout.setHorizontalGroup(
            btnStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStaff1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addGap(18, 18, 18)
                .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStaff1Layout.setVerticalGroup(
            btnStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        btnStaff2.setBackground(Functions.pnlBackgroundSideMenu());
        btnStaff2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaff2MouseClicked(evt);
            }
        });

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/keys.png"))); // NOI18N

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(204, 204, 204));
        jLabel65.setText("Roles");

        javax.swing.GroupLayout btnStaff2Layout = new javax.swing.GroupLayout(btnStaff2);
        btnStaff2.setLayout(btnStaff2Layout);
        btnStaff2Layout.setHorizontalGroup(
            btnStaff2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStaff2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addGap(18, 18, 18)
                .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStaff2Layout.setVerticalGroup(
            btnStaff2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSideMenuLayout.createSequentialGroup()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStaff2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStaff1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStaff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCourses, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCampuses, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResultRemark, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCampuses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResultRemark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStaff1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStaff2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdminCourseRegistration, btnAdminHome, btnCampuses, btnCourses, btnResultRemark, btnStaff, btnStaff1, btnStaff2});

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnHomeLayout = new javax.swing.GroupLayout(jpnHome);
        jpnHome.setLayout(jpnHomeLayout);
        jpnHomeLayout.setHorizontalGroup(
            jpnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnHomeLayout.setVerticalGroup(
            jpnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnHome, "card2");

        jcbDepartment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbDepartment.setForeground(new java.awt.Color(51, 51, 51));
        jcbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jcbDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDepartmentActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Department:");

        jScrollPane5.setBorder(null);

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
        jScrollPane5.setViewportView(jtblDepartmentCourses);
        if (jtblDepartmentCourses.getColumnModel().getColumnCount() > 0) {
            jtblDepartmentCourses.getColumnModel().getColumn(0).setPreferredWidth(150);
            jtblDepartmentCourses.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        jScrollPane6.setBorder(null);

        jtblCourseModules.setAutoCreateRowSorter(true);
        jtblCourseModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblCourseModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblCourseModules.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblCourseModules.setGridColor(new java.awt.Color(204, 204, 204));
        jtblCourseModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblCourseModules.setRowHeight(40);
        jtblCourseModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblCourseModulesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtblCourseModules);
        if (jtblCourseModules.getColumnModel().getColumnCount() > 0) {
            jtblCourseModules.getColumnModel().getColumn(0).setPreferredWidth(150);
            jtblCourseModules.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        javax.swing.GroupLayout jpnCoursesLayout = new javax.swing.GroupLayout(jpnCourses);
        jpnCourses.setLayout(jpnCoursesLayout);
        jpnCoursesLayout.setHorizontalGroup(
            jpnCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCoursesLayout.createSequentialGroup()
                .addContainerGap(5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnCoursesLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
            .addGroup(jpnCoursesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        jpnCoursesLayout.setVerticalGroup(
            jpnCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCoursesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)))
        );

        jpnSlider.add(jpnCourses, "card3");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane4.setBorder(null);

        tblCampuses.setAutoCreateRowSorter(true);
        tblCampuses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblCampuses.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblCampuses.setForeground(new java.awt.Color(102, 102, 102));
        tblCampuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REF #", "Campus", "Shortname", "isHQ", "Telephone", "Fax", "Address", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCampuses.setGridColor(new java.awt.Color(204, 204, 204));
        tblCampuses.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblCampuses.setRowHeight(40);
        tblCampuses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCampusesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblCampuses);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Campus Information"));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("Campus :");

        txtCampus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTelephone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Campus short name:");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Head Quaters:");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 102, 102));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("Telephone:");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 102, 102));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Fax:");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("Address:");

        txtFax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtShortName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        buttonGroupIsHq.add(jrbtnISHQYes);
        jrbtnISHQYes.setForeground(new java.awt.Color(102, 102, 102));
        jrbtnISHQYes.setText("Yes");

        buttonGroupIsHq.add(jrbtnISHQNo);
        jrbtnISHQNo.setForeground(new java.awt.Color(102, 102, 102));
        jrbtnISHQNo.setSelected(true);
        jrbtnISHQNo.setText("No");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 102, 102));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel66.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtShortName, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtCampus)
                            .addComponent(txtTelephone, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jrbtnISHQYes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jrbtnISHQNo))
                            .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCampus, txtFax, txtShortName, txtTelephone});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txtCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jrbtnISHQYes)
                    .addComponent(jrbtnISHQNo))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCampus, txtFax, txtShortName, txtTelephone});

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));

        jtbtnSaveCampus.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jtbtnSaveCampus.setForeground(new java.awt.Color(102, 102, 102));
        jtbtnSaveCampus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/save.png"))); // NOI18N
        jtbtnSaveCampus.setText("SAVE");
        jtbtnSaveCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbtnSaveCampusActionPerformed(evt);
            }
        });

        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(102, 102, 102));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/trash.png"))); // NOI18N
        jToggleButton2.setText("REMOVE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbtnSaveCampus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jtbtnSaveCampus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton2)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCampusesLayout = new javax.swing.GroupLayout(jpnCampuses);
        jpnCampuses.setLayout(jpnCampusesLayout);
        jpnCampusesLayout.setHorizontalGroup(
            jpnCampusesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnCampusesLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCampusesLayout.setVerticalGroup(
            jpnCampusesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCampusesLayout.createSequentialGroup()
                .addGroup(jpnCampusesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnCampuses, "card3");

        jScrollPane3.setBorder(null);

        tblPersonEnrolments.setAutoCreateRowSorter(true);
        tblPersonEnrolments.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblPersonEnrolments.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblPersonEnrolments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseCode", "Course", "Term", "Study Type", "Module Code", "Module"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPersonEnrolments.setGridColor(new java.awt.Color(204, 204, 204));
        tblPersonEnrolments.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblPersonEnrolments.setRowHeight(40);
        jScrollPane3.setViewportView(tblPersonEnrolments);

        javax.swing.GroupLayout jpnRegisteredCoursesLayout = new javax.swing.GroupLayout(jpnRegisteredCourses);
        jpnRegisteredCourses.setLayout(jpnRegisteredCoursesLayout);
        jpnRegisteredCoursesLayout.setHorizontalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );
        jpnRegisteredCoursesLayout.setVerticalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnRegisteredCoursesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnRegisteredCourses, "card3");

        jPanel12.setBackground(Functions.pnlBackgroundBottom());
        jPanel12.setToolTipText("");

        jLabel59.setBackground(Functions.pnlBackgroundBottom());
        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 204, 204));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Result Remarks according to the department");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane7.setBorder(null);

        jtblResultRemark.setAutoCreateRowSorter(true);
        jtblResultRemark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblResultRemark.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblResultRemark.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblResultRemark.setGridColor(new java.awt.Color(204, 204, 204));
        jtblResultRemark.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblResultRemark.setRowHeight(40);
        jtblResultRemark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblResultRemarkMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtblResultRemark);
        if (jtblResultRemark.getColumnModel().getColumnCount() > 0) {
            jtblResultRemark.getColumnModel().getColumn(0).setPreferredWidth(150);
            jtblResultRemark.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 939, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );

        javax.swing.GroupLayout jpnResultRemarkLayout = new javax.swing.GroupLayout(jpnResultRemark);
        jpnResultRemark.setLayout(jpnResultRemarkLayout);
        jpnResultRemarkLayout.setHorizontalGroup(
            jpnResultRemarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnResultRemarkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnResultRemarkLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jSeparator3))
            .addGroup(jpnResultRemarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnResultRemarkLayout.createSequentialGroup()
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jpnResultRemarkLayout.setVerticalGroup(
            jpnResultRemarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnResultRemarkLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnResultRemarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnResultRemarkLayout.createSequentialGroup()
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 546, Short.MAX_VALUE)))
        );

        jpnSlider.add(jpnResultRemark, "card6");

        javax.swing.GroupLayout jpnSettingsLayout = new javax.swing.GroupLayout(jpnSettings);
        jpnSettings.setLayout(jpnSettingsLayout);
        jpnSettingsLayout.setHorizontalGroup(
            jpnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSettingsLayout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
                    .addGroup(jpnSettingsLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(alert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnSettingsLayout.setVerticalGroup(
            jpnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSettingsLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jpnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSettingsLayout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jpnSettings, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdminHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminHomeMouseClicked
        // TODO add your handling code here:
        btnAdminHomePressed();
        jpnSlider.nextPanel(10, jpnHome, jpnSlider.right);
    }//GEN-LAST:event_btnAdminHomeMouseClicked

    private void btnCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCoursesMouseClicked
        // TODO add your handling code here:
        btnCoursesPressed();
        jpnSlider.nextPanel(10, jpnCourses, jpnSlider.right);
    }//GEN-LAST:event_btnCoursesMouseClicked

    private void btnAdminCourseRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminCourseRegistrationMouseClicked
        // TODO add your handling code here:
        btnAdminCourseRegistrationPressed();
        jpnSlider.nextPanel(10, jpnHome, jpnSlider.right);
    }//GEN-LAST:event_btnAdminCourseRegistrationMouseClicked

    private void btnCampusesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCampusesMouseClicked
        // TODO add your handling code here:
        btnCampusesPressed();
       if(tblModelCampuses.getRowCount() == 0){
           fillCampuses();
       } 
       jpnSlider.nextPanel(10, jpnCampuses, jpnSlider.right);
    }//GEN-LAST:event_btnCampusesMouseClicked

    private void btnResultRemarkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResultRemarkMouseClicked
        // TODO add your handling code here:
        btnResultRemarkPressed();
        if(tblModelResultRemark.getRowCount() == 0){
            fillResultRemark();
        }
        jpnSlider.nextPanel(10, jpnResultRemark, jpnSlider.right);
    }//GEN-LAST:event_btnResultRemarkMouseClicked

    private void btnStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaffMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStaffMouseClicked

    private void btnStaff1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaff1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStaff1MouseClicked

    private void btnStaff2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaff2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStaff2MouseClicked

    private void tblCampusesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCampusesMouseClicked
        // TODO add your handling code here:
        int row = tblCampuses.getSelectedRow();
        REF_CAMPUS = Integer.parseInt(tblCampuses.getModel().getValueAt(row, 0).toString());
        txtCampus.setText(tblCampuses.getModel().getValueAt(row, 1).toString());
        txtShortName.setText(tblCampuses.getModel().getValueAt(row, 2).toString());
        txtTelephone.setText(tblCampuses.getModel().getValueAt(row, 4).toString());
        txtFax.setText(tblCampuses.getModel().getValueAt(row, 5).toString());
        txtAddress.setText(tblCampuses.getModel().getValueAt(row, 6).toString());
        txtEmail.setText(tblCampuses.getModel().getValueAt(row, 7).toString());
        if((Boolean)tblCampuses.getModel().getValueAt(row, 3)){
            jrbtnISHQYes.setSelected(true);
        }else{
            jrbtnISHQNo.setSelected(true);
        }
    }//GEN-LAST:event_tblCampusesMouseClicked

    private void jtbtnSaveCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbtnSaveCampusActionPerformed
        // TODO add your handling code here:
        int saved = 0;
        
        Campus campus = new Campus();
        campus.setCampusName(txtCampus.getText());
        campus.setCampusABR(txtShortName.getText());
        if(jrbtnISHQYes.isSelected()){
            campus.setIsHQ(1);
        }else{
            campus.setIsHQ(0);
        }
        campus.setTelephone(txtTelephone.getText());
        campus.setFax(txtFax.getText());
        campus.setAddress(txtAddress.getText());
        campus.setEmail(txtEmail.getText());
        
        if(REF_CAMPUS > 0){
            campus.setCampusCode(REF_CAMPUS);
        }
        
        REF_CAMPUS = 0;        
        saved = campusImpl.EditCampus(campus);
        
        if(saved > 0){
            Functions.successMessage("Campus saved.");
            fillCampuses();
        }
    }//GEN-LAST:event_jtbtnSaveCampusActionPerformed

    private void jcbDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDepartmentActionPerformed
        // TODO add your handling code here:
        if(jcbDepartmentClicked){
            String department = jcbDepartment.getSelectedItem().toString();
            fillDepartmentCourse(department);
            tblModelCourseModules.setRowCount(0);
            selectedCourseCode = null;
            selectedCourse = null;
            selectedTerm = null;
        }
    }//GEN-LAST:event_jcbDepartmentActionPerformed

    private void jtblDepartmentCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblDepartmentCoursesMouseClicked
        // TODO add your handling code here:
         int row = jtblDepartmentCourses.getSelectedRow();
        String courseCode = (jtblDepartmentCourses.getModel().getValueAt(row, 0).toString());
        String course = (jtblDepartmentCourses.getModel().getValueAt(row, 1).toString());

        selectedCourseCode = courseCode;
        selectedCourse = course;

        fillCourseModules(courseCode);
    }//GEN-LAST:event_jtblDepartmentCoursesMouseClicked

    private void jtblCourseModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblCourseModulesMouseClicked
        // TODO add your handling code here:
        int row = jtblCourseModules.getSelectedRow();
        String moduleCode = (jtblCourseModules.getModel().getValueAt(row, 0).toString());
        String module = (jtblCourseModules.getModel().getValueAt(row, 1).toString());

        tblModelSelectedModules.addRow(new Object[]{
            moduleCode,
            module
        });
        tblModelCourseModules.removeRow(row);

        if(tblModelSelectedModules.getRowCount() > 0){
            jtblDepartmentCourses.setEnabled(false);
            jcbDepartment.setEnabled(false);
        }
    }//GEN-LAST:event_jtblCourseModulesMouseClicked

    private void jtblResultRemarkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblResultRemarkMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtblResultRemarkMouseClicked

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
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JPanel btnAdminCourseRegistration;
    private javax.swing.JPanel btnAdminHome;
    private javax.swing.JPanel btnCampuses;
    private javax.swing.JPanel btnCourses;
    private javax.swing.JPanel btnResultRemark;
    private javax.swing.JPanel btnStaff;
    private javax.swing.JPanel btnStaff1;
    private javax.swing.JPanel btnStaff2;
    private javax.swing.ButtonGroup btngTyoe;
    private javax.swing.ButtonGroup buttonGroupIsHq;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JComboBox<String> jcbDepartment;
    private javax.swing.JPanel jpnCampuses;
    private javax.swing.JPanel jpnCourses;
    private javax.swing.JPanel jpnHome;
    private javax.swing.JPanel jpnRegisteredCourses;
    private javax.swing.JPanel jpnResultRemark;
    private javax.swing.JPanel jpnSettings;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JRadioButton jrbtnISHQNo;
    private javax.swing.JRadioButton jrbtnISHQYes;
    private javax.swing.JTable jtblCourseModules;
    private javax.swing.JTable jtblDepartmentCourses;
    private javax.swing.JTable jtblResultRemark;
    private javax.swing.JToggleButton jtbtnSaveCampus;
    private javax.swing.JTable tblCampuses;
    private javax.swing.JTable tblPersonEnrolments;
    private com.molorane.college.view.Controls.TitlePnl title;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCampus;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtShortName;
    private javax.swing.JTextField txtTelephone;
    // End of variables declaration//GEN-END:variables
}
