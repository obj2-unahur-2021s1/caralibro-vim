package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {
        val juana = Usuario()
        val marito = Usuario()
        val rober = Usuario()
        val saludoCumpleanios = Texto(juana,"Felicidades Pepito, que los cumplas muy feliz", Visibilidad.PUBLICO)
        val fotoEnCuzco = Foto(juana,768, 1024, Visibilidad.PUBLICO)
        val fotoEnLaBombonera = Foto(marito,768, 1024, Visibilidad.PUBLICO)
        val videoEnLaMontania = Video(rober, 60,Calidad.HD1080, Visibilidad.PUBLICO)

        describe("Una publicación") {
            describe("de tipo foto") {
                it("ocupa ancho * alto * compresion bytes") {
                    fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
                }
            }

            describe("de tipo texto") {
                it("ocupa tantos bytes como su longitud") {
                    saludoCumpleanios.espacioQueOcupa().shouldBe(45)
                }
            }

            describe("de tipo video") {
                it("ocupa tantos bytes como su duracion SD") {
                    videoEnLaMontania.espacioQueOcupa().shouldBe(60)
                }
                it("ocupa tantos bytes como su duracion por 3") {
                    videoEnLaMontania.calidad = Calidad.HD720
                    videoEnLaMontania.espacioQueOcupa().shouldBe(180)
                }
                it("ocupa tantos bytes como su duracion por el doble de 720") {
                    videoEnLaMontania.calidad = Calidad.HD1080
                    videoEnLaMontania.espacioQueOcupa().shouldBe(360)
                }
            }
        }

        describe("Un usuario") {

            it("puede calcular el espacio que ocupan sus publicaciones") {
                juana.agregarPublicacion(fotoEnCuzco)
                juana.agregarPublicacion(saludoCumpleanios)
                juana.agregarPublicacion(videoEnLaMontania)
                juana.espacioDePublicaciones().shouldBe(550608)
            }

            it("El usuario es igual de amistoso que otro") {
                rober.agregarUnAmigo(juana)
                rober.agregarUnAmigo(marito)

                juana.agregarUnAmigo(rober)
                juana.agregarUnAmigo(marito)

                rober.esMasAmistoso(juana).shouldBeFalse()
            }

            it("El usuario es mas amistoso que otro") {
                rober.agregarUnAmigo(juana)
                rober.agregarUnAmigo(marito)

                juana.agregarUnAmigo(rober)

                rober.esMasAmistoso(juana).shouldBeTrue()
            }
        }

        describe("Me Gusta") {
            val federico = Usuario()
            val marta = Usuario()
            fotoEnLaBombonera.meGusta(federico)

            describe("Agregar un me gusta") {
                it("Verifico que haya un me gusta") {
                    fotoEnLaBombonera.cantidadMeGusta().shouldBe(1)
                }
            }
            describe("Darle al me gusta de nuevo") {
                fotoEnLaBombonera.meGusta(federico)
                it("Le vuelvo a dar me gusta") {
                    fotoEnLaBombonera.cantidadMeGusta().shouldBe(1)
                }
            }
            describe("Contar la cantidad de votaciones") {
                fotoEnLaBombonera.meGusta(marta)
                it("Verifico que tenga 2 Me gusta") {
                    fotoEnLaBombonera.cantidadMeGusta().shouldBe(2)

                }
            }
        }
    }
})
