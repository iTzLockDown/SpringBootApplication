package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.Usuario;

public interface IUserService {
    public Usuario findByNombre(String email);
}
