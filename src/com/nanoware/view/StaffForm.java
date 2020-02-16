/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import com.nanoware.bll.Functions;
import com.nanoware.bll.impl.CampusBoImpl;
import com.nanoware.bll.impl.CourseBoImpl;
import com.nanoware.bll.impl.LectureBoImpl;
import com.nanoware.bll.impl.ModuleBoImpl;
import com.nanoware.bll.impl.PersonBoImpl;
import com.nanoware.bll.impl.RoleBoImpl;
import com.nanoware.bll.impl.StaffBoImpl;
import com.nanoware.bll.impl.TermBoImpl;
import com.nanoware.model.Campus;
import com.nanoware.model.Course;
import com.nanoware.model.Lecture;
import com.nanoware.model.Module;
import com.nanoware.model.Person;
import com.nanoware.model.Role;
import com.nanoware.model.User;
import com.nanoware.model.Staff;
import com.nanoware.model.Term;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class StaffForm extends javax.swing.JFrame {
    
    private final StaffBoImpl staffBoImpl = new StaffBoImpl();
    private final CampusBoImpl campusBoImpl = new CampusBoImpl();
    private final LectureBoImpl lectureBoImpl = new LectureBoImpl();
    private final TermBoImpl termBoImpl = new TermBoImpl();
    private final CourseBoImpl courseImpl = new CourseBoImpl();
    private final ModuleBoImpl moduleImpl = new ModuleBoImpl();
    private final RoleBoImpl roleImpl = new RoleBoImpl();
    private final PersonBoImpl personBoImpl = new PersonBoImpl();
    
    private DefaultTableModel tblModelStaffList;
    private boolean jcbDepartmentClicked = false;
    
    private DefaultTableModel tblModelLectureModules;
    private DefaultTableModel tblModelUserContracts;
    
    private DefaultTableModel tblModelDepartmentCouses;
    private DefaultTableModel tblModelCourseModules;
    
    private String selectedCourseCode;
    private String selectedCourse;
    
    private String selectedModuleCode;
    private String selectedModule;
    
    
    private int staffNo;
    private final User loggedInUser;
    private boolean jcbMSCampusesFilled = false;
    
    private Campus selectedCampus;
    private int lmId;
    private int contractId;
    
    private Role selectedRole;
    
    
    private boolean jcbRoleClicked = false;

    /**
     * Creates new form Staff
     */
    
    public StaffForm() {
        super("Staff - "+Functions.appName());
        initComponents();
        
        Functions.setAppIcon(StaffForm.this);
      
        loggedInUser = Functions.GetUser("blessy"); //LoginSession.getSessionUser();
        
        campusBoImpl.fillComboBoxCampuses(jcbMSCampus);
        termBoImpl.fillComboBoxTerm(jcbTerm);
        jcbMSCampusesFilled = true;
                
        tblModelStaffList = (DefaultTableModel)tblStaffList.getModel();
        tblModelLectureModules = (DefaultTableModel)tblLectureModules.getModel();
        tblModelUserContracts = (DefaultTableModel)tblUserContracts.getModel();
        
        courseImpl.fillComboBoxDepartment(jcbDepartment);
        jcbDepartmentClicked = true;
        
        roleImpl.fillComboBoxRole(jcbRole);
        roleImpl.fillComboBoxRole(jcbMSRole);
        jcbRoleClicked = true;
        
        tblModelDepartmentCouses = (DefaultTableModel)jtblDepartmentCourses.getModel();
        tblModelCourseModules = (DefaultTableModel)jtblCourseModules.getModel();
        jtblDepartmentCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblCourseModules.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblUserContracts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        Functions.setLoggedInUser(lblHomeLoggedIn,loggedInUser);
        setLocationRelativeTo(null);
    }
    
    public StaffForm(String moduleCode,String module) {
        txtModuleCode.setText(moduleCode);
        lblModule.setText(module);
        loggedInUser = Functions.GetUser("blessy");
    }
    
    void btnFindStaff(){
        setColor(btnStaffHome);
        resetColor(btnAdminProfileRegistration);
        resetColor(btnAdminEducationHistory);
        resetColor(btnAdminCourseRegistration);
        resetColor(btnStudentFiles);
        
        jpnFindStaff.setVisible(true);
        jpnStaff.setVisible(false);
        jpnEducationHistory.setVisible(false);
        jpnRegisteredCourses.setVisible(false);
        jpnStudentFiles.setVisible(false);
    }
    
    void btnPersonnel(){
       resetColor(btnStaffHome);
       setColor(btnAdminProfileRegistration);
       resetColor(btnAdminEducationHistory);
       resetColor(btnAdminCourseRegistration);
       resetColor(btnStudentFiles);
       
       jpnFindStaff.setVisible(false);
       jpnStaff.setVisible(true);
       jpnEducationHistory.setVisible(false);
       jpnRegisteredCourses.setVisible(false);
       jpnStudentFiles.setVisible(false);
    }
    
    void btnAdminEducationHistoryPressed(){
       resetColor(btnStaffHome);
       resetColor(btnAdminProfileRegistration);
       setColor(btnAdminEducationHistory);
       resetColor(btnAdminCourseRegistration);
       resetColor(btnStudentFiles);
       
       jpnFindStaff.setVisible(false);
       jpnStaff.setVisible(false);
       jpnEducationHistory.setVisible(true);
       jpnRegisteredCourses.setVisible(false);
       jpnStudentFiles.setVisible(false);
    }
    
    void btnAdminCourseRegistrationPressed(){
       resetColor(btnStaffHome);
       resetColor(btnAdminProfileRegistration);
       resetColor(btnAdminEducationHistory);
       setColor(btnAdminCourseRegistration);
       resetColor(btnStudentFiles);
       
       jpnFindStaff.setVisible(false);
       jpnStaff.setVisible(false);
       jpnEducationHistory.setVisible(false);
       jpnRegisteredCourses.setVisible(true);
       jpnStudentFiles.setVisible(false);
    }
    
    void btnStudentFilesPressed(){
       resetColor(btnStaffHome);
       resetColor(btnAdminProfileRegistration);
       resetColor(btnAdminEducationHistory);
       resetColor(btnAdminCourseRegistration);
       setColor(btnStudentFiles);
       
       jpnFindStaff.setVisible(false);
       jpnStaff.setVisible(false);
       jpnEducationHistory.setVisible(false);
       jpnRegisteredCourses.setVisible(false);
       jpnStudentFiles.setVisible(true);
    }
    
    void setColor(JPanel panel){
        panel.setBackground(new Color(64,43,100));
    }
    
    void resetColor(JPanel panel){
       panel.setBackground(new Color(54,33,89)); 
    }
    
    private void displayStaff(ArrayList<Staff> staffList){
        tblModelStaffList.setRowCount(0);
        staffList.forEach((bean) -> {
            tblModelStaffList.addRow(new Object[]{
               bean.getPersonId(), 
               bean.getLastName(),
               bean.getFirstName(),
               bean.getOtherName(),
               bean.getRole(),
               bean.getContractFrom(),
               bean.getContractTo()
            });
        });
    }
    
    private void fillStaffList(int campusCode){
        ArrayList<Staff> staffList = staffBoImpl.GetCampusStaff(campusCode);
        displayStaff(staffList);
    }
    
    private void fillStaffList(int campusCode,int roleId){
        ArrayList<Staff> staffList = staffBoImpl.GetStaffByCampusAndRole(campusCode, roleId);
        displayStaff(staffList);
    }
       
    
    public void fillLectureModules(int lectureId){
         ArrayList<Lecture> lectureModules = lectureBoImpl.GetLectureModules(lectureId);
        tblModelLectureModules.setRowCount(0);
        lectureModules.forEach((row) -> {
            tblModelLectureModules.addRow(new Object[]{
                row.getLmId(),
                row.getCampus(),
                row.getTerm(),
                row.getModuleCode(),
                row.getModule()
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
    
    private void fillUserContracts(int personnel){
        ArrayList<Staff> staffList = staffBoImpl.GetStaffContracts(personnel);
        tblModelUserContracts.setRowCount(0);
        staffList.forEach((bean) -> {
            tblModelUserContracts.addRow(new Object[]{
               bean.getContractId(), 
               bean.getRole(),
               bean.getContractFrom(),
               bean.getContractTo()
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

        jpnSideMenu = new javax.swing.JPanel();
        btnStaffHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdminProfileRegistration = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAdminCourseRegistration = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnAdminEducationHistory = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnStudentFiles = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jpnTop = new javax.swing.JPanel();
        lblHomeLoggedIn = new javax.swing.JLabel();
        jpnControls = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jcbMSCampus = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jcbMSRole = new javax.swing.JComboBox<>();
        jpnContainer = new javax.swing.JPanel();
        jpnFindStaff = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStaffList = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtStaffNo = new javax.swing.JTextField();
        lblStaffNames = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblUserContracts = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jcbRole = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jdpContractFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel20 = new javax.swing.JLabel();
        jdpContractTo = new org.jdesktop.swingx.JXDatePicker();
        btnAddContract = new javax.swing.JButton();
        btnRemoveContract = new javax.swing.JButton();
        jpnStaff = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblLectureModules = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpnAddModule = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtModuleCode = new javax.swing.JTextField();
        lblFindModule = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblModule = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jcbTerm = new javax.swing.JComboBox<>();
        btnAddLectureModule = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jpnFindModule = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblDepartmentCourses = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jcbDepartment = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtblCourseModules = new javax.swing.JTable();
        lblSelectedSatff = new javax.swing.JLabel();
        jpnEducationHistory = new javax.swing.JPanel();
        jpnRegisteredCourses = new javax.swing.JPanel();
        jpnStudentFiles = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        btnStaffHome.setBackground(new java.awt.Color(64, 43, 100));
        btnStaffHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStaffHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaffHomeMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/search.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Find Staff");

        javax.swing.GroupLayout btnStaffHomeLayout = new javax.swing.GroupLayout(btnStaffHome);
        btnStaffHome.setLayout(btnStaffHomeLayout);
        btnStaffHomeLayout.setHorizontalGroup(
            btnStaffHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStaffHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStaffHomeLayout.setVerticalGroup(
            btnStaffHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStaffHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnStaffHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAdminProfileRegistration.setBackground(new java.awt.Color(54, 33, 89));
        btnAdminProfileRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminProfileRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminProfileRegistrationMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/skills.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Personnel");

        javax.swing.GroupLayout btnAdminProfileRegistrationLayout = new javax.swing.GroupLayout(btnAdminProfileRegistration);
        btnAdminProfileRegistration.setLayout(btnAdminProfileRegistrationLayout);
        btnAdminProfileRegistrationLayout.setHorizontalGroup(
            btnAdminProfileRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminProfileRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminProfileRegistrationLayout.setVerticalGroup(
            btnAdminProfileRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminProfileRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminProfileRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAdminCourseRegistration.setBackground(new java.awt.Color(54, 33, 89));
        btnAdminCourseRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminCourseRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminCourseRegistrationMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analytics.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Results");

        javax.swing.GroupLayout btnAdminCourseRegistrationLayout = new javax.swing.GroupLayout(btnAdminCourseRegistration);
        btnAdminCourseRegistration.setLayout(btnAdminCourseRegistrationLayout);
        btnAdminCourseRegistrationLayout.setHorizontalGroup(
            btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminCourseRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminCourseRegistrationLayout.setVerticalGroup(
            btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminCourseRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("STAFF");

        btnAdminEducationHistory.setBackground(new java.awt.Color(54, 33, 89));
        btnAdminEducationHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminEducationHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminEducationHistoryMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/percent.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Assessments");

        javax.swing.GroupLayout btnAdminEducationHistoryLayout = new javax.swing.GroupLayout(btnAdminEducationHistory);
        btnAdminEducationHistory.setLayout(btnAdminEducationHistoryLayout);
        btnAdminEducationHistoryLayout.setHorizontalGroup(
            btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminEducationHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminEducationHistoryLayout.setVerticalGroup(
            btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminEducationHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        btnStudentFiles.setBackground(new java.awt.Color(54, 33, 89));
        btnStudentFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentFilesMouseClicked(evt);
            }
        });

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/bookmark.png"))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(204, 204, 204));
        jLabel56.setText("Others");

        javax.swing.GroupLayout btnStudentFilesLayout = new javax.swing.GroupLayout(btnStudentFiles);
        btnStudentFiles.setLayout(btnStudentFilesLayout);
        btnStudentFilesLayout.setHorizontalGroup(
            btnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStudentFilesLayout.setVerticalGroup(
            btnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56)
                    .addComponent(jLabel54))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnStudentFiles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminEducationHistory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminProfileRegistration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStaffHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnStaffHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminProfileRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminEducationHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudentFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnTop.setBackground(Functions.pnlBackgroundTop());
        jpnTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHomeLoggedIn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblHomeLoggedIn.setForeground(new java.awt.Color(204, 204, 204));
        lblHomeLoggedIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHomeLoggedIn.setText("ADMIN: Mohlomi M");

        javax.swing.GroupLayout jpnTopLayout = new javax.swing.GroupLayout(jpnTop);
        jpnTop.setLayout(jpnTopLayout);
        jpnTopLayout.setHorizontalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTopLayout.setVerticalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTopLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        jpnControls.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Campus:");

        jcbMSCampus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSCampus.setForeground(new java.awt.Color(51, 51, 51));
        jcbMSCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSCampusActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Role:");

        jcbMSRole.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSRole.setForeground(new java.awt.Color(51, 51, 51));
        jcbMSRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnControlsLayout = new javax.swing.GroupLayout(jpnControls);
        jpnControls.setLayout(jpnControlsLayout);
        jpnControlsLayout.setHorizontalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMSCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMSRole, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnControlsLayout.setVerticalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbMSRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGroup(jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbMSCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnContainer.setLayout(new java.awt.CardLayout());

        jScrollPane5.setBorder(null);

        tblStaffList.setAutoCreateRowSorter(true);
        tblStaffList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStaffList.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStaffList.setForeground(new java.awt.Color(51, 51, 51));
        tblStaffList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StaffNo", "LastName", "FirstName", "OtherName", "Role", "ContractFrom", "ContractTo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStaffList.setGridColor(new java.awt.Color(204, 204, 204));
        tblStaffList.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblStaffList.setRowHeight(40);
        tblStaffList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblStaffList);
        if (tblStaffList.getColumnModel().getColumnCount() > 0) {
            tblStaffList.getColumnModel().getColumn(0).setHeaderValue("StaffNo");
            tblStaffList.getColumnModel().getColumn(1).setHeaderValue("LastName");
            tblStaffList.getColumnModel().getColumn(2).setHeaderValue("FirstName");
            tblStaffList.getColumnModel().getColumn(3).setHeaderValue("OtherName");
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Staff Contract", jPanel5);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Staff No:");

        txtStaffNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStaffNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStaffNoKeyReleased(evt);
            }
        });

        lblStaffNames.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane8.setBorder(null);

        tblUserContracts.setAutoCreateRowSorter(true);
        tblUserContracts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblUserContracts.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblUserContracts.setForeground(new java.awt.Color(51, 51, 51));
        tblUserContracts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref#", "Role", "ContractFrom", "ContractTo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUserContracts.setGridColor(new java.awt.Color(204, 204, 204));
        tblUserContracts.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblUserContracts.setRowHeight(40);
        tblUserContracts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserContractsMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblUserContracts);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Role:");

        jcbRole.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Contract From:");

        jdpContractFrom.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Contract To:");

        jdpContractTo.setBackground(new java.awt.Color(255, 255, 255));

        btnAddContract.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddContract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/add.png"))); // NOI18N
        btnAddContract.setText("Save");
        btnAddContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContractActionPerformed(evt);
            }
        });

        btnRemoveContract.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemoveContract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.gif"))); // NOI18N
        btnRemoveContract.setText("Delete");
        btnRemoveContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveContractActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdpContractFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdpContractTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jcbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddContract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveContract, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdpContractFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdpContractTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddContract, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveContract)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddContract, btnRemoveContract});

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStaffNames, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStaffNames, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add Contract", jPanel4);

        javax.swing.GroupLayout jpnFindStaffLayout = new javax.swing.GroupLayout(jpnFindStaff);
        jpnFindStaff.setLayout(jpnFindStaffLayout);
        jpnFindStaffLayout.setHorizontalGroup(
            jpnFindStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnFindStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jpnFindStaffLayout.setVerticalGroup(
            jpnFindStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFindStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jpnContainer.add(jpnFindStaff, "card2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Lecture:");

        jScrollPane6.setBorder(null);

        tblLectureModules.setAutoCreateRowSorter(true);
        tblLectureModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblLectureModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblLectureModules.setForeground(new java.awt.Color(51, 51, 51));
        tblLectureModules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref#", "Campus", "Term", "ModuleCode", "ModuleName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLectureModules.setGridColor(new java.awt.Color(204, 204, 204));
        tblLectureModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblLectureModules.setRowHeight(40);
        tblLectureModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLectureModulesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblLectureModules);
        if (tblLectureModules.getColumnModel().getColumnCount() > 0) {
            tblLectureModules.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblLectureModules.getColumnModel().getColumn(0).setMaxWidth(100);
            tblLectureModules.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblLectureModules.getColumnModel().getColumn(1).setMaxWidth(100);
            tblLectureModules.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblLectureModules.getColumnModel().getColumn(2).setMaxWidth(200);
            tblLectureModules.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblLectureModules.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.gif"))); // NOI18N
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                    .addComponent(jSeparator5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(12, 12, 12))
        );

        jTabbedPane2.addTab("Lecture Modules", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Module Code:");

        txtModuleCode.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtModuleCode.setEnabled(false);

        lblFindModule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/search.png"))); // NOI18N
        lblFindModule.setToolTipText("Search module");
        lblFindModule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFindModuleMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Module:");

        lblModule.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblModule.setForeground(new java.awt.Color(102, 102, 102));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Term:");

        jcbTerm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnAddLectureModule.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAddLectureModule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/add.png"))); // NOI18N
        btnAddLectureModule.setText("Save");
        btnAddLectureModule.setToolTipText("Add lecture module");
        btnAddLectureModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLectureModuleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnAddModuleLayout = new javax.swing.GroupLayout(jpnAddModule);
        jpnAddModule.setLayout(jpnAddModuleLayout);
        jpnAddModuleLayout.setHorizontalGroup(
            jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnAddModuleLayout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addGap(349, 349, 349))
                    .addGroup(jpnAddModuleLayout.createSequentialGroup()
                        .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModule, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAddLectureModule, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jcbTerm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnAddModuleLayout.createSequentialGroup()
                                        .addComponent(txtModuleCode, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFindModule)))))
                        .addContainerGap(132, Short.MAX_VALUE))))
        );
        jpnAddModuleLayout.setVerticalGroup(
            jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAddModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtModuleCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFindModule, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblModule, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnAddModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(24, 24, 24)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddLectureModule, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        jPanel3.add(jpnAddModule, "card2");

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Department:");

        jcbDepartment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbDepartment.setForeground(new java.awt.Color(51, 51, 51));
        jcbDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDepartmentActionPerformed(evt);
            }
        });

        jScrollPane7.setBorder(null);

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
        jScrollPane7.setViewportView(jtblCourseModules);
        if (jtblCourseModules.getColumnModel().getColumnCount() > 0) {
            jtblCourseModules.getColumnModel().getColumn(0).setPreferredWidth(150);
            jtblCourseModules.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        javax.swing.GroupLayout jpnFindModuleLayout = new javax.swing.GroupLayout(jpnFindModule);
        jpnFindModule.setLayout(jpnFindModuleLayout);
        jpnFindModuleLayout.setHorizontalGroup(
            jpnFindModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFindModuleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnFindModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jpnFindModuleLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnFindModuleLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnFindModuleLayout.setVerticalGroup(
            jpnFindModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnFindModuleLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jpnFindModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnFindModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.add(jpnFindModule, "card3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Add Lecture Module", jPanel1);

        lblSelectedSatff.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSelectedSatff.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jpnStaffLayout = new javax.swing.GroupLayout(jpnStaff);
        jpnStaff.setLayout(jpnStaffLayout);
        jpnStaffLayout.setHorizontalGroup(
            jpnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jpnStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSelectedSatff, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnStaffLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jpnStaffLayout.setVerticalGroup(
            jpnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblSelectedSatff, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jpnContainer.add(jpnStaff, "card3");

        javax.swing.GroupLayout jpnEducationHistoryLayout = new javax.swing.GroupLayout(jpnEducationHistory);
        jpnEducationHistory.setLayout(jpnEducationHistoryLayout);
        jpnEducationHistoryLayout.setHorizontalGroup(
            jpnEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );
        jpnEducationHistoryLayout.setVerticalGroup(
            jpnEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnEducationHistory, "card4");

        javax.swing.GroupLayout jpnRegisteredCoursesLayout = new javax.swing.GroupLayout(jpnRegisteredCourses);
        jpnRegisteredCourses.setLayout(jpnRegisteredCoursesLayout);
        jpnRegisteredCoursesLayout.setHorizontalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );
        jpnRegisteredCoursesLayout.setVerticalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnRegisteredCourses, "card5");

        javax.swing.GroupLayout jpnStudentFilesLayout = new javax.swing.GroupLayout(jpnStudentFiles);
        jpnStudentFiles.setLayout(jpnStudentFilesLayout);
        jpnStudentFilesLayout.setHorizontalGroup(
            jpnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );
        jpnStudentFilesLayout.setVerticalGroup(
            jpnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnStudentFiles, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStaffHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaffHomeMouseClicked
        // TODO add your handling code here:
        btnFindStaff();
    }//GEN-LAST:event_btnStaffHomeMouseClicked

    private void btnAdminProfileRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminProfileRegistrationMouseClicked
        // TODO add your handling code here:
        btnPersonnel();
    }//GEN-LAST:event_btnAdminProfileRegistrationMouseClicked

    private void btnAdminCourseRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminCourseRegistrationMouseClicked
        // TODO add your handling code here:
        btnAdminCourseRegistrationPressed();
    }//GEN-LAST:event_btnAdminCourseRegistrationMouseClicked

    private void btnAdminEducationHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminEducationHistoryMouseClicked
        // TODO add your handling code here:
        btnAdminEducationHistoryPressed();
    }//GEN-LAST:event_btnAdminEducationHistoryMouseClicked

    private void btnStudentFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentFilesMouseClicked
        // TODO add your handling code here:
        btnStudentFilesPressed();
    }//GEN-LAST:event_btnStudentFilesMouseClicked

    private void jcbMSCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSCampusActionPerformed
        // TODO add your handling code here:
        if(jcbMSCampusesFilled){
            Object objCampus = (Object)jcbMSCampus.getSelectedItem();
            selectedCampus = (Campus)objCampus;
            fillStaffList(selectedCampus.getCampusCode());
        }
    }//GEN-LAST:event_jcbMSCampusActionPerformed

    private void tblStaffListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffListMouseClicked
        // TODO add your handling code here:
        int row = tblStaffList.getSelectedRow();
        staffNo = Integer.parseInt((tblStaffList.getModel().getValueAt(row, 0).toString()));
        txtStaffNo.setText(staffNo+"");
        String lastName = tblStaffList.getModel().getValueAt(row, 1).toString();
        String firstName = tblStaffList.getModel().getValueAt(row, 2).toString();
        String otherName = tblStaffList.getModel().getValueAt(row, 3).toString();
        lblSelectedSatff.setText(staffNo+": "+lastName+" "+firstName+" "+otherName);
        fillLectureModules(staffNo);
        fillUserContracts(staffNo);
        btnPersonnel();
    }//GEN-LAST:event_tblStaffListMouseClicked

    private void tblLectureModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLectureModulesMouseClicked
        // TODO add your handling code here:
        int row = tblLectureModules.getSelectedRow();
        lmId = Integer.parseInt(tblLectureModules.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_tblLectureModulesMouseClicked

    private void jtblDepartmentCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblDepartmentCoursesMouseClicked
        // TODO add your handling code here:
        int row = jtblDepartmentCourses.getSelectedRow();
        String courseCode = (jtblDepartmentCourses.getModel().getValueAt(row, 0).toString());
        String course = (jtblDepartmentCourses.getModel().getValueAt(row, 1).toString());

        selectedCourseCode = courseCode;
        selectedCourse = course;

        fillCourseModules(courseCode);
    }//GEN-LAST:event_jtblDepartmentCoursesMouseClicked

    private void jcbDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDepartmentActionPerformed
        // TODO add your handling code here:
        if(jcbDepartmentClicked){
            String department = jcbDepartment.getSelectedItem().toString();
            fillDepartmentCourse(department);
            tblModelCourseModules.setRowCount(0);
            selectedCourseCode = null;
            selectedCourse = null;
        }
    }//GEN-LAST:event_jcbDepartmentActionPerformed

    private void jtblCourseModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblCourseModulesMouseClicked
        // TODO add your handling code here:
        int row = jtblCourseModules.getSelectedRow();
        selectedModuleCode = (jtblCourseModules.getModel().getValueAt(row, 0).toString().replace(" ", ""));
        selectedModule = (jtblCourseModules.getModel().getValueAt(row, 1).toString());
        txtModuleCode.setText(selectedModuleCode);
        lblModule.setText(selectedModule);
        jpnAddModule.setVisible(true);
        jpnFindModule.setVisible(false);

    }//GEN-LAST:event_jtblCourseModulesMouseClicked

    private void lblFindModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFindModuleMouseClicked
        // TODO add your handling code here:
        jpnAddModule.setVisible(false);
        jpnFindModule.setVisible(true);
    }//GEN-LAST:event_lblFindModuleMouseClicked

    private void btnAddLectureModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLectureModuleActionPerformed
        // TODO add your handling code here:
        Object objTerm = (Object)jcbTerm.getSelectedItem();
        Term term = (Term)objTerm;
        
        if(staffNo > 0){
            Lecture lecture = new Lecture();
            lecture.setLecture(-1, staffNo, selectedCampus.getCampusCode(), term.getTermId(), selectedModuleCode, "", "", "");
            int added = lectureBoImpl.AddLectureModule(lecture);

            if(added > 0){
                fillLectureModules(staffNo);
                Functions.successMessage("New module added for the selected Lecture.");
            }else{
                Functions.errorMessage("Module not added..");
            }
        }else{
            Functions.warningMessage("Please select a lecture first. \n"
                    + "NB: A lecture must first have a contract."
                    + "Select a lecture from Find Staff Panel.");
        }
    }//GEN-LAST:event_btnAddLectureModuleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(lmId > 0){
            int deleted = lectureBoImpl.RemoveLectureModule(lmId);
            if(deleted > 0){
                fillLectureModules(staffNo);
                Functions.successMessage("Transaction successful.");
            }else{
                Functions.errorMessage("Transaction not successful..");
            }
        }else{
            Functions.errorMessage("Select a record before delete operation..");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblUserContractsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserContractsMouseClicked
        // TODO add your handling code here:
        int row = tblUserContracts.getSelectedRow();
        contractId = Integer.parseInt(tblUserContracts.getModel().getValueAt(row, 0).toString().replace(" ", ""));
    }//GEN-LAST:event_tblUserContractsMouseClicked

    private void btnAddContractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContractActionPerformed
        // TODO add your handling code here:
       try{
        Object objRole = (Object)jcbRole.getSelectedItem();
        Role role = (Role)objRole;
        
        Object objCampus = (Object)jcbMSCampus.getSelectedItem();
        selectedCampus = (Campus)objCampus;
        
        int staffUser = Integer.parseInt(txtStaffNo.getText());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String contractFrom = formater.format(jdpContractFrom.getDate());
        String contractTo = formater.format(jdpContractTo.getDate());
        
        Staff staff = new Staff();
        staff.setStaff(-1, staffUser, role.getRoleId(), selectedCampus.getCampusCode(), contractFrom, contractTo, "", loggedInUser.getPersonId(), "", "", "", "", "");
        
        int staffAdded = staffBoImpl.AddStaff(staff);
        if(staffAdded > 0){
            fillUserContracts(staffUser);
            Functions.successMessage("Contract added");
        }else{
            Functions.errorMessage("Transaction not successful..");
        }
       }catch(NumberFormatException ex){
           Functions.errorMessage(ex.getLocalizedMessage());
       }
    }//GEN-LAST:event_btnAddContractActionPerformed

    private void btnRemoveContractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveContractActionPerformed
        // TODO add your handling code here:
        if(contractId > 0){
            int removed = staffBoImpl.RemoveStaff(contractId);
            if(removed > 0){
                int user = Integer.parseInt(txtStaffNo.getText());
                fillUserContracts(user);
                Functions.successMessage("Transaction successful.");
            }else{
                Functions.errorMessage("Transaction not successful.");
            }
        }
    }//GEN-LAST:event_btnRemoveContractActionPerformed

    private void txtStaffNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStaffNoKeyReleased
        // TODO add your handling code here:
        if(txtStaffNo.getText().length() >= 6){
            try{
                int staffUser = Integer.parseInt(txtStaffNo.getText());
                Person p = personBoImpl.GetPerson(staffUser);
                if(p!=null)
                    lblStaffNames.setText(p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
                fillUserContracts(staffUser);
            }catch(NumberFormatException | SQLException ex){
                Functions.errorMessage(ex.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_txtStaffNoKeyReleased

    private void jcbMSRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSRoleActionPerformed
        // TODO add your handling code here:
        if(jcbRoleClicked){
            Object objCampus = (Object)jcbMSCampus.getSelectedItem();
            selectedCampus = (Campus)objCampus;
            
            Object objRole = (Object)jcbMSRole.getSelectedItem();
            selectedRole = (Role)objRole;
            
            fillStaffList(selectedCampus.getCampusCode(),selectedRole.getRoleId());
        }
    }//GEN-LAST:event_jcbMSRoleActionPerformed

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
                new StaffForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContract;
    private javax.swing.JButton btnAddLectureModule;
    private javax.swing.JPanel btnAdminCourseRegistration;
    private javax.swing.JPanel btnAdminEducationHistory;
    private javax.swing.JPanel btnAdminProfileRegistration;
    private javax.swing.JButton btnRemoveContract;
    private javax.swing.JPanel btnStaffHome;
    private javax.swing.JPanel btnStudentFiles;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox<String> jcbDepartment;
    private javax.swing.JComboBox<String> jcbMSCampus;
    private javax.swing.JComboBox<String> jcbMSRole;
    private javax.swing.JComboBox<String> jcbRole;
    private javax.swing.JComboBox<String> jcbTerm;
    private org.jdesktop.swingx.JXDatePicker jdpContractFrom;
    private org.jdesktop.swingx.JXDatePicker jdpContractTo;
    private javax.swing.JPanel jpnAddModule;
    private javax.swing.JPanel jpnContainer;
    private javax.swing.JPanel jpnControls;
    private javax.swing.JPanel jpnEducationHistory;
    private javax.swing.JPanel jpnFindModule;
    private javax.swing.JPanel jpnFindStaff;
    private javax.swing.JPanel jpnRegisteredCourses;
    private javax.swing.JPanel jpnSideMenu;
    private javax.swing.JPanel jpnStaff;
    private javax.swing.JPanel jpnStudentFiles;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JTable jtblCourseModules;
    private javax.swing.JTable jtblDepartmentCourses;
    private javax.swing.JLabel lblFindModule;
    private javax.swing.JLabel lblHomeLoggedIn;
    private javax.swing.JLabel lblModule;
    private javax.swing.JLabel lblSelectedSatff;
    private javax.swing.JLabel lblStaffNames;
    private javax.swing.JTable tblLectureModules;
    private javax.swing.JTable tblStaffList;
    private javax.swing.JTable tblUserContracts;
    private javax.swing.JTextField txtModuleCode;
    private javax.swing.JTextField txtStaffNo;
    // End of variables declaration//GEN-END:variables
}
