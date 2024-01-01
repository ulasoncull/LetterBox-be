package com.FullProject.LetterBox.Repository;

import com.FullProject.LetterBox.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
    List<Post> findByMovieId(Long movieId);
    @Query("SELECT p FROM Post p WHERE p.user.userName = :userName")
    List<Post> findByUserName(String userName);
}
