import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
//import org.graphstream.graph.*;
/**
 * Created by Admin on 3/22/2017.
 */


public class ParserMain  {
    public static List<String> token = new ArrayList<String>();//ArrayList<String> token = new ArrayList<String>();
    public static int lookAheadPossition = 0;
	


    public ParserMain(String[] args) {
        ///////////////////////////////// constructor no return value
        //////// reading tokens from file in costructor
        try {
            String Address = "./"+args[0]+".tok";
            File file = new File(Address);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            int i =0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line!="" || line!=" " || line!="\n") {  /// have a problem if first line is empty ///////////////////  <------------ !!!!!!!
                    token.add(line);
                    System.out.println(i++ +"  " +line );
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Parser(){
        ///////////////////////////////// !!!
    }
    public static void main(String[] args) throws IOException {

       ///////////////////////////////// non-static program cannot be accesses from static main - should make new class then you can call

        ParserMain Parse = new ParserMain(args);
        Node n_program = Parse.program();
	System.out.println("\ninside parser: parsing end, pass tree ro type checking\n");
       CodeGener cg= new CodeGener(n_program,args[0]); ////////////////////////////////////////////////////////////////////////////////////kavita
       h_typeCheckingClass tp = new h_typeCheckingClass(n_program);
       Node result = tp.starttype(n_program);
	System.out.println("\ninside parser: TYPE checking end, pass tree to WRITING AST\n");
       AST2word ast=new AST2word();
       ast.read(result, args);




    }



    /////////////////////////////////////////////////////// hash table for adding symbols
    Hashtable symbolTable = new Hashtable();

    ////////////////////////////////////////////////////// node creation
    //Node n_program;
    //Node n_declarations;
//    Node n_statementSequence;
//    Node n_type;
//    Node n_statement;
//    Node n_assignment;
//    Node n_ifStatement;
//    Node n_whileStatement;
//    Node n_writeInt;
//    Node n_expression;
//    Node n_elseClause;
//    Node n_simpleExpression;
//    Node n_term;
//    Node n_factor;






    //////////////////////////////////////////////////////  if lookAhead points to the same passed argument we expect good, else ERROR
    public void CheckError(String s) {
        if (s.equals(token.get(lookAheadPossition))){
            System.out.println("Expected: "+ s +" at line:"+ lookAheadPossition);
            lookAheadPossition=lookAheadPossition+1;
        }else {
            System.out.println("ERROR: at line:"+ lookAheadPossition +"      expected-> "+ s +" --- " + token.get(lookAheadPossition)+ " <-detected." );
            System.exit(0);
        }
    }


    ////////////////////////////////////////////////////// start of parsing functions


    // <program> ::= PROGRAM <declarations> BEGIN <statementSequence> END
    public Node program() {

        this.CheckError("PROGRAM");


        Node n_program = new Node("program");
        n_program.setParent(null);
        System.out.println("read node from app: "+n_program.getData());
        System.out.println("                 :parent: "+n_program.getParent());

        Node n_declarations = n_program.setChildren("decl list");
        System.out.println("read node from app :data: "+n_declarations.getData());
        System.out.println("                 :parent: "+n_declarations.getParent().getData());

        this.declaration(n_declarations);

        this.CheckError("BEGIN");

        Node n_statementSequence = n_program.setChildren("stmt list");
        this.statementSequence(n_statementSequence);

        this.CheckError("END");

        System.out.println(":::: Parsing Successful Hamid ::::");

        return n_program;
        //////////////////////////////////////////// writeout PROGRAM treee -----------------
        //////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////
        ///////////output test generator
        /////////////////////////////////////////////////////////////////////////////////


        /*Node iteration = n_program;
        for (int i=0; i<iteration.childrenSize();i++){
            System.out.print("| "+iteration.getChildren(i).getData()+"|");
            System.out.print("                               ");
                    }
        System.out.println();
        iteration = n_program.getChildren(0);
        for (int i=0; i<iteration.childrenSize();i++){
            System.out.print("| "+iteration.getChildren(i).getData()+" |");
                    }
        System.out.print("        ");
        iteration = n_program.getChildren(1);
        for (int i=0; i<iteration.childrenSize();i++){
            System.out.print("| "+iteration.getChildren(i).getData()+" |");
        }
        System.out.println("");
        System.out.print("                                           ");
        Node iteration0= iteration.getChildren(0);
        for (int i=0; i<iteration0.childrenSize();i++){
            System.out.print("| "+iteration0.getChildren(i).getData()+" |");
        }
        System.out.println();
        System.out.print("                                           ");
        Node iteration1= iteration.getChildren(1);
        for (int i=0; i<iteration1.childrenSize();i++){
            System.out.print("| "+iteration1.getChildren(i).getData()+" |");
        }
        System.out.println();
        System.out.print("                                           ");
        Node iteration2= iteration.getChildren(2);
        for (int i=0; i<iteration2.childrenSize();i++){
            System.out.print("| "+iteration2.getChildren(i).getData()+" |");
        }
        System.out.println();
        System.out.print("                                           ");
        Node iteration3= iteration.getChildren(3);
        for (int i=0; i<iteration3.childrenSize();i++){
            System.out.print("| "+iteration3.getChildren(i).getData()+" |");
        }
        System.out.println();
        System.out.print("                                           ");
        Node iteration4= iteration.getChildren(4);
        for (int i=0; i<iteration4.childrenSize();i++){
            System.out.print("| "+iteration4.getChildren(i).getData()+" |");
        }
        System.out.println();
        System.out.print("                                           w\n");
        System.out.print("                                           ");
        Node iteration0w= iteration2.getChildren(0);
        for (int i=0; i<iteration0w.childrenSize();i++){
            System.out.print("| "+iteration0w.getChildren(i).getData()+" |");
        }
        System.out.print("\n                                           ");
        Node iteration1w= iteration2.getChildren(1);
        for (int i=0; i<iteration1w.childrenSize();i++){
            System.out.print("| "+iteration1w.getChildren(i).getData()+" |");
        }
        System.out.print("\n                                           ");
        iteration= iteration0w.getChildren(0);
        for (int i=0; i<iteration.childrenSize();i++){
            System.out.print("| "+iteration.getChildren(i).getData()+" |");
        }*/

    }

    // <declarations> ::= VAR ident AS <type> SC <declarations> | ε
    public void declaration(Node n_parent) {
        System.out.println(":: declaration :"+n_parent.getData());
        if (token.get(lookAheadPossition).equals("VAR")) {
            //System.out.println(":: declaration::if");
            this.CheckError("VAR");

            String identifier = "";
            if (token.get(lookAheadPossition).contains("ident(")) {
                identifier = token.get(lookAheadPossition).substring(6, token.get(lookAheadPossition).length() - 1);
                this.CheckError(token.get(lookAheadPossition));
            } else {
                System.out.println("ERROR: identifier <-token expected at line:" + lookAheadPossition + 1 + " but " + token.get(lookAheadPossition) + " <-detected.");
                System.exit(0);
            }
            this.CheckError("AS");

            String type = token.get(lookAheadPossition);
            this.type();
            type = type.toLowerCase();
            symbolTable.put(identifier, type);// type exist then put into symbol table


            this.CheckError("SC");

            n_parent.setChildren("decl:'"+identifier+"':"+type);

            this.declaration(n_parent);

        }

    }

    // <type> ::= INT | BOOl
    static String type;
    public void type() {
        if(token.get(lookAheadPossition).equals("INT")){
            System.out.println(":: type::if");
            this.CheckError("INT");
            type = "int";
        } else if(token.get(lookAheadPossition).equals("BOOL")){
            System.out.println(":: type::if");
            this.CheckError("BOOL");
            type = "BOOL";
        } else {System.out.println("ERROR: "+type+" <-type expected at line:"+ lookAheadPossition+1+ " but " + token.get(lookAheadPossition)+ " <-detected." );
            System.exit(0);

        }
    }


    // <statementSequence> ::= <statement> SC <statementSequence> | ε
    public void statementSequence(Node n_parent) {
        System.out.println(":: statementSequence::inside: "+n_parent.getData());
        if(token.get(lookAheadPossition).contains("ident(") || token.get(lookAheadPossition).equals("IF") || token.get(lookAheadPossition).equals("WHILE") || token.get(lookAheadPossition).equals("WRITEINT")){
            System.out.println(":: statementSequence::if: "+n_parent.getData());

            this.statement(n_parent);
            this.CheckError("SC");
            this.statementSequence(n_parent);
        }
        //token.get(lookAheadPossition).;/////////////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }


    // <statement> ::= <assignment> | <ifStatement> | <whileStatement>| <writeInt>
    public void statement(Node n_parent) {
        System.out.println(":: statement:"+n_parent.getData());
        if(token.get(lookAheadPossition).contains("ident(")){
            System.out.println(":: statement::if");

            Node n_assignment = n_parent.setChildren(":=");
            this.assignment(n_assignment);
        }
        else if (token.get(lookAheadPossition).equals("IF")) {
            System.out.println(":: statement::if");

            Node n_ifStatement = n_parent.setChildren("if");
            this.ifStatement(n_ifStatement);
        }
        else if (token.get(lookAheadPossition).equals("WHILE")){
            System.out.println(":: statement::if");

            Node n_whileStatement = n_parent.setChildren("while");
            this.whileStatement(n_whileStatement);
        }
        else if (token.get(lookAheadPossition).equals("WRITEINT")){
            System.out.println(":: statement::if");

            Node n_writeInt = n_parent.setChildren("writeInt");
            this.writeInt(n_writeInt);
        }
    }
    // <assignment> ::= ident ASGN <expression> | ident ASGN READINT
    // <assignment> ::= ident ASGN <restAssignment> ====> <restAssignment> :: = <expression> | READINT
     public void assignment(Node n_parent) {
        System.out.println(":: assignment:"+n_parent.getData());
        if(token.get(lookAheadPossition).contains("ident(")){
            System.out.println(":: assignment::if::1");
            String identifier="";
            System.out.println(":: assignment::if::2");
            identifier = token.get(lookAheadPossition).substring(6, token.get(lookAheadPossition).length() - 1);
            System.out.println(":: assignment::if -id "+identifier);
            String type="";
            type = (String) symbolTable.get(identifier);
            type = type.toLowerCase();
            System.out.println(":: assignment::if -type "+type);

            n_parent.setChildren(identifier+":"+type);
            this.CheckError(token.get(lookAheadPossition));
            this.CheckError("ASGN");
            this.restAssignment(n_parent);
        }
    }

    //
    //  <restAssignment>   ::=  <expression> | READINT
    public void restAssignment(Node n_parent){
        if(token.get(lookAheadPossition).contains("ident(")||token.get(lookAheadPossition).contains("num(")||token.get(lookAheadPossition).contains("boollit(")||token.get(lookAheadPossition).equals("LP")){
            System.out.println(":: restAssignment::if"+n_parent.getData());

            this.expression(n_parent);
        }
        else if(token.get(lookAheadPossition).equals("READINT")){
            System.out.println(":: restAssignment::if:"+n_parent.getData());
            n_parent.setChildren("readInt:int");
            this.CheckError("READINT");
        }
        else {
            System.out.println("ERROR: after ' <assignment> ::= ident ASGN '  we expect   '<expression> | READINT' ");
        }
    }

    // <ifStatement> ::= IF <expression> THEN <statementSequence> <els eClause> END
    public void ifStatement(Node n_parent) {
        if(token.get(lookAheadPossition).equals("IF")){   /// not necessary - once i checked in statement////////////////////////
            System.out.println(":: ifStatement::if:parent: "+n_parent.getData());
            this.CheckError("IF");

            this.expression(n_parent);
            this.CheckError("THEN");     
            //this.CheckError("SC");
            Node n_statementSequence = n_parent.setChildren("stmt list");

            this.statementSequence(n_statementSequence);

            this.elseClause(n_parent);

            this.CheckError("END");
            //this.CheckError("SC");
        }

    }

    // <elseClause> ::= ELSE <statementSequence> | ε
     public void elseClause(Node n_parent) {
        if(token.get(lookAheadPossition).equals("ELSE")){
            System.out.println(":: elseClause::if"+n_parent.getData());

            this.CheckError("ELSE");
            Node n_statementSequence = n_parent.setChildren("stmt list");
            this.statementSequence(n_statementSequence);
        }
    }


    // <whileStatement> ::= WHILE <expression> DO <statementSequence> END
    public void whileStatement(Node n_parent) {
        if(token.get(lookAheadPossition).equals("WHILE")){
            System.out.println(":: whileStatement::if"+n_parent.getData());
            this.CheckError("WHILE");

            this.expression(n_parent);
            this.CheckError("DO");
            Node n_statementSequence = n_parent.setChildren("stmt list");
            this.statementSequence(n_statementSequence);
            this.CheckError("END");

        }
    }


    // <writeInt> ::= WRITEINT <expression>
    public void writeInt(Node n_parent) {
        if(token.get(lookAheadPossition).equals("WRITEINT")){
            System.out.println(":: writeInt::if"+n_parent.getData());
            this.CheckError("WRITEINT");

            this.expression(n_parent);
        }
    }


    // <expression> ::= <simpleExpression> | <simpleExpression> COMPARE <expression>
    //<expression> ::= <simpleExpression> <restExpression> ==>   <restExpression> ::= COMPARE <expression> |e
    public void expression(Node n_parent) {
        if(token.get(lookAheadPossition).contains("ident(")||token.get(lookAheadPossition).contains("num(")||token.get(lookAheadPossition).contains("boollit(")||token.get(lookAheadPossition).equals("LP")){
            System.out.println(":: expression::if- "+n_parent.getData());

            Node makeown = new Node ("makeown");
            Node n_factor = this.simpleExpression(makeown);

            //compNode = expressionNode.addChild("Compare");
            //Node n_connect =
                               this.restExpression(n_parent,n_factor);
            //n_parent.setNodeChild(n_connect);
        }
    }


    // <restExpression> --> COMPARE <expression> |e
    public void restExpression(Node n_parent,Node n_factor){
        String compare="";
        if(token.get(lookAheadPossition).contains("COMPARE")){
            System.out.println(":: restExpression::if:compare parent: "+n_parent.getData());

            compare = token.get(lookAheadPossition).substring(8, token.get(lookAheadPossition).length() - 1);
            Node n_compare = n_parent.setChildren(compare+":bool");
            n_compare.setNodeChild(n_factor);
            this.CheckError(token.get(lookAheadPossition));
            //n_parent.setNodeChild(n_compare);
            this.expression(n_compare);

            //return(n_compare);
        }else{
            n_parent.setNodeChild(n_factor);
            //return (n_factor);
        }
    }


    // <simpleExpression> ::= <term> ADDITIVE <simpleExpression> | <term>
    // <simpleExpression> ::= <term> <restSimpleExpression>   ====>    <restSimpleExpression> ::= ADDITIVE <simpleExpression> |e
    public Node simpleExpression(Node n_parent) {

        if(token.get(lookAheadPossition).contains("ident(")||token.get(lookAheadPossition).contains("num(")||token.get(lookAheadPossition).contains("boollit(")||token.get(lookAheadPossition).equals("LP")){
            System.out.println(":: simpleExpression::if "+n_parent.getData());
            Node makeown = new Node ("makeown");
            Node n_factor = this.term(makeown);
            //if (!(n_parent==null)) {n_parent.setNodeChild(n_factor); }//simpleExpression(n_factor); }
            //if (n_parent.getData().contains("-")) {n_parent.setNodeChild(n_factor); simpleExpression(n_factor); }  /////////************add
            //addNode = simpleExpressionNode.addChild("Add");
            return(this.restSimpleExpression(n_parent,n_factor ));
        }
        return(null);
    }


    // <restSimpleExpression> ::= ADDITIVE <simpleExpression> |e
    public Node restSimpleExpression(Node n_parent, Node n_factor){
        String additive="";
        if(token.get(lookAheadPossition).contains("ADDITIVE(")){
            System.out.println(":: restSimpleExpression::if "+n_parent.getData());

            additive = token.get(lookAheadPossition).substring(9, token.get(lookAheadPossition).length() - 1);
            Node n_additive = new Node (additive+":int");
            n_additive.setNodeChild(n_factor);
            //if (!(n_parent.getData().equals("makeown"))) {n_parent.setNodeChild(n_additive); }
            //n_parent.setNodeChild(n_additive);///
            this.CheckError(token.get(lookAheadPossition));

            Node n_return = this.simpleExpression( n_additive );
            System.out.println(":: restSimple:n_return:"+n_return.getData());
            n_additive.setNodeChild(n_return);
            return ( n_additive );
        }else{

            return (n_factor);
        }
    }

    // <term> ::= <factor> MULTIPLICATIVE <term> | <factor>
    // <term> ::= <factor> <restTerm>    ===>    <restTerm> ::= MULTIPLICATIVE <term> | e
    public Node term(Node n_parent) {

        if (token.get(lookAheadPossition).contains("ident(") || token.get(lookAheadPossition).contains("num(") || token.get(lookAheadPossition).contains("boollit(") || token.get(lookAheadPossition).equals("LP")) {
            System.out.println(":: term:if:" + n_parent.getData());
            Node n_factor = this.factor(n_parent);
            //if (n_parent.getData().contains("*")) {n_parent.setNodeChild(n_factor); }
            //if (n_parent.getData().contains("DIV")) {n_parent.setNodeChild(n_factor); }
            //if (n_parent.getData().contains("MOD")) {n_parent.setNodeChild(n_factor); }
            //if (!(n_parent.getData().equals("makeown"))) {n_parent.setNodeChild(n_factor); }
            return (this.restTerm(n_parent, n_factor));
        } else {

            return (null);
        }
    }

    //  <restTerm> ::= MULTIPLICATIVE <term> | e
    public Node restTerm(Node n_parent, Node n_factor){
        String multiplicative="";
        if(token.get(lookAheadPossition).contains("MULTIPLICATIVE(")){
            System.out.println(":: restTerm:if:"+n_parent.getData());
            multiplicative = token.get(lookAheadPossition).substring(15, token.get(lookAheadPossition).length() - 1);
            Node n_multiplicative = new Node (multiplicative+":int");
            n_multiplicative.setNodeChild(n_factor);
            //if (!(n_parent.getData().equals("makeown"))) {n_parent.setNodeChild(n_multiplicative); }
            this.CheckError(token.get(lookAheadPossition));


            Node n_return = this.term(n_multiplicative);
            System.out.println(":: restTerm:n_return:"+n_return.getData());
            n_multiplicative.setNodeChild(n_return);
            return ( n_multiplicative );
        }else {
            //n_parent.setNodeChild(n_factor);
            //if (!(n_parent.getData().equals("makeown"))) {n_parent.setNodeChild(n_factor); }
            return (n_factor);
        }

    }
    // <factor> ::= ident | num | boollit | LP <simpleExpression> RP
    public Node factor(Node n_parent) {
        String identifier="";
        String type="";
        if(token.get(lookAheadPossition).contains("ident(")){
            System.out.println(":: factor:if:");
            identifier = token.get(lookAheadPossition).substring(6, token.get(lookAheadPossition).length() - 1);
            type = (String) symbolTable.get(identifier);
            type=type.toLowerCase();

            this.CheckError(token.get(lookAheadPossition));
            Node n_factor =new Node(identifier+":"+type);

            return n_factor;
        }
        else if(token.get(lookAheadPossition).contains("num(")){
            System.out.println(":: factor:if");
            identifier = token.get(lookAheadPossition).substring(4, token.get(lookAheadPossition).length() - 1);
            type = "int";

            this.CheckError(token.get(lookAheadPossition));
            Node n_factor =new Node(identifier+":"+type);
            return n_factor;
        }
        else if(token.get(lookAheadPossition).contains("boollit(")){
            System.out.println(":: factor:if");
            identifier = token.get(lookAheadPossition).substring(8, token.get(lookAheadPossition).length() - 1);

            System.out.println(":: factor:if:boollit "+identifier);
            if (identifier.contentEquals("true") || identifier.contentEquals("false") ){ type="bool";}else { type = (String) symbolTable.get(identifier); type = type.toLowerCase();}
            System.out.println(":: factor:if:boolint"+type);
            this.CheckError(token.get(lookAheadPossition));
            Node n_factor =new Node(identifier+":"+type);
            return n_factor;
        }
        else if(token.get(lookAheadPossition).equals("LP")){
          System.out.println(":: factor:if:: LP");
//
           this.CheckError("LP");
//            Node n_LP_RP = n_parent.setChildren("LP:RP");
           //this.simpleExpression(n_parent );
          Node n_factor = this.simpleExpression(n_parent);
           this.CheckError("RP");
            return n_factor;
         }
        return(null);
    }





}



