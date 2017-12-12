import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/2/2017.
 */



////////////////////////////////////////////////////////////////////////
    //////////////////   website to see : http://dreampuf.github.io/GraphvizOnline/
  /////////////////////  https://stamm-wilbrandt.de/GraphvizFiddle/
    ///////////////////////////////////////////////////////////////////////////////////////

public class AST2word {
    Node n_program;
    int i=1;

    public void read(Node n_program, String[] args) {
	GenerateASTOutput.Setfilename(args[0]);
        GenerateASTOutput.writeOut("digraph tl16Ast {");
        GenerateASTOutput.writeOut("ordering=out;");
        GenerateASTOutput.writeOut("node [shape = box, style = filled, fillcolor=\"white\"];");
        GenerateASTOutput.writeOut("n1 [label=\"program\",shape=box]");
        write(n_program,1);
        GenerateASTOutput.writeOut("}");

    }

    public void write(Node node,int self) {
        List queue = new ArrayList();
        List queue_parent = new ArrayList();
        queue.add(node);
        queue_parent.add(self);
        List relations = new ArrayList();

while (queue.size()!=0){

    Node temp = (Node) queue.get(0);
    int temp_self = (int) queue_parent.get(0);
    System.out.println("taken from queue: "+temp.getData()+" "+temp_self);
        if (temp.childrenSize() > 0) {

            for (int j = 0; j < temp.childrenSize(); j++) {
                i = i + 1;
                if (temp.getChildren(j).getData().contains("red")){
                    GenerateASTOutput.writeOut("n" + i + " [label=\"" + temp.getChildren(j).getData() + "\",shape=box, fillcolor=\"red\"]");
                }else{
                GenerateASTOutput.writeOut("n" + i + " [label=\"" + temp.getChildren(j).getData() + "\",shape=box]");}
                relations.add("n" + temp_self + " -> n" + i);

                        queue.add(temp.getChildren(j));
                        queue_parent.add(i);
                    }
            }
        queue.remove(0);
        queue_parent.remove(0);
        }
        for (int z=0;z<relations.size();z++){
            GenerateASTOutput.writeOut((String) relations.get(z));
        }
    }
    }


class GenerateASTOutput {
public static String filename;
    public static void GenerateScannerOutput(){

    }
public static void Setfilename(String arg){
	
	filename = arg;
    }
    public static void writeOut(String arg){
        String add = System.getProperty("user.dir");
        String outputAddress =add+ "/"+filename+".ast.dot"; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

 
 //       String add = System.getProperty("user.dir");
   //     String outputAddress ="C:\\Users\\Admin\\OneDrive\\01_UTSA\\4_Compiler\\Parser\\_report.ast.dot"; // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        PrintWriter writer = null;

        try{
            File fileAST =new File(outputAddress);

                /* This logic is to create the file if the
                 * file is not already present
                 */
            //Here true is to append the content to file
            FileWriter fwAST = new FileWriter(fileAST,true);
            //BufferedWriter writer give better performance
            BufferedWriter bwAST = new BufferedWriter(fwAST);



//            if(!file.exists()){file.createNewFile();}
//            writer = new PrintWriter(new FileWriter(new File(outputAddress),true));
            bwAST.write(arg);
            bwAST.write(System.getProperty( "line.separator" ));

            //Closing BufferedWriter Stream
            bwAST.close();

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
