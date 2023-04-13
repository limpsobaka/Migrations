package ru.netology.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DAORepository {
  private final String SCRIPT = read("getProductsQuery.sql");
  @Autowired
  private DataSource dataSource;
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public String getProductByName(String name) {
    Map<String, String> map = new HashMap<>();
    map.put("name", name);
    String product = namedParameterJdbcTemplate.query(SCRIPT, map,
            (rs, rn) -> rs.getString(1)).toString();
    return product;
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
