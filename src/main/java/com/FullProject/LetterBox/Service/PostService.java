package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Dto.LikeRequest;
import com.FullProject.LetterBox.Exception.PostNotFoundException;
import com.FullProject.LetterBox.Model.Post;
import com.FullProject.LetterBox.Model.User;
import com.FullProject.LetterBox.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post could not found"));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Post createPost(Post post){
        User user = userService.getUserByUsername(post.getUser().getUserName());
        post.setUser(user);
        post.setCreateDate(LocalDateTime.now());
        //post.setLikedUsers(new ArrayList<>());
        return postRepository.save(post);
    }

    public Post addLike(LikeRequest likeRequest){
        Post post = getPostById(likeRequest.getPost().getId());
        User postOfUser = post.getUser();
        User likedUser = userService.getUserByUsername(likeRequest.getUser().getUserName());
        if (post.getLikedUsers() != null && userService.isUserContains(post.getLikedUsers(), likeRequest.getUser().getUserName())) {
           // log.info("call remove like: " + likeRequest.getUser().getUserName());
            return removeLike(likeRequest);
        }
        post.getLikedUsers().add(likedUser);
        return postRepository.save(post);
    }

    public Post removeLike(LikeRequest likeRequest) {
        Post post = getPostById(likeRequest.getPost().getId());
        User postOfUser = post.getUser();
        User unLikedUser = userService.getUserByUsername(likeRequest.getUser().getUserName());
        var updatedLikes = post.getLikedUsers()
                .stream()
                .filter(x -> !x.getUserName().equals(unLikedUser.getUserName()))
                .collect(Collectors.toList());
        post.setLikedUsers(updatedLikes);
        return postRepository.save(post);
    }



}
