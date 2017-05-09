package com.emerline.dao.impl;

import com.emerline.hubarevich.CarManagementApplication;
import com.emerline.hubarevich.dao.CarModelDAO;
import com.emerline.hubarevich.dao.exception.DaoException;
import com.emerline.hubarevich.domain.CarModel;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ContextConfiguration(classes = CarManagementApplication.class)
public class CarModelDAOImplTest {
    private final CarModel CAR_MODEL = new CarModel(4L, 1L, "2110");
    private final String UPD_MODEL_NAME = "2113";

    @Autowired
    private CarModelDAO carModelDao;

    @Test
    public void findAll() throws DaoException{
        assertEquals(4, carModelDao.findAll().size());
    }

    @Test
    public void findDomainById() throws DaoException {
        assertEquals(carModelDao.create(CAR_MODEL),
                carModelDao.findDomainById(CAR_MODEL.getModelId()).getModelId());
    }

    @Test
    public void delete() throws DaoException {
        carModelDao.delete(1L);
        assertEquals(3, carModelDao.findAll().size());
    }

    @Test
    public void create() throws DaoException {
        assertEquals(CAR_MODEL.getModelId(), carModelDao.create(CAR_MODEL));
    }

    @Test
    public void update() throws DaoException {
        CarModel carModel;
        carModel = carModelDao.findDomainById(1L);
        carModel.setModelName(UPD_MODEL_NAME);
        carModelDao.update(carModel);
        assertEquals(UPD_MODEL_NAME, carModelDao.findDomainById(1L).getModelName());
    }

    @Test
    public void getModelsByProducer() throws DaoException {
        assertEquals(2, carModelDao.getModelsByProducer(1L).size());
    }

    @Test
    public void getModelByCar() throws DaoException {
        assertEquals((Long) 1L, carModelDao.getModelByCar(1L).getModelId());
    }

}
