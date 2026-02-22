package com.gab.DesafioBack_End.repositorys;

import com.gab.DesafioBack_End.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,Integer> {
}
