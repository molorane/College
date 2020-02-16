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
import com.nanoware.bll.impl.ModuleBoImpl;
import com.nanoware.bll.impl.PersonBoImpl;
import com.nanoware.bll.impl.PositionBoImpl;
import com.nanoware.bll.impl.QualificationBoImpl;
import com.nanoware.bll.impl.RaceBoImpl;
import com.nanoware.bll.impl.RelationshipBoImpl;
import com.nanoware.bll.impl.ReligionBoImpl;
import com.nanoware.bll.impl.StudyTypeBoImpl;
import com.nanoware.bll.impl.TitleBoImpl;
import com.nanoware.model.Country;
import com.nanoware.model.Course;
import com.nanoware.model.Education;
import com.nanoware.model.Employment;
import com.nanoware.model.Enrolment;
import com.nanoware.model.Gender;
import com.nanoware.model.Gurdian;
import com.nanoware.model.Institution;
import com.nanoware.model.Language;
import com.nanoware.model.Module;
import com.nanoware.model.Person;
import com.nanoware.model.Position;
import com.nanoware.model.Qualification;
import com.nanoware.model.Race;
import com.nanoware.model.Relationship;
import com.nanoware.model.Religion;
import com.nanoware.model.Title;
import com.nanoware.model.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Mothusi Molorane
 */
public class Admission extends javax.swing.JFrame {
    
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
    
    private DefaultTableModel tblModelEduHistory;
    private int EDUID;
    private int EDUROWSELECTED;
    private boolean jcbDepartmentClicked = false;
    
    private DefaultTableModel tblModelDepartmentCouses;
    private DefaultTableModel tblModelCourseModules;
    private DefaultTableModel tblModelSearchList;
    private DefaultTableModel tblModelPersonEnrolments;
    
    private String selectedCourseCode;
    private String selectedCourse;
    
    
    private long personId;
    private final User loggedInUser;

    /**
     * Creates new form Home
     */
    public Admission() {
        super("Admissions - "+Functions.appName());
        initComponents();       
        
        Functions.setAppIcon(Admission.this);
        
        loggedInUser = Functions.GetUser(); //LoginSession.getSessionUser();
        
        raceImpl.fillComboBoxRace(jcbRace);
        genderImpl.fillComboBoxGender(jcbGender);
        nationalityImpl.fillComboBoxCountry(jcbNationality);
        titleImpl.fillComboBoxTitle(jcbTitle);
        languageImpl.fillComboBoxLanguage(jcbLanguage);
        religionImpl.fillComboBoxReligion(jcbReligion);
        relationshipImpl.fillComboBoxRelationship(jcbRelationGurdian);
        positionImpl.fillComboBoxPosition(jcbCompanyPosition);
        qualificationImpl.fillComboBoxRace(jcbEduQualification);
        institutionImpl.fillComboBoxInstitution(jcbEduInstitution);
        
        // initialize table models
        tblModelEduHistory = (DefaultTableModel) jtbEduHistory.getModel();
        EDUID = 0;
        EDUROWSELECTED = 0;
        
        tblModelPersonEnrolments = (DefaultTableModel)tblPersonEnrolments.getModel();
        
        tblModelSearchList = (DefaultTableModel)tblSearchList.getModel();
        
        Functions.setLoggedInUser(lblHomeLoggedIn,loggedInUser);
        
        setLocationRelativeTo(null);
    }
    
    void btnAdminHomePressed(){
        setColor(btnAdminHome);
        resetColor(btnAdminProfileRegistration);
        resetColor(btnAdminEducationHistory);
        resetColor(btnAdminCourseRegistration);
        resetColor(btnStudentFiles);
        
        jpnAdmisionsHome.setVisible(true);
        jpnProfileRegistration.setVisible(false);
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
       
       jpnAdmisionsHome.setVisible(false);
       jpnProfileRegistration.setVisible(true);
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
       
       jpnAdmisionsHome.setVisible(false);
       jpnProfileRegistration.setVisible(false);
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
       
       jpnAdmisionsHome.setVisible(false);
       jpnProfileRegistration.setVisible(false);
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
       
       jpnAdmisionsHome.setVisible(false);
       jpnProfileRegistration.setVisible(false);
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
    
    private void fillUserSession(long personId){
        try{
            
            Person p = personImpl.GetPerson(personId);
            Gurdian gd = gurdianImpl.GetGurdian(personId);
            Employment em = employmentImpl.GetEmployment(personId);

            if(p!=null){
                txtFirstName.setText(p.getFirstName());
                txtLastName.setText(p.getLastName());
                txtOtherName.setText(p.getOtherName());
                txtIdNo.setText(p.getIdno());
                jdpDob.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(p.getDob()));
                for(int i=0;i<jcbNationality.getItemCount();i++){
                    Object obj = (Object)jcbNationality.getItemAt(i);
                    Country c = (Country)obj;
                    if(p.getNationalityId() == c.getCountryId()){
                        jcbNationality.setSelectedIndex(i);
                    }
                }
                for(int i=0;i<jcbRace.getItemCount();i++){
                    Object obj = (Object)jcbRace.getItemAt(i);
                    Race r = (Race)obj;
                    if(p.getRaceId()== r.getRaceId()){
                        jcbRace.setSelectedIndex(i);
                    }
                }
                for(int i=0;i<jcbGender.getItemCount();i++){
                    Object obj = (Object)jcbGender.getItemAt(i);
                    Gender g = (Gender)obj;
                    if(p.getGenderId()== g.getGenderId()){
                        jcbGender.setSelectedIndex(i);
                    }
                }
                for(int i=0;i<jcbReligion.getItemCount();i++){
                    Object obj = (Object)jcbReligion.getItemAt(i);
                    Religion rl = (Religion)obj;
                    if(p.getReligionId()== rl.getReligionId()){
                        jcbReligion.setSelectedIndex(i);
                    }
                }
                for(int i=0;i<jcbTitle.getItemCount();i++){
                    Object obj = (Object)jcbTitle.getItemAt(i);
                    Title t = (Title)obj;
                    if(p.getTitleId() == t.getTitleId()){
                        jcbTitle.setSelectedIndex(i);
                    }
                }
                for(int i=0;i<jcbLanguage.getItemCount();i++){
                    Object obj = (Object)jcbLanguage.getItemAt(i);
                    Language ln = (Language)obj;
                    if(p.getLanguageId() == ln.getLanguageId()){
                        jcbLanguage.setSelectedIndex(i);
                    }
                }

                txtPhone.setText(p.getCellphone());
                txtTelephone.setText(p.getTelephone());
                txtFax.setText(p.getFax());
                txtEmail.setText(p.getEmail());
                txtAddress.setText(p.getAddress());
                txtPostalCode.setText(p.getPostalCode());

                if(gd!=null){
                    txtFirstNameGurdian.setText(gd.getFirstName());
                    txtLastNameGurdian.setText(gd.getLastName());
                    txtTelephoneGurdian.setText(gd.getTelephone());
                    txtCellphoneGurdian.setText(gd.getCellphone());
                    txtAddressGurdian.setText(gd.getAddress());
                    txtPostalCodeGurdian.setText(gd.getPostalCode());

                    for(int i=0;i<jcbRelationGurdian.getItemCount();i++){
                        Object obj = (Object)jcbRelationGurdian.getItemAt(i);
                        Relationship t = (Relationship)obj;
                        if(gd.getRelationshipId() == t.getRelationshipId()){
                            jcbRelationGurdian.setSelectedIndex(i);
                        }
                    }
                }
                if(em!=null){
                    txtCompanyName.setText(em.getCompany());
                    txtCompanyAddress.setText(em.getAddress());
                    txtCompanyPostalcode.setText(em.getPostalCode());
                    txtContactPerson.setText(em.getContactPerson());
                    txtCompanyTelephone.setText(em.getTelephone());
                    txtCompanyCellPhone.setText(em.getCellphone());
                    txtCompanyFax.setText(em.getFax());

                    for(int i=0;i<jcbCompanyPosition.getItemCount();i++){
                        Object obj = (Object)jcbCompanyPosition.getItemAt(i);
                        Position pos = (Position)obj;
                        if(em.getPositionId()== pos.getPositionId()){
                            jcbCompanyPosition.setSelectedIndex(i);
                        }
                    }
                }
            }else{
               // clear info fields
               jdpDob.setDate(null);
               txtFirstName.setText(null);
               txtLastName.setText("");
               txtOtherName.setText("");
               txtIdNo.setText("");
               txtPhone.setText("");
               txtTelephone.setText("");
               txtFax.setText("");
               txtEmail.setText("");
               txtAddress.setText("");
               txtPostalCode.setText("");

               // clear gurdain fields
               txtFirstNameGurdian.setText("");
               txtLastNameGurdian.setText("");
               txtTelephoneGurdian.setText("");
               txtCellphoneGurdian.setText("");
               txtAddressGurdian.setText("");
               txtPostalCodeGurdian.setText("");

               // clear company fields
               txtCompanyName.setText("");
               txtCompanyAddress.setText("");
               txtCompanyPostalcode.setText("");
               txtContactPerson.setText("");
               txtCompanyTelephone.setText("");
               txtCompanyCellPhone.setText("");
               txtCompanyFax.setText("");

               // clear company fields
               txtEduYearObtained.setText("");
               txtEduSkills.setText("");
               txtEduDetails.setText("");
            }
           fillEduHistory(personId);
           fillPersonEnrolments(personId);            
        }catch(SQLException | ParseException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
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
        btngTyoe = new javax.swing.ButtonGroup();
        jpnAdmissions = new javax.swing.JPanel();
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
        jLabel15 = new javax.swing.JLabel();
        lblUserSession = new javax.swing.JLabel();
        lblHomeLoggedIn = new javax.swing.JLabel();
        jpnContainer = new javax.swing.JPanel();
        jpnAdmisionsHome = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSearchList = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jrbNewStudent = new javax.swing.JRadioButton();
        jrbExistingStudent = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtStudentNoSession = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        txtSearchStudentSession = new javax.swing.JTextField();
        jpnProfileRegistration = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtFirstNameGurdian = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtLastNameGurdian = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtTelephoneGurdian = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jcbRelationGurdian = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txtCellphoneGurdian = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtAddressGurdian = new javax.swing.JTextField();
        txtPostalCodeGurdian = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtCompanyTelephone = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtContactPerson = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtCompanyName = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jcbCompanyPosition = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        txtCompanyCellPhone = new javax.swing.JTextField();
        txtCompanyPostalcode = new javax.swing.JTextField();
        txtCompanyFax = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtCompanyAddress = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jpnEducationHistory = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbEduHistory = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtEduHistoryStudentNo = new javax.swing.JTextField();
        txtEduYearObtained = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jcbEduInstitution = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jcbEduQualification = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtEduSkills = new javax.swing.JTextField();
        txtEduDetails = new javax.swing.JTextField();
        btnNewInstitution = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();
        jtbnSaveEducationHistory = new javax.swing.JButton();
        jpnRegisteredCourses = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPersonEnrolments = new javax.swing.JTable();
        jpnStudentFiles = new javax.swing.JPanel();

        jLabel21.setText("jLabel21");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(122, 72, 221));
        setLocationByPlatform(true);
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

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/profile_add.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Profile Registration");

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

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/courses.png"))); // NOI18N

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
        jLabel12.setText("ADMISSIONS");

        btnAdminEducationHistory.setBackground(new java.awt.Color(54, 33, 89));
        btnAdminEducationHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminEducationHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminEducationHistoryMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/graduation.png"))); // NOI18N

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

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/file_explorer.png"))); // NOI18N

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
                    .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdminEducationHistory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdminProfileRegistration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdminHome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdminCourseRegistration, btnAdminEducationHistory, btnAdminHome, btnAdminProfileRegistration, btnStudentFiles});

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

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdminCourseRegistration, btnAdminEducationHistory, btnAdminHome, btnAdminProfileRegistration, btnStudentFiles});

        jpnTop.setBackground(Functions.pnlBackgroundTop());
        jpnTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Student:");

        lblUserSession.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUserSession.setForeground(new java.awt.Color(204, 204, 204));
        lblUserSession.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lblHomeLoggedIn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblHomeLoggedIn.setForeground(new java.awt.Color(204, 204, 204));
        lblHomeLoggedIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHomeLoggedIn.setText("ADMIN: Mohlomi M");

        javax.swing.GroupLayout jpnTopLayout = new javax.swing.GroupLayout(jpnTop);
        jpnTop.setLayout(jpnTopLayout);
        jpnTopLayout.setHorizontalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTopLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
                    .addGroup(jpnTopLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnTopLayout.setVerticalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTopLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );

        btngTyoe.add(jrbNewStudent);
        jrbNewStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbNewStudent.setText("New Student");
        jrbNewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNewStudentActionPerformed(evt);
            }
        });

        btngTyoe.add(jrbExistingStudent);
        jrbExistingStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbExistingStudent.setSelected(true);
        jrbExistingStudent.setText("Existing Student");
        jrbExistingStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbExistingStudentActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jrbNewStudent)
                        .addGap(18, 18, 18)
                        .addComponent(jrbExistingStudent))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel48))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStudentNoSession)
                            .addComponent(txtSearchStudentSession, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))))
                .addContainerGap(666, Short.MAX_VALUE))
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbNewStudent)
                    .addComponent(jrbExistingStudent))
                .addGap(43, 43, 43)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStudentNoSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtSearchStudentSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(807, Short.MAX_VALUE)))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(144, 144, 144)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(731, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpnAdmisionsHomeLayout = new javax.swing.GroupLayout(jpnAdmisionsHome);
        jpnAdmisionsHome.setLayout(jpnAdmisionsHomeLayout);
        jpnAdmisionsHomeLayout.setHorizontalGroup(
            jpnAdmisionsHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnAdmisionsHomeLayout.setVerticalGroup(
            jpnAdmisionsHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnAdmisionsHome, "card2");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Details"));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Student No:");

        txtStudentID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtStudentID.setEnabled(false);
        txtStudentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStudentIDKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Firstname:");

        txtFirstName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Identity No:");

        txtIdNo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Lastname:");

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Nationality:");

        jcbNationality.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbNationality.setForeground(new java.awt.Color(102, 102, 102));
        jcbNationality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Othername:");

        txtOtherName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("DOB:");

        jdpDob.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Race:");

        jcbRace.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbRace.setForeground(new java.awt.Color(102, 102, 102));
        jcbRace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Gender:");

        jcbGender.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbGender.setForeground(new java.awt.Color(102, 102, 102));
        jcbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Religion:");

        jcbReligion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbReligion.setForeground(new java.awt.Color(102, 102, 102));
        jcbReligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Title:");

        jcbTitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbTitle.setForeground(new java.awt.Color(102, 102, 102));
        jcbTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Language:");

        jcbLanguage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbLanguage.setForeground(new java.awt.Color(102, 102, 102));
        jcbLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Phone:");

        txtPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Telephone:");

        txtTelephone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Fax:");

        txtFax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel42.setForeground(new java.awt.Color(102, 102, 102));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Address:");

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Details:");

        txtPostalCode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtIdNo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdpDob, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFirstName)
                    .addComponent(txtLastName)
                    .addComponent(txtOtherName)
                    .addComponent(jcbRace, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbReligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(txtPostalCode))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(txtPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(txtIdNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel22))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24)
                                .addComponent(jdpDob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jcbRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addComponent(jcbReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jcbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(jcbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAddress, txtEmail, txtFax, txtPhone, txtPostalCode, txtTelephone});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Gurdian"));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("FIrstname:");

        txtFirstNameGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Lastname:");

        txtLastNameGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Relationship:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Tel (W):");

        txtTelephoneGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Postal Code:");

        jcbRelationGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbRelationGurdian.setForeground(new java.awt.Color(102, 102, 102));
        jcbRelationGurdian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Cellphone:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Address:");

        txtAddressGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtPostalCodeGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPostalCodeGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jcbRelationGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAddressGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(txtFirstNameGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(15, 15, 15)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTelephoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCellphoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLastNameGurdian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtFirstNameGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtLastNameGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(txtTelephoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbRelationGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtCellphoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtAddressGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtPostalCodeGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAddressGurdian, txtPostalCodeGurdian});

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Work History"));

        txtCompanyTelephone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Company:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Position:");

        txtContactPerson.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Contact Person:");

        txtCompanyName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Telephone:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Postal Code:");

        jcbCompanyPosition.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbCompanyPosition.setForeground(new java.awt.Color(102, 102, 102));
        jcbCompanyPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Cellphone:");

        txtCompanyCellPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtCompanyPostalcode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtCompanyFax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Fax:");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("Address:");

        txtCompanyAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 51, 51));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setIconTextGap(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCompanyPostalcode, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCompanyTelephone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCompanyCellPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(txtContactPerson, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(txtCompanyFax, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbCompanyPosition, 0, 193, Short.MAX_VALUE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCompanyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbCompanyPosition, txtCompanyCellPhone, txtCompanyName, txtCompanyTelephone, txtContactPerson});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(jLabel34)
                                .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcbCompanyPosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCompanyTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37)
                                    .addComponent(txtCompanyPostalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(txtContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel44)
                                    .addComponent(txtCompanyCellPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCompanyFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45)))))
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtCompanyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcbCompanyPosition, txtCompanyCellPhone, txtCompanyName, txtCompanyTelephone, txtContactPerson});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnProfileRegistrationLayout = new javax.swing.GroupLayout(jpnProfileRegistration);
        jpnProfileRegistration.setLayout(jpnProfileRegistrationLayout);
        jpnProfileRegistrationLayout.setHorizontalGroup(
            jpnProfileRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnProfileRegistrationLayout.setVerticalGroup(
            jpnProfileRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnProfileRegistration, "card3");

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane4.setBorder(null);

        jtbEduHistory.setAutoCreateRowSorter(true);
        jtbEduHistory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtbEduHistory.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtbEduHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REF #", "INSTITUTION", "QUALIFICATION", "YEAR OBTAINED", "SKILLS", "DETAILS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbEduHistory.setGridColor(new java.awt.Color(204, 204, 204));
        jtbEduHistory.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtbEduHistory.setRowHeight(40);
        jtbEduHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbEduHistoryMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtbEduHistory);
        if (jtbEduHistory.getColumnModel().getColumnCount() > 0) {
            jtbEduHistory.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtbEduHistory.getColumnModel().getColumn(1).setPreferredWidth(20);
            jtbEduHistory.getColumnModel().getColumn(2).setPreferredWidth(10);
            jtbEduHistory.getColumnModel().getColumn(3).setPreferredWidth(10);
            jtbEduHistory.getColumnModel().getColumn(4).setPreferredWidth(10);
            jtbEduHistory.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Education"));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("Student No:");

        txtEduHistoryStudentNo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEduHistoryStudentNo.setEnabled(false);

        txtEduYearObtained.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Institution:");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Qualification:");

        jcbEduInstitution.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbEduInstitution.setForeground(new java.awt.Color(102, 102, 102));
        jcbEduInstitution.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(102, 102, 102));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("Year obtained:");

        jcbEduQualification.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbEduQualification.setForeground(new java.awt.Color(102, 102, 102));
        jcbEduQualification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 102, 102));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Skills:");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("Details:");

        txtEduSkills.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtEduDetails.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnNewInstitution.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/add.png"))); // NOI18N
        btnNewInstitution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewInstitutionActionPerformed(evt);
            }
        });

        jbtnDelete.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jbtnDelete.setForeground(new java.awt.Color(51, 51, 51));
        jbtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.gif"))); // NOI18N
        jbtnDelete.setText("Delete");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        jtbnSaveEducationHistory.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jtbnSaveEducationHistory.setForeground(new java.awt.Color(51, 51, 51));
        jtbnSaveEducationHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jtbnSaveEducationHistory.setText("Save");
        jtbnSaveEducationHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnSaveEducationHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbEduInstitution, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewInstitution, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEduHistoryStudentNo))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEduDetails)
                            .addComponent(jcbEduQualification, 0, 384, Short.MAX_VALUE)
                            .addComponent(txtEduSkills)
                            .addComponent(txtEduYearObtained, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtbnSaveEducationHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addGap(248, 248, 248))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbEduQualification, txtEduDetails, txtEduHistoryStudentNo, txtEduSkills});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txtEduHistoryStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel49)
                        .addComponent(jcbEduInstitution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNewInstitution, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbEduQualification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(txtEduYearObtained, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jbtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbnSaveEducationHistory))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(txtEduSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEduDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcbEduInstitution, jcbEduQualification, txtEduDetails, txtEduHistoryStudentNo, txtEduSkills, txtEduYearObtained});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnDelete, jtbnSaveEducationHistory});

        javax.swing.GroupLayout jpnEducationHistoryLayout = new javax.swing.GroupLayout(jpnEducationHistory);
        jpnEducationHistory.setLayout(jpnEducationHistoryLayout);
        jpnEducationHistoryLayout.setHorizontalGroup(
            jpnEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnEducationHistoryLayout.setVerticalGroup(
            jpnEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEducationHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnContainer.add(jpnEducationHistory, "card3");

        jScrollPane3.setBorder(null);

        tblPersonEnrolments.setAutoCreateRowSorter(true);
        tblPersonEnrolments.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblPersonEnrolments.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblPersonEnrolments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseCode", "Course", "Term", "Study Type", "Campus", "Module Code", "Module"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPersonEnrolments.setGridColor(new java.awt.Color(204, 204, 204));
        tblPersonEnrolments.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblPersonEnrolments.setRowHeight(40);
        jScrollPane3.setViewportView(tblPersonEnrolments);
        if (tblPersonEnrolments.getColumnModel().getColumnCount() > 0) {
            tblPersonEnrolments.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblPersonEnrolments.getColumnModel().getColumn(0).setMaxWidth(150);
            tblPersonEnrolments.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblPersonEnrolments.getColumnModel().getColumn(1).setMaxWidth(300);
            tblPersonEnrolments.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblPersonEnrolments.getColumnModel().getColumn(2).setMaxWidth(200);
            tblPersonEnrolments.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblPersonEnrolments.getColumnModel().getColumn(3).setMaxWidth(100);
            tblPersonEnrolments.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblPersonEnrolments.getColumnModel().getColumn(4).setMaxWidth(100);
            tblPersonEnrolments.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblPersonEnrolments.getColumnModel().getColumn(5).setMaxWidth(150);
            tblPersonEnrolments.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jpnRegisteredCoursesLayout = new javax.swing.GroupLayout(jpnRegisteredCourses);
        jpnRegisteredCourses.setLayout(jpnRegisteredCoursesLayout);
        jpnRegisteredCoursesLayout.setHorizontalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        jpnRegisteredCoursesLayout.setVerticalGroup(
            jpnRegisteredCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnRegisteredCourses, "card3");

        javax.swing.GroupLayout jpnStudentFilesLayout = new javax.swing.GroupLayout(jpnStudentFiles);
        jpnStudentFiles.setLayout(jpnStudentFilesLayout);
        jpnStudentFilesLayout.setHorizontalGroup(
            jpnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        jpnStudentFilesLayout.setVerticalGroup(
            jpnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 866, Short.MAX_VALUE)
        );

        jpnContainer.add(jpnStudentFiles, "card6");

        javax.swing.GroupLayout jpnAdmissionsLayout = new javax.swing.GroupLayout(jpnAdmissions);
        jpnAdmissions.setLayout(jpnAdmissionsLayout);
        jpnAdmissionsLayout.setHorizontalGroup(
            jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAdmissionsLayout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
                    .addComponent(jpnTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpnAdmissionsLayout.setVerticalGroup(
            jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAdmissionsLayout.createSequentialGroup()
                .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE))
            .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jpnAdmissions, "card2");

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

    private void btnAdminEducationHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminEducationHistoryMouseClicked
        // TODO add your handling code here:
        btnAdminEducationHistoryPressed();
    }//GEN-LAST:event_btnAdminEducationHistoryMouseClicked

    private void btnAdminCourseRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminCourseRegistrationMouseClicked
        // TODO add your handling code here:
        btnAdminCourseRegistrationPressed();
    }//GEN-LAST:event_btnAdminCourseRegistrationMouseClicked

    private void btnNewInstitutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewInstitutionActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this, "Please enter new institution?","Institution",JOptionPane.QUESTION_MESSAGE);
//        if(input == null){
//            Functions.errorMessage("Cancel button clicked.");
//        }
        if(input != null){
            institutionImpl.AddInstitution(input);
            institutionImpl.fillComboBoxInstitution(jcbEduInstitution);
            Functions.successMessage("Institution added successfully.");
        }
    }//GEN-LAST:event_btnNewInstitutionActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        // TODO add your handling code here:
        if(EDUID > 0){
            educationImpl.RemoveEducation(EDUID);
            tblModelEduHistory.removeRow(EDUROWSELECTED);
            EDUID = 0;
            EDUROWSELECTED = 0;
        }else{
            Functions.errorMessage("Before deleting, specify the record to delete on the table.");
        }  
    }//GEN-LAST:event_jbtnDeleteActionPerformed

    private void jtbnSaveEducationHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnSaveEducationHistoryActionPerformed
        // TODO add your handling code here:
        int saved = 0;
        try{
            personId = Long.parseLong(txtEduHistoryStudentNo.getText());
            Education education = new Education();
            education.setPersonId(personId);
            Object objInt = (Object)jcbEduInstitution.getSelectedItem();
            Institution ins = (Institution)objInt;
            education.setInstitutionId(ins.getInstitutionId());
            Object objQual = (Object)jcbEduQualification.getSelectedItem();
            Qualification qual = (Qualification)objQual;
            education.setQualificationId(qual.getQualificationId());
            education.setYearObtained(Integer.parseInt(txtEduYearObtained.getText()));
            education.setSkills(txtEduSkills.getText());
            education.setDetails(txtEduDetails.getText());
            if(EDUID > 0){
                education.setEduId(EDUID);
                saved = educationImpl.EditEducation(education);
            }else{
                saved = educationImpl.AddEducation(education);
            }
            
            if(saved > 0){
                Functions.successMessage("Success!");
                fillEduHistory(personId);
                EDUID = 0;
            }
        }catch(NumberFormatException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jtbnSaveEducationHistoryActionPerformed

    private void txtStudentIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIDKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
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
            
            Gurdian gurdian = new Gurdian();
            gurdian.setPersonId(personId);
            gurdian.setFirstName(txtFirstNameGurdian.getText());
            gurdian.setLastName(txtLastNameGurdian.getText());
            gurdian.setTelephone(txtTelephoneGurdian.getText());
            gurdian.setCellphone(txtCellphoneGurdian.getText());
            gurdian.setAddress(txtAddressGurdian.getText());
            gurdian.setPostalCode(txtPostalCodeGurdian.getText());
            Object objRel = (Object)jcbRelationGurdian.getSelectedItem();
            Relationship relation = (Relationship)objRel;
            gurdian.setRelationshipId(relation.getRelationshipId());
            
            Employment employment = new Employment();
            employment.setPersonId(personId);
            employment.setCompany(txtCompanyName.getText());
            Object objPos = (Object)jcbCompanyPosition.getSelectedItem();
            Position pos = (Position)objPos;
            employment.setPositionId(pos.getPositionId());
            employment.setAddress(txtAddressGurdian.getText());
            employment.setPostalCode(txtCompanyPostalcode.getText());
            employment.setContactPerson(txtContactPerson.getText());
            employment.setTelephone(txtCompanyTelephone.getText());
            employment.setCellphone(txtCompanyCellPhone.getText());
            employment.setFax(txtCompanyFax.getText());           
            
            int insertedPerson = personImpl.AddPerson(p);
            int insertedGurdian = gurdianImpl.AddGurdian(gurdian);
            int insertedEmployment = employmentImpl.AddEmployment(employment);            
            
            if(insertedPerson > 0){
                Functions.successMessage("Student with student details :"+p.getPersonId()+","+p.getFirstName()+" saved.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jrbExistingStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbExistingStudentActionPerformed
        // TODO add your handling code here:
        txtStudentNoSession.setText("");
        txtStudentNoSession.setEnabled(true);
        
        txtSearchStudentSession.setText(null);
        txtSearchStudentSession.setEditable(true);
        
        txtStudentID.setText("");
        txtEduHistoryStudentNo.setText("");
    }//GEN-LAST:event_jrbExistingStudentActionPerformed

    private void txtStudentNoSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentNoSessionKeyReleased
        // TODO add your handling code here:      
        if(txtStudentNoSession.getText().length() >= 6){
            try {
                personId = Long.parseLong(txtStudentNoSession.getText());
                Person p = personImpl.GetPerson(personId);
                
                if(p!=null){
                    lblUserSession.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
                    txtStudentID.setText(p.getPersonId()+""); 
                    txtEduHistoryStudentNo.setText(p.getPersonId()+""); 
                    fillUserSession(personId);
                }else{
                   lblUserSession.setText("");
                   txtStudentID.setText(""); 
                   txtEduHistoryStudentNo.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Admission.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtStudentNoSessionKeyReleased

    private void jtbEduHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbEduHistoryMouseClicked
        // TODO add your handling code here:
        int row = jtbEduHistory.getSelectedRow();
        EDUROWSELECTED = row;
        int eduId = Integer.parseInt((jtbEduHistory.getModel().getValueAt(row, 0).toString()));
        EDUID = eduId;
        Education ed = educationImpl.GetEducation(eduId);

        if(ed!=null){
            for(int i=0;i<jcbEduInstitution.getItemCount();i++){
                Object obj = (Object)jcbEduInstitution.getItemAt(i);
                Institution edu = (Institution)obj;
                if(ed.getInstitutionId()== edu.getInstitutionId()){
                    jcbEduInstitution.setSelectedIndex(i);
                }
            }

            for(int i=0;i<jcbEduQualification.getItemCount();i++){
                Object obj = (Object)jcbEduQualification.getItemAt(i);
                Qualification qual = (Qualification)obj;
                if(ed.getQualificationId()== qual.getQualificationId()){
                    jcbEduQualification.setSelectedIndex(i);
                }
            }
            txtEduYearObtained.setText(ed.getYearObtained()+"");
            txtEduSkills.setText(ed.getSkills());
            txtEduDetails.setText(ed.getDetails());
        }
    }//GEN-LAST:event_jtbEduHistoryMouseClicked

    private void jrbNewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNewStudentActionPerformed
        try {
            txtStudentNoSession.setText(personImpl.GetNewStudentNo()+"");
            txtStudentNoSession.setEnabled(false);
            
            txtStudentID.setText(personImpl.GetNewStudentNo()+"");
            txtEduHistoryStudentNo.setText(personImpl.GetNewStudentNo()+"");
            lblUserSession.setText("");
            txtSearchStudentSession.setText(null);
            txtSearchStudentSession.setEditable(false);
            
            fillUserSession(personImpl.GetNewStudentNo());
        } catch (SQLException ex) {
            Logger.getLogger(Admission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jrbNewStudentActionPerformed

    private void txtSearchStudentSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentSessionKeyReleased
        // TODO add your handling code here:        
        fillSearchStudents(txtSearchStudentSession.getText());     
    }//GEN-LAST:event_txtSearchStudentSessionKeyReleased

    private void btnStudentFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentFilesMouseClicked
        // TODO add your handling code here:
        btnStudentFilesPressed();
    }//GEN-LAST:event_btnStudentFilesMouseClicked

    private void tblSearchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchListMouseClicked
        // TODO add your handling code here:
        int row = tblSearchList.getSelectedRow();
        personId = Long.parseLong((tblSearchList.getModel().getValueAt(row, 0).toString()));
        try {
            Person p = personImpl.GetPerson(personId);

            if(p!=null){
                lblUserSession.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
                txtStudentID.setText(p.getPersonId()+""); 
                txtEduHistoryStudentNo.setText(p.getPersonId()+""); 
                fillUserSession(personId);
            }else{
               lblUserSession.setText("");
               txtStudentID.setText(""); 
               txtEduHistoryStudentNo.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblSearchListMouseClicked
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">        
        Functions.setFormTheme();        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAdminCourseRegistration;
    private javax.swing.JPanel btnAdminEducationHistory;
    private javax.swing.JPanel btnAdminHome;
    private javax.swing.JPanel btnAdminProfileRegistration;
    private javax.swing.JButton btnNewInstitution;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel btnStudentFiles;
    private javax.swing.ButtonGroup btngTyoe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JComboBox<String> jcbCompanyPosition;
    private javax.swing.JComboBox<String> jcbEduInstitution;
    private javax.swing.JComboBox<String> jcbEduQualification;
    private javax.swing.JComboBox<String> jcbGender;
    private javax.swing.JComboBox<String> jcbLanguage;
    private javax.swing.JComboBox<String> jcbNationality;
    private javax.swing.JComboBox<String> jcbRace;
    private javax.swing.JComboBox<String> jcbRelationGurdian;
    private javax.swing.JComboBox<String> jcbReligion;
    private javax.swing.JComboBox<String> jcbTitle;
    private org.jdesktop.swingx.JXDatePicker jdpDob;
    private javax.swing.JPanel jpnAdmisionsHome;
    private javax.swing.JPanel jpnAdmissions;
    private javax.swing.JPanel jpnContainer;
    private javax.swing.JPanel jpnEducationHistory;
    private javax.swing.JPanel jpnProfileRegistration;
    private javax.swing.JPanel jpnRegisteredCourses;
    private javax.swing.JPanel jpnSideMenu;
    private javax.swing.JPanel jpnStudentFiles;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JRadioButton jrbExistingStudent;
    private javax.swing.JRadioButton jrbNewStudent;
    private javax.swing.JTable jtbEduHistory;
    private javax.swing.JButton jtbnSaveEducationHistory;
    private javax.swing.JLabel lblHomeLoggedIn;
    private javax.swing.JLabel lblUserSession;
    private javax.swing.JTable tblPersonEnrolments;
    private javax.swing.JTable tblSearchList;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddressGurdian;
    private javax.swing.JTextField txtCellphoneGurdian;
    private javax.swing.JTextField txtCompanyAddress;
    private javax.swing.JTextField txtCompanyCellPhone;
    private javax.swing.JTextField txtCompanyFax;
    private javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtCompanyPostalcode;
    private javax.swing.JTextField txtCompanyTelephone;
    private javax.swing.JTextField txtContactPerson;
    private javax.swing.JTextField txtEduDetails;
    private javax.swing.JTextField txtEduHistoryStudentNo;
    private javax.swing.JTextField txtEduSkills;
    private javax.swing.JTextField txtEduYearObtained;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtFirstNameGurdian;
    private javax.swing.JTextField txtIdNo;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLastNameGurdian;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPostalCode;
    private javax.swing.JTextField txtPostalCodeGurdian;
    private javax.swing.JTextField txtSearchStudentSession;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtStudentNoSession;
    private javax.swing.JTextField txtTelephone;
    private javax.swing.JTextField txtTelephoneGurdian;
    // End of variables declaration//GEN-END:variables
}
