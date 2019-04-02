package control;

import java.util.ArrayList;

import model.AnglesVO;
import view.StatusView;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class ControlStatus implements GLEventListener {

//	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	private float x_interval;
	private StatusView statusView;
	private ArrayList<AnglesVO>
	anglesSmartController,
	anglesSmartCargo;
	int maxNamberAngles, drawingStartPoint;

	public ControlStatus(StatusView statusView) {
		super();
		this.statusView = statusView;
		this.anglesSmartController = new ArrayList<AnglesVO>();
		this.anglesSmartController.add(new AnglesVO(0f, 0f));
		
		this.anglesSmartCargo = new ArrayList<AnglesVO>();
		this.anglesSmartCargo.add(new AnglesVO(0f, 0f));
		
		this.x_interval = 0.8f;
		this.maxNamberAngles = 158;
		this.drawingStartPoint = 128;
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {

		if ( !(this.statusView.angles_smart_controller.get(0) == 0 && this.statusView.angles_smart_controller.get(1) == 0) ) {

			if (this.anglesSmartController.size() == this.maxNamberAngles)
				this.anglesSmartController.remove(0);
			
			this.anglesSmartController.add(new AnglesVO( this.statusView.angles_smart_controller.get(0), this.statusView.angles_smart_controller.get(1)));
		}
		
		if ( !(this.statusView.angles_smart_cargo.get(0) == 0 && this.statusView.angles_smart_cargo.get(1) == 0) ) {

			if (this.anglesSmartCargo.size() == this.maxNamberAngles)
				this.anglesSmartCargo.remove(0);

			this.anglesSmartCargo.add(new AnglesVO( this.statusView.angles_smart_cargo.get(0), this.statusView.angles_smart_cargo.get(1)));

		}

		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glLoadIdentity();
		gl.glTranslatef(-67f, 0f, -130f);

		// float scale = 3f;
		// gl.glScalef(scale, scale, scale);

		float axis_size_x = 160f, axis_size_y = 48f;

		// Eixo X - Preto
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0f, 0f, 0f);
		gl.glVertex2f(-axis_size_x, 0f);
		gl.glVertex2f(axis_size_x, 0f);
		gl.glEnd();

		// Eixo Y - Preto
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0f, 0f, 0f);
		gl.glVertex2f(0f, -axis_size_y);
		gl.glVertex2f(0f, axis_size_y);
		gl.glEnd();

		// Desenhar linhas paralelas ao eixo X
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0.66f, 0.66f, 0.66f);
		for (float i = -40f; i <= 40; i += 5) {
			if (i != 0) { // para não pintar o eixo X
				gl.glVertex2f(-1f, i);
				gl.glVertex2f(135f, i);

			}

		}
		gl.glEnd();

		float x_desloc; //deslocamento no eixo X
		int array_position; //percorre o arrayList
		
		if (this.statusView.getChckbx_showAngleXController().isSelected()) {
			// Desenhar eixo X do smartphone controle
			gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glColor3f(0f, 0f, 0f);
			for (x_desloc = this.drawingStartPoint, array_position = this.anglesSmartController.size() - 1; array_position >=0; x_desloc -= this.x_interval, array_position--)
				gl.glVertex2f(x_desloc, this.anglesSmartController.get(array_position).getXangle());
			
			gl.glEnd();
			
		}
		
		if (this.statusView.getChckbx_showAngleYController().isSelected()) {
			// Desenhar eixo Y do smartphone controle
			gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glColor3f(0f, 1f, 0f);
			for (x_desloc = this.drawingStartPoint, array_position = this.anglesSmartController.size() - 1; array_position >=0; x_desloc -= this.x_interval, array_position--)
				gl.glVertex2f(x_desloc, this.anglesSmartController.get(array_position).getYangle());
			
			gl.glEnd();
			
		}
		
		if (this.statusView.getChckbx_showAngleXCargo().isSelected()) {
			// Desenhar eixo X do smartphone carga
			gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glColor3f(0f, 0f, 1f);
			
			for (x_desloc = this.drawingStartPoint, array_position = this.anglesSmartCargo.size() - 1; array_position >=0; x_desloc -= this.x_interval, array_position--)
				gl.glVertex2f(x_desloc, this.anglesSmartCargo.get(array_position).getXangle());
			
			gl.glEnd();
			
		}
		
		if (this.statusView.getChckbx_showAngleYCargo().isSelected()) {
			// Desenhar eixo Y do smartphone carga
			gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glColor3f(1f, 0f, 0f);
			for (x_desloc = this.drawingStartPoint, array_position = this.anglesSmartCargo.size() - 1; array_position >=0; x_desloc -= this.x_interval, array_position--)
				gl.glVertex2f(x_desloc, this.anglesSmartCargo.get(array_position).getYangle());
			
			gl.glEnd();
			
		}
		

		gl.glFlush();

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// System.out.println("dispose");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// System.out.println("init");
		final GL2 gl = drawable.getGL().getGL2();

		gl.glShadeModel(GL2.GL_SMOOTH); // define o tipo de sombra
		gl.glClearColor(1f, 1f, 1f, 1f); // define a cor do fundo
		gl.glClearDepth(1.0f); // nao sei
		gl.glEnable(GL2.GL_DEPTH_TEST); // nao sei
		gl.glDepthFunc(GL2.GL_LEQUAL); // nao sei
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {

		final GL2 gl = drawable.getGL().getGL2();

		if (height <= 0)
			height = 1;

		final float h = (float) width / (float) height;

		gl.glViewport(0, 0, width, height); // define a area q sera usada para o
											// desenho
		gl.glMatrixMode(GL2.GL_PROJECTION); // define o modo de matriz utilizada
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 1.0f, 200.0f); // define a perspectiva, p1:
													// angulo de visão; p2:
													// aspecto; p3: visao mais
													// perto; p4: visao mais
													// longe
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

}