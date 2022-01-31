package Model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int idPedidoAux;
	private int idClienteAux;
	private int idFuncionarioAux = 1;
	private int idEntregadorAux;
	private String dataPedido;
	private String horaPedido;
	private Double valorPedido;
	private String statusPedido;
	private List<Integer>idCardapioAux;
	private List<Integer>quantidade;
	
	
	public Order() {
		idCardapioAux = new ArrayList<>();
		quantidade = new ArrayList<>();
;
	}

	public Integer getIdPedidoAux() {
		return idPedidoAux;
	}

	public void setIdPedidoAux(Integer idPedidoAux) {
		this.idPedidoAux = idPedidoAux;
	}

	public Integer getIdClienteAux() {
		return idClienteAux;
	}

	public void setIdClienteAux(Integer idClienteAux) {
		this.idClienteAux = idClienteAux;
	}

	public Integer getIdFuncionarioAux() {
		return idFuncionarioAux;
	}

	public void setIdFuncionarioAux(Integer idFuncionarioAux) {
		this.idFuncionarioAux = idFuncionarioAux;
	}

	public Integer getIdEntregadorAux() {
		return idEntregadorAux;
	}

	public void setIdEntregadorAux(Integer idEntregadorAux) {
		this.idEntregadorAux = idEntregadorAux;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}

	public Double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	public int getIdCardapioAux(int position) {
		return idCardapioAux.get(position);
	}

	public void setIdCardapioAux(int idCardapioAux) {
		this.idCardapioAux.add(idCardapioAux);
	}

	public int getQuantidade(int position) {
		return quantidade.get(position);
	}

	public void setQuantidade(int quantidade) {
		this.quantidade.add(quantidade);
	}
	

}
