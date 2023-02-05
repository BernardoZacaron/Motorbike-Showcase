package bz.java.motoreasy.controle;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.dto.MotoDTO;
import bz.java.motoreasy.repository.MotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class Control {
    @Autowired
    MotoRepo motoRepo;

    @GetMapping({"/", "/home"})
    public String callHomePage() {
        return "index";
    }

    @GetMapping("/catalogo")
    public String callCatalogoPage(Model model) {
        model.addAttribute("motos", motoRepo.findAll());

        return "catalogo";
    }

    @GetMapping({"/registrarMoto"})
    public String callRegistroMotoPage(Model model){
        model.addAttribute("novoMoto", new Moto());
        return "registrarMoto";
    }

    @Transactional
    @PostMapping("/saveMoto")
    public String saveMoto(@ModelAttribute MotoDTO novoMoto){
        Moto moto = new Moto(novoMoto.getModelo(), novoMoto.getCilindradas(), novoMoto.getPreco(), novoMoto.isAutomatica());

        motoRepo.save(moto);

        return "redirect:/home";
    }

    @Transactional
    @PostMapping("/addDesejo")
    public String saveMotoFav(@ModelAttribute("idMoto") long id){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        moto.toggleFavorito();

        motoRepo.saveAndFlush(moto);


        return "redirect:/catalogo";
    }

    @Transactional
    @PostMapping("/removerDesejo")
    public String removeMotoFav(@ModelAttribute("idMoto") long id){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        moto.setFavorita(false);

        motoRepo.saveAndFlush(moto);

        return "redirect:/lista-desejo";
    }

    @GetMapping("/lista-desejo")
    public String callListaDesejoPage(Model model) {
        List<Moto> motos = motoRepo.findAll();

        motos.stream().filter(moto -> moto.isFavorita());

        model.addAttribute("motosFavoritas", motos);

        return "listaDesejo";
    }
}
