package jsf.projektDAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.projekt.User;

@Stateless
public class userDAO {

@PersistenceContext	
EntityManager em;	

public void create(User user) {
	em.persist(user);
}

public User merge(User user) {
	return em.merge(user);
}

public void remove(User user) {
	em.remove(em.merge(user));
}

public User find(Object id) {
	return em.find(User.class, id);
}


public List<User> getFullList() {
	List<User> list = null;

	Query query = em.createQuery("select p from User p");

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

public List<User> getList(Map<String, Object> searchParams) {
	List<User> list = null;

	// 1. Build query string with parameters
	String select = "select p ";
	String from = "from User p ";
	String where = "";
	String orderby = "order by p.userLogin asc, p.userRole";

	// search for surname
	String userLogin = (String) searchParams.get("userLogin");
	if (userLogin != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "p.userLogin like :userLogin ";
	}
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	if (userLogin != null) {
		query.setParameter("userLogin", userLogin+"%");
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
public User getUser(String login,String pass) {
	User user;
	for(int i=0;i<100; i++) {
	user = find(i);
	if(user!=null) {
	if(login.equals(user.getUserLogin()) && pass.equals(user.getUserPassword()) ) {
	return user;		
	}
	}
	}
	
	user = new User();
	user.setUserRole("null");
	return user;	
	
}

}