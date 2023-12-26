package sookook.daybyday.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
}
