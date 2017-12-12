/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package compilermain;

//import static compilermain.CfgGeneration.nodeNumber;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author MID-nyt
 */
public class Flow_graph {
    public Flow_graph(ArrayList<ArrayList<String>> inst_List, String inputfile){
     new GenerateCFGOutput(inputfile.substring(0,inputfile.length()-3) + ".3A.cfg.dot");
     //GenerateCFGOutput.writeOut(inputfile.substring(0,inputfile.length()-3) + ".3A.cfg.dot");
     //String outfile = inputfile.substring(0,inputfile.length()-3) + ".3A.cfg.dot";
     new GenerateCFGOutput("digraph tl16Ast {");
     new GenerateCFGOutput("node [shape = none];");
     new GenerateCFGOutput("edge [tailport = s];");
     new GenerateCFGOutput("entry");
     new GenerateCFGOutput("subgraph cluster {");
     new GenerateCFGOutput("color=\"/x11/white\"");
     int num=0;
     int count=0;
     String s="n";
     int isnum=0, know=0;
     ArrayList<ArrayList<String>> temp = inst_List;
     
     while(count<CodeGener.block_num){
        // System.out.println("------------------------------");
         //System.out.println(count);
         String k = s.concat(Integer.toString(num));
         //System.out.println("------------------------------");
         //System.out.println(k);
         new GenerateCFGOutput(k+"[label=<<table border=\"0\">");
         for(ArrayList<String> node: temp){
             isnum++;
             if(isnum<know){
		continue;
             }
             //if(node.get(0).equals("exit")){
               //  break;
             //}
    //            System.out.println("------------------------------");
      //            System.out.println(node.get(1));
             if (node.get(1).equals("B"+ num+2)){
                 know=isnum;
                 break;
                 
             }
             if(node.get(1).equals("B"+ (num+1))){
//                  System.out.println("------------------------------");
  //                System.out.println(node.get(1));
                 new GenerateCFGOutput("<tr>");
                 //String k1= "<td border=\"1\" colspan=\"3\">".concat(node.get(1));
                 //String k2= k1.concat("</td>");
                 new GenerateCFGOutput("<td border=\"1\" colspan=\"3\">"+node.get(1)+"</td>");
                 new GenerateCFGOutput("/tr");
             } 
             
             else {
                 new GenerateCFGOutput("<tr>");
                 for(int m=0; m<3; m++){
                 //String k3= "<td align=\"left\">".concat(node.get(m));
                 //String k4= k3.concat("</td>");
                 new GenerateCFGOutput("<td align=\"left\">"+node.get(m)+"</td>");
                 }
                 new GenerateCFGOutput("<td align=\"left\"> =&gt; </td>");
                 new GenerateCFGOutput("<td align=\"left\">"+node.get(3)+"</td>");
                 new GenerateCFGOutput("</tr>");
             }
             if(node.get(0).equals("jumpl")){
                 String k5= k.concat(" ->");
                 int sub = Integer.parseInt(node.get(3).substring(1));
                 String ap= s.concat(Integer.toString(sub-1));
                 String k6= k5.concat( ap);
                 new GenerateCFGOutput(k6);
             }
             //Integer.parseInt
             if(node.get(0).equals("cbr")){
                 //System.out.println("------------------------------");
                 //System.out.println(node.get(0));
                 String k7= k.concat(" ->");
                 //System.out.println("------------------------------");
                 //System.out.println(node.get(3));
                 int sub1= Integer.parseInt(node.get(3).substring(1, 2));
                 //System.out.println("------------------------------------");
                 //System.out.println(sub1);
                 int sub2= Integer.parseInt(node.get(3).substring(4));
                 //System.out.println("------------------------------------");
                 //System.out.println(sub2);
                 String ap1= s.concat(Integer.toString(sub1-1));
                 String ap2= s.concat(Integer.toString(sub2-1));
                 //String k8= k7.concat( ap1);
                 new GenerateCFGOutput(k7+ ap1);
                 //String k9= k7.concat( ap2);
                 new GenerateCFGOutput(k7+ ap2);
             }
         }
         new GenerateCFGOutput("</table>>,fillcolor=\"/x11/white\",shape=box]");
         num++;
         count++;
         isnum=0;
     }
     new GenerateCFGOutput("}");
     new GenerateCFGOutput("entry -> n0");
     new GenerateCFGOutput("n"+(num-1)+"->exit");
}}

class GenerateCFGOutput {
    String outfile;
    public GenerateCFGOutput(String oufile){
        this.outfile = outfile;
    }
            
    public void GenerateCFGOutput(String arg){

        try{
           System.out.println("----------Kavita-------------");
            FileWriter fwcgf = new FileWriter(outfile);
            //BufferedWriter writer give better performance
            BufferedWriter bwcgf = new BufferedWriter(fwcgf);
            System.out.println((arg));
            bwcgf.write(arg);
            System.out.println((arg));
            //bwcgf.write(System.getProperty( "line.separator" ));
            FileReader fr = null;
            BufferedReader br = null;
            fr = new FileReader(outfile);
	    br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(outfile));

			while ((sCurrentLine = br.readLine()) != null) {
                                System.out.println("shdgs sjdh");
				System.out.println(sCurrentLine);
			}

            bwcgf.close();

           
        }catch(FileNotFoundException e){
            e.getMessage();
            System.out.println("Scanner Error:    Writer error FileNotFoundException");

        }catch(IOException e){
            e.getMessage();
            System.out.println("Scanner Error:    Writer error IOException");
        }
    }
}

