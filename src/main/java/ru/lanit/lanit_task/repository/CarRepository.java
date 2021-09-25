package ru.lanit.lanit_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.lanit_task.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT SUBSTR(UPPER(car.model),1,INSTR(UPPER(car.model),'-')-1)) FROM car")
    Long countModel();

}
