/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.matric;

import com.molorane.college.bll.impl.CollegeBoImpl;
import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.MatricBoImpl;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.PinBoImpl;
import com.molorane.college.bll.impl.StaffSubjectBoImpl;
import com.molorane.college.bll.impl.SubjectBoImpl;
import com.molorane.college.bll.impl.UserBoImpl;
import com.molorane.college.custom.Exporting;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.custom.NotifyData;
import com.molorane.college.custom.Printing;
import com.molorane.college.custom.TableColumnHider;
import com.molorane.college.jasperservice.JasperMatric;
import com.molorane.college.model.Matric;
import com.molorane.college.model.Person;
import com.molorane.college.model.StaffSubject;
import com.molorane.college.model.Subject;
import com.molorane.college.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 * @author Mothusi Molorane
 */

public final class MatricAdminFrm extends javax.swing.JFrame {
    
    private final CollegeBoImpl collegeBoImpl = new CollegeBoImpl();
    private final MatricBoImpl matricBoImpl = new MatricBoImpl();
    private final JasperMatric jasperMatric = new JasperMatric();
    private final StaffSubjectBoImpl staffSubjectBoImpl = new StaffSubjectBoImpl();
    private final UserBoImpl userBoImpl = new UserBoImpl();
    private final PinBoImpl pinBoImpl = new PinBoImpl();
    private final SubjectBoImpl subjectBoImpl = new SubjectBoImpl();
    private final PersonBoImpl personBoImpl = new PersonBoImpl();
    
    // variables
    private final DefaultTableModel tblModelViewStudents;
    private final DefaultTableModel tblModelSearchStudent;
    private final DefaultTableModel tblModelStudentMarks;
    private final DefaultTableModel tblModelStaffList;
    private final DefaultTableModel tblModelStaffSubjects;
    private final DefaultTableModel tblModelUploadStudents;
    private final DefaultTableModel tblModelStaffToAuthorize;
    private final DefaultTableModel tblModelMarksToAuthorize;
    private final DefaultTableModel tblModelTermAnalysis;
    
    private final DefaultTableModel tblModelLogStaffAuthorized;
    private final DefaultTableModel tblModelLogMarksToAuthorized;
    
    
    private final int selectedYear;
    private Subject selectedSubject;
    private String studentSubject;
    
    private final User loggedInUser;
    
    private boolean jcbYearFilled = false;
    private boolean jcbSubjectsFilled;
    
    private List<HashMap<String,Object>> uploadStudents = new ArrayList<>();
    
    private Printing p;
    
    private StaffSubject selectedStaffSubject;
    private StaffSubject staffSubjectClicked;
    private JFileChooser fc;
    
    private TableColumnHider tblHider;
    
    List<HashMap<String, Object>> marksToAUtorize = new ArrayList<>();
    
    private int rowOfStaffToAuthorize;
    private int rowOfStudent;
    
    private String term;
    
    /**
     * Creates new form Admin
     */
    public MatricAdminFrm() {
        super("Matric Admin - "+Functions.appName());
        initComponents();
        
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(MatricAdminFrm.this);
        
        selectedYear = collegeBoImpl.GetCurrentYear();
        lblYear.setText(selectedYear+"");
        
        IsUserAdmin();
        
        fillSubjects();
        
        jcbYearFilled = true;
        jpnProgress.setVisible(false);
        lblNeedAuthorization.setVisible(false);
       
        fc = new JFileChooser();
        
        tblModelViewStudents = (DefaultTableModel)tblViewStudents.getModel();
        tblModelSearchStudent = (DefaultTableModel)tblSearchStudent.getModel();
        tblModelStudentMarks = (DefaultTableModel)tblStudentMarks.getModel();
        tblModelStaffList = (DefaultTableModel)tblStaffList.getModel();
        tblModelStaffSubjects = (DefaultTableModel)tblStaffSubjects.getModel();
        tblModelUploadStudents = (DefaultTableModel)tblUploadStudents.getModel();
        tblModelStaffToAuthorize = (DefaultTableModel)tblStaffToAuthorize.getModel();
        tblModelMarksToAuthorize = (DefaultTableModel)tblMarksToAuthorize.getModel();
        tblModelTermAnalysis = (DefaultTableModel)tblAnalysisTerm.getModel();
        
        tblModelLogStaffAuthorized = (DefaultTableModel)tblLogStaffAuthorized.getModel();
        tblModelLogMarksToAuthorized = (DefaultTableModel)tblLogMarksAuthorized.getModel();
        
        fillStaffToAuthorize();
        Functions.initForm(MatricAdminFrm.this);
    }
    
    private void IsUserAdmin(){
        if(loggedInUser != null){
            if(!loggedInUser.getRoleName().equalsIgnoreCase("MT-Admin")){
                Functions.errorMessage("Access denied\n"
                        + "Only Matric administrator is allowed to access this Panel.");
                System.exit(0);
            }
        }else{
            Functions.errorMessage("Login first.");
                System.exit(0);
        }
    }
    
    void btnViewStudentPressed(){
        Functions.setColor(btnViewStudents);
        Functions.resetColor(btnSearchStudent);
        Functions.resetColor(btnStudent);
        Functions.resetColor(btnReports);
        Functions.resetColor(btnStaff);
        Functions.resetColor(btnAuthorize);
        Functions.resetColor(btnPassword);
        Functions.resetColor(btnAnalysis);
        Functions.resetColor(btnLog);
    }
    
    void btnSearchStudentPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.setColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.resetColor(btnAuthorize);
       Functions.resetColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnStudentPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.setColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.resetColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnReportsPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.setColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.resetColor(btnAuthorize);
       Functions.resetColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnStaffPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.setColor(btnStaff);
       Functions.resetColor(btnAuthorize);
       Functions.resetColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnAuthorizePressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.setColor(btnAuthorize);
       Functions.resetColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnPasswordPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.resetColor(btnAuthorize);
       Functions.setColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnAnalysisPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.resetColor(btnAuthorize);
       Functions.resetColor(btnPassword);
       Functions.setColor(btnAnalysis);
       Functions.resetColor(btnLog);
    }
    
    void btnLogPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnReports);
       Functions.resetColor(btnStaff);
       Functions.resetColor(btnAuthorize);
       Functions.resetColor(btnPassword);
       Functions.resetColor(btnAnalysis);
       Functions.setColor(btnLog);
    }
    
    public void ViewStudent(ArrayList<Matric> students){
        tblModelViewStudents.setRowCount(0);
        students.forEach((bean) -> {
            tblModelViewStudents.addRow(new Object[]{
                bean.getIdno(),
                bean.getLastName(),
                bean.getFirstName(),
                bean.getMyear(),
                bean.getSubject(),
                bean.getTerm1(),
                bean.getTerm2(),
                bean.getTerm3()
             });
        });
    }
    
    public void SearchStudent(ArrayList<Matric> students){
        tblModelSearchStudent.setRowCount(0);
        students.forEach((bean) -> {
            tblModelSearchStudent.addRow(new Object[]{
                bean.getIdno(),
                bean.getLastName(),
                bean.getFirstName(),
                bean.getSubject(),
                bean.getTerm1(),
                bean.getTerm2(),
                bean.getTerm3()
             });
        });
    }
    
    public void StudentMarks(ArrayList<Matric> marks){
        tblModelStudentMarks.setRowCount(0);
        marks.forEach((bean) -> {
            tblModelStudentMarks.addRow(new Object[]{
                bean.getSubject(),
                bean.getTerm1(),
                bean.getTerm2(),
                bean.getTerm3()
             });
        });
    }
    
    public void fillStaffList(List<HashMap<String, Object>> staffList){
        tblModelStaffList.setRowCount(0);
        staffList.forEach((row) -> {
            tblModelStaffList.addRow(new Object[]{
                row.get("personId"),
                row.get("firstName"),
                row.get("lastName")
             });
        });
    }
    
    public void fillStaffInSubject(String subject){
        List<HashMap<String, Object>> staffInSubject = staffSubjectBoImpl.GetStaffInSubject(subject, selectedYear);
        fillStaffList(staffInSubject);
    }
    
    public void fillStaffSubjects(List<HashMap<String, Object>> staffSubjects){
        tblModelStaffSubjects.setRowCount(0);
        staffSubjects.forEach((row) -> {
            tblModelStaffSubjects.addRow(new Object[]{
                row.get("syear"),
                row.get("subjectCode")
             });
        });
    }
    
    public void fillUploadStudents(List<HashMap<String, Object>> students){
        tblModelUploadStudents.setRowCount(0);
        students.forEach((row) -> {
            tblModelUploadStudents.addRow(new Object[]{
                row.get("idno"),
                row.get("lastName"),
                row.get("names"),
                row.get("subject")
             });
        });
    }
    
    public void getControlData(){
        Object objSubject = (Object)jcbSubject.getSelectedItem();
        selectedSubject = (Subject)objSubject;
    }
    
    public void endProgress(){
        jpnProgress.setVisible(false);
        lblProgress.setText("");
    }
    
    public void startProcess(String msg){
        jpnProgress.setVisible(true);
        lblProgress.setText(msg);
    }
    
    private void fillStaffToAuthorize(){
       List<HashMap<String, Object>> staffToAuthorize = matricBoImpl.GetStaffToAuthorize();
       tblModelStaffToAuthorize.setRowCount(0);
        staffToAuthorize.forEach((row) -> {
            tblModelStaffToAuthorize.addRow(new Object[]{
                row.get("personId"),
                row.get("subject"),
                row.get("myear"),
                row.get("fullNames")
             });
        });
        
        tblHider = new TableColumnHider(tblStaffToAuthorize);
        tblHider.hide("StaffNo");
        tblHider.hide("Year");
        if(tblStaffToAuthorize.getRowCount() > 0){
            lblNeedAuthorization.setVisible(true);
            alert.notify("Heads up! Updates available.", 0);
        }
    }
    
    private void fillLogStaffAuthorized(){
       List<HashMap<String, Object>> staffToAuthorize = matricBoImpl.GetWhoAuthorizedMatricMarks();
       tblModelLogStaffAuthorized.setRowCount(0);
        staffToAuthorize.forEach((row) -> {
            tblModelLogStaffAuthorized.addRow(new Object[]{
                row.get("adminId"),
                row.get("subject"),
                row.get("myear"),
                row.get("firstName") + " "+row.get("lastName")
             });
        });
        
        tblHider = new TableColumnHider(tblLogStaffAuthorized);
        tblHider.hide("Year");
        
        if(tblStaffToAuthorize.getRowCount() > 0){
            lblNeedAuthorization.setVisible(true);
            alert.notify("Heads up! Updates available.", 0);
        }
    }
    
    public void displayMarksToAuthorize(boolean checked){
        tblModelMarksToAuthorize.setRowCount(0);
        marksToAUtorize.forEach((row) -> {
            tblModelMarksToAuthorize.addRow(new Object[]{
                row.get("idno"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("subject"),
                row.get("myear"),
                row.get("term1"),
                row.get("term2"),
                row.get("term3"),
                checked
             });
        });
        
        if(tblModelMarksToAuthorize.getRowCount() == 0){
            tblModelStaffToAuthorize.removeRow(rowOfStaffToAuthorize);
            if(tblModelStaffToAuthorize.getRowCount() == 0){
                lblNeedAuthorization.setVisible(false);
            }
        }
    }
    
    public void fillSubjects(){
        jcbSubject.removeAll();
        jcbSubjectsFilled = false;
        subjectBoImpl.fillComboBoxSubject(jcbSubject);
        jcbSubjectsFilled = true;
    }
    
    public void fillMarksToAuthorize(List<HashMap<String, Object>> marks){
        marksToAUtorize = marks; 
        displayMarksToAuthorize(false);
    }
    
    private boolean IsAllowed() {
        Object[] options1 = { "ALLOW", "CANCEL"};
        int result = JOptionPane.showOptionDialog(null, jpnlPassword, "Enter admin pin to authorize this action",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION){
            String input = Functions.GetPassword(jpswPIN);
            jpswPIN.setText("");
            return pinBoImpl.validatePin(input);
        }
        return false;
    }
    
    public void registerStudent(String idno, String lastName, String names){
        getControlData();
        if(idno.length() > 0 && lastName.length() > 0 && names.length() > 0){
            if(!selectedSubject.getSubject().equalsIgnoreCase("SELECT")){
                Matric matric = new Matric();
                matric.setIdno(idno);
                matric.setLastName(lastName);
                matric.setFirstName(names);
                matric.setMyear(selectedYear);
                matric.setSubject(selectedSubject.getSubject());
                matric.setTerm1("-");
                matric.setTerm2("-");
                matric.setTerm3("-");

                int saved = matricBoImpl.AddMatric(matric);
                if(saved > 0){
                    StudentMarks(matricBoImpl.GetMatricStudentMarks(idno));
                    Functions.successMessage("Student registered.");
                }else{
                    Functions.errorMessage("Student not registered.");
                }   
            }else{
                Functions.errorMessage("Select subject first.");
            }
        }else{
            alert.notify("All fields must not be empty.", 0);
        }
    }
    
    public void fillTerm(List<HashMap<String, Object>> analysis){
        tblModelTermAnalysis.setRowCount(0);
        analysis.forEach((row) -> {
            tblModelTermAnalysis.addRow(new Object[]{
                row.get("subject"),
                row.get("registered"),
                row.get("wrote"),
                row.get("absent"),
                row.get("passed"),
                row.get("failed"),
                row.get("avgMark"),
                row.get("passRate")
             });
        });
    }
    
    public void fillLogMarksAuthorized(List<HashMap<String, Object>> logMarks){
        tblModelLogMarksToAuthorized.setRowCount(0);
        lblStaff.setText(null);
        logMarks.forEach((row) -> {
            long staffNo = Long.parseLong(row.get("staffNo").toString());
            if(lblStaff.getText() == null){
                Person p = personBoImpl.GetPerson(staffNo);
                lblStaff.setText(p.getPersonId()+" "+p.getLastName()+" "+p.getFirstName());
            }
            tblModelLogMarksToAuthorized.addRow(new Object[]{
                row.get("idno"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("term1"),
                row.get("term2"),
                row.get("term3"),
                row.get("log_time")
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
        btnSearchStudent = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnStudent = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnReports = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnStaff = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnAuthorize = new javax.swing.JPanel();
        lblNeedAuthorization = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnPassword = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnAnalysis = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        btnLog = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jpnControls = new javax.swing.JPanel();
        jpnProgress = new javax.swing.JPanel();
        lblProgress = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jpnSelect = new com.molorane.college.custom.JPanelSliding();
        pnlSelect = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jcbSubject = new javax.swing.JComboBox<>();
        pnlEmpty = new javax.swing.JPanel();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnViewStudents = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        btnExport = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblViewStudents = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnBrowseStudentsCSVFile = new javax.swing.JButton();
        btnUploadStudentsData = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblUploadStudents = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtASIDno = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtASLastNAme = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtASNames = new javax.swing.JTextField();
        btnRegisterMatricStudent = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jpnSearchStudent = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSearchStudent = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearchStudent = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jpnStudent = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtNames = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSaveProfile = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtIDNo = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblStudentMarks = new javax.swing.JTable();
        btnStudentReport = new javax.swing.JButton();
        btnSRegister = new javax.swing.JButton();
        btnDeregister = new javax.swing.JButton();
        jpnReports = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnPrintReports = new javax.swing.JButton();
        btnStopProcess = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jpnStaff = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblStaffList = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        txtSearchTeacher = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtStaffNo = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblStaffSubjects = new javax.swing.JTable();
        btnRemoveStaffSubject = new javax.swing.JButton();
        btnStaffSubject = new javax.swing.JButton();
        btnPersonalInformation = new javax.swing.JButton();
        jpnAuthorize = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblStaffToAuthorize = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblMarksToAuthorize = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        btnAcceptAuthorization = new javax.swing.JButton();
        rbtnCheckAll = new javax.swing.JRadioButton();
        rbtnUncheckAll = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jpnPassword = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        pinOld = new javax.swing.JPasswordField();
        jLabel32 = new javax.swing.JLabel();
        pinNew = new javax.swing.JPasswordField();
        btnChangeAdminPin = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        admId = new javax.swing.JTextField();
        jpnAnalysis = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblAnalysisTerm = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        btnTerm1 = new javax.swing.JButton();
        btnTerm2 = new javax.swing.JButton();
        btnTerm3 = new javax.swing.JButton();
        btnPrintAnalysis = new javax.swing.JButton();
        jpnLog = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblLogStaffAuthorized = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblLogMarksAuthorized = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        lblStaff = new javax.swing.JLabel();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        btnViewStudents.setBackground(new java.awt.Color(64, 43, 100));
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
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnViewStudentsLayout.setVerticalGroup(
            btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        btnSearchStudent.setBackground(Functions.pnlBackgroundSideMenu());
        btnSearchStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchStudentMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/search.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Search Student");

        javax.swing.GroupLayout btnSearchStudentLayout = new javax.swing.GroupLayout(btnSearchStudent);
        btnSearchStudent.setLayout(btnSearchStudentLayout);
        btnSearchStudentLayout.setHorizontalGroup(
            btnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSearchStudentLayout.setVerticalGroup(
            btnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnStudent.setBackground(Functions.pnlBackgroundSideMenu());
        btnStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/information.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Student");

        javax.swing.GroupLayout btnStudentLayout = new javax.swing.GroupLayout(btnStudent);
        btnStudent.setLayout(btnStudentLayout);
        btnStudentLayout.setHorizontalGroup(
            btnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStudentLayout.setVerticalGroup(
            btnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        btnReports.setBackground(Functions.pnlBackgroundSideMenu());
        btnReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportsMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Print Reports");

        javax.swing.GroupLayout btnReportsLayout = new javax.swing.GroupLayout(btnReports);
        btnReports.setLayout(btnReportsLayout);
        btnReportsLayout.setHorizontalGroup(
            btnReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnReportsLayout.setVerticalGroup(
            btnReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        btnStaff.setBackground(Functions.pnlBackgroundSideMenu());
        btnStaff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaffMouseClicked(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/skills.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("Staff");

        javax.swing.GroupLayout btnStaffLayout = new javax.swing.GroupLayout(btnStaff);
        btnStaff.setLayout(btnStaffLayout);
        btnStaffLayout.setHorizontalGroup(
            btnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStaffLayout.setVerticalGroup(
            btnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        btnAuthorize.setBackground(Functions.pnlBackgroundSideMenu());
        btnAuthorize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthorize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAuthorizeMouseClicked(evt);
            }
        });

        lblNeedAuthorization.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/notification.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setText("Updates");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/mail.png"))); // NOI18N

        javax.swing.GroupLayout btnAuthorizeLayout = new javax.swing.GroupLayout(btnAuthorize);
        btnAuthorize.setLayout(btnAuthorizeLayout);
        btnAuthorizeLayout.setHorizontalGroup(
            btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNeedAuthorization, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAuthorizeLayout.setVerticalGroup(
            btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNeedAuthorization, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnPassword.setBackground(Functions.pnlBackgroundSideMenu());
        btnPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPasswordMouseClicked(evt);
            }
        });

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/keys.png"))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 204, 204));
        jLabel28.setText("Password");

        javax.swing.GroupLayout btnPasswordLayout = new javax.swing.GroupLayout(btnPassword);
        btnPassword.setLayout(btnPasswordLayout);
        btnPasswordLayout.setHorizontalGroup(
            btnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnPasswordLayout.setVerticalGroup(
            btnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        btnAnalysis.setBackground(Functions.pnlBackgroundSideMenu());
        btnAnalysis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnalysis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnalysisMouseClicked(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/percent.png"))); // NOI18N

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setText("Analysis");

        javax.swing.GroupLayout btnAnalysisLayout = new javax.swing.GroupLayout(btnAnalysis);
        btnAnalysis.setLayout(btnAnalysisLayout);
        btnAnalysisLayout.setHorizontalGroup(
            btnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAnalysisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAnalysisLayout.setVerticalGroup(
            btnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        lblYear.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblYear.setForeground(new java.awt.Color(204, 204, 204));
        lblYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnLog.setBackground(Functions.pnlBackgroundSideMenu());
        btnLog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogMouseClicked(evt);
            }
        });

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/mailbox.png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("Log");

        javax.swing.GroupLayout btnLogLayout = new javax.swing.GroupLayout(btnLog);
        btnLog.setLayout(btnLogLayout);
        btnLogLayout.setHorizontalGroup(
            btnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnLogLayout.setVerticalGroup(
            btnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReports, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnLog, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnStudent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearchStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnViewStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAnalysis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btnStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAuthorize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAuthorize, btnStaff, btnStudent});

        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnAuthorize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnSideMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAnalysis, btnAuthorize, btnLog, btnPassword, btnReports, btnSearchStudent, btnStaff, btnStudent, btnViewStudents});

        jpnControls.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblProgress.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProgress.setText("Exporting please wait..");
        lblProgress.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/loaderIcon.gif"))); // NOI18N

        javax.swing.GroupLayout jpnProgressLayout = new javax.swing.GroupLayout(jpnProgress);
        jpnProgress.setLayout(jpnProgressLayout);
        jpnProgressLayout.setHorizontalGroup(
            jpnProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnProgressLayout.createSequentialGroup()
                .addComponent(lblProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21))
        );
        jpnProgressLayout.setVerticalGroup(
            jpnProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnSelect.setBorder(null);

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Subject:");

        jcbSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbSubject.setForeground(new java.awt.Color(51, 51, 51));
        jcbSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectLayout = new javax.swing.GroupLayout(pnlSelect);
        pnlSelect.setLayout(pnlSelectLayout);
        pnlSelectLayout.setHorizontalGroup(
            pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectLayout.createSequentialGroup()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSubject, 0, 115, Short.MAX_VALUE))
        );
        pnlSelectLayout.setVerticalGroup(
            pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectLayout.createSequentialGroup()
                .addGroup(pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnSelect.add(pnlSelect, "card2");

        javax.swing.GroupLayout pnlEmptyLayout = new javax.swing.GroupLayout(pnlEmpty);
        pnlEmpty.setLayout(pnlEmptyLayout);
        pnlEmptyLayout.setHorizontalGroup(
            pnlEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
        pnlEmptyLayout.setVerticalGroup(
            pnlEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jpnSelect.add(pnlEmpty, "card3");

        javax.swing.GroupLayout jpnControlsLayout = new javax.swing.GroupLayout(jpnControls);
        jpnControls.setLayout(jpnControlsLayout);
        jpnControlsLayout.setHorizontalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnControlsLayout.setVerticalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpnSelect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnExport.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnExport.setForeground(new java.awt.Color(51, 51, 51));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/import_export.png"))); // NOI18N
        btnExport.setToolTipText("Export data");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        tblViewStudents.setAutoCreateRowSorter(true);
        tblViewStudents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblViewStudents.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblViewStudents.setForeground(new java.awt.Color(51, 51, 51));
        tblViewStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "LastName", "Names", "Year", "Subject", "Term 1", "Term 2", "Term 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblViewStudents.setGridColor(new java.awt.Color(204, 204, 204));
        tblViewStudents.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblViewStudents.setRowHeight(40);
        jScrollPane5.setViewportView(tblViewStudents);
        if (tblViewStudents.getColumnModel().getColumnCount() > 0) {
            tblViewStudents.getColumnModel().getColumn(3).setMaxWidth(100);
            tblViewStudents.getColumnModel().getColumn(4).setMaxWidth(150);
            tblViewStudents.getColumnModel().getColumn(5).setMaxWidth(100);
            tblViewStudents.getColumnModel().getColumn(6).setMaxWidth(100);
            tblViewStudents.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnExport)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("View Students", jPanel6);

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
                "IDNo", "LastName", "Names", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUploadStudents.setGridColor(new java.awt.Color(204, 204, 204));
        tblUploadStudents.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblUploadStudents.setRowHeight(40);
        jScrollPane10.setViewportView(tblUploadStudents);
        if (tblUploadStudents.getColumnModel().getColumnCount() > 0) {
            tblUploadStudents.getColumnModel().getColumn(3).setMaxWidth(150);
        }

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
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE))
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
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jTabbedPane3.addTab("Upload Students", jPanel5);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("IDNo:");

        txtASIDno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("LastName:");

        txtASLastNAme.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Names:");

        txtASNames.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnRegisterMatricStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegisterMatricStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnRegisterMatricStudent.setText("Register");
        btnRegisterMatricStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterMatricStudentActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("If the student is already registered for the selected subject, the registration is ignored.");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnRegisterMatricStudent)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtASLastNAme, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                .addComponent(txtASIDno)
                                .addComponent(txtASNames))))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtASIDno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtASLastNAme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtASNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegisterMatricStudent)
                .addGap(128, 128, 128)
                .addComponent(jLabel35)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Add New Student", jPanel14);

        javax.swing.GroupLayout jpnViewStudentsLayout = new javax.swing.GroupLayout(jpnViewStudents);
        jpnViewStudents.setLayout(jpnViewStudentsLayout);
        jpnViewStudentsLayout.setHorizontalGroup(
            jpnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
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

        tblSearchStudent.setAutoCreateRowSorter(true);
        tblSearchStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblSearchStudent.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblSearchStudent.setForeground(new java.awt.Color(51, 51, 51));
        tblSearchStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "Last Name", "Names", "Subject", "Term 1", "Term 2", "Term 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSearchStudent.setGridColor(new java.awt.Color(204, 204, 204));
        tblSearchStudent.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblSearchStudent.setRowHeight(40);
        tblSearchStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchStudentMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSearchStudent);
        if (tblSearchStudent.getColumnModel().getColumnCount() > 0) {
            tblSearchStudent.getColumnModel().getColumn(3).setMaxWidth(150);
            tblSearchStudent.getColumnModel().getColumn(4).setMaxWidth(100);
            tblSearchStudent.getColumnModel().getColumn(5).setMaxWidth(100);
            tblSearchStudent.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Search:");

        txtSearchStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearchStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchStudentKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/information.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnSearchStudentLayout = new javax.swing.GroupLayout(jpnSearchStudent);
        jpnSearchStudent.setLayout(jpnSearchStudentLayout);
        jpnSearchStudentLayout.setHorizontalGroup(
            jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jpnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSearchStudentLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnSearchStudentLayout.setVerticalGroup(
            jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnSearchStudent, "card3");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("LastName:");

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtNames.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Names:");

        btnSaveProfile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSaveProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/save.png"))); // NOI18N
        btnSaveProfile.setText("UPDATE");
        btnSaveProfile.setToolTipText("Update student information");
        btnSaveProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveProfileActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("IDNo:");

        txtIDNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtIDNo.setEnabled(false);

        tblStudentMarks.setAutoCreateRowSorter(true);
        tblStudentMarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStudentMarks.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStudentMarks.setForeground(new java.awt.Color(51, 51, 51));
        tblStudentMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Term 1", "Term 2", "Term 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudentMarks.setGridColor(new java.awt.Color(204, 204, 204));
        tblStudentMarks.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblStudentMarks.setRowHeight(40);
        tblStudentMarks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMarksMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblStudentMarks);

        btnStudentReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        btnStudentReport.setToolTipText("Print student report");
        btnStudentReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentReportActionPerformed(evt);
            }
        });

        btnSRegister.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnSRegister.setText("REGISTER");
        btnSRegister.setToolTipText("Register new module for student");
        btnSRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSRegisterActionPerformed(evt);
            }
        });

        btnDeregister.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDeregister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/error_delete.png"))); // NOI18N
        btnDeregister.setText("DEREGISTER");
        btnDeregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeregisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnStudentReport, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSaveProfile))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(txtIDNo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNames))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeregister)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSRegister))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSaveProfile, btnStudentReport});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtIDNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStudentReport, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnDeregister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSaveProfile, btnStudentReport});

        jTabbedPane2.addTab("Student Information", jPanel4);

        javax.swing.GroupLayout jpnStudentLayout = new javax.swing.GroupLayout(jpnStudent);
        jpnStudent.setLayout(jpnStudentLayout);
        jpnStudentLayout.setHorizontalGroup(
            jpnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jpnStudentLayout.setVerticalGroup(
            jpnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jpnSlider.add(jpnStudent, "card4");

        btnPrintReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/start.png"))); // NOI18N
        btnPrintReports.setToolTipText("Start pringing reports.");
        btnPrintReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReportsActionPerformed(evt);
            }
        });

        btnStopProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/stop.png"))); // NOI18N
        btnStopProcess.setToolTipText("Stop process");
        btnStopProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopProcessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("PRINT REPORTS");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Generate reports for all students, for the current year.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrintReports, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStopProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPrintReports, btnStopProcess});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnStopProcess, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnPrintReports, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap(306, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPrintReports, btnStopProcess});

        jTabbedPane1.addTab("Reports", jPanel1);

        javax.swing.GroupLayout jpnReportsLayout = new javax.swing.GroupLayout(jpnReports);
        jpnReports.setLayout(jpnReportsLayout);
        jpnReportsLayout.setHorizontalGroup(
            jpnReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnReportsLayout.setVerticalGroup(
            jpnReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jpnSlider.add(jpnReports, "card5");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblStaffList.setAutoCreateRowSorter(true);
        tblStaffList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStaffList.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStaffList.setForeground(new java.awt.Color(51, 51, 51));
        tblStaffList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Staff No", "First Name", "Last Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        jScrollPane8.setViewportView(tblStaffList);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setText("Search staff:");

        txtSearchTeacher.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearchTeacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTeacherKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtSearchTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Staff No:");

        txtStaffNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Staff Subjects"));

        tblStaffSubjects.setAutoCreateRowSorter(true);
        tblStaffSubjects.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStaffSubjects.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStaffSubjects.setForeground(new java.awt.Color(51, 51, 51));
        tblStaffSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStaffSubjects.setGridColor(new java.awt.Color(204, 204, 204));
        tblStaffSubjects.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblStaffSubjects.setRowHeight(40);
        tblStaffSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffSubjectsMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblStaffSubjects);
        if (tblStaffSubjects.getColumnModel().getColumnCount() > 0) {
            tblStaffSubjects.getColumnModel().getColumn(0).setHeaderValue("Year");
        }

        btnRemoveStaffSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemoveStaffSubject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/trash.png"))); // NOI18N
        btnRemoveStaffSubject.setToolTipText("Remove module for staff");
        btnRemoveStaffSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStaffSubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemoveStaffSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveStaffSubject)
                .addGap(9, 9, 9))
        );

        btnStaffSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnStaffSubject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/16x16/add.png"))); // NOI18N
        btnStaffSubject.setToolTipText("Add new module for staff");
        btnStaffSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStaffSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnStaffSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnPersonalInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPersonalInformation.setForeground(new java.awt.Color(51, 51, 51));
        btnPersonalInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnPersonalInformation.setText("STAFF");
        btnPersonalInformation.setToolTipText("Add new personal information");
        btnPersonalInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalInformationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPersonalInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPersonalInformation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Staff", jPanel2);

        javax.swing.GroupLayout jpnStaffLayout = new javax.swing.GroupLayout(jpnStaff);
        jpnStaff.setLayout(jpnStaffLayout);
        jpnStaffLayout.setHorizontalGroup(
            jpnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jpnStaffLayout.setVerticalGroup(
            jpnStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4))
        );

        jpnSlider.add(jpnStaff, "card6");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblStaffToAuthorize.setAutoCreateRowSorter(true);
        tblStaffToAuthorize.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblStaffToAuthorize.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStaffToAuthorize.setForeground(new java.awt.Color(51, 51, 51));
        tblStaffToAuthorize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StaffNo", "Subject", "Year", "Full Names"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStaffToAuthorize.setGridColor(new java.awt.Color(204, 204, 204));
        tblStaffToAuthorize.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblStaffToAuthorize.setRowHeight(40);
        tblStaffToAuthorize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffToAuthorizeMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblStaffToAuthorize);
        if (tblStaffToAuthorize.getColumnModel().getColumnCount() > 0) {
            tblStaffToAuthorize.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblStaffToAuthorize.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        tblMarksToAuthorize.setAutoCreateRowSorter(true);
        tblMarksToAuthorize.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblMarksToAuthorize.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblMarksToAuthorize.setForeground(new java.awt.Color(51, 51, 51));
        tblMarksToAuthorize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "LastName", "Names", "Subject", "Year", "Term1", "Term2", "Term3", "Allow"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMarksToAuthorize.setGridColor(new java.awt.Color(204, 204, 204));
        tblMarksToAuthorize.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblMarksToAuthorize.setRowHeight(40);
        jScrollPane11.setViewportView(tblMarksToAuthorize);
        if (tblMarksToAuthorize.getColumnModel().getColumnCount() > 0) {
            tblMarksToAuthorize.getColumnModel().getColumn(3).setMaxWidth(80);
            tblMarksToAuthorize.getColumnModel().getColumn(4).setMaxWidth(80);
            tblMarksToAuthorize.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblMarksToAuthorize.getColumnModel().getColumn(5).setMaxWidth(80);
            tblMarksToAuthorize.getColumnModel().getColumn(6).setMaxWidth(80);
            tblMarksToAuthorize.getColumnModel().getColumn(7).setMaxWidth(80);
        }

        btnAcceptAuthorization.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAcceptAuthorization.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/success.png"))); // NOI18N
        btnAcceptAuthorization.setText("Authorize");
        btnAcceptAuthorization.setToolTipText("Authorize an update");
        btnAcceptAuthorization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptAuthorizationActionPerformed(evt);
            }
        });

        btnGroupCheck.add(rbtnCheckAll);
        rbtnCheckAll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rbtnCheckAll.setText("CHECK ALL");
        rbtnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCheckAllActionPerformed(evt);
            }
        });

        btnGroupCheck.add(rbtnUncheckAll);
        rbtnUncheckAll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rbtnUncheckAll.setText("UNCHECK");
        rbtnUncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnUncheckAllActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("UPDATE REQUESTS FROM");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAcceptAuthorization)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtnCheckAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnUncheckAll)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAcceptAuthorization, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtnCheckAll)
                                .addComponent(rbtnUncheckAll))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnAuthorizeLayout = new javax.swing.GroupLayout(jpnAuthorize);
        jpnAuthorize.setLayout(jpnAuthorizeLayout);
        jpnAuthorizeLayout.setHorizontalGroup(
            jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnAuthorizeLayout.setVerticalGroup(
            jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnAuthorize, "card7");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Main Admin"));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("OLD PIN:");

        pinOld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("NEW PIN:");

        pinNew.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnChangeAdminPin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnChangeAdminPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/keys.png"))); // NOI18N
        btnChangeAdminPin.setText("Update");
        btnChangeAdminPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeAdminPinActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Admin ID:");

        admId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(admId, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pinNew, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pinOld, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnChangeAdminPin, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(admId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(pinOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(pinNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangeAdminPin)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {admId, pinNew, pinOld});

        javax.swing.GroupLayout jpnPasswordLayout = new javax.swing.GroupLayout(jpnPassword);
        jpnPassword.setLayout(jpnPasswordLayout);
        jpnPasswordLayout.setHorizontalGroup(
            jpnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPasswordLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jpnPasswordLayout.setVerticalGroup(
            jpnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPasswordLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnPassword, "card6");

        tblAnalysisTerm.setAutoCreateRowSorter(true);
        tblAnalysisTerm.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblAnalysisTerm.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblAnalysisTerm.setForeground(new java.awt.Color(51, 51, 51));
        tblAnalysisTerm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Registered", "Wrote", "Absent", "Passed", "Failed", "AVG", "Pass Rate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAnalysisTerm.setGridColor(new java.awt.Color(204, 204, 204));
        tblAnalysisTerm.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblAnalysisTerm.setRowHeight(40);
        jScrollPane12.setViewportView(tblAnalysisTerm);

        btnTerm1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTerm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/percent.png"))); // NOI18N
        btnTerm1.setText("TERM 1");
        btnTerm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerm1ActionPerformed(evt);
            }
        });

        btnTerm2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTerm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/percent.png"))); // NOI18N
        btnTerm2.setText("TERM 2");
        btnTerm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerm2ActionPerformed(evt);
            }
        });

        btnTerm3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTerm3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/percent.png"))); // NOI18N
        btnTerm3.setText("TERM 3");
        btnTerm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerm3ActionPerformed(evt);
            }
        });

        btnPrintAnalysis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        btnPrintAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintAnalysisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnAnalysisLayout = new javax.swing.GroupLayout(jpnAnalysis);
        jpnAnalysis.setLayout(jpnAnalysisLayout);
        jpnAnalysisLayout.setHorizontalGroup(
            jpnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAnalysisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addComponent(jSeparator8)
                    .addGroup(jpnAnalysisLayout.createSequentialGroup()
                        .addComponent(btnTerm1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTerm2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTerm3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
                        .addComponent(btnPrintAnalysis)))
                .addContainerGap())
        );
        jpnAnalysisLayout.setVerticalGroup(
            jpnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnAnalysisLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jpnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTerm1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTerm2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTerm3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPrintAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnAnalysis, "card9");

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblLogStaffAuthorized.setAutoCreateRowSorter(true);
        tblLogStaffAuthorized.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblLogStaffAuthorized.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblLogStaffAuthorized.setForeground(new java.awt.Color(51, 51, 51));
        tblLogStaffAuthorized.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AdminId", "Subject", "Year", "Full Names"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLogStaffAuthorized.setGridColor(new java.awt.Color(204, 204, 204));
        tblLogStaffAuthorized.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblLogStaffAuthorized.setRowHeight(40);
        tblLogStaffAuthorized.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLogStaffAuthorizedMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblLogStaffAuthorized);
        if (tblLogStaffAuthorized.getColumnModel().getColumnCount() > 0) {
            tblLogStaffAuthorized.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblLogStaffAuthorized.getColumnModel().getColumn(0).setMaxWidth(150);
            tblLogStaffAuthorized.getColumnModel().getColumn(1).setMaxWidth(100);
            tblLogStaffAuthorized.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jSplitPane2.setLeftComponent(jPanel17);

        tblLogMarksAuthorized.setAutoCreateRowSorter(true);
        tblLogMarksAuthorized.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblLogMarksAuthorized.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblLogMarksAuthorized.setForeground(new java.awt.Color(51, 51, 51));
        tblLogMarksAuthorized.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "LastName", "Names", "Term1", "Term2", "Term3", "LogTime"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLogMarksAuthorized.setGridColor(new java.awt.Color(204, 204, 204));
        tblLogMarksAuthorized.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblLogMarksAuthorized.setRowHeight(40);
        jScrollPane15.setViewportView(tblLogMarksAuthorized);
        if (tblLogMarksAuthorized.getColumnModel().getColumnCount() > 0) {
            tblLogMarksAuthorized.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblLogMarksAuthorized.getColumnModel().getColumn(3).setMaxWidth(80);
            tblLogMarksAuthorized.getColumnModel().getColumn(4).setMaxWidth(80);
            tblLogMarksAuthorized.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel42.setText("Staff:");

        lblStaff.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStaff, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(548, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGap(44, 44, 44)))
        );

        jSplitPane2.setRightComponent(jPanel18);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnLogLayout = new javax.swing.GroupLayout(jpnLog);
        jpnLog.setLayout(jpnLogLayout);
        jpnLogLayout.setHorizontalGroup(
            jpnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnLogLayout.setVerticalGroup(
            jpnLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnSlider.add(jpnLog, "card10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpnControls, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(alert, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)))
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewStudentsMouseClicked
        // TODO add your handling code here:
        btnViewStudentPressed();
        jpnSlider.nextPanel(10, jpnViewStudents, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlSelect, jpnSelect.right);
    }//GEN-LAST:event_btnViewStudentsMouseClicked

    private void btnSearchStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchStudentMouseClicked
        // TODO add your handling code here:
        btnSearchStudentPressed();
        jpnSlider.nextPanel(10, jpnSearchStudent, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlSelect, jpnSelect.right);
    }//GEN-LAST:event_btnSearchStudentMouseClicked

    private void btnStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseClicked
        // TODO add your handling code here:
        btnStudentPressed();
        jpnSlider.nextPanel(10, jpnStudent, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlSelect, jpnSelect.right);
    }//GEN-LAST:event_btnStudentMouseClicked

    private void btnReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportsMouseClicked
        // TODO add your handling code here:
        btnReportsPressed();
        jpnSlider.nextPanel(10, jpnReports, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlEmpty, jpnSelect.right);
    }//GEN-LAST:event_btnReportsMouseClicked

    private void jcbSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSubjectActionPerformed
        // TODO add your handling code here:
        if(jcbSubjectsFilled){
            getControlData();
            ViewStudent(matricBoImpl.GetMatricBySubjectAndYear(selectedYear, selectedSubject.getSubject()));
        }
    }//GEN-LAST:event_jcbSubjectActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        //pnlNoDisplay.setVisible(true);
        if(tblModelViewStudents.getRowCount() > 0){
            alert.notify("Data.", 0);
            NotifyData nt = new NotifyData();
            nt.setNotifyData(alert.getpnlNotify(), alert.getlblMSGNotify(), alert.getlblIMGNotify(), "Data.", 0);
            Exporting ex = new Exporting();
            ex.setProgress(jpnProgress, lblProgress, tblModelViewStudents, nt,alert,selectedSubject.getSubject());
            ex.start();
        }else{
            Functions.warningMessage("Before exporting data, table must contain data.");
        }
    }//GEN-LAST:event_btnExportActionPerformed

    private void tblSearchStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchStudentMouseClicked
        // TODO add your handling code here:
        int row = tblSearchStudent.getSelectedRow();
        String idno = tblSearchStudent.getModel().getValueAt(row, 0).toString();
        String lastName = tblSearchStudent.getModel().getValueAt(row, 1).toString();
        String names = tblSearchStudent.getModel().getValueAt(row, 2).toString();
        if(idno.replace(" ", "").length() > 0){
            txtIDNo.setText(idno);
            txtLastName.setText(lastName);
            txtNames.setText(names);
            btnStudentPressed();
            jpnSlider.nextPanel(10, jpnStudent, jpnSlider.right);
            StudentMarks(matricBoImpl.GetMatricStudentMarks(idno));
        }else{
            alert.notify("ID number can't be empty.", 0);
        }
    }//GEN-LAST:event_tblSearchStudentMouseClicked

    private void txtSearchStudentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentKeyReleased
        // TODO add your handling code here:
        String search = txtSearchStudent.getText();
        if(search.length() > 1){
            SearchStudent(matricBoImpl.SearchMatricStudent(search, selectedYear));
        }
    }//GEN-LAST:event_txtSearchStudentKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Functions.infoMessage("Search student by lastname or firstnames only.");
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnSaveProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveProfileActionPerformed
        // TODO add your handling code here:
        if(IsAllowed()){
            String idno = txtIDNo.getText();
            String lastName = txtLastName.getText();
            String names = txtNames.getText();
            if(idno.length() > 0 && lastName.length() > 0 && names.length() > 0){
                int edited = matricBoImpl.EditProfile(idno, lastName, names);
                if(edited > 0){
                    alert.notify("Changes got saved.", 1);
                }else{
                    alert.notify("No changes saved.", 0);
                }
            }else{
                alert.notify("All fields must be filled.",0);
            }
        }else{
            alert.notify("Access denied!",0);
        }
    }//GEN-LAST:event_btnSaveProfileActionPerformed

    private void btnPrintReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReportsActionPerformed
        // TODO add your handling code here:
        p = new Printing();
        p.setPrinting(jpnProgress, lblProgress, alert, btnPrintReports,  selectedYear,this);
        p.start();
    }//GEN-LAST:event_btnPrintReportsActionPerformed

    private void btnStopProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopProcessActionPerformed
        // TODO add your handling code here:
        if(p!=null){
            p.Cancel();
        }else{
            alert.notify("Start print job first.",0);
        }
    }//GEN-LAST:event_btnStopProcessActionPerformed

    private void btnStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaffMouseClicked
        // TODO add your handling code here:
        btnStaffPressed();
        jpnSlider.nextPanel(10, jpnStaff, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlSelect, jpnSelect.right);
    }//GEN-LAST:event_btnStaffMouseClicked

    private void btnStaffSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSubjectActionPerformed
        // TODO add your handling code here:
        String staffNo = txtStaffNo.getText();
        if(staffNo.length() > 0){
            getControlData();
            if(!selectedSubject.getSubject().equalsIgnoreCase("SELECT")){
                StaffSubject sb = new StaffSubject();
                sb.setPersonId(Integer.parseInt(staffNo));
                sb.setSubjectCode(selectedSubject.getSubject());
                sb.setSyear(selectedYear);
                int added = staffSubjectBoImpl.AddStaffSubject(sb);
                NotifyData nt = new NotifyData();
                if(added > 0){
                    fillStaffSubjects(staffSubjectBoImpl.GetStaffSubjects(Integer.parseInt(staffNo)));
//                    nt.setNotifyData(pnlNotify, lblMSGNotify, lblIMGNotify, "Staff subject registered.", 1);
//                    jpnNotifySlider.notify(pnlNotify, nt);
                }else{
//                    nt.setNotifyData(pnlNotify, lblMSGNotify, lblIMGNotify, "Staff subject not registered.", 0);
//                    jpnNotifySlider.notify(pnlNotify, nt);
                }
            }else{
                Functions.errorMessage("Select subject first.");
            }
        }else{
            Functions.errorMessage("Staff no field must not me empty.");
        }
    }//GEN-LAST:event_btnStaffSubjectActionPerformed

    private void btnRemoveStaffSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStaffSubjectActionPerformed
        // TODO add your handling code here:
        if(selectedStaffSubject != null){
            String staffNo = txtStaffNo.getText();
            int removed = staffSubjectBoImpl.RemoveStaffSubject(selectedStaffSubject);
            if(removed > 0){
                getControlData();
                fillStaffSubjects(staffSubjectBoImpl.GetStaffSubjects(Integer.parseInt(staffNo)));
                alert.notify("Subject removed.", 1);
                selectedStaffSubject = null;
            }else{
                alert.notify("Subject could not be removed.", 0);
            }
        }else{
            Functions.errorMessage("Select a record to remove before.");
        }
    }//GEN-LAST:event_btnRemoveStaffSubjectActionPerformed

    private void tblStaffListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffListMouseClicked
        // TODO add your handling code here:
        int row = tblStaffList.getSelectedRow();
        int staffNo = Integer.parseInt(tblStaffList.getModel().getValueAt(row, 0).toString());
        txtStaffNo.setText(staffNo+"");
        fillStaffSubjects(staffSubjectBoImpl.GetStaffSubjects(staffNo));
    }//GEN-LAST:event_tblStaffListMouseClicked

    private void btnBrowseStudentsCSVFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseStudentsCSVFileActionPerformed
        // TODO add your handling code here:
        try{
           String path = Functions.BrowseFile(this);
           if(path!=null){
            List<HashMap<String,Object>> students = Functions.readStudentsUploadFile(path);
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
            startProcess("Uploading please wait..");
            int saved = matricBoImpl.UploadMatricStudents(uploadStudents, selectedYear);
            if(saved > 0){
                alert.notify("Data uploaded.", 1);
                fillSubjects();
                tblModelUploadStudents.setRowCount(0);
            }else{
                alert.notify("Data not uploaded.",0);
            }
            endProgress();
        }else{
            Functions.warningMessage("Browse csv file first.");
        }
    }//GEN-LAST:event_btnUploadStudentsDataActionPerformed

    private void btnAuthorizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAuthorizeMouseClicked
        // TODO add your handling code here:
        btnAuthorizePressed();
        jpnSlider.nextPanel(10, jpnAuthorize, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlEmpty, jpnSelect.right);
    }//GEN-LAST:event_btnAuthorizeMouseClicked

    private void tblStaffToAuthorizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffToAuthorizeMouseClicked
        // TODO add your handling code here:
        int row = tblStaffToAuthorize.getSelectedRow();
        rowOfStaffToAuthorize = row;
        int staffNo = Integer.parseInt(tblStaffToAuthorize.getModel().getValueAt(row, 0).toString());
        String subject = tblStaffToAuthorize.getModel().getValueAt(row, 1).toString();
        int year = Integer.parseInt(tblStaffToAuthorize.getModel().getValueAt(row, 2).toString());
        staffSubjectClicked = new StaffSubject();
        staffSubjectClicked.setStaffSubject(staffNo, subject, year, "");
        fillMarksToAuthorize(matricBoImpl.GetMarksToAuthorize(staffNo, year, subject));
    }//GEN-LAST:event_tblStaffToAuthorizeMouseClicked

    private void btnAcceptAuthorizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptAuthorizationActionPerformed
        // TODO add your handling code here:
        if(IsAllowed()){
            if(tblModelMarksToAuthorize.getRowCount() > 0){
                if(staffSubjectClicked != null){
                    startProcess("Authorizing an update..");
                    int row = 0;
                    List<HashMap<String,Object>> marks = new ArrayList<>();
                    while(row < tblModelMarksToAuthorize.getRowCount()){
                        boolean checked = (tblModelMarksToAuthorize.getValueAt(row, 8)!= null)? 
                                (boolean)tblModelMarksToAuthorize.getValueAt(row, 8):false;
                        if(checked){
                            String idno =  tblModelMarksToAuthorize.getValueAt(row, 0).toString();
                            String lastName =  tblModelMarksToAuthorize.getValueAt(row, 1).toString();
                            String firstName =  tblModelMarksToAuthorize.getValueAt(row, 2).toString();
                            String subject =  tblModelMarksToAuthorize.getValueAt(row, 3).toString();
                            String year =  tblModelMarksToAuthorize.getValueAt(row, 4).toString();
                            String term1 =  tblModelMarksToAuthorize.getValueAt(row, 5).toString();
                            String term2 =  tblModelMarksToAuthorize.getValueAt(row, 6).toString();
                            String term3 =  tblModelMarksToAuthorize.getValueAt(row, 7).toString();

                            HashMap<String,Object> mark = new HashMap<>(5);
                            mark.put("idno", idno);
                            mark.put("lastName", lastName);
                            mark.put("firstName", firstName);
                            mark.put("subject", subject);
                            mark.put("year", year);
                            mark.put("term1", term1);
                            mark.put("term2", term2);
                            mark.put("term3", term3);
                            marks.add(mark);
                        }
                        row++;
                    }
                    int authorized = matricBoImpl.AuthorizeMarks(marks, staffSubjectClicked.getPersonId(), loggedInUser.getPersonId());
                    if(authorized > 0){
                        alert.notify("Selected marks authorized.", 1);
                        tblModelUploadStudents.setRowCount(0);
                        endProgress();
                    }else{
                        alert.notify("No marks authorized.", 0);
                        endProgress();
                    }
                    fillMarksToAuthorize(matricBoImpl.GetMarksToAuthorize(staffSubjectClicked.getPersonId(), 
                                                                        staffSubjectClicked.getSyear(),
                                                                        staffSubjectClicked.getSubjectCode()));
                    fillLogStaffAuthorized();                                                  
                }else{
                    Functions.infoMessage("Select marks to authorize first.");
                }
            }else{
                alert.notify("No marks selected for authorization.", 0);
            }
        }else{
            alert.notify("Access denied!",0);
        }
    }//GEN-LAST:event_btnAcceptAuthorizationActionPerformed

    private void rbtnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCheckAllActionPerformed
        // TODO add your handling code here:
        displayMarksToAuthorize(true);
    }//GEN-LAST:event_rbtnCheckAllActionPerformed

    private void rbtnUncheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnUncheckAllActionPerformed
        // TODO add your handling code here:
        displayMarksToAuthorize(false);
    }//GEN-LAST:event_rbtnUncheckAllActionPerformed

    private void btnStudentReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentReportActionPerformed
        // TODO add your handling code here:
        String idno = txtIDNo.getText();
        if(idno.length() > 0){
            jasperMatric.PrintStudentReport(idno,selectedYear);
        }else{
            alert.notify("ID no must not be empty.",0);
        }
    }//GEN-LAST:event_btnStudentReportActionPerformed

    private void btnPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPasswordMouseClicked
        // TODO add your handling code here:
        btnPasswordPressed();
        jpnSlider.nextPanel(10, jpnPassword, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlEmpty, jpnSelect.right);
    }//GEN-LAST:event_btnPasswordMouseClicked

    private void btnChangeAdminPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeAdminPinActionPerformed
        // TODO add your handling code here:
        try{
            int adminId = Integer.parseInt(admId.getText());
            String oldPin = Functions.GetPassword(pinOld);
            String newPin = Functions.GetPassword(pinNew);

            if(oldPin.length() > 0 && newPin.length() > 0){
                boolean isValid = pinBoImpl.IsAdminPinValid(adminId, oldPin);
                if(isValid){
                    boolean updated = pinBoImpl.updateAdminPin(adminId, newPin);
                    if(updated){
                        alert.notify("Password got changed.", 1);
                    }else{
                        alert.notify("No changes saved.", 0);
                    }
                }else{
                    alert.notify("Old password incorrect.", 0);
                }
            }else{
                alert.notify("All fields must not be empty.", 0);
            }
        }catch(NumberFormatException ex){
            Functions.errorMessage(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnChangeAdminPinActionPerformed

    private void btnPersonalInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalInformationActionPerformed
        // TODO add your handling code here:
        new MatricNewStaffFrm().setVisible(true);
    }//GEN-LAST:event_btnPersonalInformationActionPerformed

    private void btnRegisterMatricStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterMatricStudentActionPerformed
        // TODO add your handling code here:
        String idno = txtASIDno.getText();
        String lastName = txtASLastNAme.getText();
        String names = txtASNames.getText();
        registerStudent(idno,lastName, names);
        StudentMarks(matricBoImpl.GetMatricStudentMarks(idno));
    }//GEN-LAST:event_btnRegisterMatricStudentActionPerformed

    private void btnSRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSRegisterActionPerformed
        // TODO add your handling code here:
        String idno = txtIDNo.getText();
        String lastName = txtLastName.getText();
        String names = txtNames.getText();
        registerStudent(idno,lastName, names);
    }//GEN-LAST:event_btnSRegisterActionPerformed
 
    private void btnAnalysisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnalysisMouseClicked
        // TODO add your handling code here:
        btnAnalysisPressed();
        jpnSlider.nextPanel(10, jpnAnalysis, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlEmpty, jpnSelect.right);
    }//GEN-LAST:event_btnAnalysisMouseClicked

    private void btnTerm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerm1ActionPerformed
        // TODO add your handling code here:
        term = "Term1";
        List<HashMap<String,Object>> analysis = matricBoImpl.GetAnalysisByTerm(term, selectedYear);
        fillTerm(analysis);
    }//GEN-LAST:event_btnTerm1ActionPerformed

    private void btnTerm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerm2ActionPerformed
        // TODO add your handling code here:
        term = "Term2";
        List<HashMap<String,Object>> analysis = matricBoImpl.GetAnalysisByTerm(term, selectedYear);
        fillTerm(analysis);
    }//GEN-LAST:event_btnTerm2ActionPerformed

    private void btnTerm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerm3ActionPerformed
        // TODO add your handling code here:
        term = "Term3";
        List<HashMap<String,Object>> analysis = matricBoImpl.GetAnalysisByTerm(term, selectedYear);
        fillTerm(analysis);
    }//GEN-LAST:event_btnTerm3ActionPerformed

    private void btnPrintAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintAnalysisActionPerformed
        // TODO add your handling code here:
        if(term != null){
            jasperMatric.GetAnalysisByTerm(term, selectedYear);
        }else{
            Functions.errorMessage("Select the term first.");
        }        
    }//GEN-LAST:event_btnPrintAnalysisActionPerformed

    private void tblStudentMarksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMarksMouseClicked
        // TODO add your handling code here:
        int row = tblStudentMarks.getSelectedRow();
        rowOfStudent = row;
        studentSubject = tblStudentMarks.getModel().getValueAt(row, 0).toString();
    }//GEN-LAST:event_tblStudentMarksMouseClicked

    private void btnDeregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeregisterActionPerformed
        // TODO add your handling code here:
        if(IsAllowed()){
            String idno = txtIDNo.getText();
            if(idno.length() > 0 && studentSubject != null){
                int removed = matricBoImpl.RemoveMatric(idno, selectedYear, studentSubject);
                if(removed > 0){
                    tblModelStudentMarks.removeRow(rowOfStudent);
                    alert.notify("Subject deregistered.", 1);
                    studentSubject = null;
                }else{
                    Functions.errorMessage("No changes were made.");
                }
            }else{
                Functions.errorMessage("ID number of student must not be empty.\n"
                        + "There must be a selected subject.");
            }
        }else{
            alert.notify("Access denied!",0);
        }
    }//GEN-LAST:event_btnDeregisterActionPerformed

    private void tblStaffSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffSubjectsMouseClicked
        // TODO add your handling code here:
        int row = tblStaffSubjects.getSelectedRow();
        selectedStaffSubject = new StaffSubject();
        selectedStaffSubject.setSubjectCode(tblStaffSubjects.getModel().getValueAt(row, 1).toString());
        selectedStaffSubject.setSyear(selectedYear);
        selectedStaffSubject.setPersonId(Integer.parseInt(txtStaffNo.getText()));
    }//GEN-LAST:event_tblStaffSubjectsMouseClicked

    private void txtSearchTeacherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTeacherKeyReleased
        // TODO add your handling code here:
        String search = txtSearchTeacher.getText();
        if(search.length() > 2){
            fillStaffList(staffSubjectBoImpl.SearchMatricStaff(search));
        }
    }//GEN-LAST:event_txtSearchTeacherKeyReleased

    private void tblLogStaffAuthorizedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLogStaffAuthorizedMouseClicked
        // TODO add your handling code here:
        int row = tblLogStaffAuthorized.getSelectedRow();
        int adminId = Integer.parseInt(tblLogStaffAuthorized.getModel().getValueAt(row, 0).toString());
        String subject = tblLogStaffAuthorized.getModel().getValueAt(row, 1).toString();
        int year = Integer.parseInt(tblLogStaffAuthorized.getModel().getValueAt(row, 2).toString());
        
        fillLogMarksAuthorized(matricBoImpl.GetLogMarksAuthorized(adminId, year, subject));
    }//GEN-LAST:event_tblLogStaffAuthorizedMouseClicked

    private void btnLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogMouseClicked
        // TODO add your handling code here:
        btnLogPressed();
        jpnSlider.nextPanel(10, jpnLog, jpnSlider.right);
        jpnSelect.nextPanel(1,pnlEmpty, jpnSelect.right);
        if(tblModelLogStaffAuthorized.getRowCount() == 0){
            fillLogStaffAuthorized();
        }
    }//GEN-LAST:event_btnLogMouseClicked

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
                new MatricAdminFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField admId;
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JButton btnAcceptAuthorization;
    private javax.swing.JPanel btnAnalysis;
    private javax.swing.JPanel btnAuthorize;
    private javax.swing.JButton btnBrowseStudentsCSVFile;
    private javax.swing.JButton btnChangeAdminPin;
    private javax.swing.JButton btnDeregister;
    private javax.swing.JButton btnExport;
    private javax.swing.ButtonGroup btnGroupCheck;
    private javax.swing.JPanel btnLog;
    private javax.swing.JPanel btnPassword;
    private javax.swing.JButton btnPersonalInformation;
    private javax.swing.JButton btnPrintAnalysis;
    private javax.swing.JButton btnPrintReports;
    private javax.swing.JButton btnRegisterMatricStudent;
    private javax.swing.JButton btnRemoveStaffSubject;
    private javax.swing.JPanel btnReports;
    private javax.swing.JButton btnSRegister;
    private javax.swing.JButton btnSaveProfile;
    private javax.swing.JPanel btnSearchStudent;
    private javax.swing.JPanel btnStaff;
    private javax.swing.JButton btnStaffSubject;
    private javax.swing.JButton btnStopProcess;
    private javax.swing.JPanel btnStudent;
    private javax.swing.JButton btnStudentReport;
    private javax.swing.JButton btnTerm1;
    private javax.swing.JButton btnTerm2;
    private javax.swing.JButton btnTerm3;
    private javax.swing.JButton btnUploadStudentsData;
    private javax.swing.JPanel btnViewStudents;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
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
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JComboBox<String> jcbSubject;
    private javax.swing.JPanel jpnAnalysis;
    private javax.swing.JPanel jpnAuthorize;
    private javax.swing.JPanel jpnControls;
    private javax.swing.JPanel jpnLog;
    private javax.swing.JPanel jpnPassword;
    private javax.swing.JPanel jpnProgress;
    private javax.swing.JPanel jpnReports;
    private javax.swing.JPanel jpnSearchStudent;
    private com.molorane.college.custom.JPanelSliding jpnSelect;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnStaff;
    private javax.swing.JPanel jpnStudent;
    private javax.swing.JPanel jpnViewStudents;
    private javax.swing.JPanel jpnlPassword;
    private javax.swing.JPasswordField jpswPIN;
    private javax.swing.JLabel lblNeedAuthorization;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblStaff;
    private javax.swing.JLabel lblYear;
    private javax.swing.JPasswordField pinNew;
    private javax.swing.JPasswordField pinOld;
    private javax.swing.JPanel pnlEmpty;
    private javax.swing.JPanel pnlSelect;
    private javax.swing.JRadioButton rbtnCheckAll;
    private javax.swing.JRadioButton rbtnUncheckAll;
    private javax.swing.JTable tblAnalysisTerm;
    private javax.swing.JTable tblLogMarksAuthorized;
    private javax.swing.JTable tblLogStaffAuthorized;
    private javax.swing.JTable tblMarksToAuthorize;
    private javax.swing.JTable tblSearchStudent;
    private javax.swing.JTable tblStaffList;
    private javax.swing.JTable tblStaffSubjects;
    private javax.swing.JTable tblStaffToAuthorize;
    private javax.swing.JTable tblStudentMarks;
    private javax.swing.JTable tblUploadStudents;
    private javax.swing.JTable tblViewStudents;
    private com.molorane.college.view.Controls.TitlePnl title;
    private javax.swing.JTextField txtASIDno;
    private javax.swing.JTextField txtASLastNAme;
    private javax.swing.JTextField txtASNames;
    private javax.swing.JTextField txtIDNo;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtNames;
    private javax.swing.JTextField txtSearchStudent;
    private javax.swing.JTextField txtSearchTeacher;
    private javax.swing.JTextField txtStaffNo;
    // End of variables declaration//GEN-END:variables
}
