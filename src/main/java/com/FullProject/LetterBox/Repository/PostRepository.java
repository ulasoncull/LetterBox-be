package com.FullProject.LetterBox.Repository;

import com.FullProject.LetterBox.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
    List<Post> findByMovieId(Long movieId);
}
