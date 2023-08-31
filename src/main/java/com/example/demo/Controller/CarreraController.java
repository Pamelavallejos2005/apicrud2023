package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.CarreraEntity;
import com.example.demo.service.CarreraService;
import static com.example.demo.commons.GlobalConstants.API_CARRERA;
@RestController
@RequestMapping(API_CARRERA)
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping("/all")
    public List<CarreraEntity> listar() {
        return carreraService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraEntity> listar2(@PathVariable("id") long id) {
        CarreraEntity carr = carreraService.read(id);
        if (carr != null) {
            return new ResponseEntity<>(carr, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/carrera")
    public ResponseEntity<CarreraEntity> createTutorial(@RequestBody CarreraEntity p) {
        try {
            CarreraEntity carr = carreraService.create(new CarreraEntity(p.getId(), p.getNombre(), p.getEstado()));

            return new ResponseEntity<>(carr, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/carr/{id}")
    public ResponseEntity<CarreraEntity> updateTutorial(@PathVariable("id") long id, @RequestBody CarreraEntity carr) {
        CarreraEntity carrera = carreraService.read(id);

        if (carrera != null) {
            carrera.setId(carr.getId());
            carrera.setNombre(carr.getNombre());
            carrera.setEstado(carr.getEstado());
            return new ResponseEntity<>(carreraService.update(carrera), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/carrera/{id}")
    public ResponseEntity<HttpStatus> deleteCarrera(@PathVariable("id") long id) {
        try {
            carreraService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
