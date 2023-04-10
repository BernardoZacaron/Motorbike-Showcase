package bz.java.motoreasy.controle;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.MotoDTO;
import bz.java.motoreasy.model.util.Adicao;
import bz.java.motoreasy.repository.AdicaoRepo;
import bz.java.motoreasy.repository.MotoRepo;
import bz.java.motoreasy.repository.UsuarioRepo;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cliente")
public class ControlCliente {
    @Autowired
    MotoRepo motoRepo;

    @Autowired
    AdicaoRepo adicaoRepo;

    @Transactional
    @PostMapping("/addDesejo")
    public String saveMotoFav(@ModelAttribute("idMoto") long id, Authentication authentication){
        Usuario logado = (Usuario) authentication.getPrincipal();

        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        if(listaFavoritos(logado).contains(moto)){
            adicaoRepo.removerMotoDaLista(logado.getId(), moto.getId());
        }else{
            adicaoRepo.save(new Adicao(logado, moto));
        }
        return "redirect:/catalogo";
    }

    @Transactional
    @PostMapping("/removerDesejo")
    public String removeMotoFav(@ModelAttribute("idMoto") long id, Authentication authentication){
        Usuario logado = (Usuario) authentication.getPrincipal();
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        adicaoRepo.removerMotoDaLista(logado.getId(), moto.getId());

        return "redirect:/cliente/lista-desejo";
    }

    @GetMapping("/lista-desejo")
    public String callListaDesejoPage(Model model, Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        List<Moto> motosFavoritas = listaFavoritos(logado);

        model.addAttribute("motosFavoritas", motosFavoritas);

        return "listaDesejo";
    }


    List<MotoDTO> filtrarDuplicadas(List<MotoDTO> listaOriginal){
        Set<MotoDTO> filtrada = listaOriginal.stream().collect(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(MotoDTO::getId)))
        );

        return filtrada.stream().toList();
    }
    List<Moto> listaFavoritos(Usuario logado){
        List<Adicao> adicoes = adicaoRepo.findByUsuario(logado);
        List<Moto> favoritas = new ArrayList<>();

        for (Adicao ad : adicoes) {
            favoritas.add(motoRepo.getById(ad.getMoto().getId()));
        }

        return favoritas;
    }
}
