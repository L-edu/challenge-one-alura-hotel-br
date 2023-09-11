package jdbc.controller;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdb.factory.ConnectionFactory;
import jdbc.dao.HospedeDAO;
import jdbc.modelo.Hospede;


public class HospedeController {
	 private HospedeDAO hospedesDAO;

	 public HospedeController() {
			Connection connection = new ConnectionFactory().recuperarConexao();
			this.hospedesDAO = new HospedeDAO(connection);
		}
	 
		public void salvar(Hospede hospedes) {
			this.hospedesDAO.salvar(hospedes);
		}
		public List<Hospede> listarHospedes() {
			return this.hospedesDAO.listarHospedes();
		}
		
		public List<Hospede> listarHospedesId(String id) {
			return this.hospedesDAO.buscarId(id);
		}

	public void atualizar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Integer idReserva, Integer id) {
		this.hospedesDAO.atualizar(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva, id);
	}
		public void deletar(Integer id) {
			this.hospedesDAO.deletar(id);
		}
}