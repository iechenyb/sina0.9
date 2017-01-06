package com.cyb.shejimoshi;

import java.util.Enumeration;
import java.util.Vector;

import net.sf.json.JSONObject;

class TreeNode {  
    private String name;  
    private TreeNode parent;  
    private Vector<TreeNode> children = new Vector<TreeNode>();  
    public TreeNode(String name){  
        this.name = name;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public TreeNode getParent() {  
        return parent;  
    }  
    public void setParent(TreeNode parent) {  
        this.parent = parent;  
    }  
    //添加孩子节点  
    public void add(TreeNode node){  
        children.add(node);  
    }  
    //删除孩子节点  
    public void remove(TreeNode node){  
        children.remove(node);  
    }  
    //取得孩子节点  
    public Enumeration<TreeNode> getChildren(){  
        return children.elements();  
    }  
}  
public class 组合模式 {
	TreeNode root = null;  
    public 组合模式(String name) {  
        root = new TreeNode(name);  
    }  
    public static void displayChilds(TreeNode treeNode){
    	 Enumeration<TreeNode> childs = treeNode.getChildren();
    	 if(childs.hasMoreElements()){
    		  System.out.println(treeNode.getName()+","+treeNode.getParent().getName());
			 while(childs.hasMoreElements()){
				 TreeNode node = childs.nextElement();
				 displayChilds(node);
			 }
    	 }else{
    		 System.out.println(treeNode.getName()+","+treeNode.getParent().getName());//树的根节点
    		 return;
    	 }
    }
    public static void main(String[] args) {  
    	组合模式 tree = new 组合模式("A");  
        TreeNode nodeB = new TreeNode("B");  
        TreeNode nodeC = new TreeNode("C");  
        TreeNode nodeD = new TreeNode("D");  
        TreeNode nodeE = new TreeNode("E");  
        TreeNode nodeF = new TreeNode("F");  
        nodeD.add(nodeE);
        nodeD.add(nodeF);
        nodeB.add(nodeC);  
        tree.root.add(nodeD);
        tree.root.add(nodeB); 
        tree.root.setParent(new TreeNode("root"));
        nodeB.setParent(tree.root);
        nodeD.setParent(tree.root);
        nodeE.setParent(nodeD);
        nodeF.setParent(nodeD);
        nodeC.setParent(nodeB);
        nodeB.remove(nodeC);
        System.out.println("build the tree finished!");  
        // System.out.println(JSONObject.fromObject(tree.root));
        displayChilds(tree.root);
    }  
}
