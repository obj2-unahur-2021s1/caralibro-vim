package ar.edu.unahur.obj2.caralibro

class Usuario() {
  val publicaciones = mutableListOf<Publicacion>()
  val amigos = mutableListOf<Usuario>()

  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
  }
  fun agregarUnAmigo(usuario: Usuario) {
    if (!amigos.contains(usuario)) {
      amigos.add(usuario)
    } else {
      throw error("Ya existe como amigo!")
    }
  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
  fun misAmigos() = amigos
  fun cantidadDeAmigos() = amigos.size

  fun esMasAmistoso(unAmigo: Usuario): Boolean {
    return this.cantidadDeAmigos() > unAmigo.cantidadDeAmigos()
  }

  fun mejoresAmigos(): List<Usuario> {
    val noAmigos = mutableListOf<Usuario>()
    val mejoresAmigos = mutableListOf<Usuario>()

      val publicacionesRestringidas = publicaciones.filter { it.visibilidad == Visibilidad.PRIVADO }
      val publicacionesExcluidos    = publicaciones.filter { it.visibilidad == Visibilidad.EXCLUIDOS}

    for(publacion in publicacionesExcluidos) {
        for(usuario in publacion.obtenerUsuarios()) {
            if (!noAmigos.contains(usuario)) {
                noAmigos.add(usuario)
            }
        }
    }
    for(publacion in publicacionesRestringidas) {
        for(usuario in publacion.obtenerUsuarios()) {
            if (!mejoresAmigos.contains(usuario)) {
              mejoresAmigos.add(usuario)
            }
        }
    }

    return amigos.filter { !noAmigos.contains(it) && mejoresAmigos.contains(it) }
  }
}
