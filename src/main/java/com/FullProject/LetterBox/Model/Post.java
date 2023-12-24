package com.FullProject.LetterBox.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDateTime createDate;
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_Id")
    private User user;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Post_LikedUsers",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> likedUsers;
    @ManyToOne(cascade =CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_Id")
    private Movie movie;
}
