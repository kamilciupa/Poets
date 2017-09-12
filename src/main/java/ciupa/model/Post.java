package ciupa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kamil on 2017-09-07.
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 300, name = "title")
    private String title;

    @Column(nullable = false, length = 500, name="body")
    private String body;


    @Column(nullable = false)
    private Date date = new Date();

    @Column
    private String autor;

    /* OCENA POSTÓW */

    @Column
    private int votesAmount;

    @Column
    private float srednia;

    @Column
    private float controlSum;

    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

  /*  public Post(Long id, String title, String body, Blogers author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getVotesAmount() {
        return votesAmount;
    }

    public void setVotesAmount(int votesAmount) {
        this.votesAmount = votesAmount;
    }

    public float getSrednia() {
        return srednia;
    }

    public void setSrednia(float srednia) {
        this.srednia = srednia;
    }

    public float getControlSum() {
        return controlSum;
    }

    public void setControlSum(float controlSum) {
        this.controlSum = controlSum;
    }
}
