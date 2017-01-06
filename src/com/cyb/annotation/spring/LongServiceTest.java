package com.cyb.annotation.spring;


@AnnotationService 
public class LongServiceTest {
	private IUser userdao;  
	public IUser getUserdao1() {
		return userdao1;
	}

	private IUser userdao1;  
	  
    public IUser getUserdao() {  
        return userdao;  
    }  
  
    @AnnotationIOP(className = "ChineseUserImpl")  
    public void setUserdao(IUser userdao) {  
        this.userdao = userdao;  
    }  
    
    @AnnotationIOP(className = "EnglishUserImpl")  
    public void setUserdao1(IUser userdao1) {  
        this.userdao1 = userdao1;  
    } 
  
    public void chineaselogin() {  
        userdao.login();  
    } 
    public void englishlogin() {  
        userdao1.login();  
    }  
}
