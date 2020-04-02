package pl.bykowski.homework3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.homework3.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private List<Car> cars;

    public CarController() {
        this.cars = new ArrayList<Car>();
        cars.add(new Car(1L,"Ford", "Fusion", "czarny"));
        cars.add(new Car(2L, "Opel", "Astra", "czarny"));
        cars.add(new Car(3L, "Fiat", "Punto 2", "srebrny"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> car = cars.stream().filter(c -> c.getId() == id).findFirst();

        if(car.isPresent()) {
            return new ResponseEntity(car, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @GetMapping("colors/{color}")
    public ResponseEntity<Car> getCarbyColor(@PathVariable String color) {
        Optional<List<Car>> findList = Optional.of(cars.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toList()));
        if (findList.isPresent()) {
            return new ResponseEntity(findList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        boolean add = cars.add(car);
        if(add) {
            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modCar(@RequestBody Car newCar) {
        Optional<Car> first = cars.stream().filter(video -> video.getId() == newCar.getId()).findFirst();

        if(first.isPresent()) {
            cars.remove(first.get());
            cars.add(newCar);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/models")
    public ResponseEntity<Car> patCar(@RequestParam Long id, @RequestParam String model) {
        Optional<Car> first = cars.stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            first.get().setModel(model);
            return new ResponseEntity<Car>(first.get(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

   @DeleteMapping("/{id}")
   public ResponseEntity modCar(@PathVariable long id) {
       Optional<Car> first = cars.stream().filter(c -> c.getId() == id).findFirst();

       if(first.isPresent()) {
           cars.remove(first.get());
           return new ResponseEntity(HttpStatus.OK);
       }

       return new ResponseEntity(HttpStatus.NOT_FOUND);
   }


}
