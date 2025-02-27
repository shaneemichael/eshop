package id.ac.ui.cs.advprog.eshop.repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

@Repository
public class CarRepository implements CarRepositoryInterface{
    static int id = 0;

    private List<Car> carData = new ArrayList<>();
    private final IdGenerator idGenerator;

    public CarRepository(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public CarRepository() {
        this(new UuidGenerator());
    }

    @Override
    public Car create(Car car) {
        if(car.getCarId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        for(Car car: carData) {
            if(car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Car update(String id, Car updatedCar) {
        for(int i=0; i<carData.size(); i++) {
            Car car = carData.get(i);
            if(car.getCarId().equals(id)) {
                car.update(updatedCar);
                return car;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
