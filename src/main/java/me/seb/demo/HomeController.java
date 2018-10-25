package me.seb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    BookRepository bookRepository;


    @RequestMapping("/")
    public String listBooks(Model model){
        model.addAttribute("books",bookRepository.findAll());

        return "list";
    }
    @GetMapping("/add")
    public String addBooks(Model model){
        model.addAttribute("course",new Book());
        return "bookform";
    }
    @PostMapping("/process")
    public String showDetail(@Valid Book book, BindingResult result){
     if(result.hasErrors()){
         return "redirect:/";
     }
     bookRepository.save(book);
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model){
        model.addAttribute("book",bookRepository.findById(id));

        return "bookform";
    }
    @RequestMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") long id,Model model){
        model.addAttribute("book",bookRepository.findById(id).get());
         return "show";
    }
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookRepository.deleteById(id);

        return "redirect:/";
    }


}
