import java.io.*;
import java.io.FileReader;
import java.util.Hashtable;

/**
 * Created by Admin on 2/17/2017.
 */
public class ScannerMainClass {

    public static void main (String[] args){

        ///// creating hashtable

        Hashtable<String,String> MYHASH = new Hashtable<String,String>() {{
            put("if", "IF");
            put("then", "THEN");
            put("else", "ELSE");
            put("begin", "BEGIN");
            put("end", "END");
            put("while", "WHILE");
            put("do", "DO");
            put("program", "PROGRAM");
            put("var", "VAR");
            put("as", "AS");
            put("int", "INT");
            put("bool", "BOOL");

            put("writeInt", "WRITEINT");
            put("readInt", "READINT");

            put("(", "LP");
            put(")", "RP");
            put(":=", "ASGN");
            put(";", "SC");
            put("*", "MULTIPLICATIVE(*)");
            put("div", "MULTIPLICATIVE(DIV)");
            put("mod", "MULTIPLICATIVE(MOD)");
            put("+", "ADDITIVE(+)");
            put("-", "ADDITIVE(-)");
            put("=" , "COMPARE(=)");
            put("!=" , "COMPARE(!=)");
            put("<" , "COMPARE(<)");
            put(">" , "COMPARE(>)");
            put("<=" , "COMPARE(<=)");
            put(">=" , "COMPARE(>=)");

            put("false" , "boollit(false)");
            put("true" , "boollit(true)");
            

        }};




        Hashtable<String,String> SYMBOLS = new Hashtable<String,String>() {{
            put("(", "LP");
            put(")", "RP");
            put(":=", "ASGN");
            put(";", "SC");
            put("*", "MULTIPLICATIVE(*)");
            put("div", "MULTIPLICATIVE(DIV)");
            put("mod", "MULTIPLICATIVE(MOD)");
            put("+", "ADDITIVE(+)");
            put("-", "ADDITIVE(-)");
            put("=" , "COMPARE(=)");
            put("!=" , "COMPARE(!=)");
            put("<" , "COMPARE(<)");
            put(">" , "COMPARE(>)");
            put("<=" , "COMPARE(<=)");
            put(">=" , "COMPARE(>=)");
            put(":","!!! test !!!");

        }};

        Hashtable<String,String> ILLEGAL = new Hashtable<String,String>() {{
            put("~", "");
            put("`", "");
            put("!", "");
            put("@", "");
            put("#", "");
            put("$", "");
            put("^", "");
            put("&", "");
            put("{", "");
            put("}", "");
            put("[", "");
            put("]", "");
            put("|", "");
            put("\\", "");
            put("'", "");
            put("/", "");
            put("?", "");
            put(",", "");
            put(".", "");
            put("\"", "");






        }};
        ///// Reading input file location
        String add = System.getProperty("user.dir");
        System.out.print("\n\nAPP Running address is: "+add);
	System.out.println("*****************************************");
	System.out.println("*****************************************");
	System.out.print("\n Output will be generated here as: \n"+args[0]+".tok");
        if(args.length==0) {
            System.out.print("\n you should specify input file address\n ex: 'sh exec.sh ScannerInputFile.tl' \n\n");//The file was not found, insert error handling here
        }
	    System.out.print("\nFirst args Passed is: "+args[0]);
//        URL path = ClassLoader.getSystemResource("myFile.txt");

          String Address=null;
//        System.out.print("\n     Scanner: Please type the input file address: ");
//        InputStreamReader inputFileLocation= new InputStreamReader(System.in);
//        BufferedReader readerBuffer = new BufferedReader(inputFileLocation);
//
//        try {
//            Address = readerBuffer.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("\n     Error in the input file address" + e.getMessage());
//        }
        Address =args[0];//"C:\\Users\\Admin\\Desktop\\123.txt"; //add+args;   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.print("\ncombined address of input *.tl file is: "+     Address+"\n\n");

        ///// Reading the file
        try {
            File file = new File(Address);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber=lineNumber+1;
                if (line.contains("%")){
                    if ( line.indexOf('%')==0){
                        line = " ";
			
                    }else {
                        line=line.substring(0,line.indexOf('%'));
                    }
                }
		line = line.replace("\t", "");
                String[] spaceSplited = line.split(" ");



		GenerateScannerOutput.Setfilename(args[0]);
                for (int word=0; word<spaceSplited.length; word++){   // iterated word by word
                            if (MYHASH.containsKey(spaceSplited[word])) {
                                System.out.print("\n"+MYHASH.get(spaceSplited[word]));
                                //// we should write it here to the file !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                GenerateScannerOutput.writeOut(MYHASH.get(spaceSplited[word]));




                            }else{ // when we dont have in hash table
                                //System.out.print("\n---going to analyze char by char:  -> "+spaceSplited[word]+"\n"); ///////////////////////////////////////////////////////------------------------debuging

                                //System.out.print("\n---before second for: when not in hash table -> spaceSplited[word].length()-> "+spaceSplited[word].length()+"\n");
    //                                if (spaceSplited[word].equals("/%")) {
    //                                    System.out.print("\n alone % detected");
    //                                    break;
    //                                }else if (Character.toString(spaceSplited[word].charAt(0)).matches("%")) {
    //                                    System.out.print("\n string with % detected");
    //                                    break;
    //                                }


                                //if (spaceSplited[word].charAt(0)=='%') { break;}

                                for (int charInWord=0; charInWord<spaceSplited[word].length(); charInWord++) {  // iterating through all characters in the one word




                                    //System.out.print("\n---first char:  -> "+spaceSplited[word].charAt(charInWord)+"\n");
                                    //System.out.print("\n---first char: is in symbols  -> "+SYMBOLS.containsKey(Character.toString(spaceSplited[word].charAt(charInWord)))+"\n");
                                    if (SYMBOLS.containsKey(Character.toString(spaceSplited[word].charAt(charInWord)))) {
                                        //System.out.print("\n---first char is symbol:  -> "+spaceSplited[word].charAt(charInWord)+"\n");
                                        String temp = "";
                                        boolean twoSymbolDetected =false;
                                        if (   (charInWord+1) < spaceSplited[word].length() && SYMBOLS.containsKey(Character.toString(spaceSplited[word].charAt(charInWord+1)))) {
                                            //System.out.print("\n---Second char is symbol:  -> "+spaceSplited[word].charAt(charInWord+1)+"\n");
                                            if (   (charInWord+2) < spaceSplited[word].length() && SYMBOLS.containsKey(Character.toString(spaceSplited[word].charAt(charInWord+2)))) {
                                                //System.out.print("\n---third char is symbol:  -> "+spaceSplited[word].charAt(charInWord+3)+"\n");
                                                System.out.print("\nScanner Error: 3 Symbol beside each other in line "+lineNumber+" !!!");    // we know that 3 character are symbol
                                                System.exit(0);
                                            }
                                            temp = temp + spaceSplited[word].charAt(charInWord);
                                            temp = temp + spaceSplited[word].charAt(charInWord+1);// we know that 2 character are symbol
                                            //System.out.print("\n---first two char are symbol:  -> "+temp+"\n");
                                            if (SYMBOLS.containsKey(temp)){
                                                //System.out.print("\n---the two recognized in Symbol hash:  -> "+temp+"\n");
                                                System.out.print("\n"+SYMBOLS.get(temp));
                                                GenerateScannerOutput.writeOut(SYMBOLS.get(temp));
                                            }else if ( temp.equals("++")){
                                                System.out.print("\nScanner Error:  not exist in this language !!!");
                                            }else if (temp.equals("--")){
                                                System.out.print("\nScanner Error:  not exist in this language !!!");
                                            }else {
                                                System.out.print("\nScanner Error:  Error: this 2 symbol are not right to be used with each other, number line: "+lineNumber+" temp:"+temp+"!!!");
                                                System.exit(0);
                                            }
                                            charInWord = charInWord+1;// manually increase charInWord because we detected two symbol
                                            twoSymbolDetected =true;
                                        }
                                        if (!twoSymbolDetected) {// we know that it only one symbol
                                            temp = temp + spaceSplited[word].charAt(charInWord);
                                            //System.out.print("\n---only one char is symbol:  -> "+temp+"\n");
                                            if (SYMBOLS.containsKey(temp)){
                                                //System.out.print("\n---only one char is in symbol hash:  -> "+temp+"\n");
                                                System.out.print("\n"+SYMBOLS.get(temp));
                                                GenerateScannerOutput.writeOut(SYMBOLS.get(temp));
                                            }else {
                                                System.out.print("\n     Scanner Error: this 1 character not recognized "+lineNumber+"!!! because not definde in language temp:"+temp+"!!!");
                                                System.exit(0);
                                            }
                                        }








                                    }else if (Character.isDigit(spaceSplited[word].charAt(charInWord)) ) {   ///// end of first if for checking symbols --- symbols !!!
                                        String temp = "";
                                        temp = temp + spaceSplited[word].charAt(charInWord);
                                        if (spaceSplited[word].charAt(charInWord)=='0' &&(charInWord+1) < spaceSplited[word].length() && Character.isDigit(spaceSplited[word].charAt(charInWord+1))){
                                            System.out.print("\nScanner Error: a number starts  with zero in line "+lineNumber+", invalid number!");
                                            System.exit(0);
                                        }
                                        while (   (charInWord+1) < spaceSplited[word].length() && Character.isDigit(spaceSplited[word].charAt(charInWord+1))){

                                            temp = temp + spaceSplited[word].charAt(charInWord+1);
                                            charInWord= charInWord+1;
                                        }
                                        if ( (charInWord+1) < spaceSplited[word].length() && Character.isLetter(spaceSplited[word].charAt(charInWord+1)   ) || spaceSplited[word].charAt(charInWord)=='_'){
                                            System.out.print("\nScanner Error: variable name could not start with number in line "+lineNumber+", invalid variable!");
                                            System.exit(0);
                                        }
                                        GenerateScannerOutput.writeOut("num("+temp+")");
                                        System.out.print("\nnum("+temp+")");







                                    }else if (Character.isLetter(spaceSplited[word].charAt(charInWord)) || spaceSplited[word].charAt(charInWord)=='_') {   //// end of else if for checking number, here the remaining is not symbol or number
                                        String temp = "";
                                        temp = temp + spaceSplited[word].charAt(charInWord);
                                        while (   (charInWord+1) < spaceSplited[word].length() && Character.isLetter(spaceSplited[word].charAt(charInWord+1))){
                                            temp = temp + spaceSplited[word].charAt(charInWord+1);

                                            charInWord= charInWord+1;
                                        }
                                        if (MYHASH.containsKey(temp)  && Character.isDigit(spaceSplited[word].charAt(0)) ){
                                            System.out.print("\n"+MYHASH.get(temp));
                                            GenerateScannerOutput.writeOut(MYHASH.get(temp));
                                        }else {
                                            while (   (charInWord+1) < spaceSplited[word].length() && (Character.isLetter(spaceSplited[word].charAt(charInWord+1)) || Character.isDigit(spaceSplited[word].charAt(charInWord+1)))){
                                                temp = temp + spaceSplited[word].charAt(charInWord+1);
                                                charInWord= charInWord+1;
                                            }
                                            GenerateScannerOutput.writeOut("ident("+temp+")");
                                            System.out.print("\nident("+temp+")");
                                        }
                                    }else if (ILLEGAL.containsKey(Character.toString(spaceSplited[word].charAt(charInWord)))) {
                                        System.out.print("\n      Scanner Error: this character is an illegal char in line "+lineNumber+" !!! because it is not definde in the language : "+spaceSplited[word].charAt(charInWord)+" !!! ");
                                    }else {//System.out.print("\n     Last else, tocken not recognized");}
                                    }

                                }//end of for for analysing char by char





                            }// else if it was not in hash table
                    } // for the for which iterated word by word




                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            //System.out.println("\n\nContents of file:");
            //System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("\nScanner Error: Error in reading the input file" + e.getMessage());
        }


//
//        workdir' where you should put two files 'build.sh', and 'exec.sh'. Running 'build.sh' should build your project, and running 'exec.sh <basename>.tl' should execute your compiler on a source code file in
//        'workdir', and output all corresponding outputs into 'workdir'


//System.out.print("----------: we are here at: --------  "); ///////////////////////////////////////////////////////------------------------debuging


    }
}
class GenerateScannerOutput {
public static String filename;
    public static void GenerateScannerOutput(String arg){
	
    }
public static void Setfilename(String arg){
	
	filename = arg;
    }
    public static void writeOut(String Swrite){
        String add = System.getProperty("user.dir");
        String outputAddress =add+ "/"+filename+".tok"; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//System.out.println("outputAddress: "+ outputAddress);
        PrintWriter writer = null;

        try{
            File file =new File(outputAddress);

                /* This logic is to create the file if the
                 * file is not already present
                 */
            //Here true is to append the content to file
            FileWriter fw = new FileWriter(file,true);
            //BufferedWriter writer give better performance
            BufferedWriter bw = new BufferedWriter(fw);



//            if(!file.exists()){file.createNewFile();}
//            writer = new PrintWriter(new FileWriter(new File(outputAddress),true));

            
            bw.write(Swrite);
bw.write(System.getProperty( "line.separator" ));////////////////
            //Closing BufferedWriter Stream
            bw.close();

            //System.out.println("Data successfully Written or Appended at the end of file");


            // writer.close();

        }catch(FileNotFoundException e){
            e.getMessage();
            System.out.println("Scanner Error:    Writer error FileNotFoundException");

        }catch(IOException e){
            e.getMessage();
            System.out.println("Scanner Error:    Writer error IOException");
        }
    }
}
