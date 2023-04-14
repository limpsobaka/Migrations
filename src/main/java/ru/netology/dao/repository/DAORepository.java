package ru.netology.dao.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DAORepository {
  private final String SCRIPT = read("getProductsQuery.sql");
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public DAORepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  public List<String> getProductByName(String name) {
    Map<String, String> map = new HashMap<>();
    map.put("name", name);
    return namedParameterJdbcTemplate.queryForList(SCRIPT, map, String.class);
  }

  private static String read(String scriptFileName) {
    try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
      return bufferedReader.lines().collect(Collectors.joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
