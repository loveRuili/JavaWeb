package com.czxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("typeservice")
public interface TypeFeign {
    @GetMapping("/type/{tid}")
    Type selectTypeByTid(@PathVariable("tid") Integer news_type_id);
}
