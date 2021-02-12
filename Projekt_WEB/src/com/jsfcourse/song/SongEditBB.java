package com.jsfcourse.song;

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

import jsf.projektDAO.songDAO;
import jsf.projekt.Song;

@Named
@ViewScoped
public class SongEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_SONG_LIST = "songList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Song song = new Song();
	private Song loaded = null;

	@EJB
	songDAO songDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Song getSong() {
		return song;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Song) flash.get("song");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			song = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bledne uzycie systemu", null));
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
			if (song.getSongId() == null) {
				// new record
				songDAO.create(song);
			} else {
				// existing record
				songDAO.merge(song);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_SONG_LIST;
	}

}
