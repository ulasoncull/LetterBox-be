package com.FullProject.LetterBox.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(cascade =CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id", insertable = false, updatable = false)
    private Post post;

}
