package org.barreragerman.repositoryDAO.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barreragerman.JDBC.DBConnectionPool;
import org.barreragerman.entity.BehaviourAttentionCall;
import org.barreragerman.entity.BehaviourAttentionCall;
import org.barreragerman.entity.BehaviourAttentionCall;
import org.barreragerman.entity.BehaviourAttentionCall;
import org.barreragerman.exceptions.DAO_exception;
import org.barreragerman.repositoryDAO.IBehaviourAttentionCallDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BehaviourAttentionCallDAOImpl implements IBehaviourAttentionCallDAO {

    private static final String SQL_SELECT_ALL = "SELECT id_behaviour_call, detailOfBehaviour, dateOfBehaviourCall, numberOfCall, isTutorAwareOf FROM behaviour_attention_calls ";
    private static final String SQL_INSERT = "INSERT INTO behaviour_attention_calls (detailOfBehaviour, dateOfBehaviourCall, numberOfCall, isTutorAwareOf) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE behaviour_attention_calls  SET  detailOfBehaviour = ?, dateOfBehaviourCall = ?,numberOfCall = ?,isTutorAwareOf = ? WHERE id_behaviour_call = ?";
    private static final String SQL_DELETE = "DELETE FROM behaviour_attention_calls  WHERE id_behaviour_call = ? ";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM behaviour_attention_calls  WHERE id_behaviour_call = ? ";
    private static final Logger LOGGER = LogManager.getLogger(CourseDAOImpl.class);
    private final Connection conn = DBConnectionPool.getInstance().getConnection();

    private BehaviourAttentionCall converInfo(ResultSet rs) throws DAO_exception {

        String detailOfBehaviour = null;
        Date dateOfBehaviourCall = null;
        int numberOfCall = 0;
        boolean isTutorAwareOf;
        BehaviourAttentionCall attCall = null;
        try {
            detailOfBehaviour = rs.getString("detailOfBehaviour");
            dateOfBehaviourCall = rs.getDate("dateOfBehaviourCall");
            numberOfCall = rs.getInt("numberOfCall");
            isTutorAwareOf = rs.getBoolean("isTutorAwareOf");

            attCall = new BehaviourAttentionCall(dateOfBehaviourCall,detailOfBehaviour,numberOfCall,isTutorAwareOf);
            attCall.setIdBehaviourCall(rs.getInt("id_behaviour_call"));

        } catch (SQLException e) {
            throw new DAO_exception("The attCall could not be created. ", e);
        }
        return attCall;
    }

    @Override
    public List<BehaviourAttentionCall> list() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BehaviourAttentionCall> attCall = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                attCall.add(converInfo(rs));
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
        return attCall;
    }

    @Override
    public BehaviourAttentionCall getById(Integer id) throws DAO_exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BehaviourAttentionCall attCall = null;
        try {
            stmt = conn.prepareStatement(SQL_FIND_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                attCall = converInfo(rs);
            } else {
                throw new DAO_exception("This register is not found ");
            }
            //the next line update the state into the DB; used after-INSERT-UPDATE-DELETE
            // stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("Error with this SQL query");
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
        return attCall;
    }

    @Override
    public void update(BehaviourAttentionCall attCall) throws DAO_exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,attCall.getDetailOfBehaviour());
            stmt.setDate(2, (java.sql.Date) attCall.getDateOfBehaviourCall());
            stmt.setInt(3,attCall.getNumberOfCall());
            stmt.setBoolean(4,attCall.isTutorAwareOf());
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
    public void insert(BehaviourAttentionCall attCall) throws DAO_exception, SQLException {
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
        try {
            stmt.setString(1,attCall.getDetailOfBehaviour());
            stmt.setDate(2, (java.sql.Date) attCall.getDateOfBehaviourCall());
            stmt.setInt(3,attCall.getNumberOfCall());
            stmt.setBoolean(4,attCall.isTutorAwareOf());
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
    public void delete(BehaviourAttentionCall attCall) throws DAO_exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
          stmt.setInt(1,attCall.getIdBehaviourCall());
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
