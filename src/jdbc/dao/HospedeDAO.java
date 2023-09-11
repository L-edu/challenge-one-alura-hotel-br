package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.modelo.Hospede;

public class HospedeDAO {

	private Connection connection;

	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	// Create - Insert
	public void salvar(Hospede hospede) {
		try {
			String sql = "INSERT INTO hospedes (nome, sobrenome, dataNascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setDate(3, hospede.getDataNascimento());
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setInt(6, hospede.getIdReserva());
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Search - Select
	public List<Hospede> listarHospedes() {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {
			String sql = "SELECT id, nome, sobrenome, dataNascimento, nacionalidade, telefone, id_reserva FROM hospedes";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEmHospede(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospede> buscarId(String id) {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		try {

			String sql = "SELECT id, nome, sobrenome, dataNascimento, nacionalidade, telefone, id_reserva FROM hospedes WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();
				transformarResultSetEmHospede(hospedes, pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Delete - delete
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM hospedes WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Update - update
	public void atualizar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone,
			Integer idReserva, Integer id) {
		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE reservas SET nome=?, sobrenome=?, dataNascimento=?, nacionalidade=?, telefone=?, id_reserva=? WHERE id = ?")) {
			stm.setString(1, nome);
			stm.setString(2, sobrenome);
			stm.setDate(3, dataNascimento);
			stm.setString(4, nacionalidade);
			stm.setString(5, telefone);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultSetEmHospede(List<Hospede> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4),
						rst.getString(5), rst.getString(6), rst.getInt(7));
				reservas.add(hospede);
			}
		}

	}

}
