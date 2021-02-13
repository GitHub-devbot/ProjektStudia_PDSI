package com.jsfcourse.band;

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

import jsf.projektDAO.bandDAO;
import jsf.projekt.Band;

@Named
@RequestScoped
public class BandListBB {
	private static final String PAGE_BAND_EDIT = "bandEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	bandDAO bandDAO;
		
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
	
	public String newBand(){
		Band band = new Band();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("band", band);
		
		return PAGE_BAND_EDIT;
	}

	public String editBand(Band band){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("band", band);
		
		return PAGE_BAND_EDIT;
	}

	public String deleteBand(Band band){
		bandDAO.remove(band);
		return PAGE_STAY_AT_THE_SAME;
	}
}
