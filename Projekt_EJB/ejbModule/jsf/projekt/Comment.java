package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;


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

	@Lob
	private String comment;

	@Lob
	@Column(name="post_date")
	private String postDate;

	//bi-directional one-to-one association to Album
	@OneToOne(mappedBy="comment")
	private Album album1;

	//bi-directional one-to-one association to Band
	@OneToOne(mappedBy="comment")
	private Band band1;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="album_id")
	private Album album2;

	//bi-directional many-to-one association to Band
	@ManyToOne
	@JoinColumn(name="author_id")
	private Band band2;

	//bi-directional many-to-one association to Song
	@ManyToOne
	@JoinColumn(name="song_id")
	private Song song1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by_id")
	private User user1;

	//bi-directional one-to-one association to Song
	@OneToOne(mappedBy="comment")
	private Song song2;

	//bi-directional one-to-one association to User
	@OneToOne(mappedBy="comment")
	private User user2;

	public Comment() {
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPostDate() {
		return this.postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public Album getAlbum1() {
		return this.album1;
	}

	public void setAlbum1(Album album1) {
		this.album1 = album1;
	}

	public Band getBand1() {
		return this.band1;
	}

	public void setBand1(Band band1) {
		this.band1 = band1;
	}

	public Album getAlbum2() {
		return this.album2;
	}

	public void setAlbum2(Album album2) {
		this.album2 = album2;
	}

	public Band getBand2() {
		return this.band2;
	}

	public void setBand2(Band band2) {
		this.band2 = band2;
	}

	public Song getSong1() {
		return this.song1;
	}

	public void setSong1(Song song1) {
		this.song1 = song1;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public Song getSong2() {
		return this.song2;
	}

	public void setSong2(Song song2) {
		this.song2 = song2;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}