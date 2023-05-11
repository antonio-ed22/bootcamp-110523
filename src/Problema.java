import java.io.*;
import java.util.Scanner;

public class Problema {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader("mensaje.txt"));
            bw = new BufferedWriter(new FileWriter("mensaje_cifrado.txt"));

            String linea = null;

            /* Lectura y validación  de clave */
            String key;
            int keypos = 0;
            while (true) {
                System.out.print("Introduce la clave >> ");
                key = sc.nextLine();
                if(key.matches("[a-zA-Z]{1,12}")){
                    break;
                }
                System.out.println("Clave no valida, vuelve a intentarlo");
            }

            key = key.toLowerCase();

            while ((linea = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder(linea.length());

                linea = linea.toLowerCase();

                /* Aquí vendría la lógica del programa */
                for (int i = 0; i < linea.length(); i++) {
                    char cm = linea.charAt(i);
                    if(cm < 'a' || cm > 'z') {
                        sb.append(cm);
                        continue;
                    }
                    char ck = key.charAt(keypos);


                    char r = (char)((((int)cm-'a')+((int)ck)-'a') % 26+'a');
                    sb.append(r);

                    keypos++;
                    if(keypos == key.length()) keypos = 0;
                }



                bw.write(sb.toString().toUpperCase()); /* Escribe la cadena de caracteres en el fichero*/
                bw.newLine(); /* escribe nueva línea en el fichero */

            }
            System.out.println("El mensaje ha sido cifrado correctamente");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

}


