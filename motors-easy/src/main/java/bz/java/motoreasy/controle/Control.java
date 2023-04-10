package bz.java.motoreasy.controle;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.MotoDTO;
import bz.java.motoreasy.model.dto.UsuarioDTO;
import bz.java.motoreasy.model.util.Adicao;
import bz.java.motoreasy.repository.AdicaoRepo;
import bz.java.motoreasy.repository.MotoRepo;
import bz.java.motoreasy.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class Control {
    @Autowired
    MotoRepo motoRepo;

    @Autowired
    UsuarioRepo userRepo;

    @Autowired
    AdicaoRepo adicaoRepo;

    @Autowired
    PasswordEncoder pe;


    @GetMapping({"/", "/home"})
    public String callHomePage(Model model, Authentication authentication){
        String nomeUsuario = null;
        if(authentication!=null) {
            Usuario logado = (Usuario) authentication.getPrincipal();
            nomeUsuario = logado.getNome();

            if(logado.getAdicoes()==null){
                logado.setAdicoes(new ArrayList<Adicao>());
            }
        }

        model.addAttribute("nomeUsuario", nomeUsuario);
        return "index";
    }

    @GetMapping("/catalogo")
    public String callCatalogoPage(Model model, Authentication authentication) {
        List<MotoDTO> motos = new ArrayList<>();
        List<Moto> todas = motoRepo.findAll();

        if(authentication!=null && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            Usuario logado = (Usuario) authentication.getPrincipal();
            List<Moto> favoritadas = listaFavoritos(logado);
            List<Moto> naoFavoritas = new ArrayList<>(todas);

            for (Moto m : favoritadas) {
                motos.add(new MotoDTO(m, true));
            }
            for (Moto m : naoFavoritas) {
                if(!motos.contains(m))
                    motos.add(new MotoDTO(m, false));
            }
            motos = filtrarDuplicadas(motos);
            motos = motos.stream().filter(MotoDTO::isVisivel).toList();
        }else{
            for (Moto m : todas) {
                motos.add(new MotoDTO(m, false));
            }
            motos = motos.stream().filter(MotoDTO::isVisivel).toList();
        }

        model.addAttribute("motos", motos);

        return "catalogo";
    }

    @GetMapping("/login")
    public String callLoginPage(){
        return "login";
    }

    @GetMapping("/registrarUsuario")
    public String callRegistrarUsuarioPage(Model model){
        model.addAttribute("novoUsuario", new UsuarioDTO());

        return "registrarUsuario";
    }

    @PostMapping("/saveUsuario")
    public String saveUsuario(@ModelAttribute("novoUsuario") UsuarioDTO novoUsuario){
        Usuario registrando = new Usuario(novoUsuario.getNome(), novoUsuario.getUsername(), novoUsuario.getEmail(), pe.encode(novoUsuario.getSenha()));

        userRepo.save(registrando);

        return "redirect:/login";
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
