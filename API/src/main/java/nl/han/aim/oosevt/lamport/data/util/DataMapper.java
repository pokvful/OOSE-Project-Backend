package nl.han.aim.oosevt.lamport.data.util;

import java.sql.ResultSet;

public interface DataMapper<T> {
    T getFromResultSet(ResultSet resultSet);
}
