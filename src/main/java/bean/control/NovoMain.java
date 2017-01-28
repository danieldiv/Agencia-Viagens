/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.control;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
        
        System.out.println("antes: " + cal.getTime());
        
        cal.add(Calendar.DAY_OF_MONTH, -1);
        
        Date data = new Date();
        
        System.out.println("teste: " + cal.getTime());
        
        data.setTime(0);
        
        System.out.println("depois: " + cal.getTime());
    }
    
}
