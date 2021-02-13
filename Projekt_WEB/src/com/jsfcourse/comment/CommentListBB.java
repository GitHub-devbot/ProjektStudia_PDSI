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

@Named
@RequestScoped
public class CommentListBB {
	private static final String PAGE_COMMENT_EDIT = "commentEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
	private int albumId;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	commentDAO commentDAO;
	
	public Integer getalbumComment() {
		return albumId;
	}
	
	
	public String getcommentName() {
		return name;
	}

	public void setcommentName(String name) {
		this.name = name;
	}

	public List<Comment> getFullList(){
		return commentDAO.getFullList();
	}

	public List<Comment> getList(){
		List<Comment> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = commentDAO.getList(searchParams);
		
		return list;
	}
	
	public List<Comment> albumComments(){
		List<Comment> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
	//	if (albumId != null && albumId.length() > 0){
			searchParams.put("albumId", albumId);
	//	}
		
		//2. Get list
		list = commentDAO.getList(searchParams);
		
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

	public String deleteComment(Comment comment){
		commentDAO.remove(comment);
		return PAGE_STAY_AT_THE_SAME;
	}
}
