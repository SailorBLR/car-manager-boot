package com.emerline.dao.impl;

import com.emerline.hubarevich.CarManagementApplication;
import com.emerline.hubarevich.dao.ProducerDAO;
import com.emerline.hubarevich.dao.exception.DaoException;
import com.emerline.hubarevich.domain.Producer;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@ContextConfiguration(classes = CarManagementApplication.class)
public class ProducerDAOImplTest {

    private final Producer PRODUCER = new Producer(1L, "Volvo");
    private final String UPD_NAME = "Kia";

    @Autowired
    ProducerDAO producerDAO;

    @Test
    public void findAll() throws DaoException {
        assertEquals(2, producerDAO.findAll().size());
    }

    @Test
    public void findDomainById() throws DaoException {
        assertEquals(producerDAO.create(PRODUCER),
                producerDAO.findDomainById(PRODUCER.getProducerId()).getProducerId());
    }

    @Test
    public void delete() throws DaoException{
            producerDAO.delete(1L);
            assertEquals(1, producerDAO.findAll().size());
    }

    @Test
    public void create() throws DaoException {
        assertEquals(PRODUCER.getProducerId(), producerDAO.create(PRODUCER));
    }

    @Test
    public void update() throws DaoException {
        Producer producer;
        producer = producerDAO.findDomainById(1L);
        producer.setProducerName(UPD_NAME);
        producerDAO.update(producer);
        assertEquals(UPD_NAME, producerDAO.findDomainById(1L).getProducerName());
    }

    @Test
    public void getProducerByCarId() throws DaoException{
        assertEquals(producerDAO.findDomainById(1L), producerDAO.getProducerByCarId(1L));
    }

    @Test
    public void getProducerByModelId() throws DaoException{
            assertEquals(producerDAO.findDomainById(1L), producerDAO.getProducerByModelId(1L));
    }

}
