package com.competa.competademo.controller;

import com.competa.competademo.entity.Competa;
import com.competa.competademo.entity.User;
import com.competa.competademo.repository.CompetaRepository;
import com.competa.competademo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CompetaPagesController {

    private static final String COMPETA_VIEW_VARIABLE = "competa";
    private static final String REDIRECT_COMPETA = "redirect:/competa";
    private final CompetaRepository competaRepository;

    private final UserService userService;

    public CompetaPagesController(CompetaRepository competaRepository, UserService userService) {
        this.competaRepository = competaRepository;
        this.userService = userService;
    }

    @GetMapping("/competa")
    public String competaMain(Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // вызов контекста
        final String authUserEmail = authentication.getName(); // получение имени текущего пользователя
        final User user = userService.findByEmail(authUserEmail);
        final List<Competa> userCompetas = competaRepository.findAllByUser(user);
        model.addAttribute("competas", userCompetas);
        return "competa-main"; // вызывается шаблон
    }

    @GetMapping("/competa/add")  // переход на страницу
    public String competaAdd(Model model) {
        model.addAttribute(COMPETA_VIEW_VARIABLE, new Competa());// через model связали шаблон с классом Competa
        return "competa-add";  // вызывается шаблон
    }

    @PostMapping("/competa/add")
    public String competaAdd(@ModelAttribute Competa competa, Model model) {
        model.addAttribute(COMPETA_VIEW_VARIABLE, new Competa());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // вызов контекста
        String authUserEmail = authentication.getName(); // получение имени текущего пользователя
        User user = userService.findByEmail(authUserEmail);
        competa.setUser(user); // задаем значение поля в competa
        competaRepository.save(competa); // сохраняем объект competa
        user.getCompetas().add(competa);
        userService.saveUser(user);
        return REDIRECT_COMPETA; // переход на страницу redirect:/competa"
    }

    @GetMapping("/competa/{id}")  // переход на страницу
    public String competaDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Competa> competa = competaRepository.findById(id); // в классе Optional создали экземпляр
        if (competa.isPresent()) {
            model.addAttribute(COMPETA_VIEW_VARIABLE, competa.get());
            return "competa-details";
        } else {
            return REDIRECT_COMPETA;
        }
    }

    @GetMapping("/competa/{id}/edit")
    public String competaEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<Competa> competa = competaRepository.findById(id); // взяли "футляр"
        if (competa.isPresent()) {  // если внутри "футляра" есть результат
            model.addAttribute(COMPETA_VIEW_VARIABLE, competa.get()); // взяли в model
            return "competa-edit";
        } else {
            return REDIRECT_COMPETA;
        }
    }

    @PostMapping("/competa/{id}/edit") //
    public String competaUpdate(@PathVariable(value = "id") long id, @ModelAttribute Competa competa) {
        Competa competaToEdit = competaRepository.findById(id).orElseThrow();
        competaToEdit.setTitle(competa.getTitle()); // название
        competaToEdit.setDescription(competa.getDescription()); // описание
        competaToEdit.setDateOut(competa.getDateOut()); // дата
        competaToEdit.setStatus(competa.isStatus()); // статус
        competaRepository.save(competaToEdit); // сохраняем в репозитории
        return REDIRECT_COMPETA;
    }

    @PostMapping("/competa/{id}/remove")
    public String competaDelete(@PathVariable(value = "id") long id) {
        Competa competa = competaRepository.findById(id).orElseThrow();
        competaRepository.delete(competa);
        return REDIRECT_COMPETA;
    }
}
