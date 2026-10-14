package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nasa")
public class NasaController {

    @Autowired
    private ICommentRepository commentRepository;

    @GetMapping
    public String showIndex(
            Model model,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {

        // Lấy danh sách comment đã phân trang (mặc định 5 comment/trang)
        Page<Comment> comments = commentRepository.findAll(pageable);

        // Truyền đối tượng comment rỗng cho Form thêm mới
        model.addAttribute("comment", new Comment());

        // Truyền danh sách comment dạng Page sang View
        model.addAttribute("comments", comments);

        return "index";
    }

    @PostMapping("/save")
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        commentRepository.save(comment);
        return "redirect:/nasa";
    }

    @PostMapping("/like/{id}")
    public String likeComment(@PathVariable("id") Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setLikes(comment.getLikes() + 1);
            commentRepository.save(comment);
        }
        return "redirect:/nasa";
    }
}