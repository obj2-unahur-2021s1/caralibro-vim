package ar.edu.unahur.obj2.caralibro
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PublicacionTest : DescribeSpec({
    describe("Publicacion") {
        val marito = Usuario()
        val rober = Usuario()
        val videoEnLaMontania = Video(marito, 60, Calidad.HD720, Visibilidad.PUBLICO)
        describe("Saber si un usuario puede ver una cierta publicacion.") {
            describe("Un usuario") {
                it("Puede ver la publicacion") {
                    videoEnLaMontania.esVisiblePor(marito).shouldBeTrue()
                }
                it("No puede ver la publicacion") {
                    videoEnLaMontania.cambiarVisibilidad(Visibilidad.AMIGOS)
                    videoEnLaMontania.esVisiblePor(marito).shouldBeFalse()
                }
                it("puede ver la publicacion porque es privado") {
                    var restringidos = listOf(rober)
                    val videoEnLaPlaya = Video(marito, 60, Calidad.HD720, Visibilidad.PRIVADO, restringidos)
                    marito.agregarUnAmigo(rober)
                    videoEnLaPlaya.esVisiblePor(rober).shouldBeTrue()
                }
                it("no puede ver la publicacion porque es excluido") {
                    var excluido = listOf(rober)
                    val videoEnLaPlaya = Texto(marito, "Esta es mi publicacion pero no para los excluidos.", Visibilidad.EXCLUIDOS, excluido)
                    marito.agregarUnAmigo(rober)
                    videoEnLaPlaya.esVisiblePor(rober).shouldBeFalse()
                }
            }
        }
    }
})