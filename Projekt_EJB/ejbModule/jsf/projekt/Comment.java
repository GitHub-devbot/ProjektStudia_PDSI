package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;

	@Column(name="album_id")
	private int albumId;

	@Column(name="author_id")
	private int authorId;

	@Lob
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_date")
	private Date postDate;

	@Column(name="song_id")
	private int songId;

	//bi-directional one-to-one association to Album
	@OneToOne(mappedBy="comment")
	private Album album;

	//bi-directional one-to-one association to Band
	@OneToOne(mappedBy="comment")
	private Band band;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by_id")
	private User user;

	//bi-directional one-to-one association to Song
	@OneToOne(mappedBy="comment")
	private Song song;

	public Comment() {
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getSongId() {
		return this.songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Band getBand() {
		return this.band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}