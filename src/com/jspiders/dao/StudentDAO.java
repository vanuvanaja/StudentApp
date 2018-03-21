package com.jspiders.dao;

import java.util.List;

import com.jspiders.dto.StdentBean;

public interface StudentDAO {
	public StdentBean authenticate(int regno, String password);
	
	public boolean createprofile(StdentBean data);
	
	public StdentBean getstudentdeatails(int regno);
	
	public void UbdateStudentdetails(StdentBean data);
	
	
	public boolean changepassword(int regno,String oldpassword,String newpassword);
	
	public List<StdentBean> getallstudentdetails(int from ,int to);
	
	public boolean deletestudentservlet(int regno);
	
}
