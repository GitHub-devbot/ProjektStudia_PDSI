package com.jsfcourse.song;

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

import jsf.projektDAO.songDAO;
import jsf.projektDAO.albumDAO;
import jsf.projektDAO.userDAO;
import jsf.projekt.Song;
import jsf.projekt.Album;
import jsf.projekt.Band;
import jsf.projekt.User;

@Named
@RequestScoped
public class SongListBB {
	private static final String PAGE_SONG_EDIT = "/pages/user/songEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	songDAO songDAO;
	@EJB
	albumDAO albumDAO;
	@EJB
	userDAO userDAO;
		
	public String getsongName() {
		return name;
	}

	public void setsongName(String name) {
		this.name = name;
	}

	public List<Song> getFullList(){
		return songDAO.getFullList();
	}

	public List<Song> getList(){
		List<Song> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = songDAO.getList(searchParams);
		
		return list;
	}

	public String newSong(Integer uzytkownik, Album albumek){
		Song song = new Song();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("song", song);
		song.setAlbum2(albumek);
		song.setBand(albumek.getBand());
		song.setUser1(userDAO.find(uzytkownik));
		return PAGE_SONG_EDIT;
	}

	public String editSong(Integer uzytkownik, Song song){
		if(song.getUser1().getUserId().toString().equals(uzytkownik.toString())|| userDAO.find(uzytkownik).getUserRole().equals("admin")) {
		flash.put("song", song);		
		return PAGE_SONG_EDIT;		
	}else {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Nie masz wystarczaj¹cych uprawnieñ", null));
	return PAGE_STAY_AT_THE_SAME;
}
	}
	
	public String addSong(Album album){

		flash.put("album", album);
		
		return PAGE_SONG_EDIT;
	}

	public String deleteSong(Integer uzytkownik, Song song){
		if(song.getUser1().getUserId().toString().equals(uzytkownik.toString())|| userDAO.find(uzytkownik).getUserRole().equals("admin")) {
			songDAO.remove(song);
			return PAGE_STAY_AT_THE_SAME;	
		}else {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nie masz wystarczaj¹cych uprawnieñ", null));
		return PAGE_STAY_AT_THE_SAME;
	}
	}
}
