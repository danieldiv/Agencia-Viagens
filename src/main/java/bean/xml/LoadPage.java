/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.xml;

import java.io.*;
import java.net.URL;

public class LoadPage {

    public void getPage(URL url, File file) throws IOException {
        BufferedWriter out;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            out = new BufferedWriter(new FileWriter(file));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                // Imprime a pagina no console
                //System.out.println(inputLine);

                // Grava pagina no arquivo
                out.write(inputLine);
                out.newLine();
            }
        }
        out.flush();
        out.close();
    }
}
