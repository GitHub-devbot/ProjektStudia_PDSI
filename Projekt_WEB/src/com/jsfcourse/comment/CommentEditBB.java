package com.jsfcourse.comment;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import jsf.projektDAO.commentDAO;
import jsf.projektDAO.userDAO;
import jsf.projekt.Comment;
import jsf.projekt.Band;
import jsf.projekt.Album;
import jsf.projekt.Song;
import jsf.projekt.User;


@Named
@ViewScoped
public class CommentEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_COMMENT_LIST = "/pages/user/commentList?faces-redirect=true";
	private static final String PAGE_COMMENT_BAND = "/pages/user/commentsBand?faces-redirect=true";
	private static final String PAGE_COMMENT_ALBUM = "/pages/user/commentsAlbum?faces-redirect=true";
	private static final String PAGE_COMMENT_SONG = "/pages/user/commentsSong?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Comment comment = new Comment();
	private Comment loaded = null;
	
	@EJB
	commentDAO commentDAO;
	@EJB
	userDAO userDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Comment getComment() {
		return comment;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Comment) flash.get("comment");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			comment = loaded;
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
			if (comment.getCommentId() == null) {
				// new record
				commentDAO.create(comment);
			} else {
				// existing record
				commentDAO.merge(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
			
			return PAGE_COMMENT_LIST;				
	
		}
	
	public String saveDataBand(Integer uzytkownik, Band band) {
		// no Person object passed		
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (comment.getCommentId() == null) {
				// new record
				commentDAO.create(comment);
			} else {
				// existing record
				commentDAO.merge(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		Comment comment = new Comment();
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = date.format(date2);
		
		comment.setBand2(band);
		comment.setUser1(userDAO.find(uzytkownik));
		comment.setPostDate(formattedDate);
			
			return PAGE_COMMENT_BAND;						
	}
	
	public String saveDataAlbum(Integer uzytkownik, Album album) {
		// no Person object passed		
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (comment.getCommentId() == null) {
				// new record
				commentDAO.create(comment);
			} else {
				// existing record
				commentDAO.merge(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		Comment comment = new Comment();
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = date.format(date2);
		
		comment.setAlbum2(album);
		comment.setUser1(userDAO.find(uzytkownik));
		comment.setPostDate(formattedDate);
			
	
			return PAGE_COMMENT_ALBUM;		

		
	}
	
	public String saveDataSong(Integer uzytkownik, Song song) {
		// no Person object passed		
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (comment.getCommentId() == null) {
				// new record
				commentDAO.create(comment);
			} else {
				// existing record
				commentDAO.merge(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		Comment comment = new Comment();
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = date.format(date2);
		
		comment.setSong1(song);
		comment.setUser1(userDAO.find(uzytkownik));
		comment.setPostDate(formattedDate);
			
	
			return PAGE_COMMENT_SONG;		

		
	}


}
