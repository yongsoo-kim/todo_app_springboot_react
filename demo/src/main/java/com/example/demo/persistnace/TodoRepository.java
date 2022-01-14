package com.example.demo.persistnace;

import com.example.demo.model.TodoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    //@Query("select * from Todo t where t.userId =?1")
    List<TodoEntity> findByUserId(String userId);


}
