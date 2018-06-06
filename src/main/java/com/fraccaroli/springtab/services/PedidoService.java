package com.fraccaroli.springtab.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraccaroli.springtab.domain.ItemPedido;
import com.fraccaroli.springtab.domain.PagamentoComBoleto;
import com.fraccaroli.springtab.domain.Pedido;
import com.fraccaroli.springtab.domain.enums.EstadoPagamento;
import com.fraccaroli.springtab.repositories.ItemPedidoRepository;
import com.fraccaroli.springtab.repositories.PagamentoRepository;
import com.fraccaroli.springtab.repositories.PedidoRepository;
import com.fraccaroli.springtab.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;	
	
	@Autowired
	private ClienteService clienteService;		
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;	
	
	public Pedido find(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrato! id: " +  id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.00);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}	
}
