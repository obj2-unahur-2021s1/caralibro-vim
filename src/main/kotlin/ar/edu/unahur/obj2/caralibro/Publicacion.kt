package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

enum class Visibilidad {
    PUBLICO, AMIGOS, PRIVADO, EXCLUIDOS
}

enum class Calidad {
    SD, HD720, HD1080
}

abstract class Publicacion (val owner: Usuario, var visibilidad: Visibilidad, val usuariosRestringidos: List<Usuario> = listOf() ){
    private val usuariosMeGusta = mutableListOf<Usuario>()

    abstract fun espacioQueOcupa(): Int

    fun meGusta(user: Usuario) {
        if (!usuariosMeGusta.contains(user)) {
            usuariosMeGusta.add(user)
        }
    }

    fun cantidadMeGusta() = usuariosMeGusta.size

    fun cambiarVisibilidad(nuevaVisibilidad: Visibilidad) {
        this.visibilidad = nuevaVisibilidad
    }

    fun obtenerUsuarios() = this.usuariosMeGusta

    fun esVisiblePor(usuario: Usuario) =
        when (visibilidad) {
            Visibilidad.PUBLICO -> true
            Visibilidad.AMIGOS -> owner.misAmigos().contains(usuario)
            Visibilidad.PRIVADO -> usuariosRestringidos.contains(usuario)
            Visibilidad.EXCLUIDOS -> !usuariosRestringidos.contains(usuario)
        }
}

class Foto(owner: Usuario, val alto: Int, val ancho: Int, visibilidad: Visibilidad, restingido: List<Usuario> = listOf()) : Publicacion(owner, visibilidad, restingido) {
    val factorDeCompresion = 0.7
    override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(owner: Usuario,  val contenido: String, visibilidad: Visibilidad, restingido: List<Usuario> = listOf()) : Publicacion(owner, visibilidad, restingido) {
    override fun espacioQueOcupa() = contenido.length
}

class Video(owner: Usuario, val duracion: Int, var calidad: Calidad = Calidad.SD, visibilidad: Visibilidad,  restingido: List<Usuario> = listOf()) : Publicacion(owner, visibilidad, restingido) {
    override fun espacioQueOcupa(): Int {
        when (calidad) {
            Calidad.HD720 -> return duracion * 3
            Calidad.HD1080 -> return duracion * 3 * 2
            else -> return duracion
        }
    }
}
