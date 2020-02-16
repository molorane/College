/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.Controls;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.CountryBoImpl;
import com.molorane.college.bll.impl.CourseBoImpl;
import com.molorane.college.bll.impl.EducationBoImpl;
import com.molorane.college.bll.impl.EmploymentBoImpl;
import com.molorane.college.bll.impl.EnrolmentBoImpl;
import com.molorane.college.bll.impl.GenderBoImpl;
import com.molorane.college.bll.impl.GurdianBoImpl;
import com.molorane.college.bll.impl.InstitutionBoImpl;
import com.molorane.college.bll.impl.LanguageBoImpl;
import com.molorane.college.bll.impl.ModuleBoImpl;
import com.molorane.college.bll.impl.PFileBoImpl;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.PositionBoImpl;
import com.molorane.college.bll.impl.QualificationBoImpl;
import com.molorane.college.bll.impl.RaceBoImpl;
import com.molorane.college.bll.impl.RelationshipBoImpl;
import com.molorane.college.bll.impl.ReligionBoImpl;
import com.molorane.college.bll.impl.StudyTypeBoImpl;
import com.molorane.college.bll.impl.TitleBoImpl;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.custom.TableColumnHider;
import com.molorane.college.model.Country;
import com.molorane.college.model.Course;
import com.molorane.college.model.Education;
import com.molorane.college.model.Employment;
import com.molorane.college.model.Enrolment;
import com.molorane.college.model.Gender;
import com.molorane.college.model.Gurdian;
import com.molorane.college.model.Institution;
import com.molorane.college.model.Language;
import com.molorane.college.model.PFile;
import com.molorane.college.model.Person;
import com.molorane.college.model.Position;
import com.molorane.college.model.Qualification;
import com.molorane.college.model.Race;
import com.molorane.college.model.Relationship;
import com.molorane.college.model.Religion;
import com.molorane.college.model.Title;
import com.molorane.college.model.User;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class AdmissionFrm extends javax.swing.JFrame {
    
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
    private final PFileBoImpl pFileBoImpl = new PFileBoImpl();
    private DefaultTableModel tblModelEduHistory;
    private int EDUID;
    private int EDUROWSELECTED;
    private boolean jcbDepartmentClicked = false;
    
    private final DefaultTableModel tblModelSearchList;
    private final DefaultTableModel tblModelPersonEnrolments;
    private final DefaultTableModel tblModelPersonFiles;
    
    private String selectedCourseCode;
    private String selectedCourse;
    
    private ArrayList<PFile> uploads;
    
    
    private long personId;
    private final User loggedInUser;
    
    private Webcam webcam;
    private WebcamPanel webPanel;
    
    private TableColumnHider tblHider;

    /**
     * Creates new form Home
     */
    public AdmissionFrm() {
        super("Admissions - "+Functions.appName());
        initComponents();
        
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(AdmissionFrm.this);
        
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
        tblModelPersonFiles = (DefaultTableModel)tblPersonFiles.getModel();
        
        Functions.initForm(AdmissionFrm.this);
        
        tblHider = new TableColumnHider(tblPersonFiles);
        tblHider.hide("REF");
    }
    
    void btnAdminHomePressed(){
        Functions.setColor(btnAdminHome);
        Functions.resetColor(btnAdminPersonalInfo);
        Functions.resetColor(btnAdminEducationHistory);
        Functions.resetColor(btnAdminCourseRegistration);
        Functions.resetColor(btnStudentFiles);
    }
    
    void profileRegistrationPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.setColor(btnAdminPersonalInfo);
       Functions.resetColor(btnAdminEducationHistory);
       Functions.resetColor(btnAdminCourseRegistration);
       Functions.resetColor(btnStudentFiles);
    }
    
    void btnAdminEducationHistoryPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.resetColor(btnAdminPersonalInfo);
       Functions.setColor(btnAdminEducationHistory);
       Functions.resetColor(btnAdminCourseRegistration);
       Functions.resetColor(btnStudentFiles);
    }
    
    void btnAdminCourseRegistrationPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.resetColor(btnAdminPersonalInfo);
       Functions.resetColor(btnAdminEducationHistory);
       Functions.setColor(btnAdminCourseRegistration);
       Functions.resetColor(btnStudentFiles);
    }
    
    void btnStudentFilesPressed(){
       Functions.resetColor(btnAdminHome);
       Functions.resetColor(btnAdminPersonalInfo);
       Functions.resetColor(btnAdminEducationHistory);
       Functions.resetColor(btnAdminCourseRegistration);
       Functions.setColor(btnStudentFiles);
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
    
    private void GetPersonFiles(long personId){        
        uploads = pFileBoImpl.GetFiles(personId);
        tblModelPersonFiles.setRowCount(0);
        uploads.forEach((file) -> {
            tblModelPersonFiles.addRow(new Object[]{
               file.getUpId(), 
               file.getFileName()
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
                    txtPostalCodeGurdian.setText(gd.getAddress());
                    txtAddressGurdian.setText(gd.getPostalCode());

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
               txtPostalCodeGurdian.setText("");
               txtAddressGurdian.setText("");

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
           GetPersonFiles(personId);
        }catch(ParseException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }
    
    private void fillSearchStudents(String search){        
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
    }
    
    private void uploadFile(PFile file){
        file.setPersonId(personId);
        int row = pFileBoImpl.UploadFile(file);
        if(row > 0){
            GetPersonFiles(personId);
            alert.notify("File uploaded",1);
        }else{
            alert.notify("File not uploaded",0);
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
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAdminHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAdminPersonalInfo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAdminEducationHistory = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnAdminCourseRegistration = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnStudentFiles = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();
        jpnTop = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblUserSession = new javax.swing.JLabel();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
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
        jpnPersonalInfo = new javax.swing.JPanel();
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
        jSeparator6 = new javax.swing.JSeparator();
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
        txtPostalCodeGurdian = new javax.swing.JTextField();
        txtAddressGurdian = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
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
        jSeparator8 = new javax.swing.JSeparator();
        jpnEducationInfo = new javax.swing.JPanel();
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
        jpnCoursesInfo = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPersonEnrolments = new javax.swing.JTable();
        jpnFilesInfo = new javax.swing.JPanel();
        btnUploadFile = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jpnPhoto = new javax.swing.JPanel();
        btnCaptureImage = new javax.swing.JButton();
        btnOpenCamera = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPersonFiles = new javax.swing.JTable();

        jLabel21.setText("jLabel21");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(122, 72, 221));
        setLocationByPlatform(true);
        getContentPane().setLayout(new java.awt.CardLayout());

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("ADMISSIONS");

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
            .addGroup(btnAdminHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAdminPersonalInfo.setBackground(Functions.pnlBackgroundSideMenu());
        btnAdminPersonalInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminPersonalInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminPersonalInfoMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/profile_add.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Personal Info");

        javax.swing.GroupLayout btnAdminPersonalInfoLayout = new javax.swing.GroupLayout(btnAdminPersonalInfo);
        btnAdminPersonalInfo.setLayout(btnAdminPersonalInfoLayout);
        btnAdminPersonalInfoLayout.setHorizontalGroup(
            btnAdminPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminPersonalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminPersonalInfoLayout.setVerticalGroup(
            btnAdminPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminPersonalInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAdminEducationHistory.setBackground(Functions.pnlBackgroundSideMenu());
        btnAdminEducationHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminEducationHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminEducationHistoryMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/graduation.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Education");

        javax.swing.GroupLayout btnAdminEducationHistoryLayout = new javax.swing.GroupLayout(btnAdminEducationHistory);
        btnAdminEducationHistory.setLayout(btnAdminEducationHistoryLayout);
        btnAdminEducationHistoryLayout.setHorizontalGroup(
            btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminEducationHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAdminEducationHistoryLayout.setVerticalGroup(
            btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdminEducationHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminEducationHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        btnAdminCourseRegistration.setBackground(Functions.pnlBackgroundSideMenu());
        btnAdminCourseRegistration.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminCourseRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdminCourseRegistrationMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/courses.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Courses");

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
            .addGroup(btnAdminCourseRegistrationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAdminCourseRegistrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnStudentFiles.setBackground(Functions.pnlBackgroundSideMenu());
        btnStudentFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentFilesMouseClicked(evt);
            }
        });

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/file_explorer.png"))); // NOI18N

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
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStudentFilesLayout.setVerticalGroup(
            btnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnStudentFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56)
                    .addComponent(jLabel54))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnStudentFiles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdminEducationHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdminHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdminPersonalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminPersonalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminEducationHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdminCourseRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudentFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
        );

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdminCourseRegistration, btnAdminEducationHistory, btnAdminHome, btnAdminPersonalInfo, btnStudentFiles});

        jpnTop.setBackground(Functions.pnlBackgroundSideMenu());
        jpnTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
            .addGroup(jpnTopLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTopLayout.setVerticalGroup(
            jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTopLayout.createSequentialGroup()
                .addGroup(jpnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserSession, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(0, 8, Short.MAX_VALUE))
        );

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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
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
                .addContainerGap(596, Short.MAX_VALUE))
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

        jpnSlider.add(jpnAdmisionsHome, "card2");

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

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(txtPostalCode))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
        jLabel30.setText("Address:");

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
        jLabel32.setText("Postal Code:");

        txtPostalCodeGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtAddressGurdian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtAddressGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbRelationGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFirstNameGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPostalCodeGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelephoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCellphoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastNameGurdian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbRelationGurdian, txtFirstNameGurdian, txtPostalCodeGurdian});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
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
                            .addComponent(txtCellphoneGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(txtPostalCodeGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtAddressGurdian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAddressGurdian, txtPostalCodeGurdian});

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Work History"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCompanyTelephone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtCompanyTelephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 66, 196, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Company:");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 25, 92, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Position:");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 22, 92, -1));

        txtContactPerson.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtContactPerson, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 68, 177, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Contact:");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 71, 92, -1));

        txtCompanyName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtCompanyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 22, 196, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Telephone:");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 69, 92, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Postal Code:");
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 113, 88, -1));

        jcbCompanyPosition.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbCompanyPosition.setForeground(new java.awt.Color(102, 102, 102));
        jcbCompanyPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jPanel5.add(jcbCompanyPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 22, 177, -1));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Cellphone:");
        jPanel5.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 115, 92, -1));

        txtCompanyCellPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtCompanyCellPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 112, 177, -1));

        txtCompanyPostalcode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtCompanyPostalcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 110, 196, -1));

        txtCompanyFax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtCompanyFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 156, 177, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Fax:");
        jPanel5.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 159, 96, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("Address:");
        jPanel5.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 147, 95, -1));

        txtCompanyAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel5.add(txtCompanyAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 147, 197, -1));

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(51, 51, 51));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/save.png"))); // NOI18N
        btnSave.setIconTextGap(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel5.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, -1, 47));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 22, -1, 162));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnPersonalInfoLayout = new javax.swing.GroupLayout(jpnPersonalInfo);
        jpnPersonalInfo.setLayout(jpnPersonalInfoLayout);
        jpnPersonalInfoLayout.setHorizontalGroup(
            jpnPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnPersonalInfoLayout.setVerticalGroup(
            jpnPersonalInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnPersonalInfo, "card3");

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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
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

        btnNewInstitution.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/16x16/add.png"))); // NOI18N
        btnNewInstitution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewInstitutionActionPerformed(evt);
            }
        });

        jbtnDelete.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnDelete.setForeground(new java.awt.Color(51, 51, 51));
        jbtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/delete.gif"))); // NOI18N
        jbtnDelete.setText("Delete");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        jtbnSaveEducationHistory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jtbnSaveEducationHistory.setForeground(new java.awt.Color(51, 51, 51));
        jtbnSaveEducationHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/save.png"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtbnSaveEducationHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbEduQualification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(txtEduYearObtained, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(txtEduSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEduDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jbtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbnSaveEducationHistory)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcbEduInstitution, jcbEduQualification, txtEduDetails, txtEduHistoryStudentNo, txtEduSkills, txtEduYearObtained});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnDelete, jtbnSaveEducationHistory});

        javax.swing.GroupLayout jpnEducationInfoLayout = new javax.swing.GroupLayout(jpnEducationInfo);
        jpnEducationInfo.setLayout(jpnEducationInfoLayout);
        jpnEducationInfoLayout.setHorizontalGroup(
            jpnEducationInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnEducationInfoLayout.setVerticalGroup(
            jpnEducationInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEducationInfoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnEducationInfo, "card3");

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

        javax.swing.GroupLayout jpnCoursesInfoLayout = new javax.swing.GroupLayout(jpnCoursesInfo);
        jpnCoursesInfo.setLayout(jpnCoursesInfoLayout);
        jpnCoursesInfoLayout.setHorizontalGroup(
            jpnCoursesInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
        );
        jpnCoursesInfoLayout.setVerticalGroup(
            jpnCoursesInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnCoursesInfo, "card3");

        btnUploadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/upload.png"))); // NOI18N
        btnUploadFile.setText("UPLOAD FILE");
        btnUploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadFileActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpnPhoto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jpnPhoto.setLayout(new java.awt.CardLayout());

        btnCaptureImage.setText("Capture Image");
        btnCaptureImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureImageActionPerformed(evt);
            }
        });

        btnOpenCamera.setText("Open Camera");
        btnOpenCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCameraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnOpenCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(btnCaptureImage)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCaptureImage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCaptureImage, btnOpenCamera});

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane6.setBorder(null);

        tblPersonFiles.setAutoCreateRowSorter(true);
        tblPersonFiles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblPersonFiles.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblPersonFiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REF", "FileName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPersonFiles.setGridColor(new java.awt.Color(204, 204, 204));
        tblPersonFiles.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblPersonFiles.setRowHeight(40);
        tblPersonFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPersonFilesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblPersonFiles);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnFilesInfoLayout = new javax.swing.GroupLayout(jpnFilesInfo);
        jpnFilesInfo.setLayout(jpnFilesInfoLayout);
        jpnFilesInfoLayout.setHorizontalGroup(
            jpnFilesInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFilesInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnFilesInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnFilesInfoLayout.createSequentialGroup()
                        .addComponent(btnUploadFile)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnFilesInfoLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnFilesInfoLayout.setVerticalGroup(
            jpnFilesInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFilesInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUploadFile)
                .addGap(8, 8, 8)
                .addGroup(jpnFilesInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpnSlider.add(jpnFilesInfo, "card6");

        javax.swing.GroupLayout jpnAdmissionsLayout = new javax.swing.GroupLayout(jpnAdmissions);
        jpnAdmissions.setLayout(jpnAdmissionsLayout);
        jpnAdmissionsLayout.setHorizontalGroup(
            jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAdmissionsLayout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(alert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        jpnAdmissionsLayout.setVerticalGroup(
            jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jpnAdmissionsLayout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jpnAdmissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnAdmissionsLayout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        getContentPane().add(jpnAdmissions, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdminHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminHomeMouseClicked
        // TODO add your handling code here:
        btnAdminHomePressed();
        jpnSlider.nextPanel(10, jpnAdmisionsHome, jpnSlider.right);
    }//GEN-LAST:event_btnAdminHomeMouseClicked

    private void btnAdminPersonalInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminPersonalInfoMouseClicked
        // TODO add your handling code here:
        profileRegistrationPressed();
        jpnSlider.nextPanel(10, jpnPersonalInfo, jpnSlider.right);
    }//GEN-LAST:event_btnAdminPersonalInfoMouseClicked

    private void btnAdminEducationHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminEducationHistoryMouseClicked
        // TODO add your handling code here:
        btnAdminEducationHistoryPressed();
        jpnSlider.nextPanel(10, jpnEducationInfo, jpnSlider.right);
    }//GEN-LAST:event_btnAdminEducationHistoryMouseClicked

    private void btnAdminCourseRegistrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminCourseRegistrationMouseClicked
        // TODO add your handling code here:
        btnAdminCourseRegistrationPressed();
        jpnSlider.nextPanel(10, jpnCoursesInfo, jpnSlider.right);
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
                fillEduHistory(personId);
                EDUID = 0;
                Functions.successMessage("Success!");
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
        try{
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
            gurdian.setAddress(txtPostalCodeGurdian.getText());
            gurdian.setPostalCode(txtAddressGurdian.getText());
            Object objRel = (Object)jcbRelationGurdian.getSelectedItem();
            Relationship relation = (Relationship)objRel;
            gurdian.setRelationshipId(relation.getRelationshipId());
            Employment employment = new Employment();
            employment.setPersonId(personId);
            employment.setCompany(txtCompanyName.getText());
            Object objPos = (Object)jcbCompanyPosition.getSelectedItem();
            Position pos = (Position)objPos;
            employment.setPositionId(pos.getPositionId());
            employment.setAddress(txtPostalCodeGurdian.getText());
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
        }catch(NumberFormatException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
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
        txtStudentNoSession.setText(personImpl.GetNewStudentNo()+"");
        txtStudentNoSession.setEnabled(false);
        txtStudentID.setText(personImpl.GetNewStudentNo()+"");
        txtEduHistoryStudentNo.setText(personImpl.GetNewStudentNo()+"");
        lblUserSession.setText("");
        txtSearchStudentSession.setText(null);
        txtSearchStudentSession.setEditable(false);
        fillUserSession(personImpl.GetNewStudentNo());
    }//GEN-LAST:event_jrbNewStudentActionPerformed

    private void txtSearchStudentSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentSessionKeyReleased
        // TODO add your handling code here:   
        if(txtSearchStudentSession.getText().length() >= 2){
            fillSearchStudents(txtSearchStudentSession.getText());
        } 
    }//GEN-LAST:event_txtSearchStudentSessionKeyReleased

    private void btnStudentFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentFilesMouseClicked
        // TODO add your handling code here:
        btnStudentFilesPressed();
        jpnSlider.nextPanel(10, jpnFilesInfo, jpnSlider.right);
    }//GEN-LAST:event_btnStudentFilesMouseClicked

    private void tblSearchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchListMouseClicked
        // TODO add your handling code here:
        int row = tblSearchList.getSelectedRow();
        personId = Long.parseLong((tblSearchList.getModel().getValueAt(row, 0).toString()));
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
    }//GEN-LAST:event_tblSearchListMouseClicked

    private void btnUploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadFileActionPerformed
        // TODO add your handling code here:
        try{
            PFile file = Functions.BrowseUploadFile(this);
            if(file != null){
                uploadFile(file);
            }
        }catch(Exception ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnUploadFileActionPerformed

    private void tblPersonFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPersonFilesMouseClicked
        // TODO add your handling code here:
        int row = tblPersonFiles.getSelectedRow();
        int upId = Integer.parseInt((tblPersonFiles.getModel().getValueAt(row, 0).toString()));
        Object[] options = { "DELETE FILE","DOWNLOAD FILE" };
        int res = JOptionPane.showOptionDialog(null, "What do you want to do?", "Option",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
        if(res == 0){
            int deleted = pFileBoImpl.RemoveFile(upId);
            GetPersonFiles(personId);
            if(deleted > 0)
                alert.notify("File deleted.",1);
            else
                alert.notify("File not deleted.",0);
        }else if(res == 1){
            PFile file = pFileBoImpl.GetFile(upId);
            Functions.DownloadFile(file);
        }
    }//GEN-LAST:event_tblPersonFilesMouseClicked

    private void btnOpenCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCameraActionPerformed
        // TODO add your handling code here:
        jpnPhoto.removeAll();
        if(webcam!=null){
            webcam.close();
        }
        webcam = Webcam.getDefault();
        Dimension dm = new Dimension(320, 240);
        webcam.setViewSize(dm);
        webPanel = new WebcamPanel(webcam);
        webPanel.setFPSDisplayed(true);
        webPanel.setMirrored(true);
        webPanel.setFillArea(true);
        jpnPhoto.add(webPanel);
        jpnPhoto.getParent().revalidate();
    }//GEN-LAST:event_btnOpenCameraActionPerformed

    private void btnCaptureImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaptureImageActionPerformed
        // TODO add your handling code here:
        try {
            if(webcam != null){
                ImageIO.write(webcam.getImage(), "PNG", new File(System.getProperty("user.home"),"Desktop\\profile.png"));
                webcam.close();
                webcam = null;
                PFile file = Functions.BrowseProfilePhoto(this);
                if(file != null){
                    uploadFile(file);
                }
            }else{
                Functions.warningMessage("Open camera first.");
            }
        } catch (IOException ex) {
            Logger.getLogger(AdmissionFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCaptureImageActionPerformed
    
    
    
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
                new AdmissionFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JPanel btnAdminCourseRegistration;
    private javax.swing.JPanel btnAdminEducationHistory;
    private javax.swing.JPanel btnAdminHome;
    private javax.swing.JPanel btnAdminPersonalInfo;
    private javax.swing.JButton btnCaptureImage;
    private javax.swing.JButton btnNewInstitution;
    private javax.swing.JButton btnOpenCamera;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel btnStudentFiles;
    private javax.swing.JButton btnUploadFile;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
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
    private javax.swing.JPanel jpnCoursesInfo;
    private javax.swing.JPanel jpnEducationInfo;
    private javax.swing.JPanel jpnFilesInfo;
    private javax.swing.JPanel jpnPersonalInfo;
    private javax.swing.JPanel jpnPhoto;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnTop;
    private javax.swing.JRadioButton jrbExistingStudent;
    private javax.swing.JRadioButton jrbNewStudent;
    private javax.swing.JTable jtbEduHistory;
    private javax.swing.JButton jtbnSaveEducationHistory;
    private javax.swing.JLabel lblUserSession;
    private javax.swing.JTable tblPersonEnrolments;
    private javax.swing.JTable tblPersonFiles;
    private javax.swing.JTable tblSearchList;
    private com.molorane.college.view.Controls.TitlePnl title;
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
