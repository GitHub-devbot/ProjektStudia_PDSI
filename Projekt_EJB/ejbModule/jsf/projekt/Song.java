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

	@Lob
	@Column(name="song_author")
	private String songAuthor;

	@Lob
	@Column(name="song_name")
	private String songName;

	@Lob
	@Column(name="song_url")
	private String songUrl;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by_id")
	private User user;

	//bi-directional one-to-one association to Comment
	@OneToOne
	@JoinColumn(name="song_id")
	private Comment comment;

	public Song() {
	}

	public int getSongId() {
		return this.songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}