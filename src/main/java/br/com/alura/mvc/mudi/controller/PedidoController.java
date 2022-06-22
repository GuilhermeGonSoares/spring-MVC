package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.PedidoDto;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;
import br.com.alura.mvc.mudi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/formulario")
    public String formulario(PedidoDto pedidoDto){
        return "pedido/formulario";
    }

    @PostMapping("/novo")
    public String novo(@Valid PedidoDto pedidoDto, BindingResult result){
        if(result.hasErrors()){
            return "pedido/formulario";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userRepository.findByUsername(username);

        Pedido pedido = pedidoDto.toPedido();
        pedido.setUser(u);

        pedidoService.cadastrar(pedido);
        return "redirect:/home";
    }

}
