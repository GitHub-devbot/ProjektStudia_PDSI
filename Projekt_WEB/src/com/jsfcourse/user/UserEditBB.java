package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import jsf.projektDAO.userDAO;
import jsf.projekt.User;

@Named
@ViewScoped
public class UserEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "/pages/admin/userList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private User user = new User();
	private User loaded = null;

	@EJB
	userDAO userDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public User getUser() {
		return user;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (User) flash.get("user");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			user = loaded;
			// session.removeAttribute("person");
		} else {
		//	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bledne uzycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (user.getUserId() == null) {
				// new record
				userDAO.create(user);
			} else {
				// existing record
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		return PAGE_PERSON_LIST;
	}
	
	public String saveDataNew(String login) {		
		User user2 = new User();
		for(int i=0;i<100; i++) {
		user2 = userDAO.find(i);
		if(user2!=null) {
		if(login.equals(user2.getUserLogin()) ) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"U�ytkownik z takim loginem ju� istnieje", null));
			return PAGE_STAY_AT_THE_SAME;		
		}
		}
		}
		
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (user.getUserId() == null) {
				// new record
				userDAO.create(user);
			} else {
				// existing record
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		return PAGE_PERSON_LIST;
	}	
	
	
	public String returnto() {
		return PAGE_PERSON_LIST;	
	}
	
}
