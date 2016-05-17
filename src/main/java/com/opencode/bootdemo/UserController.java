package com.opencode.bootdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
	
	@Autowired UserDao userdao;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers(){
		return (List<User>) userdao.findAll();
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User newUser){
     if(newUser != null){
       if( userdao.save(newUser) == null){
    	    return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
       }else{
    	   return new ResponseEntity<Void>(HttpStatus.CREATED);
       }
     }else{
    	 return new ResponseEntity<Void>(HttpStatus.CONFLICT);
     }
	}
	
	@RequestMapping(value="/users/{userName}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable String userName){
		User searchedUser = userdao.findByUserName(userName);
		if(searchedUser != null){
			userdao.delete(searchedUser);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			
		}
		
	}

}
