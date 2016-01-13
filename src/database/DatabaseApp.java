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
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;



import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.Currency;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
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
import com.toedter.calendar.JDateChooser;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.Properties;

public class DatabaseApp extends JFrame implements ActionListener, MouseListener {

	/**
	 * @param args
	 */
	
	JPanel panel = new JPanel();
	JPanel new_panel = new JPanel();
	JPanel view_panel = new JPanel();
	JPanel edit_panel = new JPanel();	
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
	JMenuItem clearDb = new JMenuItem("Clear Database");
	JMenuItem exit = new JMenuItem("Exit");
	
	JMenu allReport = new JMenu("All Report");
	JMenuItem csvAllData = new JMenuItem("CSV");
	JMenuItem pdfAllAddress = new JMenuItem("PDF");
	
	JMenu notifyReport = new JMenu("Notification Report");
	JMenuItem csvCurrentMonthData = new JMenuItem("CSV");
	JMenuItem pdfCurrentMonthAddress = new JMenuItem("PDF");
	
	
	JMenuItem refresh = new JMenuItem("Refresh");
	JMenuItem refreshNotify = new JMenuItem("Refresh Notity");
	JMenuItem search = new JMenuItem("Find");
	
	
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
	DateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	JDateChooser dobDatePicker1, dobDatePicker2, annivDatePicker1, annivDatePicker2;
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
		tab_pane.addTab("Notify", notifyPanel);		
		
		tab_pane.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				// TODO Auto-generated method stub
				 JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
			     int index = sourceTabbedPane.getSelectedIndex();			     
			     
			     if(index == 1){			    	
			    	model.setRowCount(0);
					view_tab_data();
										
			     } else if(index == 3){
			    	notifyTableModel.setRowCount(0);
					NotifyTableData();
					
			     }
			}
			
		});
		
		panel.add(tab_pane, BorderLayout.CENTER);
		//interior();
		//add(panel);
		setVisible(true);
	}
	
	public void new_interior(){
		file.setMnemonic(KeyEvent.VK_F);
		
		help.setMnemonic(KeyEvent.VK_H);
		Import.setMnemonic(KeyEvent.VK_I);
		export.setMnemonic(KeyEvent.VK_E);
		clearDb.setMnemonic(KeyEvent.VK_D);
		exit.setMnemonic(KeyEvent.VK_X);
		
		allReport.setMnemonic(KeyEvent.VK_A);
		csvAllData.setMnemonic(KeyEvent.VK_C);
		pdfAllAddress.setMnemonic(KeyEvent.VK_P);
		
		notifyReport.setMnemonic(KeyEvent.VK_P);
		csvCurrentMonthData.setMnemonic(KeyEvent.VK_C);
		pdfCurrentMonthAddress.setMnemonic(KeyEvent.VK_P);
		
//		pdf.setMnemonic(KeyEvent.VK_P);
		search.setMnemonic(KeyEvent.VK_F);
		refresh.setMnemonic(KeyEvent.VK_R);
		
		
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
		
		refreshNotify.setIcon(refresh_img);
		refreshNotify.addActionListener(this);
		file.add(refreshNotify);
		
//		file.add(pdf);
//		pdf.setIcon(pdf_img);
//		pdf.addActionListener(this);
//		pdf.setAccelerator(KeyStroke.getKeyStroke('P', ActionEvent.CTRL_MASK));
		
		file.add(allReport);
		allReport.setIcon(stmt_img);
		csvAllData.addActionListener(this);
		csvAllData.setIcon(rece_img);
		allReport.add(csvAllData);
		pdfAllAddress.addActionListener(this);
		pdfAllAddress.setIcon(pdf_img);
		allReport.add(pdfAllAddress);
		
		file.add(notifyReport);
		notifyReport.setIcon(st_img);
		csvCurrentMonthData.addActionListener(this);
		csvCurrentMonthData.setIcon(rece_img);
		notifyReport.add(csvCurrentMonthData);
		pdfCurrentMonthAddress.addActionListener(this);
		pdfCurrentMonthAddress.setIcon(pdf_img);
		notifyReport.add(pdfCurrentMonthAddress);
		
		search.addActionListener(this);
		search.setIcon(find_img);
		search.setAccelerator(KeyStroke.getKeyStroke('F', ActionEvent.CTRL_MASK));
		file.add(search);
		
		clearDb.addActionListener(this);
		clearDb.setIcon(cancel_img);
		clearDb.setAccelerator(KeyStroke.getKeyStroke('D', ActionEvent.CTRL_MASK));
		file.add(clearDb);
		
		
		file.add(exit);
		exit.setIcon(exit_img);
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke('X', ActionEvent.ALT_MASK));
		
		help.add(about);
		about.setIcon(about_img);
		about.addActionListener(this);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menu.add(file);		
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
		//date_of_birth_1 = new JTextField(15);
		//date_of_anniversary_1 = new JTextField(15);
		
		dobDatePicker1 = new JDateChooser();		
		//dobDatePicker1.setFormats(standardFormat);
		
		annivDatePicker1 = new JDateChooser();		
		//annivDatePicker1.setFormats(standardFormat);
		
		save = new JButton("Save");
		reset = new JButton("Reset");	
		
		
		
		
		annivDatePicker1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					save.doClick();					
					annivDatePicker1.transferFocus();
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
		//dobDatePicker1.setBorder(border);
		//annivDatePicker1.setBorder(border);
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
		new_panel.add(dobDatePicker1, c);
		
		c.gridx = 0; c.gridy = 13;
		new_panel.add(anniversary_1, c);
		c.gridx = 1; c.gridy = 13;
		new_panel.add(annivDatePicker1, c);
		
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
		//edit_table.setAutoCreateRowSorter(true);
		Font ff = new Font("Arial", Font.PLAIN, 16);
		Font tableFont = new Font("Calibiri", Font.PLAIN, 14);
		viewTabStatusBar.setFont(ff);
		edit_table.setFont(tableFont);
		edit_table.getTableHeader().setFont(new Font("Calibiri", Font.BOLD, 18));
		
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
			String dob = simpleFormat.format(rs.getDate(14));
			String anniversary = simpleFormat.format(rs.getDate(15));
			
			
			
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
		BorderLayout layout = new BorderLayout();
		notifyPanel.setLayout(layout);
		notifyTable.addMouseListener(this);
//		donationRegisterTable.setRowHeight(150);
		
		Font ff = new Font("Arial", Font.PLAIN, 14);
		notifyTable.setFont(ff);
		notifyTable.setRowHeight(60);
		notifyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		//notifyTable.getTableHeader().setPreferredSize(new Dimension(100, 100));
		
		notifyTable.setAutoCreateRowSorter(true);
		
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
		notifyPanel.add(jspNotify, layout.CENTER);
	}
	
	void NotifyTableData(){
		
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		try{
			Connection conn = DriverManager.
				    getConnection("jdbc:h2:~/databaseapp", "sa", "");
			Statement stm = conn.createStatement();
			String st = "select * from details where EXTRACT(MONTH FROM date_of_birth) = "+currentMonth+" OR EXTRACT(MONTH FROM anniversary) = "+currentMonth;
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
				String dob = simpleFormat.format(rs.getDate(14));
				String anniversary = simpleFormat.format(rs.getDate(15));
				
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
		//date_of_birth_3 = new JTextField(15);
		//date_of_anniversary_3 = new JTextField(15);
		
		dobDatePicker2 = new JDateChooser();		
		//dobDatePicker2.setFormats(standardFormat);
		
		annivDatePicker2 = new JDateChooser();		
		//annivDatePicker2.setFormats(standardFormat);
		
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
		
		annivDatePicker2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_ENTER){
					edit.doClick();
					annivDatePicker2.transferFocus();
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
		//dobDatePicker2.setBorder(border);
		//annivDatePicker2.setBorder(border);
		
		
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
		dobDatePicker2.setEnabled(false);
		//dobDatePicker2.setEditable(false);
		annivDatePicker2.setEnabled(false);
		//annivDatePicker2.setEditable(false);
		
		
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
		edit_panel.add(dobDatePicker2, c);
		
		c.gridx = 0; c.gridy = 14;
		edit_panel.add(anniversary_3, c);
		c.gridx = 1; c.gridy = 14;
		edit_panel.add(annivDatePicker2, c);
		
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
			} else if (dobDatePicker1.getDate() == null){
				Telegraph tele = new Telegraph("Enter Date Of Birth", "Date Of Birth can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				dobDatePicker1.setFocusable(true);
			} else if (annivDatePicker1.getDate() == null){
				Telegraph tele = new Telegraph("Enter Anniversary", "Anniversart Date can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				annivDatePicker1.setFocusable(true);
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
					
					String dobString = standardFormat.format(dobDatePicker1.getDate());
					String annivString = standardFormat.format(annivDatePicker1.getDate()); 
					
					String st = "insert into details (first_name, last_name, addr_1, addr_2, addr_3, area, city, pincode, country, state, phone_num, email, date_of_birth, anniversary, last_updated_at) values("+"'"+cand_first_name_p1.getText()+"', '"+cand_last_name_p1.getText()+"'"+","+"'"+addr_11.getText()+"'"+", '"+addr_21.getText()+"', '"+addr_31.getText()+"', '"+area_1.getText()+"', '"+city_town1.getText()+"' ,"+" '"+pin_code_1.getText()+"', '"+country_t1.getText()+"', '"+state_t1.getText()+"', '"+cand_ph_p1.getText()+"', '"+cand_email_p1.getText()+"', '"+dobString+"', '"+annivString+"', '"+GetCurrentDateTime()+"')";
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
				dobDatePicker1.setDate(null);
				annivDatePicker1.setDate(null);
				//date_of_birth_1.setText("");
				//date_of_anniversary_1.setText("");
				
				
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
			dobDatePicker1.setDate(null);
			annivDatePicker1.setDate(null);
			//date_of_birth_1.setText("");
			//date_of_anniversary_1.setText("");
			
		} 
		
		else if (e.getActionCommand().equals("Refresh")){
			model.setRowCount(0);
			view_tab_data();
		}
		
		else if (e.getSource() == refreshNotify){
			notifyTableModel.setRowCount(0);
			NotifyTableData();
		}
		
		else if (e.getSource() == csvAllData){
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
					String st = "call csvwrite('"+s+"', 'select no,first_name,last_name,addr_1,addr_2,addr_3,area,city,pincode,country,state,phone_num,email,date_of_birth,anniversary from details ')";
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
		
		else if (e.getSource() == pdfAllAddress){
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
		        String st = "select * from details";
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	
		        	
		        	String resultAddress = "No: "+rs.getString(1)+"\n"+rs.getString(2)+" "+rs.getString(3);
		        	
		        	//address line 1 can't be empty so directly append it
		        	resultAddress += "\n"+rs.getString(4);
		        	
		        	//address line 2 can be empty so we must check it before append
		        	if(!StringUtils.isNullOrEmpty(rs.getString(5)))
		        		resultAddress += "\n"+rs.getString(5);
		        	
		        	//address line 3 can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(6)))
		        		resultAddress += "\n"+rs.getString(6);
		        	
		        	//area can also be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(7)))
		        		resultAddress += "\n"+rs.getString(7);
		        	
		        	//city can't be empty anyhow checking for type safety
		        	if(!StringUtils.isNullOrEmpty(rs.getString(8)))
		        		resultAddress += "\n"+rs.getString(8);
		        	
		        	//pin code can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(9)))
		        		resultAddress += "\n"+rs.getString(9);
		        	
		        	//country can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(10)))
		        		resultAddress += "\n"+rs.getString(10);
		        	
		        	//state can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(11)))
		        		resultAddress += "\n"+rs.getString(11);
		        	
		        	//phone number can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(12)))
		        		resultAddress += "\nPh: "+rs.getString(12);
		        	
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
		
		else if (e.getSource() == csvCurrentMonthData){
			int ret = chose.showSaveDialog(this);
			File s;
			int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
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
					String st = "call csvwrite('"+s+"', 'select no,first_name,last_name,addr_1,addr_2,addr_3,area,city,pincode,country,state,phone_num,email,date_of_birth,anniversary from details where EXTRACT(MONTH FROM date_of_birth) = "+currentMonth+"  OR EXTRACT(MONTH FROM anniversary) = "+currentMonth+"')";
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
		
		else if (e.getSource() == pdfCurrentMonthAddress){
			int x = 0, y = 0;
			int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
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
			List<String> pdfDobContent = new ArrayList<String>();
			List<String> pdfAnnivContent = new ArrayList<String>();
			//List<String> allPdfContent = new ArrayList<String>();
			
			try{
				Class.forName("org.h2.Driver");
		        Connection conn = DriverManager.
		            getConnection("jdbc:h2:~/databaseapp", "sa", "");
		        Statement stm = conn.createStatement();
		        String st = "select * from details where EXTRACT(MONTH FROM date_of_birth) = "+currentMonth +" OR EXTRACT(MONTH FROM anniversary) = "+currentMonth;
		        ResultSet rs = stm.executeQuery(st);
		        PdfContentByte canvas = writer.getDirectContentUnder();
		        while(rs.next()){
		        	
		        	
		        	String resultAddress = "No: "+rs.getString(1)+"\n"+rs.getString(2)+" "+rs.getString(3);
		        	
		        	//address line 1 can't be empty so directly append it
		        	resultAddress += "\n"+rs.getString(4);
		        	
		        	//address line 2 can be empty so we must check it before append
		        	if(!StringUtils.isNullOrEmpty(rs.getString(5)))
		        		resultAddress += "\n"+rs.getString(5);
		        	
		        	//address line 3 can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(6)))
		        		resultAddress += "\n"+rs.getString(6);
		        	
		        	//area can also be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(7)))
		        		resultAddress += "\n"+rs.getString(7);
		        	
		        	//city can't be empty anyhow checking for type safety
		        	if(!StringUtils.isNullOrEmpty(rs.getString(8)))
		        		resultAddress += "\n"+rs.getString(8);
		        	
		        	//pin code can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(9)))
		        		resultAddress += "\n"+rs.getString(9);
		        	
		        	//country can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(10)))
		        		resultAddress += "\n"+rs.getString(10);
		        	
		        	//state can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(11)))
		        		resultAddress += "\n"+rs.getString(11);
		        	
		        	//phone number can be empty
		        	if(!StringUtils.isNullOrEmpty(rs.getString(12)))
		        		resultAddress += "\nPh: "+rs.getString(12);
		        	
		        	Calendar dob = Calendar.getInstance();
		        	dob.setTime(rs.getDate(14));
		        	
		        	Calendar anniversary = Calendar.getInstance();
		        	anniversary.setTime(rs.getDate(15));
		        	
		        	if(rs.getDate(14)!= null && dob.get(Calendar.MONTH)+1 == currentMonth){
		        		//resultAddress += "\nDOB: "+rs.getDate(14);
		        		pdfDobContent.add(resultAddress);
		        	}
		        	else if(rs.getDate(15) != null && anniversary.get(Calendar.MONTH)+1 == currentMonth){
		        		//resultAddress += "\nAnniversary: "+rs.getDate(15);
		        		pdfAnnivContent.add(resultAddress);
		        	}
		        	
		        	//Phrase h = new Phrase(resultAddress);
		        	
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
		        	 
		        	//PdfPCell cell = new PdfPCell(h);
		        	 //cell.setFixedHeight(100f);
		        	//table.addCell(cell);
		        	
		        }
		        
		        //allPdfContent.addAll(pdfDobContent);
		        //allPdfContent.addAll(pdfAnnivContent);
		        
		        /*for(int i=0; i<allPdfContent.size(); i++){
		        	Phrase h = new Phrase(allPdfContent.get(i));
		        	PdfPCell cell = new PdfPCell(h);
		        	cell.setFixedHeight(100f);
		        	table.addCell(cell);
		        }*/
		        
		        //table.addCell("");
		        //table.addCell("");
		        //table.addCell("");
			}
			catch(Exception e4){
				System.err.println(e4);
			}
			try {
				Paragraph dobHead = new Paragraph("Based on Date Of Birth");
				dobHead.setAlignment(Element.ALIGN_CENTER);
				doc.add(dobHead);
				doc.add(Chunk.NEWLINE);
				
				for(int i=0; i<pdfDobContent.size(); i++){
					Phrase h = new Phrase(pdfDobContent.get(i));
		        	PdfPCell cell = new PdfPCell(h);
		        	cell.setFixedHeight(100f);
		        	table.addCell(cell);
				}
				table.addCell("");
		        table.addCell("");
		        
				doc.add(table);
				
				doc.newPage();
				
				Paragraph annivHead = new Paragraph("Based on Date Of Birth");
				annivHead.setAlignment(Element.ALIGN_CENTER);
				doc.add(annivHead);
				doc.add(Chunk.NEWLINE);
				
				for(int i=0; i<pdfAnnivContent.size(); i++){
					Phrase h = new Phrase(pdfAnnivContent.get(i));
		        	PdfPCell cell = new PdfPCell(h);
		        	cell.setFixedHeight(100f);
		        	table.addCell(cell);
				}
				table.addCell("");
		        table.addCell("");
		        
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
		
		else if(e.getSource() == clearDb){
			int t = JOptionPane.showConfirmDialog(null, "Are you sure want to clear database ", "Confirm Clear", JOptionPane.YES_NO_OPTION);
			if (t == JOptionPane.YES_OPTION){
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/databaseapp", "sa", "");
			        Statement stm = conn.createStatement();
			        String st = "truncate table details";
			        stm.executeUpdate(st);
			        Telegraph tele = new Telegraph("Success", "Database cleared successfully...", TelegraphType.NOTIFICATION_DONE, WindowPosition.BOTTOMRIGHT, 4000);
					TelegraphQueue quee = new TelegraphQueue();
					quee.add(tele);
			        //JOptionPane.showMessageDialog(null, "Updated Successfully...", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception e1){
					System.err.println(e1);
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
		        		dobDatePicker2.setDate(rs.getDate(14));
		        		annivDatePicker2.setDate(rs.getDate(15));
		        		//date_of_birth_3.setText(rs.getDate(14).toString());
		        		//date_of_anniversary_3.setText(rs.getDate(15).toString());
		        		
		        		
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
		        		dobDatePicker2.setEnabled(true);
		        		//dobDatePicker2.setEditable(true);
		        		annivDatePicker2.setEnabled(true);
		        		//annivDatePicker2.setEditable(true);
		        		
		        		
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
			} else if (dobDatePicker2.getDate() == null){
				Telegraph tele = new Telegraph("Enter Date Of Birth", "Date Of Birth can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				dobDatePicker2.setFocusable(true);
			} else if (annivDatePicker2.getDate() == null){
				Telegraph tele = new Telegraph("Enter Anniversary", "Anniversart Date can't be empty", TelegraphType.NOTIFICATION_WARNING, WindowPosition.BOTTOMRIGHT, 4000);				
				que.add(tele);
				//JOptionPane.showMessageDialog(null, "Address can't be empty...", "Error", JOptionPane.ERROR_MESSAGE);
				annivDatePicker2.setFocusable(true);
			}
				
			 else {
				try{
					Class.forName("org.h2.Driver");
			        Connection conn = DriverManager.
			            getConnection("jdbc:h2:~/databaseapp", "sa", "");
			        Statement stm = conn.createStatement();
			        
			        String dobString = standardFormat.format(dobDatePicker2.getDate());
					String annivString = standardFormat.format(annivDatePicker2.getDate());
			        
			        String st = "update details set first_name = '"+cand_first_name_p3.getText()+"', last_name = '"+cand_last_name_p3.getText()+"', "+"addr_1 = "+"'"+addr_13.getText()+"'"+", addr_2 = '"+addr_23.getText()+"', addr_3 = '"+addr_33.getText()+"', area = '"+area_3.getText()+"', city = '"+city_town3.getText()+"', pincode = '"+pin_code_3.getText()+"', country = '"+country_t3.getText()+"', state = '"+state_t3.getText()+"', phone_num = '"+cand_ph_p3.getText()+"', email = '"+cand_email_p3.getText()+"', date_of_birth = '"+dobString+"', anniversary = '"+annivString+"', last_updated_at = '"+GetCurrentDateTime()+"'  where no = "+num_p3.getText();
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
			        dobDatePicker2.setDate(null);
			        annivDatePicker2.setDate(null);
			        //date_of_birth_3.setText("");
			        //date_of_anniversary_3.setText("");
			        
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
			        dobDatePicker2.setEnabled(false);
			        //dobDatePicker2.setEditable(false);
			        annivDatePicker2.setEnabled(false);
			        //annivDatePicker2.setEditable(false);
			        
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
		        dobDatePicker2.setDate(null);
		        annivDatePicker2.setDate(null);
		        //date_of_birth_3.setText("");
		        //date_of_anniversary_3.setText("");
		        
		        
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
		        dobDatePicker2.setEnabled(false);
		        //dobDatePicker2.setEditable(false);
		        annivDatePicker2.setEnabled(false);
		        //annivDatePicker2.setEditable(false);
		        
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
	        dobDatePicker2.setDate(null);
	        annivDatePicker2.setDate(null);
	        //date_of_birth_3.setText("");
	        //date_of_anniversary_3.setText("");
			
			
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
		     dobDatePicker2.setEnabled(false);
		     //dobDatePicker2.setEditable(false);
		     annivDatePicker2.setEnabled(false);
		     //annivDatePicker2.setEditable(false);
		     
		}
		
		
		

		

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	
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
