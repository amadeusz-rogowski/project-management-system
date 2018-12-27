package com.amicolon.controllers;

import com.amicolon.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PanelController
{

    private final CategoryRepository categoryRepository;

    @Autowired
    public PanelController(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }


    @RequestMapping({"", "/", "/panel"})
    public String getPanelPage(Model model)
    {
        model.addAttribute("categories", categoryRepository.findAll());

        return "panel";
    }


}
