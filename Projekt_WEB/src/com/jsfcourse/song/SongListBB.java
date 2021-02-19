package com.jsfcourse.song;

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

import jsf.projektDAO.songDAO;
import jsf.projekt.Song;
import jsf.projekt.Album;

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

	public String newSong(){
		Song song = new Song();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("song", song);
		
		return PAGE_SONG_EDIT;
	}

	public String editSong(Song song){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("song", song);
		
		return PAGE_SONG_EDIT;
	}
	
	public String addSong(Album album){

		flash.put("album", album);
		
		return PAGE_SONG_EDIT;
	}

	public String deleteSong(Song song){
		songDAO.remove(song);
		return PAGE_STAY_AT_THE_SAME;
	}
}
