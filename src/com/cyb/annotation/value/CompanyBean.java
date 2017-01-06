package com.cyb.annotation.value;

public class CompanyBean {
	    @Hidden(true)  
	    @Header("公司ID")  
	    private int companyId;  
	    @Header("公司编码")   
	    private String member;   
	    @Header("公司名称")   
	    private String companyName;
		public int getCompanyId() {
			return companyId;
		}
		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}
		public String getMember() {
			return member;
		}
		public void setMember(String member) {
			this.member = member;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}  
}
