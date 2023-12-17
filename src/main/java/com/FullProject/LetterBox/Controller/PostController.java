package com.FullProject.LetterBox.Controller;

import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }


}
