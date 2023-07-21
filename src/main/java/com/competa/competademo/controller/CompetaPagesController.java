package com.competa.competademo.controller;

import com.competa.competademo.dto.UserDto;
import com.competa.competademo.models.Competa;
import com.competa.competademo.repository.CompetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CompetaPagesController {

    @Autowired
    private CompetaRepository competaRepository;

// @GetMapping ("/competa") // переход на страницу
    // здесь должны быть все Competas данного User, а пока просто все
//    public String competaMain (Model model){
//        Iterable<Competa> competas = competaRepository.findAll();
//        model.addAttribute("competas", competas);
//        return "competa-main"; // вызывается шаблон
//    }

    @GetMapping("/competa")
    public String competaMain(Model model){
        Iterable<Competa> competas = competaRepository.findAll();
        model.addAttribute("competas", competas);
        return "competa-main"; // вызывается шаблон
    }

    @GetMapping("/competa/add")  // переход на страницу
    public String competaAdd (Model model){
        model.addAttribute("competa", new Competa());
        return "competa-add";  // вызывается шаблон
    }

    @PostMapping("/competa/add")
    public String competaAdd(@ModelAttribute Competa competa, Model model) {
        model.addAttribute("competa", new Competa());
        competaRepository.save(competa);
        return "redirect:/competa"; // переход на страницу
    }

    @GetMapping ("/competa/{id}")  // переход на страницу
    public String competaDetails (@PathVariable(value ="id") long id, Model model){
        Optional<Competa> competa = competaRepository.findById(id); // в классе Optional создали экземпляр
        if (competa.isPresent()) {
            model.addAttribute("competa", competa.get());
            return "competa-details";
        } else {
            return "redirect:/competa";
        }
    }

    @GetMapping("/competa/{id}/edit")
    public String competaEdit( @PathVariable(value = "id") long id, Model model) {
        Optional<Competa> competa = competaRepository.findById(id); // взяли "футляр"
        if (competa.isPresent()) {  // если внутри "футляра" есть результат
            model.addAttribute("competa", competa.get()); // взяли в model
            return "competa-edit";
        } else {
            return "redirect:/competa";
        }
    }

    @PostMapping ("/competa/{id}/edit") //
    public String competaUpdate(@PathVariable(value = "id") long id, @ModelAttribute Competa competa){
        Competa competaToEdit = competaRepository.findById(id).orElseThrow();
        competaToEdit.setTitle(competa.getTitle()); // название
        competaToEdit.setDescription(competa.getDescription()); // описание
        competaToEdit.setDateOut(competa.getDateOut()); // дата
        competaToEdit.setStatus(competa.isStatus()); // статус
        competaRepository.save(competaToEdit); // сохраняем в репозитории
        return "redirect:/competa";
    }

    @PostMapping("/competa/{id}/remove")
    public String competaDelete(@PathVariable(value = "id") long id, Model model) {
        Competa competa = competaRepository.findById(id).orElseThrow();
        competaRepository.delete(competa);
        return "redirect:/competa";
    }
}
