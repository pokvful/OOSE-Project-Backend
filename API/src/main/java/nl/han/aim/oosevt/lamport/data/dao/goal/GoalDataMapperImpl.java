package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.data.entity.Goal;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GoalDataMapperImpl implements GoalDataMapper {
    private static final Logger LOGGER = Logger.getLogger(GoalDataMapperImpl.class.getName());

    @Override
    public Goal getFromResultSet(ResultSet resultSet) {
        try {
            return new Goal(
                    resultSet.getInt("goal_id"),
                    resultSet.getString("goal_name"),
                    new ArrayList<>());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "goalFromResultSet::A database error occurred!", e);
        }
        return null;
    }
}
