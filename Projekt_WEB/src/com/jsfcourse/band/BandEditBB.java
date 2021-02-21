package com.jsfcourse.band;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import jsf.projektDAO.bandDAO;
import jsf.projekt.Band;

@Named
@ViewScoped
public class BandEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_BAND_LIST = "/pages/user/bandList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Band band = new Band();
	private Band loaded = null;

	@EJB
	bandDAO bandDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Band getBand() {
		return band;
	}
	
	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Band) flash.get("band");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			band = loaded;
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
			if (band.getBandId() == null) {
				// new record
				bandDAO.create(band);
			} else {
				// existing record
				bandDAO.merge(band);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystapil blad podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_BAND_LIST;
	}

}
