package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.repository.PostRepository;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

}
