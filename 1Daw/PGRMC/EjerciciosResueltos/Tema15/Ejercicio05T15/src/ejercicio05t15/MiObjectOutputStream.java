/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio05t15;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Quique Pineda
 */
public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }
    
    public MiObjectOutputStream(OutputStream out) throws IOException, SecurityException {
        super(out);
    }
    
    @Override
    protected void writeStreamHeader() throws IOException{
        //No hago nada
    }
    
}
