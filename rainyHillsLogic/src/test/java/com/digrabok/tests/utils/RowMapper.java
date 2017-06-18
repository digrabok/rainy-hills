package com.digrabok.tests.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<E> {
    E mapRow(ResultSet rs, int rowNumber) throws SQLException;
}
