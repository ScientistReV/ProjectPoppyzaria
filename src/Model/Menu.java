package Model;

public class Menu {
	
	private Integer idCardapio;
	private String descricaoCardapio;
	private String tipoCardapio;
	private Double valorCardapio;
	
	public Menu() {
	}

	public Integer getIdCardapio() {
		return idCardapio;
	}

	public void setIdCardapio(Integer idCardapio) {
		this.idCardapio = idCardapio;
	}

	public String getDescricaoCardapio() {
		return descricaoCardapio;
	}

	public void setDescricaoCardapio(String descricaoCardapio) {
		this.descricaoCardapio = descricaoCardapio;
	}

	public String getTipoCardapio() {
		return tipoCardapio;
	}

	public void setTipoCardapio(String tipoCardapio) {
		this.tipoCardapio = tipoCardapio;
	}

	public Double getValorCardapio() {
		return valorCardapio;
	}

	public void setValorCardapio(Double valorCardapio) {
		this.valorCardapio = valorCardapio;
	}

}
