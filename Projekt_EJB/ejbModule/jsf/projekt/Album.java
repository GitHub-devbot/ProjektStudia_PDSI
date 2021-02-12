package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="album_id")
	private int albumId;

	@Column(name="added_by_id")
	private int addedById;

	@Lob
	@Column(name="album_name")
	private String albumName;

	public Album() {
	}

	public Integer getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getAddedById() {
		return this.addedById;
	}

	public void setAddedById(int addedById) {
		this.addedById = addedById;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

}