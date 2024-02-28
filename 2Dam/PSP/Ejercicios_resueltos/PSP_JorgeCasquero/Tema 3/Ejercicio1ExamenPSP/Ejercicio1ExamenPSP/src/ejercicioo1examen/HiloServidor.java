package ejercicioo1examen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HiloServidor extends Thread{
	ObjectInputStream fentrada;
	DataOutputStream fsalida;
	Socket  cliente =null;
	public HiloServidor(Socket cliente) {
		super();
		this.cliente = cliente;
		try {
			fentrada=new ObjectInputStream(cliente.getInputStream());
			fsalida = new DataOutputStream(cliente.getOutputStream());
		}catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public String comprobarFechaITV(Coche  coche) {
		String mensaje ="";
		LocalDate hoy = LocalDate.now();
		int anio_matric = coche.getAnio_matric();
		int fecha_ultimaITV = coche.getFecha_ul_ITV();
		
		int anios = hoy.getYear() - anio_matric;
		System.out.println("Años: "+anios);
		
		if(anios<4) {
			mensaje = "=> El coche "+coche.getMatricula()+ " esta exento de pagar la itv";
			
		}
		if(fecha_ultimaITV !=-1) {
			if(anios>=4 && anios <=10) {
				mensaje = "=> El coche "+coche.getMatricula()+"tiene que pasar la itv en el "+(fecha_ultimaITV+2);
			}else {
				mensaje = "=> El coche "+coche.getMatricula()+"tiene que pasar la itv en el "+(fecha_ultimaITV+1);
			}
		}else if(anios>=4) {
			mensaje = "=> El coche tiene maas de 4 años y no hay datos sobre la ultima itv";
		}
		return mensaje;
	}
	
	@Override
	public void run() {
		//tarea a realizar coon cada cliente
		try {
			Coche coche = (Coche) fentrada.readObject();
			System.out.println("[Servidor] Consultar coche: "+coche.toString());
			fsalida.writeUTF(comprobarFechaITV(coche));
			fentrada.close();
			fsalida.close();
			cliente.close();
		}catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	

}
