package com.gab.DesafioBack_End.repositorys;

import com.gab.DesafioBack_End.entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
