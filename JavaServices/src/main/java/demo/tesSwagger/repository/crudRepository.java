package demo.tesSwagger.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import demo.tesSwagger.model.crudObj;

public interface crudRepository extends JpaRepository<crudObj, Integer>    {

    crudObj findByUsername(String username);

    String deleteByUsername(String username);


}
