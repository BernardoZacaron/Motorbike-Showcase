package bz.java.motoreasy.seguranca;

import bz.java.motoreasy.model.Usuario;
import bz.java.motoreasy.model.dto.UsuarioDTO;

public interface IUserService {
    Usuario registerNewUserAccount(UsuarioDTO userDto);
}
