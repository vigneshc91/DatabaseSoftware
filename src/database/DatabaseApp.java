package database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;





import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;

import org.freixas.jcalendar.JCalendar;
import org.h2.util.StringUtils;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.Currency;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class DatabaseApp extends JFrame implements ActionListener, MouseListener {

	/**
	 * @param args
	 */
	
	JPanel panel = new JPanel();
	JPanel new_panel = new JPanel();
	JPanel view_panel = new JPanel();
	JPanel edit_panel = new JPanel();
	JPanel notify_panel = new JPanel();
	JPanel notifyPanel = new JPanel();
	
	JTabbedPane tab_pane = new JTabbedPane();
	JLabel donation_type, pay_mode, pay_num, dated, bank_nam, branch, date, receipt, bank_recvd, dob_1, anniversary_1, dob_2, anniversary_2, dob_3, anniversary_3;
	JLabel addr_line_11, addr_line_21, addr_line_31, area1, city1, pin_code1, country_1, state_1, addr_line_12, addr_line_22, addr_line_32, area2, city2, pin_code2, country_2, state_2, addr_line_13, addr_line_23, addr_line_33, area3, city3, pin_code3, country_3, state_3;
	JLabel viewTabStatusBar;
	
	JTextField payment_num, bank_name, branch_nam, issue_dat, receipt_no, date_of_birth_1, date_of_anniversary_1, date_of_birth_2, date_of_anniversary_2, date_of_birth_3, date_of_anniversary_3;
	JComboBox don_type, payment_mode, bank_received;	
	JLabel no_p1, last_name_p1, first_name_p1, add_p1, ph_p1, email_p1, amt_p1, other_ns_num_p1, no_p2, initial_p2, name_p2, add_p2, ph_p2, email_p2, amt_p2, other_ns_num_p2, no_p3, first_name_p3, last_name_p3, add_p3, ph_p3, email_p3, amt_p3, other_ns_num_p3;
	JTextField num_p1, cand_first_name_p1, cand_last_name_p1, cand_ph_p1, cand_email_p1, cand_amt_p1, cand_other_ns_num_p1, num_p2, cand_initial_p2, cand_nam_p2, cand_ph_p2, cand_email_p2, cand_amt_p2, cand_other_ns_num_p2, num_p3, cand_first_name_p3, cand_last_name_p3, cand_ph_p3, cand_email_p3, cand_amt_p3, cand_other_ns_num_p3;
	JTextField addr_11, addr_21, addr_31, area_1, city_town1, pin_code_1, country_t1, state_t1, addr_12, addr_22, addr_32, area_2, city_town2, pin_code_2, country_t2, state_t2, addr_13, addr_23, addr_33, area_3, city_town3, pin_code_3, country_t3, state_t3;
	
	//JTextArea cand_add_p1, cand_add_p2, cand_add_p3;
	JButton save;
	JButton reset, reset1;
	JButton retrive;
	JButton find;
	JButton edit;
	JCalendar calendar1;
	JButton delete;
	JButton proceed;
	JFileChooser chose = new JFileChooser();
	JFileChooser gen_pdf = new JFileChooser();
	FileNameExtensionFilter csv_files = new FileNameExtensionFilter("csv files", "csv");
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem Import = new JMenuItem("Import");
	JMenuItem export = new JMenuItem("Export");
	JMenuItem exit = new JMenuItem("Exit");
	
	JMenu annualReport = new JMenu("Annual Report");
	JMenuItem csvAnnualReport = new JMenuItem("CSV");
	JMenuItem pdfAnnualReport = new JMenuItem("PDF");
	
	JMenu prasadam = new JMenu("Prasadam");
	JMenuItem csvPrasadam = new JMenuItem("CSV");
	JMenuItem pdfPrasadam = new JMenuItem("PDF");
	
	JMenuItem pdf = new JMenuItem("Generate Pdf");
	JMenuItem refresh = new JMenuItem("Refresh");
	JMenuItem refreshDonationRegister = new JMenuItem("Refresh Donation");
	JMenuItem search = new JMenuItem("Find");
	JMenuItem saveStatus = new JMenuItem("Save Status");
	JMenuItem donationRegisterCsv = new JMenuItem("Export Donation");
	
	JMenu bill = new JMenu("Bill");
	JMenuItem cancel = new JMenuItem("Cancel Payment");
	JMenuItem statement = new JMenuItem("Statement");
	JMenuItem stmt_full = new JMenuItem("Complete Statement");
	JMenuItem recep = new JMenuItem("Receipt");
	
	JMenu help = new JMenu("Help");
	JMenuItem about = new JMenuItem("About");
	JButton view = new JButton("Refresh");
	DefaultTableModel model = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	DefaultTableModel notifyTableModel = new DefaultTableModel(){
		public boolean isCellEditable(int row, int column){
			return false;
		}
	};
	DefaultTableModel print_mod = new DefaultTableModel();
	JTable edit_table = new JTable(model);
	JTable notifyTable = new JTable(notifyTableModel);
	
	TableRowSorter sorter;
	JScrollPane jsp, jspNotify;
	Dimension dim;
	HashMap annualReportStatus = new HashMap();
	HashMap prasadamStatus = new HashMap();
	JToolBar view_tool = new JToolBar();
	ImageIcon import_img = new ImageIcon(this.getClass().getResource("import.png"));
	ImageIcon export_img = new ImageIcon(this.getClass().getResource("export.png"));
	ImageIcon refresh_img = new ImageIcon(this.getClass().getResource("refresh.png"));
	ImageIcon pdf_img = new ImageIcon(this.getClass().getResource("pdf.png"));
	ImageIcon find_img = new ImageIcon(this.getClass().getResource("find.png"));
	ImageIcon save_img = new ImageIcon(this.getClass().getResource("save.png"));
	ImageIcon about_img = new ImageIcon(this.getClass().getResource("about.png"));
	ImageIcon exit_img = new ImageIcon(this.getClass().getResource("exit.png"));
	ImageIcon cancel_img = new ImageIcon(this.getClass().getResource("cancel.png"));
	ImageIcon st_img = new ImageIcon(this.getClass().getResource("document.png"));
	ImageIcon stmt_img = new ImageIcon(this.getClass().getResource("complete-file.png"));
	ImageIcon rece_img = new ImageIcon(this.getClass().getResource("pay.png"));
	//ImageIcon header = new ImageIcon(this.getClass().getResource("header.png"));
	Font f;
	
	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	DateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
	Validator validator = new Validator();
	
	DatabaseApp(){
		super("Database");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int taskht = dim.height - rec.height;
		setSize(dim.width, dim.height-taskht);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JLabel head = new JLabel(header);
		//add(head);
		
		
		//head.setBounds(0, 0, dim.width, 150);
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		//panel.setLayout(new GridBagLayout());
		//add(view);
		//view.addActionListener(this);
		//view.setBounds(10, 10, 100, 20);
		//setLayout(null);
		
		
		chose.addChoosableFileFilter(csv_files);
		f = new Font("Arial", Font.PLAIN, 12);
		
		new_interior();		
		view_interior();
		edit_interior();		
		NotifyInterior();
		
		tab_pane.addTab("New Entry", new JScrollPane(new_panel));
		tab_pane.addTab("View", view_panel);
		tab_pane.addTab("Update", new JScrollPane(edit_panel));
		tab_pane.addTab("Notify", new JScrollPane(notify_panel));		
		
		panel.add(tab_pane, BorderLayout.CENTER);
		//interior();
		//add(panel);
		setVisible(true);
	}
	
	public void new_interior(){
		file.setMnemonic(KeyEvent.VK_F);
		bill.setMnemonic(KeyEvent.VK_B);
		help.setMnemonic(KeyEvent.VK_H);
		Import.setMnemonic(KeyEvent.VK_I);
		export.setMnemonic(KeyEvent.VK_E);
		exit.setMnemonic(KeyEvent.VK_X);
		
		annualReport.setMnemonic(KeyEvent.VK_A);
		csvAnnualReport.setMnemonic(KeyEvent.VK_C);
		pdfAnnualReport.setMnemonic(KeyEvent.VK_P);
		
		prasadam.setMnemonic(KeyEvent.VK_P);
		csvPrasadam.setMnemonic(KeyEvent.VK_C);
		pdfPrasadam.setMnemonic(KeyEvent.VK_P);
		
//		pdf.setMnemonic(KeyEvent.VK_P);
		search.setMnemonic(KeyEvent.VK_F);
		refresh.setMnemonic(KeyEvent.VK_R);
		saveStatus.setMnemonic(KeyEvent.VK_S);
		donationRegisterCsv.setMnemonic(KeyEvent.VK_D);
		cancel.setMnemonic(KeyEvent.VK_C);
		statement.setMnemonic(KeyEvent.VK_S);
		
		about.setMnemonic(KeyEvent.VK_A);
		file.add(Import);
		Import.setIcon(import_img);
		Import.addActionListener(this);
		Import.setAccelerator(KeyStroke.getKeyStroke('I', ActionEvent.CTRL_MASK));
		file.add(export);
		export.setIcon(export_img);
		export.addActionListener(this);
		export.setAccelerator(KeyStroke.getKeyStroke('E', ActionEvent.CTRL_MASK));
		file.add(refresh);
		refresh.setIcon(refresh_img);
		refresh.addActionListener(this);
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		refreshDonationRegister.setIcon(refresh_img);
		refreshDonationRegister.addActionListener(this);
		file.add(refreshDonationRegister);
		
//		file.add(pdf);
//		pdf.setIcon(pdf_img);
//		pdf.addActionListener(this);
//		pdf.setAccelerator(KeyStroke.getKeyStroke('P', ActionEvent.CTRL_MASK));
		
		file.add(annualReport);
		annualReport.setIcon(stmt_img);
		csvAnnualReport.addActionListener(this);
		csvAnnualReport.setIcon(rece_img);
		annualReport.add(csvAnnualReport);
		pdfAnnualReport.addActionListener(this);
		pdfAnnualReport.setIcon(pdf_img);
		annualReport.add(pdfAnnualReport);
		
		file.add(prasadam);
		prasadam.setIcon(st_img);
		csvPrasadam.addActionListener(this);
		csvPrasadam.setIcon(rece_img);
		prasadam.add(csvPrasadam);
		pdfPrasadam.addActionListener(this);
		pdfPrasadam.setIcon(pdf_img);
		prasadam.add(pdfPrasadam);
		
		search.addActionListener(this);
		search.setIcon(find_img);
		search.setAccelerator(KeyStroke.getKeyStroke('F', ActionEvent.CTRL_MASK));
		file.add(search);
		saveStatus.addActionListener(this);
		saveStatus.setIcon(save_img);
		saveStatus.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.CTRL_MASK));
		file.add(saveStatus);
		
		donationRegisterCsv.addActionListener(this);
		donationRegisterCsv.setIcon(rece_img);
		donationRegisterCsv.setAccelerator(KeyStroke.getKeyStroke('D', ActionEvent.CTRL_MASK));
		file.add(donationRegisterCsv);
		
		file.add(exit);
		exit.setIcon(exit_img);
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.ALT_MASK));
		bill.add(cancel);
		cancel.addActionListener(this);
		cancel.setIcon(cancel_img);
		bill.add(statement);
		statement.addActionListener(this);
		statement.setIcon(st_img);
		bill.add(stmt_full);
		stmt_full.addActionListener(this);
		stmt_full.setIcon(stmt_img);
		bill.add(recep);
		recep.addActionListener(this);
		recep.setIcon(rece_img);
		help.add(about);
		about.setIcon(about_img);
		about.addActionListener(this);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menu.add(file);
		menu.add(bill);
		menu.add(help);
		setJMenuBar(menu);
		
		
		first_name_p1 = new JLabel("First Name");
		//add_p1 = new JLabel("Address");
		last_name_p1 = new JLabel("Last Name");
		addr_line_11 = new JLabel("Address Line 1");
		addr_line_21 = new JLabel("Address Line 2");
		addr_line_31 = new JLabel("Address Line 3");
		area1 = new JLabel("Area");
		city1 = new JLabel("City");
		pin_code1 = new JLabel("Pin Code");
		country_1 = new JLabel("Country");
		state_1 = new JLabel("State");
		ph_p1 = new JLabel("Phone Num");
		email_p1 = new JLabel("Email");
		dob_1 = new JLabel("Date Of Birth");
		anniversary_1 = new JLabel("Date Of Anniversary");
		
		
		
		
		cand_first_name_p1 = new JTextField(15);
		cand_last_name_p1 = new JTextField(15);
		//cand_add_p1 = new JTextArea(4, 15);
		addr_11 = new JTextField(15);
		addr_21 = new JTextField(15);
		addr_31 = new JTextField(15);
		area_1 = new JTextField(15);
		city_town1 = new JTextField(15);
		pin_code_1 = new JTextField(15);
		country_t1 = new JTextField(15);
		state_t1 = new JTextField(15);
		cand_ph_p1 = new JTextField(15);
		cand_email_p1 = new JTextField(15);
		date_of_birth_1 = new JTextField(15);
		date_of_anniversary_1 = new JTextField(15);
		
		
		save = new JButton("Save");
		reset = new JButton("Reset");	
		
		date_of_anniversary_1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					save.doClick();					
					date_of_anniversary_1.transferFocus();
				}
			}
		});
		
		//cand_add_p1.setLineWrap(true);
		//cand_add_p1.setWrapStyleWord(true);
		new_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		cand_first_name_p1.setBorder(border);
		cand_last_name_p1.setBorder(border);
		addr_11.setBorder(border);
		addr_21.setBorder(border);
		addr_31.setBorder(border);
		area_1.setBorder(border);
		city_town1.setBorder(border);
		pin_code_1.setBorder(border);
		country_t1.setBorder(border);
		state_t1.setBorder(border);
		//cand_add_p1.setBorder(border);
		cand_ph_p1.setBorder(border);
		cand_email_p1.setBorder(border);
		date_of_birth_1.setBorder(border);
		date_of_anniversary_1.setBorder(border);
		save.addActionListener(this);
		reset.addActionListener(this);
		
//		save.setDefaultCapable(true);
//		getRootPane().setDefaultButton(save);
		c.insets = new Insets(15, 15, 10, 10);
		
		c.gridx = 0; c.gridy = 0; 
		new_panel.add(first_name_p1, c);
		c.gridx = 1; c.gridy = 0;
		new_panel.add(cand_first_name_p1, c);
		
		c.gridx = 0; c.gridy = 1; 
		new_panel.add(last_name_p1, c);
		c.gridx = 1; c.gridy = 1;
		new_panel.add(cand_last_name_p1, c);
		
		c.gridx = 0; c.gridy = 2;
		new_panel.add(addr_line_11, c);
		c.gridx = 1; c.gridy = 2;
		new_panel.add(addr_11, c);
		
		c.gridx = 0; c.gridy = 3;
		new_panel.add(addr_line_21, c);
		c.gridx = 1; c.gridy = 3;		
		new_panel.add(addr_21, c);
		
		c.gridx = 0; c.gridy = 4;
		new_panel.add(addr_line_31, c);
		c.gridx = 1; c.gridy = 4;
		new_panel.add(addr_31, c);
		
		c.gridx = 0; c.gridy = 5; 
		new_panel.add(area1, c);
		c.gridx = 1; c.gridy = 5;
		new_panel.add(area_1, c);
		
		
		c.gridx = 0; c.gridy = 6;
		new_panel.add(city1, c);
		c.gridx = 1; c.gridy = 6;
		new_panel.add(city_town1, c);
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		c.gridx = 0; c.gridy = 7;
		new_panel.add(pin_code1, c);
		c.gridx = 1; c.gridy = 7;
		new_panel.add(pin_code_1, c);
		
		c.gridx = 0; c.gridy = 8;
		new_panel.add(country_1, c);
		c.gridx = 1; c.gridy = 8;
		new_panel.add(country_t1, c);
		
		c.gridx = 0; c.gridy = 9;
		new_panel.add(state_1, c);
		c.gridx = 1; c.gridy = 9;
		new_panel.add(state_t1, c);
		
		c.gridx = 0; c.gridy = 10;
		new_panel.add(ph_p1, c);
		c.gridx = 1; c.gridy = 10;
		new_panel.add(cand_ph_p1, c);
		
		c.gridx = 0; c.gridy = 11;
		new_panel.add(email_p1, c);
		c.gridx = 1; c.gridy = 11;
		new_panel.add(cand_email_p1, c);
		
		c.gridx = 0; c.gridy = 12;
		new_panel.add(dob_1, c);
		c.gridx = 1; c.gridy = 12;
		new_panel.add(date_of_birth_1, c);
		
		c.gridx = 0; c.gridy = 13;
		new_panel.add(anniversary_1, c);
		c.gridx = 1; c.gridy = 13;
		new_panel.add(date_of_anniversary_1, c);
		
		c.gridx = 1; c.gridy = 14;
		new_panel.add(save, c);
		c.gridx = 2; c.gridy = 14;
		new_panel.add(reset, c);
	/*	cand_add_p1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					if (e.getModifiers() > 0){
						cand_add_p1.transferFocusBackward();
					} else {
						cand_add_p1.transferFocus();
					}
					e.consume();
				}
			}
		}); */
	}
	
	public void view_interior(){
		viewTabStatusBar = new JLabel("");
		
		BorderLayout layout = new BorderLayout();
		view_panel.setLayout(layout);
					
		sorter = new TableRowSorter<DefaultTableModel>(model);
		edit_table.setRowSorter(sorter);
		edit_table.addMouseListener(this);
		edit_table.setRowHeight(60);
		Font ff = new Font("Arial", Font.PLAIN, 16);
		Font tableFont = new Font("Calibiri", Font.PLAIN, 12);
		viewTabStatusBar.setFont(ff);
		edit_table.setFont(tableFont);
		edit_table.getTableHeader().setFont(new Font("Calibiri", Font.BOLD, 12));
		
		InputMap im = edit_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = edit_table.getActionMap();

		KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

		im.put(escapeKey, "Action.escape");
		am.put("Action.escape", new AbstractAction() {
		    public void actionPerformed(ActionEvent evt) {
		    	
		    	RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)");
				
				sorter.setRowFilter(rowFilter);  
		    }
		});
		
		model.addColumn("NO.");
		model.addColumn("FIRST NAME");
		model.addColumn("LAST NAME");			
		model.addColumn("ADDRESS");
		model.addColumn("AREA");
		model.addColumn("CITY");
		model.addColumn("PINCODE");
		model.addColumn("COUNTRY");
		model.addColumn("STATE");
		model.addColumn("PH NO.");
		model.addColumn("EMAIL");
		model.addColumn("DOB");
		model.addColumn("ANNIV");
		
		view_tab_data();
		
//		edit_table.setEnabled(false);
		//edit_table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    
        jsp = new JScrollPane(edit_table);        
        
        view_panel.add(jsp, layout.CENTER);
        view_panel.add(viewTabStatusBar, layout.SOUTH);
	}
	
	
	void view_tab_data(){
		int count = 0;
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
	    
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/databaseapp", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from details order by last_updated_at desc";
			ResultSet rs = stm.executeQuery(st);
		
		while (rs.next()){
			String no = rs.getString(1);
			String first_name = rs.getString(2);
			String last_name = rs.getString(3); 
			String addr = rs.getString(4)+ "  "+ rs.getString(5) + "  "+rs.getString(6);
			
			String area1 = rs.getString(7);
			String city1 = rs.getString(8);
			String pinCode1 = rs.getString(9);
			String country = rs.getString(10);
			String state = rs.getString(11);
			String phNum = rs.getString(12);
			String email = rs.getString(13);
			String dob = rs.getDate(14).toString();
			String anniversary = rs.getDate(15).toString();
			
			
			
			count++;
			
			
//			edit_table.getColumnModel().getColumn(7).setCellRenderer(new checkBoxRenderer("unselect"));
			model.addRow(new Object[] {no, first_name, last_name, addr, area1, city1, pinCode1, country, state, phNum, email, dob, anniversary});
			
		}
		
		viewTabStatusBar.setText("    Total Count : "+count);
				
		for(int i=0; i<edit_table.getColumnModel().getColumnCount(); i++)
			edit_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		
		

		
		
	}
		catch(Exception e){
			System.err.print(e);
		}
	}
	
	
	
	public void NotifyInterior(){
		notifyPanel.setLayout(new BorderLayout());
		notifyTable.addMouseListener(this);
//		donationRegisterTable.setRowHeight(150);
		
		Font ff = new Font("Arial", Font.PLAIN, 16);
		notifyTable.setFont(ff);
		notifyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		//notifyTable.getTableHeader().setPreferredSize(new Dimension(100, 100));
		
		notifyTableModel.addColumn("NO.");
		notifyTableModel.addColumn("FIRST NAME");
		notifyTableModel.addColumn("LAST NAME");			
		notifyTableModel.addColumn("ADDRESS");
		notifyTableModel.addColumn("AREA");
		notifyTableModel.addColumn("CITY");
		notifyTableModel.addColumn("PINCODE");
		notifyTableModel.addColumn("COUNTRY");
		notifyTableModel.addColumn("STATE");
		notifyTableModel.addColumn("PH NO.");
		notifyTableModel.addColumn("EMAIL");
		notifyTableModel.addColumn("DOB");
		notifyTableModel.addColumn("ANNIV");
		
		
		NotifyTableData();				    
		    
		jspNotify = new JScrollPane(notifyTable);        
		notifyPanel.add(jspNotify);
	}
	
	void NotifyTableData(){
		
		
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/databaseapp", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from details";
			ResultSet rs = stm.executeQuery(st);
		
			while (rs.next()){
				String no = rs.getString(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3); 
				String addr = rs.getString(4)+ "  "+ rs.getString(5) + "  "+rs.getString(6);
				
				String area1 = rs.getString(7);
				String city1 = rs.getString(8);
				String pinCode1 = rs.getString(9);
				String country = rs.getString(10);
				String state = rs.getString(11);
				String phNum = rs.getString(12);
				String email = rs.getString(13);
				String dob = rs.getDate(14).toString();
				String anniversary = rs.getDate(15).toString();
				
				notifyTableModel.addRow(new Object[] {no, first_name, last_name, addr, area1, city1, pinCode1, country, state, phNum, email, dob, anniversary});
	
				
				
			}
	
		} catch (Exception e){
			System.err.println(e);
		}
		
		for (int row = 0; row < notifyTable.getRowCount(); row++)
		{
		    int rowHeight = notifyTable.getRowHeight();

		    for (int column = 0; column < notifyTable.getColumnCount(); column++)
		    {
		        Component comp = notifyTable.prepareRenderer(notifyTable.getCellRenderer(row, column), row, column);
		        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
		     }

		    notifyTable.setRowHeight(row, rowHeight);
		}
		    
		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		    rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
		    
		    for(int i=0; i<notifyTable.getColumnModel().getColumnCount(); i++)
		    	notifyTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		    
		   


	}
	
	void edit_interior(){
		
		no_p3 = new JLabel("Enter No");
		first_name_p3 = new JLabel("First Name");
		last_name_p3 = new JLabel("Last Name");
		//add_p3 = new JLabel("Address");
		addr_line_13 = new JLabel("Address Line 1");
		addr_line_23 = new JLabel("Address Line 2");
		addr_line_33 = new JLabel("Address Line 3");
		area3 = new JLabel("Area");
		city3 = new JLabel("City");
		pin_code3 = new JLabel("Pin Code");
		country_3 = new JLabel("Country");
		state_3 = new JLabel("State");
		ph_p3 = new JLabel("Phone Num");
		email_p3 = new JLabel("Email");
		dob_3 = new JLabel("Date Of Birth");
		anniversary_3 = new JLabel("Date Of Anniversary");
		
		num_p3 = new JTextField(15);
		cand_first_name_p3 = new JTextField(15);
		cand_last_name_p3 = new JTextField(15);
		//cand_add_p3 = new JTextArea(4, 15);
		addr_13 = new JTextField(15);
		addr_23 = new JTextField(15);
		addr_33 = new JTextField(15);
		area_3 = new JTextField(15);
		city_town3 = new JTextField(15);
		pin_code_3 = new JTextField(15);
		country_t3 = new JTextField(15);
		state_t3 = new JTextField(15);
		cand_ph_p3 = new JTextField(15);
		cand_email_p3 = new JTextField(15);
		date_of_birth_3 = new JTextField(15);
		date_of_anniversary_3 = new JTextField(15);
		
		
		//save = new JButton("Save");
		//reset = new JButton("Reset");
		find = new JButton("Find");
		edit = new JButton("Update");
		delete = new JButton("Delete");
		reset = new JButton("Reset");
		
		num_p3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					find.doClick();
					num_p3.transferFocus();
				}
			}
		});
		
		date_of_anniversary_3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					edit.doClick();
					cand_amt_p3.transferFocus();
				}
			}
		});
		
		//cand_add_p3.setLineWrap(true);
		//cand_add_p3.setWrapStyleWord(true);
		edit_panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		num_p3.setBorder(border);
		cand_first_name_p3.setBorder(border);
		cand_last_name_p3.setBorder(border);
		//cand_add_p3.setBorder(border);
		addr_13.setBorder(border);
		addr_23.setBorder(border);
		addr_33.setBorder(border);
		area_3.setBorder(border);
		city_town3.setBorder(border);
		pin_code_3.setBorder(border);
		country_t3.setBorder(border);
		state_t3.setBorder(border);
		cand_ph_p3.setBorder(border);
		cand_email_p3.setBorder(border);
		date_of_birth_3.setBorder(border);
		date_of_anniversary_3.setBorder(border);
		
		
		cand_last_name_p3.setEditable(false);
		cand_first_name_p3.setEditable(false);
		addr_13.setEditable(false);
		addr_23.setEditable(false);
		addr_33.setEditable(false);
		area_3.setEditable(false);
		city_town3.setEditable(false);
		pin_code_3.setEditable(false);
		country_t3.setEditable(false);
		state_t3.setEditable(false);
		cand_ph_p3.setEditable(false);
		cand_email_p3.setEditable(false);
		//cand_add_p3.setEditable(false);
		date_of_birth_3.setEditable(false);
		date_of_anniversary_3.setEditable(false);
		
		//save.addActionListener(this);
		//reset.addActionListener(this);
		find.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
		reset.addActionListener(this);
		
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(15, 15, 10, 10);
		edit_panel.add(no_p3, c);
		c.gridx = 1; c.gridy = 0;
		edit_panel.add(num_p3, c);
		c.gridx = 2; c.gridy = 0;
		edit_panel.add(find, c);
		
		c.gridx = 0; c.gridy = 1;
		edit_panel.add(first_name_p3, c);
		c.gridx = 1; c.gridy = 1;
		edit_panel.add(cand_first_name_p3, c);
		
		c.gridx = 0; c.gridy = 2;
		edit_panel.add(last_name_p3, c);
		c.gridx = 1; c.gridy = 2;
		edit_panel.add(cand_last_name_p3, c);
		
		c.gridx = 0; c.gridy = 3;
		edit_panel.add(addr_line_13, c);
		c.gridx = 1; c.gridy = 3;
		edit_panel.add(addr_13, c);
		
		c.gridx = 0; c.gridy = 4;
		edit_panel.add(addr_line_23, c);
		c.gridx = 1; c.gridy = 4;
		edit_panel.add(addr_23, c);
		
		c.gridx = 0; c.gridy = 5;
		edit_panel.add(addr_line_33, c);
		c.gridx = 1; c.gridy = 5;
		edit_panel.add(addr_33, c);
		
		c.gridx = 0; c.gridy = 6;
		edit_panel.add(area3, c);
		c.gridx = 1; c.gridy = 6;
		edit_panel.add(area_3, c);
		
		c.gridx = 0; c.gridy = 7;
		edit_panel.add(city3, c);
		c.gridx = 1; c.gridy = 7;
		edit_panel.add(city_town3, c);
		
		c.gridx = 0; c.gridy = 8;
		edit_panel.add(pin_code3, c);
		c.gridx = 1; c.gridy = 8;
		edit_panel.add(pin_code_3, c);
		
		c.gridx = 0; c.gridy = 9;
		edit_panel.add(country_3, c);
		c.gridx = 1; c.gridy = 9;
		edit_panel.add(country_t3, c);
		
		c.gridx = 0; c.gridy = 10;
		edit_panel.add(state_3, c);
		c.gridx = 1; c.gridy = 10;
		edit_panel.add(state_t3, c);
		
		//panel.add(new JScrollPane(cand_add, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		c.gridx = 0; c.gridy = 11;
		edit_panel.add(ph_p3, c);
		c.gridx = 1; c.gridy = 11;
		edit_panel.add(cand_ph_p3, c);
		
		c.gridx = 0; c.gridy = 12;
		edit_panel.add(email_p3, c);
		c.gridx = 1; c.gridy = 12;
		edit_panel.add(cand_email_p3, c);
		
		c.gridx = 0; c.gridy = 13;
		edit_panel.add(dob_3, c);
		c.gridx = 1; c.gridy = 13;
		edit_panel.add(date_of_birth_3, c);
		
		c.gridx = 0; c.gridy = 14;
		edit_panel.add(anniversary_3, c);
		c.gridx = 1; c.gridy = 14;
		edit_panel.add(date_of_anniversary_3, c);
		
		c.gridx = 1; c.gridy = 15;
		edit_panel.add(edit, c);
		c.gridx = 2; c.gridy = 15;
		edit_panel.add(delete, c);
		c.gridx = 3; c.gridy = 15;
		edit_panel.add(reset, c);
	/*	cand_add_p3.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_TAB){
					if (e.getModifiers() > 0){
						cand_add_p3.transferFocusBackward();
					} else {
						cand_add_p3.transferFocus();
					}
					e.consume();
				}
			}
		}); */
		
	}
	
	
	
	public String GetCurrentDateTime(){
		Calendar cal = Calendar.getInstance();
		String currentDateTime = dateformat.format(cal.getTime());
		
		return currentDateTime;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Connection conn = null;
		try {
			conn = DriverManager.
			    getConnection("jdbc:h2:~/databaseapp", "sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // add application code here
		Statement stm;
		try {
			stm = conn.createStatement();
			
			String st = "create table if not exists details(no bigint auto_increment primary key, first_name varchar(50), last_name varchar(50), addr_1 varchar(50), addr_2 varchar(50), addr_3 varchar(50), area varchar(30), city varchar(30), pincode varchar(8), country varchar(30), state varchar(30), phone_num varchar(40), email varchar(60), date_of_birth date, anniversary date, last_updated_at timestamp)";
			
			stm.executeUpdate(st);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // NumberFormat formatter = 
       //     new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
       // String result = formatter.format(num);
        

		DatabaseApp vr = new DatabaseApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Import")){
			int ret = chose.showOpenDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String fi = s.toString();
			}
			Connection conn = null;
			try{
				Class.forName("org.h2.Driver");
				conn = DriverManager.
					    getConnection("jdbc:h2:~/databaseapp", "sa", "");
				Statement stm = conn.createStatement();
				String st = "insert into details (select * from csvread('"+s+"'))";
				stm.executeUpdate(st);
				Telegraph tele = new Telegraph("Success", "Imported successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue que = new TelegraphQueue();
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Imported Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch(SQLException e6){
				System.err.println(e6);
				if (e6.getErrorCode() == 23505){
					Telegraph tele = new Telegraph("Data already exist", "Duplicate entry found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Duplicate Entry Found...", "Data already exists", JOptionPane.ERROR_MESSAGE);
				} else {
					Telegraph tele = new Telegraph("Error", "Some problem occured...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (e.getActionCommand().equals("Export")){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/databaseapp", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select * from details')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "Successfully Exported...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getActionCommand().equals("Save")){
			TelegraphQueue que = new TelegraphQueue();
			if(cand_first_name_p1.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter First Name", "First Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "NS number can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				cand_first_name_p1.setFocusable(true);
			}else if (cand_last_name_p1.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter Last Name", "Last Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Name can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
					cand_last_name_p1.setFocusable(true);
			} else if (addr_11.getText().length() == 0){
						Telegraph tele = new Telegraph("Enter Address", "Address line 1 can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						que.add(tele);
						//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
						addr_11.setFocusable(true);
			} else if (city_town1.getText().length() == 0){
					Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
					city_town1.setFocusable(true);
			} else if (pin_code_1.getText().length() != 0 && !validator.IsVaidPinCode(pin_code_1.getText())){
				Telegraph tele = new Telegraph("Invalid Pin Code", "Pin Code format is invalid", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				pin_code_1.setFocusable(true);
			}
			
			else {
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/databaseapp", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
		        try {
					Statement stm = conn.createStatement();
					
					
					
					String st = "insert into details values("+"'"+cand_first_name_p1.getText()+"', '"+cand_last_name_p1.getText()+"'"+","+"'"+addr_11.getText()+"'"+", '"+addr_21.getText()+"', '"+addr_31.getText()+"', '"+area_1.getText()+"', '"+city_town1.getText()+"' ,"+" '"+pin_code_1.getText()+"', '"+country_t1.getText()+"', '"+state_t1.getText()+"', '"+cand_ph_p1.getText()+"', '"+cand_email_p1.getText()+"', '"+date_of_birth_1.getText()+"', '"+date_of_anniversary_1.getText()+"', '"+GetCurrentDateTime()+"')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "Saved successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					if (e2.getErrorCode() == 23505){
						Telegraph tele = new Telegraph("Data already exist", "The NS Number You Entered Is Already Registered...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					//JOptionPane.showMessageDialog(null, "NS number "+num_p1.getText()+" already found...", "Duplicate entry found", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				cand_first_name_p1.setText("");
				cand_last_name_p1.setText("");
				addr_11.setText("");
				addr_21.setText("");
				addr_31.setText("");
				area_1.setText("");
				city_town1.setText("");
				pin_code_1.setText("");
				country_t1.setText("");
				state_t1.setText("");
				cand_ph_p1.setText("");
				cand_email_p1.setText("");
				date_of_birth_1.setText("");
				date_of_anniversary_1.setText("");
				
				
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
					
				
			
			
			
		} 
		
		else if (e.getActionCommand().equals("Reset")){
			
			cand_first_name_p1.setText("");
			cand_last_name_p1.setText("");
			addr_11.setText("");
			addr_21.setText("");
			addr_31.setText("");
			area_1.setText("");
			city_town1.setText("");
			pin_code_1.setText("");
			country_t1.setText("");
			state_t1.setText("");
			cand_ph_p1.setText("");
			cand_email_p1.setText("");
			date_of_birth_1.setText("");
			date_of_anniversary_1.setText("");
			
		} 
		
		else if (e.getActionCommand().equals("Refresh")){
			model.setRowCount(0);
			view_tab_data();
		}
		
		else if (e.getSource() == refreshDonationRegister){
			notifyTableModel.setRowCount(0);
			NotifyTableData();
		}
		
		else if (e.getSource() == csvAnnualReport){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/databaseapp", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select no,initial,name,addr_1,addr_2,area,city,pincode,phone_num,email from details where annual_report = ''S'' ')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "CSV Generated Successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getSource() == pdfAnnualReport){
			int x = 0, y = 0;
			PdfWriter writer = null;
			int ret = gen_pdf.showSaveDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = gen_pdf.getSelectedFile();
				String stm = s.toString();
				if(!stm.endsWith(".pdf")){
					s = new File(stm+".pdf");
				}
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A4);
			doc.setMargins(30, 30, 10, 10);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 11);
			PdfPTable table = new PdfPTable(3);
			table.setComplete(true);
			table.setWidthPercentage(110);
			
			//Paragraph para = new Paragraph();
			//para.setAlignment(Element.RECTANGLE);
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/databaseapp", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "select * from details where annual_report = 'S'";
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	
		        	
		        	String resultAddress = "NS No: "+rs.getString(1)+"\n"+rs.getString(3)+" "+rs.getString(2);
		        	
		        	//address line 1 can't be empty so directly append it
		        	resultAddress += "\n"+rs.getString(4);
		        	
		        	//address line 2 can be empty so we must check it before append
		        	if(rs.getString(5) != null)
		        		resultAddress += "\n"+rs.getString(5);
		        	
		        	//area can also be empty
		        	if(rs.getString(6) != null)
		        		resultAddress += "\n"+rs.getString(6);
		        	
		        	//city can't be empty anyhow checking for type safety
		        	if(rs.getString(7) != null)
		        		resultAddress += "\n"+rs.getString(7);
		        	
		        	//pin code can be empty
		        	if(rs.getString(8) != null)
		        		resultAddress += "\n"+rs.getString(8);
		        	
		        	//phone number can be empty
		        	if(rs.getString(9) != null)
		        		resultAddress += "\n"+rs.getString(9);
		        	
		        	Phrase h = new Phrase(resultAddress);
		        	
		        	/*if (rs.getString(4) != null && rs.getString(5) != null){
		        		//para.add(rs.getString(1)+rs.getString(2)+rs.getString(3));
		        		h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(5), n);
		        		//ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, h, 10, 800-x, 0);
		        	//	x+=10;
		        	 } else if (rs.getString(4) == null && rs.getString(5) != null) {
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(5), n);
		        	 
		        	 } else if (rs.getString(4) != null && rs.getString(5) == null){
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4), n);
		        		 
		        	 }*/
		        	 
		        	PdfPCell cell = new PdfPCell(h);
		        	 cell.setFixedHeight(100f);
		        	table.addCell(cell);
		        	
		        }
		        
		        table.addCell("");
		        table.addCell("");
		        //table.addCell("");
			}
			catch(Exception e4){
				System.err.println(e4);
			}
			try {
				doc.add(table);
				
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.close();
			//System.out.println("sucess");
			Telegraph tele = new Telegraph("Success", "Pdf generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null, "Pdf generated successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if (e.getSource() == csvPrasadam){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
					conn = DriverManager.
						    getConnection("jdbc:h2:~/databaseapp", "sa", "");
					Statement stm = conn.createStatement();
					String st = "call csvwrite('"+s+"', 'select no,initial,name,addr_1,addr_2,area,city,pincode,phone_num,email from details where prasadam = ''S'' ')";
					stm.executeUpdate(st);
					Telegraph tele = new Telegraph("Success", "CSV Generated Successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
					//JOptionPane.showMessageDialog(null, "Successfully Exported...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e7){
					System.err.println(e7);
				}
			}
		}
		
		else if (e.getSource() == pdfPrasadam){
			int x = 0, y = 0;
			PdfWriter writer = null;
			int ret = gen_pdf.showSaveDialog(this);
			File s = null;
			if (ret == JFileChooser.APPROVE_OPTION){
				s = gen_pdf.getSelectedFile();
				String stm = s.toString();
				if(!stm.endsWith(".pdf")){
					s = new File(stm+".pdf");
				}
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A4);
			doc.setMargins(30, 30, 10, 10);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 11);
			PdfPTable table = new PdfPTable(3);
			table.setComplete(true);
			table.setWidthPercentage(110);
			
			//Paragraph para = new Paragraph();
			//para.setAlignment(Element.RECTANGLE);
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/databaseapp", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "select * from details where prasadam = 'S'";
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	
		        	
		        	String resultAddress = "NS No: "+rs.getString(1)+"\n"+rs.getString(3)+" "+rs.getString(2);
		        	
		        	//address line 1 can't be empty so directly append it
		        	resultAddress += "\n"+rs.getString(4);
		        	
		        	//address line 2 can be empty so we must check it before append
		        	if(rs.getString(5) != null)
		        		resultAddress += "\n"+rs.getString(5);
		        	
		        	//area can also be empty
		        	if(rs.getString(6) != null)
		        		resultAddress += "\n"+rs.getString(6);
		        	
		        	//city can't be empty anyhow checking for type safety
		        	if(rs.getString(7) != null)
		        		resultAddress += "\n"+rs.getString(7);
		        	
		        	//pin code can be empty
		        	if(rs.getString(8) != null)
		        		resultAddress += "\n"+rs.getString(8);
		        	
		        	//phone number can be empty
		        	if(rs.getString(9) != null)
		        		resultAddress += "\n"+rs.getString(9);
		        	
		        	Phrase h = new Phrase(resultAddress);
		        	
		        	/*if (rs.getString(4) != null && rs.getString(5) != null){
		        		//para.add(rs.getString(1)+rs.getString(2)+rs.getString(3));
		        		h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(5), n);
		        		//ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, h, 10, 800-x, 0);
		        	//	x+=10;
		        	 } else if (rs.getString(4) == null && rs.getString(5) != null) {
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(5), n);
		        	 
		        	 } else if (rs.getString(4) != null && rs.getString(5) == null){
		        		 h = new Phrase("NS NO "+rs.getString(1)+"\n"+rs.getString(2)+"\n"+rs.getString(3)+"\n"+rs.getString(4), n);
		        		 
		        	 }*/
		        	 
		        	PdfPCell cell = new PdfPCell(h);
		        	 cell.setFixedHeight(100f);
		        	table.addCell(cell);
		        	
		        }
		        
		        table.addCell("");
		        table.addCell("");
		        //table.addCell("");
			}
			catch(Exception e4){
				System.err.println(e4);
			}
			try {
				doc.add(table);
				
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.close();
			//System.out.println("sucess");
			Telegraph tele = new Telegraph("Success", "Pdf generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
			TelegraphQueue que = new TelegraphQueue();
			que.add(tele);
			//JOptionPane.showMessageDialog(null, "Pdf generated successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource() == search){
			tab_pane.setSelectedIndex(1);
			String nameFilter = JOptionPane.showInputDialog(null, "Enter Name to Find");
			int[] ar = {0,1,2,3,4,5,6,7}; 
			if(nameFilter != null){
				RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)"+nameFilter, ar);
			
				sorter.setRowFilter(rowFilter);
			}
			
		}
		
		else if(e.getSource() == saveStatus){
			tab_pane.setSelectedIndex(1);
			
//			for(Object key : status.keySet()){
//			System.out.println(key + " : "+ status.get(key));
//		}
			if(annualReportStatus.size() == 0 && prasadamStatus.size() == 0){
				Telegraph tele = new Telegraph("Warning", "No Changes to made...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
			} else{	
				
				Statement stm = null;
				Connection conn = null;
				try{
					Class.forName("org.h2.Driver");
			        conn = DriverManager.
			            getConnection("jdbc:h2:~/databaseapp", "sa", "");
			        stm = conn.createStatement();
				}
				catch(Exception e1){
					System.err.println(e1);
				}
				
				Iterator annualReportIterator = annualReportStatus.entrySet().iterator();
				Iterator prasadamIterator = prasadamStatus.entrySet().iterator();
				try{
					if(annualReportStatus.size() != 0){
						while(annualReportIterator.hasNext()){
							Map.Entry pair = (Map.Entry)annualReportIterator.next();
							
							String st = "update details set annual_report = "+"'"+pair.getValue()+"'"+" where no = "+"'"+pair.getKey()+"'";
						    stm.executeUpdate(st);
						    						
							annualReportIterator.remove();
						} 
						//clear the hashmap dictionary
						annualReportStatus.clear();
					}
					if(prasadamStatus.size() != 0){
						while(prasadamIterator.hasNext()){
							Map.Entry pair = (Map.Entry)prasadamIterator.next();
							
							String st = "update details set prasadam = "+"'"+pair.getValue()+"'"+" where no = "+"'"+pair.getKey()+"'";
						    stm.executeUpdate(st);
						    						
						    prasadamIterator.remove();
						} 
						//clear the hashmap dictionary
						prasadamStatus.clear();
					}
					
					
					
					model.setRowCount(0);
					view_tab_data();
					
					Telegraph tele = new Telegraph("Success", "Operations Completed successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					
				} catch(Exception e1){
					Telegraph tele = new Telegraph("Error", "Some Problem occured. Try Again Later...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					System.err.println(e1);
					
				}
			
			}
		}
		
		else if (e.getSource() == donationRegisterCsv){
			int ret = chose.showSaveDialog(this);
			File s;
			if(ret == JFileChooser.APPROVE_OPTION){
				s = chose.getSelectedFile();
				String file_name = s.toString();
				if (!file_name.toLowerCase().endsWith(".csv")){
					s = new File(file_name+".csv");
				}
				
				 try{
				        TableModel model = notifyTable.getModel();
				        FileWriter excel = new FileWriter(s);

				        for(int i = 0; i < model.getColumnCount(); i++){
				            excel.write(model.getColumnName(i).replaceAll("<[^>]*>", "") + ",");				            
				        }

				        excel.write("\n");

				        for(int i=0; i< model.getRowCount(); i++) {				        	
				            for(int j=0; j < model.getColumnCount(); j++) {
				            	if(model.getValueAt(i,j) != null)
				            		excel.write(model.getValueAt(i,j).toString().replaceAll(",", " ").replaceAll("<[br^>]*>", " ").replaceAll("<[^>]*>", "")+",");				                
				            }
				            excel.write("\n");
				        }

				        excel.close();
				        
				        Telegraph tele = new Telegraph("Success", "Donation Register Successfully Exported...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);

				 	}catch(IOException e1){ 
				    	System.out.println(e1); 
				    	Telegraph tele = new Telegraph("Error", "Some Problem Occured...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
				    }
			}
		}
		
		else if (e.getActionCommand().equals("Exit")){
			System.exit(1);
		}
		
		else if (e.getActionCommand().equals("About")){
			JOptionPane.showMessageDialog(null, "Content created by vignesh c", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource() == find){
			Statement stm = null;
			Connection conn = null;
			if(num_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Warning", "Number Can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
    			TelegraphQueue que = new TelegraphQueue();
    			que.add(tele);
			} else{
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/databaseapp", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p3.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p3.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_first_name_p3.setText(rs.getString(2));
		        		cand_last_name_p3.setText(rs.getString(3));
		        		addr_13.setText(rs.getString(4));
		        		addr_23.setText(rs.getString(5));
		        		addr_33.setText(rs.getString(6));
		        		area_3.setText(rs.getString(7));
		        		city_town3.setText(rs.getString(8));
		        		pin_code_3.setText(rs.getString(9));
		        		country_t3.setText(rs.getString(10));
		        		state_t3.setText(rs.getString(11));
		        		cand_ph_p3.setText(rs.getString(12));
		        		cand_email_p3.setText(rs.getString(13));
		        		date_of_birth_3.setText(rs.getDate(14).toString());
		        		date_of_anniversary_3.setText(rs.getDate(15).toString());
		        		
		        		
		        		num_p3.setEditable(false);
		        		cand_first_name_p3.setEditable(true);
		        		cand_last_name_p3.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		addr_13.setEditable(true);
		        		addr_23.setEditable(true);
		        		addr_33.setEditable(true);
		        		area_3.setEditable(true);
		        		city_town3.setEditable(true);
		        		pin_code_3.setEditable(true);
		        		country_t3.setEditable(true);
		        		state_t3.setEditable(true);
		        		cand_ph_p3.setEditable(true);
		        		cand_email_p3.setEditable(true);
		        		date_of_birth_3.setEditable(true);
		        		date_of_anniversary_3.setEditable(true);
		        		
		        		
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
		    			TelegraphQueue que = new TelegraphQueue();
		    			que.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
			}
			
		} 
		
		else if(e.getActionCommand().equals("Update")){
			TelegraphQueue que = new TelegraphQueue();			
			if(cand_first_name_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter First Name", "First Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "NS number can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				cand_first_name_p3.setFocusable(true);
			}else if (cand_last_name_p3.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Last Name", "Last Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Name can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				cand_last_name_p3.setFocusable(true);
			} else if (addr_13.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Address", "Address line 1 can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				addr_13.setFocusable(true);
			} else if (city_town3.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				city_town3.setFocusable(true);
			} else if (pin_code_3.getText().length() != 0 && !validator.IsVaidPinCode(pin_code_3.getText())){
				Telegraph tele = new Telegraph("Invalid Pin Code", "Pin Code format is invalid", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				pin_code_3.setFocusable(true);
			}
				
			 else {
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/databaseapp", "sa", "");
			        Statement stm = conn.createStatement();
			        String st = "update details set first_name = '"+cand_first_name_p3.getText()+"', last_name = '"+cand_last_name_p3.getText()+"', "+"addr_1 = "+"'"+addr_13.getText()+"'"+", addr_2 = '"+addr_23.getText()+"', addr_3 = '"+addr_33.getText()+"', area = '"+area_3.getText()+"', city = '"+city_town3.getText()+"', pincode = '"+pin_code_3.getText()+"', country = '"+country_t3.getText()+"', state = '"+state_t3.getText()+"', phone_num = '"+cand_ph_p3.getText()+"', email = '"+cand_email_p3.getText()+"', date_of_birth = "+date_of_birth_3.getText()+", anniversary = '"+date_of_anniversary_3.getText()+"', last_updated_at = '"+GetCurrentDateTime()+"'  where no = "+"'"+num_p3.getText()+"'";
			        stm.executeUpdate(st);
			        Telegraph tele = new Telegraph("Success", "Updated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
			        //JOptionPane.showMessageDialog(null, "Updated Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
			        
					num_p3.setText("");
					cand_first_name_p3.setText("");
					cand_last_name_p3.setText("");
			       // cand_add_p3.setText("");
			        addr_13.setText("");
			        addr_23.setText("");
			        addr_33.setText("");
			        area_3.setText("");
			        city_town3.setText("");
			        pin_code_3.setText("");
			        country_t3.setText("");
			        state_t3.setText("");
			        cand_ph_p3.setText("");
			        cand_email_p3.setText("");
			        date_of_birth_3.setText("");
			        date_of_anniversary_3.setText("");
			        
			        num_p3.setEditable(true);
			        cand_first_name_p3.setEditable(false);
			        cand_last_name_p3.setEditable(false);
			        //cand_add_p3.setEditable(false);
			        addr_13.setEditable(false);
			        addr_23.setEditable(false);
			        addr_33.setEditable(false);
			        area_3.setEditable(false);
			        city_town3.setEditable(false);
			        pin_code_3.setEditable(false);
			        country_t3.setEditable(false);
			        state_t3.setEditable(false);
			        cand_ph_p3.setEditable(false);
			        cand_email_p3.setEditable(false);
			        date_of_birth_3.setEditable(false);
			        date_of_anniversary_3.setEditable(false);
			        
				}
				catch(Exception e2){
					System.err.println(e2);
				}
			}
			
		} 
		
		else if (e.getActionCommand().equals("Delete")){
			int t = JOptionPane.showConfirmDialog(null, "Are you sure want to delete "+cand_last_name_p3.getText(), "Confirm delete", JOptionPane.YES_NO_OPTION);
			if (t == JOptionPane.YES_OPTION){
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/databaseapp", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "delete from details where no = "+"'"+num_p3.getText()+"'";
		        stm.executeUpdate(st);
		        Telegraph tele = new Telegraph("Success", "Deleted successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
		        //JOptionPane.showMessageDialog(null, "Deleted Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
		        num_p3.setText("");
		        cand_first_name_p3.setText("");
		        cand_last_name_p3.setText("");
		       // cand_add_p3.setText("");
		        addr_13.setText("");
		        addr_23.setText("");
		        addr_33.setText("");
		        area_3.setText("");
		        area_3.setText("");
		        city_town3.setText("");
		        pin_code_3.setText("");
		        country_t3.setText("");
		        state_t3.setText("");
		        cand_ph_p3.setText("");
		        cand_email_p3.setText("");
		        date_of_birth_3.setText("");
		        date_of_anniversary_3.setText("");
		        
		        
		        num_p3.setEditable(true);
		        cand_first_name_p3.setEditable(false);
		        cand_last_name_p3.setEditable(false);
		        //cand_add_p3.setEditable(false);
		        addr_13.setEditable(false);
		        addr_23.setEditable(false);
		        addr_33.setEditable(false);
		        area_3.setEditable(false);
		        city_town3.setEditable(false);
		        pin_code_3.setEditable(false);
		        country_t3.setEditable(false);
		        state_t3.setEditable(false);
		        cand_ph_p3.setEditable(false);
		        cand_email_p3.setEditable(false);
		        date_of_birth_3.setEditable(false);
		        date_of_anniversary_3.setEditable(false);
		        
			} catch(Exception e5){
				System.err.println(e5);
			}
		  }
		} 
		
		else if (e.getActionCommand().equals("Reset")){
			num_p3.setText("");
			cand_first_name_p3.setText("");
			cand_last_name_p3.setText("");
			//cand_add_p3.setText("");
			addr_13.setText("");
	        addr_23.setText("");
	        addr_33.setText("");
	        area_3.setText("");
	        city_town3.setText("");
	        pin_code_3.setText("");
	        country_t3.setText("");
	        state_t3.setText("");
	        cand_ph_p3.setText("");
	        cand_email_p3.setText("");
	        date_of_birth_3.setText("");
	        date_of_anniversary_3.setText("");
			
			
			num_p3.setEditable(true);
			cand_first_name_p3.setEditable(false);
			cand_last_name_p3.setEditable(false);
			
			//cand_add_p3.setEditable(false);
			 addr_13.setEditable(false);
		     addr_23.setEditable(false);
		     addr_33.setEditable(false);
		     area_3.setEditable(false);
		     city_town3.setEditable(false);
		     pin_code_3.setEditable(false);
		     country_t3.setEditable(false);
		     state_t3.setEditable(false);
		     cand_ph_p3.setEditable(false);
		     cand_email_p3.setEditable(false);
		     date_of_birth_3.setEditable(false);
		     date_of_anniversary_3.setEditable(false);
		     
		}
		
		if (e.getActionCommand().equals("Retrive")){
			Statement stm = null;
			Connection conn = null;
			if(num_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Warning", "Ns Number Can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
			} else{
			try{
				Class.forName("org.h2.Driver");
		        conn = DriverManager.
		            getConnection("jdbc:h2:~/databaseapp", "sa", "");
		        stm = conn.createStatement();
			}
			catch(Exception e1){
				System.err.println(e1);
			}
			
			try{
		        String st = "select * from details where no = "+"'"+num_p2.getText()+"'";
		        ResultSet rs = stm.executeQuery(st);
		        
		        
		        
		        if(rs.first()){
		        	String s = rs.getString(1);
		        	String d = num_p2.getText();
		        	//System.out.println(s+d);
		        	//if (s.equals(d)){
		        		//System.out.println("in");
		        		cand_initial_p2.setText(rs.getString(2));
		        		cand_nam_p2.setText(rs.getString(3));
		        		addr_12.setText(rs.getString(4));
		        		addr_22.setText(rs.getString(5));
		        		area_2.setText(rs.getString(6));
		        		city_town2.setText(rs.getString(7));
		        		pin_code_2.setText(rs.getString(8));
		        		cand_ph_p2.setText(rs.getString(9));
		        		cand_email_p2.setText(rs.getString(10));
		        		Float f = rs.getFloat(11);
		        		//System.out.println(rs.getString(2));
		        		//cand_amt_p3.setText(f.toString());
		        		num_p2.setEditable(false);
		        		//cand_nam_p3.setEditable(true);
		        		//cand_add_p3.setEditable(true);
		        		//cand_amt_p3.setEditable(true);
		        		//break;
		        	} 
		        	else{
		        		//System.out.println("not found");
		        		Telegraph tele = new Telegraph("Error", "Entrie not found...", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
		        		//JOptionPane.showMessageDialog(null, "Entrie not found...", "Error", JOptionPane.ERROR_MESSAGE);
		        	}
		       // }
		        // add application code here
		        conn.close();
			} catch(Exception e6){
				System.err.println(e6);
				
			}
			}
		}
		if (e.getSource().equals(payment_mode)){
			if (payment_mode.getSelectedItem().equals("CASH")){
				payment_num.setText("");
				bank_name.setText("");
				branch_nam.setText("");
				issue_dat.setText("");
				bank_received.setSelectedIndex(0);
				payment_num.setEditable(false);
				bank_name.setEditable(false);
				branch_nam.setEditable(false);
				issue_dat.setEditable(false);
				bank_received.setEnabled(false);
			} else if (payment_mode.getSelectedItem().equals("CHQ")){
				payment_num.setText("");
				bank_name.setText("");
				branch_nam.setText("");
				issue_dat.setText("");
				bank_received.setSelectedIndex(0);
				payment_num.setEditable(true);
				bank_name.setEditable(true);
				branch_nam.setEditable(true);
				issue_dat.setEditable(true);
				bank_received.setEnabled(true);
			} else if (payment_mode.getSelectedItem().equals("A/C TRANSFER")){
				payment_num.setText("");
				bank_name.setText("");
				branch_nam.setText("");
				issue_dat.setText("");
				bank_received.setSelectedIndex(0);
				payment_num.setEditable(true);
				bank_name.setEditable(false);
				branch_nam.setEditable(false);
				issue_dat.setEditable(true);
				bank_received.setEnabled(true);
			}
			
		}
		
		if (e.getSource() == reset1){
			num_p2.setEditable(true);
			num_p2.setText("");
			cand_initial_p2.setText("");
			cand_nam_p2.setText("");
			addr_12.setText("");
			addr_22.setText("");
			area_2.setText("");
			city_town2.setText("");
			pin_code_2.setText("");
			cand_ph_p2.setText("");
			cand_email_p2.setText("");
			don_type.setSelectedIndex(0);
			cand_amt_p2.setText("");
			payment_mode.setSelectedIndex(0);
			payment_num.setText("");
			issue_dat.setText("");
			bank_name.setText("");
			branch_nam.setText("");
			bank_received.setSelectedIndex(0);
			
			
		}
		
		if (e.getActionCommand().equals("Proceed")){
			TelegraphQueue que = new TelegraphQueue();
			if (cand_nam_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Name", "Name can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(addr_12.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Address", "Address can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(city_town2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter City", "City can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if(cand_amt_p2.getText().length() == 0){
				Telegraph tele = new Telegraph("Enter Amount", "Amount can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
			} else if((payment_mode.getSelectedIndex() != 0) && (payment_num.getText().length() == 0)) {
					Telegraph tele = new Telegraph("Enter Cheque/D.D No.", "Cheque/D.D number can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					que.add(tele);
													
			} else if((payment_mode.getSelectedIndex() != 0) && (issue_dat.getText().length() == 0)) {
				Telegraph tele = new Telegraph("Enter Cheque/Transfer Date", "Cheque/Transfer Date can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
												
			}else if((payment_mode.getSelectedIndex() != 0) && (bank_received.getSelectedIndex() == 0)) {
				Telegraph tele = new Telegraph("Select Bank Received", "Bank Received By VRNT can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
												
			} else {				
				
				try {
					Class.forName("org.h2.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Connection conn = null;
				try {
					conn = DriverManager.
					    getConnection("jdbc:h2:~/databaseapp", "sa", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        // add application code here
				
		        try {
					Statement stm = conn.createStatement();
					String st = "insert into bill values("+receipt_no.getText()+", '"+date.getText()+"', '"+num_p2.getText()+"', '"+cand_initial_p2.getText()+"', '"+cand_nam_p2.getText()+"'"+","+"'"+addr_12.getText()+"'"+", '"+addr_22.getText()+"', '"+area_2.getText()+"', '"+city_town2.getText()+"', '"+pin_code_2.getText()+"', '"+cand_ph_p2.getText()+"', '"+cand_email_p2.getText()+"', '"+don_type.getSelectedItem()+"', "+cand_amt_p2.getText()+", '"+payment_mode.getSelectedItem()+"', '"+payment_num.getText()+"', '"+issue_dat.getText()+"', '"+bank_name.getText()+"', '"+branch_nam.getText()+"', '"+bank_received.getSelectedItem()+"', 'cleared'"+")";
					stm.executeUpdate(st);
					if(num_p2.getText().length() != 0){
						String st1 = "select amount from details where no = '"+num_p2.getText()+"'";
						ResultSet rs2 = stm.executeQuery(st1);
						rs2.next();
						double amt = rs2.getDouble(1);
						
						
						amt += Integer.parseInt(cand_amt_p2.getText());
						String st2 = "update details set amount = "+amt+"where no = '"+num_p2.getText()+"'";
						stm.executeUpdate(st2);
					}
					Telegraph tele = new Telegraph("Success", "Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
		        
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
			PdfWriter writer = null;
			
			File vrntBill = new File("Vrnt Donation Bill");
			if(!vrntBill.exists()){
				try{
					vrntBill.mkdir();
				} catch(Exception e1){
					System.err.println(e1);
				}
			}
			
			File s = null;
			try {
//				s = File.createTempFile("vrnt_bill", ".pdf");
				
				s = new File(vrntBill.getAbsolutePath()+"/Bill_"+receipt_no.getText()+".pdf");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Document doc = new Document();
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doc.setPageSize(PageSize.A5);
			//doc.setMargins(36, 72, 108, 180);
			doc.setMarginMirroring(true);
			doc.open();
			com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
			com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);			
			com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
			com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
			com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
			PdfPTable tab = new PdfPTable(3);
			PdfPCell a, b, c, d, f, g;
			//tab.setWidths(new int[] {80, 60, 60});
			tab.setWidthPercentage(100);
			
			NumberFormat formatter = new RuleBasedNumberFormat(Locale.ENGLISH, RuleBasedNumberFormat.SPELLOUT);
			
			String result = formatter.format(Integer.parseInt(cand_amt_p2.getText()));
			

			Phrase vrnt = new Phrase(" ", fi);

			c = new PdfPCell(vrnt);

			c.setFixedHeight(40);

			Phrase rece = new Phrase("Receipt Number: "+receipt_no.getText(), si);
			
			
			Phrase donat = new Phrase(don_type.getSelectedItem().toString(), si);
			g = new PdfPCell(donat);
			g.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String receivedDate = dateFormat.format(Date.valueOf(date.getText()));
			
			Phrase dat = new Phrase("Date: "+receivedDate, si);
			
			Phrase rs = new Phrase("AMOUNT Rs. "+cand_amt_p2.getText(), si);
			Phrase nsno = new Phrase("N.S No: "+num_p2.getText(), si);
			
			Phrase rup = new Phrase("RUPEES "+result.toUpperCase()+" ONLY", ni);
			String address = ""; int emptyAddressCount = 0;
			
			address += (addr_12.getText().length() != 0) ?  addr_12.getText() : emptyAddressCount++;
				
			address += (addr_22.getText().length() != 0) ? "\n"+addr_22.getText() : emptyAddressCount++;
			
			address += (area_2.getText().length() != 0) ? "\n"+area_2.getText() : emptyAddressCount++;
			
			address += (city_town2.getText().length() != 0) ? "\n"+city_town2.getText() : emptyAddressCount++;
			
			address += (pin_code_2.getText().length() != 0) ? " "+pin_code_2.getText() : " ";
			
			address += (cand_ph_p2.getText().length() != 0) ? "\nPh No: "+cand_ph_p2.getText() : emptyAddressCount++;
			
			address += (cand_email_p2.getText().length() != 0) ? "\nEmail: "+cand_email_p2.getText() : emptyAddressCount++;
			
			for(int i=0; i<emptyAddressCount; i++)
				address += "\n";
			
			Phrase addr = new Phrase(address, si);
			
			String paymentDetails = ""; 
			paymentDetails += "Mode Of Receipt: "+payment_mode.getSelectedItem();
			switch(payment_mode.getSelectedItem().toString()){
				case "CHQ":
					paymentDetails += "\n" + "CHEQUE NO : " + payment_num.getText()  + "\n" +"Date: " + issue_dat.getText();
					paymentDetails += "\n" + "Bank Drawn: " + bank_name.getText();
					paymentDetails += "\n" + "Branch: " + branch_nam.getText();
					break;
				case "A/C TRANSFER":
					paymentDetails += "\n" + "TRNF NO : " + payment_num.getText()  + "\n" +"Date: " + issue_dat.getText();
					break;
			}
			
			Phrase mod = new Phrase(paymentDetails, si);

			
			Phrase don_for = new Phrase("THIS DONATION IS FOR CORPUS OF THE TRUST", ni);
			Phrase sign_don = new Phrase("SIGNATURE OF DONOR", si);
			Phrase sign_rec = new Phrase("Signature of Receiver", si);
			Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
			

			String name = cand_nam_p2.getText()+" "+cand_initial_p2.getText();

			Phrase nam = new Phrase(name, si);

			

			Phrase amt = new Phrase("Donation towards "+don_type.getSelectedItem()+" fund", si);
			
			Phrase pay = new Phrase("Payment Details", si);
			
			
			Phrase emp = new Phrase("\0");
			
			
			Phrase ins1 = new Phrase(" ", ni);
			
			PdfPCell nam_c = new PdfPCell(nam);
			nam_c.setFixedHeight(20);
			
			PdfPCell address_c = new PdfPCell(addr);
			address_c.setFixedHeight(80);
			
			PdfPCell dummy = new PdfPCell(emp);
			PdfPCell rece_c = new PdfPCell(rece);
			
			PdfPCell dat_c = new PdfPCell(dat);
			dat_c.setHorizontalAlignment(Element.ALIGN_RIGHT);

			PdfPCell nsno_c = new PdfPCell(nsno);
			PdfPCell amt_c = new PdfPCell(amt);
			PdfPCell rs_c = new PdfPCell(rs);
			
			PdfPCell rup_c = new PdfPCell(rup);
			PdfPCell pay_c = new PdfPCell(pay);
			PdfPCell mod_c = new PdfPCell(mod);
			mod_c.setFixedHeight(80);

			PdfPCell don_for_c = new PdfPCell(don_for);
			PdfPCell empty = new PdfPCell(emp);
			
			PdfPCell sign_don_c = new PdfPCell(sign_don);
			PdfPCell sign_rec_c = new PdfPCell(sign_rec);
			PdfPCell trust_c = new PdfPCell(trust);
			PdfPCell ins1_c = new PdfPCell(ins1);
			ins1_c.setFixedHeight(20);

			empty.setFixedHeight(6);

//			pay_c.setColspan(2);

//			rs_c.setColspan(2);
			rup_c.setColspan(3);
//			mod_c.setColspan(2);
			
			nsno_c.setColspan(2);
			nam_c.setColspan(2);
			address_c.setColspan(2);

			empty.setColspan(3);
			don_for_c.setColspan(3);
			ins1_c.setColspan(3);


			c.setBorder(0);
			g.setBorder(0);
			empty.setBorder(0);
			dummy.setBorder(0);
			nam_c.setBorder(0);
			rece_c.setBorder(0);
			address_c.setBorder(0);

			dat_c.setBorder(0);

			nsno_c.setBorder(0);

			amt_c.setBorder(0);
			rs_c.setBorder(0);
			rup_c.setBorder(0);
			pay_c.setBorder(0);
			mod_c.setBorder(0);

			don_for_c.setBorder(0);
			sign_don_c.setBorder(0);
			sign_rec_c.setBorder(0);
			trust_c.setBorder(0);
			ins1_c.setBorder(0);

//			PdfContentByte cb = writer.getDirectContent();
//			cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
//			cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
			
//			cb.closePath();
//			cb.stroke();
//			com.itextpdf.text.Image donor_img = null;
//			com.itextpdf.text.Image off_img = null;
			com.itextpdf.text.Image  sign = null;
			PdfPCell signature = null;
			
			try {
//				donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
//				off_img = Image.getInstance(this.getClass().getResource("off.png"));
				sign = Image.getInstance(this.getClass().getResource("ET-SIGN.png"));
				signature = new PdfPCell(sign);
//				donor_img.scaleAbsolute(150, 150);

				sign.scaleAbsolute(80, 15);
//				off_img.scaleAbsolute(150, 150);
//				donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
//				off_img.setAbsolutePosition(120, 90);
				
			} catch (BadElementException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (MalformedURLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			signature.setBorder(0);
			signature.setColspan(3);
			signature.setHorizontalAlignment(Element.ALIGN_RIGHT);
			signature.setPaddingRight(30);
			for (int i = 0; i < 2; i++){

			tab.addCell(c);

			
			tab.addCell(empty);
			
			
			tab.addCell(rece_c);
			tab.addCell(g);
			tab.addCell(dat_c);
			tab.addCell(empty);
			
			tab.addCell(rs_c);
			tab.addCell(nsno_c);
			
			tab.addCell(dummy);
			
			tab.addCell(nam_c);
			tab.addCell(mod_c);
			
			tab.addCell(address_c);
			
			
			tab.addCell(rup_c);
			tab.addCell(don_for_c);

			tab.addCell(empty);
			
			
			
			tab.addCell(signature);
			tab.addCell(sign_don_c);
			tab.addCell(sign_rec_c);
			tab.addCell(trust_c);
			tab.addCell(empty);
			tab.addCell(ins1_c);

			
			if (i == 0){
				tab.addCell(empty);
				tab.addCell(empty);
			}
			}

			try {
				doc.add(tab);
				
//				doc.add(donor_img);
//				doc.add(off_img);
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			num_p2.setEditable(true);
			num_p2.setText("");
			cand_initial_p2.setText("");
			cand_nam_p2.setText("");
			addr_12.setText("");
			addr_22.setText("");
			area_2.setText("");
			city_town2.setText("");
			pin_code_2.setText("");
			cand_ph_p2.setText("");
			cand_email_p2.setText("");
			don_type.setSelectedIndex(0);
			cand_amt_p2.setText("");
			payment_mode.setSelectedIndex(0);
			payment_num.setText("");
			issue_dat.setText("");
			bank_name.setText("");
			branch_nam.setText("");
			bank_received.setSelectedIndex(0);
			try{
				Class.forName("org.h2.Driver");
	   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/databaseapp","sa","");
				Statement stm1=conn1.createStatement();
				String st = "select max(receipt) from bill";
				
				ResultSet rs1 = stm1.executeQuery(st);
				rs1.next();
				int s1 = rs1.getInt(1);		
				if (s1 == 0){
					receipt_no.setEditable(true);
				} else {
					receipt_no.setText(String.valueOf(s1+1));
					receipt_no.setEditable(false);
				}
				
				
				conn1.close();
			}
			catch (ClassNotFoundException e1)
			{
				Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
			}
			catch(SQLException ee)
			{
				System.err.println(ee);
				
				Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
			}
			
//			s.deleteOnExit();
			doc.close();
			try {
				Desktop.getDesktop().open(s);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//print_interior();
		  }
		} 
		
		if (e.getSource() == cancel){
			String rece = JOptionPane.showInputDialog(null, "Enter Receipt No.");
			if (rece != null){
				try{
					Class.forName("org.h2.Driver");
		   			Connection conn1=DriverManager.getConnection("jdbc:h2:~/databaseapp","sa","");
					Statement stm1=conn1.createStatement();
					String st = "select no,amt,status from bill where receipt = "+Integer.parseInt(rece);
					
					ResultSet rs1 = stm1.executeQuery(st);
					rs1.next();
					String s1 = rs1.getString(1);		
					double s2 = rs1.getDouble(2);
					String s3 = rs1.getString(3);
					if (s3.equals("cleared")){
					if (s1.length() != 0){
						String st1 = "select amount from details where no = '"+s1+"'";
						ResultSet rs2 = stm1.executeQuery(st1);
						rs2.next();
						double d1 = rs2.getDouble(1);
						double tot = d1 - s2;
						String st2 = "update details set amount = "+tot+" where no = '"+s1+"'";
						stm1.executeUpdate(st2);
					} 
					
						String st3 = "update bill set status = 'cancelled' where receipt = "+Integer.parseInt(rece);
						stm1.executeUpdate(st3);
					} else{
						Telegraph tele = new Telegraph("Warning!", "Receipt is already cancelled...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}
					Telegraph tele = new Telegraph("Success", "Receipt cancelled successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					conn1.close();
				}
				catch (ClassNotFoundException e1)
				{
					System.err.println(e1);
					
					Telegraph tele = new Telegraph("Warning!", "Please Check Your Database Driver", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
					//JOptionPane.showMessageDialog(null,"Please Check Your Database Driver", "Warning!",JOptionPane.WARNING_MESSAGE);
				}
				catch(SQLException ee)
				{
					System.err.println(ee);
					if (ee.getErrorCode() == 2000){
						Telegraph tele = new Telegraph("Warning!", "Receipt not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}else {
						Telegraph tele = new Telegraph("Warning!", "Some problem might be occured in Database", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
					}
					//JOptionPane.showMessageDialog(null,"Some problem might be occured in Database","Warning!",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
			
			if (e.getSource() == statement){
				String strtdate = JOptionPane.showInputDialog(null, "Enter starting date", date.getText());
				if(strtdate != null){
					String enddate = JOptionPane.showInputDialog(null, "Enter ending date", date.getText());
				if(enddate != null){
				
				PdfWriter writer = null;
				
				//Paragraph para = new Paragraph();
				//para.setAlignment(Element.RECTANGLE);
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/databaseapp", "sa", "");
			        Statement stmt = conn.createStatement();
			        String st = "select receipt,dat,no,initial,name,amt,pay_mode,chqno from bill where dat between '"+strtdate+"' and '"+enddate+"' and status = 'cleared'";
			        ResultSet rs = stmt.executeQuery(st);
			       if(rs.isBeforeFirst()){
			    	   int ret = gen_pdf.showSaveDialog(this);
						File s = null;
						if (ret == JFileChooser.APPROVE_OPTION){
							s = gen_pdf.getSelectedFile();
							String stm = s.toString();
							if(!stm.endsWith(".pdf")){
								s = new File(stm+".pdf");
							}
						
						Document doc = new Document();
						try {
							writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						doc.setPageSize(PageSize.A4);
						//doc.setMargins(36, 72, 108, 180);
						doc.setMarginMirroring(true);
						doc.open();
						com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
						PdfPTable table = new PdfPTable(6);
						PdfPTable tab = new PdfPTable(2);
						table.setComplete(true);
						table.setWidthPercentage(100);
						table.addCell(new Phrase("Receipt No"));
						table.addCell(new Phrase("Date"));
						table.addCell(new Phrase("N.S No"));
						table.addCell(new Phrase("Name"));
						table.addCell(new Phrase("Chque/D.D No"));
						table.addCell(new Phrase("Amount"));
						table.setHeaderRows(6);
						double cashtot=0, chqtot=0, ddtot=0, total, amt;
			        while(rs.next()){
			        	 table.addCell(new Phrase(rs.getString(1), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getDate(2)), n));
			        	 table.addCell(new Phrase(rs.getString(3), n));
			        	 table.addCell(new Phrase(rs.getString(5) + " " + rs.getString(4), n));			        	 			        	 
			        	 table.addCell(new Phrase(rs.getString(8), n));
			        	 amt = rs.getDouble(6);
			        	 table.addCell(new Phrase(String.valueOf(amt), n));
			        	 String sss = rs.getString(7);
			        	 if (sss.equals("CASH")){
			        		 cashtot += amt;
			        	 } else if(sss.equals("CHQ")){
			        		 chqtot += amt;
			        	 } else if(sss.equals("A/C TRANSFER")){
			        		 ddtot += amt;
			        	 }
			        	
			        	
			        }
			        total = cashtot+chqtot+ddtot;
			        try {
			        	
			        	doc.add(new Phrase("Statement From "+strtdate+" To "+enddate));
						doc.add(table);
						PdfPCell e1 = new PdfPCell(new Phrase("\0"));
						e1.setColspan(2);
						e1.setBorder(0);
						tab.addCell(e1);
						PdfPCell p2 = new PdfPCell(new Phrase("Total Amount By Cash "));
						p2.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p2.setBorder(0);
						tab.addCell(p2);
						PdfPCell p3 = new PdfPCell(new Phrase(String.valueOf(cashtot)));
						p3.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p3.setBorder(0);
						tab.addCell(p3);
						PdfPCell p5 = new PdfPCell(new Phrase("Total Amount By Cheque "));
						p5.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p5.setBorder(0);
						tab.addCell(p5);
						PdfPCell p4 = new PdfPCell(new Phrase(String.valueOf(chqtot)));
						p4.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p4.setBorder(0);
						tab.addCell(p4);
						PdfPCell p6 = new PdfPCell(new Phrase("Total Amount By A/C Transfer "));
						p6.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p6.setBorder(0);
						tab.addCell(p6);
						PdfPCell p7 = new PdfPCell(new Phrase(String.valueOf(ddtot)));
						p7.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p7.setBorder(0);
						tab.addCell(p7);
						PdfPCell p8 = new PdfPCell(new Phrase("Total "));
						p8.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p8.setBorder(0);
						tab.addCell(p8);
						PdfPCell p9 = new PdfPCell(new Phrase(String.valueOf(total)));
						p9.setHorizontalAlignment(Element.ALIGN_RIGHT);
						p9.setBorder(0);
						tab.addCell(p9);
						tab.addCell(e1);
						Phrase p = new Phrase("*** End Of Statement ***");
						PdfPCell p1 = new PdfPCell(p);
						p1.setHorizontalAlignment(Element.ALIGN_CENTER);
						p1.setColspan(2);
						p1.setBorder(0);
						tab.addCell(p1);
						doc.add(tab);
						
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					doc.close();
					//System.out.println("sucess");
					Telegraph tele = new Telegraph("Success", "Statement generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
						}} else{
			        	Telegraph tele = new Telegraph("Invalid Date Range", "The Date Range you entered is not valid...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
			        }
			       }
				catch(Exception e4){
					System.err.println(e4);
				}
				
			  
			 }
			}
		  }
		
			
			if (e.getSource() == stmt_full){
				String startdate = JOptionPane.showInputDialog(null, "Enter starting date", date.getText());
				if(startdate != null){
					String endate = JOptionPane.showInputDialog(null, "Enter ending date", date.getText());
				if(endate != null){
				
				
				//Paragraph para = new Paragraph();
				//para.setAlignment(Element.RECTANGLE);
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/databaseapp", "sa", "");
			        Statement stmt = conn.createStatement();
			        String st = "select * from bill where dat between '"+startdate+"' and '"+endate+"' and status = 'cleared'";
			        ResultSet rs = stmt.executeQuery(st);
			        String aa,ab,a1,a2,a3,a4,a5,a6,a7;
			        
			      // System.out.println(rs.isBeforeFirst());
			        if (rs.isBeforeFirst()){
			        	PdfWriter writer = null;
						int ret = gen_pdf.showSaveDialog(this);
						File s = null;
						if (ret == JFileChooser.APPROVE_OPTION){
							s = gen_pdf.getSelectedFile();
							String stm = s.toString();
							if(!stm.endsWith(".pdf")){
								s = new File(stm+".pdf");
							}
						
						Document doc = new Document();
						try {
							writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						doc.setPageSize(PageSize.A2);
						//doc.setMargins(36, 72, 108, 180);
						doc.setMarginMirroring(true);
						doc.open();
						com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
						PdfPTable table = new PdfPTable(9);
						PdfPTable tab = new PdfPTable(2);
						table.setComplete(true);
						table.setWidthPercentage(100);
						table.addCell(new Phrase("Receipt No"));
						table.addCell(new Phrase("Date"));
						table.addCell(new Phrase("N.S No"));
						table.addCell(new Phrase("Name"));
						table.addCell(new Phrase("Address"));
					//	table.addCell(new Phrase("Address Line 2"));
					//	table.addCell(new Phrase("City"));
						table.addCell(new Phrase("Donation Type"));				
						table.addCell(new Phrase("Amount"));
						table.addCell(new Phrase("Payment Mode"));
						table.addCell(new Phrase("Payment Details"));
					//	table.addCell(new Phrase("Date Of Issue"));
				//		table.addCell(new Phrase("Bank"));
				//		table.addCell(new Phrase("Branch"));
						
						//table.setHeaderRows(9);
						double cashtot=0, chqtot=0, ddtot=0, total, amt;
			        while(rs.next()){
			        	
			        	 table.addCell(new Phrase(rs.getString(1), n));
			        	 table.addCell(new Phrase(String.valueOf(rs.getDate(2)), n));
			        	 table.addCell(new Phrase(rs.getString(3), n));
			        	 table.addCell(new Phrase(rs.getString(5) + " " + rs.getString(4), n));
			        	// table.addCell(new Phrase(rs.getString(5), n));
			        	// table.addCell(new Phrase(rs.getString(6), n));
			        	// table.addCell(new Phrase(rs.getString(7), n));
			        	 table.addCell(new Phrase(rs.getString(6)+"\n"+rs.getString(7)+"\n"+rs.getString(8)+"\n"+rs.getString(9)+" - "+rs.getString(10), n));
			        				        	
			        	
			        	 table.addCell(new Phrase(rs.getString(13), n));
			        	// System.out.println(rs.getString(1)+aa);
			        	 amt = rs.getDouble(14);
			        	 table.addCell(new Phrase(String.valueOf(amt), n));
			        	 String sss = rs.getString(15);
			        	 table.addCell(new Phrase(sss, n));
			        	// table.addCell(new Phrase(String.valueOf(rs.getInt(11)), n));
			        	// table.addCell(new Phrase(rs.getString(12), n));
			        	// table.addCell(new Phrase(rs.getString(13), n));
			        	// table.addCell(new Phrase(rs.getString(14), n));
			        	table.addCell(new Phrase(String.valueOf(rs.getString(16))+"\n"+rs.getString(17)+"\n"+rs.getString(18)+"\n"+rs.getString(19), n));
			        	
			        	 if (sss.equals("CASH")){
			        		 cashtot += amt;
			        	 } else if(sss.equals("CHQ")){
			        		 chqtot += amt;
			        	 } else if(sss.equals("A/C TRANSFER")){
			        		 ddtot += amt;
			        	 }
			        	 
			        }
			        total = cashtot+chqtot+ddtot;
			        try {
			        	 doc.add(new Phrase("Statement From "+startdate+" To "+endate));
		        		 
			        	 doc.add(table);
		        		 PdfPCell e1 = new PdfPCell(new Phrase("\0"));
							e1.setColspan(2);
							e1.setBorder(0);
							tab.addCell(e1);
							PdfPCell p2 = new PdfPCell(new Phrase("Total Amount By Cash "));
							p2.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p2.setBorder(0);
							tab.addCell(p2);
							PdfPCell p3 = new PdfPCell(new Phrase(String.valueOf(cashtot)));
							p3.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p3.setBorder(0);
							tab.addCell(p3);
							PdfPCell p5 = new PdfPCell(new Phrase("Total Amount By Cheque "));
							p5.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p5.setBorder(0);
							tab.addCell(p5);
							PdfPCell p4 = new PdfPCell(new Phrase(String.valueOf(chqtot)));
							p4.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p4.setBorder(0);
							tab.addCell(p4);
							PdfPCell p6 = new PdfPCell(new Phrase("Total Amount By A/C Transfer "));
							p6.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p6.setBorder(0);
							tab.addCell(p6);
							PdfPCell p7 = new PdfPCell(new Phrase(String.valueOf(ddtot)));
							p7.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p7.setBorder(0);
							tab.addCell(p7);
							PdfPCell p8 = new PdfPCell(new Phrase("Total "));
							p8.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p8.setBorder(0);
							tab.addCell(p8);
							PdfPCell p9 = new PdfPCell(new Phrase(String.valueOf(total)));
							p9.setHorizontalAlignment(Element.ALIGN_RIGHT);
							p9.setBorder(0);
							tab.addCell(p9);
							tab.addCell(e1);
							Phrase p = new Phrase("*** End Of Statement ***");
							PdfPCell p1 = new PdfPCell(p);
							p1.setHorizontalAlignment(Element.ALIGN_CENTER);
							p1.setColspan(2);
							p1.setBorder(0);
							tab.addCell(p1);
							doc.add(tab);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					doc.close();
					Telegraph tele = new Telegraph("Success", "Complete Statement generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue que = new TelegraphQueue();
					que.add(tele);
			        }} else{
			        	Telegraph tele = new Telegraph("Invalid Date Range", "The Date Range you entered is not valid...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue que = new TelegraphQueue();
						que.add(tele);
			        }
				}
				catch(Exception e4){
					System.err.println(e4);
				}
				
				//System.out.println("sucess");
				
				
			 
			}
		  }
		}
			
			if (e.getSource() == recep){
				String r = JOptionPane.showInputDialog(null, "Enter Receipt No.");
				if (r != null){
					try {
						Class.forName("org.h2.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        Connection conn = null;
					try {
						conn = DriverManager.
						    getConnection("jdbc:h2:~/databaseapp", "sa", "");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        // add application code here
					String re = null, dates = null, nums= null, name = null, addrs1 = null, addrs2 = null, citys = null, types = null, mods = null, dats = null, ban = null, bran = null, stat = null;
					int chqs = 0;
					double amts = 0;
			        try {
						Statement stm = conn.createStatement();
						String st = "select * from bill where receipt = "+Integer.parseInt(r);
						ResultSet rs = stm.executeQuery(st);
						rs.next();
						re = rs.getString(1);
						dates = String.valueOf(rs.getDate(2));
						nums = rs.getString(3);
						name = rs.getString(4);
						addrs1 = rs.getString(5);
						addrs2 = rs.getString(6);
						citys = rs.getString(7);
						types = rs.getString(8);
						amts = rs.getDouble(9);
						mods = rs.getString(10);
						chqs = rs.getInt(11);
						dats = rs.getString(12);
						ban = rs.getString(13);
						bran = rs.getString(14);
						stat = rs.getString(15);
						
						//JOptionPane.showMessageDialog(null, "Saved Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						if(e2.getErrorCode() == 2000){
							Telegraph tele = new Telegraph("Receipt Not Found", "The Receipt Number you entered is not found...", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);
							TelegraphQueue que = new TelegraphQueue();
							que.add(tele);
						}
						
					}
			        if (stat.equals("cleared")){
			        
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
					
				PdfWriter writer = null;
				
				File s = null;
				try {
					s = File.createTempFile("vrnt_bill", ".pdf");
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Document doc = new Document();
				try {
					writer = PdfWriter.getInstance(doc, new FileOutputStream(s));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doc.setPageSize(PageSize.A4);
				//doc.setMargins(36, 72, 108, 180);
				doc.setMarginMirroring(true);
				doc.open();
				com.itextpdf.text.Font fi = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16);
				com.itextpdf.text.Font n = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10);
				com.itextpdf.text.Font nb = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
				com.itextpdf.text.Font si = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 8);
				com.itextpdf.text.Font ni = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 6);
				PdfPTable tab = new PdfPTable(3);
				PdfPCell a, b, c, d, f, g;
				//tab.setWidths(new int[] {80, 60, 60});
				tab.setWidthPercentage(100);
				
				NumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
				String result = formatter.format(amts);
				Phrase h = new Phrase("SRI GURUBHYO NAMAHA", ni);
				
				Phrase pan = new Phrase("PAN No.: AAATV3147P", ni);
				Phrase vrnt = new Phrase("VEDA RAKSHNA NIDHI TRUST (Regd.)", fi);
				Phrase vrnt_add = new Phrase("No.64/31, SUBRAMANIYAN STREET, WEST MAMBALAM, CHENNAI - 600 033.", ni);
				Phrase mail = new Phrase("e-mail: vrnt@vsnl.net \t Tel: 044-24740549", si);
				Phrase donat = new Phrase(types+" Donation", si);
				//Phrase ph = new Phrase("Tel: 044-24740549");
				a = new PdfPCell(h);
				b = new PdfPCell(pan);
				c = new PdfPCell(vrnt);
				d = new PdfPCell(vrnt_add);
				f = new PdfPCell(mail);
				g = new PdfPCell(donat);
				//g = new PdfPCell(ph);
				a.setColspan(2);
				//b.setColspan(3);
				c.setColspan(3);
				d.setColspan(3);
				f.setColspan(3);
				g.setColspan(3);
				a.setHorizontalAlignment(Element.ALIGN_RIGHT);
				b.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c.setHorizontalAlignment(Element.ALIGN_CENTER);
				d.setHorizontalAlignment(Element.ALIGN_CENTER);
				f.setHorizontalAlignment(Element.ALIGN_CENTER);
				g.setHorizontalAlignment(Element.ALIGN_CENTER);
				//g.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				Phrase t = new Phrase("To", si);
				PdfPCell to = new PdfPCell(t);
				to.setColspan(3);
				
				Phrase nam = new Phrase("     "+name, si);
				Phrase addr1 = new Phrase("       "+addrs1, ni);
				Phrase addr2 = null;
				Phrase city = null;
				if (addrs2.length() != 0){
				addr2 = new Phrase("       "+addrs2, ni);
				city = new Phrase("       "+citys, ni);
				} else{
					addr2 =  new Phrase("       "+citys, ni);
					city = new Phrase("\0", ni);
				}
				
				Phrase rece = new Phrase("Receipt No: "+re, si);
				Phrase dat = new Phrase("Date: "+dates, si);
				Phrase nsno = new Phrase("N.S No: "+nums, si);
				Phrase amt = new Phrase("Donation towards "+types+" fund", si);
				Phrase rs = new Phrase("Rs. "+amts, si);
				Phrase rup = new Phrase("Rupees "+result+" only", si);
				Phrase pay = new Phrase("Payment Details", si);
				Phrase mod = new Phrase("Mode Of Payment: "+mods, si);
				Phrase chq = new Phrase("Cheque / DD No: "+chqs, si);
				Phrase dated = new Phrase("Dated: "+dats, si);
				Phrase bank = new Phrase("Bank: "+ban, si);
				Phrase branch = new Phrase("Branch: "+bran, si);
				Phrase emp = new Phrase("\0");
				Phrase sign_don = new Phrase("Signature of Donor", si);
				Phrase sign_rec = new Phrase("Signature of Receiver", si);
				Phrase trust = new Phrase("Exe. Trustee/Treasurer", si);
				Phrase ins1 = new Phrase("1. Donations to the trust are exempt from income Tax Section 80-G of income Tax Act.", ni);
				Phrase ins2 = new Phrase("2. Please quote your N.S No. given above in future correspondence with us.", ni);
				PdfPCell nam_c = new PdfPCell(nam);
				PdfPCell addr1_c = new PdfPCell(addr1);
				PdfPCell addr2_c = new PdfPCell(addr2);
				PdfPCell city_c = new PdfPCell(city);
				PdfPCell rece_c = new PdfPCell(rece);
				PdfPCell dat_c = new PdfPCell(dat);
				PdfPCell nsno_c = new PdfPCell(nsno);
				PdfPCell amt_c = new PdfPCell(amt);
				PdfPCell rs_c = new PdfPCell(rs);
				PdfPCell rup_c = new PdfPCell(rup);
				PdfPCell pay_c = new PdfPCell(pay);
				PdfPCell mod_c = new PdfPCell(mod);
				PdfPCell chq_c = new PdfPCell(chq);
				PdfPCell dated_c = new PdfPCell(dated);
				PdfPCell bank_c = new PdfPCell(bank);
				PdfPCell branch_c = new PdfPCell(branch);
				PdfPCell empty = new PdfPCell(emp);
				PdfPCell sign_don_c = new PdfPCell(sign_don);
				PdfPCell sign_rec_c = new PdfPCell(sign_rec);
				PdfPCell trust_c = new PdfPCell(trust);
				PdfPCell ins1_c = new PdfPCell(ins1);
				PdfPCell ins2_c = new PdfPCell(ins2);
				empty.setFixedHeight(6);
				nam_c.setColspan(2);
				addr1_c.setColspan(2);
				addr2_c.setColspan(2);
				city_c.setColspan(3);
				amt_c.setColspan(2);
				rup_c.setColspan(3);
				pay_c.setColspan(3);
				chq_c.setColspan(2);
				bank_c.setColspan(2);
				branch_c.setColspan(3);
				empty.setColspan(3);
				ins1_c.setColspan(3);
				ins2_c.setColspan(3);
				a.setBorder(0);
				b.setBorder(0);
				c.setBorder(0);
				d.setBorder(0);
				f.setBorder(0);
				g.setBorder(0);
				empty.setBorder(0);
				to.setBorder(0);
				nam_c.setBorder(0);
				rece_c.setBorder(0);
				addr1_c.setBorder(0);
				dat_c.setBorder(0);
				addr2_c.setBorder(0);
				nsno_c.setBorder(0);
				city_c.setBorder(0);
				amt_c.setBorder(0);
				rs_c.setBorder(0);
				rup_c.setBorder(0);
				pay_c.setBorder(0);
				mod_c.setBorder(0);
				chq_c.setBorder(0);
				dated_c.setBorder(0);
				bank_c.setBorder(0);
				branch_c.setBorder(0);
				sign_don_c.setBorder(0);
				sign_rec_c.setBorder(0);
				trust_c.setBorder(0);
				ins1_c.setBorder(0);
				ins2_c.setBorder(0);
				PdfContentByte cb = writer.getDirectContent();
				cb.roundRectangle(20, doc.getPageSize().getHeight()/2+5, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
				cb.roundRectangle(20, 40, doc.getPageSize().getWidth()-40, doc.getPageSize().getHeight()/2-40, 20);
				
				//cb.closePath();
				cb.stroke();
				com.itextpdf.text.Image donor_img = null;
				com.itextpdf.text.Image off_img = null;
				try {
					donor_img = Image.getInstance(this.getClass().getResource("donr.png"));
					off_img = Image.getInstance(this.getClass().getResource("off.png"));
					donor_img.scaleAbsolute(150, 150);
					
					off_img.scaleAbsolute(150, 150);
					donor_img.setAbsolutePosition(120, doc.getPageSize().getHeight()/2+50);
					off_img.setAbsolutePosition(120, 90);
					
				} catch (BadElementException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (MalformedURLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} 
				for (int i = 0; i < 2; i++){
				tab.addCell(a);
				tab.addCell(b);
				tab.addCell(c);
				tab.addCell(d);
				tab.addCell(f);
				tab.addCell(g);
				tab.addCell(empty);
				tab.addCell(to);			
				tab.addCell(nam_c);
				tab.addCell(rece_c);
				tab.addCell(addr1_c);
				tab.addCell(dat_c);
				tab.addCell(addr2_c);
				tab.addCell(nsno_c);
				tab.addCell(city_c);
				tab.addCell(empty);
				tab.addCell(amt_c);
				tab.addCell(rs_c);
				tab.addCell(rup_c);
				tab.addCell(empty);
				tab.addCell(pay_c);
				tab.addCell(mod_c);
				tab.addCell(chq_c);
				tab.addCell(dated_c);
				tab.addCell(bank_c);
				tab.addCell(branch_c);
				tab.addCell(empty);
				tab.addCell(sign_don_c);
				tab.addCell(sign_rec_c);
				tab.addCell(trust_c);
				tab.addCell(empty);
				tab.addCell(ins1_c);
				tab.addCell(ins2_c);
				if (i == 0){
					tab.addCell(empty);
					//tab.addCell(empty);
				}
				}
				//tab.addCell(g);
				try {
					doc.add(tab);
					doc.add(donor_img);
					doc.add(off_img);
				} catch (DocumentException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				Telegraph tele = new Telegraph("Success", "Receipt generated successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
				TelegraphQueue quee = new TelegraphQueue();
				quee.add(tele);
				
				try {
					Desktop.getDesktop().open(s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s.deleteOnExit();
				doc.close(); 
			        } else {
			        	Telegraph tele = new Telegraph("Receipt Cancelled", "The Receipt is cancelled", TelegraphType.NOTIFICATION_ERROR, WindowPosition.BOTTOMRIGHT, 4000);
						TelegraphQueue quee = new TelegraphQueue();
						quee.add(tele);
			        }
				}
			}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		int col = edit_table.columnAtPoint(e.getPoint());
		
		if(col == 8){
			int row = edit_table.rowAtPoint(e.getPoint());			
			String nsNum = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 0).toString();
			String select = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 8).toString();
			
			if(select.equals("S")){
				
				edit_table.getModel().setValueAt("DS", edit_table.convertRowIndexToModel(row), 8);
				select = "DS";
			}
			else{
				edit_table.getModel().setValueAt("S", edit_table.convertRowIndexToModel(row), 8);
				select = "S";
			}
			
			annualReportStatus.put(nsNum, select);
		}else if(col == 9){
			int row = edit_table.rowAtPoint(e.getPoint());			
			String nsNum = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 0).toString();
			String select = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 9).toString();
			
			if(select.equals("S")){
				
				edit_table.getModel().setValueAt("DS", edit_table.convertRowIndexToModel(row), 9);
				select = "DS";
			}
			else{
				edit_table.getModel().setValueAt("S", edit_table.convertRowIndexToModel(row), 9);
				select = "S";
			}
			
			prasadamStatus.put(nsNum, select);
		}
		
		if(e.getClickCount() == 2){
			
			if(col == 0 || col == 1){
				int row = edit_table.rowAtPoint(e.getPoint());			
				String nsNum = edit_table.getModel().getValueAt(edit_table.convertRowIndexToModel(row), 0).toString();
				//new UserDetails().ViewDetails(nsNum);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}