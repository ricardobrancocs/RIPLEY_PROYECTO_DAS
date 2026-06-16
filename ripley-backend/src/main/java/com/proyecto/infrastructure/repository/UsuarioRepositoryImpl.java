package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Usuario;
import com.proyecto.domain.interfaces.IUsuarioRepository;
import com.proyecto.infrastructure.mapping.UsuarioMapper;
import com.proyecto.infrastructure.models.UsuarioModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IUsuarioRepository usando JPA + Hibernate
 * Tabla BD: usuarios
 */
@ApplicationScoped
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        UsuarioModel model = UsuarioMapper.toModel(usuario);
        if (model.getIdUsuario() == null) {
            em.persist(model);
        } else {
            model = em.merge(model);
        }
        return UsuarioMapper.toDomain(model);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return em.createQuery(
                "SELECT u FROM UsuarioModel u WHERE u.correo = :correo",
                UsuarioModel.class)
                .setParameter("correo", correo)
                .getResultStream()
                .findFirst()
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer idUsuario) {
        UsuarioModel model = em.find(UsuarioModel.class, idUsuario);
        return Optional.ofNullable(UsuarioMapper.toDomain(model));
    }

    @Override
    @Transactional
    public void incrementarIntentosFallidos(Integer idUsuario) {
        em.createQuery(
                "UPDATE UsuarioModel u SET u.intentosFallidos = u.intentosFallidos + 1 " +
                "WHERE u.idUsuario = :id")
                .setParameter("id", idUsuario)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void resetearIntentosFallidos(Integer idUsuario) {
        em.createQuery(
                "UPDATE UsuarioModel u SET u.intentosFallidos = 0 " +
                "WHERE u.idUsuario = :id")
                .setParameter("id", idUsuario)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void bloquearCuenta(Integer idUsuario) {
        em.createQuery(
                "UPDATE UsuarioModel u SET u.cuentaBloqueada = true, " +
                "u.fechaBloqueo = :ahora WHERE u.idUsuario = :id")
                .setParameter("ahora", java.time.LocalDateTime.now())
                .setParameter("id", idUsuario)
                .executeUpdate();
    }

    @Override
    @Transactional
    public boolean estaBloqueada(Integer idUsuario) {
        UsuarioModel u = em.find(UsuarioModel.class, idUsuario);
        if (u == null || !u.isCuentaBloqueada()) return false;

        // Si ya pasaron 2 minutos → desbloquear automáticamente
        if (u.getFechaBloqueo() != null &&
            u.getFechaBloqueo().plusMinutes(2).isBefore(java.time.LocalDateTime.now())) {
            
            em.createQuery(
                "UPDATE UsuarioModel u SET u.cuentaBloqueada = false, " +
                "u.intentosFallidos = 0, u.fechaBloqueo = null " +
                "WHERE u.idUsuario = :id")
                .setParameter("id", idUsuario)
                .executeUpdate();
            return false;
        }
        return true;
    }

    @Transactional
    private void desbloquearCuenta(Integer idUsuario) {
        em.createQuery(
                "UPDATE UsuarioModel u SET u.cuentaBloqueada = false, " +
                "u.intentosFallidos = 0, u.fechaBloqueo = null " +
                "WHERE u.idUsuario = :id")
                .setParameter("id", idUsuario)
                .executeUpdate();
    }

    @Override
    public int obtenerIntentosFallidos(Integer idUsuario) {
        return em.createQuery(
                "SELECT u.intentosFallidos FROM UsuarioModel u WHERE u.idUsuario = :id",
                Integer.class)
                .setParameter("id", idUsuario)
                .getSingleResult();
    }
}
