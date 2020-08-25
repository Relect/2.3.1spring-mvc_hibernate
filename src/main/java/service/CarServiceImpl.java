package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService{
    private List<Car> myCars = new ArrayList<>();

    public List<Car> getCars () {
        List<Car> cars =  new ArrayList<>();
        Car firstCar = new Car("Волга","ГАЗ 24", 1986);
        Car secondCar = new Car("Жигули", "ВАЗ 2101", 1987);
        Car thirdCar = new Car("Москвич", "412", 1988);
        cars.add(firstCar);
        cars.add(secondCar);
        cars.add(thirdCar);
        return cars;
    }
    @Override
    public void add(Car car) {
        this.myCars.add(car);
    }

    @Override
    public List<Car> listCars() {
        return myCars;
    }
}
