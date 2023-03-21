package bz.java.motoreasy.controle;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.MotoDTO;
import bz.java.motoreasy.model.dto.UsuarioDTO;
import bz.java.motoreasy.model.util.AdicaoLista;
import bz.java.motoreasy.repository.AdicaoRepo;
import bz.java.motoreasy.repository.MotoRepo;
import bz.java.motoreasy.repository.UsuarioRepo;
import bz.java.motoreasy.seguranca.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class Control {
    @Autowired
    MotoRepo motoRepo;

    @Autowired
    UsuarioRepo userRepo;

//    @Autowired
//    ListaRepo listaRepo;

    @Autowired
    AdicaoRepo adicaoRepo;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder pe;

    //Aberto
    @GetMapping({"/", "/home"})
    public String callHomePage(Model model, Authentication authentication){
        String nomeUsuario = null;
        if(authentication!=null) {
            Usuario logado = (Usuario) authentication.getPrincipal();
            nomeUsuario = logado.getNome();

            if(logado.getAdicoes()==null){
                logado.setAdicoes(new ArrayList<AdicaoLista>());
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
            List<Moto> favoritadas = new ArrayList<>();
            if(logado.getAdicoes() == null)
                favoritadas = new ArrayList<>();
            else{
                favoritadas = listaFavoritos(logado);
            }


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


    //Clientes logados
    @Transactional
    @PostMapping("/cliente/addDesejo")
    public String saveMotoFav(@ModelAttribute("idMoto") long id, Authentication authentication){
        Usuario logado = (Usuario) authentication.getPrincipal();

        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);

        if(listaFavoritos(logado).contains(moto)){
            adicaoRepo.deleteByUsuarioAndMoto(logado, moto);
        }else{
            adicaoRepo.save(new AdicaoLista(logado, moto));
        }
        return "redirect:/catalogo";
    }

//    @Transactional
//    @PostMapping("/cliente/removerDesejo")
//    public String removeMotoFav(@ModelAttribute("idMoto") long id, Authentication authentication){
//        Usuario logado = (Usuario) authentication.getPrincipal();
//
//        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);
//        ListaFavoritos lista = listaRepo.findById(logado.getLista().getId()).orElseThrow(NotFoundException::new);
//
//        //listaRepo.removerMoto(lista.getId(), moto.getId());
//        //lista.getMotos().stream().filter(m -> m.getId() == moto.getId()).toList().remove(m);
//        lista.getMotos().removeIf(m -> m.getId() == moto.getId());
//
//        listaRepo.saveAndFlush(lista);
//
//        return "redirect:/cliente/lista-desejo";
//    }

    @GetMapping("/cliente/lista-desejo")
    public String callListaDesejoPage(Model model, Authentication authentication) {
        Usuario logado;
        List<Moto> motosFavoritas = new ArrayList<>();

        if(authentication.isAuthenticated()){
            logado = (Usuario) authentication.getPrincipal();
            if(logado.getAdicoes()!=null)
                motosFavoritas = listaFavoritos(logado);
        }else{
            return "redirect:/login";
        }

        model.addAttribute("motosFavoritas", motosFavoritas);

        return "listaDesejo";
    }


    //Admin apenas
    @GetMapping("/admin/gerenciar")
    public String callGerenciarPage(Model model){
        model.addAttribute("todasMotos", motoRepo.findAll());

        return "gerenciarMoto";
    }

    @GetMapping("/admin/registrarMoto")
    public String callRegistroMotoPage(Model model){
        model.addAttribute("novoMoto", new Moto());
        return "registrarMoto";
    }

    @Transactional
    @PostMapping("/admin/saveMoto")
    public String saveMoto(@ModelAttribute MotoDTO novoMoto){
        Moto moto = new Moto(novoMoto);

        motoRepo.save(moto);

        return "redirect:/home";
    }

    @GetMapping("/admin/editarMoto")
    public String callEditarMotoPage(@ModelAttribute("idMoto") long id, Model model){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("moto", moto);

        return "editarMoto";
    }
    @Transactional
    @PostMapping("/admin/editarMoto")
    public String saveMotoEditada(@ModelAttribute Moto moto){
        Moto motoEditada = motoRepo.getById(moto.getId());
        motoEditada.setMarca(moto.getMarca());
        motoEditada.setModelo(moto.getModelo());
        motoEditada.setCilindradas(moto.getCilindradas());
        motoEditada.setPreco(moto.getPreco());
        motoEditada.setTerreno(moto.getTerreno());
        motoEditada.setAutomatica(moto.isAutomatica());

        motoRepo.saveAndFlush(motoEditada);

        return "redirect:/admin/gerenciar";
    }

    @Transactional
    @PostMapping("/admin/ocultarMoto")
    public String ocultarMoto(@ModelAttribute("idMoto") long id){
        Moto moto = motoRepo.findById(id).orElseThrow(NotFoundException::new);
        moto.toggleVisibilidade();

        motoRepo.saveAndFlush(moto);

        return "redirect:/admin/gerenciar";
    }

    @Transactional
    @PostMapping("/admin/excluirMoto")
    public String excluirMoto(@ModelAttribute("idMoto") long id){
        motoRepo.deleteById(id);

        return "redirect:/admin/gerenciar";
    }


    List<MotoDTO> filtrarDuplicadas(List<MotoDTO> listaOriginal){
        Set<MotoDTO> filtrada = listaOriginal.stream().collect(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(MotoDTO::getId)))
        );

        return filtrada.stream().toList();
    }

    List<Moto> listaFavoritos(Usuario logado){
        return adicaoRepo.findByUsuario(logado);
    }
}
