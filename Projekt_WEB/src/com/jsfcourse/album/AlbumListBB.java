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
import jsf.projekt.Album;

@Named
@RequestScoped
public class AlbumListBB {
	private static final String PAGE_ALBUM_EDIT = "albumEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	albumDAO albumDAO;
		
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

	public String newAlbum(){
		Album album = new Album();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("album", album);
		
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

	public String deleteAlbum(Album album){
		albumDAO.remove(album);
		return PAGE_STAY_AT_THE_SAME;
	}
}
