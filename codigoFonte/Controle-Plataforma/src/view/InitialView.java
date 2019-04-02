package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.controlManagerSensors.ControlDataSave;
import control.controlManagerSensors.ControlStartCaptureSensors;
import control.controlManagerSensors.ControlStartSendStatus;
import control.controlManagerSensors.ControlStopCaptureSensors;
import control.controlManagerSensors.ControlStopSendStatus;
import control.directionControl.ControlDirectionBack;
import control.directionControl.ControlDirectionFront;
import control.directionControl.ControlDirectionKeyListener;
import control.directionControl.ControlDirectionLeft;
import control.directionControl.ControlDirectionRight;
import control.directionControl.ControlDirectionStop;

public class InitialView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField_IP;
	private JCheckBox chckBoxDataSave;
	private StatusView statusView;
	private JLabel lbl_IP_local;
	
	public InitialView() {
		getContentPane().setBackground(new Color(240, 248, 255));
		
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		setSize(500, 569);
		setTitle("Controle Plataforma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(770, 100);
		getContentPane().setLayout(null);
		
		JLabel lbl_IP_smartphone = new JLabel("IP do Smartphone");
		lbl_IP_smartphone.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_IP_smartphone.setBounds(36, 15, 140, 17);
		getContentPane().add(lbl_IP_smartphone);
		
		textField_IP = new JTextField();
		textField_IP.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_IP.setText("192.168.43.1");
		textField_IP.setBounds(181, 12, 125, 24);
		getContentPane().add(textField_IP);
		textField_IP.setColumns(10);
		
		JLabel lblIP = new JLabel("IP local para status");
		lblIP.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIP.setBounds(27, 44, 149, 17);
		getContentPane().add(lblIP);
		
		lbl_IP_local = new JLabel(getIp());
		lbl_IP_local.setFont(new Font("Dialog", Font.BOLD, 13));
		lbl_IP_local.setBounds(181, 45, 125, 15);
		getContentPane().add(lbl_IP_local);
		
		JButton btnStartCapture = new JButton("Iniciar captura de sensores");
		btnStartCapture.setBackground(new Color(70, 130, 180));
		btnStartCapture.setBounds(37, 126, 228, 35);
		btnStartCapture.addActionListener(new ControlStartCaptureSensors(this));
		getContentPane().add(btnStartCapture);
		
		JButton btnStopCapture = new JButton("Parar captura de sensores");
		btnStopCapture.setBackground(new Color(205, 92, 92));
		btnStopCapture.setBounds(37, 173, 228, 35);
		btnStopCapture.addActionListener(new ControlStopCaptureSensors(this));
		getContentPane().add(btnStopCapture);
		
		JButton btnShowStatus = new JButton("Exibir Status");
		btnShowStatus.setBackground(new Color(70, 130, 180));
		btnShowStatus.setBounds(295, 126, 170, 35);
		btnShowStatus.addActionListener(new ControlStartSendStatus(this));
		getContentPane().add(btnShowStatus);
		
		JButton btnNewButton = new JButton("Fechar Status");
		btnNewButton.setBackground(new Color(205, 92, 92));
		btnNewButton.setBounds(295, 173, 170, 35);
		btnNewButton.addActionListener(new ControlStopSendStatus(this));
		getContentPane().add(btnNewButton);
		
		final JButton btnFront = new JButton("Frente");
		btnFront.setBounds(189, 286, 117, 45);
		btnFront.addActionListener(new ControlDirectionFront(this));
		getContentPane().add(btnFront);
		
		final JButton btnRight = new JButton("Direita");
		btnRight.setBounds(326, 361, 117, 45);
		btnRight.addActionListener(new ControlDirectionRight(this));
		getContentPane().add(btnRight);
		
		final JButton btnBack = new JButton("Atr\u00E1s");
		btnBack.setBounds(189, 436, 117, 45);
		btnBack.addActionListener(new ControlDirectionBack(this));
		getContentPane().add(btnBack);
		
		final JButton btnLeft = new JButton("Esquerda");
		btnLeft.setBounds(52, 361, 117, 45);
		btnLeft.addActionListener(new ControlDirectionLeft(this));
		getContentPane().add(btnLeft);
				
		final JButton btnStop = new JButton("Parar");
		btnStop.setBounds(189, 361, 117, 45);
		btnStop.addActionListener(new ControlDirectionStop(this));
		getContentPane().add(btnStop);
		
		chckBoxDataSave = new JCheckBox("Salvar Dados em Arquivo no Smartphone");
		chckBoxDataSave.setBounds(27, 98, 313, 23);
		chckBoxDataSave.addActionListener(new ControlDataSave(this));
		getContentPane().add(chckBoxDataSave);
		
		final JCheckBox chckbxUseKeysDirections = new JCheckBox("Usar teclas direcionais do teclado");
		chckbxUseKeysDirections.setBounds(141, 256, 266, 23);
		getContentPane().add(chckbxUseKeysDirections);
		
		chckbxUseKeysDirections.addKeyListener(new ControlDirectionKeyListener(this));
		chckbxUseKeysDirections.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxUseKeysDirections.isSelected()) {
					btnFront.setEnabled(false);
					btnRight.setEnabled(false);
					btnBack.setEnabled(false);
					btnLeft.setEnabled(false);
					btnStop.setEnabled(false);
					
				} else {
					btnFront.setEnabled(true);
					btnRight.setEnabled(true);
					btnBack.setEnabled(true);
					btnLeft.setEnabled(true);
					btnStop.setEnabled(true);
				}
				
			}
		});
		
		setVisible(true);
		
	}

	public StatusView getStatusView() {
		return statusView;
	}

	public void setStatusView(StatusView statusView) {
		this.statusView = statusView;
	}

	public JTextField getTextField_IP() {
		return textField_IP;
	}

	public JCheckBox getChckBoxDataSave() {
		return chckBoxDataSave;
	}
	
	public JLabel getLbl_IP_local() {
		return lbl_IP_local;
	}

	public String getIp() {
        String ipAddress = null;
        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();

                if (ip.isSiteLocalAddress()) {
                    ipAddress = ip.getHostAddress();
                }
            }
        }
        return ipAddress;
    }
	
	public static void main(String[] args) {
		new InitialView();
	}
}