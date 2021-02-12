package com.jsfcourse.album;

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

import jsf.projektDAO.albumDAO;
import jsf.projekt.Album;

@Named
@ViewScoped
public class AlbumEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ALBUM_LIST = "albumList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Album album = new Album();
	private Album loaded = null;

	@EJB
	albumDAO albumDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Album getAlbum() {
		return album;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Album) flash.get("album");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			album = loaded;
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
			if (album.getAlbumId() == null) {
				// new record
				albumDAO.create(album);
			} else {
				// existing record
				albumDAO.merge(album);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ALBUM_LIST;
	}

}
