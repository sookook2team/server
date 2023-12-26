package sookook.daybyday.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sookook.daybyday.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
}
