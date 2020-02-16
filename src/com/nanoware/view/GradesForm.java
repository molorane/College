/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import com.nanoware.bll.Functions;
import com.nanoware.bll.impl.GradeBoImpl;
import com.nanoware.bll.impl.GradeCategoryBoImpl;
import com.nanoware.bll.impl.CountryBoImpl;
import com.nanoware.bll.impl.CourseBoImpl;
import com.nanoware.bll.impl.EducationBoImpl;
import com.nanoware.bll.impl.EmploymentBoImpl;
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
import com.nanoware.bll.impl.StudentBoImpl;
import com.nanoware.bll.impl.StudyTypeBoImpl;
import com.nanoware.bll.impl.TestTimeTableBoImpl;
import com.nanoware.bll.impl.TitleBoImpl;
import com.nanoware.jasperservice.JasperLecture;
import com.nanoware.model.Grade;
import com.nanoware.model.GradeCategory;
import com.nanoware.model.Campus;
import com.nanoware.model.Course;
import com.nanoware.model.Module;
import com.nanoware.model.Term;
import com.nanoware.model.TestTimeTable;
import com.nanoware.model.User;
import diu.swe.habib.JPanelSlider.JPanelSlider;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.FileDialog;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Mothusi Molorane
 */
public class GradesForm extends javax.swing.JFrame {
    
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
    private final GradeCategoryBoImpl gradeCategoryBoImpl = new GradeCategoryBoImpl();
    private final LectureBoImpl lectureBoImpl = new LectureBoImpl();
    private final GradeBoImpl gradetBoImpl = new GradeBoImpl();
    private final StudentBoImpl studentBoImpl = new StudentBoImpl();
    private final TestTimeTableBoImpl testTimeTableBoImpl = new TestTimeTableBoImpl();
    private List<HashMap<String,Object>> uploadMarks = new ArrayList<>();
    private final GUI gui = new GUI();
    private GUIThread guiThread;
    private GUIThread guiThreadPrevious = null;
    private DefaultTableModel tblModelEduHistory;
    private int EDUID;
    private int EDUROWSELECTED;
    private boolean jcbDepartmentClicked = false;
    
    private DefaultTableModel tblModelDepartmentCouses;
    private DefaultTableModel tblModelCourseModules;
    private DefaultTableModel tblModelStudentsInModule;
    private DefaultTableModel tblModelMarks;
    private DefaultTableModel tblModelContinuousAssessment;
    private DefaultTableModel tblModelTestTimeTable;    
    private String selectedCourseCode;
    private String selectedCourse;
    
    
    private long personId;
    private final User loggedInUser;
    
    private boolean jcbMSCampusesFilled = false;
    private boolean jcbMSTermsFilled = false;
    private boolean jcbMSModulesFilled = false;
    private boolean jcbGradeFilled = false;
    
    private Campus selectedCampus;
    private Term selectedTerm;
    private Module selectedModule;
    private GradeCategory selectedGradeCategory;
    
    private final JasperLecture jasperLecture = new JasperLecture();

    /**
     * Creates new form Instructor
     */
    public GradesForm() {
        super("Lecture - "+Functions.appName());
        initComponents();
        Functions.setAppIcon(GradesForm.this);
      
        loggedInUser = Functions.GetUser("blessy"); //LoginSession.getSessionUser();
        
        lectureBoImpl.fillComboBoxLectureCampuses(jcbMSCampuses, loggedInUser.getPersonId());
        jcbMSCampusesFilled = true;
        
        gradeCategoryBoImpl.fillComboBoxGradeCategory(jcbGradeCategory);
        jcbGradeFilled = true;
        
        EDUID = 0;
        EDUROWSELECTED = 0;
                
        tblModelStudentsInModule = (DefaultTableModel)tblStudentsInModule.getModel();
        tblModelMarks = (DefaultTableModel)tblMarks.getModel();
        tblModelContinuousAssessment = (DefaultTableModel)tblContinuousAssessment.getModel();
        tblModelTestTimeTable = (DefaultTableModel)tblTestTimeTable.getModel();
        
        Functions.setLoggedInUser(lblHomeLoggedIn,loggedInUser);
        setLocationRelativeTo(null);
    }
    
     void btnSMMyStudentsPressed(){
        setColor(btnSMMyStudents);
        resetColor(btnSMUploadMarks);
        resetColor(btnSMAssessments);
        resetColor(btnSMTestTimeTable);
        resetColor(btnSMExamTimeTable);
    }
    
    void btnSMUploadMarksPressed(){
       resetColor(btnSMMyStudents);
       setColor(btnSMUploadMarks);
       resetColor(btnSMAssessments);
       resetColor(btnSMTestTimeTable);
       resetColor(btnSMExamTimeTable);
    }
    
    void btnSMAssessmentsPressed(){
       resetColor(btnSMMyStudents);
       resetColor(btnSMUploadMarks);
       setColor(btnSMAssessments);
       resetColor(btnSMTestTimeTable);
       resetColor(btnSMExamTimeTable);
    }
    
    void btnSMTestTimeTablePressed(){
       resetColor(btnSMMyStudents);
       resetColor(btnSMUploadMarks);
       resetColor(btnSMAssessments);
       setColor(btnSMTestTimeTable);
       resetColor(btnSMExamTimeTable);
    }
    
    void btnSMExamTimeTablePressed(){
       resetColor(btnSMMyStudents);
       resetColor(btnSMUploadMarks);
       resetColor(btnSMAssessments);
       resetColor(btnSMTestTimeTable);
       setColor(btnSMExamTimeTable);
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
    
    public void fillTableMarks(List<HashMap<String, Object>> marks){
        tblModelMarks.setRowCount(0);
        marks.forEach((row) -> {
            tblModelMarks.addRow(new Object[]{
                row.get("personId"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("otherName"),
                row.get("mark")
             });
        });
    }
    
    public void fillTableContinuousAssessment(List<HashMap<String, Object>> assessments){
        tblModelContinuousAssessment.setRowCount(0);
        assessments.forEach((row) -> {
            tblModelContinuousAssessment.addRow(new Object[]{
                row.get("personId"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("otherName"),
                row.get("assessment")
             });
        });
    }
    
    public void fillTableTestTimeTable(ArrayList<TestTimeTable> timeTable){
        tblModelTestTimeTable.setRowCount(0);
        timeTable.forEach((row) -> {
            tblModelTestTimeTable.addRow(new Object[]{
                row.getTtId(),
                row.getGrade(),
                row.getTestDate(),
                row.getStartTime(),
                row.getDuration(),
                row.getVenue()
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
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnSMMyStudents = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSMUploadMarks = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSMAssessments = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSMTestTimeTable = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSMExamTimeTable = new javax.swing.JPanel();
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
        jpnSlider = new diu.swe.habib.JPanelSlider.JPanelSlider();
        jpnMyStudents = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStudentsInModule = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        btnClassList = new javax.swing.JButton();
        jpnUploadMarks = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnBrowseFile = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMarks = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jcbGradeCategory = new javax.swing.JComboBox<>();
        btnUploadMarks = new javax.swing.JButton();
        btnMarksheet = new javax.swing.JButton();
        jpnAssessments = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblContinuousAssessment = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnContinuousAssemment = new javax.swing.JButton();
        jpnStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        imgStatus = new javax.swing.JLabel();
        jpnTestTimeTable = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        btnTTRefresh = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTestTimeTable = new javax.swing.JTable();
        btnTestTimeTable = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jpnExamTimeTable = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("LECTURE");

        btnSMMyStudents.setBackground(new java.awt.Color(64, 43, 100));
        btnSMMyStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMMyStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMMyStudentsMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/users.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("My Students");

        javax.swing.GroupLayout btnSMMyStudentsLayout = new javax.swing.GroupLayout(btnSMMyStudents);
        btnSMMyStudents.setLayout(btnSMMyStudentsLayout);
        btnSMMyStudentsLayout.setHorizontalGroup(
            btnSMMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMMyStudentsLayout.setVerticalGroup(
            btnSMMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSMUploadMarks.setBackground(new java.awt.Color(54, 33, 89));
        btnSMUploadMarks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMUploadMarks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMUploadMarksMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/import_export.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Upload Marks");

        javax.swing.GroupLayout btnSMUploadMarksLayout = new javax.swing.GroupLayout(btnSMUploadMarks);
        btnSMUploadMarks.setLayout(btnSMUploadMarksLayout);
        btnSMUploadMarksLayout.setHorizontalGroup(
            btnSMUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMUploadMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMUploadMarksLayout.setVerticalGroup(
            btnSMUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMUploadMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSMAssessments.setBackground(new java.awt.Color(54, 33, 89));
        btnSMAssessments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMAssessments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMAssessmentsMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/graduation.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Assessments");

        javax.swing.GroupLayout btnSMAssessmentsLayout = new javax.swing.GroupLayout(btnSMAssessments);
        btnSMAssessments.setLayout(btnSMAssessmentsLayout);
        btnSMAssessmentsLayout.setHorizontalGroup(
            btnSMAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMAssessmentsLayout.setVerticalGroup(
            btnSMAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        btnSMTestTimeTable.setBackground(new java.awt.Color(54, 33, 89));
        btnSMTestTimeTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMTestTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMTestTimeTableMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/schedule.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Test TimeTable");

        javax.swing.GroupLayout btnSMTestTimeTableLayout = new javax.swing.GroupLayout(btnSMTestTimeTable);
        btnSMTestTimeTable.setLayout(btnSMTestTimeTableLayout);
        btnSMTestTimeTableLayout.setHorizontalGroup(
            btnSMTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMTestTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMTestTimeTableLayout.setVerticalGroup(
            btnSMTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMTestTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSMExamTimeTable.setBackground(new java.awt.Color(54, 33, 89));
        btnSMExamTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMExamTimeTableMouseClicked(evt);
            }
        });

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/schedule.png"))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(204, 204, 204));
        jLabel56.setText("Exam TimeTable");

        javax.swing.GroupLayout btnSMExamTimeTableLayout = new javax.swing.GroupLayout(btnSMExamTimeTable);
        btnSMExamTimeTable.setLayout(btnSMExamTimeTableLayout);
        btnSMExamTimeTableLayout.setHorizontalGroup(
            btnSMExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMExamTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMExamTimeTableLayout.setVerticalGroup(
            btnSMExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMExamTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                    .addComponent(btnSMExamTimeTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSMTestTimeTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSMAssessments, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSMUploadMarks, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSMMyStudents, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(btnSMMyStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMUploadMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMAssessments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMTestTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMExamTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jcbMSCampuses.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSCampuses.setForeground(new java.awt.Color(51, 51, 51));
        jcbMSCampuses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSCampusesActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Term:");

        jcbMSTerms.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSTerms.setForeground(new java.awt.Color(51, 51, 51));
        jcbMSTerms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMSTermsActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Module:");

        jcbMSModules.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbMSModules.setForeground(new java.awt.Color(51, 51, 51));
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
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMSCampuses, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMSTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jScrollPane5.setBorder(null);

        tblStudentsInModule.setAutoCreateRowSorter(true);
        tblStudentsInModule.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStudentsInModule.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStudentsInModule.setForeground(new java.awt.Color(51, 51, 51));
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

        btnClassList.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClassList.setForeground(new java.awt.Color(51, 51, 51));
        btnClassList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        btnClassList.setToolTipText("Class list");
        btnClassList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnMyStudentsLayout = new javax.swing.GroupLayout(jpnMyStudents);
        jpnMyStudents.setLayout(jpnMyStudentsLayout);
        jpnMyStudentsLayout.setHorizontalGroup(
            jpnMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(jpnMyStudentsLayout.createSequentialGroup()
                        .addComponent(btnClassList)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnMyStudentsLayout.setVerticalGroup(
            jpnMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMyStudentsLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnClassList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnMyStudents, "card2");

        btnBrowseFile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBrowseFile.setForeground(new java.awt.Color(51, 51, 51));
        btnBrowseFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/csv.png"))); // NOI18N
        btnBrowseFile.setToolTipText("Import marks from a csv file");
        btnBrowseFile.setIconTextGap(20);
        btnBrowseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseFileActionPerformed(evt);
            }
        });

        jScrollPane6.setBorder(null);

        tblMarks.setAutoCreateRowSorter(true);
        tblMarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblMarks.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblMarks.setForeground(new java.awt.Color(51, 51, 51));
        tblMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student No", "Last Name", "First Name", "Other Name", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMarks.setGridColor(new java.awt.Color(204, 204, 204));
        tblMarks.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblMarks.setRowHeight(40);
        tblMarks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarksMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblMarks);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Grade Category:");

        jcbGradeCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbGradeCategory.setForeground(new java.awt.Color(51, 51, 51));
        jcbGradeCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGradeCategoryActionPerformed(evt);
            }
        });

        btnUploadMarks.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUploadMarks.setForeground(new java.awt.Color(51, 51, 51));
        btnUploadMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        btnUploadMarks.setToolTipText("Upload marks from a csv file");
        btnUploadMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadMarksActionPerformed(evt);
            }
        });

        btnMarksheet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMarksheet.setForeground(new java.awt.Color(51, 51, 51));
        btnMarksheet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        btnMarksheet.setToolTipText("Marksheet");
        btnMarksheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarksheetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnUploadMarksLayout = new javax.swing.GroupLayout(jpnUploadMarks);
        jpnUploadMarks.setLayout(jpnUploadMarksLayout);
        jpnUploadMarksLayout.setHorizontalGroup(
            jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jpnUploadMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbGradeCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBrowseFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUploadMarks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMarksheet)
                .addContainerGap(583, Short.MAX_VALUE))
            .addGroup(jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnUploadMarksLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jpnUploadMarksLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBrowseFile, btnMarksheet, btnUploadMarks});

        jpnUploadMarksLayout.setVerticalGroup(
            jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUploadMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBrowseFile)
                        .addComponent(jLabel3))
                    .addComponent(btnMarksheet)
                    .addComponent(btnUploadMarks)
                    .addComponent(jcbGradeCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(677, Short.MAX_VALUE))
            .addGroup(jpnUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnUploadMarksLayout.createSequentialGroup()
                    .addGap(86, 86, 86)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jpnUploadMarksLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBrowseFile, btnMarksheet, btnUploadMarks});

        jpnSlider.add(jpnUploadMarks, "card3");

        jScrollPane7.setBorder(null);

        tblContinuousAssessment.setAutoCreateRowSorter(true);
        tblContinuousAssessment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblContinuousAssessment.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblContinuousAssessment.setForeground(new java.awt.Color(51, 51, 51));
        tblContinuousAssessment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student No", "Last Name", "First Name", "Other Name", "ContASS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContinuousAssessment.setGridColor(new java.awt.Color(204, 204, 204));
        tblContinuousAssessment.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblContinuousAssessment.setRowHeight(40);
        tblContinuousAssessment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblContinuousAssessmentMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblContinuousAssessment);

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/order.png"))); // NOI18N
        btnRefresh.setToolTipText("Refresh to display records");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnContinuousAssemment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnContinuousAssemment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        btnContinuousAssemment.setToolTipText("Continuous assessment");
        btnContinuousAssemment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuousAssemmentActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(51, 51, 51));
        lblStatus.setToolTipText("");

        javax.swing.GroupLayout jpnStatusLayout = new javax.swing.GroupLayout(jpnStatus);
        jpnStatus.setLayout(jpnStatusLayout);
        jpnStatusLayout.setHorizontalGroup(
            jpnStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(imgStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnStatusLayout.setVerticalGroup(
            jpnStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnStatusLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(imgStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnAssessmentsLayout = new javax.swing.GroupLayout(jpnAssessments);
        jpnAssessments.setLayout(jpnAssessmentsLayout);
        jpnAssessmentsLayout.setHorizontalGroup(
            jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                    .addGroup(jpnAssessmentsLayout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuousAssemment)
                        .addGap(577, 577, 577)
                        .addComponent(jpnStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpnAssessmentsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnContinuousAssemment, btnRefresh});

        jpnAssessmentsLayout.setVerticalGroup(
            jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnContinuousAssemment, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnRefresh))
                    .addComponent(jpnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnAssessmentsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnContinuousAssemment, btnRefresh});

        jpnSlider.add(jpnAssessments, "card4");

        btnTTRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/order.png"))); // NOI18N
        btnTTRefresh.setToolTipText("Refresh to display");
        btnTTRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTRefreshActionPerformed(evt);
            }
        });

        jScrollPane8.setBorder(null);

        tblTestTimeTable.setAutoCreateRowSorter(true);
        tblTestTimeTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblTestTimeTable.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblTestTimeTable.setForeground(new java.awt.Color(51, 51, 51));
        tblTestTimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref#", "Grade", "TestDate", "StartTime", "Duration", "Venue"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTestTimeTable.setGridColor(new java.awt.Color(204, 204, 204));
        tblTestTimeTable.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblTestTimeTable.setRowHeight(40);
        tblTestTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTestTimeTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblTestTimeTable);

        btnTestTimeTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        btnTestTimeTable.setToolTipText("Time table for the selected campus , term & module");
        btnTestTimeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestTimeTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTTRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTestTimeTable)
                        .addGap(0, 967, Short.MAX_VALUE))
                    .addComponent(jSeparator5))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTTRefresh)
                    .addComponent(btnTestTimeTable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(620, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Test Time Table", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Add TimeTable", jPanel2);

        javax.swing.GroupLayout jpnTestTimeTableLayout = new javax.swing.GroupLayout(jpnTestTimeTable);
        jpnTestTimeTable.setLayout(jpnTestTimeTableLayout);
        jpnTestTimeTableLayout.setHorizontalGroup(
            jpnTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTestTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jpnTestTimeTableLayout.setVerticalGroup(
            jpnTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTestTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jpnSlider.add(jpnTestTimeTable, "card5");

        javax.swing.GroupLayout jpnExamTimeTableLayout = new javax.swing.GroupLayout(jpnExamTimeTable);
        jpnExamTimeTable.setLayout(jpnExamTimeTableLayout);
        jpnExamTimeTableLayout.setHorizontalGroup(
            jpnExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1171, Short.MAX_VALUE)
        );
        jpnExamTimeTableLayout.setVerticalGroup(
            jpnExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 762, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnExamTimeTable, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSMMyStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMMyStudentsMouseClicked
        // TODO add your handling code here:
        btnSMMyStudentsPressed();
        jpnSlider.nextPanel(10, jpnMyStudents, JPanelSlider.right);
    }//GEN-LAST:event_btnSMMyStudentsMouseClicked

    private void btnSMUploadMarksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMUploadMarksMouseClicked
        // TODO add your handling code here:
        btnSMUploadMarksPressed();
        jpnSlider.nextPanel(10, jpnUploadMarks, JPanelSlider.right);
    }//GEN-LAST:event_btnSMUploadMarksMouseClicked

    private void btnSMTestTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMTestTimeTableMouseClicked
        // TODO add your handling code here:
        btnSMTestTimeTablePressed();
        jpnSlider.nextPanel(10, jpnTestTimeTable, JPanelSlider.left);
    }//GEN-LAST:event_btnSMTestTimeTableMouseClicked

    private void btnSMAssessmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMAssessmentsMouseClicked
        // TODO add your handling code here:
        btnSMAssessmentsPressed();
        jpnSlider.nextPanel(10, jpnAssessments, JPanelSlider.right);
    }//GEN-LAST:event_btnSMAssessmentsMouseClicked

    private void btnSMExamTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMExamTimeTableMouseClicked
        // TODO add your handling code here:
        btnSMExamTimeTablePressed();
        jpnSlider.nextPanel(10, jpnExamTimeTable, JPanelSlider.right);
    }//GEN-LAST:event_btnSMExamTimeTableMouseClicked

    private void tblStudentsInModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsInModuleMouseClicked
        // TODO add your handling code here:
        int row = tblStudentsInModule.getSelectedRow();
        int personId = Integer.parseInt((tblStudentsInModule.getModel().getValueAt(row, 0).toString()));
        new StudentModuleGrades(personId, selectedTerm.getTermId(), selectedModule.getModuleCode()).setVisible(true);
    }//GEN-LAST:event_tblStudentsInModuleMouseClicked

    private void jcbMSCampusesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSCampusesActionPerformed
        // TODO add your handling code here:
        if(jcbMSCampusesFilled){
            Object objCampus = (Object)jcbMSCampuses.getSelectedItem();
            selectedCampus = (Campus)objCampus;
            jcbMSTermsFilled = false;
            lectureBoImpl.fillComboBoxLectureCampusTerms(jcbMSTerms, loggedInUser.getPersonId(), selectedCampus.getCampusCode());
            jcbMSTermsFilled = true;
        }
    }//GEN-LAST:event_jcbMSCampusesActionPerformed

    private void jcbMSTermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSTermsActionPerformed
        // TODO add your handling code here:
        if(jcbMSTermsFilled){
            Object objTerm = (Object)jcbMSTerms.getSelectedItem();
            selectedTerm = (Term)objTerm;
            jcbMSModulesFilled = false;
            lectureBoImpl.fillComboBoxLectureCampusTermModules(jcbMSModules, loggedInUser.getPersonId(),selectedCampus.getCampusCode(),selectedTerm.getTermId());
            jcbMSModulesFilled = true;
        }
    }//GEN-LAST:event_jcbMSTermsActionPerformed

    private void jcbMSModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMSModulesActionPerformed
        // TODO add your handling code here:
         if(jcbMSModulesFilled){
            Object objModule = (Object)jcbMSModules.getSelectedItem();
            selectedModule = (Module)objModule;
            StudentInModule(lectureBoImpl.GetLectureStudentsInTerm(loggedInUser.getPersonId(),selectedCampus.getCampusCode(), selectedTerm.getTermId(), selectedModule.getModuleCode()));
        }
    }//GEN-LAST:event_jcbMSModulesActionPerformed

    private void tblMarksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarksMouseClicked
        // TODO add your handling code here:
        if(uploadMarks.isEmpty()){
            int row = tblMarks.getSelectedRow();
            String mark = (tblMarks.getModel().getValueAt(row, 4).toString());
            int studentNo = Integer.parseInt(tblMarks.getModel().getValueAt(row, 0).toString());

            String student = "Student No:"+studentNo+"\n"+
                             "Last Name:"+tblMarks.getModel().getValueAt(row, 1).toString()+"\n"+
                             "First Name:"+tblMarks.getModel().getValueAt(row, 2).toString();
            String input = (String)JOptionPane.showInputDialog(this, "NEW MARK FOR STUDENT\n\n"+student,"Edit student mark",JOptionPane.QUESTION_MESSAGE,null,null,mark);
            if(input != null){
                double newMark = Double.parseDouble(input);
                Grade a = new Grade();
                
                a.setGrade(studentNo, selectedModule.getModuleCode(), 
                                           selectedTerm.getTermId(), 
                                            selectedGradeCategory.getGradeId(), 
                                            newMark);
                int updated = gradetBoImpl.EditGrade(a, selectedCampus.getCampusCode());
                if(updated > 0){
                    tblMarks.getModel().setValueAt(newMark, row, 4);
                    Functions.successMessage("Grade successfully updated!");
                }else{
                    Functions.warningMessage("No changes made.");
                }
            }
        }
    }//GEN-LAST:event_tblMarksMouseClicked

    private void btnBrowseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseFileActionPerformed
        // TODO add your handling code here:
        try{
            String filename = null;
            FileDialog fd = new FileDialog(this, "Open File",FileDialog.LOAD);
            fd.setFile("*.csv");
            fd.setResizable(true);
            fd.setVisible(true);

            filename = fd.getDirectory()+fd.getFile();

            if(filename != null){
                List<HashMap<String,Object>> marks = Functions.readCSVFile(filename);
                uploadMarks = marks;
                fillTableMarks(marks);
            }
        }catch(Exception ex){
           Functions.errorMessage(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnBrowseFileActionPerformed

    private void btnUploadMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadMarksActionPerformed
        // TODO add your handling code here:
        
        if(uploadMarks.size() > 0){
            List<HashMap<String,Object>> list = new ArrayList<>();
            uploadMarks.forEach((row) -> {
                HashMap<String,Object> mark = new HashMap<>(2);
                mark.put("personId", row.get("personId"));
                mark.put("mark", row.get("mark"));
                list.add(mark);
            });
            
            Object objGrade = (Object)jcbGradeCategory.getSelectedItem();
            selectedGradeCategory = (GradeCategory)objGrade;
            
            Functions.infoMessage("To successfully upload marks, the following conditions must hold\n"
                    + "1. Student must be registered for a selected campus,term and module\n"
                    + "2. If a record of a student for the selected campus,term, module already exists, it will be updated\n"
                    + "3. All students who doesn't meet the conditions will be ignored!");
            
            int uploaded = gradetBoImpl.UploadMarks(list, selectedCampus.getCampusCode(), selectedModule.getModuleCode(), selectedTerm.getTermId(), selectedGradeCategory.getGradeId());
           
            if(uploaded > 0){
               Functions.successMessage("Congratulations! Marks uploaded successfully");
               tblModelMarks.setRowCount(0);
               uploadMarks.clear();
            }else{
               Functions.errorMessage("No changes made to repository!\n"
                       + "This might be because you uploaded same marks as previously stored for all students\n"
                       + "or the students you are uploading marks for are not currently registered for the module.\n"
                       + "in a selected term at at selected campus");
            }
        }else{
            Functions.warningMessage("Before uploading marks, you must first have selected a CSV file\n"
                    + "containing the marks. The file must be formatted correctly. If you didn't format the CSV file\n"
                    + "correctly, you might not be able to upload marks.");
        }
    }//GEN-LAST:event_btnUploadMarksActionPerformed

    private void jcbGradeCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGradeCategoryActionPerformed
        // TODO add your handling code here:
        if(jcbGradeFilled){
            if(selectedCampus!=null && selectedModule!=null && selectedTerm!=null){
                Object objGrade = (Object)jcbGradeCategory.getSelectedItem();
                selectedGradeCategory = (GradeCategory)objGrade;
                List<HashMap<String,Object>> list = gradetBoImpl.GetStudentMarks(selectedCampus.getCampusCode(), 
                                                                selectedModule.getModuleCode(), 
                                                                selectedTerm.getTermId(), 
                                                                selectedGradeCategory.getGradeId());
                uploadMarks.clear();
                fillTableMarks(list);
            }
        }
    }//GEN-LAST:event_jcbGradeCategoryActionPerformed

    private void btnMarksheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarksheetActionPerformed
        // TODO add your handling code here:
         if(selectedCampus!=null && selectedModule!=null && selectedTerm!=null){
            Object objGrade = (Object)jcbGradeCategory.getSelectedItem();
            selectedGradeCategory = (GradeCategory)objGrade;
             
            jasperLecture.GetStudentMarks(selectedCampus.getCampusCode(), 
                                          selectedModule.getModuleCode(), 
                                          selectedTerm.getTermId(), 
                                          selectedGradeCategory.getGradeId());
        }
    }//GEN-LAST:event_btnMarksheetActionPerformed

    private void btnClassListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassListActionPerformed
        // TODO add your handling code here:
        if(selectedCampus!=null && selectedTerm!=null && selectedModule!=null ){
            jasperLecture.LectureClassList(loggedInUser.getPersonId(),
                                          selectedCampus.getCampusCode(), 
                                          selectedTerm.getTermId(), 
                                          selectedModule.getModuleCode()); 
        }else{
            Functions.warningMessage("Select all required options first.");
        }
    }//GEN-LAST:event_btnClassListActionPerformed

    private void tblContinuousAssessmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblContinuousAssessmentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblContinuousAssessmentMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        gui.setNotification(jpnStatus,lblStatus, imgStatus, true);
        guiThread = new GUIThread();
        if(guiThreadPrevious!=null){ guiThreadPrevious.Stop(); }
        guiThreadPrevious = guiThread;
        guiThread.setGUIThread(jpnStatus,"Action successful.", lblStatus);
        guiThread.start();
        if(selectedCampus!=null && selectedTerm!=null && selectedModule!=null ){
            fillTableContinuousAssessment(
                studentBoImpl.
                        GetStudentModuleAssessment(
                                selectedCampus.getCampusCode(), 
                                          selectedTerm.getTermId(), 
                                          selectedModule.getModuleCode()));
        }
        
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnContinuousAssemmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuousAssemmentActionPerformed
        // TODO add your handling code here:
        gui.setNotification(jpnStatus,lblStatus, imgStatus, false);
        guiThread = new GUIThread();
        if(guiThreadPrevious!=null){ guiThreadPrevious.Stop(); }
        guiThreadPrevious = guiThread;
        guiThread.setGUIThread(jpnStatus,"Action not successful.", lblStatus);
        guiThread.start();
        
        if(selectedCampus!=null && selectedTerm!=null && selectedModule!=null ){
            jasperLecture.GetStudentModuleAssessment(
                                          selectedCampus.getCampusCode(), 
                                          selectedTerm.getTermId(), 
                                          selectedModule.getModuleCode()); 
        }
    }//GEN-LAST:event_btnContinuousAssemmentActionPerformed

    private void tblTestTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTestTimeTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTestTimeTableMouseClicked

    private void btnTTRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTRefreshActionPerformed
        // TODO add your handling code here:
        if(selectedCampus!=null && selectedModule!=null && selectedTerm!=null){
            fillTableTestTimeTable(testTimeTableBoImpl.
                    GetCampusModuleTestTimeTable(
                            selectedCampus.getCampusCode(), 
                            selectedModule.getModuleCode(), 
                            selectedTerm.getTermId()));
        }else{
            Functions.warningMessage("Select all required options first.");
        }
    }//GEN-LAST:event_btnTTRefreshActionPerformed

    private void btnTestTimeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestTimeTableActionPerformed
        // TODO add your handling code here:
        if(selectedCampus!=null && selectedModule!=null && selectedTerm!=null){
            jasperLecture.GetCampusModuleTestTimeTable(
                                          selectedCampus.getCampusCode(), 
                                          selectedModule.getModuleCode(), 
                                          selectedTerm.getTermId()); 
        }else{
            Functions.warningMessage("Select all required options first.");
        }
    }//GEN-LAST:event_btnTestTimeTableActionPerformed

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
                new GradesForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseFile;
    private javax.swing.JButton btnClassList;
    private javax.swing.JButton btnContinuousAssemment;
    private javax.swing.JButton btnMarksheet;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel btnSMAssessments;
    private javax.swing.JPanel btnSMExamTimeTable;
    private javax.swing.JPanel btnSMMyStudents;
    private javax.swing.JPanel btnSMTestTimeTable;
    private javax.swing.JPanel btnSMUploadMarks;
    private javax.swing.JButton btnTTRefresh;
    private javax.swing.JButton btnTestTimeTable;
    private javax.swing.JButton btnUploadMarks;
    private javax.swing.JLabel imgStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JComboBox<String> jcbGradeCategory;
    private javax.swing.JComboBox<String> jcbMSCampuses;
    private javax.swing.JComboBox<String> jcbMSModules;
    private javax.swing.JComboBox<String> jcbMSTerms;
    private javax.swing.JPanel jpnAssessments;
    private javax.swing.JPanel jpnControls;
    private javax.swing.JPanel jpnExamTimeTable;
    private javax.swing.JPanel jpnMyStudents;
    private javax.swing.JPanel jpnSideMenu;
    private diu.swe.habib.JPanelSlider.JPanelSlider jpnSlider;
    private javax.swing.JPanel jpnStatus;
    private javax.swing.JPanel jpnTestTimeTable;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JPanel jpnUploadMarks;
    private javax.swing.JLabel lblHomeLoggedIn;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblContinuousAssessment;
    private javax.swing.JTable tblMarks;
    private javax.swing.JTable tblStudentsInModule;
    private javax.swing.JTable tblTestTimeTable;
    // End of variables declaration//GEN-END:variables
}
