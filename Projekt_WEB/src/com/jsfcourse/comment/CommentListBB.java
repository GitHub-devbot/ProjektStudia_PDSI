package com.jsfcourse.comment;

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

import jsf.projektDAO.commentDAO;
import jsf.projekt.Comment;
import jsf.projekt.Band;

@Named
@RequestScoped
public class CommentListBB {
	private static final String PAGE_COMMENT_EDIT = "/pages/user/commentEdit?faces-redirect=true";
	private static final String PAGE_COMMENTS_BAND = "/pages/user/commentsBand?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String comment;
	private int commentId;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	commentDAO commentDAO;
	
	public Integer getcommentId() {
		return commentId;
	}
	
	
	public String getcomment() {
		return comment;
	}

	public void setcomment(String comment) {
		this.comment = comment;
	}

	public List<Comment> getFullList(){
		return commentDAO.getFullList();
	}

	public List<Comment> getList(){
		List<Comment> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (comment != null && comment.length() > 0){
			searchParams.put("comment", comment);
		}
		
		//2. Get list
		list = commentDAO.getList(searchParams);
		
		// list.remove(list.get(1));
		
		return list;
	}
	
	public String newComment(){
		Comment comment = new Comment();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("comment", comment);
		
		return PAGE_COMMENT_EDIT;
	}

	public String editComment(Comment comment){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("comment", comment);
		
		return PAGE_COMMENT_EDIT;
	}
	
	public String commentsBand(Band band){
	//	if(bandId == comment.getAuthorId() ) {
		flash.put("band", band);
	//	}
		return PAGE_COMMENTS_BAND;
	}

	public String deleteComment(Comment comment){
		commentDAO.remove(comment);
		return PAGE_STAY_AT_THE_SAME;
	}
}
