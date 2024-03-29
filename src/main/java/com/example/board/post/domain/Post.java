package com.example.board.post.domain;

import com.example.board.author.domain.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
public class Post {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000, nullable = false)
    private String content;

//  @JoinColumn(nullable = false, name="author_email", referencedColumnName = "email")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn // 설정 없으면 pk에 걸린다.
    private Author author;

//    Time
    @Setter
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Setter
    @UpdateTimestamp
    private LocalDateTime updatedTime;


    private String appointment;
    private LocalDateTime appointmentTime;
    public void setAppointment(String appointment, LocalDateTime appointmentTime) {
        this.appointment = appointment;
        this.appointmentTime = appointmentTime;
    }


    public Post(){}

    @Builder
    public Post (String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
//        this.author.getPosts().add(this);
    }

    public void postUpdate (String title, String content) {
        this.title = title;
        this.content = content;
    }

}