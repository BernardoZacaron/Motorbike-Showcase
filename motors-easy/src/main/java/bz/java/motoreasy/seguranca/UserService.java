package bz.java.motoreasy.seguranca;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.UsuarioDTO;
import bz.java.motoreasy.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    PasswordEncoder pe;
    @Autowired
    private UsuarioRepo repo;
    @Override
    public Usuario registerNewUserAccount(UsuarioDTO userDto){

        Usuario user = new Usuario();

        user.setUsername(userDto.getUsername());
        user.setNome(userDto.getNome());
        user.setEmail(userDto.getEmail());
        user.setSenha(pe.encode(userDto.getSenha()));
        //user.setListaDesejo(new ArrayList<Moto>());

        user.setRoles(Arrays.asList("ROLE_USER"));

        return repo.save(user);
    }

    private boolean usernameExistente(String username) {
        return repo.findByUsername(username) != null;
    }

}
