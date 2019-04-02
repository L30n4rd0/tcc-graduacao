package control.controlManagerSensors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import control.ControlClientSocket;
import view.InitialView;

public class ControlDataSave implements ActionListener{
	
	private InitialView initialView;
	
	public ControlDataSave(InitialView initialView) {
		super();
		this.initialView = initialView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ArrayList<Object> option = new ArrayList<Object>();
		
		if (this.initialView.getChckBoxDataSave().isSelected())
			option.add(3);
		
		else
			option.add(4);
			
		new ControlClientSocket(this.initialView).sendToServer(option);
		
	}

}
