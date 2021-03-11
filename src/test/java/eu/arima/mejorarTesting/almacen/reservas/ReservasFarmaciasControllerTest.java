package eu.arima.mejorarTesting.almacen.reservas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservasFarmaciasController.class)
@Tag("testIntegracion")
class ReservasFarmaciasControllerTest {

    public static final int ID_FARMACIA = 1;
    public static final int ID_MEDICAMENTO = 2;
    public static final int UNIDADES = 3;
    public static final long ID_RESERVA = 88L;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReservasService reservasService;

    @Test
    @DisplayName("La petición (POST) /reservas con la información para una reserva realiza la reserva devolviendo el id de la misma")
    void reservar_devuelve_id_reserva() throws Exception {

        Reserva reserva = new Reserva(ID_FARMACIA, ID_MEDICAMENTO, UNIDADES);
        reserva.setId(ID_RESERVA);
        Optional<Reserva> reservaOptional = Optional.of(reserva);
        when(reservasService.reservarMedicamento(ID_FARMACIA, ID_MEDICAMENTO, UNIDADES)).thenReturn(reservaOptional);

        ResultActions resultado = mockMvc.perform(post("/reservas")
                .contentType(APPLICATION_JSON)
                .content("{\"idFarmacia\": " + ID_FARMACIA + ", \"idMedicamento\": " + ID_MEDICAMENTO + ", \"unidadesReservar\": " + UNIDADES + "}"));

        assertAll("La respuesta tiene el id de la reserva",
                () -> resultado.andExpect(status().isCreated()),
                () -> assertEquals(ID_RESERVA, Long.valueOf(resultado.andReturn().getResponse().getContentAsString())));
    }
}