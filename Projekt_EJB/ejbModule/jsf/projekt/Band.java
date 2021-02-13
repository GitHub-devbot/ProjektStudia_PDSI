package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the band database table.
 * 
 */
@Entity
@NamedQuery(name="Band.findAll", query="SELECT b FROM Band b")
public class Band implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="band_id")
	private int bandId;

	@Column(name="added_by_id")
	private int addedById;

	@Lob
	@Column(name="band_name")
	private String bandName;

	public Band() {
	}

	public Integer getBandId() {
		return this.bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public int getAddedById() {
		return this.addedById;
	}

	public void setAddedById(int addedById) {
		this.addedById = addedById;
	}

	public String getBandName() {
		return this.bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

}