package nl.han.aim.oosevt.lamport.data.dao.role;

import nl.han.aim.oosevt.lamport.data.entity.Role;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RoleDataMapperImpl implements RoleDataMapper {

    private static final Logger LOGGER = Logger.getLogger(RoleDataMapperImpl.class.getName());

    @Override
    public Role getFromResultSet(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("role_id");
            String name = resultSet.getString("role_name");

            return new Role(id, name, new ArrayList<>());
        } catch(SQLException e) {
            LOGGER.log(Level.SEVERE, "RoleDataMapperImpl::An error occurred mapping the entity!", e);
        }
        return null;
    }
}
