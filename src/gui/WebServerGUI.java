package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import WebServer.ServerStatus;
import WebServer.WebServer;
import WebServer.WebServerException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class WebServerGUI {

	private JFrame frame;
	private JTextField txtCusersmaradesktoptestsite;
	private JTextField textField_port;
	private JTextField txtCusersmaradesktoptestsitemaintenancehtml;
	private WebServer ws;
	
//	try {
//		ws = new WebServer(getRootFolder(), getPort());
//        ws.setServerStatus(ServerStatus.valueOf(getServerStatus()));
//        ws.start();
//        
//	} catch (WebServerException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebServerGUI window = new WebServerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WebServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.setBackground(Color.GREEN);
		btnStartServer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnStartServer.setBounds(374, 330, 115, 27);
		frame.getContentPane().add(btnStartServer);
		
		txtCusersmaradesktoptestsite = new JTextField();
		txtCusersmaradesktoptestsite.setText("C:\\Users\\mara\\Desktop\\TestSite");
		txtCusersmaradesktoptestsite.setBounds(10, 231, 278, 19);
		frame.getContentPane().add(txtCusersmaradesktoptestsite);
		txtCusersmaradesktoptestsite.setColumns(10);
		
		textField_port = new JTextField();
		textField_port.setText("8080");
		textField_port.setBounds(10, 190, 96, 19);
		frame.getContentPane().add(textField_port);
		textField_port.setColumns(10);
		
		JLabel lblPortNumber = new JLabel("Port number:");
		lblPortNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPortNumber.setBounds(10, 166, 90, 27);
		frame.getContentPane().add(lblPortNumber);
		
		JLabel lblRootDirectory = new JLabel("Root directory:");
		lblRootDirectory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRootDirectory.setBounds(10, 209, 96, 13);
		frame.getContentPane().add(lblRootDirectory);
		
		JLabel lblMaintenanceDirectory = new JLabel("Maintenance directory:");
		lblMaintenanceDirectory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaintenanceDirectory.setBounds(10, 260, 149, 13);
		frame.getContentPane().add(lblMaintenanceDirectory);
		
		txtCusersmaradesktoptestsitemaintenancehtml = new JTextField();
		txtCusersmaradesktoptestsitemaintenancehtml.setText("C:\\Users\\mara\\Desktop\\TestSite\\maintenance.html");
		txtCusersmaradesktoptestsitemaintenancehtml.setBounds(10, 283, 278, 19);
		frame.getContentPane().add(txtCusersmaradesktoptestsitemaintenancehtml);
		txtCusersmaradesktoptestsitemaintenancehtml.setColumns(10);
		
		JButton btnMaintenance = new JButton("Maintenance");
		btnMaintenance.setBackground(Color.ORANGE);
		btnMaintenance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMaintenance.setBounds(193, 331, 115, 26);
		frame.getContentPane().add(btnMaintenance);
		
		JButton btnStopServer = new JButton("Stop Server");
		btnStopServer.setBackground(Color.RED);
		btnStopServer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnStopServer.setBounds(30, 329, 96, 28);
		frame.getContentPane().add(btnStopServer);
		
		JLabel lblServerStatus = new JLabel("Server Status:");
		lblServerStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServerStatus.setBounds(10, 36, 83, 19);
		frame.getContentPane().add(lblServerStatus);
		
		JLabel lblServerAddress = new JLabel("Server address:");
		lblServerAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServerAddress.setBounds(10, 65, 96, 27);
		frame.getContentPane().add(lblServerAddress);
		
		JLabel lblServerListeningPort = new JLabel("Server listening port:");
		lblServerListeningPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServerListeningPort.setBounds(10, 101, 126, 19);
		frame.getContentPane().add(lblServerListeningPort);
		
		JLabel lblShowServerStatus = new JLabel("STOPPED");
		lblShowServerStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShowServerStatus.setBounds(159, 40, 149, 13);
		frame.getContentPane().add(lblShowServerStatus);
		
		JLabel lblShowServerAdreess = new JLabel("STOPPED");
		lblShowServerAdreess.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShowServerAdreess.setBounds(159, 73, 348, 13);
		frame.getContentPane().add(lblShowServerAdreess);
		
		JLabel lblShowServerPort = new JLabel("STOPPED");
		lblShowServerPort.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShowServerPort.setBounds(159, 105, 96, 13);
		frame.getContentPane().add(lblShowServerPort);
		
		try {
			ws = new WebServer(txtCusersmaradesktoptestsite.getText(), Integer.parseInt(textField_port.getText()));
//			ws.setServerStatus(ServerStatus.valueOf("STOPPED"));
//			ws.start();
		} catch (WebServerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblShowServerStatus.setText("STOPPED");
				txtCusersmaradesktoptestsite.setEditable(true);
				txtCusersmaradesktoptestsitemaintenancehtml.setEditable(true);
				lblShowServerAdreess.setText("STOPPED");
				lblShowServerPort.setText("STOPPED");
				ws.setServerStatus(ServerStatus.valueOf("STOPPED"));
				ws.stop();
			}
		});
		
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = null;
				try {
					ip = "" + InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblShowServerStatus.setText("MAINTENANCE");
				txtCusersmaradesktoptestsite.setEditable(true);
				txtCusersmaradesktoptestsitemaintenancehtml.setEditable(false);
				lblShowServerAdreess.setText(ip);
				lblShowServerPort.setText(textField_port.getText());
				ws.setServerStatus(ServerStatus.valueOf("MAINTENANCE"));
			}
		});
		
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = null;
				try {
					ip = "" + InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblShowServerStatus.setText("RUNNING");
				txtCusersmaradesktoptestsite.setEditable(false);
				txtCusersmaradesktoptestsitemaintenancehtml.setEditable(true);
				lblShowServerAdreess.setText(ip);
				lblShowServerPort.setText(textField_port.getText());
				ws.setServerStatus(ServerStatus.valueOf("RUNNING"));
//				try {
//					ws.start();
//					
//				} catch (WebServerException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
	}
}
