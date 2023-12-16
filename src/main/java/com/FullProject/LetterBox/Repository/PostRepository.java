package com.FullProject.LetterBox.Repository;

import com.FullProject.LetterBox.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {
}
