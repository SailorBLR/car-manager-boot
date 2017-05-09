package com.emerline.hubarevich.controller;

import com.emerline.hubarevich.DTOmodels.CarObjectDTO;
import com.emerline.hubarevich.business.CarModelService;
import com.emerline.hubarevich.business.CarService;
import com.emerline.hubarevich.business.DTOService;
import com.emerline.hubarevich.business.ProducerService;
import com.emerline.hubarevich.business.exception.LogicException;
import com.emerline.hubarevich.domain.*;

import com.emerline.hubarevich.entity.CarModelProxy;
import com.emerline.hubarevich.entity.CarProxy;
import com.emerline.hubarevich.entity.ProducerProxy;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
@Component
public class CarManagerController {

    private final Logger LOG = LoggerFactory.getLogger(CarManagerController.class);
    @Autowired
    private CarService carService;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private DTOService dtoService;


    @RequestMapping(value = "/CarManagement", method = RequestMethod.GET, headers = "Accept=application/json")
    public String startPage() {
        return "index.html";
    }

    @RequestMapping(value = "/api/cars", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<CarObjectDTO>> getAllCars() {
        List<CarObjectDTO> cars = null;
        try {
            cars = dtoService.getListOfDtos();
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/cars/{carId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<CarObjectDTO> getCar(@PathVariable Long carId) {
        CarObjectDTO carObjectDTO = null;
        try {
            carObjectDTO = dtoService.getCarDTO(carId);
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<>(carObjectDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/producers", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<ProducerProxy>> getAllProducers() {
        List<ProducerProxy> producers = new ArrayList<>();
        try {
            for (Producer producer : producerService.getListOfProducers()) {
                producers.add(new ProducerProxy(producer));
            }
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<List<ProducerProxy>>(producers, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/producers/{producerId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity <ProducerProxy> getProducer(@PathVariable Long producerId) {
        ProducerProxy producer = null;
        try {
            producer = new ProducerProxy(producerService.getProducerById(producerId));
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<ProducerProxy>(producer, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/models", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<CarModelProxy>> getAllModels() {
        List<CarModelProxy> models = new ArrayList<>();
        try {
            for (CarModel carModel : carModelService.getListOfCarModels()) {
                models.add(new CarModelProxy(carModel));
            }
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<List<CarModelProxy>>(models, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/models/{modelId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<CarModelProxy> getModel(@PathVariable Long modelId) {
        CarModelProxy model = null;
        try {
            model = new CarModelProxy(carModelService.getCarModelById(modelId));
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<CarModelProxy>(model, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/models/producerId/{producerId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<CarModelProxy>> getModelByProducer(@PathVariable Long producerId) {
        List<CarModelProxy> models = new ArrayList<>();
        List<CarModel> carModels = null;
        try {
            carModels = carModelService.getListOfCarModelsByProducer(producerId);
            if (carModels != null) {
                for (CarModel carModel : carModelService.getListOfCarModelsByProducer(producerId)) {
                    models.add(new CarModelProxy(carModel));
                }
            }
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
        return new ResponseEntity<List<CarModelProxy>>(models, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/create/insert", method = RequestMethod.POST)
    public void createCar(@RequestBody CarProxy car) throws IOException {
        System.out.println("inserted car " + car);
        LocalDate date =
                Instant.ofEpochMilli(car.getProduceYear()).atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            Car carInstance = new Car();
            carInstance.setModelId(car.getModelId());
            carInstance.setProducerId(car.getProducerId());
            carInstance.setProduceYear(date);
            carInstance.setFuel(Fuel.valueOf(car.getFuel()));
            carInstance.setTransmission(Transmission.valueOf(car.getTransmission()));
            carService.createCar(carInstance);
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
    }

    @RequestMapping(value = "/api/deleteCar/{carId}",
            method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable Long carId) throws ParseException {
        try {
            carService.deleteCar(carId);
        } catch (LogicException e) {
            LOG.error("Server side error", e);
        }
    }
}