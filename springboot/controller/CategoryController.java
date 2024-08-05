package com.example.springboot.controller;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;


    @GetMapping("/list")
    public Result list(){
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest) {
        return Result.success(categoryService.page(categoryPageRequest));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Category obj){
        categoryService.save(obj);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category categories = categoryService.getById(id);
        return Result.success(categories);
    }

    @PutMapping("/updata")
    public Result updata(@RequestBody Category obj){
        categoryService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }
    

}
