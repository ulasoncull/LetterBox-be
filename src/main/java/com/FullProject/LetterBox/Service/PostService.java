package com.FullProject.LetterBox.Service;

import com.FullProject.LetterBox.Dto.CreatePost;
import com.FullProject.LetterBox.Dto.LikeRequest;
import com.FullProject.LetterBox.Dto.PostDto;
import com.FullProject.LetterBox.Dto.PostDtoConverter;
import com.FullProject.LetterBox.Exception.PostNotFoundException;
import com.FullProject.LetterBox.Model.Movie;
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
    private final MovieService movieService;
    private final PostDtoConverter postDtoConverter;
    public PostService(PostRepository postRepository, UserService userService, MovieService movieService, PostDtoConverter postDtoConverter) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.movieService = movieService;
        this.postDtoConverter = postDtoConverter;
    }


    public PostDto getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post could not found"));
        return postDtoConverter.convert(post);

    }



    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(postDtoConverter::convert).collect(Collectors.toList());
    }
    public PostDto createPost(CreatePost postRequest){
        User user = userService.getUserByUsername(postRequest.getUserName());
        Movie movie = movieService.getMovieByTitle(postRequest.getMovieName());
        Post post = Post.builder()
                .text(postRequest.getText())
                .createDate(LocalDateTime.now())
                .movie(movie)
                .user(user)
                .likedUsers(new ArrayList<>())
                .build();

        return postDtoConverter.convert(postRepository.save(post));
    }

    public PostDto addLike(LikeRequest likeRequest){
        Post post = postRepository.findById(likeRequest.getPostId()).orElseThrow(() -> new PostNotFoundException("Post could not found"));
        User likedUser = userService.getUserByUsername(likeRequest.getUserName());
        if (post.getLikedUsers() != null && userService.isUserContains(post.getLikedUsers(), likeRequest.getUserName())) {
           //log.info("call remove like: " + likeRequest.getUser().getUserName());
            return removeLike(likeRequest);
        }
        post.getLikedUsers().add(likedUser);
        return postDtoConverter.convert(postRepository.save(post));
    }

    public PostDto removeLike(LikeRequest likeRequest) {
        Post post = postRepository.findById(likeRequest.getPostId()).orElseThrow(() -> new PostNotFoundException("Post could not found"));
        User unLikedUser = userService.getUserByUsername(likeRequest.getUserName());
        var updatedLikes = post.getLikedUsers()
                .stream()
                .filter(x -> !x.getUserName().equals(unLikedUser.getUserName()))
                .collect(Collectors.toList());
        post.setLikedUsers(updatedLikes);
        return postDtoConverter.convert(postRepository.save(post));
    }

}
