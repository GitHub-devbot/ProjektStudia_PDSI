package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@Column(name="song_name")
	private String songName;

	@Lob
	@Column(name="song_url")
	private String songUrl;

	//bi-directional one-to-one association to Album
	@OneToOne(mappedBy="song")
	private Album album1;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="song1")
	private List<Comment> comments;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="album_id")
	private Album album2;

	//bi-directional many-to-one association to Band
	@ManyToOne
	@JoinColumn(name="author_id")
	private Band band;

	//bi-directional one-to-one association to Comment
	@OneToOne
	@JoinColumn(name="song_id")
	private Comment comment;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by_id")
	private User user1;

	//bi-directional one-to-one association to User
	@OneToOne(mappedBy="song")
	private User user2;

	public Song() {
	}

	public Integer getSongId() {
		return this.songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
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

	public Album getAlbum1() {
		return this.album1;
	}

	public void setAlbum1(Album album1) {
		this.album1 = album1;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setSong1(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setSong1(null);

		return comment;
	}

	public Album getAlbum2() {
		return this.album2;
	}

	public void setAlbum2(Album album2) {
		this.album2 = album2;
	}

	public Band getBand() {
		return this.band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}