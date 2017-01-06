package com.cyb.menutree;

import java.util.List;
public class Branch {
	  private int branchNo;  
	  private String branchName;   
	  private int upBranchNo ;  
	  private List<Branch> branchs;
	  private int branchType;//1 root 0 not root
	public int getBranchType() {
		return branchType;
	}
	public void setBranchType(int branchType) {
		this.branchType = branchType;
	}
	public int getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public int getUpBranchNo() {
		return upBranchNo;
	}
	public void setUpBranchNo(int upBranchNo) {
		this.upBranchNo = upBranchNo;
	}
	public List<Branch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}
	@Override
	public String toString() {
		return this.branchName+","+this.branchNo;
	}
}
