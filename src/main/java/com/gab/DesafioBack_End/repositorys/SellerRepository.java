package com.gab.DesafioBack_End.repositorys;

import com.gab.DesafioBack_End.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
