import org.openrndr.application


fun main() {
    application {

        configure {
            width = 600
            height = 600
        }

        program {
            var spout = Spout()

            val texture = spout.receiveColorBuffer()

            drawer.image(texture)

        }
    }
}