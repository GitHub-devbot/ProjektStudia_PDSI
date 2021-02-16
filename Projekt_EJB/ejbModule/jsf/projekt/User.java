package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Lob
	@Column(name="user_login")
	private String userLogin;

	@Lob
	@Column(name="user_password")
	private String userPassword;

	@Lob
	@Column(name="user_role")
	private String userRole;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="user")
	private List<Album> albums;

	//bi-directional many-to-one association to Band
	@OneToMany(mappedBy="user")
	private List<Band> bands;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user1")
	private List<Comment> comments;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="user1")
	private List<Song> songs;

	//bi-directional one-to-one association to Comment
	@OneToOne
	@JoinColumn(name="user_id")
	private Comment comment;

	//bi-directional one-to-one association to Song
	@OneToOne
	@JoinColumn(name="user_id")
	private Song song;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setUser(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setUser(null);

		return album;
	}

	public List<Band> getBands() {
		return this.bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

	public Band addBand(Band band) {
		getBands().add(band);
		band.setUser(this);

		return band;
	}

	public Band removeBand(Band band) {
		getBands().remove(band);
		band.setUser(null);

		return band;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser1(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser1(null);

		return comment;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setUser1(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setUser1(null);

		return song;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}