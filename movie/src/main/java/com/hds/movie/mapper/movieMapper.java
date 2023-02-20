package com.hds.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hds.movie.bean.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface movieMapper extends BaseMapper<Movie> {

}
