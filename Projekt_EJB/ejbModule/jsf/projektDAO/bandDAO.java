package jsf.projektDAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.projekt.Band;

@Stateless
public class bandDAO {

@PersistenceContext	
EntityManager em;	

public void create(Band band) {
	em.persist(band);
}

public Band merge(Band band) {
	return em.merge(band);
}

public void remove(Band band) {
	em.remove(em.merge(band));
}

public Band find(Object id) {
	return em.find(Band.class, id);
}

public List<Band> getFullList() {
	List<Band> list = null;

	Query query = em.createQuery("select p from Band p");

	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}

public List<Band> getList(Map<String, Object> searchParams) {
	List<Band> list = null;

	// 1. Build query string with parameters
	String select = "select p ";
	String from = "from Band p ";
	String where = "";
	String orderby = "order by p.bandName asc, p.bandId";

	// search for surname
	String bandName = (String) searchParams.get("bandName");
	if (bandName != null) {
		if (where.isEmpty()) {
			where = "where ";
		} else {
			where += "and ";
		}
		where += "p.bandName like :bandName ";
	}
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	if (bandName != null) {
		query.setParameter("bandName", bandName+"%");
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