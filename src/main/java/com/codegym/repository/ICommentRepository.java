package com.codegym.repository;

import com.codegym.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    // Tự động phân trang danh sách Comment
    Page<Comment> findAll(Pageable pageable);
}