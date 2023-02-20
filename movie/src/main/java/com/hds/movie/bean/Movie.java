package com.hds.movie.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

@Data
@TableName("movie_msg")
public class Movie {
    private Integer id;
    private String name;
    private String country;
    private Integer year;
    private BigDecimal score;

    public Movie() {
    }

    public Movie(Integer id, String name, String country, Integer year, BigDecimal score) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.year = year;
        this.score = score;
    }
}


