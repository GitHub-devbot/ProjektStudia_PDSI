package jsf.projekt;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	@Lob
	@Column(name="band_name")
	private String bandName;

	//bi-directional one-to-one association to Comment
	@OneToOne
	@JoinColumn(name="band_id")
	private Comment comment;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="added_by_id")
	private User user;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="band2")
	private List<Comment> comments;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="band")
	private List<Song> songs;

	public Band() {
	}

	public Integer getBandId() {
		return this.bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public String getBandName() {
		return this.bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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
		comment.setBand2(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setBand2(null);

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
		song.setBand(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setBand(null);

		return song;
	}

}