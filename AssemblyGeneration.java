/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package compilermain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author MID-nyt
 */
public class AssemblyGeneration {
    private Hashtable<String, Integer> stackspace= new Hashtable<String, Integer>();
    private Integer stacktop= 4;
    public AssemblyGeneration(ArrayList<ArrayList<String>> inst_list, String inputfile) throws IOException{
        String outfile = inputfile.substring(0,inputfile.length()-3) + ".s";
        try{
            File fileasm =new File(outfile);
            FileWriter fwasm = new FileWriter(fileasm);
            //BufferedWriter writer give better performance
            BufferedWriter bwasm = new BufferedWriter(fwasm);
            bwasm.write("\t.data");
            bwasm.newLine();
            bwasm.write("newline: .asciiz \"\\n\"");
            bwasm.newLine();
            bwasm.write("\t.text");
            bwasm.newLine();
            bwasm.write("\t.globl main");
            bwasm.newLine();
            bwasm.write("main:");
            bwasm.newLine();
            bwasm.write("\tli $fp, 0x7ffffffc");
            bwasm.newLine();
            for(ArrayList<String> node: inst_list){
            bwasm.write("\t# "+ node.get(0)+ " " + node.get(1) + " " + node.get(2) + " " + node.get(3) );
				bwasm.newLine();
				if(node.get(1).startsWith("B")){
					bwasm.write(node.get(1)+":");
					bwasm.newLine();
				}
				else if(node.get(0).equals("loadI")){
					bwasm.write("\tli $t0, "+node.get(1));
					bwasm.newLine();
                                        String x= node.get(3);
                                        if(stackspace.containsKey(x))
                                            bwasm.write("\tsw $t0, "+ stackspace.get(x) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x,stacktop);
                                            bwasm.write("\tsw $t0, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
				}
				else if(node.get(0).equals("readint")){
					bwasm.write("\tli $v0, 5");
					bwasm.newLine();
                                        //System.out.println(x);
					bwasm.write("\tsyscall");
					bwasm.newLine();
					bwasm.write("\tadd $t0, $v0, $zero");
					bwasm.newLine();
                                        String x= node.get(3);
                                        if(stackspace.containsKey(x))
                                            bwasm.write("\tsw $t0, "+ stackspace.get(node.get(3)) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x,stacktop);
                                            bwasm.write("\tsw $t0, "+ stacktop +"($fp)");
                                        }
					
					bwasm.newLine();
				}
				else if(!node.get(0).isEmpty() && !node.get(1).isEmpty() && !node.get(2).isEmpty() && !node.get(3).isEmpty()){
					String x1= node.get(1);
                                        if(stackspace.containsKey(x1))
                                            bwasm.write("\tlw $t1, "+ stackspace.get(x1) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x1,stacktop);
                                            bwasm.write("\tlw $t1, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
					String x2= node.get(2);
                                        if(stackspace.containsKey(x2))
                                            bwasm.write("\tlw $t2, "+ stackspace.get(x2) +"($fp)");
                                        else{
                                            stacktop = stacktop-4;
                                            stackspace.put(x2,stacktop);
                                            bwasm.write("\tlw $t2, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
					bwasm.write("\t"+node.get(0)+" $t0, $t1, $t2");
					bwasm.newLine();
					String x3= node.get(3);
                                        if(stackspace.containsKey(x3))
                                            bwasm.write("\tsw $t0, "+ stackspace.get(x3) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x3,stacktop);
                                            bwasm.write("\tsw $t0, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
				}
				else if(node.get(0).equals("exit")){
					bwasm.write("\tli $v0, 10");
					bwasm.newLine();
					bwasm.write("\tsyscall");
					bwasm.newLine();
				}
				else if(node.get(0).equals("writeint")){
					bwasm.write("\tli $v0, 1");
					bwasm.newLine();
					String x= node.get(3);
                                        if(stackspace.containsKey(x))
                                            bwasm.write("\tlw $t1, "+ stackspace.get(x) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x,stacktop);
                                            bwasm.write("\tlw $t1, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
					bwasm.write("\tadd $a0, $t1, $zero");
					bwasm.newLine();
					bwasm.write("\tsyscall");
					bwasm.newLine();
					bwasm.write("\tli $v0, 4");
					bwasm.newLine();
					bwasm.write("\tlw $a0, newline");
					bwasm.newLine();
					bwasm.write("\tsyscall");
					bwasm.newLine();
				}
				else if(node.get(0).equals("i2i")){
					String x= node.get(1);
                                        if(stackspace.containsKey(x))
                                            bwasm.write("\tlw $t1, "+ stackspace.get(x) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x,stacktop);
                                            bwasm.write("\tlw $t1, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
					bwasm.write("\tadd $t0, $t1, $zero");
					bwasm.newLine();
					String x1= node.get(3);
                                        if(stackspace.containsKey(x1))
                                            bwasm.write("\tsw $t0, "+ stackspace.get(x1) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x1,stacktop);
                                            bwasm.write("\tsw $t0, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
				}
				else if(node.get(0).equals("jumpl")){
					bwasm.write("\tj "+ node.get(3));
					bwasm.newLine();
				}
				else if(node.get(0).equals("cbr")){
					String x= node.get(1);
                                        if(stackspace.containsKey(x))
                                            bwasm.write("\tlw $t0, "+ stackspace.get(x) +"($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x,stacktop);
                                            bwasm.write("\tlw $t0, "+ stacktop +"($fp)");
                                        }
					bwasm.newLine();
					bwasm.write("\tbne $t0, $zero "+ node.get(3).split(",")[0]);
					bwasm.newLine();
					bwasm.write("L1:");
					bwasm.newLine();
					bwasm.write("\tj "+ node.get(3).split(",")[1]);
					bwasm.newLine();
				}
                                else if(node.get(0).equals("writeint")){
                                        bwasm.write("\tli $v0, 1");
                                        bwasm.newLine();
                                        String x= node.get(1);
                                        if(stackspace.containsKey(x))
                                        bwasm.write("\tlw $t1,"+ stackspace.get(x) + "($fp)");
                                        else{
                                            stacktop= stacktop-4;
                                            stackspace.put(x,stacktop);
                                            bwasm.write("\tlw $t1, "+ stacktop +"($fp)");
                                        }
                                        bwasm.newLine();
                                        bwasm.write("\tadd $a0, $t1, $zero");
                                        bwasm.newLine();
                                        bwasm.write("\tsyscall");
                                        bwasm.newLine();
                                        bwasm.write("\tli $v0, 4");
                                        bwasm.newLine();
                                        bwasm.write("\tla $a0, newline");
                                        bwasm.newLine();
                                        bwasm.write("\tsyscall");
                                        bwasm.newLine();
                }
				bwasm.newLine();
        }
            bwasm.close();
        }catch(FileNotFoundException e){
            e.getMessage();
        }
        
    }
    
    
}
