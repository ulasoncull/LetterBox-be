package com.FullProject.LetterBox.Exception;

import com.FullProject.LetterBox.Model.Movie;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String s) {
        super(s);
    }

}
