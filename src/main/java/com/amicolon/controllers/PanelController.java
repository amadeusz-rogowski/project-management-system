package com.amicolon.controllers;

import com.amicolon.services.middlewares.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PanelController
{
	private final CategoryService categoryService;

	@Autowired
	public PanelController(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@RequestMapping({"", "/", "/panel"})
    public String getPanelPage(Model model)
    {
        model.addAttribute("categories", categoryService.getAllCategories());

        return "panel";
    }

}
