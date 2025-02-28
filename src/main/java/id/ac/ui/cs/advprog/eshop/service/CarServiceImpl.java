package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryInterface;

@Service
public class CarServiceImpl extends AbstractService<Car, String, CarRepositoryInterface> implements CarService {
    
    public CarServiceImpl(CarRepositoryInterface carRepository) {
        super(carRepository);
    }
}