package com.FullProject.LetterBox.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(cascade =CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id", insertable = false, updatable = false)
    private Post post;

}
