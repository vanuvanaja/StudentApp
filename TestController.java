package com.mmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmp.Entity.ContactEntity;
import com.mmp.dto.ContactDto;
import com.mmp.service.ContactService;

@RestController
public class TestController {
	
	@Autowired
	 ContactService contactService;
	
	//create
	@RequestMapping(value="/contact/create",method=RequestMethod.POST)
    public ContactDto createTest(@RequestBody ContactDto dto) throws Exception{
	return contactService.create(dto);
}



	//Read
	@RequestMapping(value="/contact/get",method=RequestMethod.GET)
	public List<ContactDto> getTest() throws Exception{
		return contactService.get();
}
	
	//Read By Id
	@RequestMapping(value="/contact/DataById",method=RequestMethod.GET)
	public ContactDto getbyid(@RequestParam(value="uid",required=true) String uid)throws Exception{
		return contactService.getById(uid);
	}
	
	

	//Update
		@RequestMapping(value="/contact/update",method=RequestMethod.POST)
		public ContactDto updateTest(@RequestBody ContactDto dto) throws Exception{
			return contactService.create(dto);
		}

	//Delete

		//Read By Id
		@RequestMapping(value="/contact/deleteById",method=RequestMethod.GET)
		public String deletebyUid(@RequestParam(value="uid",required=true) String uid)throws Exception{
			 return contactService.deleteByUid(uid);
			 
		}

}
