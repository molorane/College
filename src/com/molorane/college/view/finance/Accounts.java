/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.finance;

import com.molorane.college.custom.LoginSession;
import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.EnrolmentBoImpl;
import com.molorane.college.bll.impl.PayScheduleBoImpl;
import com.molorane.college.bll.impl.PaySchedulePaymentBoImpl;
import com.molorane.college.bll.impl.PaymentCategoryBoImpl;
import com.molorane.college.bll.impl.PersonBoImpl;
import com.molorane.college.bll.impl.TuitionBoImpl;
import com.molorane.college.jasperservice.JasperAccounts;
import com.molorane.college.jasperservice.JasperEnrolments;
import com.molorane.college.jasperservice.JasperPerson;
import com.molorane.college.model.PaySchedule;
import com.molorane.college.model.PaySchedulePayment;
import com.molorane.college.model.PaymentCategory;
import com.molorane.college.model.Person;
import com.molorane.college.model.Term;
import com.molorane.college.model.Tuition;
import com.molorane.college.model.User;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mothusi Molorane
 */
public class Accounts extends javax.swing.JFrame {
    
    private final PersonBoImpl personImpl = new PersonBoImpl();
    private final TuitionBoImpl tuitionImpl = new TuitionBoImpl();
    private final PaymentCategoryBoImpl paymentCategoryBoImpl = new PaymentCategoryBoImpl();
    private final EnrolmentBoImpl enrolmentImpl = new EnrolmentBoImpl();
    private final PayScheduleBoImpl payScheduleImpl = new PayScheduleBoImpl();
    private final PaySchedulePaymentBoImpl paySchedulePaymentImpl = new PaySchedulePaymentBoImpl();
        
    private DefaultTableModel tblModelSearchList;
    private DefaultTableModel tblModeltblAllTransactions;
    private DefaultTableModel tblModeltblTuitionAccount;
    
    
    private DefaultTableModel tblModelEnrolledCourses;
    private DefaultTableModel tblModelEnrolledCourseModules;
    
    
    private DefaultTableModel tblModelPaySchedules;
    private DefaultTableModel tblModelPaySchedulePayments;
    
    private String selectedCourseCode;
    private String selectedCourse;
    private String selectedTerm;
    private int selectedScheduleId;
    private int selectedPaymentScheduleRow = -1;
    private double scheduleBalance;
    private int selectedSchedulePaymentId = -1;
    private int PaymentSchedulePaymentRow = -1;
    
    private long personId;
    private final User loggedInUser;
    
    private double termScheduleTotal = 0;
    private double termTotalDebitBalance = 0;
    private double termTtotalCreditBalance = 0;
    
    private boolean jcbTPaymentScheduleClicked = false;
    
    
    private int selectedTransactionRow = -1;
    private int receipt_no = -1;
    
    private final String beforeNavigation = "Before navigating you must first specify the student by typing the student no\n"
                    + "or by searching the student then selecting the desired student on the search list.";

    private final JasperPerson jasperPerson = new JasperPerson();
    private final JasperAccounts jasperAccounts = new JasperAccounts();
    private final JasperEnrolments jasperEnrolments = new JasperEnrolments();

    /**
     * Creates new form NewJFrame
     */
    
    public Accounts() {
        super("Accounts - "+Functions.appName());
        initComponents();
        
        
        loggedInUser = LoginSession.GetSessionUser();
        
        paymentCategoryBoImpl.fillComboBoxPaymentCategory(jcbCategory);
        paymentCategoryBoImpl.fillComboBoxPaymentCategory(jcbCategoryDebit);
        
        // set table models
        tblModelSearchList = (DefaultTableModel)jtblSearchList.getModel(); 
        tblModeltblAllTransactions = (DefaultTableModel)jtblAllTransactions.getModel(); 
        tblModeltblTuitionAccount = (DefaultTableModel)jtblTuitionAccount.getModel();
        
        
        tblModelEnrolledCourses = (DefaultTableModel)jtblEnrolledCourses.getModel(); 
        tblModelEnrolledCourseModules = (DefaultTableModel)jtblEnrolledCourseModules.getModel();
        
        
        tblModelPaySchedules = (DefaultTableModel)jtblPaymentSchedules.getModel();
        tblModelPaySchedulePayments = (DefaultTableModel)jtblPaymentSchedulePayments.getModel();
        
        // set single selection mode for tables
        jtblAllTransactions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblSearchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblEnrolledCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtblPaymentSchedules.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jtblPaymentSchedules.setDefaultRenderer(String.class, new Accounts.CustomTableRenderer());
        
        Functions.setLoggedInUser(lblHomeLoggedIn,loggedInUser);
        
        Functions.initForm(Accounts.this);
    }
    
    void btnHomePressed(){
        setColor(btnHome);
        resetColor(btnTransactions);
        resetColor(btnTuition);
        resetColor(btnPrintService);
        resetColor(btnStudentRegistrations);
        resetColor(btnPaymentSchedules);
    }
    
    void btnTransactionsPressed(){
       resetColor(btnHome);
       setColor(btnTransactions);
       resetColor(btnTuition);
       resetColor(btnPrintService);
       resetColor(btnStudentRegistrations);
       resetColor(btnPaymentSchedules);
    }
    
    void btnPrintServicePressed(){
       resetColor(btnHome);
       resetColor(btnTransactions);
       setColor(btnTuition);
       resetColor(btnPrintService);
       resetColor(btnStudentRegistrations);
       resetColor(btnPaymentSchedules);
    }
    
    void btnTuitionPressed(){
       resetColor(btnHome);
       resetColor(btnTransactions);
       resetColor(btnTuition);
       setColor(btnPrintService);
       resetColor(btnStudentRegistrations);
       resetColor(btnPaymentSchedules);
    }
    
    void btnStudentRegistrationsPressed(){
       resetColor(btnHome);
       resetColor(btnTransactions);
       resetColor(btnTuition);
       resetColor(btnPrintService);
       setColor(btnStudentRegistrations);
       resetColor(btnPaymentSchedules);
    }
    
    void btnPaymentSchedulesPressed(){
       resetColor(btnHome);
       resetColor(btnTransactions);
       resetColor(btnTuition);
       resetColor(btnPrintService);
       resetColor(btnStudentRegistrations);
       setColor(btnPaymentSchedules);
    }
    
    void setColor(JPanel panel){
        panel.setBackground(new Color(64,43,100));
    }
    
    void resetColor(JPanel panel){
       panel.setBackground(new Color(54,33,89)); 
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
    
    private void studentTransactions(){        
        ArrayList<Tuition> transactions = tuitionImpl.GetAllStudentTransactions(personId);
        tblModeltblAllTransactions.setRowCount(0);
        transactions.forEach((bean) -> {
            tblModeltblAllTransactions.addRow(new Object[]{
               bean.getReceipt_no(),
               bean.getTerm(),
               bean.getTransaction_date(),
               bean.getCategory(),
               bean.getRef_no(),
               (bean.getDebit()==0.0)? "": Functions.GetCurrency(bean.getDebit()),
               (bean.getCredit()==0.0)? "": Functions.GetCurrency(bean.getCredit())
            });
        });      
    }
    
    private void studentTuitionAccount(){        
        List<HashMap<String, Object>> list = tuitionImpl.GetStudentTuitionAccount(personId);
        tblModeltblTuitionAccount.setRowCount(0);
        list.forEach((row) -> {
            double debit = Double.parseDouble(row.get("debit").toString());
            double credit = Double.parseDouble(row.get("credit").toString());
            double balance = Double.parseDouble(row.get("balance").toString());
            double nbalance = (balance<0)?-1*balance:balance;
            String balanceView = Functions.GetCurrencyZAR(nbalance);
            tblModeltblTuitionAccount.addRow(new Object[]{
               row.get("receipt_no"),
               row.get("term"),
               row.get("transaction_date"),
               row.get("category"),
               (debit==0.0)? "":Functions.GetCurrency(debit),
               (credit==0.0)? "":Functions.GetCurrency(credit),
               (balance<0)? balanceView+" Dr" : balanceView+" Cr"
            });
        });      
    }
    
    public void refreshStudentRecords(){
        studentTransactions();
        studentTuitionAccount();
    }
    
    public void createStudentSession(){        
        Person p = personImpl.GetPerson(personId);
        if(p!=null){
            personId = p.getPersonId();
            txtStudentNoSession.setText(p.getPersonId()+"");
            lblUserSession.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
            lblRCStudentName.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
            lblEStudentName.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
            lblPEStudentName.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
            lblSRStudentName.setText(p.getPersonId()+" - "+p.getFirstName()+" "+p.getLastName()+" "+p.getOtherName());
            refreshStudentRecords();
            jcbTPaymentScheduleClicked = false;
            personImpl.fillComboBoxPersonTerm(jcbTerm,personId);
            personImpl.fillComboBoxPersonTerm(jcbTermDebit,personId);
            personImpl.fillComboBoxPersonTerm(jcbTermPaymentSchedules,personId);
            personImpl.fillComboBoxPersonTerm(jcbSRTerms,personId);
            jcbTPaymentScheduleClicked = true;
            tblModelEnrolledCourses.setRowCount(0);
            tblModelEnrolledCourseModules.setRowCount(0);
            StudentEnrolledCourses(enrolmentImpl.GetStudentCourseEnrolments(personId));
        }else{
            personId = 0;
            lblUserSession.setText("");
            lblRCStudentName.setText("");
            lblEStudentName.setText("");
            lblPEStudentName.setText("");
            lblSRStudentName.setText("");
        }
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
    
    public void PaySchedules(List<HashMap<String, Object>> paySchedules){
        tblModelPaySchedules.setRowCount(0);
        termScheduleTotal = 0;
        paySchedules.forEach((row) -> {
            termScheduleTotal += Double.parseDouble(row.get("amount").toString());
            tblModelPaySchedules.addRow(new Object[]{
                row.get("payscheduleId"),
                row.get("scheduledate"),
               Functions.GetCurrency(Double.parseDouble(row.get("amount").toString())),
               Functions.GetCurrency(Double.parseDouble(row.get("balance").toString()))
            });
        });
    }
    
    public void PaySchedulePayments(ArrayList<PaySchedulePayment> paySchedulePayments){
        tblModelPaySchedulePayments.setRowCount(0);
        paySchedulePayments.forEach((row) -> {
            tblModelPaySchedulePayments.addRow(new Object[]{
                row.getPspId(),
                row.getPaymentdate(),
               Functions.GetCurrency(row.getAmount())
             });
        });
    }
    
    public class CustomTableRenderer extends DefaultTableCellRenderer{
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            Component c = super.getTableCellRendererComponent(jtblPaymentSchedules, value, isSelected, hasFocus, row, column);
            
           
           double balance = Double.parseDouble(jtblPaymentSchedules.getValueAt(row, 4).toString());
           System.out.println("called render");
            if(column == 3 && balance > 0){
               c.setForeground(Color.RED);
           }
           return c;
        }
    }
    
    public void StudentTermAccount(List<HashMap<String, Object>> studentTermAccount){
        if(studentTermAccount.size() > 0){
            studentTermAccount.forEach((row) -> {
                termTotalDebitBalance = Double.parseDouble(row.get("debit").toString());
                termTtotalCreditBalance = Double.parseDouble(row.get("credit").toString());
                
                lblTermDebitBalance.setText(Functions.GetCurrency(termTotalDebitBalance));
                lblTermCreditBalance.setText(Functions.GetCurrency(termTtotalCreditBalance));
                
                double balance = Double.parseDouble(row.get("balance").toString());
                if(balance < 0){
                  lblTermBalance.setText((Functions.GetCurrency(-1*balance))+"");
                  lblTermBalance.setForeground(Color.RED);
                }else{
                  lblTermBalance.setText(Functions.GetCurrency(balance)+"");
                  lblTermBalance.setForeground(Color.BLACK);
                }
            });
        }else{
            lblTermDebitBalance.setText("");
            lblTermCreditBalance.setText("");
            lblTermBalance.setText("");
        }
    }
    
    
    public void performTransaction(double actualPayment, double newBalance){
        
        Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();
        
        List<HashMap<String, Object>> previousUnpaidSchedules = paySchedulePaymentImpl.PreviousUnpaidPaySchedule(personId, term.getTermId(), selectedScheduleId);

        if(previousUnpaidSchedules.isEmpty()){

            PaySchedulePayment psp = new PaySchedulePayment();
            psp.setAmount(actualPayment);
            psp.setPayscheduleId(selectedScheduleId);


            Tuition t = new Tuition();
            t.setPersonId(personId);
            t.setTermId(term.getTermId());
            t.setCredit(actualPayment);
            t.setRecordedBy(loggedInUser.getPersonId());
            t.setRef_no(" ");
            t.setCategoryId(2);
            t.setMethodId(7);

            int paid = paySchedulePaymentImpl.TransactionPaySchedule(psp, t);

            if(paid > 0){
                jtblPaymentSchedules.getModel().setValueAt(Functions.GetCurrency(newBalance), selectedPaymentScheduleRow, 3);
                PaySchedulePayments(paySchedulePaymentImpl.GetStudentPaySchedulePayments(selectedScheduleId));
                StudentTermAccount(tuitionImpl.GetStudentTermAccount(personId, term.getTermId()));
                Functions.successMessage("Transaction payment successful!");
            }else{
                Functions.errorMessage("Transaction payment not successful!");
            }
        }else{
            Functions.errorMessage("Number of previous unpaid schedules is: "+previousUnpaidSchedules.size()+"\n"+
                                    "Please ensure that all previous schedules are fully paid before moving to the next.\n"+
                                    "N.B: Schedule is unpaid if its corresponding balance is greator than zero.");
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

        jpnAccounts = new javax.swing.JPanel();
        jpnSideMenu = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnTransactions = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnPrintService = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnTuition = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnStudentRegistrations = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnPaymentSchedules = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnHome = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblHomeLoggedIn = new javax.swing.JLabel();
        lblUserSession = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtblSearchList = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtStudentNoSession = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        txtSearchStudentSession = new javax.swing.JTextField();
        jpnTransactions = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        lblPEStudentName = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtblAllTransactions = new javax.swing.JTable();
        jbtnDeleteTransaction = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jcbTerm = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbCategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtReference = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnAddCategory = new javax.swing.JButton();
        jbtnPaynow = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jcbTermDebit = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jcbCategoryDebit = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        txtReferenceDebit = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtAmountDebit = new javax.swing.JTextField();
        btnAddCategoryDebit = new javax.swing.JButton();
        jbtnPaynow1 = new javax.swing.JButton();
        jpnTuitionAccount = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        lblEStudentName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtblTuitionAccount = new javax.swing.JTable();
        jpnPrintService = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lblRCStudentName = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jpnStudentRegistrations = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        lblSRStudentName = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jcbSRTerms = new javax.swing.JComboBox<>();
        jbtnSRPrint = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtblEnrolledCourseModules = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtblEnrolledCourses = new javax.swing.JTable();
        jpnPaymentSchedule = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jcbTermPaymentSchedules = new javax.swing.JComboBox<>();
        jbtnTuitionFeeAccount = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtActualPayment = new javax.swing.JTextField();
        jbtnPaySchedule = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jbtnPayBalance = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jcbPaymentMethod = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jxdScheduleDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel24 = new javax.swing.JLabel();
        txtScheduleAmount = new javax.swing.JTextField();
        jbtnAddSchedulePayment = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lblTermDebitBalance = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblTermCreditBalance = new javax.swing.JLabel();
        lblTermBalance = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtblPaymentSchedules = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jbtnDeleteSchedule = new javax.swing.JButton();
        jbtnStudentTermSchedule = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtblPaymentSchedulePayments = new javax.swing.JTable();
        jbtnDeletePayment = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("ACCOUNT");

        btnHome.setBackground(new java.awt.Color(64, 43, 100));
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/home.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Home");

        javax.swing.GroupLayout btnHomeLayout = new javax.swing.GroupLayout(btnHome);
        btnHome.setLayout(btnHomeLayout);
        btnHomeLayout.setHorizontalGroup(
            btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnHomeLayout.setVerticalGroup(
            btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnTransactions.setBackground(new java.awt.Color(54, 33, 89));
        btnTransactions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransactionsMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/shopping_basket_add.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Transactions");

        javax.swing.GroupLayout btnTransactionsLayout = new javax.swing.GroupLayout(btnTransactions);
        btnTransactions.setLayout(btnTransactionsLayout);
        btnTransactionsLayout.setHorizontalGroup(
            btnTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTransactionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnTransactionsLayout.setVerticalGroup(
            btnTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTransactionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnPrintService.setBackground(new java.awt.Color(54, 33, 89));
        btnPrintService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintServiceMouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Print Service");

        javax.swing.GroupLayout btnPrintServiceLayout = new javax.swing.GroupLayout(btnPrintService);
        btnPrintService.setLayout(btnPrintServiceLayout);
        btnPrintServiceLayout.setHorizontalGroup(
            btnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPrintServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnPrintServiceLayout.setVerticalGroup(
            btnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPrintServiceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnTuition.setBackground(new java.awt.Color(54, 33, 89));
        btnTuition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTuition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTuitionMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/money-bag.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Tuition Account");

        javax.swing.GroupLayout btnTuitionLayout = new javax.swing.GroupLayout(btnTuition);
        btnTuition.setLayout(btnTuitionLayout);
        btnTuitionLayout.setHorizontalGroup(
            btnTuitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTuitionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnTuitionLayout.setVerticalGroup(
            btnTuitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTuitionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnTuitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        btnStudentRegistrations.setBackground(new java.awt.Color(54, 33, 89));
        btnStudentRegistrations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentRegistrationsMouseClicked(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/courses.png"))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Student Registrations");

        javax.swing.GroupLayout btnStudentRegistrationsLayout = new javax.swing.GroupLayout(btnStudentRegistrations);
        btnStudentRegistrations.setLayout(btnStudentRegistrationsLayout);
        btnStudentRegistrationsLayout.setHorizontalGroup(
            btnStudentRegistrationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentRegistrationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStudentRegistrationsLayout.setVerticalGroup(
            btnStudentRegistrationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnStudentRegistrationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnStudentRegistrationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnStudentRegistrationsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnPaymentSchedules.setBackground(new java.awt.Color(54, 33, 89));
        btnPaymentSchedules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPaymentSchedulesMouseClicked(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/analytics.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("Payment Schedules");

        javax.swing.GroupLayout btnPaymentSchedulesLayout = new javax.swing.GroupLayout(btnPaymentSchedules);
        btnPaymentSchedules.setLayout(btnPaymentSchedulesLayout);
        btnPaymentSchedulesLayout.setHorizontalGroup(
            btnPaymentSchedulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPaymentSchedulesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnPaymentSchedulesLayout.setVerticalGroup(
            btnPaymentSchedulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPaymentSchedulesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnPaymentSchedulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnPaymentSchedulesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel19))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnSideMenuLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
                        .addComponent(btnTransactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTuition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrintService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStudentRegistrations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnPaymentSchedules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTuition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrintService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudentRegistrations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPaymentSchedules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(Functions.pnlBackgroundTop());
        jPanel3.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Student:");

        lblHomeLoggedIn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblHomeLoggedIn.setForeground(new java.awt.Color(204, 204, 204));
        lblHomeLoggedIn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHomeLoggedIn.setText("ADMIN: Mohlomi M");

        lblUserSession.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUserSession.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblHomeLoggedIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHomeLoggedIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblUserSession, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtblSearchList.setAutoCreateRowSorter(true);
        jtblSearchList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblSearchList.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblSearchList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student No", "Id No", "Last Name", "First Name", "Other Name", "Gender", "Dob", "Nationality"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSearchList.setGridColor(new java.awt.Color(204, 204, 204));
        jtblSearchList.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblSearchList.setRowHeight(40);
        jtblSearchList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblSearchListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtblSearchList);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
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
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
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
            .addGroup(jpnHomeLayout.createSequentialGroup()
                .addGroup(jpnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jpnHomeLayout.setVerticalGroup(
            jpnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHomeLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnHome, "card2");

        jPanel1.setBackground(new java.awt.Color(122, 72, 221));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(204, 204, 204));
        jLabel53.setText("Student:");

        lblPEStudentName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPEStudentName.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPEStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPEStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jtblAllTransactions.setAutoCreateRowSorter(true);
        jtblAllTransactions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblAllTransactions.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblAllTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rec#", "Term", "Date", "Category", "Ref#", "Debit", "Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblAllTransactions.setGridColor(new java.awt.Color(204, 204, 204));
        jtblAllTransactions.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblAllTransactions.setRowHeight(40);
        jtblAllTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAllTransactionsMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jtblAllTransactions);

        jbtnDeleteTransaction.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnDeleteTransaction.setForeground(new java.awt.Color(204, 0, 0));
        jbtnDeleteTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/trash.png"))); // NOI18N
        jbtnDeleteTransaction.setText("DELETE");
        jbtnDeleteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteTransactionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnDeleteTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnDeleteTransaction))
        );

        jTabbedPane1.addTab("Student Transactions", jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Term:");

        jcbTerm.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jcbTerm.setForeground(new java.awt.Color(51, 51, 51));
        jcbTerm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Category:");

        jcbCategory.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jcbCategory.setForeground(new java.awt.Color(51, 51, 51));
        jcbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Reference:");

        txtReference.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtReference.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Amount (ZAR):");

        txtAmount.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtAmount.setForeground(new java.awt.Color(51, 51, 51));

        btnAddCategory.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnAddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });

        jbtnPaynow.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jbtnPaynow.setForeground(new java.awt.Color(51, 51, 51));
        jbtnPaynow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/shopping_basket_add.png"))); // NOI18N
        jbtnPaynow.setText("CREDIT STUDENT");
        jbtnPaynow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPaynowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnPaynow, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jcbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAddCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jcbTerm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtReference, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addComponent(txtAmount)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jcbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jbtnPaynow)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(392, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Credit Student", jPanel2);

        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Term:");

        jcbTermDebit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jcbTermDebit.setForeground(new java.awt.Color(51, 51, 51));
        jcbTermDebit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Category:");

        jcbCategoryDebit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jcbCategoryDebit.setForeground(new java.awt.Color(51, 51, 51));
        jcbCategoryDebit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Reference:");

        txtReferenceDebit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtReferenceDebit.setForeground(new java.awt.Color(51, 51, 51));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Amount (ZAR):");

        txtAmountDebit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtAmountDebit.setForeground(new java.awt.Color(51, 51, 51));

        btnAddCategoryDebit.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnAddCategoryDebit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        btnAddCategoryDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryDebitActionPerformed(evt);
            }
        });

        jbtnPaynow1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jbtnPaynow1.setForeground(new java.awt.Color(51, 51, 51));
        jbtnPaynow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/shopping_basket_add.png"))); // NOI18N
        jbtnPaynow1.setText("DEBIT STUDENT");
        jbtnPaynow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPaynow1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnPaynow1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel22Layout.createSequentialGroup()
                                    .addComponent(jcbCategoryDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAddCategoryDebit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jcbTermDebit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtReferenceDebit, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addComponent(txtAmountDebit)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jcbTermDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddCategoryDebit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jcbCategoryDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtReferenceDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtAmountDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jbtnPaynow1)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(392, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Debit Student", jPanel21);

        javax.swing.GroupLayout jpnTransactionsLayout = new javax.swing.GroupLayout(jpnTransactions);
        jpnTransactions.setLayout(jpnTransactionsLayout);
        jpnTransactionsLayout.setHorizontalGroup(
            jpnTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator4)
            .addGroup(jpnTransactionsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTransactionsLayout.setVerticalGroup(
            jpnTransactionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTransactionsLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jpnSlider.add(jpnTransactions, "card3");

        jpnTuitionAccount.setBackground(new java.awt.Color(204, 204, 204));

        jPanel11.setBackground(new java.awt.Color(122, 72, 221));
        jPanel11.setToolTipText("");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(204, 204, 204));
        jLabel51.setText("Student:");

        lblEStudentName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblEStudentName.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jtblTuitionAccount.setAutoCreateRowSorter(true);
        jtblTuitionAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblTuitionAccount.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblTuitionAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref#", "Term", "Date", "Category", "Debit", "Credit", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblTuitionAccount.setGridColor(new java.awt.Color(204, 204, 204));
        jtblTuitionAccount.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblTuitionAccount.setRowHeight(40);
        jtblTuitionAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTuitionAccountMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jtblTuitionAccount);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Tuition Account", jPanel5);

        javax.swing.GroupLayout jpnTuitionAccountLayout = new javax.swing.GroupLayout(jpnTuitionAccount);
        jpnTuitionAccount.setLayout(jpnTuitionAccountLayout);
        jpnTuitionAccountLayout.setHorizontalGroup(
            jpnTuitionAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(jpnTuitionAccountLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTuitionAccountLayout.setVerticalGroup(
            jpnTuitionAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTuitionAccountLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jpnSlider.add(jpnTuitionAccount, "card3");

        jPanel10.setBackground(new java.awt.Color(122, 72, 221));
        jPanel10.setToolTipText("");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 204, 204));
        jLabel50.setText("Student:");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(204, 204, 204));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("PRINT SERVICES");

        lblRCStudentName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblRCStudentName.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRCStudentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRCStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        jToggleButton1.setText("PRINT");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnPrintServiceLayout = new javax.swing.GroupLayout(jpnPrintService);
        jpnPrintService.setLayout(jpnPrintServiceLayout);
        jpnPrintServiceLayout.setHorizontalGroup(
            jpnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrintServiceLayout.createSequentialGroup()
                .addContainerGap(341, Short.MAX_VALUE)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(295, 295, 295))
        );
        jpnPrintServiceLayout.setVerticalGroup(
            jpnPrintServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrintServiceLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(452, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnPrintService, "card3");

        jPanel12.setBackground(new java.awt.Color(122, 72, 221));
        jPanel12.setToolTipText("");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 204, 204));
        jLabel54.setText("Student:");

        lblSRStudentName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSRStudentName.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSRStudentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(lblSRStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jcbSRTerms.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbSRTerms.setForeground(new java.awt.Color(51, 51, 51));

        jbtnSRPrint.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbtnSRPrint.setForeground(new java.awt.Color(51, 51, 51));
        jbtnSRPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        jbtnSRPrint.setText("Print");
        jbtnSRPrint.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnSRPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSRPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbSRTerms, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSRPrint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnSRPrint)
                    .addComponent(jcbSRTerms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtblEnrolledCourseModules.setAutoCreateRowSorter(true);
        jtblEnrolledCourseModules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblEnrolledCourseModules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblEnrolledCourseModules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module Code", "Module"
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
        jScrollPane11.setViewportView(jtblEnrolledCourseModules);

        jtblEnrolledCourses.setAutoCreateRowSorter(true);
        jtblEnrolledCourses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblEnrolledCourses.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblEnrolledCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Code", "Course", "Term", "Campus"
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
        jScrollPane10.setViewportView(jtblEnrolledCourses);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnStudentRegistrationsLayout = new javax.swing.GroupLayout(jpnStudentRegistrations);
        jpnStudentRegistrations.setLayout(jpnStudentRegistrationsLayout);
        jpnStudentRegistrationsLayout.setHorizontalGroup(
            jpnStudentRegistrationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnStudentRegistrationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnStudentRegistrationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpnStudentRegistrationsLayout.setVerticalGroup(
            jpnStudentRegistrationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStudentRegistrationsLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnStudentRegistrations, "card6");

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Term:");

        jcbTermPaymentSchedules.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbTermPaymentSchedules.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data" }));
        jcbTermPaymentSchedules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTermPaymentSchedulesActionPerformed(evt);
            }
        });

        jbtnTuitionFeeAccount.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnTuitionFeeAccount.setForeground(new java.awt.Color(51, 51, 51));
        jbtnTuitionFeeAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        jbtnTuitionFeeAccount.setToolTipText("Print tuition fee account for the selected term");
        jbtnTuitionFeeAccount.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jbtnTuitionFeeAccount.setIconTextGap(10);
        jbtnTuitionFeeAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTuitionFeeAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTermPaymentSchedules, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTuitionFeeAccount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnTuitionFeeAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbTermPaymentSchedules, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Pay Amount:");

        txtActualPayment.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtActualPayment.setForeground(new java.awt.Color(51, 51, 51));

        jbtnPaySchedule.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnPaySchedule.setForeground(new java.awt.Color(51, 51, 51));
        jbtnPaySchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/shopping.png"))); // NOI18N
        jbtnPaySchedule.setText("PAY");
        jbtnPaySchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPayScheduleActionPerformed(evt);
            }
        });

        jbtnPayBalance.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jbtnPayBalance.setForeground(new java.awt.Color(51, 51, 51));
        jbtnPayBalance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/credit-card.png"))); // NOI18N
        jbtnPayBalance.setText("PAY BALANCE");
        jbtnPayBalance.setToolTipText("Pay balance for the selected schedule.");
        jbtnPayBalance.setIconTextGap(20);
        jbtnPayBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPayBalanceActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Method:");

        jcbPaymentMethod.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addComponent(jbtnPayBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtActualPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jcbPaymentMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnPaySchedule)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtActualPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jcbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPaySchedule))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnPayBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setText("Schedule:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Amount:");

        txtScheduleAmount.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jbtnAddSchedulePayment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbtnAddSchedulePayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/add.png"))); // NOI18N
        jbtnAddSchedulePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddSchedulePaymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jxdScheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txtScheduleAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnAddSchedulePayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnAddSchedulePayment)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jxdScheduleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(txtScheduleAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Total debit balance:");

        lblTermDebitBalance.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTermDebitBalance.setForeground(new java.awt.Color(51, 51, 51));
        lblTermDebitBalance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Total credit balance:");

        lblTermCreditBalance.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTermCreditBalance.setForeground(new java.awt.Color(51, 51, 51));
        lblTermCreditBalance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lblTermBalance.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTermBalance.setForeground(new java.awt.Color(51, 51, 51));
        lblTermBalance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Total balance:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTermDebitBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermCreditBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(lblTermDebitBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTermCreditBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTermBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Payment Schedule", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 51))); // NOI18N

        jtblPaymentSchedules.setAutoCreateRowSorter(true);
        jtblPaymentSchedules.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblPaymentSchedules.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblPaymentSchedules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REF#", "Schedule", "Amount", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPaymentSchedules.setGridColor(new java.awt.Color(204, 204, 204));
        jtblPaymentSchedules.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblPaymentSchedules.setRowHeight(40);
        jtblPaymentSchedules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblPaymentSchedulesMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtblPaymentSchedules);

        jbtnDeleteSchedule.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnDeleteSchedule.setForeground(new java.awt.Color(204, 0, 0));
        jbtnDeleteSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/trash.png"))); // NOI18N
        jbtnDeleteSchedule.setText("DELETE");
        jbtnDeleteSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteScheduleActionPerformed(evt);
            }
        });

        jbtnStudentTermSchedule.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnStudentTermSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/printer.png"))); // NOI18N
        jbtnStudentTermSchedule.setText("PRINT");
        jbtnStudentTermSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStudentTermScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnStudentTermSchedule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnDeleteSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel19Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnDeleteSchedule, jbtnStudentTermSchedule});

        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnStudentTermSchedule)
                    .addComponent(jbtnDeleteSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel19Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbtnDeleteSchedule, jbtnStudentTermSchedule});

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Payments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 51))); // NOI18N

        jtblPaymentSchedulePayments.setAutoCreateRowSorter(true);
        jtblPaymentSchedulePayments.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jtblPaymentSchedulePayments.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jtblPaymentSchedulePayments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REF#", "Date", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPaymentSchedulePayments.setToolTipText("");
        jtblPaymentSchedulePayments.setGridColor(new java.awt.Color(204, 204, 204));
        jtblPaymentSchedulePayments.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jtblPaymentSchedulePayments.setRowHeight(40);
        jtblPaymentSchedulePayments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblPaymentSchedulePaymentsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtblPaymentSchedulePayments);

        jbtnDeletePayment.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jbtnDeletePayment.setForeground(new java.awt.Color(204, 0, 0));
        jbtnDeletePayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/trash.png"))); // NOI18N
        jbtnDeletePayment.setText("DELETE");
        jbtnDeletePayment.setToolTipText("Delete payment");
        jbtnDeletePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeletePaymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnDeletePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDeletePayment, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Schedule List", jPanel15);

        javax.swing.GroupLayout jpnPaymentScheduleLayout = new javax.swing.GroupLayout(jpnPaymentSchedule);
        jpnPaymentSchedule.setLayout(jpnPaymentScheduleLayout);
        jpnPaymentScheduleLayout.setHorizontalGroup(
            jpnPaymentScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnPaymentScheduleLayout.setVerticalGroup(
            jpnPaymentScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPaymentScheduleLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3)
                .addGap(0, 0, 0))
        );

        jpnSlider.add(jpnPaymentSchedule, "card7");

        javax.swing.GroupLayout jpnAccountsLayout = new javax.swing.GroupLayout(jpnAccounts);
        jpnAccounts.setLayout(jpnAccountsLayout);
        jpnAccountsLayout.setHorizontalGroup(
            jpnAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAccountsLayout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnAccountsLayout.setVerticalGroup(
            jpnAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jpnAccounts, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        // TODO add your handling code here:
        btnHomePressed();
        jpnSlider.nextPanel(10, jpnHome, jpnSlider.right);
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransactionsMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnTransactionsPressed();
            jpnSlider.nextPanel(10, jpnTransactions, jpnSlider.right);
        }
    }//GEN-LAST:event_btnTransactionsMouseClicked

    private void btnPrintServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintServiceMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnTuitionPressed();
            jpnSlider.nextPanel(10, jpnPrintService, jpnSlider.right);
        }
    }//GEN-LAST:event_btnPrintServiceMouseClicked

    private void btnTuitionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTuitionMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnPrintServicePressed();
            jpnSlider.nextPanel(10, jpnTuitionAccount, jpnSlider.right);
        }
    }//GEN-LAST:event_btnTuitionMouseClicked

    private void btnStudentRegistrationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentRegistrationsMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnStudentRegistrationsPressed();
            jpnSlider.nextPanel(10, jpnStudentRegistrations, jpnSlider.right);
        }
    }//GEN-LAST:event_btnStudentRegistrationsMouseClicked

    private void btnPaymentSchedulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaymentSchedulesMouseClicked
        // TODO add your handling code here:
        if(lblUserSession.getText().length() == 0){
            Functions.warningMessage(beforeNavigation);
        }else{
            btnPaymentSchedulesPressed();
            jpnSlider.nextPanel(10, jpnPaymentSchedule, jpnSlider.right);
        }
    }//GEN-LAST:event_btnPaymentSchedulesMouseClicked

    private void jtblSearchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblSearchListMouseClicked
        // TODO add your handling code here:
        int row = jtblSearchList.getSelectedRow();
        personId = Long.parseLong((jtblSearchList.getModel().getValueAt(row, 0).toString()));
        createStudentSession();
    }//GEN-LAST:event_jtblSearchListMouseClicked

    private void txtStudentNoSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentNoSessionKeyReleased
        // TODO add your handling code here:
        if(txtStudentNoSession.getText().length() >= 6){
            personId = Long.parseLong(txtStudentNoSession.getText());
            createStudentSession();
        }
    }//GEN-LAST:event_txtStudentNoSessionKeyReleased

    private void txtSearchStudentSessionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentSessionKeyReleased
        // TODO add your handling code here:
        fillSearchStudents(txtSearchStudentSession.getText());
    }//GEN-LAST:event_txtSearchStudentSessionKeyReleased

    private void jtblAllTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAllTransactionsMouseClicked
        // TODO add your handling code here:
        int row = jtblAllTransactions.getSelectedRow();
        selectedTransactionRow = row;
        receipt_no = Integer.parseInt(jtblAllTransactions.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_jtblAllTransactionsMouseClicked

    private void jbtnDeleteTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTransactionActionPerformed
        // TODO add your handling code here:

        if(selectedTransactionRow >= 0){
            int deleted = tuitionImpl.RemoveTuition(receipt_no);
            if(deleted > 0){
                tblModeltblAllTransactions.removeRow(selectedTransactionRow);
                Functions.successMessage("Transaction deleted successfully.");
                selectedTransactionRow = -1;
                receipt_no = -1;
            }else{
                Functions.errorMessage("Sorry! Transaction not deleted!");
            }
        }else{
            Functions.warningMessage("Sorry! you have to select a transaction you want to delete first!");
        }
    }//GEN-LAST:event_jbtnDeleteTransactionActionPerformed

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoryActionPerformed
        // TODO add your handling code here:

        String input = JOptionPane.showInputDialog(this, "Please enter new Category?","Catgeory",JOptionPane.QUESTION_MESSAGE);

        //        if(input == null){
            //            Functions.errorMessage("Cancel button clicked.");
            //        }
        if(input != null){
            PaymentCategory pc = new PaymentCategory();
            pc.setCategory(input);
            paymentCategoryBoImpl.AddPaymentCategory(pc);
            paymentCategoryBoImpl.fillComboBoxPaymentCategory(jcbCategory);
            paymentCategoryBoImpl.fillComboBoxPaymentCategory(jcbCategoryDebit);
            Functions.successMessage("Category added successfully.");
        }
    }//GEN-LAST:event_btnAddCategoryActionPerformed

    private void jbtnPaynowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPaynowActionPerformed
        // TODO add your handling code here:
        try{
            Term term = (Term)jcbTerm.getSelectedItem();
            PaymentCategory category = (PaymentCategory)jcbCategory.getSelectedItem();
            String ref_no = txtReference.getText();
            double amount = Double.parseDouble(txtAmount.getText());

            Tuition tuition = new Tuition();
            tuition.setPersonId(personId);
            tuition.setTermId(term.getTermId());
            tuition.setCategoryId(category.getCategoryId());
            tuition.setMethodId(7);
            tuition.setRef_no(ref_no);
            tuition.setCredit(amount);
            tuition.setRecordedBy(loggedInUser.getPersonId());

            int paid = tuitionImpl.AddTuition(tuition);

            if(paid > 0){
                Functions.successMessage("Student credited with amount "+amount);
                refreshStudentRecords();
            }
        }catch(NumberFormatException ex){
            Functions.errorMessage("Make sure the format of your inputs are valid: "+ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtnPaynowActionPerformed

    private void btnAddCategoryDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoryDebitActionPerformed
        // TODO add your handling code here:

        String input = JOptionPane.showInputDialog(this, "Please enter new Category?","Catgeory",JOptionPane.QUESTION_MESSAGE);

        //        if(input == null){
            //            Functions.errorMessage("Cancel button clicked.");
            //        }
        if(input != null){
            PaymentCategory pc = new PaymentCategory();
            pc.setCategory(input);
            paymentCategoryBoImpl.AddPaymentCategory(pc);
            paymentCategoryBoImpl.fillComboBoxPaymentCategory(jcbCategoryDebit);
            paymentCategoryBoImpl.fillComboBoxPaymentCategory(jcbCategory);
            Functions.successMessage("Category added successfully.");
        }
    }//GEN-LAST:event_btnAddCategoryDebitActionPerformed

    private void jbtnPaynow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPaynow1ActionPerformed
        // TODO add your handling code here:
        try{
            Term term = (Term)jcbTermDebit.getSelectedItem();
            PaymentCategory category = (PaymentCategory)jcbCategoryDebit.getSelectedItem();
            String ref_no = txtReferenceDebit.getText();
            double amount = Double.parseDouble(txtAmountDebit.getText());

            Tuition tuition = new Tuition();
            tuition.setPersonId(personId);
            tuition.setTermId(term.getTermId());
            tuition.setCategoryId(category.getCategoryId());
            tuition.setMethodId(7);
            tuition.setRef_no(ref_no);
            tuition.setDebit(amount);
            tuition.setRecordedBy(loggedInUser.getPersonId());

            int paid = tuitionImpl.AddTuition(tuition);

            if(paid > 0){
                Functions.successMessage("Student debited with "+amount);
                refreshStudentRecords();
            }
        }catch(NumberFormatException ex){
            Functions.errorMessage("Make sure the format of your inputs are valid: "+ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtnPaynow1ActionPerformed

    private void jtblTuitionAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTuitionAccountMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtblTuitionAccountMouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        jasperPerson.getPerson(personId);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jtblEnrolledCourseModulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblEnrolledCourseModulesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtblEnrolledCourseModulesMouseClicked

    private void jtblEnrolledCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblEnrolledCoursesMouseClicked
        // TODO add your handling code here:
        int row = jtblEnrolledCourses.getSelectedRow();
        selectedCourseCode = jtblEnrolledCourses.getModel().getValueAt(row, 0).toString();
        selectedTerm = jtblEnrolledCourses.getModel().getValueAt(row, 2).toString();
        StudentEnrolledCourseModules(enrolmentImpl.GetStudentCourseEnrolmentModules(personId, selectedCourseCode, selectedTerm));
    }//GEN-LAST:event_jtblEnrolledCoursesMouseClicked

    private void jcbTermPaymentSchedulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTermPaymentSchedulesActionPerformed
        // TODO add your handling code here:
        if(jcbTPaymentScheduleClicked){
            Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();
            PaySchedules(payScheduleImpl.GetStudentPayScheduleWithBalance(personId, term.getTermId()));
            StudentTermAccount(tuitionImpl.GetStudentTermAccount(personId, term.getTermId()));
            selectedPaymentScheduleRow = -1;
            PaymentSchedulePaymentRow = -1;
            tblModelPaySchedulePayments.setRowCount(0);
        }
    }//GEN-LAST:event_jcbTermPaymentSchedulesActionPerformed

    private void jbtnPayScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPayScheduleActionPerformed
        // TODO add your handling code here:
        try{
            if(selectedScheduleId > 0){
                String bal = jtblPaymentSchedules.getModel().getValueAt(selectedPaymentScheduleRow, 3).toString();
                String refined = bal.replace(",", "");
                double balance = Double.parseDouble(refined);
                double actualPayment = Double.parseDouble(txtActualPayment.getText());
                if(balance > 0){
                    if(actualPayment <= balance){
                        performTransaction(actualPayment, balance-actualPayment);
                    }else{
                        Functions.warningMessage("Payment "+actualPayment+" cannot be more than the balance "+balance+" for the selected schedule.");
                    }
                }else{
                    Functions.warningMessage("Balance for this schedule is zero. Please proceed to the next schedule.");
                }
            }else{
                Functions.warningMessage("Before adding payments, first select the schedule to pay for!");
            }
        }catch(NumberFormatException ex){
            Functions.errorMessage("Amount value should be numeric: "+ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtnPayScheduleActionPerformed

    private void jbtnPayBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPayBalanceActionPerformed
        // TODO add your handling code here:
        if(selectedPaymentScheduleRow > -1){
            String bal = jtblPaymentSchedules.getModel().getValueAt(selectedPaymentScheduleRow, 3).toString();
            String refined = bal.replace(",", "");
            double balance = Double.parseDouble(refined);
            if(balance > 0){
                performTransaction(balance,0);
            }else{
                Functions.warningMessage("Balance for this schedule is zero. Please proceed to the next schedule.");
            }
        }
    }//GEN-LAST:event_jbtnPayBalanceActionPerformed

    private void jbtnAddSchedulePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddSchedulePaymentActionPerformed
        // TODO add your handling code here:

        try{
            double schedulePayment = Double.parseDouble(txtScheduleAmount.getText());
            Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();
            double balance = termTotalDebitBalance - termScheduleTotal;

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String dt = formater.format(jxdScheduleDate.getDate());

            if(balance > 0){
                PaySchedule ps = new PaySchedule();
                ps.setAmount(schedulePayment);
                ps.setScheduledate(dt);
                ps.setRecordedBy(loggedInUser.getPersonId());
                ps.setPersonId(personId);
                ps.setTermId(term.getTermId());
                int saved = payScheduleImpl.AddPaySchedule(ps);
                if(saved > 0){
                    PaySchedules(payScheduleImpl.GetStudentPayScheduleWithBalance(personId, term.getTermId()));
                    Functions.successMessage("Transaction successful!");
                }else{
                    Functions.errorMessage("Transaction not successful!");
                }
            }else{
                Functions.warningMessage("The scheduled payments already add up to the debit balance.");
            }
        }catch(NumberFormatException | NullPointerException ex){
            Functions.errorMessage("Make sure all fields are filled with valid data: "+ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtnAddSchedulePaymentActionPerformed

    private void jtblPaymentSchedulesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPaymentSchedulesMouseClicked
        // TODO add your handling code here:
        int row = jtblPaymentSchedules.getSelectedRow();
        selectedPaymentScheduleRow = row;
        selectedScheduleId = Integer.parseInt(jtblPaymentSchedules.getModel().getValueAt(row, 0).toString());
        PaySchedulePayments(paySchedulePaymentImpl.GetStudentPaySchedulePayments(selectedScheduleId));
    }//GEN-LAST:event_jtblPaymentSchedulesMouseClicked

    private void jbtnDeleteScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteScheduleActionPerformed
        // TODO add your handling code here:

        ArrayList<PaySchedulePayment> paySchedulePayments = paySchedulePaymentImpl.GetStudentPaySchedulePayments(selectedScheduleId);

        if(paySchedulePayments.isEmpty()){
            int deleted = payScheduleImpl.RemovePaySchedule(selectedScheduleId);
            if(deleted > 0){
                Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();
                PaySchedules(payScheduleImpl.GetStudentPayScheduleWithBalance(personId, term.getTermId()));
                Functions.successMessage("Transaction successful!");
            }else{
                Functions.errorMessage("Transaction not successful!");
            }
        }else{
            Functions.errorMessage("You can't delete this schedule. It already has payments. Please delete all it's payments first.");
        }
    }//GEN-LAST:event_jbtnDeleteScheduleActionPerformed

    private void jtblPaymentSchedulePaymentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblPaymentSchedulePaymentsMouseClicked
        // TODO add your handling code here:
        int row = jtblPaymentSchedulePayments.getSelectedRow();
        PaymentSchedulePaymentRow = row;

        selectedSchedulePaymentId = Integer.parseInt(jtblPaymentSchedulePayments.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_jtblPaymentSchedulePaymentsMouseClicked

    private void jbtnDeletePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeletePaymentActionPerformed
        // TODO add your handling code here:

        Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();

        if(PaymentSchedulePaymentRow > -1 && selectedSchedulePaymentId > -1){
            String bal = jtblPaymentSchedules.getModel().getValueAt(selectedPaymentScheduleRow, 3).toString();
            String refined = bal.replace(",", "");
            double balance = Double.parseDouble(refined);

            String deletedAmount = jtblPaymentSchedulePayments.getModel().getValueAt(PaymentSchedulePaymentRow, 2).toString();
            String refinedAmount = bal.replace(",", "");
            double deletedPayment = Double.parseDouble(refinedAmount);

            double newBalance = balance + deletedPayment;

            System.out.println("balance: "+balance+" dele:"+deletedPayment+" newBalance:"+newBalance);

            int deleted = paySchedulePaymentImpl.RemovePaySchedulePayment(selectedSchedulePaymentId);

            if(deleted > 0){
                tblModelPaySchedulePayments.removeRow(PaymentSchedulePaymentRow);
                jtblPaymentSchedules.getModel().setValueAt(Functions.GetCurrency(newBalance), selectedPaymentScheduleRow, 3);
                StudentTermAccount(tuitionImpl.GetStudentTermAccount(personId, term.getTermId()));
                jtblPaymentSchedules.setRowSelectionInterval(selectedPaymentScheduleRow, 0);
                Functions.successMessage("Transaction successful!");
            }else{
                Functions.errorMessage("Transaction not successful!");
            }
        }else{
            Functions.warningMessage("Before deleting any payment, please select a payment from a payments table.");
        }
    }//GEN-LAST:event_jbtnDeletePaymentActionPerformed

    private void jbtnTuitionFeeAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTuitionFeeAccountActionPerformed
        // TODO add your handling code here:
        Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();
        jasperAccounts.StudentTermTuitionAccount(personId, term.getTermId());
    }//GEN-LAST:event_jbtnTuitionFeeAccountActionPerformed

    private void jbtnStudentTermScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStudentTermScheduleActionPerformed
        // TODO add your handling code here:
        Term term = (Term)jcbTermPaymentSchedules.getSelectedItem();
        jasperAccounts.StudentTermSchedule(personId, term.getTermId());
    }//GEN-LAST:event_jbtnStudentTermScheduleActionPerformed

    private void jbtnSRPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSRPrintActionPerformed
        // TODO add your handling code here:
        Term term = (Term)jcbSRTerms.getSelectedItem();
        jasperEnrolments.StudentTermEnrolment(personId, term.getTermId());
    }//GEN-LAST:event_jbtnSRPrintActionPerformed

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
                new Accounts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JButton btnAddCategoryDebit;
    private javax.swing.JPanel btnHome;
    private javax.swing.JPanel btnPaymentSchedules;
    private javax.swing.JPanel btnPrintService;
    private javax.swing.JPanel btnStudentRegistrations;
    private javax.swing.JPanel btnTransactions;
    private javax.swing.JPanel btnTuition;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton jbtnAddSchedulePayment;
    private javax.swing.JButton jbtnDeletePayment;
    private javax.swing.JButton jbtnDeleteSchedule;
    private javax.swing.JButton jbtnDeleteTransaction;
    private javax.swing.JButton jbtnPayBalance;
    private javax.swing.JButton jbtnPaySchedule;
    private javax.swing.JButton jbtnPaynow;
    private javax.swing.JButton jbtnPaynow1;
    private javax.swing.JButton jbtnSRPrint;
    private javax.swing.JButton jbtnStudentTermSchedule;
    private javax.swing.JButton jbtnTuitionFeeAccount;
    private javax.swing.JComboBox<String> jcbCategory;
    private javax.swing.JComboBox<String> jcbCategoryDebit;
    private javax.swing.JComboBox<String> jcbPaymentMethod;
    private javax.swing.JComboBox<String> jcbSRTerms;
    private javax.swing.JComboBox<String> jcbTerm;
    private javax.swing.JComboBox<String> jcbTermDebit;
    private javax.swing.JComboBox<String> jcbTermPaymentSchedules;
    private javax.swing.JPanel jpnAccounts;
    private javax.swing.JPanel jpnHome;
    private javax.swing.JPanel jpnPaymentSchedule;
    private javax.swing.JPanel jpnPrintService;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnStudentRegistrations;
    private javax.swing.JPanel jpnTransactions;
    private javax.swing.JPanel jpnTuitionAccount;
    private javax.swing.JTable jtblAllTransactions;
    private javax.swing.JTable jtblEnrolledCourseModules;
    private javax.swing.JTable jtblEnrolledCourses;
    private javax.swing.JTable jtblPaymentSchedulePayments;
    private javax.swing.JTable jtblPaymentSchedules;
    private javax.swing.JTable jtblSearchList;
    private javax.swing.JTable jtblTuitionAccount;
    private org.jdesktop.swingx.JXDatePicker jxdScheduleDate;
    private javax.swing.JLabel lblEStudentName;
    private javax.swing.JLabel lblHomeLoggedIn;
    private javax.swing.JLabel lblPEStudentName;
    private javax.swing.JLabel lblRCStudentName;
    private javax.swing.JLabel lblSRStudentName;
    private javax.swing.JLabel lblTermBalance;
    private javax.swing.JLabel lblTermCreditBalance;
    private javax.swing.JLabel lblTermDebitBalance;
    private javax.swing.JLabel lblUserSession;
    private javax.swing.JTextField txtActualPayment;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtAmountDebit;
    private javax.swing.JTextField txtReference;
    private javax.swing.JTextField txtReferenceDebit;
    private javax.swing.JTextField txtScheduleAmount;
    private javax.swing.JTextField txtSearchStudentSession;
    private javax.swing.JTextField txtStudentNoSession;
    // End of variables declaration//GEN-END:variables
}
