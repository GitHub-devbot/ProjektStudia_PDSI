package jsf.projektDAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.projekt.Album;

@Stateless
public class albumDAO {

@PersistenceContext	
EntityManager em;	

public void create(Album album) {
	em.persist(album);
}

public Album merge(Album album) {
	return em.merge(album);
}

public void remove(Album album) {
	em.remove(em.merge(album));
}

public Album find(Object id) {
	return em.find(Album.class, id);
}

public List<Album> getFullList() {
	List<Album> list = null;

	Query query = em.createQuery("select p from Album p");

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

public List<Album> getList(Map<String, Object> searchParams) {
	List<Album> list = null;

	// 1. Build query string with parameters
	String select = "select p ";
	String from = "from Album p ";
	String where = "";
	String orderby = "order by p.albumId asc, p.albumName";

	// search for surname
	String albumName = (String) searchParams.get("albumName");
	if (albumName != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "p.albumName like :albumName ";
	}
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	if (albumName != null) {
		query.setParameter("albumName", albumName+"%");
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