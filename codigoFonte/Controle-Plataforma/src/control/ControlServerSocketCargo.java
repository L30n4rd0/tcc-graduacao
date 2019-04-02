package control;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import view.StatusView;

public class ControlServerSocketCargo implements Runnable{
	
	private StatusView statusView;
	private Thread thread;
	
	public ControlServerSocketCargo(StatusView statusView) {
		super();
		this.statusView = statusView;
		this.thread = new Thread(this);
		this.thread.start();
		
	}
	
	@Override
	public void run() {
		
		try {
			java.net.ServerSocket serverSocket;
			Socket socket;
			
			while (this.statusView.isVisible()) {
				serverSocket = new java.net.ServerSocket(9999);
				
				socket = serverSocket.accept();
				
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				
				@SuppressWarnings("unchecked")
				ArrayList<Float> arrayListAngles = (ArrayList<Float>) objectInputStream.readObject();
				this.statusView.angles_smart_cargo = arrayListAngles;
				
				objectOutputStream.flush();
				objectInputStream.close();
				objectOutputStream.close();
				socket.close();
				serverSocket.close();
				
			}
			
			this.thread.interrupt();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
}
