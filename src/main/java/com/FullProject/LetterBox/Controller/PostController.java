package com.FullProject.LetterBox.Controller;

import com.FullProject.LetterBox.Dto.CreatePost;
import com.FullProject.LetterBox.Dto.LikeRequest;
import com.FullProject.LetterBox.Dto.PostDto;
import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePost post){
        return ResponseEntity.ok(postService.createPost(post));
    }

    @PostMapping("/like")
    public ResponseEntity<PostDto> addLike(@RequestBody@Valid LikeRequest likeRequest){
        return ResponseEntity.ok(postService.addLike(likeRequest));
    }
    @DeleteMapping("/like")
    public ResponseEntity<PostDto> deleteLike(@RequestBody @Valid LikeRequest likeRequest ){
        return  ResponseEntity.ok(postService.removeLike(likeRequest));
    }

}
