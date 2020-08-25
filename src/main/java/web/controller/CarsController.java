package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CarServiceImpl;


@Controller
public class CarsController {
    @GetMapping(value = "/cars")
    public String printCar(@RequestParam(value = "locale", required = false) String param, ModelMap model) {
        String title;
        if (param.equals("en")) {
            title = "CARS";
        } else if (param.equals("ru")) {
            title = "МАШИНЫ";
        } else {
            title = "Autos";
        }
        CarServiceImpl carService = new CarServiceImpl();
        model.addAttribute("title", title);
        model.addAttribute("cars", carService.getCars());
        return "cars";
    }

}
