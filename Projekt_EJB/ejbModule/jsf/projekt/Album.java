package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	@Lob
	@Column(name="album_name")
	private String albumName;

	//bi-directional one-to-one association to Comment
	@OneToOne
	@JoinColumn(name="album_id")
	private Comment comment;

	//bi-directional one-to-one association to Song
	@OneToOne
	@JoinColumn(name="album_id")
	private Song song;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by_id")
	private User user;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="album2")
	private List<Comment> comments;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="album2")
	private List<Song> songs;

	public Album() {
	}

	public Integer getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setAlbum2(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setAlbum2(null);

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
		song.setAlbum2(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setAlbum2(null);

		return song;
	}

}