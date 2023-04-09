package bz.java.motoreasy.controle;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.dto.MotoDTO;
import bz.java.motoreasy.repository.AdicaoRepo;
import bz.java.motoreasy.repository.MotoRepo;
import bz.java.motoreasy.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.NotFoundException;

@Controller
@RequestMapping("/admin")
public class ControlAdministracao {
    @Autowired
    MotoRepo motoRepo;

    @Autowired
    UsuarioRepo userRepo;

    @Autowired
    AdicaoRepo adicaoRepo;

    @GetMapping("/admin/gerenciar")
    public String callGerenciarPage(Model model){
        model.addAttribute("todasMotos", motoRepo.findAll());

        return "gerenciarMoto";
    }

    @GetMapping("/admin/registrarMoto")
    public String callRegistroMotoPage(Model model){
        model.addAttribute("novoMoto", new MotoDTO());
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
}
