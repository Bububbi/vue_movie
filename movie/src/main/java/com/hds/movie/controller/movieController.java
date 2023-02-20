package com.hds.movie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hds.movie.bean.Movie;
import com.hds.movie.common.Result;
import com.hds.movie.mapper.movieMapper;
import com.hds.movie.service.movieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movie")
public class movieController {

    @Autowired
    movieService service;

    @Autowired
    movieMapper mapper;

    //新增一个电影信息
    @PostMapping
    public Result add(@RequestBody Movie movie){
        if(service.save(movie)){
            return Result.success();
        }else{
            return Result.error("401", "添加电影失败");
        }

    }

    //获取所有电影信息
    @GetMapping
    public Result getAllMovie(){
        return new Result(mapper.selectList(null));
    }

    //获取一个电影信息
    @GetMapping("{id}")
    public Result getOneMovie(@PathVariable Integer id){
        Movie movie = mapper.selectById(id);
        if(movie!=null){
            return Result.success(movie);
        }else{
            return Result.error("403","获取电影信息失败");
        }
    }

    //编辑一个电影信息
    @PutMapping
    public Result modify(@RequestBody Movie movie){
        if(service.updateById(movie)){
            return Result.success(movie);
        }else{
            return Result.error("403","修改电影信息失败");
        }
    }

    //删除一条电影信息
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        if(mapper.deleteById(id)>0){
            return Result.success();
        }else{
            return Result.error("403","删除电影信息失败");
        }
    }

    //获取电影页面分页信息
    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestParam String name){
        IPage<Movie> page = service.getPage(currentPage, pageSize, name);
        //当删除当前页面仅存的一条数据时，自动刷新到前一页
        if(currentPage > page.getPages()){
            page = service.getPage((int)page.getPages(),pageSize,name);
        }
        return Result.success(page);
    }
}


