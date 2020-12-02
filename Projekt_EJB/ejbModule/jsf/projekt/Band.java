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

	@Column(name="band_name")
	private int bandName;

	@Column(name="singer_name")
	private int singerName;

	@Column(name="singer_surname")
	private int singerSurname;

	//bi-directional one-to-one association to Comment
	@OneToOne
	@JoinColumn(name="band_id")
	private Comment comment;

	public Band() {
	}

	public int getBandId() {
		return this.bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public int getBandName() {
		return this.bandName;
	}

	public void setBandName(int bandName) {
		this.bandName = bandName;
	}

	public int getSingerName() {
		return this.singerName;
	}

	public void setSingerName(int singerName) {
		this.singerName = singerName;
	}

	public int getSingerSurname() {
		return this.singerSurname;
	}

	public void setSingerSurname(int singerSurname) {
		this.singerSurname = singerSurname;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}