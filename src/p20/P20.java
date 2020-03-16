package p20;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P20 {

    static String nameFolder;
    static String separator;
    static String pathFolder;
    static String fileName;
    static String[] DocumentsList;
    static String text;

    /**
     * @param args the command line arguments
     */
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        keyboard.useDelimiter("\n");
        int option = -1;

        p1_CreateFolder("Sergi-Monlau");
        do {
            userMenu();
            option = keyboard.nextInt();
            switch (option) {//inicio switch
                case 1:
                    System.out.println("File Name?: ");
                    nameFolder = keyboard.next();
                    p1_CreateFolder(nameFolder);
                    break;
                case 2:
                    System.out.print("File name?: ");
                    String fileName = keyboard.next();
                    System.out.print("Text?: ");
                    String text = keyboard.next();
                    fileName = pathFolder + separator + fileName + ".txt";
                    p2_createFile(fileName, text);

                    break;
                case 3:
                    showFiles();
                    System.out.println("File ?:");
                    int nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    text = readFiles(fileName);
                    System.out.println(text);
                    break;
                case 4:
                    showFiles();
                    System.out.println("File ?:");
                    nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    p4_deleteFiles(fileName);
                    break;
                case 5:
                    showFiles();
                    System.out.println("File ?:");
                    nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    text = p5_modifyFiles(fileName);
                    System.out.print("Text: ");
                    text = keyboard.next();
                    FileWriter fw = new FileWriter(fileName);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(text);
                    bw.flush();
                    bw.close();
                    break;
                case 6:
                    showFiles();
                    System.out.println("File ?:");
                    nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    text = readFiles(fileName);
                    int nChar = p6_characterCounter(text);
                    System.out.println("Number of Char= " + nChar);
                    break;

                case 7:
                    showFiles();
                    System.out.println("File ?:");
                    nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    text = readFiles(fileName);
                    nChar = p7_wordsCounter(text);
                    System.out.println("Number of Char= " + nChar);
                    break;
                case 8:
                    showFiles();
                    System.out.println("File ?:");
                    nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    System.out.println("What word do you want to replace?: ");
                    String replace = keyboard.next();
                    System.out.println("You replace it with:");
                    String replace1 = keyboard.next();
                    p8_Replace(fileName, replace, replace1);
                    break;
                case 9:
                    showFiles();
                    System.out.println("File ?:");
                    nFile = keyboard.nextInt();
                    fileName = DocumentsList[nFile - 1];
                    fileName = pathFolder + separator + fileName;
                    p9_Print(fileName);
                    break;
                case 10:
                    showFiles();
                    System.out.println("Quieres salir? Y/N");
                    String res = keyboard.next();
                    if (res.equals("Y")) {
                        System.out.println("Byee");
                    } else {
                        option = 90;
                        System.out.println("Continue: ");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
            }//fin switch
        } while (option != 10);
    }

    private static void p0() {
        System.out.println("");
        System.out.println("gracias ......     adeu");
    }

    private static void userMenu() {
        System.out.println();
        System.out.println("Opción 1- (Create a folder)");
        System.out.println("Opción 2- (Create a file)");
        System.out.println("Opción 3-(View a file)");
        System.out.println("Opción 4-(Delete a file)");
        System.out.println("Opción 5- (Modify a file)");
        System.out.println("Opción 6- (--):");
        System.out.println("Opción 7- (--):");
        System.out.println("Opción 8- (--)):");
        System.out.println("Opción 9- (--):");
        System.out.println("Opción 10- (--):");
        System.out.print("\nOpcion ?: ");
    }

    private static void p1_CreateFolder(String nameFolder) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        separator = File.separator;
        pathFolder = path + separator + nameFolder;
        System.out.println(pathFolder);
        File folder = new File(pathFolder);//poner en formato file
        System.out.println(folder);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private static void p2_createFile(String fileName, String text) throws IOException {
        //1- abrir ficheor
        //fileName = pathFolder + separator + fileName + ".txt";
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        //2- escribir en el fichero
        bw.write(text);
        //3- cerrar fichero
        bw.flush();//borrar memoria
        bw.close();//cerrar el fichero y la conexion
    }

    private static void showFiles() {
        File folder = new File(pathFolder);
        DocumentsList = folder.list();
        for (int i = 0; i < DocumentsList.length; i++) {
            System.out.println((i + 1) + " - " + DocumentsList[i]);
        }
    }

    private static String readFiles(String fileName) throws FileNotFoundException, IOException {
        String text = "";
        String line;
        //1- abrir ficheor        
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        //2- leer el fichero
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            text = line;
        }
        //3- cerrar fichero
        br.close();
        return text;
    }

    private static void p4_deleteFiles(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    private static String p5_modifyFiles(String fileName) throws IOException {
        String text = "";
        String line;
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            text += line;
        }
        br.close();
        return text;
    }

    private static int p6_characterCounter(String txt) {
        return txt.length();
    }

    private static int p7_wordsCounter(String fileName) {
        StringTokenizer st = new StringTokenizer(fileName);       
        return st.countTokens();

    }

    private static String p8_Replace(String fileName, String replace, String replace1) {
        return fileName.replaceAll(replace, replace1);
    }

    private static void p9_Print(String fileName) throws IOException {
        File fileToPrint = new File(fileName);
        Desktop.getDesktop().print(fileToPrint);
    }
}
