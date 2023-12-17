package com.FullProject.LetterBox.Repository;

import com.FullProject.LetterBox.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
