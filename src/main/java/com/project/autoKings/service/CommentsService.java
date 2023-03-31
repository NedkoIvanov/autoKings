package com.project.autoKings.service;

import com.project.autoKings.model.service.CommentServiceModel;
import com.project.autoKings.model.view.CommentViewModel;

import java.util.List;

public interface CommentsService {

    List<CommentViewModel> findAllCommentsById(Long id);

    CommentViewModel addNewComment(CommentServiceModel commentServiceModel);
}
