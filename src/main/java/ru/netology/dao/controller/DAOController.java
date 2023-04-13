package ru.netology.dao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.dao.repository.DAORepository;

@RestController
@RequestMapping("/products/fetch-product")
public class DAOController {
  private DAORepository daoRepository;

  public DAOController(DAORepository daoRepository) {
    this.daoRepository = daoRepository;
  }

  @GetMapping()
  public String getProductByName(@RequestParam("name") String name) {
    return daoRepository.getProductByName(name);
  }
}
