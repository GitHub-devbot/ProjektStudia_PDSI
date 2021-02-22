package com.jsfcourse.band;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;

import jsf.projektDAO.bandDAO;
import jsf.projekt.Band;
import jsf.projektDAO.userDAO;
import jsf.projekt.User;

@Named
@RequestScoped
public class BandListBB {
	private static final String PAGE_BAND_EDIT = "/pages/user/bandEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	bandDAO bandDAO;
	@EJB
	userDAO userDAO;
		
	public String getbandName() {
		return name;
	}

	public void setbandName(String name) {
		this.name = name;
	}

	public List<Band> getFullList(){
		return bandDAO.getFullList();
	}

	public List<Band> getList(){
		List<Band> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = bandDAO.getList(searchParams);
		
		return list;
	}
	
	public String newBand(Integer uzytkownik){
		Band band = new Band();

		flash.put("band", band);
		band.setUser(userDAO.find(uzytkownik));
		return PAGE_BAND_EDIT;
	}

	public String editBand(Integer uzytkownik, Band band){
		if(band.getUser().getUserId().toString().equals(uzytkownik.toString())|| userDAO.find(uzytkownik).getUserRole().equals("admin")) {
			flash.put("band", band);	
	//		band.setBandName(band.getUser().getUserId().toString());
	//		band.setBandName(uzytkownik.toString());
			return PAGE_BAND_EDIT;				
		}else {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nie masz wystarczaj¹cych uprawnieñ", null));
			return PAGE_STAY_AT_THE_SAME;		
		}
		
	}

	public String deleteBand(Integer uzytkownik, Band band){
		if(band.getUser().getUserId().toString().equals(uzytkownik.toString())|| userDAO.find(uzytkownik).getUserRole().equals("admin")) {
			bandDAO.remove(band);
			return PAGE_STAY_AT_THE_SAME;	
		}else {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nie masz wystarczaj¹cych uprawnieñ", null));
			return PAGE_STAY_AT_THE_SAME;		
		}
			
	}
}
