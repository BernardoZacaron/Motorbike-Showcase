package bz.java.motoreasy.controle;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.MotoDTO;
import bz.java.motoreasy.model.dto.UsuarioDTO;
import bz.java.motoreasy.repository.MotoRepo;
import bz.java.motoreasy.repository.UsuarioRepo;
import bz.java.motoreasy.seguranca.UserLogado;
import bz.java.motoreasy.seguranca.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.NotFoundException;


@Controller
@RequestMapping("/")
public class Control {
    @Autowired
    MotoRepo motoRepo;

    @Autowired
    UsuarioRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder pe;

    //Aberto
    @GetMapping({"/", "/home"})
    public String callHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        model.addAttribute("nomeUsuario", nomeUsuario);
        return "index";
    }

    @GetMapping("/catalogo")
    public String callCatalogoPage(Model model/*, Authentication authentication*/) {
//        UserLogado logado = (UserLogado) authentication.getPrincipal();
//        Usuario usuario = logado.getUsuario();
//
//        List<Moto> favoritadas = usuario.getFavoritas();
//        List<MotoDTO> motos = new ArrayList<>();
//        for (Moto m : motoRepo.findAll()) {
//            motos.add(new MotoDTO(motoRepo.findById(m.getId()).get()));
//        }
//        for(MotoDTO mDTO : motos){
//            for
//        }
//
//        List<MotoDTO> motosFavoritadas = new ArrayList<>();
//        for(MotoDTO mDTO : motos){
//            mDTO.setFavoritada(true);
//        }

        model.addAttribute("motos", motoRepo.findAll());

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


    //Clientes logados
    @Transactional
    @PostMapping("/cliente/addDesejo")
    public String saveMotoFav(@ModelAttribute("idMoto") long id, Authentication authentication){
        UserLogado logado = (UserLogado) authentication.getPrincipal();
        Usuario usuario = logado.getUsuario();
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        usuario.adicionarFavorita(moto);

        userRepo.saveAndFlush(logado.getUsuario());

        return "redirect:/catalogo";
    }

    @Transactional
    @PostMapping("/cliente/removerDesejo")
    public String removeMotoFav(@ModelAttribute("idMoto") long id, Authentication authentication){
        UserLogado logado = (UserLogado) authentication.getPrincipal();
        Usuario usuario = logado.getUsuario();
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        usuario.getFavoritas().remove(moto);

        userRepo.saveAndFlush(usuario);

        return "redirect:/cliente/lista-desejo";
    }

    @GetMapping("/cliente/lista-desejo")
    public String callListaDesejoPage(Model model, Authentication authentication) {
        UserLogado logado = (UserLogado) authentication.getPrincipal();
        Usuario usuario = logado.getUsuario();


        model.addAttribute("motosFavoritas", usuario.getFavoritas());

        return "listaDesejo";
    }


    //Admin apenas
    @GetMapping({"/admin/registrarMoto"})
    public String callRegistroMotoPage(Model model){
        model.addAttribute("novoMoto", new Moto());
        return "registrarMoto";
    }

    @Transactional
    @PostMapping("/admin/saveMoto")
    public String saveMoto(@ModelAttribute MotoDTO novoMoto){
//        Moto moto = new Moto(novoMoto.getModelo(), novoMoto.getCilindradas(), novoMoto.getPreco(), novoMoto.isAutomatica());
//
//        motoRepo.save(moto);

        return "redirect:/home";
    }

    @GetMapping("/admin/gerenciar")
    public String callGerenciarPage(Model model){
        return "gerenciarMoto";
    }

    @GetMapping("/admin/editarMoto")
    public String callEditarMotoPage(@ModelAttribute("idMoto") long id){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        return "redirect:/";
    }

    @GetMapping("/admin/excluirMoto")
    public String excluirMoto(@ModelAttribute("idMoto") long id){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        return "redirect:/";
    }

    @Transactional
    @PostMapping("/admin/ocultarMoto")
    public String ocultarMoto(@ModelAttribute("idMoto") long id){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        return "redirect:/";
    }
}
