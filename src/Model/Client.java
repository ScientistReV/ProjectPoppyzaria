package Model;


//Modelo cliente
public class Client {
		private Integer idCliente;
		private String nomeCliente;
		private String enderecoCliente;
		private String complementoEnderecoCliente;
		private String bairroCliente;
		private String telefoneCliente;
		private String dataCadastro;
		

		public Client() {
		}


		public int getIdCliente() {
			return idCliente;
		}


		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}


		public String getNomeCliente() {
			return nomeCliente;
		}


		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}


		public String getEnderecoCliente() {
			return enderecoCliente;
		}


		public void setEnderecoCliente(String enderecoCliente) {
			this.enderecoCliente = enderecoCliente;
		}
		
		public String getComplementoEnderecoCliente() {
			return complementoEnderecoCliente;
		}


		public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
			this.complementoEnderecoCliente = complementoEnderecoCliente;
		}


		public String getBairroCliente() {
			return bairroCliente;
		}


		public void setBairroCliente(String bairroCliente) {
			this.bairroCliente = bairroCliente;
		}
		
		public String getTelefoneCliente() {
			return telefoneCliente;
		}


		public void setTelefoneCliente(String telefoneCliente) {
			this.telefoneCliente = telefoneCliente;
		}


		public String getDataCadastro() {
			return dataCadastro;
		}


		public void setDataCadastro(String dataCadastro) {
			this.dataCadastro = dataCadastro;
		}
		
		
}