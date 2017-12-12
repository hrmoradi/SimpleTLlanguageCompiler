import java.io.*;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Admin on 3/22/2017.
 */
public class CompilerMain {
    public static void main(String[] args) throws IOException{


String add = System.getProperty("user.dir");
String outputAddress =add+ "/"+args[0]+".tok";
File f = new File(outputAddress);
if(f.exists()){
    System.out.println("\ntok file::  Exists we will del");
    f.delete();
}

	System.out.println("*****************************************");
	System.out.println("*****************************************");
	System.out.println("***************Scanner Start*************");
        ScannerMainClass.main(args);
	System.out.println("*****************************************");
	System.out.println("*****************************************");
	System.out.println("***************Scanner END***************");


outputAddress =add+ "/"+args[0]+".ast.dot";
 f = new File(outputAddress);
if(f.exists()){
    System.out.println("\n.ast.dot:: file  Exists we will del");
    f.delete();
}

	System.out.println("*****************************************");
	System.out.println("*****************************************");
	System.out.println("***************Parser Start**************");

			ParserMain.main(args);

	System.out.println("*****************************************");
	System.out.println("*****************************************");
	System.out.println("***************Parser End****************");
	System.out.println("***************Type cheking and writing out the tree END*****************");




    }
}
