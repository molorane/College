/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import com.nanoware.bll.Functions;
import com.nanoware.bll.impl.CampusBoImpl;
import com.nanoware.bll.impl.CountryBoImpl;
import com.nanoware.bll.impl.CourseBoImpl;
import com.nanoware.bll.impl.EducationBoImpl;
import com.nanoware.bll.impl.EmploymentBoImpl;
import com.nanoware.bll.impl.EnrolmentBoImpl;
import com.nanoware.bll.impl.GenderBoImpl;
import com.nanoware.bll.impl.GurdianBoImpl;
import com.nanoware.bll.impl.InstitutionBoImpl;
import com.nanoware.bll.impl.LanguageBoImpl;
import com.nanoware.bll.impl.ModuleBoImpl;
import com.nanoware.bll.impl.PersonBoImpl;
import com.nanoware.bll.impl.PositionBoImpl;
import com.nanoware.bll.impl.QualificationBoImpl;
import com.nanoware.bll.impl.RaceBoImpl;
import com.nanoware.bll.impl.RelationshipBoImpl;
import com.nanoware.bll.impl.ReligionBoImpl;
import com.nanoware.bll.impl.StudyTypeBoImpl;
import com.nanoware.bll.impl.TermBoImpl;
import com.nanoware.bll.impl.TitleBoImpl;
import com.nanoware.jasperservice.JasperEnrolments;
import com.nanoware.model.Campus;
import com.nanoware.model.Course;
import com.nanoware.model.Module;
import com.nanoware.model.Person;
import com.nanoware.model.StudyType;
import com.nanoware.model.Term;
import com.nanoware.model.User;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class Enrolment extends javax.swing.JFrame {
    
    private final PersonBoImpl personImpl = new PersonBoImpl();
    private final RaceBoImpl raceImpl = new RaceBoImpl();
    private final GenderBoImpl genderImpl = new GenderBoImpl();
    private final CountryBoImpl nationalityImpl = new CountryBoImpl();
    private final TitleBoImpl titleImpl = new TitleBoImpl();
    private final LanguageBoImpl languageImpl = new LanguageBoImpl();
    private final ReligionBoImpl religionImpl = new ReligionBoImpl();
    private final RelationshipBoImpl relationshipImpl = new RelationshipBoImpl();
    private final GurdianBoImpl gurdianImpl = new GurdianBoImpl();
    private final EmploymentBoImpl employmentImpl = new EmploymentBoImpl();
    private final PositionBoImpl positionImpl = new PositionBoImpl();
    private final QualificationBoImpl qualificationImpl = new QualificationBoImpl();
    private final InstitutionBoImpl institutionImpl = new InstitutionBoImpl();
    private final EducationBoImpl educationImpl = new EducationBoImpl();
    private final CourseBoImpl courseImpl = new CourseBoImpl();
    private final StudyTypeBoImpl studyTypeImpl = new StudyTypeBoImpl();
    private final ModuleBoImpl moduleImpl = new ModuleBoImpl();
    private final EnrolmentBoImpl enrolmentImpl = new EnrolmentBoImpl();
    private final TermBoImpl termImpl = new TermBoImpl();
    private final CampusBoImpl campusImpl = new CampusBoImpl();
    private final JasperEnrolments jasperEnolment = new JasperEnrolments();
    
    private boolean jcbDepartmentClicked = false;
    
    private DefaultTableModel tblModelDepartmentCouses;
    private DefaultTableModel tblModelCourseModules;
    private DefaultTableModel tblModelSelectedModules;
    
    private DefaultTableModel tblModelSearchList;
    private DefaultTableModel tblModelPersonEnrolments;
    private DefaultTableModel tblModelEnrolledCourses;
    private DefaultTableModel tblModelEnrolledCourseModules;
    
    private String selectedCourseCode;
    private String selectedCourse;
    private String selectedTerm;
    
    private long personId;
    private final User loggedInUser;
    
    private final String beforeNavigation = "Before navigating you must first specify the student by typing the student no\n"
                    + "or by searching the student then selecting the desired student on the search list.";


    /**
     * Creates new form Enrol
     */
    public Enrolment() {
        super("Enrolments - "+Functions.appName());
        initComponents();
        
        Functions.setAppIcon(Enrolment.this);
        
        loggedInUser = Functions.GetUser(); //LoginSession.getSessionUser();
        
        courseImpl.fillComboBoxDepartment(jcbDepartment);
        studyTypeImpl.fillComboBoxStudyType(jcbStudyType);
        campusImpl.fillComboBoxCampuses(jcbCampus);        
        jcbDepartmentClicked = true;
        
        // set table models
        tblModelDepartmentCouses = (DefaultTableModel)jtblDepartmentCourses.getModel();        
        tblModelPersonEnrolments = (DefaultTableModel)tblPersonEnrolments.getModel();        
        tblModelEnrolledCourses = (DefaultTableModel)jtblEnrolledCourses.getModel();       
        tblModelEnrolledCourseModules = (DefaultTableModel)jtblEnrolledCourseModules.getModel();       
        tblModelSearchList = (DefaultTableModel)tblSearchList.getModel();
        tblModelCourseModules = (DefaultTableModel)jtblCourseModules.getModel();
        tblModelSelectedModules = (DefaultTableModel)jtblSelectedModules.getModel();  
        
        // set single selection mode for tables
        jtblEnrolledCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblDepartmentCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSearchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Functions.setLoggedInUser(lblHomeLoggedIn,loggedInUser);
        
        setLocationRelativeTo(null);
    }
    
     void btnAdminHomePressed(){
        setColor(btnAdminHome);
        resetColor(btnPreviousEnrolemnts);
        resetColor(btnEnrolStudent);
        resetColor(btnPrintEnrolment);
        
        jpnHome.setVisible(true);
        jpnEnrolments.setVisible(false);
        jpnEnrolStudent.setVisible(false);
        jpnPrintService.setVisible(false);
    }
    
    void profileRegistrationPressed(){
       resetColor(btnAdminHome);
       setColor(btnPreviousEnrolemnts);
       resetColor(btnEnrolStudent);
       resetColor(btnPrintEnrolment);
       
       jpnHome.setVisible(false);
       jpnEnrolments.setVisible(true);
       jpnEnrolStudent.setVisible(false);
       jpnPrintService.setVisible(false);
    }
    
    void btnAdminEducationHistoryPressed(){
       resetColor(btnAdminHome);
       resetColor(btnPreviousEnrolemnts);
       setColor(btnEnrolStudent);
       resetColor(btnPrintEnrolment);
       
       jpnHome.setVisible(false);
       jpnEnrolments.setVisible(false);
       jpnEnrolStudent.setVisible(true);
       jpnPrintService.setVisible(false);
    }
    
    void btnAdminCourseRegistrationPressed(){
       resetColor(btnAdminHome);
       resetColor(btnPreviousEnrolemnts);
       resetColor(btnEnrolStudent);
       setColor(btnPrintEnrolment);
       
       jpnHome.setVisible(false);
       jpnEnrolments.setVisible(false);
       jpnEnrolStudent.setVisible(false);
       jpnPrintService.setVisible(true);
    }
    
    void setColor(JPanel panel){
        panel.setBackground(new Color(64,43,100));
    }
    
    void resetColor(JPanel panel){
       panel.setBackground(new Color(54,33,89)); 
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
    
    private void fillPersonEnrolments(long personId){
        ArrayList<com.nanoware.model.Enrolment> personEnrolments = enrolmentImpl.GetPersonEnrolments(personId);
        tblModelPersonEnrolments.setRowCount(0);
        personEnrolments.forEach((enrolment) -> {
            tblModelPersonEnrolments.addRow(new Object[]{
                enrolment.getCourseCode(),
                enrolment.getCourse(),
                enrolment.getTerm(),
                enrolment.getStudytype(),
                enrolment.getModuleCode(),
                enrolment.getModule()
            });
        });
    }
    
    private void fillSearchStudents(String search){        
        try{
            ArrayList<Person> searchList = personImpl.searchPersons(search);
            tblModelSearchList.setRowCount(0);
            searchList.forEach((person) -> {
                tblModelSearchList.addRow(new Object[]{
                   person.getPersonId(),
                    person.getIdno(),
                    person.getLastName(),
                    person.getFirstName(),
                    person.getOtherName(),
                    person.getGender(),
                    person.getDob(),
                    person.getNationality()
                });
            });  
        }catch(SQLException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
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
    
    public void createStudentSession(){         
        try {
            Person p = personImpl.GetPerson(personId);

            if(p!=null){
                personId = p.getPersonId();
                txtStudentNoSession.setText(p.getPersonId()+"");
                lblUserSession.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
                tblModelEnrolledCourses.setRowCount(0);
                tblModelEnrolledCourseModules.setRowCount(0);
                StudentEnrolledCourses(enrolmentImpl.GetStudentCourseEnrolments(personId));
            }else{
                personId = 0;
                lblUserSession.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admission.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel21 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jpnEnrolment = new javax.swing.JPanel();
        jpnSideMenu = new javax.swing.JPanel();
        btnAdminHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnPreviousEnrolemnts = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnPrintEnrolment = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnEnrolStudent = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jpnTop = new javax.swing.JPanel();
        lblHomeLoggedIn = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblUserSession = new javax.swing.JLabel();
        jpnContainer = new javax.swing.JPanel();
        jpnHome = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSearchList = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtStudentNoSession = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        txtSearchStudentSession = new javax.swing.JTextField();
        jpnEnrolments = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtblEnrolledCourses = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtblEnrolledCourseModules = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnDeregisterModules = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnProofOfReg = new javax.swing.JButton();
        jpnEnrolStudent = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jcbDepartment = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbTerm = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbStudyType = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcbCampus = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtblCourseModules = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtblSelectedModules = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblDepartmentCourses = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnEnrol = new javax.swing.JButton();
        jpnPrintService = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPersonEnrolments = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();

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

        btnAdminHome.setBackground(new java.awt.Color(64, 43, 100));
        btnAdminHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminHomeMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/home.png"))); // NOI18N

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
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminHomeLayout.setVerticalGroup(
            btnAdminHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnPreviousEnrolemnts.setBackground(new java.awt.Color(54, 33, 89));
        btnPreviousEnrolemnts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreviousEnrolemnts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviousEnrolemntsMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/courses.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Enrolments");

        javax.swing.GroupLayout btnPreviousEnrolemntsLayout = new javax.swing.GroupLayout(btnPreviousEnrolemnts);
        btnPreviousEnrolemnts.setLayout(btnPreviousEnrolemntsLayout);
        btnPreviousEnrolemntsLayout.setHorizontalGroup(
            btnPreviousEnrolemntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPreviousEnrolemntsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnPreviousEnrolemntsLayout.setVerticalGroup(
            btnPreviousEnrolemntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPreviousEnrolemntsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnPreviousEnrolemntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnPrintEnrolment.setBackground(new java.awt.Color(54, 33, 89));
        btnPrintEnrolment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintEnrolment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintEnrolmentMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Print Service");

        javax.swing.GroupLayout btnPrintEnrolmentLayout = new javax.swing.GroupLayout(btnPrintEnrolment);
        btnPrintEnrolment.setLayout(btnPrintEnrolmentLayout);
        btnPrintEnrolmentLayout.setHorizontalGroup(
            btnPrintEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPrintEnrolmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnPrintEnrolmentLayout.setVerticalGroup(
            btnPrintEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPrintEnrolmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnPrintEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("ENROLMENT");

        btnEnrolStudent.setBackground(new java.awt.Color(54, 33, 89));
        btnEnrolStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnrolStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnrolStudentMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/profile_add.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Enrol Student");

        javax.swing.GroupLayout btnEnrolStudentLayout = new javax.swing.GroupLayout(btnEnrolStudent);
        btnEnrolStudent.setLayout(btnEnrolStudentLayout);
        btnEnrolStudentLayout.setHorizontalGroup(
            btnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnEnrolStudentLayout.setVerticalGroup(
            btnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
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
                    .addComponent(btnPrintEnrolment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnrolStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPreviousEnrolemnts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdminHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPreviousEnrolemnts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnrolStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrintEnrolment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnTop.setBackground(Functions.pnlBackgroundTop());
        jpnTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHomeLoggedIn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblHomeLoggedIn.setForeground(new java.awt.Color(204, 204, 204));
        lblHomeLoggedIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHomeLoggedIn.setText("ADMIN: Mohlomi M");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Student:");

        lblUserSession.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUserSession.setForeground(new java.awt.Color(204, 204, 204));
        lblUserSession.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jpnTopLayout = new javax.swing.GroupLayout(jpnTop);
        jpnTop.setLayout(jpnTopLayout);
        jpnTopLayout.setHorizontalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnTopLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnTopLayout.setVerticalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTopLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblUserSession, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpnContainer.setLayout(new java.awt.CardLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane5.setBorder(null);

        tblSearchList.setAutoCreateRowSorter(true);
        tblSearchList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblSearchList.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblSearchList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT NO", "ID NO", "LAST NAME", "FIRST NAME", "OTHER NAME", "GENDER", "DOB", "NATIONALITY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSearchList.setGridColor(new java.awt.Color(204, 204, 204));
        tblSearchList.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblSearchList.setRowHeight(40);
        tblSearchList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSearchList);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Student No:");

        txtStudentNoSession.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStudentNoSession.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStudentNoSessionKeyReleased(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setText("Search Student:");

        txtSearchStudentSession.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearchStudentSession.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchStudentSessionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(42, 42, 42)
                        .addComponent(txtStudentNoSession, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchStudentSession, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStudentNoSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtSearchStudentSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnHomeLayout = new javax.swing.GroupLayout(jpnHome);
        jpnHome.setLayout(jpnHomeLayout);
        jpnHomeLayout.setHorizontalGroup(
            jpnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnHomeLayout.setVerticalGroup(
            jpnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnHome, "card2");

        jScrollPane8.setBorder(null);

        jtblEnrolledCourses.setAutoCreateRowSorter(true);
        jtblEnrolledCourses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblEnrolledCourses.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblEnrolledCourses.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblEnrolledCourses.setGridColor(new java.awt.Color(204, 204, 204));
        jtblEnrolledCourses.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblEnrolledCourses.setRowHeight(40);
        jtblEnrolledCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblEnrolledCoursesMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jtblEnrolledCourses);
        if (jtblEnrolledCourses.getColumnModel().getColumnCount() > 0) {
            jtblEnrolledCourses.getColumnModel().getColumn(0).setPreferredWidth(130);
            jtblEnrolledCourses.getColumnModel().getColumn(0).setMaxWidth(130);
            jtblEnrolledCourses.getColumnModel().getColumn(3).setPreferredWidth(90);
            jtblEnrolledCourses.getColumnModel().getColumn(3).setMaxWidth(90);
        }

        jScrollPane9.setBorder(null);

        jtblEnrolledCourseModules.setAutoCreateRowSorter(true);
        jtblEnrolledCourseModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblEnrolledCourseModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblEnrolledCourseModules.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblEnrolledCourseModules.setGridColor(new java.awt.Color(204, 204, 204));
        jtblEnrolledCourseModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblEnrolledCourseModules.setRowHeight(40);
        jtblEnrolledCourseModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblEnrolledCourseModulesMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jtblEnrolledCourseModules);
        if (jtblEnrolledCourseModules.getColumnModel().getColumnCount() > 0) {
            jtblEnrolledCourseModules.getColumnModel().getColumn(0).setPreferredWidth(150);
            jtblEnrolledCourseModules.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnDeregisterModules.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnDeregisterModules.setForeground(new java.awt.Color(51, 51, 51));
        btnDeregisterModules.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trash.png"))); // NOI18N
        btnDeregisterModules.setText("Deregister");
        btnDeregisterModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeregisterModulesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeregisterModules)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeregisterModules, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnProofOfReg.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnProofOfReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        btnProofOfReg.setText("Proof of registration");
        btnProofOfReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProofOfRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProofOfReg, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProofOfReg, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnEnrolmentsLayout = new javax.swing.GroupLayout(jpnEnrolments);
        jpnEnrolments.setLayout(jpnEnrolmentsLayout);
        jpnEnrolmentsLayout.setHorizontalGroup(
            jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnEnrolmentsLayout.setVerticalGroup(
            jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnEnrolmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpnContainer.add(jpnEnrolments, "card3");

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Campus:");

        jcbCampus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbCampus.setForeground(new java.awt.Color(51, 51, 51));

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

        jScrollPane7.setBorder(null);

        jtblSelectedModules.setAutoCreateRowSorter(true);
        jtblSelectedModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblSelectedModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblSelectedModules.setModel(new javax.swing.table.DefaultTableModel(
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
        jtblSelectedModules.setGridColor(new java.awt.Color(204, 204, 204));
        jtblSelectedModules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblSelectedModules.setRowHeight(40);
        jtblSelectedModules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblSelectedModulesMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtblSelectedModules);

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

        btnEnrol.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnEnrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/add.png"))); // NOI18N
        btnEnrol.setText("Enrol ");
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
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEnrolStudentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEnrol, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
                    .addComponent(jLabel6)
                    .addComponent(jcbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnEnrolStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnEnrolStudentLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnrol))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        jpnContainer.add(jpnEnrolStudent, "card3");

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

        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        jToggleButton1.setText("Proof of Registration");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        jToggleButton2.setText("Class Time Table");
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jToggleButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        jToggleButton3.setText("Exam Time Table");
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jpnPrintServiceLayout = new javax.swing.GroupLayout(jpnPrintService);
        jpnPrintService.setLayout(jpnPrintServiceLayout);
        jpnPrintServiceLayout.setHorizontalGroup(
            jpnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrintServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPrintServiceLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jpnPrintServiceLayout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 162, Short.MAX_VALUE))))
        );
        jpnPrintServiceLayout.setVerticalGroup(
            jpnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrintServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnContainer.add(jpnPrintService, "card3");

        javax.swing.GroupLayout jpnEnrolmentLayout = new javax.swing.GroupLayout(jpnEnrolment);
        jpnEnrolment.setLayout(jpnEnrolmentLayout);
        jpnEnrolmentLayout.setHorizontalGroup(
            jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEnrolmentLayout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE)
                    .addComponent(jpnTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpnEnrolmentLayout.setVerticalGroup(
            jpnEnrolmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnEnrolmentLayout.createSequentialGroup()
                .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jpnEnrolment, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdminHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminHomeMouseClicked
        // TODO add your handling code here:
        btnAdminHomePressed();
    }//GEN-LAST:event_btnAdminHomeMouseClicked

    private void btnPreviousEnrolemntsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviousEnrolemntsMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            profileRegistrationPressed();
        }
    }//GEN-LAST:event_btnPreviousEnrolemntsMouseClicked

    private void btnPrintEnrolmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintEnrolmentMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnAdminCourseRegistrationPressed();
        }
    }//GEN-LAST:event_btnPrintEnrolmentMouseClicked

    private void btnEnrolStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnrolStudentMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnAdminEducationHistoryPressed();
        }
    }//GEN-LAST:event_btnEnrolStudentMouseClicked

    private void tblSearchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchListMouseClicked
        // TODO add your handling code here:
        int row = tblSearchList.getSelectedRow();
        personId = Long.parseLong((tblSearchList.getModel().getValueAt(row, 0).toString()));
        createStudentSession();
    }//GEN-LAST:event_tblSearchListMouseClicked

    private void txtStudentNoSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentNoSessionKeyReleased
        // TODO add your handling code here:
        if(txtStudentNoSession.getText().length() >= 6){
            personId = Integer.parseInt(txtStudentNoSession.getText());
            createStudentSession();
        }
    }//GEN-LAST:event_txtStudentNoSessionKeyReleased

    private void txtSearchStudentSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentSessionKeyReleased
        // TODO add your handling code here:
        fillSearchStudents(txtSearchStudentSession.getText());
    }//GEN-LAST:event_txtSearchStudentSessionKeyReleased

    private void jtblEnrolledCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblEnrolledCoursesMouseClicked
        // TODO add your handling code here:
        int row = jtblEnrolledCourses.getSelectedRow();
        selectedCourseCode = jtblEnrolledCourses.getModel().getValueAt(row, 0).toString();
        selectedTerm = jtblEnrolledCourses.getModel().getValueAt(row, 2).toString();
        StudentEnrolledCourseModules(enrolmentImpl.GetStudentCourseEnrolmentModules(personId, selectedCourseCode, selectedTerm));
    }//GEN-LAST:event_jtblEnrolledCoursesMouseClicked

    private void jtblEnrolledCourseModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblEnrolledCourseModulesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtblEnrolledCourseModulesMouseClicked

    private void btnDeregisterModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeregisterModulesActionPerformed
        // TODO add your handling code here:
        if(selectedCourseCode!=null && selectedTerm!=null){
            int rows[] = jtblEnrolledCourseModules.getSelectedRows();
            if(rows.length > 0){
                ArrayList<String> selectedModules = new ArrayList<>();
                for(int row:rows){
                    selectedModules.add(jtblEnrolledCourseModules.getModel().getValueAt(row, 0).toString());
                }
                int rowsDeleted =  enrolmentImpl.deregisterModules(personId, selectedCourseCode, selectedModules, selectedTerm);
                if(rowsDeleted == selectedModules.size()){
                    if(selectedModules.size() == tblModelEnrolledCourseModules.getRowCount()){
                        tblModelEnrolledCourseModules.setRowCount(0);
                        StudentEnrolledCourses(enrolmentImpl.GetStudentCourseEnrolments(personId));
                    }
                    for(int row:rows){
                        tblModelEnrolledCourseModules.removeRow(row);
                    }
                    jtblEnrolledCourseModules.clearSelection();
                    Functions.successMessage("Modules successfully deregistered.");
                }
            }else{
                Functions.warningMessage("Before deregistering modules, you must first select at least one module to deregister.");
            }
        }else{
            Functions.warningMessage("Before deregistering modules, you must first select a course.");
        }
    }//GEN-LAST:event_btnDeregisterModulesActionPerformed

    private void btnEnrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrolActionPerformed
        // TODO add your handling code here:

        String course = "Selected courseCode:"+selectedCourseCode+"\n"+
        "Selected course:"+selectedCourse+"\n";

        String modules = "";

        Term term = (Term)jcbTerm.getSelectedItem();
        StudyType studyType = (StudyType)jcbStudyType.getSelectedItem();
        Campus campus = (Campus)jcbCampus.getSelectedItem();

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
            Functions.errorMessage("Before enrolling student, you must first select the modules for the student!!");
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

    private void jtblSelectedModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblSelectedModulesMouseClicked
        // TODO add your handling code here:
        int row = jtblSelectedModules.getSelectedRow();
        String moduleCode = (jtblSelectedModules.getModel().getValueAt(row, 0).toString());
        String module = (jtblSelectedModules.getModel().getValueAt(row, 1).toString());

        tblModelCourseModules.addRow(new Object[]{
            moduleCode,
            module
        });
        tblModelSelectedModules.removeRow(row);

        if(tblModelSelectedModules.getRowCount() == 0){
            jtblDepartmentCourses.setEnabled(true);
            jcbDepartment.setEnabled(true);
        }
    }//GEN-LAST:event_jtblSelectedModulesMouseClicked

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
                + "Either complete registration by clicking enrol button or remove all of the selected modules by clicking on each of them.");
        }
    }//GEN-LAST:event_jtblDepartmentCoursesMouseClicked

    private void btnProofOfRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProofOfRegActionPerformed
        // TODO add your handling code here:
        if(selectedCourseCode!= null){
            jasperEnolment.GetStudentProofOfRegistration(personId, selectedCourseCode, selectedTerm);
        }else{
            Functions.warningMessage("Before printing a proof of registation\n"+
                    "ensure you have selected course registration");
        }
    }//GEN-LAST:event_btnProofOfRegActionPerformed

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
                new Enrolment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAdminHome;
    private javax.swing.JButton btnDeregisterModules;
    private javax.swing.JButton btnEnrol;
    private javax.swing.JPanel btnEnrolStudent;
    private javax.swing.JPanel btnPreviousEnrolemnts;
    private javax.swing.JPanel btnPrintEnrolment;
    private javax.swing.JButton btnProofOfReg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JComboBox<String> jcbCampus;
    private javax.swing.JComboBox<String> jcbDepartment;
    private javax.swing.JComboBox<String> jcbStudyType;
    private javax.swing.JComboBox<String> jcbTerm;
    private javax.swing.JPanel jpnContainer;
    private javax.swing.JPanel jpnEnrolStudent;
    private javax.swing.JPanel jpnEnrolment;
    private javax.swing.JPanel jpnEnrolments;
    private javax.swing.JPanel jpnHome;
    private javax.swing.JPanel jpnPrintService;
    private javax.swing.JPanel jpnSideMenu;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JTable jtblCourseModules;
    private javax.swing.JTable jtblDepartmentCourses;
    private javax.swing.JTable jtblEnrolledCourseModules;
    private javax.swing.JTable jtblEnrolledCourses;
    private javax.swing.JTable jtblSelectedModules;
    private javax.swing.JLabel lblHomeLoggedIn;
    private javax.swing.JLabel lblUserSession;
    private javax.swing.JTable tblPersonEnrolments;
    private javax.swing.JTable tblSearchList;
    private javax.swing.JTextField txtSearchStudentSession;
    private javax.swing.JTextField txtStudentNoSession;
    // End of variables declaration//GEN-END:variables
}
