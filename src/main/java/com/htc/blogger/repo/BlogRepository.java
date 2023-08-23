package com.htc.blogger.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htc.blogger.entity.Blog;



@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

}
