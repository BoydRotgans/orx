import org.lwjgl.opengl.ARBInternalformatQuery2.*
import org.openrndr.Program
import org.openrndr.draw.*
import org.openrndr.internal.gl3.ColorBufferDataGL3
import org.openrndr.internal.gl3.ColorBufferGL3
import spout.JNISpout


class Spout() {

    var spoutPtr = JNISpout.init()
    var senderName = ""
    var dim = intArrayOf(250, 250)
    lateinit var parent: Program
    var bReceiverInitialized = false
    var invertMode = 0
    var counter = 0
    var cb = colorBuffer(dim[0], dim[1]) as ColorBufferGL3

    init {
        cb = colorBuffer(dim[0], dim[1]) as ColorBufferGL3
    }

    fun createReceiver(name: String): Boolean {

        var newName = ""

        if(JNISpout.createReceiver(name, dim, spoutPtr)) {
            newName = JNISpout.getSpoutSenderName(spoutPtr)

            if(newName != null && newName.length > 0) {
                bReceiverInitialized = true
                senderName = newName
                println("Found sender")
            }
        } else {
            bReceiverInitialized = false
            return false
        }

        return true
    }

    fun receiveTexture(): Boolean {

        if(!bReceiverInitialized) {
            createReceiver("")
            return false
        }

        var bInvert = true
        if(invertMode >= 0) {
            bInvert = (invertMode == 1)
        }

        if(dim[0] != cb.width || dim[1] != cb.height && dim[0] > 0 && dim[1] > 0 ) {

            cb = colorBuffer(dim[0], dim[1]) as ColorBufferGL3
        } else {

            val target = cb.target
            val texture = cb.texture

            if(JNISpout.receiveTexture(dim, texture, target, bInvert, spoutPtr)) {
                val textureID = JNISpout.getTextureID(spoutPtr)

            } else {
                JNISpout.releaseReceiver(spoutPtr)
                senderName = ""
                bReceiverInitialized = false
                return false
            }
        }
        return true
    }

    fun receiveColorBuffer() : ColorBuffer {
        if(this.receiveTexture()) {
            return cb
        } else {
            return colorBuffer(10, 10)
        }
    }

}