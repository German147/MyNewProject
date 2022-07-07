package org.barreragerman.repositoryDAO.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barreragerman.JDBC.DBConnectionPool;
import org.barreragerman.entity.Address;
import org.barreragerman.exceptions.DAO_exception;
import org.barreragerman.repositoryDAO.IAddressDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements IAddressDAO {

    private static final String SQL_SELECT_ALL = "SELECT id_address, streetName, streetNumber FROM addresses";
    private static final String SQL_INSERT = "INSERT INTO addresses(streetName, streetNumber) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE addresses SET  streetName = ?, streetNumber = ? WHERE id_address = ?";
    private static final String SQL_DELETE = "DELETE FROM addresses WHERE id_address = ? ";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM addresses WHERE id_address = ? ";
    private static final Logger LOGGER = LogManager.getLogger(CourseDAOImpl.class);
    private final Connection conn = DBConnectionPool.getInstance().getConnection();

    private Address converInfo(ResultSet rs) throws DAO_exception {
        String streetName = null;
        Address address = null;
        try {
            streetName = rs.getString("streetName");
            int streetNumber = rs.getInt("streetNumber");
            address = new Address(streetName, streetNumber);
            address.setIdAddress(rs.getInt("id_address"));
        } catch (SQLException e) {
            throw new DAO_exception("The address could not be created. ", e);
        }
        return address;
    }

    @Override
    public List<Address> list() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Address> address = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                address.add(converInfo(rs));
            }
            //the next line update the state into the DB; used after-INSERT-UPDATE-DELETE
            //stmt.executeUpdate();
        } catch (SQLException | DAO_exception e) {
            LOGGER.debug(e.getMessage());
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            DBConnectionPool.getInstance().freeConnection(conn);
        }
        return address;
    }

    @Override
    public Address getById(Integer id) throws DAO_exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Address address = null;
        try {
            stmt = conn.prepareStatement(SQL_FIND_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                address = converInfo(rs);
            } else {
                throw new DAO_exception("This register is not found ");
            }
            //the next line update the state into the DB; used after-INSERT-UPDATE-DELETE
            // stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Error en esta sentencia SQL");
            LOGGER.debug(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAO_exception("There is an error at closing rs", e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new DAO_exception("There is an error at closing stmt", e);
                }
                DBConnectionPool.getInstance().freeConnection(conn);
            }
        }
        return address;
    }

    @Override
    public void update(Address address) throws DAO_exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, address.getStreetName());
            stmt.setInt(2, address.getStreetNumber());
            stmt.setInt(3,address.getIdAddress());
            //the next line update the state into the DB; used after-INSERT-UPDATE-DELETE
            if (stmt.executeUpdate() == 0) {
                throw new DAO_exception("The register was not updated  ");
            }
        } catch (SQLException e) {
            LOGGER.debug(e);
        } finally {
            DBConnectionPool.getInstance().freeConnection(conn);
        }
    }

    @Override
    public void insert(Address address) throws DAO_exception, SQLException {
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
        try {
            stmt.setString(1, address.getStreetName());
            stmt.setInt(2, address.getStreetNumber());
            //the next line update the state into the DB; used after-INSERT-UPDATE-DELETE
            if (stmt.executeUpdate() == 0) {
                throw new DAO_exception("The register was not inserted ");
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            LOGGER.debug(e.getMessage());
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    throw new DAO_exception("SQL Error ", ex);
                }
            DBConnectionPool.getInstance().freeConnection(conn);
        }
    }

    @Override
    public void delete(Address address) throws DAO_exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,address.getIdAddress());
            if (stmt.executeUpdate() == 0) {
                throw new DAO_exception("The delete could not be executed");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnectionPool.getInstance().freeConnection(conn);
        }
    }

    public void deleteByID(Integer integer)throws DAO_exception{
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,integer.intValue());
            if (stmt.executeUpdate() == 0) {
                throw new DAO_exception("The delete could not be executed");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBConnectionPool.getInstance().freeConnection(conn);
        }
    }
}
