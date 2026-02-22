package com.gab.DesafioBack_End.repositorys;

import com.gab.DesafioBack_End.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByEmail(String email);
}
