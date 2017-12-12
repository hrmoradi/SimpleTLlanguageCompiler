public class h_typeCheckingClass {
    Node n_program;
    public h_typeCheckingClass(Node n_node){
        this.n_program =n_node;
    }



    public  Node starttype (Node n_program){
       h_typeChecking(n_program);
        return n_program;
    }

    public Node h_typeChecking(Node node) {
        System.out.println(":: typechecking: " + node.getData());

        if (node.childrenSize() > 0) {
            if (node.childrenSize() > 1) {
                System.out.println(":: typechecking: " + node.getData() + " " + node.childrenSize());
                if (node.getChildren(0).getData().contains(":") && node.getChildren(1).getData().contains(":")) {
                    String[] left = node.getChildren(0).getData().split(":");
                    String[] right = node.getChildren(1).getData().split(":");

                    if (!left[left.length-1].equals(right[right.length-1])) {
                         if (!node.getData().contains("decl list") && !node.getData().contains("stmt list")) {
                            node.setData("red " + node.getData());
                            System.out.println(":: typechecking: " + "red " + node.getData());
                        }
                    }

                }
            }
            for (int i = 0; i < node.childrenSize(); i++) {
                h_typeChecking(node.getChildren(i));
            }
           }else{
                return null;
            }
        return null;
    }






    }

