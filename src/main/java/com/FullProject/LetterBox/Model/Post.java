package com.FullProject.LetterBox.Model;

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
    private Long Id;
    private String text;
    private LocalDateTime createDate;
    @ManyToOne(cascade =CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", insertable = false, updatable = false)
    private User user;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Post_LikedUsers",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> likedUsers;
    @ManyToOne(cascade =CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_Id", insertable = false, updatable = false)
    private Movie movie;


}
