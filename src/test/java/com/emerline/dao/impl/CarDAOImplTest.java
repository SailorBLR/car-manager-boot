package com.emerline.dao.impl;

import com.emerline.hubarevich.CarManagementApplication;
import com.emerline.hubarevich.dao.CarDAO;
import com.emerline.hubarevich.dao.CarModelDAO;
import com.emerline.hubarevich.dao.ProducerDAO;
import com.emerline.hubarevich.dao.exception.DaoException;
import com.emerline.hubarevich.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ContextConfiguration(classes = CarManagementApplication.class)

public class CarDAOImplTest {

    private final int YEAR_INT = 2005;
    private final int MONTH_INT = 1;
    private final int DAY_INT = 1;
    private final LocalDate YEAR = LocalDate.of(YEAR_INT, MONTH_INT, DAY_INT);
    private final Car CAR = new Car(null, 1L, 1L, YEAR, Fuel.diesel, Transmission.automatic);
    private final Car CAR_2 = new Car(null, 1L, 1L, YEAR, Fuel.diesel, Transmission.automatic);
    private final CarModel CAR_MODEL = new CarModel(1L, 1L, "2110");
    private final Producer PRODUCER = new Producer(1L, "Volvo");

    private final Fuel UPD_FUEL = Fuel.gas;

    @Autowired
    CarDAO carDao;
    @Autowired
    CarModelDAO carModelDao;
    @Autowired
    ProducerDAO producerDao;

    @Test
    public void findAll() throws DaoException {
            assertEquals(6, carDao.findAll().size());
    }

    @Test
    public void findDomainById() throws DaoException {
            assertEquals((Long)1L,
                    carDao.findDomainById(1L).getProducerId());
    }

    @Test
    public void delete() throws DaoException {
            carDao.delete(1L);
            assertEquals(5, carDao.findAll().size());
    }

    @Test
    public void create() throws DaoException{
            carDao.create(CAR);
            assertEquals(7,carDao.findAll().size());
    }

    @Test
    public void update() throws DaoException {
        Car car;
            car = carDao.findDomainById(1L);
            car.setFuel(UPD_FUEL);
            carDao.update(car);
            assertEquals(UPD_FUEL, carDao.findDomainById(1L).getFuel());
    }

    @Test
    public void getCarsByProducer() throws DaoException {
        List<Car> cars = new ArrayList<>();
            cars.addAll(carDao.getCarsByProducer(1L));
            assertEquals(3, cars.size());
    }

    @Test
    public void getCarsByModel() throws DaoException {
        List<Car> cars = new ArrayList<>();
            cars.addAll(carDao.getCarsByModel(1L));
            assertEquals(2, cars.size());
    }

}
