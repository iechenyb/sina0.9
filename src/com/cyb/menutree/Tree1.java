package com.cyb.menutree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;

public class Tree1 {
	public static void main(String[] args) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode n = new TreeNode();
		n.setNodeid("01");
		n.setSuperid("0");
		list.add(n);

		n = new TreeNode();
		n.setNodeid("02");
		n.setSuperid("0");
		list.add(n);

		n = new TreeNode();
		n.setNodeid("0101");
		n.setSuperid("01");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("0102");
		n.setSuperid("01");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("0103");
		n.setSuperid("01");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("0201");
		n.setSuperid("02");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("0202");
		n.setSuperid("02");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("020201");
		n.setSuperid("0202");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("010101");
		n.setSuperid("0101");
		list.add(n);
		n = new TreeNode();
		n.setNodeid("01010101");
		n.setSuperid("010101");
		list.add(n);

		// Collections.sort(list);

		DefaultMutableTreeNode tree = createTree(list);

		showTree(tree);
	}

	public static void showTree(DefaultMutableTreeNode tree) {
		if (tree != null) {
			Stack<javax.swing.tree.TreeNode> stack = new Stack<javax.swing.tree.TreeNode>();
			javax.swing.tree.TreeNode t = null;
			stack.push(tree);
			while (!stack.empty()) {
				javax.swing.tree.TreeNode tpop = stack.pop();
				System.out.println(tpop);
				int count = tpop.getChildCount();
				if (count > 0) {
					for (int i = 0; i < count; i++) {
						stack.push(tpop.getChildAt(i));
					}
				}
			}
		}
	}

	public static final DefaultMutableTreeNode createTree(List<TreeNode> list) {
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode("root");
		Map<String, DefaultMutableTreeNode> map = getTreeNodeMap(list);
		while (list.size() > 0) {
			TreeNode n = (TreeNode) list.get(list.size() - 1);
			if (map.get(n.getSuperid()) == null) {
				tree.add(map.get(n.getNodeid()));
				map.remove(n.getNodeid());
				list.remove(n);
			} else {
				DefaultMutableTreeNode treeNode = map.get(n.getSuperid());
				treeNode.add(map.get(n.getNodeid()));
				map.remove(n.getNodeid());
				list.remove(n);
			}
		}
		return tree;
	}

	public static final Map<String, DefaultMutableTreeNode> getTreeNodeMap(
			List<TreeNode> nodes) {
		Map<String, DefaultMutableTreeNode> treeNodeMap = new HashMap<String, DefaultMutableTreeNode>();
		if (nodes == null) {
			return null;
		}
		Iterator I = nodes.iterator();
		while (I.hasNext()) {
			TreeNode n = (TreeNode) I.next();
			treeNodeMap.put(n.getNodeid(),
					new DefaultMutableTreeNode(n.getNodeid()));
		}
		treeNodeMap.put("root", new DefaultMutableTreeNode("root"));
		return treeNodeMap;
	}

}
