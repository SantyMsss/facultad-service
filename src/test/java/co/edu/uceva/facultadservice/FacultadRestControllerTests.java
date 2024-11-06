package co.edu.uceva.facultadservice;


import co.edu.uceva.facultadservice.model.entities.Facultad;
import co.edu.uceva.facultadservice.model.service.FacultadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas unitarias (unit tests) para la API RESTful que se encarga de realizar operaciones CRUD sobre una entidad
 * llamada "Facultad".
 * Se importan las clases necesarias para realizar las pruebas (MockMvc, ObjectMapper, etc.), se inyecta el servicio
 * que se encarga de realizar las operaciones sobre la entidad "Facultad" (FacultadService), y se definen varios métodos de
 * prueba para la API RESTful, que comprueban el correcto funcionamiento de los métodos:
 * GET, POST, PUT y DELETE de la API.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FacultadRestControllerTests {

    /**
     * Esta anotación @Autowired permite la inyección de dependencia, lo que significa que
     * wac (WebApplicationContext) se inicializará automáticamente con el contexto de la aplicación web.
     * El WebApplicationContext proporciona acceso a los componentes y configuraciones de Spring.
     */
    @Autowired
    private WebApplicationContext wac;

    /**
     * MockMvc es una clase que se utiliza para simular las solicitudes HTTP y probar
     * controladores y endpoints sin necesidad de un navegador web en una prueba de integración.
     */
    private MockMvc mockMvc;

    @Autowired
    private FacultadService facultadService;

    /**
     * Inicializa los objetos necesarios para la prueba. En el ejemplo de código dado, este método se utiliza para
     * inicializar el objeto MockMvc, que se utiliza para simular el envío de solicitudes HTTP en la prueba de la
     * clase FacultadRestController.
     */
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Prueba del método GET "/api/v1/facultad-service/hola/{nombre}", que comprueba que se recibe el nombre correcto
     * en la respuesta.
     * @throws Exception Se lanza una excepción si no devuelve el mensaje correcto.
     */
    @Test
    public void testHolaMundo() throws Exception {
        String nombre = "pedriño holiii";
        this.mockMvc.perform(get("/api/v1/facultad-service/hola/{nombre}", nombre))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola " + nombre));
    }

    /**
     * Prueba del método GET "/api/v1/facultad-service/facultades", que comprueba que se recibe una lista de las facultades en la respuesta.
     * @throws Exception Se lanza una excepción si no devuelve la lista de las facultades correcta.
     */
    @Test
    public void testListar() throws Exception {
        Facultad facultad1 = new Facultad(null,"Facultad de Ciencias Ambientales","Dr. Pedro Rojas","Presencial","Gestion Ambiental y Cambio Climatico","Facultad enfocada en la proteccion del medio ambiente y la sostenibilidad.",new Date(),889900112,"ambientales@ejemplo.com","Ingenieria Ambiental, Ciencias Ambientales","2024-2025");
        Facultad facultad2 = new Facultad(null,"Facultad de Ciencias Ambientales","Dr. Pedro Rojas","Presencial","Gestion Ambiental y Cambio Climatico","Facultad enfocada en la proteccion del medio ambiente y la sostenibilidad.",new Date(),889900112,"ambientales@ejemplo.com","Ingenieria Ambiental, Ciencias Ambientales","2024-2025");
        facultadService.save(facultad1);
        facultadService.save(facultad2);
        this.mockMvc.perform(get("/api/facultad-service/facultades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].facultad", is(facultad1.getNombre_facu())))
                .andExpect(jsonPath("$[0].facultad", is(facultad2.getNombre_facu())));
        facultadService.delete(facultad1);
        facultadService.delete(facultad2);
    }

    /**
     * Prueba del método GET "/api/v1/facultad-service/facultades/{id}", que comprueba que se recibe la facultad correcta en la respuesta.
     * @throws Exception Se lanza una excepción si no se encuentra la facultad con el id especificado.
     */
    @Test
    public void testBuscarFacultad() throws Exception {

        Facultad facultad = new Facultad(null,"Facultad de Ciencias Ambientales","Dr. Pedro Rojas","Presencial","Gestion Ambiental y Cambio Climatico","Facultad enfocada en la proteccion del medio ambiente y la sostenibilidad.",new Date(),889900112,"ambientales@ejemplo.com","Ingenieria Ambiental, Ciencias Ambientales","2024-2025");
        facultadService.save(facultad);

        this.mockMvc.perform(get("/api/v1/facultad-service/facultades/{id}", facultad.getCodigo_facu()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultad", is(facultad.getNombre_facu())));

        facultadService.delete(facultad);
    }

    /**
     * Prueba del método POST "/api/v1/facultad-service/facultades", que comprueba que se crea una nueva facultad correctamente.
     * @throws Exception Se lanza una excepción si no se encuentra la facultad con el id especificado.
     */
    @Test
    public void testCrearFacultad() throws Exception {
        Facultad facultad = new Facultad(null, "Facultad de Ciencias Ambientales", "Dr. Pedro Rojas", "Presencial", "Gestion Ambiental y Cambio Climatico", "Facultad enfocada en la proteccion del medio ambiente y la sostenibilidad.", new Date(), 889900112, "ambientales@ejemplo.com", "Ingenieria Ambiental, Ciencias Ambientales", "2024-2025");

        this.mockMvc.perform(post("/api/v1/facultad-service/facultades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facultad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultad", is(facultad.getNombre_facu())));
        facultadService.delete(facultad);
    }

    /**
     * Prueba del método PUT "/api/v1/facultad-service/facultades", que comprueba que se actualiza una facultad correctamente.
     * @throws Exception Se lanza una excepción si no se encuentra la facultad con el id especificado.
     */
    @Test
    public void testActualizarFacultad() throws Exception {
        Facultad facultad = new Facultad(null, "Facultad de Ciencias Ambientales", "Dr. Pedro Rojas", "Presencial", "Gestion Ambiental y Cambio Climatico", "Facultad enfocada en la proteccion del medio ambiente y la sostenibilidad.", new Date(), 889900112, "ambientales@ejemplo.com", "Ingenieria Ambiental, Ciencias Ambientales", "2024-2025");
        facultadService.save(facultad);
        facultad.setNombre_facu("casi que no");

        this.mockMvc.perform(put("/api/v1/facultad-service/facultades/{id}",facultad.getCodigo_facu())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(facultad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultad", is(facultad.getNombre_facu())));
        facultadService.delete(facultad);
    }

    /**
     * Prueba del método DELETE "/api/v1/facultad-service/facultades/{id}", que comprueba que se elimina una facultad correctamente.
     * @throws Exception Se lanza una excepción si no se encuentra la facultad con el id especificado.
     */
    @Test
    public void testBorrarFacultad() throws Exception {
        Facultad facultad = new Facultad(null, "Facultad de Ciencias Ambientales", "Dr. Pedro Rojas", "Presencial", "Gestion Ambiental y Cambio Climatico", "Facultad enfocada en la proteccion del medio ambiente y la sostenibilidad.", new Date(), 889900112, "ambientales@ejemplo.com", "Ingenieria Ambiental, Ciencias Ambientales", "2024-2025");
        facultad = facultadService.save(facultad);

        this.mockMvc.perform(delete("/api/v1/facultad-service/facultades/{id}", facultad.getCodigo_facu()))
                .andExpect(status().isOk());
        assertNull(facultadService.findById(facultad.getCodigo_facu()));
    }

    /**
     * Método para convertir un objeto a una cadena JSON
     *
     * @param obj Objeto a convertir
     * @return Cadena JSON
     */
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}