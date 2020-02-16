/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.lecture;

import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.GradeBoImpl;
import com.molorane.college.bll.impl.GradeCategoryBoImpl;
import com.molorane.college.bll.impl.CountryBoImpl;
import com.molorane.college.bll.impl.CourseBoImpl;
import com.molorane.college.bll.impl.EducationBoImpl;
import com.molorane.college.bll.impl.EmploymentBoImpl;
import com.molorane.college.bll.impl.GenderBoImpl;
import com.molorane.college.bll.impl.GurdianBoImpl;
import com.molorane.college.bll.impl.InstitutionBoImpl;
import com.molorane.college.bll.impl.LanguageBoImpl;
import com.molorane.college.bll.impl.LectureBoImpl;
import com.molorane.college.bll.impl.ModuleBoImpl;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.PositionBoImpl;
import com.molorane.college.bll.impl.QualificationBoImpl;
import com.molorane.college.bll.impl.RaceBoImpl;
import com.molorane.college.bll.impl.RelationshipBoImpl;
import com.molorane.college.bll.impl.ReligionBoImpl;
import com.molorane.college.bll.impl.StudentBoImpl;
import com.molorane.college.bll.impl.StudyTypeBoImpl;
import com.molorane.college.bll.impl.TestTimeTableBoImpl;
import com.molorane.college.bll.impl.TitleBoImpl;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.custom.NotifyData;
import com.molorane.college.jasperservice.JasperLecture;
import com.molorane.college.model.Grade;
import com.molorane.college.model.GradeCategory;
import com.molorane.college.model.Campus;
import com.molorane.college.model.Course;
import com.molorane.college.model.Module;
import com.molorane.college.model.Term;
import com.molorane.college.model.TestTimeTable;
import com.molorane.college.model.User;
import com.molorane.college.view.Controls.StudentModuleGradesPnl;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Mothusi Molorane
 */
public class GradesFrm extends javax.swing.JFrame {
    
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
    private DefaultTableModel tblModelEduHistory;
    private int EDUID;
    private int EDUROWSELECTED;
    private boolean jcbDepartmentClicked = false;
    
    private final DefaultTableModel tblModelStudentsInModule;
    private final DefaultTableModel tblModelMarks;
    private final DefaultTableModel tblModelCaptureMarks;
    private final DefaultTableModel tblModelContinuousAssessment;
    private final DefaultTableModel tblModelTestTimeTable;
    
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
    
    private List<HashMap<String, Object>> grades;
    private double continuousAssessment;
    
    private final JasperLecture jasperLecture = new JasperLecture();
    
    private StudentModuleGradesPnl studentModuleGrades;

    /**
     * Creates new form Instructor
     */
    
    public GradesFrm() {
        super("Lecture - "+Functions.appName());
        initComponents();
     
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(GradesFrm.this);
        
        lectureBoImpl.fillComboBoxLectureCampuses(jcbMSCampuses, loggedInUser.getPersonId());
        jcbMSCampusesFilled = true;
        
        gradeCategoryBoImpl.fillComboBoxGradeCategory(jcbGradeCategory);
        jcbGradeFilled = true;
        
        EDUID = 0;
        EDUROWSELECTED = 0;
                
        tblModelStudentsInModule = (DefaultTableModel)tblStudentsInModule.getModel();
        tblModelMarks = (DefaultTableModel)tblMarks.getModel();
        tblModelCaptureMarks = (DefaultTableModel)tblCaptureMarks.getModel();
        tblModelContinuousAssessment = (DefaultTableModel)tblContinuousAssessment.getModel();
        tblModelTestTimeTable = (DefaultTableModel)tblTestTimeTable.getModel();
        
        Functions.initForm(GradesFrm.this);
    }
    
    void btnSMMyStudentsPressed(){
        Functions.setColor(btnSMMyStudents);
        Functions.resetColor(btnSMUploadMarks);
        Functions.resetColor(btnSMAssessments);
        Functions.resetColor(btnSMTestTimeTable);
        Functions.resetColor(btnSMExamTimeTable);
        Functions.resetColor(btnSMClassTimeTable);
    }
    
    void btnSMUploadMarksPressed(){
       Functions.resetColor(btnSMMyStudents);
       Functions.setColor(btnSMUploadMarks);
       Functions.resetColor(btnSMAssessments);
       Functions.resetColor(btnSMTestTimeTable);
       Functions.resetColor(btnSMExamTimeTable);
       Functions.resetColor(btnSMClassTimeTable);
    }
    
    void btnSMAssessmentsPressed(){
       Functions.resetColor(btnSMMyStudents);
       Functions.resetColor(btnSMUploadMarks);
       Functions.setColor(btnSMAssessments);
       Functions.resetColor(btnSMTestTimeTable);
       Functions.resetColor(btnSMExamTimeTable);
       Functions.resetColor(btnSMClassTimeTable);
    }
    
    void btnSMTestTimeTablePressed(){
       Functions.resetColor(btnSMMyStudents);
       Functions.resetColor(btnSMUploadMarks);
       Functions.resetColor(btnSMAssessments);
       Functions.setColor(btnSMTestTimeTable);
       Functions.resetColor(btnSMExamTimeTable);
       Functions.resetColor(btnSMClassTimeTable);
    }
    
    void btnSMExamTimeTablePressed(){
       Functions.resetColor(btnSMMyStudents);
       Functions.resetColor(btnSMUploadMarks);
       Functions.resetColor(btnSMAssessments);
       Functions.resetColor(btnSMTestTimeTable);
       Functions.setColor(btnSMExamTimeTable);
       Functions.resetColor(btnSMClassTimeTable);
    }
    
    void btnSMClassTimeTablePressed(){
       Functions.resetColor(btnSMMyStudents);
       Functions.resetColor(btnSMUploadMarks);
       Functions.resetColor(btnSMAssessments);
       Functions.resetColor(btnSMTestTimeTable);
       Functions.resetColor(btnSMExamTimeTable);
       Functions.setColor(btnSMClassTimeTable);
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
    
    public void fillMarks(List<HashMap<String, Object>> marks){
        tblModelMarks.setRowCount(0);
        marks.forEach((row) -> {
            tblModelMarks.addRow(new Object[]{
                row.get("personId"),
                row.get("idno"),
                row.get("lastName") + " " + row.get("firstName") + " " + row.get("otherName"),
                row.get("mark")
             });
        });
    }
    
    public void fillCaptureMarks(List<HashMap<String, Object>> marks){
        tblModelCaptureMarks.setRowCount(0);
        marks.forEach((row) -> {
            tblModelCaptureMarks.addRow(new Object[]{
                row.get("personId"),
                row.get("idno"),
                row.get("lastName") + " " + row.get("firstName") + " " + row.get("otherName"),
                row.get("mark")
             });
        });
    }
    
    public void fillTableContinuousAssessment(List<HashMap<String, Object>> assessments){
        tblModelContinuousAssessment.setRowCount(0);
        assessments.forEach((row) -> {
            tblModelContinuousAssessment.addRow(new Object[]{
                row.get("personId"),
                row.get("idno"),
                row.get("lastName") + " " + row.get("firstName") + " " + row.get("otherName"),
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

        jpnStudentModuleGrades = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblModuleGrades = new javax.swing.JTable();
        btnStudentAssessment = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblConsinuousAssessment = new javax.swing.JLabel();
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
        btnSMClassTimeTable = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();
        jpnControls = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jcbMSCampuses = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jcbMSTerms = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        jcbMSModules = new javax.swing.JComboBox<>();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnMyStudents = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStudentsInModule = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        btnClassList = new javax.swing.JButton();
        jpnMarks = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jcbGradeCategory = new javax.swing.JComboBox<>();
        btnMarksheet = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMarks = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblCaptureMarks = new javax.swing.JTable();
        btnCaptureMarks = new javax.swing.JButton();
        jpnAssessments = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblContinuousAssessment = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnContinuousAssemment = new javax.swing.JButton();
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
        jpnClassTimeTable = new javax.swing.JPanel();

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

        javax.swing.GroupLayout jpnStudentModuleGradesLayout = new javax.swing.GroupLayout(jpnStudentModuleGrades);
        jpnStudentModuleGrades.setLayout(jpnStudentModuleGradesLayout);
        jpnStudentModuleGradesLayout.setHorizontalGroup(
            jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnStudentModuleGradesLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane9)
                        .addGroup(jpnStudentModuleGradesLayout.createSequentialGroup()
                            .addComponent(btnStudentAssessment)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblConsinuousAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(11, 11, 11)))
        );
        jpnStudentModuleGradesLayout.setVerticalGroup(
            jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
            .addGroup(jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnStudentModuleGradesLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)
                    .addGroup(jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnStudentModuleGradesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblConsinuousAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addComponent(btnStudentAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("LECTURE");

        btnSMMyStudents.setBackground(new java.awt.Color(64, 43, 100));
        btnSMMyStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMMyStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMMyStudentsMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/users.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Students");

        javax.swing.GroupLayout btnSMMyStudentsLayout = new javax.swing.GroupLayout(btnSMMyStudents);
        btnSMMyStudents.setLayout(btnSMMyStudentsLayout);
        btnSMMyStudentsLayout.setHorizontalGroup(
            btnSMMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMMyStudentsLayout.setVerticalGroup(
            btnSMMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMMyStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMMyStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSMUploadMarks.setBackground(Functions.pnlBackgroundSideMenu());
        btnSMUploadMarks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMUploadMarks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMUploadMarksMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/percent.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Marks");

        javax.swing.GroupLayout btnSMUploadMarksLayout = new javax.swing.GroupLayout(btnSMUploadMarks);
        btnSMUploadMarks.setLayout(btnSMUploadMarksLayout);
        btnSMUploadMarksLayout.setHorizontalGroup(
            btnSMUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMUploadMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMUploadMarksLayout.setVerticalGroup(
            btnSMUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMUploadMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMUploadMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnSMAssessments.setBackground(Functions.pnlBackgroundSideMenu());
        btnSMAssessments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMAssessments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMAssessmentsMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/bookmark.png"))); // NOI18N

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
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMAssessmentsLayout.setVerticalGroup(
            btnSMAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSMTestTimeTable.setBackground(Functions.pnlBackgroundSideMenu());
        btnSMTestTimeTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSMTestTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMTestTimeTableMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/tests.png"))); // NOI18N

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
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMTestTimeTableLayout.setVerticalGroup(
            btnSMTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMTestTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMTestTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnSMExamTimeTable.setBackground(Functions.pnlBackgroundSideMenu());
        btnSMExamTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMExamTimeTableMouseClicked(evt);
            }
        });

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/billboard.png"))); // NOI18N

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
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMExamTimeTableLayout.setVerticalGroup(
            btnSMExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMExamTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56)
                    .addComponent(jLabel54))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnSMClassTimeTable.setBackground(Functions.pnlBackgroundSideMenu());
        btnSMClassTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSMClassTimeTableMouseClicked(evt);
            }
        });

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/lesson_copy.png"))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 204, 204));
        jLabel57.setText("Class TimeTable");

        javax.swing.GroupLayout btnSMClassTimeTableLayout = new javax.swing.GroupLayout(btnSMClassTimeTable);
        btnSMClassTimeTable.setLayout(btnSMClassTimeTableLayout);
        btnSMClassTimeTableLayout.setHorizontalGroup(
            btnSMClassTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMClassTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addGap(18, 18, 18)
                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSMClassTimeTableLayout.setVerticalGroup(
            btnSMClassTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSMClassTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSMClassTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel57)
                    .addComponent(jLabel55))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSMClassTimeTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSMExamTimeTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSMTestTimeTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSMAssessments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSMUploadMarks, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSMMyStudents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMMyStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMUploadMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMAssessments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMTestTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMExamTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSMClassTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSMAssessments, btnSMClassTimeTable, btnSMExamTimeTable, btnSMMyStudents, btnSMTestTimeTable, btnSMUploadMarks});

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
                .addComponent(jcbMSCampuses, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMSTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jpnMyStudents.setBackground(new java.awt.Color(204, 204, 204));

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
        btnClassList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnMyStudents, "card2");

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

        btnMarksheet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMarksheet.setForeground(new java.awt.Color(51, 51, 51));
        btnMarksheet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        btnMarksheet.setToolTipText("Marksheet");
        btnMarksheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarksheetActionPerformed(evt);
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
                "StudentNo", "IDNo", "Names", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
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
        if (tblMarks.getColumnModel().getColumnCount() > 0) {
            tblMarks.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblMarks.getColumnModel().getColumn(0).setMaxWidth(150);
            tblMarks.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblMarks.getColumnModel().getColumn(1).setMaxWidth(200);
            tblMarks.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("MARKS", jPanel4);

        jScrollPane10.setBorder(null);

        tblCaptureMarks.setAutoCreateRowSorter(true);
        tblCaptureMarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblCaptureMarks.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblCaptureMarks.setForeground(new java.awt.Color(51, 51, 51));
        tblCaptureMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StudentNo", "IDNo", "Names", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCaptureMarks.setGridColor(new java.awt.Color(204, 204, 204));
        tblCaptureMarks.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblCaptureMarks.setRowHeight(40);
        jScrollPane10.setViewportView(tblCaptureMarks);
        if (tblCaptureMarks.getColumnModel().getColumnCount() > 0) {
            tblCaptureMarks.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblCaptureMarks.getColumnModel().getColumn(0).setMaxWidth(150);
            tblCaptureMarks.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblCaptureMarks.getColumnModel().getColumn(1).setMaxWidth(200);
            tblCaptureMarks.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        btnCaptureMarks.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnCaptureMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/success.png"))); // NOI18N
        btnCaptureMarks.setText("SAVE ALL");
        btnCaptureMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureMarksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCaptureMarks)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCaptureMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("CAPTURE MARKS", jPanel5);

        javax.swing.GroupLayout jpnMarksLayout = new javax.swing.GroupLayout(jpnMarks);
        jpnMarks.setLayout(jpnMarksLayout);
        jpnMarksLayout.setHorizontalGroup(
            jpnMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jpnMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbGradeCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMarksheet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jpnMarksLayout.setVerticalGroup(
            jpnMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(btnMarksheet)
                    .addComponent(jcbGradeCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2))
        );

        jpnSlider.add(jpnMarks, "card3");

        jScrollPane7.setBorder(null);

        tblContinuousAssessment.setAutoCreateRowSorter(true);
        tblContinuousAssessment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblContinuousAssessment.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblContinuousAssessment.setForeground(new java.awt.Color(51, 51, 51));
        tblContinuousAssessment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student No", "IDNo", "Names", "ContASS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        if (tblContinuousAssessment.getColumnModel().getColumnCount() > 0) {
            tblContinuousAssessment.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblContinuousAssessment.getColumnModel().getColumn(0).setMaxWidth(150);
            tblContinuousAssessment.getColumnModel().getColumn(1).setPreferredWidth(190);
            tblContinuousAssessment.getColumnModel().getColumn(1).setMaxWidth(190);
            tblContinuousAssessment.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/order.png"))); // NOI18N
        btnRefresh.setToolTipText("Refresh to display records");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnContinuousAssemment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnContinuousAssemment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        btnContinuousAssemment.setToolTipText("Continuous assessment");
        btnContinuousAssemment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuousAssemmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnAssessmentsLayout = new javax.swing.GroupLayout(jpnAssessments);
        jpnAssessments.setLayout(jpnAssessmentsLayout);
        jpnAssessmentsLayout.setHorizontalGroup(
            jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                    .addGroup(jpnAssessmentsLayout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinuousAssemment)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpnAssessmentsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnContinuousAssemment, btnRefresh});

        jpnAssessmentsLayout.setVerticalGroup(
            jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAssessmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAssessmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinuousAssemment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnAssessmentsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnContinuousAssemment, btnRefresh});

        jpnSlider.add(jpnAssessments, "card4");

        btnTTRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/rules.png"))); // NOI18N
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
        if (tblTestTimeTable.getColumnModel().getColumnCount() > 0) {
            tblTestTimeTable.getColumnModel().getColumn(5).setHeaderValue("Venue");
        }

        btnTestTimeTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
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
                        .addGap(0, 701, Short.MAX_VALUE))
                    .addComponent(jSeparator5))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
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
                .addContainerGap(400, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Test Time Table", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
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
            .addGap(0, 905, Short.MAX_VALUE)
        );
        jpnExamTimeTableLayout.setVerticalGroup(
            jpnExamTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnExamTimeTable, "card6");

        javax.swing.GroupLayout jpnClassTimeTableLayout = new javax.swing.GroupLayout(jpnClassTimeTable);
        jpnClassTimeTable.setLayout(jpnClassTimeTableLayout);
        jpnClassTimeTableLayout.setHorizontalGroup(
            jpnClassTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
        );
        jpnClassTimeTableLayout.setVerticalGroup(
            jpnClassTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnClassTimeTable, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jpnControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSMMyStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMMyStudentsMouseClicked
        // TODO add your handling code here:
        btnSMMyStudentsPressed();
        jpnSlider.nextPanel(10, jpnMyStudents, jpnSlider.right);
    }//GEN-LAST:event_btnSMMyStudentsMouseClicked

    private void btnSMUploadMarksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMUploadMarksMouseClicked
        // TODO add your handling code here:
        btnSMUploadMarksPressed();
        jpnSlider.nextPanel(10, jpnMarks, jpnSlider.right);
    }//GEN-LAST:event_btnSMUploadMarksMouseClicked

    private void btnSMTestTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMTestTimeTableMouseClicked
        // TODO add your handling code here:
        btnSMTestTimeTablePressed();
        jpnSlider.nextPanel(10, jpnTestTimeTable, jpnSlider.right);
    }//GEN-LAST:event_btnSMTestTimeTableMouseClicked

    private void btnSMAssessmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMAssessmentsMouseClicked
        // TODO add your handling code here:
        btnSMAssessmentsPressed();
        jpnSlider.nextPanel(10, jpnAssessments, jpnSlider.right);
    }//GEN-LAST:event_btnSMAssessmentsMouseClicked

    private void btnSMExamTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMExamTimeTableMouseClicked
        // TODO add your handling code here:
        btnSMExamTimeTablePressed();
        jpnSlider.nextPanel(10, jpnExamTimeTable, jpnSlider.right);
    }//GEN-LAST:event_btnSMExamTimeTableMouseClicked

    private void tblStudentsInModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsInModuleMouseClicked
        // TODO add your handling code here:
        int row = tblStudentsInModule.getSelectedRow();
        int personId;
        personId = Integer.parseInt((tblStudentsInModule.getModel().getValueAt(row, 0).toString()));
        
        studentModuleGrades = new StudentModuleGradesPnl(personId, selectedTerm.getTermId(), selectedModule.getModuleCode());
        studentModuleGrades.display();
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
            String mark = (tblMarks.getModel().getValueAt(row, 3).toString());
            int studentNo = Integer.parseInt(tblMarks.getModel().getValueAt(row, 0).toString());

            String student = "Student No:"+studentNo+"\n"+
                             "Names: "+tblMarks.getModel().getValueAt(row, 2).toString();
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
                    tblMarks.getModel().setValueAt(newMark, row, 3);
                    alert.notify("Grade updated!", 1);
                }else{
                    alert.notify("No changes made.", 0);
                }
            }
        }
    }//GEN-LAST:event_tblMarksMouseClicked

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
                fillMarks(list);
                fillCaptureMarks(list);
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

    private void btnSMClassTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSMClassTimeTableMouseClicked
        // TODO add your handling code here:
        btnSMClassTimeTablePressed();
        jpnSlider.nextPanel(10, jpnClassTimeTable, jpnSlider.right);
    }//GEN-LAST:event_btnSMClassTimeTableMouseClicked

    private void btnStudentAssessmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentAssessmentActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnStudentAssessmentActionPerformed

    private void btnCaptureMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaptureMarksActionPerformed
        // TODO add your handling code here:
        if(tblModelCaptureMarks.getRowCount() > 0){
            int row = 0;
            List<HashMap<String,Object>> marks = new ArrayList<>();
            while(row < tblModelCaptureMarks.getRowCount()){
                int studentNo =  Integer.parseInt(tblModelCaptureMarks.getValueAt(row, 0).toString());
                String grade =  tblModelCaptureMarks.getValueAt(row, 3).toString();
                HashMap<String,Object> mark = new HashMap<>();
                mark.put("personId", studentNo);
                mark.put("mark", grade);
                marks.add(mark);
                row++;
            }
            int uploaded = gradetBoImpl.UploadMarks(marks, 
                                            selectedCampus.getCampusCode(), 
                                            selectedModule.getModuleCode(), 
                                            selectedTerm.getTermId(), 
                                            selectedGradeCategory.getGradeId());
            if(uploaded > 0){
               alert.notify("Marks saved.", 1);
            }
        }else{
            alert.notify("There are no marks to save.", 0);
        }
    }//GEN-LAST:event_btnCaptureMarksActionPerformed

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
                new GradesFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JButton btnCaptureMarks;
    private javax.swing.JButton btnClassList;
    private javax.swing.JButton btnContinuousAssemment;
    private javax.swing.JButton btnMarksheet;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel btnSMAssessments;
    private javax.swing.JPanel btnSMClassTimeTable;
    private javax.swing.JPanel btnSMExamTimeTable;
    private javax.swing.JPanel btnSMMyStudents;
    private javax.swing.JPanel btnSMTestTimeTable;
    private javax.swing.JPanel btnSMUploadMarks;
    private javax.swing.JButton btnStudentAssessment;
    private javax.swing.JButton btnTTRefresh;
    private javax.swing.JButton btnTestTimeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox<String> jcbGradeCategory;
    private javax.swing.JComboBox<String> jcbMSCampuses;
    private javax.swing.JComboBox<String> jcbMSModules;
    private javax.swing.JComboBox<String> jcbMSTerms;
    private javax.swing.JPanel jpnAssessments;
    private javax.swing.JPanel jpnClassTimeTable;
    private javax.swing.JPanel jpnControls;
    private javax.swing.JPanel jpnExamTimeTable;
    private javax.swing.JPanel jpnMarks;
    private javax.swing.JPanel jpnMyStudents;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnStudentModuleGrades;
    private javax.swing.JPanel jpnTestTimeTable;
    private javax.swing.JLabel lblConsinuousAssessment;
    private javax.swing.JTable tblCaptureMarks;
    private javax.swing.JTable tblContinuousAssessment;
    private javax.swing.JTable tblMarks;
    private javax.swing.JTable tblModuleGrades;
    private javax.swing.JTable tblStudentsInModule;
    private javax.swing.JTable tblTestTimeTable;
    private com.molorane.college.view.Controls.TitlePnl title;
    // End of variables declaration//GEN-END:variables
}
