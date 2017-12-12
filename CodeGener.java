/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package compilermain;
//import static compilermain.Instruc.instructionSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author MID-nyt
 */
public class CodeGener {
    Node program_node;
	String sub;
	public static int reg=-1;
	int i=0;
	public static int block_num = 0;
        public static int block_num1 = 0;
        public static ArrayList<String> inst_list = new ArrayList<String>();
        //static ArrayList<ArrayList<String>> inst_list = new ArrayList<ArrayList<String>>();
	ArrayList<String> regList = new ArrayList<String>();
	public CodeGener(Node program_node,String fileName) throws IOException{
		this.program_node=program_node;
		new Instruc("","B"+ ++block_num,"","");
                inst_list.add("");
                inst_list.add("B"+ ++block_num1);
                inst_list.add("");
                inst_list.add("");
                
                //List<Node> n= program_node.children.get(0).children;
		//Iterator<Node> n = (Iterator<Node>)program_node.children.get(0).children;
                
		//Node n= program_node.children.get(0).children.get(0);
		for(Node node1: program_node.children.get(0).children){
			//getData.split(":")[1].substring(1, n.data.split(":")[1].length()-1);
                        //Node n1= ((Iterator<Node>) n).next();
			sub=node1.data.split(":")[1].substring(1, node1.data.split(":")[1].length()-1);
			System.out.println(sub);
			new Instruc("loadI","0","","r_"+ sub);
                        inst_list.add("loadI");
                        inst_list.add("0");
                        inst_list.add("");
                        inst_list.add("r_"+ sub);
			regList.add("r_"+ sub);
			
			//n= program_node.children.get(0).children.get(i++);
			//System.out.println(program_node.children.get(0).children.get(i++));
			System.out.println(" ERROR ");
		}
		System.out.println("sdhusdhdgjsgdudgs");
               // for(ArrayList<String> n3: inst_list){
		//	System.out.println(n3);
		//}
		state_list(program_node.children.get(1));
		
		new Instruc("exit","","","");
		new Instruc(fileName);
	}
	//System.out.println(program_node.children.get(0).children.get(0));
	public void state_list(Node state){
		//Node n= state.children.get(0);
                //Iterator<Node> n = (Iterator<Node>) state.children;
		int i=0;
		for(Node node2: state.children){
                    //Node n1= n.next();
                    System.out.println("sdhusdhdgjsgdudgs");
                     System.out.println("---------++++++++-------");
                     System.out.println(node2.data);
                    switch(node2.data){
                            
			case ":=":
                            System.out.println(node2.data);
				assign_list(node2);
                                
                                //System.out.println(state.children);
				break;
			case "while":
				new Instruc("jumpl","" ,"","B"+ ++block_num);
				new Instruc("","B"+ block_num++,"","");
                                System.out.println(node2.data);
				whilestatement(node2);
				break;
			case "if":
                                new Instruc("jumpl","" ,"","B"+ ++block_num);
                                new Instruc("","B"+ block_num++,"","");
				ifstatement(node2);
				break;
			case "writeInt":
                                System.out.println("cdhddhuhdd dhdhdhdhd wuwihhduhdash bdhjdgshgd");
                                System.out.println(node2.data);
				writeInt(node2);
				break;
			}
			//n= state.children.get(i++);
		} 
	}
	public void assign_list(Node assign){
                System.out.println("----------------------------------------------------------");
                System.out.println(assign.children.get(1).data.split(":")[0]);
		if(assign.children.get(1).data.startsWith("readInt")){
			readint(assign.children.get(1));
                        //new Instruc("readint","","","r_"+assign.children.get(0).data.split(":")[0]);
		}
		else if(assign.children.get(1).data.startsWith("+") || assign.children.get(1).data.startsWith("-") || assign.children.get(1).data.startsWith("*") || assign.children.get(1).data.startsWith("div") || assign.children.get(1).data.startsWith("mod") ){
			new Instruc("i2i",operation(assign.children.get(1)),"","r_"+assign.children.get(0).data.split(":")[0]);
		}else if(assign.children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")||assign.children.get(1).data.startsWith("0")){
                        System.out.println(assign.children.get(1).data.split(":")[0]);
			new Instruc("loadI",assign.children.get(1).data.split(":")[0],"","r_"+ ++reg);
			new Instruc("i2i","r_"+ reg ,"","r_"+ assign.children.get(0).data.split(":")[0]);
                        System.out.println(assign.children.get(1).data.split(":")[0]);
		}else{
			//new Instruc("loadI",assign.children.get(1).data.split(":")[0],"","r_"+ ++reg);
			new Instruc("i2i","r_"+ assign.children.get(1).data.split(":")[0] ,"","r_"+ assign.children.get(0).data.split(":")[0]);
		}

	}
	
	public void readint(Node readNode){
		new Instruc("readint","","","r_"+readNode.parent.children.get(0).data.split(":")[0]);
		
	}
	
	public String operation(Node bn){
		reg++;
                int flag1 =0, flag2=0;
                int r1=0, r2=0;
		if(bn.children.get(0).data.split(":")[0].matches("^[1-9][0-9]*") ){
			new Instruc("loadI",bn.children.get(0).data.split(":")[0],"","r_"+reg);
                        flag1=1;
                        r1= reg;
		}
                //else{
                  //  new Instruc("loadI",bn.children.get(0).data.split(":")[0],"","r_"+reg);
                //}
		if(bn.children.get(1).data.split(":")[0].matches("^[1-9][0-9]*") ){
			new Instruc("loadI",bn.children.get(1).data.split(":")[0],"","r_"+ reg);
                        flag2=1;
                        r2= reg;
		}
                //if(bn.children.get(i)){
                    
		
		if(bn.data.startsWith("+")){
                    
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("addu","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(bn.children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("addu","r_"+reg++,"r_"+bn.children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(bn.children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("addu","r_"+bn.children.get(0).data.split(":")[0],"r_"+reg,"r_"+ ++reg);
			}
			else{
				new Instruc("addu","r_"+bn.children.get(0).data.split(":")[0],"r_"+bn.children.get(1).data.split(":")[0],"r_"+reg);
			}
                        
		}else if(bn.data.startsWith("-")){
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("subu","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(bn.children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("subu","r_"+reg++,"r_"+bn.children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(bn.children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("subu","r_"+bn.children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
			else{
				new Instruc("subu","r_"+bn.children.get(0).data.split(":")[0],"r_"+bn.children.get(1).data.split(":")[0],"r_"+reg);
			}
		} else if(bn.data.startsWith("*")){
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("mul","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(bn.children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("mul","r_"+reg++,"r_"+bn.children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(bn.children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("mul","r_"+bn.children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
			else{
				new Instruc("mul","r_"+bn.children.get(0).data.split(":")[0],"r_"+bn.children.get(1).data.split(":")[0],"r_"+reg);
			}
		}
		return "r_"+reg;
	}
	
	public void whilestatement(Node node_while){
            int flag1 =0, flag2=0, flag3=0;
                int r1=0, r2=0;
                String x= node_while.children.get(0).children.get(0).data.split(":")[0];
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println(x);
            System.out.println(node_while.children.get(0).data.split(":")[0]);
            if(x.equals("*") || x.equals("+") || x.equals("-")){
                if(node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*") ){
			new Instruc("loadI",node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"","r_"+reg);
                        flag1=1;
                        r1= reg;
		}
                //else{
                  //  new Instruc_List("loadI",bn.children.get(0).data.split(":")[0],"","r_"+reg);
                //}
		if(node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*") ){
			new Instruc("loadI",node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"","r_"+ reg);
                        flag2=1;
                        r2= reg;
		}
                //if(bn.children.get(i)){
                    
            switch(node_while.children.get(0).children.get(0).data.split(":")[0]){
                case "*" :
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("mul","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("mul","r_"+reg++,"r_"+node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("mul","r_"+node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
                        else{
                        new Instruc("mul","r_"+node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                        }
                        break;
                        
                case "+" :
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("addu","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("addu","r_"+reg++,"r_"+node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("addu","r_"+node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
                        else{
                        new Instruc("addu","r_"+node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                        }
                        break;
                        
                case "-" :
                    if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("subu","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("subu","r_"+reg++,"r_"+node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("subu","r_"+node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
                        else{
                        new Instruc("subu","r_"+node_while.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+node_while.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                        }
                        break;
            }
        }
            else{
                flag3=1;
            }
		switch(node_while.children.get(0).data.split(":")[0]){
			case "<=":
                                System.out.println(node_while.children.get(0).children.get(1).data.split(":")[0]);
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_while.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sle","r_"+ reg,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                                System.out.println("asASSA");
				break;
			case ">=":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_while.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sge","r_"+ reg,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case "==":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_while.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("seq","r_"+ reg,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case ">":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_while.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sgt","r_"+ reg,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case "<":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_while.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("slt","r_"+ reg,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case "!=":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_while.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sne","r_"+ reg,"r_"+node_while.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
		}
		new Instruc("cbr","r_"+ reg,"","B"+ block_num +",B"+ ++block_num);
		new Instruc("","B"+ (block_num-1),"","");
		state_list(node_while.children.get(1));
		new Instruc("jumpl","","","B"+(block_num-2));
		new Instruc("","B"+block_num,"","");
	}
	
	public void ifstatement(Node node_if){
            int flag1 =0, flag2=0, flag3=0;
                int r1=0, r2=0;
                String x= node_if.children.get(0).children.get(0).data.split(":")[0];
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println(x);
            System.out.println(node_if.children.get(0).data.split(":")[0]);
            if(x.equals("*") || x.equals("+") || x.equals("-")){
                if(node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*") ){
			new Instruc("loadI",node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"","r_"+reg);
                        flag1=1;
                        r1= reg;
		}
                //else{
                  //  new Instruc("loadI",bn.children.get(0).data.split(":")[0],"","r_"+reg);
                //}
		if(node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*") ){
			new Instruc("loadI",node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"","r_"+ reg);
                        flag2=1;
                        r2= reg;
		}
                //if(bn.children.get(i)){
                    
            switch(node_if.children.get(0).children.get(0).data.split(":")[0]){
                case "*" :
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("mul","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("mul","r_"+reg++,"r_"+node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("mul","r_"+node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
                        else{
                        new Instruc("mul","r_"+node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                        }
                        break;
                        
                case "+" :
                        if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("addu","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("addu","r_"+reg++,"r_"+node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("addu","r_"+node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
                        else{
                        new Instruc("addu","r_"+node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                        }
                        break;
                        
                case "-" :
                    if(flag1==1 && flag2==1){
                                System.out.println(r1);
                                new Instruc("subu","r_"+ r1,"r_"+ r2++,"r_"+ ++reg);
                        }
			if(node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("subu","r_"+reg++,"r_"+node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+reg);
			}
			else if(node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0].matches("^[1-9][0-9]*")){
				new Instruc("subu","r_"+node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+reg++,"r_"+reg);
			}
                        else{
                        new Instruc("subu","r_"+node_if.children.get(0).children.get(0).children.get(0).data.split(":")[0],"r_"+node_if.children.get(0).children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                        }
                        break;
            }
        }
            else{
                flag3=1;
            }
		switch(node_if.children.get(0).data.split(":")[0]){
			case "<=":
                                System.out.println(node_if.children.get(0).children.get(1).data.split(":")[0]);
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_if.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sle","r_"+ reg,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
                                System.out.println("asASSA");
				break;
			case ">=":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_if.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sge","r_"+ reg,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case "==":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_if.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("seq","r_"+ reg,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case ">":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_if.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sgt","r_"+ reg,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case "<":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_if.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("slt","r_"+ reg,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
			case "!=":
                                if(flag3==1)
                                    new Instruc("sle","r_"+node_if.children.get(0).children.get(0).data.split(":")[0] ,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				else
                                    new Instruc("sne","r_"+ reg,"r_"+node_if.children.get(0).children.get(1).data.split(":")[0],"r_"+ ++reg);
				break;
		}
		new Instruc("cbr","r_"+ reg,"","B"+ block_num +",B"+ ++block_num);
		new Instruc("","B"+ (block_num-1),"","");
		state_list(node_if.children.get(1));
		new Instruc("","B"+block_num,"","");
	}
	
	public void writeInt(Node writeintStatement){
		if(writeintStatement.children.get(0).data.startsWith("+") || writeintStatement.children.get(0).data.startsWith("*")){
			operation(writeintStatement.children.get(0));
			new Instruc("writeint","","","r_"+reg);
		}else{
			new Instruc("writeint","","","r_"+writeintStatement.children.get(0).data.split(":")[0]);
		}
	}
}
class Instruc {
	static ArrayList<ArrayList<String>> instru_list = new ArrayList<ArrayList<String>>();
	
	public Instruc(String value1, String value2, String value3, String value4){
		ArrayList<String> instruction = new ArrayList<String>();
		instruction.add(value1);
		instruction.add(value2);
		instruction.add(value3);
		instruction.add(value4);
		instru_list.add(instruction);
                for(ArrayList<String> n: instru_list){
			System.out.println(n);
		}
	}
	public Instruc(String fileName) throws IOException{
		for(ArrayList<String> n: instru_list){
			System.out.println(n);
		}
		new Flow_Graph1(instru_list,fileName);
		new AssemblyGeneration(instru_list,fileName);
	}
}





