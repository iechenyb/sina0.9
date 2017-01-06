package com.cyb.menutree;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;

import com.cyb.file.FileUtils;



public class MenuTree {
	public static Branch normalOrganization() {  
		String path = System.getProperty("user.dir")+File.separator+"tree.txt";
		StringBuffer content = FileUtils.readFileByLines(path);
		String[] nodes = content.toString().split(",");
        List<Branch> branchs = new ArrayList<Branch>();
        for(int i=1;i<nodes.length;i++){
        	Branch branch = new Branch();  
            branch.setBranchName(nodes[i].split("#")[2]);  
            branch.setBranchNo(Integer.valueOf(nodes[i].split("#")[1]));  
            branch.setUpBranchNo(Integer.valueOf(nodes[i].split("#")[3]));  
            branch.setBranchType(Integer.valueOf(nodes[i].split("#")[4]));
            branchs.add(branch);  
        }
        System.out.println(content);  
        // 利用得到的branchs 构造一棵树结构  
        return buildBranchTree(branchs);  
    }  
  
    private static Branch buildBranchTree(List<Branch> branchs) {  
        Branch topBranch = null;  
        for (Iterator iterator = branchs.iterator(); iterator.hasNext();) {  
            Branch branch = (Branch) iterator.next();  
            if (0 == branch.getUpBranchNo()) {  
                topBranch = branch;  
                topBranch.setBranchs(new ArrayList<Branch>());  
                // branchs.remove(branch); // 删除该数据， 减少下次的查询时间  
                //iterator.remove();  
                buildTree(topBranch, branchs);  
            }  
            break;  
        }  
        return topBranch;  
    }  
  
    private static void buildTree(Branch parent, List<Branch> branchs) {  
        for (Iterator iterator = branchs.iterator(); iterator.hasNext();) {  
            Branch branch = (Branch) iterator.next();  
            if (branch.getUpBranchNo() == parent.getBranchNo()) {  
                if (null == parent.getBranchs())  
                    parent.setBranchs(new ArrayList<Branch>());  
                parent.getBranchs().add(branch);  
               // iterator.remove();  
            }  
        }  
        if (branchs.size() == 0)  
            return;  
        if (null != parent.getBranchs()) {  
            for (Branch branch2 : parent.getBranchs()) {  
                if (1 == branch2.getBranchType()) {  
                    buildTree(branch2, branchs);  
                }  
            }  
        }    
    }  
  
    public static void main(String[] args) {
		/*TreeNode node_1 = new TreeNode();
		node_1.setName("node_1");
		Vector<TreeNode> list = new Vector<TreeNode>();
		TreeNode node1_1 = new TreeNode();
		node1_1.setName("node1_1");
		TreeNode node1_2 = new TreeNode();
		node1_2.setName("node1_2");
		list.add(node1_1);
		list.add(node1_2);
		node_1.setList(list);
		JSONObject a = JSONObject.fromObject(node_1);  */
    	Branch root = normalOrganization();
    	System.out.println(root.getBranchs());
    	//JSONArray.fromObject(root);
	}
}
