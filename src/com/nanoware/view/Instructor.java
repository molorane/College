/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import com.nanoware.bll.Functions;
import com.nanoware.bll.impl.CountryBoImpl;
import com.nanoware.bll.impl.CourseBoImpl;
import com.nanoware.bll.impl.EducationBoImpl;
import com.nanoware.bll.impl.EmploymentBoImpl;
import com.nanoware.bll.impl.EnrolmentBoImpl;
import com.nanoware.bll.impl.GenderBoImpl;
import com.nanoware.bll.impl.GurdianBoImpl;
import com.nanoware.bll.impl.InstitutionBoImpl;
import com.nanoware.bll.impl.LanguageBoImpl;
import com.nanoware.bll.impl.LectureBoImpl;
import com.nanoware.bll.impl.ModuleBoImpl;
import com.nanoware.bll.impl.PersonBoImpl;
import com.nanoware.bll.impl.PositionBoImpl;
import com.nanoware.bll.impl.QualificationBoImpl;
import com.nanoware.bll.impl.RaceBoImpl;
import com.nanoware.bll.impl.RelationshipBoImpl;
import com.nanoware.bll.impl.ReligionBoImpl;
import com.nanoware.bll.impl.StudyTypeBoImpl;
import com.nanoware.bll.impl.TitleBoImpl;
import com.nanoware.model.Campus;
import com.nanoware.model.Course;
import com.nanoware.model.Education;
import com.nanoware.model.Enrolment;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
import com.nanoware.model.User;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
/**
 *
 * @author Mothusi Molorane
 */
public class Instructor extends javax.swing.JFrame {
    
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
    private final LectureBoImpl lectureBoImpl = new LectureBoImpl();
    
    private DefaultTableModel tblModelEduHistory;
    private int EDUID;
    private int EDUROWSELECTED;
    private boolean jcbDepartmentClicked = false;
    
    private DefaultTableModel tblModelDepartmentCouses;
    private DefaultTableModel tblModelCourseModules;
    private DefaultTableModel tblModelStudentsInModule;
    private DefaultTableModel tblModelPersonEnrolments;
    
    private String selectedCourseCode;
    private String selectedCourse;
    
    
    private long personId;
    private final User loggedInUser;
    
    private boolean jcbMSCampusesFilled = false;
    private boolean jcbMSTermsFilled = false;
    private boolean jcbMSModulesFilled = false;

    /**
     * Creates new form Instructor
     */
    public Instructor() {
        super("Lecture - "+Functions.appName());
        initComponents();
      
        loggedInUser = Functions.GetUser("blessy"); //LoginSession.getSessionUser();
        
        lectureBoImpl.fillComboBoxLectureCampuses(jcbMSCampuses, loggedInUser.getPersonId());
        jcbMSCampusesFilled = true;
        
        EDUID = 0;
        EDUROWSELECTED = 0;
                
        tblModelStudentsInModule = (DefaultTableModel)tblStudentsInModule.getModel();
        
        Functions.setLoggedInUser(lblHomeLoggedIn,loggedInUser);
    }
    
     void btnAdminHomePressed(){
        setColor(btnAdminHome);
        resetColor(btnAdminProfileRegistration);
        resetColor(btnAdminEducationHistory);
        resetColor(btnAdminCourseRegistration);
        resetColor(btnStudentFiles);
        
        jpnMyStudents.setVisible(true);
        jpnUploadMarks.setVisible(false);
        jpnEducationHistory.setVisible(false);
        jpnRegisteredCourses.setVisible(false);
        jpnStudentFiles.setVisible(false);
    }
    
    void profileRegistrationPressed(){
       resetColor(btnAdminHome);
       setColor(btnAdminProfileRegistration);
       resetColor(btnAdminEducationHistory);
       resetColor(btnAdminCourseRegistration);
       resetColor(btnStudentFiles);
       
       jpnMyStudents.setVisible(false);
       jpnUploadMarks.setVisible(true);
       jpnEducationHistory.setVisible(false);
       jpnRegisteredCourses.setVisible(false);
       jpnStudentFiles.setVisible(false);
    }
    
    void btnAdminEducationHistoryPressed(){
       resetColor(btnAdminHome);
       resetColor(btnAdminProfileRegistration);
       setColor(btnAdminEducationHistory);
       resetColor(btnAdminCourseRegistration);
       resetColor(btnStudentFiles);
       
       jpnMyStudents.setVisible(false);
       jpnUploadMarks.setVisible(false);
       jpnEducationHistory.setVisible(true);
       jpnRegisteredCourses.setVisible(false);
       jpnStudentFiles.setVisible(false);
    }
    
    void btnAdminCourseRegistrationPressed(){
       resetColor(btnAdminHome);
       resetColor(btnAdminProfileRegistration);
       resetColor(btnAdminEducationHistory);
       setColor(btnAdminCourseRegistration);
       resetColor(btnStudentFiles);
       
       jpnMyStudents.setVisible(false);
       jpnUploadMarks.setVisible(false);
       jpnEducationHistory.setVisible(false);
       jpnRegisteredCourses.setVisible(true);
       jpnStudentFiles.setVisible(false);
    }
    
    void btnStudentFilesPressed(){
       resetColor(btnAdminHome);
       resetColor(btnAdminProfileRegistration);
       resetColor(btnAdminEducationHistory);
       resetColor(btnAdminCourseRegistration);
       setColor(btnStudentFiles);
       
       jpnMyStudents.setVisible(false);
       jpnUploadMarks.setVisible(false);
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
    
    private void fillEduHistory(long personId){
        ArrayList<Education> Eduhistory = educationImpl.GetPersonEducation(personId);
        tblModelEduHistory.setRowCount(0);
        Eduhistory.forEach((bean) -> {
            tblModelEduHistory.addRow(new Object[]{
               bean.getEduId(), 
               bean.getInstitution(),
               bean.getQualification(),
               bean.getYearObtained(),
               bean.getSkills(),
               bean.getDetails()
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
    
    private void fillPersonEnrolments(long personId){
        ArrayList<Enrolment> personEnrolments = enrolmentImpl.GetPersonEnrolments(personId);
        tblModelPersonEnrolments.setRowCount(0);
        personEnrolments.forEach((enrolment) -> {
            Course c = courseImpl.GetCourse(enrolment.getCourseCode());
            tblModelPersonEnrolments.addRow(new Object[]{
                enrolment.getCourseCode(),
                enrolment.getCourse()+" "+c.getLevel(),
                enrolment.getTerm(),
                enrolment.getStudytype(),
                enrolment.getCampus(),
                enrolment.getModuleCode(),
                enrolment.getModule()
            });
        });
    }
    
    public void StudentInModule(List<HashMap<String, Object>> students){
        tblModelStudentsInModule.setRowCount(0);
        students.forEach((row) -> {
            tblModelStudentsInModule.addRow(new Object[]{
                row.get("personId"),
                row.get("idno"),
                row.get("passport"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("otherName")
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
        btnAdminHome = new javax.swing.JPanel();
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
        jcbMSCampuses = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jcbMSTerms = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        jcbMSModules = new javax.swing.JComboBox<>();
        jpnContainer = new javax.swing.JPanel();
        jpnMyStudents = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStudentsInModule = new javax.swing.JTable();
        jpnUploadMarks = new javax.swing.JPanel();
        jpnEducationHistory = new javax.swing.JPanel();
        jpnRegisteredCourses = new javax.swing.JPanel();
        jpnStudentFiles = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnSideMenu.setBackground(new java.awt.Color(54, 33, 89));

        btnAdminHome.setBackground(new java.awt.Color(64, 43, 100));
        btnAdminHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminHomeMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/users.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("My Students");

        javax.swing.GroupLayout btnAdminHomeLayout = new javax.swing.GroupLayout(btnAdminHome);
        btnAdminHome.setLayout(btnAdminHomeLayout);
        btnAdminHomeLayout.setHorizontalGroup(
            btnAdminHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
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

        btnAdminProfileRegistration.setBackground(new java.awt.Color(54, 33, 89));
        btnAdminProfileRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminProfileRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminProfileRegistrationMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/import.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Upload Marks");

        javax.swing.GroupLayout btnAdminProfileRegistrationLayout = new javax.swing.GroupLayout(btnAdminProfileRegistration);
        btnAdminProfileRegistration.setLayout(btnAdminProfileRegistrationLayout);
        btnAdminProfileRegistrationLayout.setHorizontalGroup(
            btnAdminProfileRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminProfileRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
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

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/courses.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Registered Courses");

        javax.swing.GroupLayout btnAdminCourseRegistrationLayout = new javax.swing.GroupLayout(btnAdminCourseRegistration);
        btnAdminCourseRegistration.setLayout(btnAdminCourseRegistrationLayout);
        btnAdminCourseRegistrationLayout.setHorizontalGroup(
            btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminCourseRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
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
        jLabel12.setText("LECTURE");

        btnAdminEducationHistory.setBackground(new java.awt.Color(54, 33, 89));
        btnAdminEducationHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminEducationHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminEducationHistoryMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graduation.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Education History");

        javax.swing.GroupLayout btnAdminEducationHistoryLayout = new javax.swing.GroupLayout(btnAdminEducationHistory);
        btnAdminEducationHistory.setLayout(btnAdminEducationHistoryLayout);
        btnAdminEducationHistoryLayout.setHorizontalGroup(
            btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminEducationHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
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

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file_explorer.png"))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(204, 204, 204));
        jLabel56.setText("Student files");

        javax.swing.GroupLayout btnStudentFilesLayout = new javax.swing.GroupLayout(btnStudentFiles);
        btnStudentFiles.setLayout(btnStudentFilesLayout);
        btnStudentFilesLayout.setHorizontalGroup(
            btnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
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
                    .addComponent(btnAdminHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(btnAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jpnTop.setBackground(new java.awt.Color(122, 72, 221));

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
        jLabel16.setText("Campus:");

        jcbMSCampuses.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSCampuses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jcbMSCampuses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSCampusesActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setText("Term:");

        jcbMSTerms.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSTerms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jcbMSTerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSTermsActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel61.setText("Module:");

        jcbMSModules.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSModules.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jcbMSModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSModulesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnControlsLayout = new javax.swing.GroupLayout(jpnControls);
        jpnControls.setLayout(jpnControlsLayout);
        jpnControlsLayout.setHorizontalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMSCampuses, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbMSTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel61)
                .addGap(18, 18, 18)
                .addComponent(jcbMSModules, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(105, 105, 105))
        );
        jpnControlsLayout.setVerticalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jcbMSTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(jcbMSCampuses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jcbMSModules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnContainer.setLayout(new java.awt.CardLayout());

        jScrollPane5.setBorder(null);

        tblStudentsInModule.setAutoCreateRowSorter(true);
        tblStudentsInModule.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStudentsInModule.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStudentsInModule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT NO", "ID NO", "PASSPORT", "LAST NAME", "FIRST NAME", "OTHER NAME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudentsInModule.setGridColor(new java.awt.Color(204, 204, 204));
        tblStudentsInModule.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblStudentsInModule.setRowHeight(40);
        tblStudentsInModule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsInModuleMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblStudentsInModule);

        javax.swing.GroupLayout jpnMyStudentsLayout = new javax.swing.GroupLayout(jpnMyStudents);
        jpnMyStudents.setLayout(jpnMyStudentsLayout);
        jpnMyStudentsLayout.setHorizontalGroup(
            jpnMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnMyStudentsLayout.setVerticalGroup(
            jpnMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnContainer.add(jpnMyStudents, "card2");

        javax.swing.GroupLayout jpnUploadMarksLayout = new javax.swing.GroupLayout(jpnUploadMarks);
        jpnUploadMarks.setLayout(jpnUploadMarksLayout);
        jpnUploadMarksLayout.setHorizontalGroup(
            jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1165, Short.MAX_VALUE)
        );
        jpnUploadMarksLayout.setVerticalGroup(
            jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnUploadMarks, "card3");

        javax.swing.GroupLayout jpnEducationHistoryLayout = new javax.swing.GroupLayout(jpnEducationHistory);
        jpnEducationHistory.setLayout(jpnEducationHistoryLayout);
        jpnEducationHistoryLayout.setHorizontalGroup(
            jpnEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1165, Short.MAX_VALUE)
        );
        jpnEducationHistoryLayout.setVerticalGroup(
            jpnEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnEducationHistory, "card4");

        javax.swing.GroupLayout jpnRegisteredCoursesLayout = new javax.swing.GroupLayout(jpnRegisteredCourses);
        jpnRegisteredCourses.setLayout(jpnRegisteredCoursesLayout);
        jpnRegisteredCoursesLayout.setHorizontalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1165, Short.MAX_VALUE)
        );
        jpnRegisteredCoursesLayout.setVerticalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnRegisteredCourses, "card5");

        javax.swing.GroupLayout jpnStudentFilesLayout = new javax.swing.GroupLayout(jpnStudentFiles);
        jpnStudentFiles.setLayout(jpnStudentFilesLayout);
        jpnStudentFilesLayout.setHorizontalGroup(
            jpnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1165, Short.MAX_VALUE)
        );
        jpnStudentFilesLayout.setVerticalGroup(
            jpnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnStudentFiles, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btnAdminHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminHomeMouseClicked
        // TODO add your handling code here:
        btnAdminHomePressed();
    }//GEN-LAST:event_btnAdminHomeMouseClicked

    private void btnAdminProfileRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminProfileRegistrationMouseClicked
        // TODO add your handling code here:
        profileRegistrationPressed();
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

    private void tblStudentsInModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsInModuleMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblStudentsInModuleMouseClicked

    private void jcbMSCampusesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSCampusesActionPerformed
        // TODO add your handling code here:
        if(jcbMSCampusesFilled){
            Object objCampus = (Object)jcbMSCampuses.getSelectedItem();
            Campus campus = (Campus)objCampus;
            jcbMSTermsFilled = false;
            lectureBoImpl.fillComboBoxLectureCampusTerms(jcbMSTerms, loggedInUser.getPersonId(), campus.getCampusCode());
            jcbMSTermsFilled = true;
        }
    }//GEN-LAST:event_jcbMSCampusesActionPerformed

    private void jcbMSTermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSTermsActionPerformed
        // TODO add your handling code here:
        if(jcbMSTermsFilled){
            Object objCampus = (Object)jcbMSCampuses.getSelectedItem();
            Campus campus = (Campus)objCampus;
            
            Object objTerm = (Object)jcbMSTerms.getSelectedItem();
            Term term = (Term)objTerm;
            jcbMSModulesFilled = false;
            lectureBoImpl.fillComboBoxLectureCampusTermModules(jcbMSModules, loggedInUser.getPersonId(),campus.getCampusCode(),term.getTermId());
            jcbMSModulesFilled = true;
        }
    }//GEN-LAST:event_jcbMSTermsActionPerformed

    private void jcbMSModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSModulesActionPerformed
        // TODO add your handling code here:
         if(jcbMSModulesFilled){
            Object objCampus = (Object)jcbMSCampuses.getSelectedItem();
            Campus campus = (Campus)objCampus;
            
            Object objTerm = (Object)jcbMSTerms.getSelectedItem();
            Term term = (Term)objTerm;
            
            Object objModule = (Object)jcbMSModules.getSelectedItem();
            Module module = (Module)objModule;
            
            StudentInModule(lectureBoImpl.GetLectureStudentsInTerm(loggedInUser.getPersonId(),campus.getCampusCode(), term.getTermId(), module.getModuleCode()));
            
        }
    }//GEN-LAST:event_jcbMSModulesActionPerformed

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
                new Instructor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAdminCourseRegistration;
    private javax.swing.JPanel btnAdminEducationHistory;
    private javax.swing.JPanel btnAdminHome;
    private javax.swing.JPanel btnAdminProfileRegistration;
    private javax.swing.JPanel btnStudentFiles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jcbMSCampuses;
    private javax.swing.JComboBox<String> jcbMSModules;
    private javax.swing.JComboBox<String> jcbMSTerms;
    private javax.swing.JPanel jpnContainer;
    private javax.swing.JPanel jpnControls;
    private javax.swing.JPanel jpnEducationHistory;
    private javax.swing.JPanel jpnMyStudents;
    private javax.swing.JPanel jpnRegisteredCourses;
    private javax.swing.JPanel jpnSideMenu;
    private javax.swing.JPanel jpnStudentFiles;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JPanel jpnUploadMarks;
    private javax.swing.JLabel lblHomeLoggedIn;
    private javax.swing.JTable tblStudentsInModule;
    // End of variables declaration//GEN-END:variables
}
