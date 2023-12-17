package com.FullProject.LetterBox.Repository;

import com.FullProject.LetterBox.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Object> findByUserName(String title);
}
