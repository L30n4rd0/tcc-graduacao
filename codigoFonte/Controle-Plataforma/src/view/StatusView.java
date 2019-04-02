package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import control.ControlServerSocketCargo;
import control.ControlServerSocketController;
import control.ControlStatus;

public class StatusView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public ArrayList<Float> angles_smart_controller, angles_smart_cargo;
	private JCheckBox chckbx_showAngleYCargo;
	private JCheckBox chckbx_showAngleXCargo;
	private JCheckBox chckbx_showAngleXController;
	private JCheckBox chckbx_showAngleYController;

	public StatusView() {
		setTitle("Status Plataforma");
		setSize(960, 700);
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		this.angles_smart_cargo = new ArrayList<Float>();
		this.angles_smart_cargo.add(0f);
		this.angles_smart_cargo.add(0f);
		
		this.angles_smart_controller = new ArrayList<Float>();
		this.angles_smart_controller.add(0f);
		this.angles_smart_controller.add(0f);
		
		int x_labelsLeft = 9;
		int x_labelsRight = 931;
		
//		JLabels left
		JLabel label_40 = new JLabel("40");
		label_40.setBounds(x_labelsLeft, 82, 16, 15);
		getContentPane().add(label_40);
		
		JLabel label_35 = new JLabel("35");
		label_35.setBounds(x_labelsLeft, 114, 16, 15);
		getContentPane().add(label_35);
		
		JLabel label_30 = new JLabel("30");
		label_30.setBounds(x_labelsLeft, 147, 16, 15);
		getContentPane().add(label_30);
		
		JLabel label_25 = new JLabel("25");
		label_25.setBounds(x_labelsLeft, 179, 16, 15);
		getContentPane().add(label_25);
		
		JLabel label_20 = new JLabel("20");
		label_20.setBounds(x_labelsLeft, 211, 16, 15);
		getContentPane().add(label_20);
		
		JLabel label_15 = new JLabel("15");
		label_15.setBounds(x_labelsLeft, 243, 16, 15);
		getContentPane().add(label_15);
		
		JLabel label_10 = new JLabel("10");
		label_10.setBounds(x_labelsLeft, 275, 16, 15);
		getContentPane().add(label_10);
		
		JLabel label_5 = new JLabel(" 5");
		label_5.setBounds(x_labelsLeft, 306, 16, 15);
		getContentPane().add(label_5);
		
//		JLabel label_0 = new JLabel(" 0");
//		label_0.setBounds(x_labelsLeft, 341, 16, 15);
//		getContentPane().add(label_0);
		
		JLabel label_5n = new JLabel("-5");
		label_5n.setBounds(x_labelsLeft, 373, 16, 15);
		getContentPane().add(label_5n);
		
		JLabel label_10n = new JLabel("-10");
		label_10n.setBounds(x_labelsLeft, 406, 21, 15);
		getContentPane().add(label_10n);
		
		JLabel label_15n = new JLabel("-15");
		label_15n.setBounds(x_labelsLeft, 438, 21, 15);
		getContentPane().add(label_15n);
		
		JLabel label_20n = new JLabel("-20");
		label_20n.setBounds(x_labelsLeft, 472, 21, 15);
		getContentPane().add(label_20n);
		
		JLabel label_25n = new JLabel("-25");
		label_25n.setBounds(x_labelsLeft, 503, 21, 15);
		getContentPane().add(label_25n);
		
		JLabel label_30n = new JLabel("-30");
		label_30n.setBounds(x_labelsLeft, 536, 21, 15);
		getContentPane().add(label_30n);
		
		JLabel label_35n = new JLabel("-35");
		label_35n.setBounds(x_labelsLeft, 569, 21, 15);
		getContentPane().add(label_35n);
		
		JLabel label_40n = new JLabel("-40");
		label_40n.setBounds(x_labelsLeft, 601, 21, 15);
		getContentPane().add(label_40n);
		
//		JLabels right
		JLabel label_40_right = new JLabel("40");
		label_40_right.setBounds(x_labelsRight, 82, 16, 15);
		getContentPane().add(label_40_right);
		
		JLabel label_35_right = new JLabel("35");
		label_35_right.setBounds(x_labelsRight, 114, 16, 15);
		getContentPane().add(label_35_right);
		
		JLabel label_30_right = new JLabel("30");
		label_30_right.setBounds(x_labelsRight, 147, 16, 15);
		getContentPane().add(label_30_right);
		
		JLabel label_25_right = new JLabel("25");
		label_25_right.setBounds(x_labelsRight, 179, 16, 15);
		getContentPane().add(label_25_right);
		
		JLabel label_20_right = new JLabel("20");
		label_20_right.setBounds(x_labelsRight, 211, 16, 15);
		getContentPane().add(label_20_right);
		
		JLabel label_15_right = new JLabel("15");
		label_15_right.setBounds(x_labelsRight, 243, 16, 15);
		getContentPane().add(label_15_right);
		
		JLabel label_10_right = new JLabel("10");
		label_10_right.setBounds(x_labelsRight, 275, 16, 15);
		getContentPane().add(label_10_right);
		
		JLabel label_5_right = new JLabel(" 5");
		label_5_right.setBounds(x_labelsRight, 306, 16, 15);
		getContentPane().add(label_5_right);
		
//		JLabel label_0_right = new JLabel(" 0");
//		label_0_right.setBounds(x_labelsRight, 341, 16, 15);
//		getContentPane().add(label_0_right);
		
		JLabel label_5n_right = new JLabel("-5");
		label_5n_right.setBounds(x_labelsRight, 373, 16, 15);
		getContentPane().add(label_5n_right);
		
		JLabel label_10n_right = new JLabel("-10");
		label_10n_right.setBounds(x_labelsRight, 406, 21, 15);
		getContentPane().add(label_10n_right);
		
		JLabel label_15n_right = new JLabel("-15");
		label_15n_right.setBounds(x_labelsRight, 438, 21, 15);
		getContentPane().add(label_15n_right);
		
		JLabel label_20n_right = new JLabel("-20");
		label_20n_right.setBounds(x_labelsRight, 472, 21, 15);
		getContentPane().add(label_20n_right);
		
		JLabel label_25n_right = new JLabel("-25");
		label_25n_right.setBounds(x_labelsRight, 503, 21, 15);
		getContentPane().add(label_25n_right);
		
		JLabel label_30n_right = new JLabel("-30");
		label_30n_right.setBounds(x_labelsRight, 536, 21, 15);
		getContentPane().add(label_30n_right);
		
		JLabel label_35n_right = new JLabel("-35");
		label_35n_right.setBounds(x_labelsRight, 569, 21, 15);
		getContentPane().add(label_35n_right);
		
		JLabel label_40n_right = new JLabel("-40");
		label_40n_right.setBounds(x_labelsRight, 601, 21, 15);
		getContentPane().add(label_40n_right);
		
		JLabel lblNewLabel = new JLabel("Legenda");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(139, 23, 75, 19);
		getContentPane().add(lblNewLabel);
		
		chckbx_showAngleXController = new JCheckBox("Exibir \u00E2ngulo X do aferidor");
		chckbx_showAngleXController.setSelected(true);
		chckbx_showAngleXController.setBounds(228, 8, 186, 18);
		getContentPane().add(chckbx_showAngleXController);
		
		chckbx_showAngleYController = new JCheckBox("Exibir \u00E2ngulo Y do aferidor");
		chckbx_showAngleYController.setSelected(true);
		chckbx_showAngleYController.setBounds(228, 35, 186, 18);
		getContentPane().add(chckbx_showAngleYController);
		
		JLabel lblNewLabel_1 = new JLabel("________");
		lblNewLabel_1.setBounds(423, 7, 48, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("________");
		label.setForeground(Color.GREEN);
		label.setBounds(423, 30, 48, 15);
		getContentPane().add(label);
		
		chckbx_showAngleXCargo = new JCheckBox("Exibir \u00E2ngulo X da carga");
		chckbx_showAngleXCargo.setSelected(true);
		chckbx_showAngleXCargo.setBounds(524, 9, 173, 18);
		getContentPane().add(chckbx_showAngleXCargo);
		
		chckbx_showAngleYCargo = new JCheckBox("Exibir \u00E2ngulo Y da carga");
		chckbx_showAngleYCargo.setSelected(true);
		chckbx_showAngleYCargo.setBounds(524, 36, 173, 18);
		getContentPane().add(chckbx_showAngleYCargo);
		
		JLabel label_1 = new JLabel("________");
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(706, 8, 48, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("________");
		label_2.setForeground(Color.RED);
		label_2.setBounds(706, 31, 48, 15);
		getContentPane().add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 0, 946, 60);
		getContentPane().add(panel);
		
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		// The canvas
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		ControlStatus controlEventCube = new ControlStatus(this);
		glcanvas.addGLEventListener(controlEventCube);
		glcanvas.setSize(960, 700);
		glcanvas.setLocation(0, 0);
		getContentPane().add(glcanvas);
		
		setVisible(true);
		
		new ControlServerSocketController(this);
		new ControlServerSocketCargo(this);

		final FPSAnimator animator = new FPSAnimator(glcanvas, 16, true);
		animator.start();
		
	}

	public JCheckBox getChckbx_showAngleYCargo() {
		return chckbx_showAngleYCargo;
	}

	public JCheckBox getChckbx_showAngleXCargo() {
		return chckbx_showAngleXCargo;
	}

	public JCheckBox getChckbx_showAngleXController() {
		return chckbx_showAngleXController;
	}

	public JCheckBox getChckbx_showAngleYController() {
		return chckbx_showAngleYController;
	}
	
}
