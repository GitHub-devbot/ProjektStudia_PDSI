package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the songs database table.
 * 
 */
@Entity
@Table(name="songs")
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="song_id")
	private int songId;

	@Column(name="added_by_id")
	private int addedById;

	@Column(name="album_id")
	private int albumId;

	@Lob
	@Column(name="song_author")
	private String songAuthor;

	@Lob
	@Column(name="song_name")
	private String songName;

	@Lob
	@Column(name="song_url")
	private String songUrl;

	public Song() {
	}

	public Integer getSongId() {
		return this.songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getAddedById() {
		return this.addedById;
	}

	public void setAddedById(int addedById) {
		this.addedById = addedById;
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getSongAuthor() {
		return this.songAuthor;
	}

	public void setSongAuthor(String songAuthor) {
		this.songAuthor = songAuthor;
	}

	public String getSongName() {
		return this.songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongUrl() {
		return this.songUrl;
	}

	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}

}