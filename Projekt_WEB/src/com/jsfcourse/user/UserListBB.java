package com.jsfcourse.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import jsf.projektDAO.userDAO;
import jsf.projekt.User;

@Named
@RequestScoped
public class UserListBB {
	private static final String PAGE_USER_EDIT = "/pages/admin/userEdit?faces-redirect=true";
	private static final String PAGE_USER_REGISTER = "/pages/public/userNew?faces-redirect=true";	
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	userDAO userDAO;
		
	public String getuserLogin() {
		return login;
	}

	public void setuserLogin(String login) {
		this.login = login;
	}

	public List<User> getFullList(){
		return userDAO.getFullList();
	}

	public List<User> getList(){
		List<User> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (login != null && login.length() > 0){
			searchParams.put("login", login);
		}
		
		//2. Get list
		list = userDAO.getList(searchParams);
		
		return list;
	}

	public String newUser(){
		User user = new User();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);

		//2. Pass object through flash	
		flash.put("user", user);
		user.setUserRole("user");
		return PAGE_USER_EDIT;
	}
	
	public String newUserRegister(){
		User user = new User();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);

		//2. Pass object through flash	
		flash.put("user", user);
		user.setUserRole("user");
		return PAGE_USER_REGISTER;
	}

	public String editUser(User user){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("user", user);
		
		return PAGE_USER_EDIT;
	}

	public String deleteUser(User user){
		userDAO.remove(user);
		return PAGE_STAY_AT_THE_SAME;
	}
}
