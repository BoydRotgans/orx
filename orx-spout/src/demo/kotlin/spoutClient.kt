import org.openrndr.application
import org.openrndr.color.ColorRGBa


fun main() {
    application {

        configure {
            width = 1280
            height = 720
        }

        program {
            var spout = Spout()

            extend {
                drawer.background(ColorRGBa.GREEN)

                val texture = spout.receiveColorBuffer()

                drawer.image(texture, 0.0, 0.0, width*1.0, height*1.0)

            }

        }
    }
}