import org.lwjgl.opengl.GL31C
import org.openrndr.Program
import org.openrndr.draw.colorBuffer

import org.lwjgl.opengl.GL33C.GL_TEXTURE_RECTANGLE
import org.openrndr.draw.ColorBuffer
import org.openrndr.internal.gl3.ColorBufferGL3

class Spout() {

    val spoutPtr = JNISpout.init()
    var senderName = ""
    var dim = intArrayOf(1920, 1080)
    lateinit var parent: Program
    var bReceiverInitialized = false
    var invertMode = 0
    lateinit var cb : ColorBuffer

    init {


    }

    fun createReceiver(name: String): Boolean {

        dim[0] = 1920
        dim[1] = 1080
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

        if(cb == null || dim[0] != cb.width || dim[1] != cb.height && dim[0] > 0 && dim[1] > 0 ) {
            cb = colorBuffer(dim[0], dim[1])
        } else {

            if(JNISpout.receiveTexture(dim, 0, 0, bInvert, spoutPtr)) {
                println("get texture")
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
            return cb
        }

    }

}