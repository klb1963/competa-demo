package com.competa.competademo.controller;

import com.competa.competademo.entity.User;
import com.competa.competademo.models.Competa;
import com.competa.competademo.repository.CompetaRepository;
import com.competa.competademo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CompetaPagesController {


    private final CompetaRepository competaRepository;

    private final UserService userService;

    public CompetaPagesController(CompetaRepository competaRepository, UserService userService) {
        this.competaRepository = competaRepository;
        this.userService = userService;
    }

    // здесь должны быть все Competa текущего пользователя, а пока просто всё, что есть из репозитория
    @GetMapping("/competa")
    public String competaMain(Model model){
      //  Iterable<Competa> competas = competaRepository.findAll(); // пока включено
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // вызов контекста
        String name = authentication.getName(); // получение имени текущего пользователя
        // теперь по имени текущего пользователя надо получить его id
        User user = userService.findByEmail(name);
        List<Competa> userCompetas = competaRepository.findAllByUser(user);
        model.addAttribute("competas", userCompetas);
        return "competa-main"; // вызывается шаблон
    }

    @GetMapping("/competa/add")  // переход на страницу
    public String competaAdd (Model model){
        model.addAttribute("competa", new Competa());// через model связали шаблон с классом Competa
        return "competa-add";  // вызывается шаблон
    }

    @PostMapping("/competa/add")
    public String competaAdd(@ModelAttribute Competa competa, Model model) {
        model.addAttribute("competa", new Competa());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // вызов контекста
        String name = authentication.getName(); // получение имени текущего пользователя
        User user = userService.findByEmail(name);
        competa.setUser(user); // задаем значение поля в competa
        competaRepository.save(competa); // сохраняем объект competa
        user.getCompetas().add(competa);
        userService.saveUser(user);
        return "redirect:/competa"; // переход на страницу redirect:/competa"
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
