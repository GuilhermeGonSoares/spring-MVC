package br.com.alura.mvc.mudi.service;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public List<Pedido> findByStatus(StatusPedido statusPedido, Pageable page){
        return pedidoRepository.findByStatus(statusPedido, page);
    }

    public List<Pedido> findByStatusAndUser(StatusPedido status, String username) {
        return pedidoRepository.findByStatusAndUser(status, username);
    }
    public void cadastrar(Pedido pedido) {
        pedidoRepository.save(pedido);
        System.out.println("Pedido cadastrado!");
    }

    public List<Pedido> findAllByUsuario(String username) {
        return pedidoRepository.findAllByusuario(username);
    }
}
