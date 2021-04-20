package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoEnLaMontania = Video(60)

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
          videoEnLaMontania.calidad = 720
          videoEnLaMontania.espacioQueOcupa().shouldBe(180)
        }
        it("ocupa tantos bytes como su duracion por el doble de 720") {
          videoEnLaMontania.calidad = 1080
          videoEnLaMontania.espacioQueOcupa().shouldBe(360)
        }
      }
    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoEnLaMontania)
        juana.espacioDePublicaciones().shouldBe(550608)
      }
    }
  }
})
