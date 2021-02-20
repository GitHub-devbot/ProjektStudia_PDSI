package com.jsfcourse.album;

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

import jsf.projektDAO.albumDAO;
import jsf.projektDAO.bandDAO;
import jsf.projekt.Album;
import jsf.projekt.Band;
import jsf.projekt.User;
import jsf.projektDAO.userDAO;

@Named
@RequestScoped
public class AlbumListBB {
	private static final String PAGE_ALBUM_EDIT = "/pages/user/albumEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	albumDAO albumDAO;
	@EJB
	bandDAO bandDAO;
	@EJB
	userDAO userDAO;
		
	public String getalbumName() {
		return name;
	}

	public void setalbumName(String name) {
		this.name = name;
	}

	public List<Album> getFullList(){
		return albumDAO.getFullList();
	}

	public List<Album> getList(){
		List<Album> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = albumDAO.getList(searchParams);
		
		return list;
	}
	
	public String newAlbum(Integer uzytkownik, Band zespol){
		Album album = new Album();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("album", album);
		album.setBand(zespol);
		album.setUser(userDAO.find(uzytkownik));
		return PAGE_ALBUM_EDIT;
	}

	public String editAlbum(Album album){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("album", album);
		
		return PAGE_ALBUM_EDIT;
	}
	
	public String addAlbum(Band band){

		flash.put("band", band);
		
		return PAGE_ALBUM_EDIT;
	}

	public String deleteAlbum(Album album){
		albumDAO.remove(album);
		return PAGE_STAY_AT_THE_SAME;
	}
}
