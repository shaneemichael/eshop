package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

@Repository
public class CarRepository extends AbstractRepository<Car, String> implements CarRepositoryInterface {
    
    private final IdGenerator idGenerator;
    
    public CarRepository(IdGenerator idGenerator) {
        super(Car::getCarId);
        this.idGenerator = idGenerator;
    }
    
    public CarRepository() {
        this(new UuidGenerator());
    }
    
    @Override
    public Car create(Car car) {
        if (car.getCarId() == null || car.getCarId().isEmpty()) {
            car.setCarId(idGenerator.generateId());
        }
        return super.create(car);
    }
    
    @Override
    public Optional<Car> update(String id, Car updatedCar) {
        return findById(id).map(existingCar -> {
            existingCar.update(updatedCar);
            return existingCar;
        });
    }
}