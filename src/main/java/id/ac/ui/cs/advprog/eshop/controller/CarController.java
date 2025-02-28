package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String editCarPage(@PathVariable("carId") String carId, Model model, RedirectAttributes redirectAttributes) {
        Car car = service.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar/{carId}")
    public String editCarPost(@PathVariable("carId") String carId, @ModelAttribute Car car, 
                              RedirectAttributes redirectAttributes) {
        car.setCarId(carId);
        service.update(car.getCarId(), car);
        return REDIRECT_CAR_LIST;
    }

    @GetMapping("/deleteCar/{carId}")
    public String deleteCar(@PathVariable("carId") String carId, RedirectAttributes redirectAttributes) {
        service.delete(carId);
        return REDIRECT_CAR_LIST;
    }
}