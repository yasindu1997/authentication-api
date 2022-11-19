package lk.slit.demo.repository;

import lk.slit.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo  extends CrudRepository<User, String> {
}
