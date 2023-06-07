package capa_datos.datos;

import capa_datos.domain.PersonaDto;

import java.sql.SQLException;
import java.util.List;

public interface IPersonaDao {

    public int insertar(PersonaDto personaDto) throws SQLException;

    public int actualizar(PersonaDto personaDto) throws SQLException;

    public int borrar(PersonaDto personaDto) throws SQLException;

    public List<PersonaDto> seleccionar() throws SQLException;

}
