package eu.arima.mejorarTesting.almacen.reservas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByIdFarmacia(Long idFarmacia);
}