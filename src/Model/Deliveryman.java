package Model;

public class Deliveryman {
	
	private Integer idEntregador;
	private String nomeEntregador;
	private String statusEntregador;
	private String dataCadastro;
	
	public Deliveryman() {
		
	}

	public Integer getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(Integer idEntregador) {
		this.idEntregador = idEntregador;
	}

	public String getNomeEntregador() {
		return nomeEntregador;
	}

	public void setNomeEntregador(String nomeEntregador) {
		this.nomeEntregador = nomeEntregador;
	}

	public String getStatusEntregador() {
		return statusEntregador;
	}

	public void setStatusEntregador(String statusEntregador) {
		this.statusEntregador = statusEntregador;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
