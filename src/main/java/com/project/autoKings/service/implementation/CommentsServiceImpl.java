package com.project.autoKings.service.implementation;

import com.project.autoKings.model.entity.CommentEntity;
import com.project.autoKings.model.entity.MechanicEntity;
import com.project.autoKings.model.entity.UserEntity;
import com.project.autoKings.model.service.CommentServiceModel;
import com.project.autoKings.model.view.CommentViewModel;
import com.project.autoKings.repository.CommentRepository;
import com.project.autoKings.repository.UserRepository;
import com.project.autoKings.service.CommentsService;
import com.project.autoKings.service.MechanicService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;

    private final MechanicService mechanicService;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public CommentsServiceImpl(CommentRepository commentRepository, MechanicService mechanicService, ModelMapper modelMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.mechanicService = mechanicService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentViewModel> findAllCommentsById(Long id) {
        MechanicEntity mechanic = this.mechanicService.findEntityById(id);
        List<CommentEntity> comments = mechanic.getComments();
        List<CommentViewModel> commentView = new ArrayList<>();
        if (comments != null && !comments.isEmpty()) {
            for (int i = 0; i < comments.size(); i++) {
                commentView.add(
                        new CommentViewModel(
                                comments.get(i).getId(),
                                comments.get(i).getTextContent(),
                                comments.get(i).getCreated(),
                                comments.get(i).getAuthor().getUsername(),
                                comments.get(i).getAuthor().getImageUrl()
                        )
                );
            }
        }
        return commentView;
    }

    @Override
    public CommentViewModel addNewComment(CommentServiceModel commentServiceModel) {

        UserEntity author = this.userRepository.findByUsername(commentServiceModel.getAuthorName()).orElseThrow(
                ()->new UsernameNotFoundException("User with requested name: "
                        + commentServiceModel.getAuthorName() + " not found!"));
        MechanicEntity receiver = this.mechanicService.findEntityById(commentServiceModel.getMechanicId());

        CommentEntity comment = new CommentEntity();
        comment.setAuthor(author);
        comment.setCreated(LocalDateTime.now());
        comment.setMechanic(receiver);
        comment.setTextContent(commentServiceModel.getTextContent());
        this.commentRepository.save(comment);

        CommentViewModel commentView = new CommentViewModel();
        commentView.setCreated(LocalDateTime.now());
        commentView.setId(comment.getId());
        commentView.setTextContent(comment.getTextContent());
        commentView.setAuthorName(author.getUsername());
        commentView.setAuthorName(author.getImageUrl());
        return commentView;
    }

}


