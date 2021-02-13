package jsf.projektDAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.projekt.Comment;

@Stateless
public class commentDAO {

@PersistenceContext	
EntityManager em;	

public void create(Comment comment) {
	em.persist(comment);
}

public Comment merge(Comment comment) {
	return em.merge(comment);
}

public void remove(Comment comment) {
	em.remove(em.merge(comment));
}

public Comment find(Object id) {
	return em.find(Comment.class, id);
}

public List<Comment> getFullList() {
	List<Comment> list = null;

	Query query = em.createQuery("select p from Comment p");

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

public List<Comment> getList(Map<String, Object> searchParams) {
	List<Comment> list = null;

	// 1. Build query string with parameters
	String select = "select p ";
	String from = "from Comment p ";
	String where = "";
	String orderby = "order by p.commentId asc, p.postDate";

	// search for surname
	String commentName = (String) searchParams.get("commentName");
	if (commentName != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "p.commentName like :commentName ";
	}
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	if (commentName != null) {
		query.setParameter("commentName", commentName+"%");
	}

	// ... other parameters ... 

	// 4. Execute query and retrieve list of Person objects
	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

}