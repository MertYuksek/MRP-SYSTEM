package ProductOP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProductTree {

    public ProductNode root;

    public ProductTree(){

    }

    public void add(int parentId, ProductNode productNode){

        if(root==null){
            System.out.println("Root eklendi");
            root = productNode;
            return;
        }

        ProductNode node = find(parentId,root);

        if(node==null){
            System.out.println("No parent");
        }
        else{
            productNode.setParent(node);
            node.childs.add(productNode);
        }
    }

    public void remove(ProductNode productNode){

    }

    public ProductNode find(int id, ProductNode node) {

        if(root==null){
            System.out.println("null");
            return null;
        }

        Queue<ProductNode> queue = new LinkedList<>();

        int j = 0;
        while (node!=null){

            if(node.getId()==id){
                return node;
            }

            for(int i=0; i<node.childs.size(); i++){

                if(node.childs.get(i).getId()==id){
                    return node.childs.get(i);
                }

                queue.add(node.childs.get(i));
                j++;
            }

            if((j)==node.childs.size()) {

                node = queue.poll();
                j=0;
            }
        }

        return null;
    }

    public ProductNode get(int a){

        ArrayList<ProductNode> list = getList();
        return a<list.size()?list.get(a):null;
    }

    public ArrayList<ProductNode> getList(){

        ProductNode node = root;
        Queue<ProductNode> queue = new LinkedList<>();
        ArrayList<ProductNode> list = new ArrayList<>();

        int j=0;
        list.add(node);
        while (node!=null){

            for(int i=0; i<node.childs.size(); i++){

                if(!duplicateControl(list,node.childs.get(i))){
                    list.add(node.childs.get(i));
                }

                queue.add(node.childs.get(i));
                j++;
            }

            if((j)==node.childs.size()) {
                node = queue.poll();
                j=0;
            }

        }
        return list;
    }

    public ArrayList<ProductNode> getDuplicateList(){

        ProductNode node = root;
        Queue<ProductNode> queue = new LinkedList<>();
        ArrayList<ProductNode> list = new ArrayList<>();

        int j=0;
        list.add(node);
        while (node!=null){

            for(int i=0; i<node.childs.size(); i++){
                list.add(node.childs.get(i));
                queue.add(node.childs.get(i));
                j++;
            }

            if((j)==node.childs.size()) {
                node = queue.poll();
                j=0;
            }

        }
        return list;
    }

    public ArrayList<ProductNode> getDuplicateNodes(ProductNode node){

        ArrayList<ProductNode> list =new ArrayList<>();

        for(int i=0; i<getDuplicateList().size(); i++){
            if(getDuplicateList().get(i).getId()==node.getId()){
                list.add(getDuplicateList().get(i));
            }
        }

        return list;
    }

    private boolean duplicateControl(ArrayList<ProductNode> list, ProductNode node){

        for(int i=0; i<list.size(); i++){
            if(list.get(i).getId()==node.getId()){
                return true;
            }
        }
        return false;
    }

}
