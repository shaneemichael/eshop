package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController<Car, String, CarService> {
    
    private static final String REDIRECT_CAR_LIST = "redirect:/car/listCar";
    private static final String CAR_NOT_FOUND = "Car not found";

    public CarController(CarService carService) {
        super(carService);
    }
    
    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car) {
        service.create(car);
        return REDIRECT_CAR_LIST;
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = service.findAll();
        addEntitiesToModel(model, allCars, "cars");
        return "CarList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model, RedirectAttributes redirectAttributes) {
        return service.findById(carId)
                .map(car -> {
                    model.addAttribute("car", car);
                    return "EditCar";
                })
                .orElseGet(() -> handleNotFound(carId, redirectAttributes, REDIRECT_CAR_LIST));
    }

    @PostMapping("/editCar/{carId}")
    public String editCarPost(@ModelAttribute Car car, RedirectAttributes redirectAttributes) {
        return service.update(car.getCarId(), car)
                .map(updatedCar -> REDIRECT_CAR_LIST)
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", CAR_NOT_FOUND);
                    return REDIRECT_CAR_LIST;
                });
    }

    @GetMapping("/deleteCar/{carId}")
    public String deleteCar(@RequestParam("carId") String carId, RedirectAttributes redirectAttributes) {
        boolean deleted = service.delete(carId);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("errorMessage", CAR_NOT_FOUND);
        }
        return REDIRECT_CAR_LIST;
    }
}