package control.controlManagerSensors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import control.ControlClientSocket;
import view.InitialView;

public class ControlStopSendStatus implements ActionListener{
	
	private InitialView initialView;
	
	public ControlStopSendStatus(InitialView initialView) {
		super();
		this.initialView = initialView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.initialView.getStatusView().setVisible(false);
		this.initialView.getStatusView().dispose();
		
		ArrayList<Object> option = new ArrayList<Object>();
		option.add(6);
		
		new ControlClientSocket(this.initialView).sendToServer(option);
		
	}

}
