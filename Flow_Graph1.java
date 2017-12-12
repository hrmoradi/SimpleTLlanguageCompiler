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
public class Flow_Graph1 {
	public static int num=0;
        ArrayList jump = new ArrayList();
	ArrayList cbr = new ArrayList();
	public Flow_Graph1(ArrayList<ArrayList<String>> instruc_list,String fileName){
		try{
			FileWriter fwcfg = new FileWriter(fileName.substring(0,fileName.length()-3) + ".3A.cfg.dot");
			BufferedWriter bwcfg = new BufferedWriter(fwcfg);
			bwcfg.write("digraph graphviz {");
			bwcfg.write(System.getProperty( "line.separator" ));
			bwcfg.write("node [shape = none];");
			bwcfg.write(System.getProperty( "line.separator" ));
			bwcfg.write("edge [tailport = s];");
			bwcfg.write(System.getProperty( "line.separator" ));
			bwcfg.write("entry");
			bwcfg.write(System.getProperty( "line.separator" ));
			bwcfg.write("subgraph cluster {");
			bwcfg.write(System.getProperty( "line.separator" ));
			bwcfg.write("color=\"/x11/white\" ");
			//System.out.println(CodeGeneration.block);
                        String s= "n";
			int nextinst=0;
			int future=0;
			
			while(num<CodeGener.block_num){
				bwcfg.write(s+ num + " [label=<<table border=\"0\">");
				for(ArrayList<String> node: instruc_list){
					nextinst++;
					if(nextinst<future){
						continue;
					}
					//fop.write("<tr><td>"+nextinst+"</td></tr>");	
					if(node.get(1).equals("B"+(num+2))){
						future=nextinst;
						break;
					}
					if(node.get(1).equals("B"+(num+1))){
						bwcfg.write("<tr>");
						bwcfg.write("<td border=\"1\" colspan=\"3\">"+node.get(1)+"</td>");
						bwcfg.write("</tr>");
					}else{
						bwcfg.write("<tr>");
                                                for(int i=0;i<3;i++)
                                                    bwcfg.write("<td align=\"left\">"+node.get(i)+"</td>");
						bwcfg.write("<td align=\"left\"> =&gt; </td>");
						bwcfg.write("<td align=\"left\">"+node.get(3)+"</td>");
						bwcfg.write("</tr>");
					}
					if(node.get(0).equals("jumpl")){
						jump.add(num);
					}
					if(node.get(0).equals("cbr")){
						cbr.add(num);
					}
				}
				bwcfg.write("</table>>,fillcolor=\"/x11/white\",shape=box]");
				num++;
				nextinst=0;
			}
			bwcfg.newLine();
			bwcfg.write("}");
			bwcfg.newLine();
			bwcfg.write("entry -> n0"); 
			bwcfg.newLine();
                        pp(instruc_list, bwcfg);
			bwcfg.newLine();
			bwcfg.write("n"+(num-1)+"->exit");
			bwcfg.newLine();
			bwcfg.write("}");
			bwcfg.newLine();
			bwcfg.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        public void pp(ArrayList<ArrayList<String>> instruc_list, BufferedWriter bwcfg) throws IOException{
            
                        int flag1=0;
			int flag2=0;
			for(ArrayList<String> node: instruc_list){
				if(node.get(0).equals("jumpl") && flag1<jump.size()){
					bwcfg.write("n" + jump.get(flag1) +"-> n"+(Integer.parseInt(node.get(3).substring(1,node.get(3).length()))-1));
					flag1++;
					bwcfg.newLine();
				}
				else if(node.get(0).equals("cbr")&& flag2<cbr.size()){
					bwcfg.write("n" + cbr.get(flag2) +"-> n"+ (Integer.parseInt(node.get(3).substring(1,2))-1));
					bwcfg.newLine();
					bwcfg.write("n" + cbr.get(flag2) +"-> n"+ (Integer.parseInt(node.get(3).substring(4,5))-1));
					flag2++;
					bwcfg.newLine();
				}
			}
        }
}
