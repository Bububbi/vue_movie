package com.hds.movie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hds.movie.bean.Movie;
import org.springframework.stereotype.Service;



public interface movieService extends IService<Movie> {
    public Page<Movie> getPage(int currentPage, int pageSize, String bookName);
}
