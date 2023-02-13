package bz.java.motoreasy.seguranca;

import bz.java.motoreasy.model.Moto;
import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.UsuarioDTO;
import bz.java.motoreasy.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class UserService implements IUserService {
    @Autowired
    private UsuarioRepo repo;
    @Override
    public Usuario registerNewUserAccount(UsuarioDTO userDto){

        Usuario user = new Usuario();

        user.setUsername(userDto.getUsername());
        user.setNome(userDto.getNome());
        user.setEmail(userDto.getEmail());
        user.setSenha(userDto.getSenha());
        user.setListaDesejo(new ArrayList<Moto>());

        user.setRoles(Arrays.asList("ROLE_USER"));

        return repo.save(user);
    }

    private boolean usernameExistente(String username) {
        return repo.findByUsername(username) != null;
    }

}
