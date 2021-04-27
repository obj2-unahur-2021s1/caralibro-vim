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

    fun mejoresAmigos() = this.amigos.filter { amigo -> this.publicaciones.all { publicacion -> publicacion.esVisiblePor(amigo) } }
}
