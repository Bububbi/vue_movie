package com.hds.movie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hds.movie.bean.Movie;
import com.hds.movie.mapper.movieMapper;
import com.hds.movie.service.movieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class movieServiceImpl extends ServiceImpl<movieMapper, Movie> implements movieService {
    @Autowired
    movieMapper mapper;

    @Override
    public Page<Movie> getPage(int currentPage, int pageSize, String name){
        Page<Movie> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        mapper.selectPage(page,wrapper);
        return page;
    }
}


