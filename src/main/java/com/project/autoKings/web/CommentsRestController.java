package com.project.autoKings.web;

import com.project.autoKings.model.binding.AddCommentBindingModel;
import com.project.autoKings.model.service.CommentServiceModel;
import com.project.autoKings.model.view.CommentViewModel;
import com.project.autoKings.service.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentsRestController {

   private final CommentsService commentsService;

   private final ModelMapper modelMapper;

    public CommentsRestController(CommentsService commentsService, ModelMapper modelMapper) {
        this.commentsService = commentsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{id}/comments")
    public ResponseEntity<List<CommentViewModel>> getAllComments(@PathVariable Long id){
        return ResponseEntity.ok(this.commentsService.findAllCommentsById(id));
    }

    @PostMapping(path = "/api/{id}/comments")
    public ResponseEntity<CommentViewModel> addNewComment(@PathVariable Long id,
                                                          Principal principal,
                                                          @Valid @RequestBody AddCommentBindingModel addCommentBindingModel){
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setTextContent(addCommentBindingModel.getTextContent());
        commentServiceModel.setAuthorName(principal.getName());
        commentServiceModel.setMechanicId(id);

        CommentViewModel commentViewModel = this.commentsService.addNewComment(commentServiceModel);

        URI location = URI.create(String.format("/api/%s/comments/%s",id,commentViewModel.getId()));

        return ResponseEntity.created(location).body(commentViewModel);
    }

}
